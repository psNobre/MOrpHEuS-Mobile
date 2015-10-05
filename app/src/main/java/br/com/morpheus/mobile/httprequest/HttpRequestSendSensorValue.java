package br.com.morpheus.mobile.httprequest;

import android.content.Context;
import android.os.AsyncTask;
import br.com.morpheus.mobile.model.User;

/**
 * Created by pedronobre on 03/10/2015.
 */
public class HttpRequestSendSensorValue extends AsyncTask<String,Void,User> {

    private final String TAG = this.getClass().getName();
    private Context context;

    public HttpRequestSendSensorValue(Context context) {
        this.context = context;
    }

    @Override
    protected User doInBackground(String... params) {
        User user = new User();
        return user;
    }



}

