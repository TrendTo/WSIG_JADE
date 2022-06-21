package com.comunicacion.openMRS.controler;

import java.io.BufferedReader; 
import java.io.FileNotFoundException; 
import java.io.FileReader; 
import java.io.IOException;

public class LeerFichero {
	private static String muestraContenido(String archivo) throws FileNotFoundException, IOException { 
    	String cadena; 
        FileReader f = new FileReader(archivo); 
        BufferedReader b = new BufferedReader(f); 
        while((cadena = b.readLine())!=null) { 
        	System.out.println(cadena); 
        } 
        b.close();
		return cadena; 
	} 

}
