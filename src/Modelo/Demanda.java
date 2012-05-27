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
    private Long OID;
    private String descripcionValidaLaboral;
    private Date fecha;
    
    //Claves externas
    private Sector idSector;
    private Beneficiario idBeneficiario;
    private Voluntario idVoluntario;

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
    public Demanda(Long OID, String descripcionValidaLaboral, Date fecha, Sector idSector, Beneficiario idBeneficiario, Voluntario idVoluntario) {
        this.OID = OID;
        this.descripcionValidaLaboral = descripcionValidaLaboral;
        this.fecha = fecha;
        this.idSector = idSector;
        this.idBeneficiario = idBeneficiario;
        this.idVoluntario = idVoluntario;
    }
    
    
    //Métodos get y set
    public Long getOID() {
        return OID;
    }

    public void setOID(Long OID) {
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

    public Beneficiario getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(Beneficiario idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public Sector getIdSector() {
        return idSector;
    }

    public void setIdSector(Sector idSector) {
        this.idSector = idSector;
    }

    public Voluntario getIdVoluntario() {
        return idVoluntario;
    }

    public void setIdVoluntario(Voluntario idVoluntario) {
        this.idVoluntario = idVoluntario;
    }
        
}
