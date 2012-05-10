/**
 ** NOMBRE CLASE: 
 **	  Ayuda.java
 **
 ** DESCRIPCION:
 **       Clase que representa una ayuda
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
 **     001 - Mar 26, 2012 - FBR - Tipo de 'fecha' cambiado a string, tiene que ser del formato YYYY-MM-DD
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
public class Ayuda {
    
    private String fecha;
    private float importe;
    private String observaciones;
    private String OID;
    
    private ArrayList<Modificacion> modificaciones;
    private ArrayList<Movimiento> movimientos;
    private TipoAyuda tipo_ayuda;
    private Beneficiario beneficiarioDeAyuda;
    private Voluntario voluntarioQueOtorga;
    private Asociacion asociacionQueOtorga;

    
    /**
     * Constructor por defecto
     */
    public Ayuda() {
    }

    /**
     * 
     * @return OID
     */
    public String getOID() {
        return OID;
    }

    /**
     * 
     * @param OID
     */
    public void setOID(String OID) {
        this.OID = OID;
    }

    /**
     * 
     * @return
     */
    public Asociacion getAsociacionQueOtorga() {
        return asociacionQueOtorga;
    }

    /**
     * 
     * @param asociacionQueOtorga
     */
    public void setAsociacionQueOtorga(Asociacion asociacionQueOtorga) {
        this.asociacionQueOtorga = asociacionQueOtorga;
    }

    /**
     * 
     * @return
     */
    public Beneficiario getBeneficiarioDeAyuda() {
        return beneficiarioDeAyuda;
    }

    /**
     * 
     * @param beneficiarioDeAyuda
     */
    public void setBeneficiarioDeAyuda(Beneficiario beneficiarioDeAyuda) {
        this.beneficiarioDeAyuda = beneficiarioDeAyuda;
    }

    /**
     * 
     * @return
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * 
     * @param fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * 
     * @return
     */
    public float getImporte() {
        return importe;
    }

    /**
     * 
     * @param importe
     */
    public void setImporte(float importe) {
        this.importe = importe;
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
    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    /**
     * 
     * @param movimientos
     */
    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    /**
     * 
     * @return
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * 
     * @param observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * 
     * @return
     */
    public TipoAyuda getTipo_ayuda() {
        return tipo_ayuda;
    }

    /**
     * 
     * @param tipo_ayuda
     */
    public void setTipo_ayuda(TipoAyuda tipo_ayuda) {
        this.tipo_ayuda = tipo_ayuda;
    }

    /**
     * 
     * @return
     */
    public Voluntario getVoluntarioQueOtorga() {
        return voluntarioQueOtorga;
    }

    /**
     * 
     * @param voluntarioQueOtorga
     */
    public void setVoluntarioQueOtorga(Voluntario voluntarioQueOtorga) {
        this.voluntarioQueOtorga = voluntarioQueOtorga;
    }

}
