/**
 ** NOMBRE CLASE: 
 **	  PagoCuota.java
 **
 ** DESCRIPCION:
 **       Abstracción del Pago de una Cuota
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

/**
 *
 * @author PLB
 */
public class PagoCuota extends Movimiento{
    
    private int OID;
    private int OIDVoluntario;
    private int OIDSocio;
    
    /**
     * Constructor por defecto
     */
    public PagoCuota(){
    }
        
    /**
     * Obtener el OID del Pago de una Cuota
     * @return OID
     */
    public int getOIDPagoCuota() {
        return OID;
    }
    
    /**
     * Asignar un OID al Pago de una Cuota
     * @param OID
     */
    public void setOIDPagoCuota(int OID) {
        this.OID = OID;
    }            
    
    /**
     * Obtener el OID del Voluntario en un Pago de una Cuota
     * @return OIDVoluntario
     */
    public int getOIDVoluntario() {
        return OIDVoluntario;
    }
    
    /**
     * Asignar un OID al Voluntario del Pago de una Cuota
     * @param OIDVoluntario
     */
    public void setOIDVoluntario(int OIDVoluntario) {
        this.OIDVoluntario = OIDVoluntario;
    } 
    
    /**
     * Obtener el OID del Socio en un Pago de una Cuota
     * @return OIDSocio
     */
    public int getOIDSocio() {
        return OIDSocio;
    }
    
    /**
     * Asignar un OID al Socio del Pago de una Cuota
     * @param OIDSocio
     */
    public void setOIDSocio(int OIDSocio) {
        this.OIDSocio = OIDSocio;
    } 
    
}
