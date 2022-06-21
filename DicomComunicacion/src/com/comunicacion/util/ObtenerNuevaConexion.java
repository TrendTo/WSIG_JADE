package com.comunicacion.util;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;


public class ObtenerNuevaConexion implements ObtenerConexion{
	@SuppressWarnings("rawtypes")
	private Class entityClass;
	Logger log = Logger.getLogger("ObtenerNuevaConexion");
	@SuppressWarnings("rawtypes")
	public ObtenerNuevaConexion(Class entityClass) {
		this.entityClass = entityClass;
	}
	public ConexionVarianteSid obtenerNuevaConexionVarianteSidSidDataBase(String sidDataBase) {
		try {
			return new ConexionVarianteSid(sidDataBase);
		} catch (NamingException ex) {
			Logger.getLogger(entityClass.getName()).log(Level.SEVERE, null, ex);
		} catch (Exception ex) {
			Logger.getLogger(entityClass.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}





	

}
