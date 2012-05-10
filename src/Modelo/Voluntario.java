/**
 ** NOMBRE CLASE: 
 **	 Voluntario.java
 **
 ** DESCRIPCION:
 **       Abstracción de Voluntario
 **       
 **
 ** DESARROLLADO POR:
 *        Francisco José Beltrán Rodriguez (FBR)
 *	   
 **        
 ** SUPERVISADO POR:
 **       Adolfo Arcoya Nieto (AAN)   
 **
 ** HISTORIA:
 ** 	000 - Mar 24, 2012 - FBR - Creacion
 *      
 **
 ** NOTAS:
 **   
 **
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Jobero
 */
 public class Voluntario extends Persona implements Identificable {
    
    private Long OID;
	private String password;
    
    private ArrayList<Modificacion> modificaciones;
    private ArrayList<Ayuda> ayudasDadas;

    /**
     * Constructor por defecto
     */
    public Voluntario() {
    }

    /**
     * 
     * @return
     */
    public ArrayList<Ayuda> getAyudasDadas() {
        return ayudasDadas;
    }

    /**
     * 
     * @param ayudasDadas
     */
    public void setAyudasDadas(ArrayList<Ayuda> ayudasDadas) {
        this.ayudasDadas = ayudasDadas;
    }

    /**
     * 
     * @return
     */
    public ArrayList<Modificacion> getModificaciones() {
        return modificaciones;
    }

    /**
     * 
     * @param modificaciones
     */
    public void setModificaciones(ArrayList<Modificacion> modificaciones) {
        this.modificaciones = modificaciones;
    }

    /**
     * 
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

	@Override
	public void setOID(Long OID) {
		this.OID = OID;
	}

	@Override
	public Long getOID() {
		return OID;
	}
    
    
}
