package br.com.morpheus.mobile.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.morpheus.mobile.R;
import br.com.morpheus.mobile.context.ContextKey;
import br.com.morpheus.mobile.context.ContextManager;
import br.com.morpheus.mobile.listeners.ContextListener;
import br.com.morpheus.mobile.listeners.OnFragmentInteractionListener;


public class FragmentBodyTemperature extends Fragment implements ContextListener,OnFragmentInteractionListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView textView;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static FragmentBodyTemperature newInstance(String param1, String param2) {
        FragmentBodyTemperature fragment = new FragmentBodyTemperature();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentBodyTemperature() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        ContextManager.getInstance().registerListener(this);
        
    }

    @Override
    public void onPause() {
        super.onPause();
        ContextManager.getInstance().unregisterListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("CHAMEI DESTROY","CHAMEI PORRA");
        ContextManager.getInstance().unregisterListener(this);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_body_temperature, container, false);
        textView = (TextView) view.findViewById(R.id.temperature);
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onContextReady(final String data) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(data);
            }
        });

        textView.setText(data);

    }

    @Override
    public String getContextKey() {
        return ContextKey.CIB_BODY_TEMPERATURE;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
