package com.comunicacion.openMRS.services.agents;

import jade.content.AgentAction;

public class GetAgentAtiende implements AgentAction{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = -90203074100042809L;
	private String idPacienteWeb;

	public String getIdPacienteWeb() {
		return idPacienteWeb;
	}

	public void setIdPacienteWeb(String idPacienteWeb) {
		this.idPacienteWeb = idPacienteWeb;
	}
	
	

}
