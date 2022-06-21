package com.comunicacion.openMRS.Beans;

import jade.content.Concept;
import jade.util.leap.List;

public class Roles  implements Concept{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private List listaRol;
	public Roles() {}
	
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List getListaRol() {
		return listaRol;
	}
	public void setListaRol(List listaRol) {
		this.listaRol = listaRol;
	}
	
	public Roles(String userId, List listaRol) {
		this.userId = userId;
		this.listaRol = listaRol;
	}
	
	
}
