package br.com.morpheus.mobile.httprequest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import br.com.morpheus.mobile.config.Config;
import br.com.morpheus.mobile.model.User;
import br.com.morpheus.mobile.util.AndroidSession;

/**
 * Created by pedronobre on 03/10/2015.
 */
public class HttpRequestLogin extends AsyncTask<String,Void,User> {

    private final String TAG = this.getClass().getName();

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public HttpRequestLogin(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        sharedPreferences = context.getSharedPreferences(Config.MY_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    protected User doInBackground(String... params) {
        User user = new User();

        HashMap<String, String> authUser = new HashMap<>();
        authUser.put("login", params[0]);
        authUser.put("senha", params[1]);

        try {
            final String url = "http://" + Config.SERVER_IP + ":" + Config.SERVER_PORT + "/mobileLogin";
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
        AndroidSession androidSession = new AndroidSession(context);
        if (user.getLogin() != null) {
            androidSession.setUserOnSession(Config.KEY_USER_LOGADO, user);
            Log.e(TAG, "Logando user no Sistema.");
        }else {
            Log.e(TAG, "User não pode Logar no Sistema.");
        }
    }
}

