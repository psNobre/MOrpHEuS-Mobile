package br.com.morpheus.mobile.context;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

import java.util.HashMap;

import br.com.morpheus.mobile.listeners.ContextListener;
import br.com.morpheus.mobile.listeners.LoccamConnectedListener;
import br.ufc.great.loccamlib.LoccamListener;
import br.ufc.great.loccamlib.LoccamManager;
import br.ufc.great.syssu.base.Tuple;
import br.ufc.great.syssu.base.interfaces.IClientReaction;
import br.ufc.great.syssu.base.interfaces.ISysSUService;

/**
 * Created by pedronobre on 05/10/2015.
 */
public class ContextManager implements LoccamListener {

    private static ContextManager instance;
    private Context context;
    private HashMap<String, ContextListener> contextListeners;
    private HashMap<String, IClientReaction> reactions;
    private LoccamConnectedListener loccamConnectedListener;
    private LoccamManager loccamManager;
    private Object lock;

    private ContextManager() {
        contextListeners = new HashMap<>();
        lock = new Object();
        reactions = new HashMap<>();
    }


    public static boolean isServiceConnected = false;

    public static ContextManager getInstance() {
        if (instance == null) {
            instance = new ContextManager();
        }
        return instance;
    }


    public void subscribe(IClientReaction iClientReaction, String contextKey) {
        loccamManager.init(contextKey);
        loccamManager.getASync(iClientReaction, contextKey);
    }
    public void finishSubscibe(String contextKey) {
        loccamManager.finish(contextKey);
    }

    public void init(String key) {
        loccamManager.init(key);
    }

    public void connect(Context context, String appId, LoccamConnectedListener loccamConnectedListener) {
        this.context = context;
        this.loccamConnectedListener = loccamConnectedListener;

        loccamManager = new LoccamManager(context, appId);
        loccamManager.connect(this);
    }

    public void disconnect() {
        if (loccamManager != null) {
            loccamManager.disconnect();
        }
    }


    @Override
    public void onServiceConnected(ISysSUService iSysSUService) {
        isServiceConnected = true;
        loccamConnectedListener.onLoccamConnected();
    }

    @Override
    public void onServiceDisconnected() {
        isServiceConnected = false;
    }

    @Override
    public void onLoccamException(Exception e) {
        isServiceConnected = false;
    }

    public void registerListener(ContextListener listener) {
        contextListeners.put(listener.getContextKey(), listener);

        loccamManager.init(listener.getContextKey());
        IClientReaction reaction = new IClientReaction.Stub() {
            @Override
            public void react(Tuple tuple) throws RemoteException {
                ContextListener contextListener = contextListeners.get(tuple.getField("ContextKey").getValue().toString());
                String data = tuple.getField("values").getValue().toString();
                data = data.substring(1, data.length() - 1);
                Log.d("DATA", data);
                contextListener.onContextReady(data);
            }
        };
        loccamManager.getAsync(reaction, "put", listener.getContextKey(), null);
        reactions.put(listener.getContextKey(), reaction);
    }

    public void unregisterListener(ContextListener listener) {
        reactions.remove(listener.getContextKey());
        loccamManager.finish(listener.getContextKey());
        if (contextListeners.containsKey(listener.getContextKey())) {
            contextListeners.remove(listener.getContextKey());
        }
    }

}
