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
    
    private Long OID;
    private Voluntario voluntario;
    private Socio socio;
    
    /**
     * Constructor por defecto
     */
    public PagoCuota(){
    }
        
    /**
     * Obtener el OID del Pago de una Cuota
     * @return OID
     */
    public Long getOID() {
        return OID;
    }
    
    /**
     * Asignar un OID al Pago de una Cuota
     * @param OID
     */
    public void setOID(Long OID) {
        this.OID = OID;
    }            
	
    public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public Voluntario getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(Voluntario voluntario) {
		this.voluntario = voluntario;
	}    
}
