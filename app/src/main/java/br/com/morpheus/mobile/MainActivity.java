package br.com.morpheus.mobile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import br.com.morpheus.mobile.config.Config;
import br.com.morpheus.mobile.model.User;
import br.com.morpheus.mobile.util.AndroidSession;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getName();

    private Button loginButton;
    private EditText loginEditText;
    private EditText senhaEditText;
    private AndroidSession androidSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        androidSession = new AndroidSession(MainActivity.this);
        if (androidSession.isLoggedIn()) {
            Intent intent = new Intent(MainActivity.this, PacienteActivity.class);
            startActivity(intent);
            finish();
        }

        loginButton = (Button) findViewById(R.id.button);
        loginEditText = (EditText) findViewById(R.id.etLogin);
        senhaEditText = (EditText) findViewById(R.id.etSenha);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()) {
                    Log.d(TAG, "Executando HttpRequest para login...");
                    new HttpRequestLogin(MainActivity.this).execute(loginEditText.getText().toString(), senhaEditText.getText().toString());
                } else {
                    Log.e(TAG, "Dispositivos sem conexão...");
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setIcon(R.drawable.ic_alert_circle_grey600_36dp);
                    alertDialog.setTitle("MOrpHEuS");
                    alertDialog.setMessage("Dispositivo sem Conexão");
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            onResume();
                        }
                    });
                    alertDialog.show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sair) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isConnected() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }

    public class HttpRequestLogin extends AsyncTask<String, Void, User> {
        private final String TAG = this.getClass().getName();
        private Context context;
        private ProgressDialog pgDialog = new ProgressDialog(MainActivity.this);

        public HttpRequestLogin(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgDialog.setTitle("Conectando...");
            pgDialog.show();
        }

        @Override
        protected User doInBackground(String... params) {
            User user = new User();

            HashMap<String, String> authUser = new HashMap<>();
            authUser.put("login", params[0]);
            authUser.put("senha", params[1]);
            try {
                final String url = "http://" + Config.SERVER_IP_GREat + ":" + Config.SERVER_PORT + "/mobileLogin";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                user = restTemplate.postForObject(url, authUser, User.class);
                return user;
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
            return user;
        }

        @Override
        protected void onPostExecute(User user) {

            if (user.getLogin() != null) {
                androidSession.setUserOnSession(Config.KEY_USER_LOGADO, user);
                pgDialog.dismiss();
                Intent intent = new Intent(context, PacienteActivity.class);
                startActivity(intent);
                finish();

                Log.d(TAG, "Logando user no Sistema.");
            } else {
                pgDialog.dismiss();
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setIcon(R.drawable.ic_alert_circle_grey600_36dp);
                alertDialog.setTitle("MOrpHEuS");
                alertDialog.setMessage("Usuário não encontrado, tente novamente.");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        onResume();
                    }
                });
                alertDialog.show();
                Log.e(TAG, "User não pode Logar no Sistema.");
            }

        }

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}


