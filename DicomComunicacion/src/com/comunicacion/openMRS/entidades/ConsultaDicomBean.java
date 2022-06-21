package com.comunicacion.openMRS.entidades;

import jade.content.AgentAction;


public class ConsultaDicomBean implements AgentAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUsuario;
	private String nombreUsuario;
	private String idEspecialidad ;
	
	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(String idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	
	
	
}
