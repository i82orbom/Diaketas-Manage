/**
 ** NOMBRE CLASE: 
 **	  Sector.java
 **
 ** DESCRIPCION:
 **       Abstracción de un Sector
 **       
 **
 ** DESARROLLADO POR:
 **       Juan Antonio Aranda Ortega
 **	   
 **        
 ** SUPERVISADO POR:
 **       
 **
 ** HISTORIA:
 ** 	000 - Mayo 22, 2012 - Creacion 
 **
 ** NOTAS:
 **   
 **
 */
package Modelo;

public class Sector {
    
    private int OID;
    private String descripcion;

    /**
     * Constructor vacío
     */
    public Sector() {
    }

    /**
     * Constructor con todos los atributos
     * @param OID
     * @param descripcion 
     */
    public Sector(int OID, String descripcion) {
        this.OID = OID;
        this.descripcion = descripcion;
    }

    //Métodos set y get
    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
