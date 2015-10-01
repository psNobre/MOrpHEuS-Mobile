package br.com.morpheus.mobile;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import br.com.morpheus.mobile.model.User;


public class MainActivity extends ActionBarActivity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        new HttpRequestTask().execute();

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

    public class HttpRequestTask extends AsyncTask<Void,Void,User> {
        @Override
        protected User doInBackground(Void... params) {
            User user = null;
            try {
                final String url = "http://192.168.25.28:8080/showUser";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                user = restTemplate.getForObject(url, User.class);
                return user;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            textView1 = (TextView)findViewById(R.id.textView1);
            textView2 = (TextView)findViewById(R.id.textView2);
            textView3 = (TextView)findViewById(R.id.textView3);
            textView4 = (TextView)findViewById(R.id.textView4);

            textView1.setText(user.getNome());
            textView2.setText(user.getEmail());
            textView3.setText(user.getLogin());
            textView4.setText(user.getSenha());

        }
    }
}


