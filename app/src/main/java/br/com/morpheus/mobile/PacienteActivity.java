package br.com.morpheus.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import br.com.morpheus.mobile.adapters.AdapterCardSensor;
import br.com.morpheus.mobile.config.Config;
import br.com.morpheus.mobile.context.ContextKey;
import br.com.morpheus.mobile.context.ContextManager;
import br.com.morpheus.mobile.listeners.ContextListener;
import br.com.morpheus.mobile.listeners.LoccamConnectedListener;
import br.com.morpheus.mobile.model.Sensor;
import br.com.morpheus.mobile.util.AndroidSession;
import br.com.morpheus.mobile.util.RecyclerItemClickListener;

public class PacienteActivity extends AppCompatActivity implements ContextListener {
    private final String TAG = this.getClass().getName();

    private RecyclerView recyclerView;
    private AdapterCardSensor adapterCardSensor;

    private AndroidSession androidSession;
    private ArrayList<Sensor> sensors;
    private Sensor sensor;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
        intent = new Intent(PacienteActivity.this, ContentActivity.class);

        recyclerView = (RecyclerView) findViewById(R.id.card_list_view);
        recyclerView.setHasFixedSize(true);
        androidSession = new AndroidSession(PacienteActivity.this);
        sensors = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        adapterCardSensor = new AdapterCardSensor(PacienteActivity.this, sensors);
        recyclerView.setAdapter(adapterCardSensor);

        getSupportActionBar().setTitle(androidSession.getUserFromSession(Config.KEY_USER_LOGADO).getNome());

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Sensor sensor = sensors.get(position);
                intent.putExtra("context_sensor", sensor.getNome());
                intent.putExtra("context_key", sensor.getCib());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "Long Click", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        ContextManager.getInstance().connect(this.getApplicationContext(),
                getResources().getResourcePackageName(R.string.app_name), new LoccamConnectedListener() {
                    @Override
                    public void onLoccamConnected() {
                        ContextManager.getInstance().registerListener(PacienteActivity.this);
                    }
                });
    }



    @Override
    protected void onPause() {
        super.onPause();
        ContextManager.getInstance().unregisterListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ContextManager.getInstance().disconnect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_paciente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sair) {
            androidSession.logoutSystem();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onContextReady(String data) {
        if (sensors.isEmpty()) { //tá dando mas tá errado mudar condição
            getSensores(data);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapterCardSensor.notifyDataSetChanged();

            }
        });
    }

    @Override
    public String getContextKey() {
        return ContextKey.getContextkeyGetCacs();
    }

    public void getSensores(String data){
        String format = data.replace("[", "").replace("]", "").replaceAll("[\"\']", "");

        int counter = format.split(",").length - 1;
        ArrayList<String> cacs = new ArrayList<>();

        for (int i = 0; i < counter + 1; i++) {
            cacs.add(format.split(",")[i]);
            sensor = ContextKey.getSensorInfo(cacs.get(i));
            sensors.add(sensor);
        }
    }

}
