package com.comunicacion.openMRS.Beans;

import jade.content.Concept;

public class Atiende implements Concept{
	
	private String encounter_id;
	private String identifier;
	private String person_id;
	private String given_name;
	private String family_name;
	
	public Atiende() {}

	public String getEncounter_id() {
		return encounter_id;
	}

	public void setEncounter_id(String encounter_id) {
		this.encounter_id = encounter_id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

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
	

	
	public Atiende(String encounter_id,String identifier, String person_id, String given_name, String family_name ) {
		this.encounter_id = encounter_id;
	    this.identifier=identifier;
		this.person_id=person_id;
		this.family_name=family_name;
		this.given_name=given_name;
		
	}
	

}
