/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Vistas.BarraDeNavegacion;
import Vistas.Paneles.Beneficiario.VistaBeneficiario;
import Vistas.Paneles.Colaboradores.VistaColaboradores;
import Vistas.Paneles.Socio.VistaSocio;
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
 *          Alberto Moreno Mantas
 * 
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
public class ControladorColaboradores {
    /** PATRON DE DISEÑO SINGLETON */
    
    private static ControladorColaboradores instancia;


    public static ControladorColaboradores getInstance(VistaColaboradores vistaColaboradores){
        if (instancia == null)
            instancia = new ControladorColaboradores(vistaColaboradores);

        return instancia;

    }

    private VistaColaboradores vista;
    private VistaSocio vistaSocio= new VistaSocio();
    /**
     * Constructor de la clase
     */	
    private ControladorColaboradores(VistaColaboradores pvista){

	/** 
     * Establece como ventana padre la pasada como parámetro
     */
	vista = pvista;
        
        // anadir listener
        vista.getBarraDeNavigacion().setListener(new ListenerBarraNavigacion());
        vista.getPanelColaboradoresInicio().anadirListenerbtColaborador(new btColaboradorListener());
        vista.getPanelColaboradoresInicio().anadirListenerbtSocio(new btSocioListener());
        vista.getPanelColaboradoresInicio().anadirListenerbtEmpresa(new btEmpresaListener());
        
        // al principio mostrar la vista de inicio
        mostrarVistaInicio();   
       
    }
    
    // mostrar la vista que queremos y actualizacion de la barra de navigacion
    private void mostrarVistaInicio(){
        vista.showPanel(VistaColaboradores.panelColaboradoresInicio);
        vista.getBarraDeNavigacion().setTextLabelNivel1("Colaboradores");     
    }
    
    private void mostrarVistaSocio(){
        vistaSocio.showPanel(VistaSocio.panelInicio);
        vistaSocio.getBarraDeNavegacion().setTextLabelNivel1("Colaboradores");
        vistaSocio.getBarraDeNavegacion().setTextLabelNivel2("Socio");
    }
    
    private void mostrarVistaEmpresa(){
        vista.showPanel(VistaBeneficiario.panelDatos);
        vista.getBarraDeNavigacion().setTextLabelNivel1("Colaboradores");    
        vista.getBarraDeNavigacion().setTextLabelNivel2("Empresa");   
    }
    
      private void mostrarVistaColaborador(){
        vista.showPanel(VistaBeneficiario.panelDatos);
        vista.getBarraDeNavigacion().setTextLabelNivel1("Colaboradores");    
        vista.getBarraDeNavigacion().setTextLabelNivel2("Colaborador");   
    }
    
    
    //Listener de la barra de navigacion
    class ListenerBarraNavigacion implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equalsIgnoreCase(BarraDeNavegacion.TO_VISTA_INICIAL)) {
                mostrarVistaInicio();
                ControladorPrincipal.getInstance().mostrarVistaInicio();
            }
            
            if (ae.getActionCommand().equalsIgnoreCase(BarraDeNavegacion.TO_NIVEL1)) {
                mostrarVistaInicio();
            }
        }
    }
    
   
    // listeners de los botones
    class btSocioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarVistaSocio();
        }
    }
    
    class btEmpresaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarVistaEmpresa();
        }
    }
    
    class btColaboradorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarVistaColaborador();
        }
    }
}
