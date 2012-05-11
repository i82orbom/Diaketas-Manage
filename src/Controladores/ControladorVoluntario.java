/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Vistas.VistaVoluntario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 ** NOMBRE CLASE: 
 **	  ControladorVoluntario
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
 ** 	000 - 10 mai 2012 - RC - Creacion
 **
 ** NOTAS:
 **   
 **
 */
public class ControladorVoluntario {
    /** PATRON DE DISEÑO SINGLETON */
    
    private static ControladorVoluntario instancia;


    public static ControladorVoluntario getInstance(VistaVoluntario panelVoluntario){
        if (instancia == null)
            instancia = new ControladorVoluntario(panelVoluntario);

        return instancia;

    }

    private VistaVoluntario vista;
    
    /**
     * Constructor de la clase
     */	
    private ControladorVoluntario(VistaVoluntario pvista){

	/** 
     * Establece como ventana padre la pasada como parámetro
     */
	vista = pvista;
        
        // anadir listener
        vista.getBarraDeNavigacion().setListener(new ListenerBarraNavigacion());
        vista.getPanelVoluntarioInicio().anadirListenerbtAyudas(new btAyudasListener());
        vista.getPanelVoluntarioInicio().anadirListenerbtBuscarVoluntario(new btBuscarListener());
        vista.getPanelVoluntarioInicio().anadirListenerbtContabilidad(new btContabilidadListener());
        vista.getPanelVoluntarioInicio().anadirListenerbtNuevoVoluntario(new btDatosListener());
        
        // al principio mostrar la vista de inicio
        mostrarVistaInicio();       
    }
    
    // mostrar la vista que queremos y actualizacion de la barra de navigacion
    private void mostrarVistaInicio(){
        vista.showPanel(VistaVoluntario.panelInicio);
        vista.getBarraDeNavigacion().setTextLabelMenuPrincipal("Voluntario");     
    }
    
    private void mostrarVistaBuscar(){
        vista.showPanel(VistaVoluntario.panelBuscar);
        vista.getBarraDeNavigacion().setTextLabelMenuPrincipal("Voluntario");  
        vista.getBarraDeNavigacion().setTextLabelMenuAvanzado("Buscar");
    }
    
    private void mostrarVistaDatos(){
        vista.showPanel(VistaVoluntario.panelDatos);
        vista.getBarraDeNavigacion().setTextLabelMenuPrincipal("Voluntario");    
        vista.getBarraDeNavigacion().setTextLabelMenuAvanzado("Datos");   
    }
    
    private void mostrarVistaAyudas(){
        vista.showPanel(VistaVoluntario.panelAyudas);
        vista.getBarraDeNavigacion().setTextLabelMenuPrincipal("Voluntario");    
        vista.getBarraDeNavigacion().setTextLabelMenuAvanzado("Ayudas");   
    }
    
    private void mostrarVistaContabilidad(){
        vista.showPanel(VistaVoluntario.panelContabilidad);
        vista.getBarraDeNavigacion().setTextLabelMenuPrincipal("Voluntario");    
        vista.getBarraDeNavigacion().setTextLabelMenuAvanzado("Contabilidad");   
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
    
    class btDatosListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarVistaDatos();
        }
    }
    
    class btAyudasListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarVistaAyudas();
        }
    }
    
    class btContabilidadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarVistaContabilidad();
        }
    }
}
