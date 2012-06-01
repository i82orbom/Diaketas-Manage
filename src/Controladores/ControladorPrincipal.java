
package Controladores;

import Controladores.Voluntario.ControladorVoluntario;
import Modelo.Voluntario;
import Vistas.Ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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
 *		Antonio Rodriguez Segura (ARS)
 *
 * HISTORIA:
 *      000 - 10 May 2012 - RC - Creacion
 *      001 - 23 May 2012 - ARS - Mejora para eliminar la conexion contunia a la BD
 *      002 - 1 Jun 2012 - RC - Se puede connectarse pulsando el boton "enter" en el teclado
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
    private Voluntario voluntario;

    /*
     * Salto para la codificación de la contraseña (para login y registro)
     */
    private String salto = "Mary Popins";

    /**
     * Constructor de la clase
     *
     * @param pvista Panel de vista de inicio
     */
    private ControladorPrincipal(Ventana pvista) {

        /**
         * Establece como ventana padre la pasada como parámetro
         */
        vista = pvista;
        vista.setVisible(true);
        //vistaC.setVisible(false);

        // al principio mostra el panel de Login
        vista.showPanel(Ventana.panelLogin);

        // Para crear la instancia de los controladores con la vista asociada
        ControladorVoluntario.getInstance(this.vista.getPanelVoluntario());
        ControladorBeneficiario.getInstance(this.vista.getVistaBeneficiario());
        ControladorColaboradores.getInstance(this.vista.getVistaColaboradores());
        ControladorBolsaTrabajo.getInstance(this.vista.getVistaBolsaTrabajo());

        // adicion de los listeners cuyo el controlador se encarga
        vista.getVistaLogin().anadirListenerBtConectarse(new BtConectarseListener());
        vista.getVistaLogin().getTextFieldContrasena().addKeyListener(new ConnectarseKeyListener());
        vista.getVistaInicial().anadirListenerbtVoluntario(new BtVoluntarioListener());
        vista.getVistaInicial().anadirListenerbtBeneficiario(new BtBeneficiarioListener());
        vista.getVistaInicial().anadirListenerbtDesconectase(new BtDesconectarseListener());
        vista.getVistaInicial().anadirListenerbtColaboradores(new BtColaboradoresListener());
        vista.getVistaInicial().anadirListenerbtBolsaTrabajo(new BtBolsaTrabajoListener());
        
        // Para probar
        vista.getVistaLogin().getTextFieldIdUsuario().setText("12345678A");
        vista.getVistaLogin().getTextFieldContrasena().setText("admin");
    }

    /**
     * Para que los controladores especificos pregunta al controlador principal
     * de volver a la vista de inicio
     */
    public void mostrarVistaInicio() {
        vista.showPanel(Ventana.panelInicio);
    }

    public String getSalto() {
        return salto;
    }

    /*
     * Funcion para generar hash md5 a partir de una cadena
     */
    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2) {
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    private void connectar() {
        boolean exito = true;

        try {
            voluntario = JDBC.VoluntarioJDBC.getInstance().obtenerVoluntario(vista.getVistaLogin().getTextFieldIdUsuario().getText());
        } catch (SQLException ex) {
            exito = false;
            vista.getVistaLogin().mostrarErrorLogin("Hubo un problema con la Base de Datos");
//				Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
//				System.err.println("Error al conectar a la base de datos:\n"+ex);
        }

        if (exito) {
            // Comprobamos que el voluntario existe
            if (voluntario == null) {
                exito = false;
            } // La contraseña se corresponde
            else if (!voluntario.getPassword().equals(md5(vista.getVistaLogin().getTextFieldContrasena().getText() + getSalto()))) {
                voluntario = null;
                exito = false;
            }

            if (exito) {
                vista.getVistaLogin().mostrarErrorLogin("");
                // Limpiar el formulario de inicio de sesion
                vista.showPanel(Ventana.panelInicio);
            } else {
                vista.getVistaLogin().mostrarErrorLogin("Nombre usuario y/o contraseña no válidos");
            }

        }
    }

    // Listeners botones
    class BtConectarseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            connectar();
        }
    }
    
    class ConnectarseKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent ke) {
            
        }

        @Override
        public void keyPressed(KeyEvent ke) {
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                connectar();
            }
        }
        
    }

    class BtDesconectarseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            voluntario = null;
            vista.showPanel(Ventana.panelLogin);
            vista.getVistaLogin().getTextFieldIdUsuario().setText("");
            vista.getVistaLogin().getTextFieldContrasena().setText("");
        }
    }

    class BtVoluntarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            vista.showPanel(Ventana.panelVoluntario);
        }
    }

    class BtBeneficiarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            vista.showPanel(Ventana.panelBeneficiario);
        }
    }

    class BtColaboradoresListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            vista.showPanel(Ventana.panelColaboradores);
        }
    }

    class BtBolsaTrabajoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            vista.showPanel(Ventana.panelBolsaTrabajo);
        }
    }
}
