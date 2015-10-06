package br.com.morpheus.mobile.context;

import android.util.Log;

import br.com.morpheus.mobile.model.Sensor;

/**
 * Created by pedronobre on 05/10/2015.
 */
public class ContextKey {
    public static String getContextkeyGetCacs() {
        return "context.ambient.cacs";
    }
    Sensor sensor = new Sensor();

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
                Log.d(TAG,"Retornado "+CIB_AIRFLOW);
                break;
            case CIB_BODY_POSITION:
                sensor.setCib(CIB_BODY_POSITION);
                sensor.setNome("Posicao Corporal");
                Log.d(TAG, "Retornado " + CIB_BODY_POSITION);
                break;
            case CIB_BODY_TEMPERATURE:
                sensor.setCib(CIB_BODY_TEMPERATURE);
                sensor.setNome("Temperatura Corporal");
                Log.d(TAG, "Retornado " + CIB_BODY_TEMPERATURE);
                break;
            case CIB_ELECTROCARDIOGRAM:
                sensor.setCib(CIB_ELECTROCARDIOGRAM);
                sensor.setNome("Eletrocardiograma");
                Log.d(TAG, "Retornado " + CIB_ELECTROCARDIOGRAM);
                break;
            case CIB_MYOCARDIOGRAM:
                sensor.setCib(CIB_MYOCARDIOGRAM);
                sensor.setNome("Miocardiograma");
                Log.d(TAG, "Retornado " + CIB_MYOCARDIOGRAM);
                break;
            case CIB_GLUCOMETER:
                sensor.setCib(CIB_GLUCOMETER);
                sensor.setNome("Glicosimetro");
                Log.d(TAG, "Retornado " + CIB_GLUCOMETER);
                break;
            case CIB_GALVANIK_SKIN_RESPONSE:
                sensor.setCib(CIB_GALVANIK_SKIN_RESPONSE);
                sensor.setNome("Resistencia da Pele");
                Log.d(TAG, "Retornado " + CIB_GALVANIK_SKIN_RESPONSE);
                break;
            case CIB_OXIMETER:
                sensor.setCib(CIB_OXIMETER);
                sensor.setNome("Oximetro");
                Log.d(TAG, "Retornado " + CIB_OXIMETER);
                break;
            case CIB_PRESSURE_BLOOD:
                sensor.setCib(CIB_PRESSURE_BLOOD);
                sensor.setNome("Pressao Arterial");
                Log.d(TAG, "Retornado " + CIB_PRESSURE_BLOOD);
                break;
            case CIB_PULSE:
                sensor.setCib(CIB_PULSE);
                sensor.setNome("Pulso");
                Log.d(TAG, "Retornado " + CIB_PULSE);
                break;
        }
        return sensor;
    }

}
