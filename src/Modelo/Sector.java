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

    private Long OID;
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
    public Sector(Long OID, String descripcion) {
        this.OID = OID;
        this.descripcion = descripcion;
    }

    //Métodos set y get
    public Long getOID() {
        return OID;
    }

    public void setOID(Long OID) {
        this.OID = OID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
