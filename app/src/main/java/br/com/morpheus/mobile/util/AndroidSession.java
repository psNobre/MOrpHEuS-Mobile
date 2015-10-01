package br.com.morpheus.mobile.util;

import java.util.HashMap;

/**
 * Created by pedronobre on 01/10/2015.
 */
public class AndroidSession {

    private static HashMap<String, Object> session;

    public static void set(String key, Object value) {
        if(session == null) {
            session = new HashMap<String, Object>();
        }
        session.put(key, value);
    }

    public static Object  get(String key) {
       return session.get(key);
    }

}