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
    private Long idSector;
    private Long idBeneficiario;
    private Long idVoluntario;

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
    public Demanda(Long OID, String descripcionValidaLaboral, Date fecha, Long idSector, Long idBeneficiario, Long idVoluntario) {
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

    public Long getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(Long idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public Long getIdSector() {
        return idSector;
    }

    public void setIdSector(Long idSector) {
        this.idSector = idSector;
    }

    public Long getIdVoluntario() {
        return idVoluntario;
    }

    public void setIdVoluntario(Long idVoluntario) {
        this.idVoluntario = idVoluntario;
    }
        
}
