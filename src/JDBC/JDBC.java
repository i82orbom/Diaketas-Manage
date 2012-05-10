
/**
 ** NOMBRE CLASE: 
 **	  JDBC.java
 **
 ** DESCRIPCION:
 **        Clase abstracta de la que heredan todos los metodos JDBC que contiene los métodos genéricos
 **       
 **
 ** DESARROLLADO POR:
 *          José Ángel González Molina (JGM)
 **        
 **
 ** SUPERVISADO POR:
 **          
 **
 ** HISTORIA:
 ** 	000 - May  1, 2012 - JGM - Creacion
 *      
 *      
 **
 ** NOTAS:
 **   
 **
 */

package JDBC;

import Modelo.Identificable;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public abstract class JDBC {

	public static Long genOID(Identificable dato) throws IOException{
		
		if(dato == null)
			return null;
		ByteArrayOutputStream out = new ByteArrayOutputStream(5000);
		ObjectOutputStream objOut = new ObjectOutputStream(new BufferedOutputStream(out));
		objOut.flush();
		objOut.writeObject(dato);
		objOut.flush();
		byte[] bytes = out.toByteArray();
		objOut.close();
		
		String uuid = java.util.UUID.nameUUIDFromBytes(bytes).toString();
		Long oid = Long.parseLong(uuid, 16);
		return oid;
		
	}

}

