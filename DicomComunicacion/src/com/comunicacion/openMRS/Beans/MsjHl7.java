package com.comunicacion.openMRS.Beans;

import jade.content.Concept;

public class MsjHl7 implements Concept{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String Hl7xml;
	private String versionHl7;
	private String mensajeHl7;
	private String listaLogMensajesHl7;
	
	
	public MsjHl7() {}
	
	public String getHl7xml() {
		return Hl7xml;
	}
	public void setHl7xml(String hl7xml) {
		Hl7xml = hl7xml;
	}
	public String getVersionHl7() {
		return versionHl7;
	}
	public void setVersionHl7(String versionHl7) {
		this.versionHl7 = versionHl7;
	}
	public String getMensajeHl7() {
		return mensajeHl7;
	}
	public void setMensajeHl7(String mensajeHl7) {
		this.mensajeHl7 = mensajeHl7;
	}
	public String getListaLogMensajesHl7() {
		return listaLogMensajesHl7;
	}
	public void setListaLogMensajesHl7(String listaLogMensajesHl7) {
		this.listaLogMensajesHl7 = listaLogMensajesHl7;
	}
	
	

	public MsjHl7(String Hl7xml,String versionHl7, String mensajeHl7, String listaLogMensajesHl7 ) {
	this.Hl7xml = Hl7xml;
    this.versionHl7=versionHl7;
	this.mensajeHl7=mensajeHl7;
	this.listaLogMensajesHl7=listaLogMensajesHl7;
	
	
}


}
