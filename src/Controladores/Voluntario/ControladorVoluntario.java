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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

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
    private ArrayList<Voluntario> voluntarios = new ArrayList<Voluntario>();
    private String[] columnNames = {"DNI", "Nombre y Apellidos", "Fecha de Nacimiento", "Localidad", "Telefono movil"};

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
        vista.getPanelVoluntarioInicio().anadirListenerbtColaboraciones(new btColaboracionesListener());
        vista.getPanelVoluntarioColaboraciones().anadirListenerbtAñadirColaboraciones(new btAñadirColaboracionesListener());
        vista.getPanelVoluntarioColaboraciones().anadirListenerbtCuotasNoPagatas(new btCuotasNoPagadasListener());

        vista.getPanelVoluntarioDatos().getBtGuardar().addActionListener(new btGuardarVoluntarioListener());
        vista.getPanelVoluntarioDatos().getBtBorrar().addActionListener(new btBorrarVoluntarioListener());

        vista.getPanelVoluntarioBuscar().getBtBuscar().addActionListener(new BtBuscarVoluntarioListener());

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
        vista.showPanel(VistaVoluntario.panelDatos);
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
    private boolean insertarVoluntario(String[] datos) {

        if (this.comprobarDatos(datos) == false || this.comprobarContrasena(datos[Voluntario.PASSWORD_ID]) == false) {
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
        voluntario.setPassword(datos[Voluntario.PASSWORD_ID]);

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
        ArrayList<Voluntario> t_voluntarios;

        try {
            t_voluntarios = VoluntarioJDBC.getInstance().obtenerListadoVoluntario(dato, tipoDato);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVoluntario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return t_voluntarios;
    }

    private boolean modificarVoluntario(String[] datos,String password) {

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
        if(!"".equals(password))
            voluntario.setPassword(ControladorPrincipal.getInstance().md5(password+ControladorPrincipal.getInstance().getSalto()));
        else
            voluntario.setPassword(null);

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

            try {
				System.out.println("Date : " + TestDatos.formatter.parse(vista.getPanelVoluntarioDatos().getTextFechaNacimiento()).toString());

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

				/* Codificar password con md5 mas un salto */
				String password = ControladorPrincipal.getInstance().md5(vista.getPanelVoluntarioDatos().getTextPassword()+ControladorPrincipal.getInstance().getSalto());
				datos[Voluntario.PASSWORD_ID] = password;

				boolean exito = insertarVoluntario(datos);

				if (exito) {
					vista.getPanelVoluntarioDatos().setTextLabelError("Voluntario añadido correctamente.");
				} else {
					vista.getPanelVoluntarioDatos().setTextLabelError("El voluntario no ha sido añadido.");
				}
			} catch (ParseException ex) {
				vista.getPanelVoluntarioDatos().setTextLabelError("La fecha de nacimiento debe tener el formato dd/mm/aaaa");
				Logger.getLogger(ControladorVoluntario.class.getName()).log(Level.SEVERE, null, ex);
			}

        }

    }

    class btBorrarVoluntarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            vista.getPanelVoluntarioDatos().borrarCampos();
        }
    }

    class BtBuscarVoluntarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ArrayList<Voluntario> voluntarios;

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
                            return voluntarios.get(row).getFechaDENacimiento();
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
            //vista.getPanelVoluntarioBuscar().getTablaBusqueda().repaint();
        }

    }

}
