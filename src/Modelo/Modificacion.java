/**
 ** NOMBRE CLASE: 
 **	  Asociacion.java
 **
 ** DESCRIPCION:
 **       Abstracción de Modificacion
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
 *      
 **
 ** NOTAS:
 **   
 **
 */


package Modelo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;

/**
 *
 * @author Jobero
 */
public class Modificacion {
    private Date fecha;
    
    private Voluntario voluntarioQueModifica;
    private Ayuda ayudaQueModifica;

    /**
     * Constructor por defecto
     */
    public Modificacion() {
    }

    /**
     * 
     * @return
     */
    public Ayuda getAyudaQueModifica() {
        return ayudaQueModifica;
    }

    /**
     * 
     * @param ayudaQueModifica
     */
    public void setAyudaQueModifica(Ayuda ayudaQueModifica) {
        this.ayudaQueModifica = ayudaQueModifica;
    }

    /**
     * 
     * @return
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * 
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * 
     * @return
     */
    public Voluntario getVoluntarioQueModifica() {
        return voluntarioQueModifica;
    }

    /**
     * 
     * @param voluntarioQueModifica
     */
    public void setVoluntarioQueModifica(Voluntario voluntarioQueModifica) {
        this.voluntarioQueModifica = voluntarioQueModifica;
    }
    
    
}
