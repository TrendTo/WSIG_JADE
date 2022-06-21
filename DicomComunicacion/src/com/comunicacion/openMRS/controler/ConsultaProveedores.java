package com.comunicacion.openMRS.controler;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.comunicacion.openMRS.Beans.ProveedorCabecera;
import com.comunicacion.openMRS.Beans.UsuarioCabecera;
import com.comunicacion.util.ConexionVarianteSid;
import com.comunicacion.util.ObtenerNuevaConexion;
import com.comunicacion.util.ResultSetMapper;

public class ConsultaProveedores extends ObtenerNuevaConexion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(ConsultaProveedores.class.getName());
	private List<ProveedorCabecera> listProveedores = new ArrayList<ProveedorCabecera>();

	public ConsultaProveedores(String sid, String Proveedor) {
		super(ConsultaProveedores.class);
		this.getConsultaproveedor(sid, Proveedor);
	}
	public List<ProveedorCabecera> getConsultaproveedor(String sid, String Proveedor) 
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
  	 String queryBusqueda= "SELECT person_name.person_id,											"+
				  			"       given_name,														"+
				  			"       family_name,													"+
				  			"       providermanagement_provider_role.name,							"+
				  			"       username														"+
				  			"  from person_name, provider, providermanagement_provider_role, users  "+
				  			" WHERE person_name.person_id = provider.person_id						"+
				  			"   AND provider.provider_role_id =										"+
				  			"       providermanagement_provider_role.provider_role_id				"+
				  			"   and person_name.person_id = users.person_id							"+	
				  			"   and username = '"+Proveedor+"' 										";
  	 
  	
       log.info("Execucion de la consulta is starting...");
       ResultSet rs = conexionVarianteSid.consulta(queryBusqueda);
       log.info("Resultado de Consulta..."+rs);
       ResultSetMapper<ProveedorCabecera> resultSetMapper = new ResultSetMapper<ProveedorCabecera>();
       List<ProveedorCabecera> listaProveedores =resultSetMapper.mapRersultSetToObject(rs, ProveedorCabecera.class);
      
      
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
       listProveedores=listaProveedores;
      return listaProveedores; 
}

	 public List<ProveedorCabecera> getResultado() {
			
			return listProveedores ;
			}
		
	}
