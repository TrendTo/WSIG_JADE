package com.comunicacion.openMRS.Beans;


import jade.content.Concept;
import jade.util.leap.List;

public class Usuarios implements Concept{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String person_id;
	private String identifier;
	private String given_name;
	private String family_name;
	private String value;
	
	
	
	
	
	public Usuarios() {}
	
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	public String getPerson_id() {
		return person_id;
	}



	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}



	public String getIdentifier() {
		return identifier;
	}



	public void setIdentifier(String identifier) {
		this.identifier = identifier;
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



	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}

	
	
		public Usuarios(String person_id,String identifier, String given_name, String family_name, String value ) {
		this.person_id = person_id;
	    this.identifier=identifier;
		this.given_name=given_name;
		this.family_name=family_name;
		this.value=value;
		
	}
	
}
