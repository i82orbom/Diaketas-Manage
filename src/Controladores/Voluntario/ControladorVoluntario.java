
package Controladores.Voluntario;

import Controladores.ControladorErrores;
import Controladores.ControladorPrincipal;
import Controladores.TestDatos;
import JDBC.VoluntarioJDBC;
import Modelo.Voluntario;
import Vistas.BarraDeNavegacion;
import Vistas.Paneles.Voluntario.VistaVoluntario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 ** NOMBRE CLASE:
 *	ControladorVoluntario
 *
 ** DESCRIPCION:
 *
 *
 * DESARROLLADO POR:
 *			Raphael Colleau (RC)
 *
 *
 * SUPERVISADO POR:
 *
 * HISTORIA:
 *		000 - 10 mai 2012 - RC - Creacion
 *
 *
 * NOTAS:
 *
 *
 */
public class ControladorVoluntario {

    /**
     * PATRON DE DISEÑO SINGLETON
     */
    private static ControladorVoluntario instancia;
    private static final String baseContrasena = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

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
    // el voluntario que esta modificando
    Voluntario voluntario_temp;
    // List de voluntarios por la tabla
    private ArrayList<Voluntario> voluntarios = new ArrayList<Voluntario>();
    private String[] columnNames = {"DNI", "Nombre y Apellidos", "Fecha de Nacimiento", "Localidad", "Telefono movil"};

    /**
     * Constructor de la clase
     */
    private ControladorVoluntario(VistaVoluntario pvista) {
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
        vista.getPanelVoluntarioInicio().anadirListenerbtColaboraciones(new btColaboracionesListener());
        vista.getPanelVoluntarioColaboraciones().anadirListenerbtAñadirColaboraciones(new btAñadirColaboracionesListener());
        vista.getPanelVoluntarioColaboraciones().anadirListenerbtCuotasNoPagatas(new btCuotasNoPagadasListener());

        vista.getPanelVoluntarioDatos().getBtGuardar().addActionListener(new btGuardarVoluntarioListener());
        vista.getPanelVoluntarioDatos().getBtBorrar().addActionListener(new btBorrarVoluntarioListener());
        vista.getPanelVoluntarioDatos().getBtEliminar().addActionListener(new BtEliminarVoluntario());

        vista.getPanelVoluntarioBuscar().getBtBuscar().addActionListener(new BtBuscarVoluntarioListener());
        vista.getPanelVoluntarioBuscar().getBtVerVoluntario().addActionListener(new BtVerVoluntarioListener());

        anadirKeyListener();

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

    private void mostrarVistaNuevoVoluntario() {
        voluntario_temp = null;
        vista.showPanel(VistaVoluntario.panelDatos);
        vista.getPanelVoluntarioDatos().nuevoVoluntario();
        vista.getBarraDeNavegacion().setTextLabelNivel1("Voluntario");
        vista.getBarraDeNavegacion().setTextLabelNivel2("Nuevo Voluntario");
    }

    private void mostrarVistaModificarVoluntario() {
        vista.showPanel(VistaVoluntario.panelDatos);
        vista.getBarraDeNavegacion().setTextLabelNivel1("Voluntario");
        vista.getBarraDeNavegacion().setTextLabelNivel2("Modificar Voluntario");
    }

    private void mostrarVistaAyudas() {
        vista.showPanel(VistaVoluntario.panelAyudas);
        ControladorAyuda.getInstance(null).actualizarTipoAyuda();
        vista.getBarraDeNavegacion().setTextLabelNivel1("Voluntario");
        vista.getBarraDeNavegacion().setTextLabelNivel2("Ayudas");
    }

    private void mostrarVistaContabilidad() {
        vista.showPanel(VistaVoluntario.panelContabilidad);
        vista.getBarraDeNavegacion().setTextLabelNivel1("Voluntario");
        vista.getBarraDeNavegacion().setTextLabelNivel2("Contabilidad");
    }

    private void mostrarVistaColaboraciones() {
        vista.showPanel(VistaVoluntario.panelColaboraciones);
        vista.getBarraDeNavegacion().setTextLabelNivel1("Voluntario");
        vista.getBarraDeNavegacion().setTextLabelNivel2("Colaboraciones");
    }

    private void mostrarVistaAñadirColaboraciones() {
        vista.showPanel(VistaVoluntario.panelAñadir);
        vista.getBarraDeNavegacion().setTextLabelNivel1("Voluntario");
        vista.getBarraDeNavegacion().setTextLabelNivel2("Colaboraciones");
        vista.getBarraDeNavegacion().setTextLabelNivel3("Añadir Couta");
    }

    private void mostrarVistaCuotasNoPagadas() {
        vista.showPanel(VistaVoluntario.panelCuotas);
        vista.getBarraDeNavegacion().setTextLabelNivel1("Voluntario");
        vista.getBarraDeNavegacion().setTextLabelNivel2("Colaboraciones");
        vista.getBarraDeNavegacion().setTextLabelNivel3("Cuotas no pagadas");
    }

    // metodos de interaccion con JDBC
    private boolean insertarVoluntario(Voluntario v) {
        if (consultarVoluntario(v.getNIF()) != null) {
            ControladorErrores.mostrarAlerta("Y a existe un voluntario con este DNI.");
            return false;
        }

        else {
            try {
                VoluntarioJDBC.getInstance().anadirVoluntario(v);
            } catch (SQLException se) {
                System.err.print(se.getMessage());
				ControladorErrores.mostrarError("Error al añadir voluntario:\n" + se.getMessage());
                return false;
            }
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
        ArrayList<Voluntario> t_voluntarios;

        try {
            t_voluntarios = VoluntarioJDBC.getInstance().obtenerListadoVoluntario(dato, tipoDato);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVoluntario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return t_voluntarios;
    }

    private boolean modificarVoluntario(Voluntario v) {

        if(!"".equals(v.getPassword()))
            v.setPassword(ControladorPrincipal.getInstance().md5(v.getPassword()+ControladorPrincipal.getInstance().getSalto()));
        else
            v.setPassword(null);

        try {
            VoluntarioJDBC.getInstance().modificarDatosVoluntario(v);
        } catch (SQLException se) {
            System.err.print(se.getMessage());
            return false;
        }

        return true;
    }

    private boolean eliminarVoluntario(Voluntario v) {
        boolean exito;
        try {
            exito = VoluntarioJDBC.getInstance().borrarVoluntario(v);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVoluntario.class.getName()).log(Level.SEVERE, null, ex);
            exito = false;
        }

        return exito;
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
            
            if (ae.getActionCommand().equalsIgnoreCase(BarraDeNavegacion.TO_NIVEL2)) {
				if(vista.getBarraDeNavegacion().getTextLebelNivel2().equalsIgnoreCase("Colaboraciones"))
					mostrarVistaColaboraciones();
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
            mostrarVistaNuevoVoluntario();
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

    class btColaboracionesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarVistaColaboraciones();
        }
    }

    class btAñadirColaboracionesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarVistaAñadirColaboraciones();
        }
    }

    class btCuotasNoPagadasListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarVistaCuotasNoPagadas();
        }
    }

    class btGuardarVoluntarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            boolean datosCorrectos = true;

            if( !TestDatos.isDNI( vista.getPanelVoluntarioDatos().getTextNIF().getText()) ) {
                vista.getPanelVoluntarioDatos().getlNIF().setForeground(Color.RED);
                datosCorrectos = false;
            }

            if ( !TestDatos.isNombre(vista.getPanelVoluntarioDatos().getTextNombre().getText())) {
                vista.getPanelVoluntarioDatos().getlNombre().setForeground(Color.RED);
                datosCorrectos = false;
            }

            if ( !TestDatos.isNombre(vista.getPanelVoluntarioDatos().getTextApellidos().getText())) {
                vista.getPanelVoluntarioDatos().getlApellidos().setForeground(Color.RED);
                datosCorrectos = false;
            }

            if ( !TestDatos.isFecha(vista.getPanelVoluntarioDatos().getTextFechaNacimiento().getText())) {
                vista.getPanelVoluntarioDatos().getlFechaNacimiento().setForeground(Color.RED);
                datosCorrectos = false;
            }

            if ( !TestDatos.isDomicilio(vista.getPanelVoluntarioDatos().getTextDomicilio().getText())) {
                vista.getPanelVoluntarioDatos().getlDomicilio().setForeground(Color.RED);
                datosCorrectos = false;
            }

            if ( !TestDatos.isDomicilio(vista.getPanelVoluntarioDatos().getTextLocalidad().getText())) {
                vista.getPanelVoluntarioDatos().getlLocalidad().setForeground(Color.RED);
                datosCorrectos = false;
            }

            if ( !TestDatos.isCodigoPostal(vista.getPanelVoluntarioDatos().getTextCP().getText())) {
                vista.getPanelVoluntarioDatos().getlCP().setForeground(Color.RED);
                datosCorrectos = false;
            }

            if ( !TestDatos.isTelefonoOFax(vista.getPanelVoluntarioDatos().getTextTelMovil().getText())) {
                vista.getPanelVoluntarioDatos().getlTelMovil().setForeground(Color.RED);
                datosCorrectos = false;
            }

            if ( !TestDatos.isTelefonoOFax(vista.getPanelVoluntarioDatos().getTextTelFijo().getText())) {
                vista.getPanelVoluntarioDatos().getlTelFijo().setForeground(Color.RED);
                datosCorrectos = false;
            }
            
            if (!TestDatos.isOnlyLetterOrDigit(vista.getPanelVoluntarioDatos().getTextPassword().getText())) {
                vista.getPanelVoluntarioDatos().getlPassword().setForeground(Color.RED);
                datosCorrectos = false;
            }

            if (!datosCorrectos) {
                vista.getPanelVoluntarioDatos().setTextLabelError("Los campos en rojo tienen errores.");
            } else {

                // Nuevo Voluntario
                if (voluntario_temp == null) {
                    Voluntario v = new Voluntario();

                    v.setNIF(vista.getPanelVoluntarioDatos().getTextNIF().getText());
                    v.setNombre(vista.getPanelVoluntarioDatos().getTextNombre().getText());
                    v.setApellidos(vista.getPanelVoluntarioDatos().getTextApellidos().getText());
                    try {
                        v.setFechaDENacimiento(TestDatos.formatter.parse(vista.getPanelVoluntarioDatos().getTextFechaNacimiento().getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorVoluntario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    v.setDomicilio(vista.getPanelVoluntarioDatos().getTextDomicilio().getText());
                    v.setCP(vista.getPanelVoluntarioDatos().getTextCP().getText());
                    v.setLocalidad(vista.getPanelVoluntarioDatos().getTextLocalidad().getText());
                    v.setTelefonoMovil(vista.getPanelVoluntarioDatos().getTextTelMovil().getText());
                    v.setTelefonoFijo(vista.getPanelVoluntarioDatos().getTextTelFijo().getText());
                    /*
                     * Codificar password con md5 mas un salto
                     */
                    String password = ControladorPrincipal.getInstance().md5(vista.getPanelVoluntarioDatos().getTextPassword().getText() + ControladorPrincipal.getInstance().getSalto());
                    v.setPassword(password);

                    if (insertarVoluntario(v)) {
                        vista.getPanelVoluntarioDatos().setTextLabelError("Voluntario añadido correctamente.");
                    } else {
                        vista.getPanelVoluntarioDatos().setTextLabelError("El voluntario no ha sido añadido.");
                    }

                } // Modificacion Voluntario
                else {
                    voluntario_temp.setNIF(vista.getPanelVoluntarioDatos().getTextNIF().getText());
                    voluntario_temp.setNombre(vista.getPanelVoluntarioDatos().getTextNombre().getText());
                    voluntario_temp.setApellidos(vista.getPanelVoluntarioDatos().getTextApellidos().getText());
                    try {
                        voluntario_temp.setFechaDENacimiento(TestDatos.formatter.parse(vista.getPanelVoluntarioDatos().getTextFechaNacimiento().getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorVoluntario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    voluntario_temp.setDomicilio(vista.getPanelVoluntarioDatos().getTextDomicilio().getText());
                    voluntario_temp.setCP(vista.getPanelVoluntarioDatos().getTextCP().getText());
                    voluntario_temp.setLocalidad(vista.getPanelVoluntarioDatos().getTextLocalidad().getText());
                    voluntario_temp.setTelefonoMovil(vista.getPanelVoluntarioDatos().getTextTelMovil().getText());
                    voluntario_temp.setTelefonoFijo(vista.getPanelVoluntarioDatos().getTextTelFijo().getText());
                    voluntario_temp.setPassword(vista.getPanelVoluntarioDatos().getTextPassword().getText());

                    if (modificarVoluntario(voluntario_temp)) {
                        vista.getPanelVoluntarioDatos().setTextLabelError("Voluntario modificado correctamente.");
                    } else {
                        vista.getPanelVoluntarioDatos().setTextLabelError("El voluntario no ha sido modificado.");
                    }
                }
            }
        }
    }

    private void anadirKeyListener () {
        TextKeyListener keyListener = new TextKeyListener();

        vista.getPanelVoluntarioDatos().getTextApellidos().addKeyListener(keyListener);
        vista.getPanelVoluntarioDatos().getTextCP().addKeyListener(keyListener);
        vista.getPanelVoluntarioDatos().getTextDomicilio().addKeyListener(keyListener);
        vista.getPanelVoluntarioDatos().getTextFechaNacimiento().addKeyListener(keyListener);
        vista.getPanelVoluntarioDatos().getTextLocalidad().addKeyListener(keyListener);
        vista.getPanelVoluntarioDatos().getTextNIF().addKeyListener(keyListener);
        vista.getPanelVoluntarioDatos().getTextNombre().addKeyListener(keyListener);
        vista.getPanelVoluntarioDatos().getTextTelFijo().addKeyListener(keyListener);
        vista.getPanelVoluntarioDatos().getTextTelMovil().addKeyListener(keyListener);
        vista.getPanelVoluntarioDatos().getTextPassword().addKeyListener(keyListener);
    }

    // Cuando el usuario escribe, cambia el color en negro si habia errores y el color estaba rojo
    class TextKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent ke) {
            if (ke.getSource().getClass() == JTextField.class || ke.getSource().getClass() == JFormattedTextField.class) {
                vista.getPanelVoluntarioDatos().setColorLabels(Color.BLACK);
            }
        }

        @Override
        public void keyPressed(KeyEvent ke) {
        }

        @Override
        public void keyReleased(KeyEvent ke) {
        }

    }

    class btBorrarVoluntarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            vista.getPanelVoluntarioDatos().limpiarCampos();
        }
    }

    class BtEliminarVoluntario implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar el Voluntario?", "Eliminar Voluntario", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                if (eliminarVoluntario(voluntario_temp)) {
                    vista.getPanelVoluntarioDatos().setTextLabelError("El Voluntario ha sido eliminado del sistema.");
                } else {
                    vista.getPanelVoluntarioDatos().setTextLabelError("Error : el Voluntario no ha sido eliminado del sistema.");
                }
            }

        }

    }

    class BtVerVoluntarioListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (vista.getPanelVoluntarioBuscar().getTablaBusqueda().getSelectedRow() != -1) {
                voluntario_temp = voluntarios.get(vista.getPanelVoluntarioBuscar().getTablaBusqueda().getSelectedRow());
                vista.getPanelVoluntarioDatos().modificarVoluntario(voluntario_temp);
                mostrarVistaModificarVoluntario();
            }
        }

    }

    class BtBuscarVoluntarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            String busqueta = vista.getPanelVoluntarioBuscar().getTextBusqueda();
            String tipoBusqueta = vista.getPanelVoluntarioBuscar().getCbTipoBusqueda().getSelectedItem().toString();

            voluntarios = obtenerListadoVoluntarios(busqueta, tipoBusqueta);

            TableModel tableModel = new TableModel() {

                @Override
                public int getRowCount() {
                    return voluntarios.size();
                }

                @Override
                public int getColumnCount() {
                    return columnNames.length;
                }

                @Override
                public String getColumnName(int i) {
                    return columnNames[i];
                }

                @Override
                public Class<?> getColumnClass(int i) {
                    return String.class;
                }

                @Override
                public boolean isCellEditable(int i, int i1) {
                    return false;
                }

                @Override
                public Object getValueAt(int row, int col) {
                    switch (col) {
                        case 0:
                            return voluntarios.get(row).getNIF();
                        case 1:
                            return voluntarios.get(row).getNombre() + " " + voluntarios.get(row).getApellidos();
                        case 2:
                            return formatter.format(voluntarios.get(row).getFechaDENacimiento());
                        case 3:
                            return voluntarios.get(row).getLocalidad();
                        case 4:
                            return voluntarios.get(row).getTelefonoMovil();
                    }
                    return "";
                }

                @Override
                public void setValueAt(Object o, int row, int col) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void addTableModelListener(TableModelListener tl) {

                }

                @Override
                public void removeTableModelListener(TableModelListener tl) {

                }
            };

            vista.getPanelVoluntarioBuscar().getTablaBusqueda().setModel(tableModel);
        }

    }

}
