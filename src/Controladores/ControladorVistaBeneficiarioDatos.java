/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Vistas.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author psylock
 */
public class ControladorVistaBeneficiarioDatos {
    
     //... The Controller needs to interact with both the Model and View.
    
    private VistaBeneficiarioDatos m_view;
    
    //========================================================== constructor
    /** Constructor */
    public ControladorVistaBeneficiarioDatos(VistaBeneficiarioDatos view) {
    
       m_view  = view;
       
        
        //... Add listeners to the view.
     
       m_view.anadirListenerbtEliminarBeneficiario(new BtEliminarBeneficiarioListener());
       m_view.anadirListenerbtEliminarFamiliarBeneficiario(new BtEliminarFamiliarBeneficiarioListener());
       m_view.anadirListenerbtEliminarIntervencionBeneficiario(new BtEliminarIntervencionBeneficiarioListener());
       m_view.anadirListenerbtGuardarBeneficiario(new BtGuardarBeneficiarioListener());
       m_view.anadirListenerbtGuardarFamiliarBeneficiario(new BtGuardarFamiliarBeneficiarioListener());
       m_view.anadirListenerbtGuardarIntervencionBeneficiario(new BtGuardarIntervencionBeneficiarioListener());
       m_view.anadirListenernavToBeneficiariosFromBeneficiarioDatos(new NavToBeneficiariosFromBeneficiarioDatosListener());
       m_view.anadirListenernavToMainFromBeneficiarioDatos(new NavToMainFromBeneficiarioDatosListener());
     
    }
    
    class BtEliminarBeneficiarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
           
        }
    }
    
    class BtEliminarFamiliarBeneficiarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
           
        }
    }
    
    class BtEliminarIntervencionBeneficiarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            
        }
    }
    
    class BtGuardarBeneficiarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
           
        }
    }
    
    class BtGuardarFamiliarBeneficiarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            
        }
    }
    
    class BtGuardarIntervencionBeneficiarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            
        }
    }
    
    class NavToBeneficiariosFromBeneficiarioDatosListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
             
            Raiz.mostrarVista(m_view, VistaBeneficiarioInicio.class);
        }
    }
   
    class NavToMainFromBeneficiarioDatosListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Raiz.mostrarVista(m_view, VistaInicial.class);
        }
    }
   
   
}
