package com.comunicacion.openMRS.controler;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.comunicacion.openMRS.Beans.UsuarioCabecera;
import com.comunicacion.util.ConexionVarianteSid;
import com.comunicacion.util.ObtenerNuevaConexion;
import com.comunicacion.util.ResultSetMapper;

import org.apache.log4j.Logger;
public class ConsultaPaciente extends ObtenerNuevaConexion implements Serializable{

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(ConsultaPaciente.class.getName());
	private List<UsuarioCabecera> listUsuarios = new ArrayList<UsuarioCabecera>();
	
	public ConsultaPaciente(String sid, String Usuario) {
		super(ConsultaPaciente.class);
		this.getConsultausuarios(sid, Usuario);
	}
	 public List<UsuarioCabecera> getConsultausuarios(String sid, String Usuario) 
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
	 String queryBusqueda= "SELECT person_name.person_id,                                    "+
						   "       patient_identifier.identifier,                            "+
						   "       person_name.given_name,                                   "+
						   "       person_name.family_name,                                  "+
						   "       person_attribute.value                                    "+
						   "  FROM patient, patient_identifier, person_name, person_attribute"+
						   " WHERE person_name.person_id = person_attribute.person_id        "+
						   "   AND patient.patient_id = patient_identifier.patient_id        "+
						   "   AND patient.patient_id = person_name.person_id                "+
	                       "   AND identifier = '"+Usuario+"'                ";
	 
	
     log.info("Execucion de la consulta is starting...");
     ResultSet rs = conexionVarianteSid.consulta(queryBusqueda);
     log.info("Resultado de Consulta..."+rs);
     ResultSetMapper<UsuarioCabecera> resultSetMapper = new ResultSetMapper<UsuarioCabecera>();
     List<UsuarioCabecera> listaUsuarios =resultSetMapper.mapRersultSetToObject(rs, UsuarioCabecera.class);   
     try {
		while (rs.next()) {
			 log.info("A RecuperaInformacion is starting..."+rs.getString("person_id"));
			 log.info("A RecuperaInformacion is starting..."+rs.getString("identifier"));
			}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}

     conexionVarianteSid.cerrarConexion(rs);
     listUsuarios=listaUsuarios;
    return listaUsuarios; 
     
}
	 public List<UsuarioCabecera> getResultado() {
		
		return listUsuarios ;
		}
	
}
