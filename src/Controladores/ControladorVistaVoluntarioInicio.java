/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.ModeloVistaLogin;
import Vistas.Raiz;
import Vistas.VistaInicial;
import Vistas.VistaLogin;
import Vistas.PanelVoluntarioInicio;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author psylock
 */
public class ControladorVistaVoluntarioInicio {
    
    //... The Controller needs to interact with both the Model and View.
    private PanelVoluntarioInicio  m_view;
    
    //========================================================== constructor
    /** Constructor */
    public ControladorVistaVoluntarioInicio(PanelVoluntarioInicio view) {
    
        m_view  = view;
    
        //... Add listeners to the view.
        
        m_view.anadirListenerbtAyudas(new BtAyudasListener());
        m_view.anadirListenerbtBuscarVoluntario(new BtBuscarVoluntarioListener());
        m_view.anadirListenerbtColaboraciones(new BtColaboracionesListener());
        m_view.anadirListenerbtContabilidad(new BtContabilidadListener());
        m_view.anadirListenerbtNuevoVoluntario(new BtNuevoVoluntarioListener());
     
    }
    
    
    

 
  

    class BtAyudasListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
          
            
        }
    }
    
    
    class BtBuscarVoluntarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
          
            
        }
    }
    
    
    class BtColaboracionesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
          
            
        }
    }
    
    
    class BtContabilidadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
          
            
        }
    }
 
     
    class BtNuevoVoluntarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
          
            
        }
    }
      
    class NavToMainFromVoluntariosListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
          Raiz.mostrarVista(m_view, VistaInicial.class);
            
        }
    }

}
