package br.com.morpheus.mobile.config;

/**
 * Created by pedronobre on 01/10/2015.
 */
public class Config {

    //CASA:192.168.25.28 GREat:10.101.45.52
    public static final String SERVER_IP = "10.101.45.52";
    public static final String SERVER_PORT = "8080";

    /**
     * Shared Preferences
     * **/
    public static final String MY_PREFERENCES = "morpheusPreference";
    public static final String KEY_USER_LOGADO = "userLogado";
    public static final String KEY_USER_STATUS = "statusUser";
    /**
     * Ramificações da Árvore de Contexto.
     * 			ambient.health.device.arduino.EXEMPLO
     * **/

    public static final String CIB_BODY_TEMPERATURE = "ambient.health.device.arduino.bodytemperature";
    public static final String CIB_ELECTROCARDIOGRAM = "ambient.health.device.arduino.electrocardiogram";
    public static final String CIB_MYOCARDIOGRAM = "ambient.health.device.arduino.myocardiogram";
    public static final String CIB_AIRFLOW = "ambient.health.device.arduino.airflow";
    public static final String CIB_PRESSURE_BLOOD = "ambient.health.device.arduino.pressureblood";
    public static final String CIB_BODY_POSITION = "ambient.health.device.arduino.bodyposition";
    public static final String CIB_GALVANIK_SKIN_RESPONSE = "ambient.health.device.arduino.galvanikskin";
    public static final String CIB_GLUCOMETER = "ambient.health.device.arduino.glucometer";
    public static final String CIB_OXIMETER = "ambient.health.device.arduino.oximeter";
    public static final String CIB_PULSE = "ambient.health.device.arduino.pulse";
}
