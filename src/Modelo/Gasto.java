/**
 ** NOMBRE CLASE: 
 **	  Gasto.java
 **
 ** DESCRIPCION:
 **       Abstracción de un Gasto
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
public class Gasto extends Movimiento{
    
    private int OID;
    private int OIDAyuda;
    
    
    /**
     * Constructor por defecto
     */
    public Gasto(){
    }
        
    /**
     * Obtener el OID del Gasto
     * @return OID
     */
    public int getOIDGasto() {
        return OID;
    }
    
    /**
     * Asignar un OID a Gasto
     * @param OID
     */
    public void setOIDGasto(int OID) {
        this.OID = OID;
    }            
            
     /**
     * Obtener el OID de la Ayuda del Gasto
     * @return OIDAyuda
     */
    public int getOIDAyuda() {
        return OIDAyuda;
    }
    
    /**
     * Asignar un OID a la Ayuda del Gasto
     * @param OIDAyuda
     */
    public void setOIDAyuda(int OIDAyuda) {
        this.OIDAyuda = OIDAyuda;
    }           
}
