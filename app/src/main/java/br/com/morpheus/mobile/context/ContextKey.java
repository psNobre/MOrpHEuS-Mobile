package br.com.morpheus.mobile.context;

import android.support.v4.app.Fragment;
import android.util.Log;

import br.com.morpheus.mobile.R;
import br.com.morpheus.mobile.fragments.FragmentAirFlow;
import br.com.morpheus.mobile.fragments.FragmentBodyTemperature;
import br.com.morpheus.mobile.model.Sensor;

/**
 * Created by pedronobre on 05/10/2015.
 */
public class ContextKey {

    public static String getContextkeyGetCacs() {
        return "context.ambient.cacs";
    }

    public static final String CIB_BODY_TEMPERATURE = "context.health.arduino.bodytemperature";
    public static final String CIB_ELECTROCARDIOGRAM = "context.health.arduino.electrocardiogram";
    public static final String CIB_MYOCARDIOGRAM = "context.health.arduino.myocardiogram";
    public static final String CIB_AIRFLOW = "context.health.arduino.airflow";
    public static final String CIB_PRESSURE_BLOOD = "context.health.arduino.pressureblood";
    public static final String CIB_BODY_POSITION = "context.health.arduino.bodyposition";
    public static final String CIB_GALVANIK_SKIN_RESPONSE = "context.health.arduino.galvanikskin";
    public static final String CIB_GLUCOMETER = "context.health.arduino.glucometer";
    public static final String CIB_OXIMETER = "context.health.arduino.oximeter";
    public static final String CIB_PULSE = "context.health.arduino.pulse";

    public static final Sensor getSensorInfo(String contextKey) {
        final String TAG = "ContextKey";
        Sensor sensor = new Sensor();
        switch (contextKey) {

            case CIB_AIRFLOW:
                sensor.setNome("Fluxo de Ar");
                sensor.setCib(CIB_AIRFLOW);
                sensor.setIdOfResource(R.drawable.airflow);
                Log.d(TAG, "Retornado " + CIB_AIRFLOW);
                break;
            case CIB_BODY_POSITION:
                sensor.setCib(CIB_BODY_POSITION);
                sensor.setNome("Posicao Corporal");
                sensor.setIdOfResource(R.drawable.position);
                Log.d(TAG, "Retornado " + CIB_BODY_POSITION);
                break;
            case CIB_BODY_TEMPERATURE:
                sensor.setCib(CIB_BODY_TEMPERATURE);
                sensor.setNome("Temperatura Corporal");
                sensor.setIdOfResource(R.drawable.temperature);
                Log.d(TAG, "Retornado " + CIB_BODY_TEMPERATURE);
                break;
            case CIB_ELECTROCARDIOGRAM:
                sensor.setCib(CIB_ELECTROCARDIOGRAM);
                sensor.setNome("Eletrocardiograma");
                sensor.setIdOfResource(R.drawable.electrocardiogram);
                Log.d(TAG, "Retornado " + CIB_ELECTROCARDIOGRAM);
                break;
            case CIB_MYOCARDIOGRAM:
                sensor.setCib(CIB_MYOCARDIOGRAM);
                sensor.setNome("Miocardiograma");
                sensor.setIdOfResource(R.drawable.myocardiogram);
                Log.d(TAG, "Retornado " + CIB_MYOCARDIOGRAM);
                break;
            case CIB_GLUCOMETER:
                sensor.setCib(CIB_GLUCOMETER);
                sensor.setNome("Glicosimetro");
                sensor.setIdOfResource(R.drawable.glucometer);
                Log.d(TAG, "Retornado " + CIB_GLUCOMETER);
                break;
            case CIB_GALVANIK_SKIN_RESPONSE:
                sensor.setCib(CIB_GALVANIK_SKIN_RESPONSE);
                sensor.setNome("Resistencia da Pele");
                sensor.setIdOfResource(R.drawable.galvanikskin);
                Log.d(TAG, "Retornado " + CIB_GALVANIK_SKIN_RESPONSE);
                break;
            case CIB_OXIMETER:
                sensor.setCib(CIB_OXIMETER);
                sensor.setNome("Oximetro");
                sensor.setIdOfResource(R.drawable.oximeter);
                Log.d(TAG, "Retornado " + CIB_OXIMETER);
                break;
            case CIB_PRESSURE_BLOOD:
                sensor.setCib(CIB_PRESSURE_BLOOD);
                sensor.setNome("Pressao Arterial");
                sensor.setIdOfResource(R.drawable.pressureblood);
                Log.d(TAG, "Retornado " + CIB_PRESSURE_BLOOD);
                break;
            case CIB_PULSE:
                sensor.setCib(CIB_PULSE);
                sensor.setNome("Pulso");
                sensor.setIdOfResource(R.drawable.pulse);
                Log.d(TAG, "Retornado " + CIB_PULSE);
                break;
        }
        return sensor;
    }

    public static final Fragment getCorrespondentFragment(String contextKey) {
        Log.d("Correspondent key", contextKey);
        Fragment fragment = new Fragment();
        switch (contextKey) {
            case CIB_AIRFLOW:
                fragment = new FragmentAirFlow();
                break;
            case CIB_BODY_POSITION:
                //TODO
                break;
            case CIB_BODY_TEMPERATURE:
                fragment = new FragmentBodyTemperature();
                break;
            case CIB_ELECTROCARDIOGRAM:
                //TODO
                break;
            case CIB_MYOCARDIOGRAM:
                //TODO
                break;
            case CIB_GLUCOMETER:
                //TODO
                break;
            case CIB_GALVANIK_SKIN_RESPONSE:
                //TODO
                break;
            case CIB_OXIMETER:
                //TODO
                break;
            case CIB_PRESSURE_BLOOD:
                //TODO
                break;
            case CIB_PULSE:
                //TODO
                break;

        }
        return fragment;
    }

}
