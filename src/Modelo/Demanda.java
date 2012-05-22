/**
 ** NOMBRE CLASE: 
 **	  Demanda.java
 **
 ** DESCRIPCION:
 **       Abstracción de una Demanda
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
import java.util.Date;

public class Demanda {
    
    //Atributos propios
    private int OID;
    private String descripcionValidaLaboral;
    private Date fecha;
    
    //Claves externas
    private int idSector;
    private int idBeneficiario;
    private int idVoluntario;

    /**
     * Constructor Vacio
     */
    public Demanda() {
    }
    
    /**
    * Constructor con todos los atributos
    * @param OID
    * @param descripcionValidaLaboral
    * @param fecha
    * @param idSector
    * @param idBeneficiario
    * @param idVoluntario 
    */
    public Demanda(int OID, String descripcionValidaLaboral, Date fecha, int idSector, int idBeneficiario, int idVoluntario) {
        this.OID = OID;
        this.descripcionValidaLaboral = descripcionValidaLaboral;
        this.fecha = fecha;
        this.idSector = idSector;
        this.idBeneficiario = idBeneficiario;
        this.idVoluntario = idVoluntario;
    }
    
    
    //Métodos get y set
    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public String getDescripcionValidaLaboral() {
        return descripcionValidaLaboral;
    }

    public void setDescripcionValidaLaboral(String descripcionValidaLaboral) {
        this.descripcionValidaLaboral = descripcionValidaLaboral;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(int idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public int getIdSector() {
        return idSector;
    }

    public void setIdSector(int idSector) {
        this.idSector = idSector;
    }

    public int getIdVoluntario() {
        return idVoluntario;
    }

    public void setIdVoluntario(int idVoluntario) {
        this.idVoluntario = idVoluntario;
    }
        
}
