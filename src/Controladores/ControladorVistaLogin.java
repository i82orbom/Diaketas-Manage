/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import JDBC.DriverJDBC;
import Modelos.ModeloVistaLogin;
import Vistas.Raiz;
import Vistas.VistaInicial;
import Vistas.VistaLogin;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author psylock
 */
public class ControladorVistaLogin {
    
    //... The Controller needs to interact with both the Model and View.
    private ModeloVistaLogin m_model;
    private VistaLogin  m_view;
    
    //========================================================== constructor
    /** Constructor */
    public ControladorVistaLogin(VistaLogin view) {
    
        m_view  = view;
        m_model = view.getModelo();
        
        //... Add listeners to the view.
        view.anadirListenerBtConectarse(new BtConectarseListener());
    }
    
    

    class BtConectarseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            
            m_view.actualizarModelo();
           
            DriverJDBC dr = DriverJDBC.getInstance();
			
				
            dr.configurar("127.0.0.1", "Diaketas", "diaketas", "diaketas");

            if (!dr.conectar()){
                m_view.mostrarErrorLogin("Hubo un problema de conexion a la BD");
                
            }
            else{
                JDBC.VoluntarioJDBC vol = JDBC.VoluntarioJDBC.getInstance();
		
                boolean exito = true;
                try{
                    m_view.actualizarModelo(); 
                    Modelo.Voluntario volun = vol.obtenerVoluntario(m_model.getUsuario());
                    if(volun == null ){
                     exito = false;
                    }
                  else{
	
                     if(!volun.getPassword().equals(m_model.getPass()))
                    	exito = false;
                    }
                
                }
		
                catch(SQLException ex){
                	exito = false;
                }
		
                if (exito){
                    Raiz.mostrarVista(m_view, VistaInicial.class);
            
                }
                else{
                    m_view.mostrarErrorLogin("Nombre usuario y/o contraseña no válidos");
                }
            }
        }
    }
    
    
 

}
