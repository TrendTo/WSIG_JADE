package com.comunicacion.openMRS.services.agents;

import jade.content.AgentAction;

/**
 * Define las acciones que el agente va a entender.	
 * @author Kleber Toaquiza
 */
public class RecuperarDatosFormularioAccion implements AgentAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7909627337887457380L;
	
	private String cadenaDatosFormulario;
	private String cadenaDatosImagenIn;

	public String getCadenaDatosFormulario() {
		return cadenaDatosFormulario;
	}

	public void setCadenaDatosFormulario(String cadenaDatosFormulario) {
		this.cadenaDatosFormulario = cadenaDatosFormulario;
	}

	public String getCadenaDatosImagenIn() {
		return cadenaDatosImagenIn;
	}

	public void setCadenaDatosImagenIn(String cadenaDatosImagenIn) {
		this.cadenaDatosImagenIn = cadenaDatosImagenIn;
	}
	
}
