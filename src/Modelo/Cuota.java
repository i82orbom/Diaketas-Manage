/**
 ** NOMBRE CLASE: 
 **	  Cuota.java
 **
 ** DESCRIPCION:
 **       Abstracción de una Cuota
 **       
 **
 ** DESARROLLADO POR:
 **       Francisco José Legaza Bailon (PLB)
 **	   
 **        
 ** SUPERVISADO POR:
 **       
 **
 ** HISTORIA:
 ** 	000 - Abril 24, 2012 - PLB - Creacion 
 **
 ** NOTAS:
 **   
 **
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author PLB
 */
public class Cuota {
      
    private Long OID;
    private Socio socio;
    private double cantidad;
    private int intervaloPagos;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaUltimoPago;
    
    public static int CANTIDAD = 0;
    public static int INTERFALO_PAGOS = 1;
    public static int FECHA_INICIO = 2;
    public static int FECHA_FIN = 3;
    public static int FECHA_ULTIMO_PAGO = 4;
    /**
     * Constructor por defecto
     */
    public Cuota(){
    }
        
    /**
     * Obtener el OID de la Cuota
     * @return OID
     */
    public Long getOIDCuota() {
        return OID;
    }
    
    /**
     * Asignar un OID a una Cuota
     * @param OID
     */
    public void setOIDCuota(Long OID) {
        this.OID = OID;
    }
    
    /**
     * Obtener el Socio
     * @return socio
     */
    public Socio getSocio() {
        return socio;
    }
    
    /**
     * Asignar un Socio 
     * @param s
     */
    public void setSocio(Socio s) {
        this.socio = s;
    }
    
    /**
     * Obtener la cantidad de la Cuota
     * @return cantidad
     */
    public double getCantidad() {
        return cantidad;
    }
    
    /**
     * Asignar una cantidad a la Cuota
     * @param cantidad
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    
    /**
     * Obtener el Intervalo de los Pagos de la Cuota
     * @return intervaloPagos
     */
    public int getIntervaloPagos() {
        return intervaloPagos;
    }
    
    /**
     * Asignar el Intervalo de los Pagos de la Cuota
     * @param intervaloPagos
     */
    public void setIntervaloPagos(int intervaloPagos) {
        this.intervaloPagos = intervaloPagos;
    }
    
    /**
     * Obtener la Fecha Inicial de la Cuota
     * @return fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }
    
    /**
     * Asignar la Fecha Inicial de la Cuota
     * @param fechaInicio
     */
    public void setFechaInicial(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    /**
     * Obtener la Fecha Final de la Cuota
     * @return fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }
    
    /**
     * Asignar la Fecha Final de la Cuota
     * @param fechaFin
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    /**
     * Obtener la Fecha del ultimo pago  de la Cuota
     * @return fechaUltimoPago
     */
    public Date getFechaUltimoPago() {
        return fechaUltimoPago;
    }
    
    /**
     * Asignar la Fecha del ultimo pago de la Cuota
     * @param fechaUltimoPago
     */
    public void setFechaUltimoPago(Date fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }
}
