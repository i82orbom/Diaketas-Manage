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
    private Colaborador colaborador;
    private Voluntario voluntario;
    
    /**
     * Constructor por defecto
     */
    public Colaboracion(){
    }
        
    /**
     * Obtener el OID de la Colaboracion
     * @return OID
     */
	@Override
    public Long getOID() {
        return OID;
    }
    
    /**
     * Asignar un OID a una Colaboracion
     * @param OID
     */
	@Override
    public void setOID(Long OID) {
        this.OID = OID;
    }
    
    /**
     * Obtener el Colaborador
     * @return Colaborador
     */
    public Colaborador getColaborador() {
        return colaborador;
    }
    
    /**
     * Asignar un  Colaborador
     * @param Colaborador
     */
    public void setColaborador(Colaborador col) {
        this.colaborador = col;
    }
    
    /**
     * Obtener el Voluntario
     * @return Voluntario
     */
    public Voluntario getVoluntario() {
        return voluntario;
    }
    
    /**
     * Asignar un Voluntario
     * @param Voluntario
     */
    public void setVoluntario(Voluntario vol) {
        this.voluntario = vol;
    }
}
