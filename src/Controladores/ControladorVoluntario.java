/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import JDBC.VoluntarioJDBC;
import Modelo.Voluntario;
import Vistas.Paneles.Voluntario.VistaVoluntario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 ** NOMBRE CLASE: *	ControladorVoluntario * * DESCRIPCION: * * * * DESARROLLADO
 * POR: Raphael Colleau (RC) * * * SUPERVISADO POR: * * * HISTORIA: * 000 - 10
 * mai 2012 - RC - Creacion * * NOTAS: * *
 */
public class ControladorVoluntario {

    /**
     * PATRON DE DISEÑO SINGLETON
     */
    private static ControladorVoluntario instancia;
    private static final String baseContrasena = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static ControladorVoluntario getInstance(VistaVoluntario panelVoluntario) {
        if (instancia == null) {
            instancia = new ControladorVoluntario(panelVoluntario);
        }

        return instancia;

    }

    public static String genContrasena() {

        String contrasena = "";
        int longitud = baseContrasena.length();
        int largoContrasena = 6;

        for (int i = 0; i < largoContrasena; i++) {
            int numero = (int) (Math.random() * (longitud));
            String caracter = baseContrasena.substring(numero, numero + 1);
            contrasena = contrasena + caracter;
        }
        return contrasena;

    }
    private VistaVoluntario vista;

    /**
     * Constructor de la clase
     */
    private ControladorVoluntario(VistaVoluntario pvista) {

        /**
         * Establece como ventana padre la pasada como parámetro
         */
        vista = pvista;

        // anadir listener
        vista.getBarraDeNavegacion().setListener(new ListenerBarraNavegacion());
        vista.getPanelVoluntarioInicio().anadirListenerbtAyudas(new btAyudasListener());
        vista.getPanelVoluntarioInicio().anadirListenerbtBuscarVoluntario(new btBuscarListener());
        vista.getPanelVoluntarioInicio().anadirListenerbtContabilidad(new btContabilidadListener());
        vista.getPanelVoluntarioInicio().anadirListenerbtNuevoVoluntario(new btDatosListener());

        vista.getPanelVoluntarioDatos().getBtGuardar().addActionListener(new btGuardarVoluntarioListener());
        vista.getPanelVoluntarioDatos().getBtBorrar().addActionListener(new btBorrarVoluntarioListener());

        // al principio mostrar la vista de inicio
        mostrarVistaInicio();
    }

    // mostrar la vista que queremos y actualizacion de la barra de navegacion
    private void mostrarVistaInicio() {
        vista.showPanel(VistaVoluntario.panelInicio);
        vista.getBarraDeNavegacion().setTextLabelMenuPrincipal("Voluntario");
    }

    private void mostrarVistaBuscar() {
        vista.showPanel(VistaVoluntario.panelBuscar);
        vista.getBarraDeNavegacion().setTextLabelMenuPrincipal("Voluntario");
        vista.getBarraDeNavegacion().setTextLabelMenuAvanzado("Buscar");
    }

    private void mostrarVistaDatos() {
        vista.showPanel(VistaVoluntario.panelDatos);
        vista.getBarraDeNavegacion().setTextLabelMenuPrincipal("Voluntario");
        vista.getBarraDeNavegacion().setTextLabelMenuAvanzado("Datos");
    }

    private void mostrarVistaAyudas() {
        vista.showPanel(VistaVoluntario.panelAyudas);
        vista.getBarraDeNavegacion().setTextLabelMenuPrincipal("Voluntario");
        vista.getBarraDeNavegacion().setTextLabelMenuAvanzado("Ayudas");
    }

    private void mostrarVistaContabilidad() {
        vista.showPanel(VistaVoluntario.panelContabilidad);
        vista.getBarraDeNavegacion().setTextLabelMenuPrincipal("Voluntario");
        vista.getBarraDeNavegacion().setTextLabelMenuAvanzado("Contabilidad");
    }

    // metodos de interaccion con JDBC
    private boolean insertarVoluntario(String[] datos, String password) {

        if (this.comprobarDatos(datos) == false || this.comprobarContrasena(password) == false) {
            return false;
        }

        Voluntario voluntario = new Voluntario();
        voluntario.setNIF(datos[Voluntario.NIF_ID]);
        voluntario.setNombre(datos[Voluntario.NOMBRE_ID]);
        voluntario.setApellidos(datos[Voluntario.APELLIDOS_ID]);
        voluntario.setFechaDENacimiento(Date.valueOf(datos[Voluntario.FECHA_DE_NACIMIENTO_ID]));
        voluntario.setDomicilio(datos[Voluntario.DOMICILIO_ID]);
        voluntario.setCP(Integer.parseInt(datos[Voluntario.CP_ID]));
        voluntario.setLocalidad(datos[Voluntario.LOCALIDAD_ID]);
        voluntario.setTelefonoMovil(Integer.parseInt(datos[Voluntario.TELEFONO_MOVIL_ID]));
        voluntario.setTelefonoFijo(Integer.parseInt(datos[Voluntario.TELEFONO_FIJO_ID]));


        try {
            VoluntarioJDBC.getInstance().añadirVoluntario(voluntario);
        } catch (SQLException se) {
            System.err.print(se.getMessage());
            return false;
        }

        return true;

    }

    private Voluntario consultarVoluntario(String DNI) {
        Voluntario voluntario;

        try {
            voluntario = VoluntarioJDBC.getInstance().obtenerVoluntario(DNI);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVoluntario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return voluntario;
    }

    private boolean modificarVoluntario(String[] datos) {

        if (this.comprobarDatos(datos) == false) {
            return false;
        }

        Voluntario voluntario = new Voluntario();
        voluntario.setNIF(datos[Voluntario.NIF_ID]);
        voluntario.setNombre(datos[Voluntario.NOMBRE_ID]);
        voluntario.setApellidos(datos[Voluntario.APELLIDOS_ID]);
        voluntario.setFechaDENacimiento(Date.valueOf(datos[Voluntario.FECHA_DE_NACIMIENTO_ID]));
        voluntario.setDomicilio(datos[Voluntario.DOMICILIO_ID]);
        voluntario.setCP(Integer.parseInt(datos[Voluntario.CP_ID]));
        voluntario.setLocalidad(datos[Voluntario.LOCALIDAD_ID]);
        voluntario.setTelefonoMovil(Integer.parseInt(datos[Voluntario.TELEFONO_MOVIL_ID]));
        voluntario.setTelefonoFijo(Integer.parseInt(datos[Voluntario.TELEFONO_FIJO_ID]));


        try {
            VoluntarioJDBC.getInstance().modificarDatosVoluntario(voluntario);
        } catch (SQLException se) {
            System.err.print(se.getMessage());
            return false;
        }

        return true;

    }

    private boolean eliminarVoluntario(String dni) {
        boolean exito;
        try {
            exito = VoluntarioJDBC.getInstance().borrarVoluntario(dni);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVoluntario.class.getName()).log(Level.SEVERE, null, ex);
            exito = false;
        }

        return exito;
    }

    private boolean comprobarDatos(String[] datos) {
        // cada campo debe ser not null
        /*
        for (int i = 0; i < datos.length; i++) {
            if (datos[i].length() < 1) {
                return false;
            }
        }*/

        if (!TestDatos.isDNI(datos[Voluntario.NIF_ID])) {
            return false;
        }

        if (!TestDatos.isCodigoPostal(datos[Voluntario.CP_ID])) {
            return false;
        }

        if (!TestDatos.isOnlyLetter(datos[Voluntario.NOMBRE_ID])) {
            return false;
        }

        if (!TestDatos.isOnlyLetter(datos[Voluntario.APELLIDOS_ID])) {
            return false;
        }

        if (!TestDatos.isOnlyLetter(datos[Voluntario.LOCALIDAD_ID])) {
            return false;
        }

        if (datos[Voluntario.TELEFONO_MOVIL_ID].length() > 0 && !TestDatos.isTelefonoOFax(datos[Voluntario.TELEFONO_MOVIL_ID])) {
            return false;
        }

        if (datos[Voluntario.TELEFONO_FIJO_ID].length() > 0 && !TestDatos.isTelefonoOFax(datos[Voluntario.TELEFONO_FIJO_ID])) {
            return false;
        }

        return true;
    }

    public boolean comprobarContrasena(String contrasena) {
        // TODO
        return true;
    }

    //Listener de la barra de navigacion
    class ListenerBarraNavegacion implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equalsIgnoreCase("toMain")) {
                mostrarVistaInicio();
                ControladorPrincipal.getInstance().mostrarVistaInicio();
            }

            if (ae.getActionCommand().equalsIgnoreCase("toMenuInicial")) {
                mostrarVistaInicio();
            }
        }
    }

    // listeners de los botones
    class btBuscarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarVistaBuscar();
        }
    }

    class btDatosListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarVistaDatos();
        }
    }

    class btAyudasListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarVistaAyudas();
        }
    }

    class btContabilidadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarVistaContabilidad();
        }
    }

    class btGuardarVoluntarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String[] datos = new String[15];

            datos[Voluntario.NIF_ID] = vista.getPanelVoluntarioDatos().getTextNIF();
            datos[Voluntario.NOMBRE_ID] = vista.getPanelVoluntarioDatos().getTextNombre();
            datos[Voluntario.APELLIDOS_ID] = vista.getPanelVoluntarioDatos().getTextApellidos();
            datos[Voluntario.FECHA_DE_NACIMIENTO_ID] = vista.getPanelVoluntarioDatos().getTextFechaNacimiento();
            datos[Voluntario.DOMICILIO_ID] = vista.getPanelVoluntarioDatos().getTextDomicilio();
            datos[Voluntario.LOCALIDAD_ID] = vista.getPanelVoluntarioDatos().getTextLocalidad();
            datos[Voluntario.CP_ID] = vista.getPanelVoluntarioDatos().getTextCP();
            datos[Voluntario.TELEFONO_MOVIL_ID] = vista.getPanelVoluntarioDatos().getTextTelefono();
            //datos[Voluntario.TELEFONO_FIJO_ID] = vista.getPanelVoluntarioDatos().getTextNivelEstudios();

            String password = genContrasena();
            boolean exito = insertarVoluntario(datos, password);
            
            
            if (exito) {
                vista.getPanelVoluntarioDatos().setTextLabelError("Voluntario anadido correctamente.");
            } else {
                vista.getPanelVoluntarioDatos().setTextLabelError("El voluntario no ha sido anadido.");
            }
        }
    }
    
    class btBorrarVoluntarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            vista.getPanelVoluntarioDatos().borrarCampos();
        }
    }
}
