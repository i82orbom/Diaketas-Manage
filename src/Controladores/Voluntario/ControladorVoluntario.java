/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Voluntario;

import Controladores.ControladorPrincipal;
import Controladores.TestDatos;
import JDBC.VoluntarioJDBC;
import Modelo.Voluntario;
import Vistas.BarraDeNavegacion;
import Vistas.Paneles.Voluntario.VistaVoluntario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        
        // Para crear la instancia de los controladores con la vista asociada
        ControladorContabilidad.getInstance(vista.getPanelVoluntarioContabilidad());
        ControladorAyuda.getInstance(vista.getPanelVoluntarioAyudas());

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
        vista.getBarraDeNavegacion().setTextLabelNivel1("Voluntario");
    }

    private void mostrarVistaBuscar() {
        vista.showPanel(VistaVoluntario.panelBuscar);
        vista.getBarraDeNavegacion().setTextLabelNivel1("Voluntario");
        vista.getBarraDeNavegacion().setTextLabelNivel2("Buscar");
    }

    private void mostrarVistaDatos() {
        vista.showPanel(VistaVoluntario.panelDatos);
        vista.getBarraDeNavegacion().setTextLabelNivel1("Voluntario");
        vista.getBarraDeNavegacion().setTextLabelNivel2("Datos");
    }

    private void mostrarVistaAyudas() {
        vista.showPanel(VistaVoluntario.panelAyudas);
        vista.getBarraDeNavegacion().setTextLabelNivel1("Voluntario");
        vista.getBarraDeNavegacion().setTextLabelNivel2("Ayudas");
    }

    private void mostrarVistaContabilidad() {
        vista.showPanel(VistaVoluntario.panelContabilidad);
        vista.getBarraDeNavegacion().setTextLabelNivel1("Voluntario");
        vista.getBarraDeNavegacion().setTextLabelNivel2("Contabilidad");
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
        try {
            voluntario.setFechaDENacimiento(TestDatos.formatter.parse(vista.getPanelVoluntarioDatos().getTextFechaNacimiento()));
        } catch (ParseException ex) {
            Logger.getLogger(ControladorVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }
        voluntario.setDomicilio(datos[Voluntario.DOMICILIO_ID]);
        voluntario.setCP(Integer.parseInt(datos[Voluntario.CP_ID]));
        voluntario.setLocalidad(datos[Voluntario.LOCALIDAD_ID]);
        voluntario.setTelefonoMovil(Integer.parseInt(datos[Voluntario.TELEFONO_MOVIL_ID]));
        voluntario.setTelefonoFijo(Integer.parseInt(datos[Voluntario.TELEFONO_FIJO_ID]));
        voluntario.setPassword(password);
        
        try {
            VoluntarioJDBC.getInstance().anadirVoluntario(voluntario);
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
    
    private ArrayList<Voluntario> obtenerListadoVoluntarios (String dato, String tipoDato) {
        ArrayList<Voluntario> voluntarios;
        
        try {
            voluntarios = VoluntarioJDBC.getInstance().obtenerListadoVoluntario(dato, tipoDato);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVoluntario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return voluntarios;
    }

    private boolean modificarVoluntario(String[] datos) {

        if (this.comprobarDatos(datos) == false) {
            return false;
        }

        Voluntario voluntario = new Voluntario();
        voluntario.setNIF(datos[Voluntario.NIF_ID]);
        voluntario.setNombre(datos[Voluntario.NOMBRE_ID]);
        voluntario.setApellidos(datos[Voluntario.APELLIDOS_ID]);
        try {
            voluntario.setFechaDENacimiento(TestDatos.formatter.parse(vista.getPanelVoluntarioDatos().getTextFechaNacimiento()));
        } catch (ParseException ex) {
            Logger.getLogger(ControladorVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            if (ae.getActionCommand().equalsIgnoreCase(BarraDeNavegacion.TO_VISTA_INICIAL)) {
                mostrarVistaInicio();
                ControladorPrincipal.getInstance().mostrarVistaInicio();
            }

            if (ae.getActionCommand().equalsIgnoreCase(BarraDeNavegacion.TO_NIVEL1)) {
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
            
            try {
                System.out.println("Date : " + TestDatos.formatter.parse(vista.getPanelVoluntarioDatos().getTextFechaNacimiento()).toString());
            } catch (ParseException ex) {
                vista.getPanelVoluntarioDatos().setTextLabelError("Pon el fecha de nacimiento con este formato dd/mm/aaaa");
                Logger.getLogger(ControladorVoluntario.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String[] datos = new String[15];

            datos[Voluntario.NIF_ID] = vista.getPanelVoluntarioDatos().getTextNIF();
            datos[Voluntario.NOMBRE_ID] = vista.getPanelVoluntarioDatos().getTextNombre();
            datos[Voluntario.APELLIDOS_ID] = vista.getPanelVoluntarioDatos().getTextApellidos();
            datos[Voluntario.FECHA_DE_NACIMIENTO_ID] = vista.getPanelVoluntarioDatos().getTextFechaNacimiento();
            datos[Voluntario.DOMICILIO_ID] = vista.getPanelVoluntarioDatos().getTextDomicilio();
            datos[Voluntario.LOCALIDAD_ID] = vista.getPanelVoluntarioDatos().getTextLocalidad();
            datos[Voluntario.CP_ID] = vista.getPanelVoluntarioDatos().getTextCP();
            datos[Voluntario.TELEFONO_MOVIL_ID] = vista.getPanelVoluntarioDatos().getTextTelFijo();
            datos[Voluntario.TELEFONO_FIJO_ID] = vista.getPanelVoluntarioDatos().getTextTelMovil();
            datos[Voluntario.PASSWORD_ID] = vista.getPanelVoluntarioDatos().getTextPassword();

            boolean exito = insertarVoluntario(datos, datos[Voluntario.PASSWORD_ID]);
            
            
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
