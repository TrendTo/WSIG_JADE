package com.comunicacion.openMRS.Beans;

import jade.content.Concept;

public class Proveedores  implements Concept{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String person_id;
	private String given_name;
	private String family_name;
	private String name;
	private String username;
	
	
	public Proveedores() {}
	
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	public String getGiven_name() {
		return given_name;
	}
	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}
	public String getFamily_name() {
		return family_name;
	}
	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	
	
	public Proveedores(String person_id,String given_name, String family_name, String name, String username ) {
		this.person_id = person_id;
	    this.given_name=given_name;
		this.name=name;
		this.family_name=family_name;
		this.username=username;
		
	}
	
}
