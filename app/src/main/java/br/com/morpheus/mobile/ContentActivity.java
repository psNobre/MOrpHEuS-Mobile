package br.com.morpheus.mobile;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.morpheus.mobile.context.ContextKey;
import br.com.morpheus.mobile.fragments.FragmentBodyTemperature;
import br.com.morpheus.mobile.listeners.OnFragmentInteractionListener;

public class ContentActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private String contextKey;
    private String contextSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        contextKey = getIntent().getStringExtra("context_key");
        contextSensor = getIntent().getStringExtra("context_sensor");
        fragmentManager = getSupportFragmentManager();

        getSupportActionBar().setTitle(contextSensor);
    }

    @Override
    protected void onResume() {
        super.onResume();

        fragment = ContextKey.getCorrespondentFragment(contextKey);

        if (fragment != null) {
            fragmentManager.beginTransaction().add(R.id.content_sensor, fragment, null).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_content, menu);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
