/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.ModeloBeneficiarioBuscar;
import Modelos.ModeloBeneficiarioDatos;
import Vistas.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author psylock
 */
public class ControladorVistaBeneficiarioBuscar {
    
     //... The Controller needs to interact with both the Model and View.
    
    private VistaBeneficiarioBuscar m_view;
    
    //========================================================== constructor
    /** Constructor */
    public ControladorVistaBeneficiarioBuscar(VistaBeneficiarioBuscar view) {
    
       m_view  = view;
       
        
        //... Add listeners to the view.
     
       m_view.anadirListenerbtBuscarBeneficiarioDNI(new BtBuscarBeneficiarioDNIListener());
       m_view.anadirListenerbtVerBeneficiarioBusqueda(new BtVerBeneficiarioBusquedaListener());
       m_view.anadirListenernavToBeneficiariosFromBuscarBeneficiario(new NavToBeneficiariosFromBuscarBeneficiarioListener());
       m_view.anadirListenernavToMainFromBuscarBeneficiario(new NavToMainFromBuscarBeneficiarioListener());
    }
    
    

    class BtBuscarBeneficiarioDNIListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            
           ModeloBeneficiarioDatos mb = new ModeloBeneficiarioDatos();
           mb.setNombre("pepito");
           mb.setApellidos("grillo");
           mb.setEstadoCivil("Viudo");
           
           ((VistaBeneficiarioDatos)Raiz.getVista(m_view, VistaBeneficiarioDatos.class)).setModelo(mb);
           ((VistaBeneficiarioDatos)Raiz.getVista(m_view, VistaBeneficiarioDatos.class)).volcarModelo();
           
           Raiz.mostrarVista(m_view, VistaBeneficiarioDatos.class);
            
        }
    }
   
    class BtVerBeneficiarioBusquedaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            
        }
    }
    
    
    class NavToBeneficiariosFromBuscarBeneficiarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Raiz.mostrarVista(m_view, VistaBeneficiarioInicio.class);
        }
    }
   
     class NavToMainFromBuscarBeneficiarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Raiz.mostrarVista(m_view, VistaInicial.class);
        }
    }
   
}
