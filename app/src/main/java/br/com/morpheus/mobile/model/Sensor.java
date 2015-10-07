package br.com.morpheus.mobile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sensor {

	private String cib;
	private String nome;
	int idOfResource;

	public int getIdOfResource() {
		return idOfResource;
	}

	public void setIdOfResource(int idOfResource) {
		this.idOfResource = idOfResource;
	}

	private List<Paciente> pacientes;

	private List<SensorValor> sensorValors;

	//Getters and Setters
	public String getCib() {
		return cib;
	}

	public void setCib(String cib) {
		this.cib = cib;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public List<SensorValor> getSensorValors() {
		return sensorValors;
	}

	public void setSensorValors(List<SensorValor> sensorValors) {
		this.sensorValors = sensorValors;
	}
	
	

	
	
}
