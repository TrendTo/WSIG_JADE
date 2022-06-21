package com.comunicacion.openMRS.services.agents;

import java.util.Date;

import jade.core.AID;
import jade.util.leap.List;

public interface RecuperaInformacionVocabulary {
	
public static final String ONTOLOGY_NAME = "recuperaInformacion-ontology";
	
	public static final String COMPLEX = "complex";
	public static final String DICOMDATA = "agentDicomBean";
	public static final String USUARIOS = "usuarios";
	public static final String ROLES = "roles";
	public static final String AGENTINFO = "agentInfo"; 
	public static final String PROVEEDORES = "proveedores"; 
	public static final String ATIENDE = "atiende";
	public static final String MSJHL7 = "msjHl7";
	
	
	public static final String RESULT = "result";
	public static final String AGENTINFOR = "agentInfor";
	public static final String AGENTDICOM = "agentDicom";
	public static final String PROPERTY = "property";
	public static final String GENERICA = "generica";
	
	
	
	

    public static final String AGENTAID = "agentAid";
    public static final String STARTDATE = "startDate";
    public static final String NOMBREEQUIPO = "nombreEquipo"; 
    public static final String DATE = "date";
    public static final String IMEI = "imei";
    
    
    public static final String TIPOTEXT = "tipoText";
    public static final String FECHATEXT = "fechaText";
    public static final String DETALLETEXT = "listaLog";
    
    
    
    public static final String IDUSUARIO = "id";
    public static final String USUARIO = "usuario";
    public static final String CLAVE = "clave";
    public static final String LISTADETALLE = "listaLog";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_VALUE = "value";
    
    
    
    public static final String NAMEIMAGEN = "nameImagen";
    public static final String IMAGENBYTES = "imageBytes";
    
    public static final String USERID ="userId";
    public static final String LISTAROL = "listaRol";
 
    public static final String USUARIOID = "idUsuarioWeb";
    public static final String USUARIOROL = "userId";
    public static final String PROVEEDRORID = "idProveedorWeb";
    public static final String ATIENDEID = "idPacienteWeb";
    public static final String MSJTX = "versionHl7";
    
    
   
    public static final String VERSIONHL7 = "version";
	public static final String ENCODIGHL7 = "encoding";
	public static final String CONFIGURACIONHL7 = "configuration";
	

	
	public static final String CADENA_DATOS_IMAGEN_OUT = "cadenaDatosImagenOut";
	public static final String CODIGOERROR = "codigoError";
    
 
   public static final String PERSON_ID = "person_id";
    public static final String IDENTIFIER ="identifier";
    public static final String GIVEN_NAME="given_name";
    public static final String FAMILY_NAME="family_name";
    public static final String VALUE="value";
    
    
    public static final String PERSON_ID_1 = "person_id";
    public static final String GIVEN_NAME_1 ="given_name";
    public static final String NAME="name";
    public static final String FAMILY_NAME_1="family_name";
    public static final String USERNAME="username";
    
    
    
    public static final String ENCOUNTER_ID = "encounter_id";
    public static final String IDENTIFIER_1 ="identifier";
    public static final String PERSON_ID_2="person_id";
    public static final String GIVEN_NAME_2="given_name";
    public static final String FAMILY_NAME_2="family_name";
    
   
   
  
    //Actions
    public static final String CONSULTAINFORMACION = "recuperaInformacio";
    public static final String GETAGENTINFOR = "getAgentInfor";
    public static final String GETAGENTROLES = "getAgentRoles";
    public static final String GETAGENTUSUARIOS = "getAgentUsuarios";
    public static final String GETAGENTPROVEEDORES = "getAgentProveedores";
    public static final String GETAGENTATIENDE = "getAgentAtiende";
    public static final String RECUPERA_DATOS_FORMULARIO = "recuperaDatosFormulario";
    public static final String RESULTADOLISTA = "getResultadoUsuarios";
    public static final String GETCOMPONENTS = "getComponents";
    public static final String GETMODULOSDCM4CH = "getModulosDCM4CH";   
    public static final String GETAGENTMSJHL7 = "getAgentVersionHL7";
    
    
 }
