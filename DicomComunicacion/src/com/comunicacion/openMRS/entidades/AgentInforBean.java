package com.comunicacion.openMRS.entidades;

import java.util.Date;

import jade.content.Concept;
import jade.core.AID;


public class AgentInforBean implements Concept {
	
	private AID agentAid;
	private Date startDate;
	private String nombreEquipo;
	public AgentInforBean(){}

		
	public AID getAgentAid() {
		return agentAid;
	}

	public void setAgentAid(AID agentAid) {
		this.agentAid = agentAid;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}
	
}
