/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Vistas.Paneles.Voluntario.PanelVoluntarioContabilidad;

/**
 ** NOMBRE CLASE: 
 **	  ControladorContabilidad
 **
 ** DESCRIPCION:
 **       
 **       
 **
 ** DESARROLLADO POR:
 *          Raphael Colleau (RC)
 **        
 **
 ** SUPERVISADO POR:
 **          
 **
 ** HISTORIA:
 ** 	000 - 16 mai 2012 - RC - Creacion
 **
 ** NOTAS:
 **   
 **
 */
public class ControladorContabilidad {
    /** PATRON DE DISEÑO SINGLETON */
    
    private static ControladorContabilidad instancia = null;

    public static ControladorContabilidad getInstance(PanelVoluntarioContabilidad pvista){

            if(instancia == null)
                    instancia = new ControladorContabilidad(pvista);
            return instancia;

    }

    private PanelVoluntarioContabilidad vista;

    public ControladorContabilidad(PanelVoluntarioContabilidad vista) {
        this.vista = vista;
    }
    
    // TODO Metodos JDBC
    
    // TODO Listeners de los botones
}
