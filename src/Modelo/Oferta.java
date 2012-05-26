/**
 ** NOMBRE CLASE:
 **	  Oferta.java
 **
 ** DESCRIPCION:
 **       Abstracción de una Oferta
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

public class Oferta {

    //Atributos propios
    private Long OID;
    private String cualificacionRequerida;
    private String descripcionOferta;
    private int duracionContrato;
    private Date fecha;
    private int plazasOfertadas;
    private String tipoContrato;

    //Claves externas
    private Long idSector;
    private Long idEmpresa;
    private Long idVoluntario;

    /**
     * Constructor Vacio
     */
    public Oferta() {
    }

    /**
     * Constructor con todos los atributos
     * @param OID
     * @param cualificacionRequerida
     * @param descripcionOferta
     * @param duracionContrato
     * @param fecha
     * @param plazasOfertadas
     * @param tipoContrato
     * @param IDSector
     * @param IDEmpresa
     * @param IDBeneficiario
     * @param IDVoluntario
     */
    public Oferta(Long OID, String cualificacionRequerida, String descripcionOferta, int duracionContrato, Date fecha, int plazasOfertadas, String tipoContrato, Long idSector, Long idEmpresa, Long idVoluntario) {
        this.OID = OID;
        this.cualificacionRequerida = cualificacionRequerida;
        this.descripcionOferta = descripcionOferta;
        this.duracionContrato = duracionContrato;
        this.fecha = fecha;
        this.plazasOfertadas = plazasOfertadas;
        this.tipoContrato = tipoContrato;
        this.idSector = idSector;
        this.idEmpresa = idEmpresa;
        this.idVoluntario = idVoluntario;
    }

    //Métodos get y set
    public Long getOID() {
        return OID;
    }

    public void setOID(Long OID) {
        this.OID = OID;
    }

    public String getCualificacionRequerida() {
        return cualificacionRequerida;
    }

    public void setCualificacionRequerida(String cualificacionRequerida) {
        this.cualificacionRequerida = cualificacionRequerida;
    }

    public String getDescripcionOferta() {
        return descripcionOferta;
    }

    public void setDescripcionOferta(String descripcionOferta) {
        this.descripcionOferta = descripcionOferta;
    }

    public int getDuracionContrato() {
        return duracionContrato;
    }

    public void setDuracionContrato(int duracionContrato) {
        this.duracionContrato = duracionContrato;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPlazasOfertadas() {
        return plazasOfertadas;
    }

    public void setPlazasOfertadas(int plazasOfertadas) {
        this.plazasOfertadas = plazasOfertadas;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
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
