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
    private Sector sector;
    private Beneficiario beneficiario;
    private Voluntario voluntario;

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
    public Demanda(Long OID, String descripcionValidaLaboral, Date fecha, Sector sector, Beneficiario beneficiario, Voluntario voluntario) {
        this.OID = OID;
        this.descripcionValidaLaboral = descripcionValidaLaboral;
        this.fecha = fecha;
        this.sector = sector;
        this.beneficiario = beneficiario;
        this.voluntario = voluntario;
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

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector idSector) {
        this.sector = idSector;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario idVoluntario) {
        this.voluntario = idVoluntario;
    }

}
