/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.ControladorBolsaTrabajo;
import Vistas.Paneles.BolsaTrabajo.VistaBolsaTrabajo;
import Vistas.BarraDeNavegacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author raul
 */
public class ControladorBolsaTrabajo {
    
    private static ControladorBolsaTrabajo instancia;  
    private VistaBolsaTrabajo vista;
    
    public static ControladorBolsaTrabajo getIntance (VistaBolsaTrabajo panelBolsaTrabajo){
            if(instancia == null){
                instancia = new ControladorBolsaTrabajo(panelBolsaTrabajo);
            }
            return instancia; 
    }
    
    private ControladorBolsaTrabajo(VistaBolsaTrabajo bvista){
    
        vista = bvista;
        
       // vista.getBarraDeNavigacion().setListener(new Co
        
        mostrarVistaInicio();
        
        vista.getBolsaTrabajoInicio().anadirListenerbtDemandasEmpleo(new btDemandasListener());
        vista.getBolsaTrabajoInicio().anadirListenerbtOfertasEmpleo(new btOfertasListener());
    }
    
    
    
    public void mostrarVistaInicio(){
        vista.showPanel(VistaBolsaTrabajo.panelInicio);
        vista.getBarraDeNavigacion().setTextLabelNivel1("Bolsa de Trabajo"); 
    }
    
    public void mostrarDemandasInicio(){
        vista.showPanel(VistaBolsaTrabajo.panelDemandaInicio);
        vista.getBarraDeNavigacion().setTextLabelNivel2("Demandas");
    }
    
    public void mostrarOfertasInicio(){
        vista.showPanel(VistaBolsaTrabajo.panelOfertasInicio);
        vista.getBarraDeNavigacion().setTextLabelNivel2("Ofertas");
    }
    
    class btDemandasListener implements ActionListener {
        
        @Override
       public void actionPerformed(ActionEvent ae){
           mostrarDemandasInicio();
       }
    }
    
    class btOfertasListener implements ActionListener {
    
        @Override
        public void actionPerformed (ActionEvent ea){
            mostrarOfertasInicio();
        }
    }
   
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

        
}
