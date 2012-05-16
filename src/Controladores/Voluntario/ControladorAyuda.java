/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Vistas.Paneles.Voluntario.PanelVoluntarioAyudas;

/**
 ** NOMBRE CLASE: 
 **	  ControladorAyuda
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
public class ControladorAyuda {
    /** PATRON DE DISEÑO SINGLETON */
    
    private static ControladorAyuda instancia = null;

    public static ControladorAyuda getInstance(PanelVoluntarioAyudas pvista){

            if(instancia == null)
                    instancia = new ControladorAyuda(pvista);
            return instancia;

    }

    private PanelVoluntarioAyudas vista;

    public ControladorAyuda(PanelVoluntarioAyudas vista) {
        this.vista = vista;
    }
    
    // TODO Metodos JDBC
    
    // TODO Listeners de los botones

}
