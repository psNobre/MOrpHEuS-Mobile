package br.com.morpheus.mobile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Paciente {
	


	private int id;
	

	private String diagnostico;
	


	private User user;

	private List<Medico> medicos;
	
	private List<Sensor> sensores;
	

	private List<SensorValor> sensorValors;
	
	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public List<Sensor> getSensors() {
		return sensores;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensores = sensors;
	}
	

	public List<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(List<Sensor> sensores) {
		this.sensores = sensores;
	}

	public List<SensorValor> getSensorValors() {
		return sensorValors;
	}

	public void setSensorValors(List<SensorValor> sensorValors) {
		this.sensorValors = sensorValors;
	}

	@Override
	public String toString() {
		return "Paciente [id=" + id + ", diagnostico=" + diagnostico + ", user=" + user + "]";
	}

	
}
