package br.com.morpheus.mobile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorValor {
	

	private int id;
	

	private String valor;

	private Timestamp data;

	private Sensor sensor;
	

	private Paciente paciente;
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
	public Sensor getSensor() {
		return sensor;
	}
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	@Override
	public String toString() {
		return "SensorValor [id=" + id + ", valor=" + valor + ", data=" + data + "]";
	}
	
	
	
}
