/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.Voluntario.ControladorVoluntario;
import JDBC.DriverJDBC;
import Modelo.Voluntario;
import Vistas.Ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 ** NOMBRE CLASE: 
 *	
 * ControladorPrincipal 
 * 
 * DESCRIPCION: 
 * 
 * 
 * 
 * DESARROLLADO POR: 
 *      Raphael Colleau (RC) 
 *
 *
 *
 * SUPERVISADO POR: 
 *
 * 
 * HISTORIA: 
 *      000 - 10 mai 2012 - RC - Creacion 
 * 
 * NOTAS: 
 * 
 *
 */
public class ControladorPrincipal {

    /**
     * PATRON DE DISEÑO SINGLETON
     */
    private static ControladorPrincipal instancia;

    public static ControladorPrincipal getInstance() {
        if (instancia == null) {
            // Crea la ventana y el controlador con ella
            Ventana ventana = new Ventana();
            instancia = new ControladorPrincipal(ventana);
        }

        return instancia;

    }
    private Ventana vista;

    /**
     * Constructor de la clase
     */
    private ControladorPrincipal(Ventana pvista) {

        /**
         * Establece como ventana padre la pasada como parámetro
         */
        vista = pvista;
        vista.setVisible(true);
        
        // al principio mostra el panel de Login
        vista.showPanel(Ventana.panelLogin);

        // Para crear la instancia de los controladores con la vista asociada
        ControladorVoluntario.getInstance(this.vista.getPanelVoluntario());
        ControladorBeneficiario.getInstance(this.vista.getVistaBeneficiario());

        // adicion de los listeners cuyo el controlador se encarga
        vista.getVistaLogin().anadirListenerBtConectarse(new BtConectarseListener());
        vista.getVistaInicial().anadirListenerbtVoluntario(new BtVoluntarioListener());
        vista.getVistaInicial().anadirListenerbtBeneficiario(new BtBeneficiarioListener());
    }

    /*
     * Para que los controladores especificos pregunta al controlador principal de volver a la vista de inicio
     */
    public void mostrarVistaInicio() {
        vista.showPanel(Ventana.panelInicio);
    }

    // Listeners botones
    class BtConectarseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            DriverJDBC dr = DriverJDBC.getInstance();


            dr.configurar("127.0.0.1", "Diaketas", "diaketas", "diaketas");

            if (!dr.conectar()) {
                vista.getVistaLogin().mostrarErrorLogin("Hubo un problema de conexion a la BD");

            } else {
                JDBC.VoluntarioJDBC vol = JDBC.VoluntarioJDBC.getInstance();

                boolean exito = true;
                try {
                    Voluntario volun = vol.obtenerVoluntario(vista.getVistaLogin().getTextFieldIdUsuario().getText());
                    if (volun == null) {
                        exito = false;
                    } else {
                        if (!volun.getPassword().equals(vista.getVistaLogin().getTextFieldContrasena().getText())) {
                            exito = false;
                        }
                    }
                } catch (SQLException ex) {
                    exito = false;
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (exito) {
                    vista.showPanel(Ventana.panelInicio);
                } else {
                    vista.getVistaLogin().mostrarErrorLogin("Nombre usuario y/o contraseña no válidos");
                }
            }
        }
    }

    class BtVoluntarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            vista.showPanel(Ventana.panelVoluntario);
        }
    }
    
    
     class  BtBeneficiarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            vista.showPanel(Ventana.panelBeneficiario);
        }
     }
}
