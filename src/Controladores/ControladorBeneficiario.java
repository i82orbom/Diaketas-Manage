/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Vistas.Paneles.Beneficiario.VistaBeneficiario;
import Vistas.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 ** NOMBRE CLASE: 
 **	  ControladorBeneficiario
 **
 ** DESCRIPCION:
 **       
 **       
 **
 ** DESARROLLADO POR:
 *          Mario Orozco Borrego (MOB) 
 **        
 **
 ** SUPERVISADO POR:
 **          
 **
 ** HISTORIA:
 ** 	000 - 20 May 2012 - MOB - Creacion
 **
 ** NOTAS:
 **   
 **
 */
public class ControladorBeneficiario {
    /** PATRON DE DISEÑO SINGLETON */
    
    private static ControladorBeneficiario instancia;


    public static ControladorBeneficiario getInstance(VistaBeneficiario panelVoluntario){
        if (instancia == null)
            instancia = new ControladorBeneficiario(panelVoluntario);

        return instancia;

    }

    private VistaBeneficiario vista;
    
    /**
     * Constructor de la clase
     */	
    private ControladorBeneficiario(VistaBeneficiario pvista){

	/** 
     * Establece como ventana padre la pasada como parámetro
     */
	vista = pvista;
        
        // anadir listener
        vista.getBarraDeNavigacion().setListener(new ListenerBarraNavigacion());
        
        
        // al principio mostrar la vista de inicio
        mostrarVistaInicio();   
        
        vista.getPanelInicio().anadirListenerbtBuscarBeneficiario(new btBuscarListener());
        vista.getPanelInicio().anadirListenerbtNuevoBeneficiario(new btNuevoBeneficiarioListener());
    }
    
    // mostrar la vista que queremos y actualizacion de la barra de navigacion
    private void mostrarVistaInicio(){
        vista.showPanel(VistaBeneficiario.panelInicio);
        vista.getBarraDeNavigacion().setTextLabelMenuPrincipal("Beneficiario");     
    }
    
    private void mostrarVistaBuscar(){
        vista.showPanel(VistaBeneficiario.panelBuscar);
        vista.getBarraDeNavigacion().setTextLabelMenuPrincipal("Beneficiario");  
        vista.getBarraDeNavigacion().setTextLabelMenuAvanzado("Buscar");
    }
    
    private void mostrarVistaNuevoBeneficiario(){
        vista.showPanel(VistaBeneficiario.panelDatos);
        vista.getBarraDeNavigacion().setTextLabelMenuPrincipal("Beneficiario");    
        vista.getBarraDeNavigacion().setTextLabelMenuAvanzado("Datos");   
    }
    
  
    
    
    //Listener de la barra de navigacion
    class ListenerBarraNavigacion implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equalsIgnoreCase("toMain")) {
                mostrarVistaInicio();
                ControladorPrincipal.getInstance().mostrarVistaInicio();
            }
            
            if (ae.getActionCommand().equalsIgnoreCase("toMenuInicial")) {
                mostrarVistaInicio();
            }
        }
    }
    
   
    // listeners de los botones
    class btBuscarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarVistaBuscar();
        }
    }
    
    class btNuevoBeneficiarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarVistaNuevoBeneficiario();
        }
    }
   
}
