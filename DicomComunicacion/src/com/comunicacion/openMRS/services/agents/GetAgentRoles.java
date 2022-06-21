package com.comunicacion.openMRS.services.agents;

import jade.content.AgentAction;

public class GetAgentRoles implements AgentAction{ 
	
	
	private static final long serialVersionUID = 1L;
	private String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	

}
