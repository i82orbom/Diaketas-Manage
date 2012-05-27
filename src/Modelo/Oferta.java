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
 *		  Antonio Rodríguez Segura
 **
 **
 ** HISTORIA:
 ** 	000 - May 22, 2012 - Creacion
 **     001 - May 27, 2012 - Añadida navegavilidad a la clase
 *
 *
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
    private Sector sector;
    private C_Empresa empresa;
    private Voluntario voluntario;

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
    public Oferta(Long OID, String cualificacionRequerida, String descripcionOferta, int duracionContrato, Date fecha, int plazasOfertadas, String tipoContrato, Sector sector, C_Empresa empresa, Voluntario voluntario) {
        this.OID = OID;
        this.cualificacionRequerida = cualificacionRequerida;
        this.descripcionOferta = descripcionOferta;
        this.duracionContrato = duracionContrato;
        this.fecha = fecha;
        this.plazasOfertadas = plazasOfertadas;
        this.tipoContrato = tipoContrato;
        this.sector = sector;
        this.empresa = empresa;
        this.voluntario = voluntario;
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

    public C_Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(C_Empresa empresa) {
        this.empresa = empresa;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

}
