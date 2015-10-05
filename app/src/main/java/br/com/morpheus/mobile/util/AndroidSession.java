package br.com.morpheus.mobile.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import br.com.morpheus.mobile.config.Config;
import br.com.morpheus.mobile.model.User;

/**
 * Created by pedronobre on 01/10/2015.
 */
public class AndroidSession {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public AndroidSession(Context context) {
        sharedPreferences = context.getSharedPreferences(Config.MY_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setUserOnSession(String key, User user) {
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        editor.putString(key, userJson);
        editor.putBoolean(Config.KEY_USER_STATUS,true);
        editor.commit();
    }

    public User  getUserFromSession(String key) {
        String userJson = sharedPreferences.getString(key, null);
        User user = new Gson().fromJson(userJson, User.class);
        return user;
    }


    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(Config.KEY_USER_STATUS, false);
    }

    public void logoutSystem() {
        editor.clear();
        editor.commit();
    }

}