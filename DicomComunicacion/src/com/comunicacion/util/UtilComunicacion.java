package com.comunicacion.util;

public class UtilComunicacion {

	public static boolean esValidoIMEI(long num){
		 
        String strIMEI = String.valueOf(num);
        int longitud = strIMEI.length();
        if(longitud != 15){
            return false;
        }
        int digito, suma = 0;
        for (int i = longitud; i >= 1; i--){
            digito = (int) (num % 10);
 
            if(i % 2 == 0){
                digito *= 2;
            }
            suma += sumaDigito(digito);
            num /= 10;
        }
        return (suma % 10 == 0);
    }
	 private static int sumaDigito(int n){
	        int suma = 0;
	        while(n > 0){
	            suma += n%10;
	            n /= 10;
	        }
	        return suma;
	    }
}
