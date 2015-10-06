package br.com.morpheus.mobile.listeners;

import java.util.List;

/**
 * Created by pedronobre on 05/10/2015.
 */
public interface SensorCACListener {
    void onCACsUpdated(List<String> contextKeys);
}
