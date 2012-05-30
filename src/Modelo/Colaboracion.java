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
    
    private Long OID;
    private Long OIDColaborador;
    private Long OIDVoluntario;
    
    /**
     * Constructor por defecto
     */
    public Colaboracion(){
    }
        
    /**
     * Obtener el OID de la Colaboracion
     * @return OID
     */
    public Long getOIDColaboracion() {
        return OID;
    }
    
    /**
     * Asignar un OID a una Colaboracion
     * @param OID
     */
    public void setOIDColaboracion(Long OID) {
        this.OID = OID;
    }
    
    /**
     * Obtener el OID de el Colaborador
     * @return OIDColaborador
     */
    public Long getOIDColaborador() {
        return OIDColaborador;
    }
    
    /**
     * Asignar un OID a un Colaborador
     * @param OIDColaborador
     */
    public void setOIDColaborador(Long OIDColaborador) {
        this.OIDColaborador = OIDColaborador;
    }
    
    /**
     * Obtener el OID del Voluntario
     * @return OIDVoluntario
     */
    public Long getOIDVoluntario() {
        return OIDVoluntario;
    }
    
    /**
     * Asignar un OID a un Voluntario
     * @param OIDVoluntario
     */
    public void setOIDVoluntario(Long OIDVoluntario) {
        this.OIDVoluntario = OIDVoluntario;
    }
}
