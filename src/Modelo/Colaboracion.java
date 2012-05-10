/**
 ** NOMBRE CLASE: 
 **	  Colaboracion.java
 **
 ** DESCRIPCION:
 **       Abstracción de una Colaboración
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
public class Colaboracion extends Movimiento{
    
    private int OID;
    private int OIDColaborador;
    private int OIDVoluntario;
    
    /**
     * Constructor por defecto
     */
    public Colaboracion(){
    }
        
    /**
     * Obtener el OID de la Colaboracion
     * @return OID
     */
    public int getOIDColaboracion() {
        return OID;
    }
    
    /**
     * Asignar un OID a una Colaboracion
     * @param OID
     */
    public void setOIDColaboracion(int OID) {
        this.OID = OID;
    }
    
    /**
     * Obtener el OID de el Colaborador
     * @return OIDColaborador
     */
    public int getOIDColaborador() {
        return OIDColaborador;
    }
    
    /**
     * Asignar un OID a un Colaborador
     * @param OIDColaborador
     */
    public void setOIDColaborador(int OIDColaborador) {
        this.OIDColaborador = OIDColaborador;
    }
    
    /**
     * Obtener el OID del Voluntario
     * @return OIDVoluntario
     */
    public int getOIDVoluntario() {
        return OIDVoluntario;
    }
    
    /**
     * Asignar un OID a un Voluntario
     * @param OIDVoluntario
     */
    public void setOIDVoluntario(int OIDVoluntario) {
        this.OIDVoluntario = OIDVoluntario;
    }
}
