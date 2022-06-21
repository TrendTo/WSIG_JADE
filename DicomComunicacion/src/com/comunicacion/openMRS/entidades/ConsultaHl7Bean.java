package com.comunicacion.openMRS.entidades;

import java.util.Date;

import jade.content.Concept;
import jade.core.AID;

public class ConsultaHl7Bean implements Concept{

	private AID agentAid;
	private Date startDate;
	private String version;
	private String encoding;
	private String configuration;
	
	public ConsultaHl7Bean() {
		
	}

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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	
	
	
	
}
