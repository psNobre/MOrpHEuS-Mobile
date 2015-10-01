package br.com.morpheus.mobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


import br.com.morpheus.mobile.model.Paciente;
import br.com.morpheus.mobile.model.User;


public class MainActivity extends ActionBarActivity {
    private Button loginButton;
    private EditText loginEditText;
    private EditText senhaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = (Button)findViewById(R.id.button);

        loginEditText = (EditText)findViewById(R.id.etLogin);
        senhaEditText = (EditText)findViewById(R.id.etSenha);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpRequestLogin().execute(loginEditText.getText().toString(),senhaEditText.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class HttpRequestLogin extends AsyncTask<String,Void,User> {

        @Override
        protected User doInBackground(String... params) {
            User user = new User();

            HashMap<String,String> authUser = new HashMap<>();
            authUser.put("login", params[0]);
            authUser.put("senha", params[1]);

            try {
                final String url = "http://192.168.25.28:8080/loginUserMobile";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                user = restTemplate.postForObject(url, authUser ,User.class);
                return user;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);

            if (user.getLogin().equals("-1")){
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("MOrpHEuS");
                alertDialog.setMessage("Usuário ou Senha não encontrado, tente novamente!");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        onResume();
                    }
                });

                alertDialog.show();

            }else {
                Intent intent = new Intent(MainActivity.this, PacienteActivity.class);
                startActivity(intent);
                finish();
            }


        }

    }

}


