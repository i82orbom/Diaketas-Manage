/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.ModeloVistaLogin;
import Vistas.*;
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
public class ControladorVistaInicial {
    
    //... The Controller needs to interact with both the Model and View.
    
    private VistaInicial m_view;
    
    //========================================================== constructor
    /** Constructor */
    public ControladorVistaInicial(VistaInicial view) {
    
        m_view  = view;
       
        
        //... Add listeners to the view.
        view.anadirListenerbtBeneficiario(new BtBeneficiarioListener());
        view.anadirListenerbtBolsaTrabajo(new BtBolsaTrabajoListener());
        view.anadirListenerbtSocio(new BtSocioListener());
        view.anadirListenerbtVoluntario(new BtVoluntarioListener());
    }
    
    

    class BtBeneficiarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Raiz.mostrarVista(m_view, VistaBeneficiarioInicio.class);
           
            
        }
    }
    
    
    class BtBolsaTrabajoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
           
            
        }
    }
      
      
    class BtSocioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
           
            
        }
    }
        
        
     class BtVoluntarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
           Raiz.mostrarVista(m_view, VistaPanelVoluntarioInicio.class);
            
        }
    }
    
    
 

}
