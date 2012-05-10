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
public class ControladorVistaBeneficiariosInicio {
    
    //... The Controller needs to interact with both the Model and View.
    
    private VistaBeneficiarioInicio m_view;
    
    //========================================================== constructor
    /** Constructor */
    public ControladorVistaBeneficiariosInicio(VistaBeneficiarioInicio view) {
    
        m_view  = view;
       
        
        //... Add listeners to the view.
     
        m_view.anadirListenernavToMainFromBeneficiarios(new NavToMainFromBeneficiariosListener());
        m_view.anadirListenerbtBuscarBeneficiario(new BtBuscarBeneficiarioListener());
        m_view.anadirListenerbtNuevoBeneficiario(new BtNuevoBeneficiarioListener());
    }
    
    

    class BtNuevoBeneficiarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Raiz.mostrarVista(m_view, VistaBeneficiarioDatos.class);
        }
    }
    
    class BtBuscarBeneficiarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Raiz.mostrarVista(m_view, VistaBeneficiarioBuscar.class);
        }
    }
    
    class NavToMainFromBeneficiariosListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Raiz.mostrarVista(m_view, VistaInicial.class);
        }
    }
    
   
    
    
 

}
