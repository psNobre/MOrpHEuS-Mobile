package br.com.morpheus.mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import br.com.morpheus.mobile.config.Config;
import br.com.morpheus.mobile.model.User;
import br.com.morpheus.mobile.util.AndroidSession;

public class PacienteActivity extends AppCompatActivity {
    TextView textView;
    private AndroidSession androidSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
        androidSession = new AndroidSession(PacienteActivity.this);

        textView = (TextView)findViewById(R.id.textView);

        AndroidSession androidSession = new AndroidSession(this);
        User user = androidSession.getUserFromSession(Config.KEY_USER_LOGADO);
        textView.setText(user.getNome());

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
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
