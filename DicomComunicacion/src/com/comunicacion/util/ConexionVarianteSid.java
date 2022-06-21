package com.comunicacion.util;

import java.sql.Connection;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;

public class ConexionVarianteSid {
	private String quienIngresa="root";
	private String comoIngresa="admin";
	private ConexionVarianteSid conexionBdd;
	private Connection conn;
	private String Url;
	private String error;
	Logger log = Logger.getLogger(ConexionVarianteSid.class.getName());

	public ConexionVarianteSid() {

	}
	
	public ConexionVarianteSid(String sidDataBase) throws NamingException, Exception {
		try {
			

			//Class.forName("org.postgresql.Driver").newInstance();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//Url = "jdbc:postgresql://localhost:5432/" + sidDataBase;
			Url = "jdbc:mysql://localhost:3307/" + sidDataBase;
			DriverManager.setLoginTimeout(120);
			conn = DriverManager.getConnection(Url, quienIngresa, comoIngresa);
		} catch (SQLException ex) {
			Logger.getLogger(ConexionVarianteSid.class.getName()).log(Level.SEVERE,
					ex.toString() + " FALLA CONEXION :  " + sidDataBase + " URL: " + Url);
			error = ex.getMessage();
		}
	}


	public void cerrarConexion(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				e.getMessage();
			}
		}
		conn = null;
	}

	public void cerrarConexion() {
		if (conn != null) {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				e.getMessage();
			}
		}
		conn = null;
	}

	public void cerrarResultSet(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean ejecutar(String comandoEjecutar) {
		Statement comando = null;
		try {
			comando = conn.createStatement();
			comando.executeUpdate(comandoEjecutar);
			comando.close();
			return true;
		} catch (SQLException error) {
			Logger.getLogger(ConexionVarianteSid.class.getName()).log(Level.SEVERE,
					"comando no ejecutado " + comandoEjecutar + "   error  " + error + " LOCAL  " + this.Url);
			try {
				comando.close();
			} catch (SQLException ex) {
				Logger.getLogger(ConexionVarianteSid.class.getName()).log(Level.SEVERE, null, ex);
			}
			return false;
		}
	}

	public String ejecutarQuery(String comandoEjecutar) {
		Statement comando = null;
		try {
			comando = conn.createStatement();
			comando.executeUpdate(comandoEjecutar);
			comando.close();
			return "OK";
		} catch (SQLException error) {
			Logger.getLogger(ConexionVarianteSid.class.getName()).log(Level.SEVERE,
					"comando no ejecutado " + comandoEjecutar + "   error  " + error + " LOCAL  " + this.Url);
			try {
				comando.close();
			} catch (Exception ex1) {
				Logger.getLogger(ConexionVarianteSid.class.getName()).log(Level.SEVERE, null, ex1);
			}

			return error.toString();
		}
	}

	public ResultSet consulta(String queryBusqueda) {
		if (conn == null)
			return null;
		Statement comando;
		ResultSet resultado = null;
		this.error = null;
		try {
			comando = conn.createStatement();
			resultado = comando.executeQuery(queryBusqueda);
		} catch (SQLException e) {
			e.getSQLState();
			Logger.getLogger(ConexionVarianteSid.class.getName()).log(Level.INFO, e.toString());
			if (e.getSQLState().equals("08003")) {//connection_does_not_exist
				Logger.getLogger(ConexionVarianteSid.class.getName()).log(Level.SEVERE,
						"COMUNICACION BASE DE DATOS DICOM JADE"
								+ "<p><strong>VALIDAR EL INGRESO DICOM JADE</strong></p>");
			} else {
				Logger.getLogger(ConexionVarianteSid.class.getName()).log(Level.SEVERE,
						"Scrip ejecutado es : " + queryBusqueda + "Error BDD: " + e.getMessage()
								+ "Error jade Codigo: " + e.getErrorCode() + " ID  " + this.Url);
				error = "QUERY : " + queryBusqueda + " ERROR " + e.getMessage();
				if (e.getErrorCode() == 01007) {
					log.info("DICOM JADE BDD: " + this.Url);
					
				}
			}
		}
		return resultado;
	}

	
	public Connection getConn() {
		return conn;
	}

	public Boolean isValid() {
		try {
			if (conn == null) {
				error = "EL CONN CONEXION EN NULA";
				return false;
			} else if (conn.isClosed()) {
				error = "LA CONEXION ESTA CERRADA";
				return false;
			} else
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		// return conn==null?Boolean.FALSE:Boolean.TRUE;
	}

	public String getError() {
		return error;
	}

	public String getUrl() {
		return Url;
	}

}
