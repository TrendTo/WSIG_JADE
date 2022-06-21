package com.comunicacion.openMRS.controler;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.comunicacion.openMRS.Beans.AtiendeCabecera;
import com.comunicacion.util.ConexionVarianteSid;
import com.comunicacion.util.ObtenerNuevaConexion;
import com.comunicacion.util.ResultSetMapper;

public class ConsultaAtiende extends ObtenerNuevaConexion implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(ConsultaAtiende.class.getName());
	private List<AtiendeCabecera> listAtiende = new ArrayList<AtiendeCabecera>();
	public ConsultaAtiende(String sid, String Usuario) {
		super(ConsultaAtiende.class);
		this.getConsultaAtiende(sid, Usuario);
	}
	 public List<AtiendeCabecera> getConsultaAtiende(String sid, String Usuario) 
     {   		 
	    CallableStatement cs = null;
        if(sid.trim().toUpperCase().contains("1"))
        {
        		sid="openmrs";
        }else if(!sid.trim().toUpperCase().contains("2"))
        {
        		sid="M";
        }
        
        ConexionVarianteSid conexionVarianteSid=obtenerNuevaConexionVarianteSidSidDataBase(sid);
   	 String queryBusqueda= "SELECT encounter.encounter_id,									 "+
				   			"       identifier,                                              "+
				   			"       provider.person_id,                                      "+
				   			"       given_name,                                              "+
				   			"       family_name                                              "+
				   			"  FROM encounter, provider, encounter_provider, person_name     "+
				   			" WHERE provider.provider_id = encounter_provider.provider_id    "+
				   			"   and encounter_provider.encounter_id = encounter.encounter_id "+
				   			"   and provider.person_id = person_name.person_id               "+
				   			"   and encounter.patient_id = '"+Usuario+"'                     ";
				   	
        log.info("Execucion de la consulta is starting...");
        ResultSet rs = conexionVarianteSid.consulta(queryBusqueda);
        log.info("Resultado de Consulta..."+rs);
        ResultSetMapper<AtiendeCabecera> resultSetMapper = new ResultSetMapper<AtiendeCabecera>();
        List<AtiendeCabecera> listaAtiende =resultSetMapper.mapRersultSetToObject(rs, AtiendeCabecera.class);
       
        try {
    		while (rs.next()) {
    			 log.info("A RecuperaInformacion is starting..."+rs.getString("person_id"));
    			 log.info("A RecuperaInformacion is starting..."+rs.getString("identifier"));
    			}
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

         conexionVarianteSid.cerrarConexion(rs);
         listAtiende=listaAtiende;
        return listaAtiende; 
         
    }
    	 public List<AtiendeCabecera> getResultado() {
    		
    		return listAtiende ;
    		}
    	
    }

