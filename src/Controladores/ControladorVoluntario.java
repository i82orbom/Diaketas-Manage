/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Vistas.Paneles.Voluntario.VistaVoluntario;
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
        vista.getBarraDeNavegacion().setListener(new ListenerBarraNavegacion());
        vista.getPanelVoluntarioInicio().anadirListenerbtAyudas(new btAyudasListener());
        vista.getPanelVoluntarioInicio().anadirListenerbtBuscarVoluntario(new btBuscarListener());
        vista.getPanelVoluntarioInicio().anadirListenerbtContabilidad(new btContabilidadListener());
        vista.getPanelVoluntarioInicio().anadirListenerbtNuevoVoluntario(new btDatosListener());
        
        // al principio mostrar la vista de inicio
        mostrarVistaInicio();       
    }
    
    // mostrar la vista que queremos y actualizacion de la barra de navegacion
    private void mostrarVistaInicio(){
        vista.showPanel(VistaVoluntario.panelInicio);
        vista.getBarraDeNavegacion().setTextLabelMenuPrincipal("Voluntario");     
    }
    
    private void mostrarVistaBuscar(){
        vista.showPanel(VistaVoluntario.panelBuscar);
        vista.getBarraDeNavegacion().setTextLabelMenuPrincipal("Voluntario");  
        vista.getBarraDeNavegacion().setTextLabelMenuAvanzado("Buscar");
    }
    
    private void mostrarVistaDatos(){
        vista.showPanel(VistaVoluntario.panelDatos);
        vista.getBarraDeNavegacion().setTextLabelMenuPrincipal("Voluntario");    
        vista.getBarraDeNavegacion().setTextLabelMenuAvanzado("Datos");   
    }
    
    private void mostrarVistaAyudas(){
        vista.showPanel(VistaVoluntario.panelAyudas);
        vista.getBarraDeNavegacion().setTextLabelMenuPrincipal("Voluntario");    
        vista.getBarraDeNavegacion().setTextLabelMenuAvanzado("Ayudas");   
    }
    
    private void mostrarVistaContabilidad(){
        vista.showPanel(VistaVoluntario.panelContabilidad);
        vista.getBarraDeNavegacion().setTextLabelMenuPrincipal("Voluntario");    
        vista.getBarraDeNavegacion().setTextLabelMenuAvanzado("Contabilidad");   
    }
    
    //Listener de la barra de navigacion
    class ListenerBarraNavegacion implements ActionListener {

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
