package br.com.morpheus.mobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.morpheus.mobile.httprequest.HttpRequestLogin;
import br.com.morpheus.mobile.util.AndroidSession;


public class MainActivity extends ActionBarActivity {

    private Button loginButton;
    private EditText loginEditText;
    private EditText senhaEditText;
    private AndroidSession androidSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidSession = new AndroidSession(MainActivity.this);
        if (androidSession.isLoggedIn()) {
            Intent intent = new Intent(MainActivity.this, PacienteActivity.class);
            startActivity(intent);
            finish();
        }

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
                new HttpRequestLogin(MainActivity.this).execute(loginEditText.getText().toString(), senhaEditText.getText().toString());
                // Error AssynkTask tão rapido q ão reconhece de primeira
                if (androidSession.isLoggedIn()) {
                    Intent intent = new Intent(MainActivity.this, PacienteActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("MOrpHEuS Alerta!");
                    alertDialog.setMessage("Usuário não encontrado, tente novamente.");
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
        if (id == R.id.sair) {
            androidSession.logoutSystem();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}


