
package Controladores;

import Controladores.Voluntario.ControladorAyuda;
import JDBC.BeneficiarioJDBC;
import Modelo.Ayuda;
import Modelo.Beneficiario;
import Modelo.Persona;
import Modelo.TipoAyuda;
import Vistas.BarraDeNavegacion;
import Vistas.Paneles.Beneficiario.VistaBeneficiario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 ** NOMBRE CLASE:
 **	  ControladorBeneficiario
 **
 ** DESCRIPCION:
 **
 **
 **
 ** DESARROLLADO POR:
 *          Mario Orozco Borrego (MOB)
 *          Raphael Colleau (RC)
 **
 **
 ** SUPERVISADO POR:
 **
 **
 ** HISTORIA:
 ** 	000 - 20 Mar 2012 - MOB - Creacion
 **	001 - 18 May 2012 - ARS - Cambios para añadir beneficiario
 *	002 - 20 May 2012 - ARS - Comprobar datos del beneficiario
 *      003 - 23 May 2012 - JAEG - Buscar y consultar beneficiarios
 *      004 - 24 May 2012 - JAEG - Consultar intervenciones del beneficiario
 *      005 - 29 May 2012 - RC - Guardar intervenciones del beneficiario y limpiar campos
 *      006 - 30 May 2012 - JAEG - Añadidas funciones para mostrar familaires del beneficiario
 ** NOTAS:
 **
 **
 */
public class ControladorBeneficiario {

    /**
     * PATRON DE DISEÑO SINGLETON
     */
    private static ControladorBeneficiario instancia;

    public static ControladorBeneficiario getInstance(VistaBeneficiario panelBeneficiario) {
        if (instancia == null) {
            instancia = new ControladorBeneficiario(panelBeneficiario);
        }

        return instancia;
    }
    private VistaBeneficiario vista;
    /*
     * Lista con los resultados de la busqueda de beneficiarios
     */
    private ArrayList<Beneficiario> beneficiarios;

    /*
     * Ultimo beneficiario consultado
     */
    private Beneficiario benef = null;
    /*
     * Nombre de las columnas para el mapeo de los datos en la vista
     */
    String[] columnasBuscarBeneficiario = {"NIF", "Nombre y Apellidos", "Fecha Nacimiento", "Localidad", "Teléfono Móvil"};
    String[] columnasIntervenciones = {"Fecha", "Concepto", "Importe", "Observaciones", "Voluntario"};
    String[] columnasFamiliares = {"Nombre y Apellidos", "Fecha nacimiento", "Parentesco","Ocupacion"};
    /*
     * Listado con el tipo de ayudas disponibles
     */
    ArrayList<TipoAyuda> tiposAyuda;
    /*
     * Ayuda seleccionada en la tabla de ayuda
     */
    Ayuda ayudaSeleccionada;
    /*
     * Formateador de fechas
     */
    private SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Constructor de la clase
     */
    private ControladorBeneficiario(VistaBeneficiario pvista) {
        /**
         * Establece como ventana padre la pasada como parámetro
         */
        vista = pvista;

        // anadir listener
        vista.getBarraDeNavigacion().setListener(new ListenerBarraNavigacion());


        // al principio mostrar la vista de inicio
        mostrarVistaInicio();

        vista.getPanelInicio().anadirListenerbtBuscarBeneficiario(new btBuscarListener());
        vista.getPanelInicio().anadirListenerbtNuevoBeneficiario(new btNuevoBeneficiarioListener());

        vista.getPanelDatos().getBtGuardar().addActionListener(new btGuardarBeneficiarioListener());
        vista.getPanelDatos().getBtBorrar().addActionListener(new btBorrarBeneficiarioListener());
        vista.getPanelDatos().getBtGuardarIntervencionBeneficiario().addActionListener(new BtGuardarIntervencionesListener());
        vista.getPanelDatos().getBtEliminarIntervencionBeneficiario().addActionListener(new BtEliminarIntervencionesListener());
        vista.getPanelDatos().getTbIntervenciones().addMouseListener(new TablaAyudaListener());

        vista.getPanelBuscar().getBtBuscarBeneficiarios().addActionListener(new btBuscarBeneficiariosListener());
        vista.getPanelBuscar().getBtVerBeneficiarioBusqueda().addActionListener(new btVerBeneficiarioListener());
    }

    // mostrar la vista que queremos y actualizacion de la barra de navigacion
    private void mostrarVistaInicio() {
        vista.showPanel(VistaBeneficiario.panelInicio);
        vista.getBarraDeNavigacion().setTextLabelNivel1("Beneficiario");
    }

    private void mostrarVistaBuscar() {
        vista.showPanel(VistaBeneficiario.panelBuscar);
        vista.getBarraDeNavigacion().setTextLabelNivel1("Beneficiario");
        vista.getBarraDeNavigacion().setTextLabelNivel2("Buscar");
    }

    private void mostrarVistaNuevoBeneficiario() {
        if (benef != null) {
            vista.getPanelDatos().limpiarFormulario();
        }
        benef = null;
        vista.getPanelDatos().getTbIntervenciones().setModel(new DefaultTableModel(columnasIntervenciones, 0));
        vista.getPanelDatos().getTbFamiliares().setModel(new DefaultTableModel(columnasFamiliares,0));
        vista.showPanel(VistaBeneficiario.panelDatos);
        vista.getBarraDeNavigacion().setTextLabelNivel1("Beneficiario");
        vista.getBarraDeNavigacion().setTextLabelNivel2("Nuevo Beneficiario");
    }

    private void mostrarVistaModificarBeneficiario() {
        vista.showPanel(VistaBeneficiario.panelDatos);
        vista.getBarraDeNavigacion().setTextLabelNivel1("Beneficiario");
        vista.getBarraDeNavigacion().setTextLabelNivel2("Modificar Beneficiario");
    }

    private void actualizarTablaAyuda() {
        if (benef != null) {
            /*
             * Actualizar intervenciones
             */
            vista.getPanelDatos().getTbIntervenciones().setModel(new TableModel() {

                @Override
                public int getRowCount() {
                    return benef.getAyudasPrestadas().size();
                }

                @Override
                public int getColumnCount() {
                    return columnasIntervenciones.length;
                }

                @Override
                public String getColumnName(int columnIndex) {
                    return columnasIntervenciones[columnIndex];
                }

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return String.class;
                }

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }

                @Override
                public Object getValueAt(int rowIndex, int columnIndex) {
                    switch (columnIndex) {
                        case 0:
                            return formateador.format(benef.getAyudasPrestadas().get(rowIndex).getFecha());
                        case 1:
                            return benef.getAyudasPrestadas().get(rowIndex).getTipo_ayuda().getTitulo();
                        case 2:
                            return benef.getAyudasPrestadas().get(rowIndex).getImporte();
                        case 3:
                            return benef.getAyudasPrestadas().get(rowIndex).getObservaciones();
                        case 4:
                            return benef.getAyudasPrestadas().get(rowIndex).getVoluntarioQueOtorga().getApellidos() + ", " + benef.getAyudasPrestadas().get(rowIndex).getVoluntarioQueOtorga().getNombre();
                        default:
                            return "";
                    }

                }

                @Override
                public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void addTableModelListener(TableModelListener l) {
                }

                @Override
                public void removeTableModelListener(TableModelListener l) {
                }
            });
        }
    }
    
    private void actualizarCampoAyuda () {
        vista.getPanelDatos().getTextImporteBeneficiario().setText(Float.toString(ayudaSeleccionada.getImporte()));
        vista.getPanelDatos().getTextObservacionesIntervencionBeneficiario().setText(ayudaSeleccionada.getObservaciones());
    }
    
    private void actualizarTablaFamiliares(){
        vista.getPanelDatos().getTbFamiliares().setModel(new TableModel() {

            @Override
            public int getRowCount() {
                return benef.getFamilia().size();
            }

            @Override
            public int getColumnCount() {
               return columnasFamiliares.length;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return columnasFamiliares[columnIndex];
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
               return String.class;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch(columnIndex){
                    case 0:
                        return benef.getFamilia().get(rowIndex).getApellidos()+", "+benef.getFamilia().get(rowIndex).getNombre();
                    case 1:
                        return formateador.format(benef.getFamilia().get(rowIndex).getFechaDENacimiento());
                    case 2:
                        return benef.getFamilia().get(rowIndex).getParentesco();
                    case 3:
                        return benef.getFamilia().get(rowIndex).getNIF();
                    default:
                        return "";
                }
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void addTableModelListener(TableModelListener l) {
               
            }

            @Override
            public void removeTableModelListener(TableModelListener l) {
                
            }
        });
    }

    private boolean insertarBeneficiario(String[] datos) {
        if (this.comprobarDatos(datos) == false) {
            return false;
        }

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setNIF(datos[Beneficiario.NIF_ID]);
        beneficiario.setNombre(datos[Beneficiario.NOMBRE_ID]);
        beneficiario.setApellidos(datos[Beneficiario.APELLIDOS_ID]);
        try {
            beneficiario.setFechaDENacimiento(TestDatos.formatter.parse(datos[Beneficiario.FECHA_DE_NACIMIENTO_ID]));
        } catch (ParseException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        }

        beneficiario.setDomicilio(datos[Beneficiario.DOMICILIO_ID]);
        beneficiario.setCP(datos[Beneficiario.CP_ID]);
        beneficiario.setLocalidad(datos[Beneficiario.LOCALIDAD_ID]);
        beneficiario.setTelefonoMovil(datos[Beneficiario.TELEFONO_MOVIL_ID]);
        beneficiario.setTelefonoFijo(datos[Beneficiario.TELEFONO_FIJO_ID]);
        beneficiario.setEstadoCivil(datos[Beneficiario.ESTADO_CIVIL_ID]);

        beneficiario.setNacionalidad(datos[Beneficiario.NACIONALIDAD_ID]);
        beneficiario.setNivelDeEstudio(datos[Beneficiario.NIVELESTUDIOS_ID]);
        beneficiario.setObservaciones(datos[Beneficiario.OBSERVACIONES_ID]);
        beneficiario.setOcupacion(datos[Beneficiario.OCUPACION_ID]);
        beneficiario.setProfesion(datos[Beneficiario.PROFESION_ID]);
        beneficiario.setSituacionEconomica(datos[Beneficiario.SITUACION_ECONOMICA_ID]);
        beneficiario.setVivienda(datos[Beneficiario.VIVIENDA_ID]);
        Float alquiler = Float.parseFloat(datos[Beneficiario.VIVIENDA_ALQUILER_ID]);
        beneficiario.setViviendaAlquiler(alquiler);
        beneficiario.setViviendaObservaciones(datos[Beneficiario.VIVIENDA_OBSERVACIONES_ID]);

        try {
            BeneficiarioJDBC.getInstance().anadirBeneficiario(beneficiario);
        } catch (SQLException se) {
            ControladorErrores.mostrarError("Error al añadir beneficiario:\n" + se.getMessage());
            return false;
        }

        return true;
    }

    private ArrayList<Beneficiario> buscarBeneficiario(String dato, String tipoDato) {
        ArrayList<Beneficiario> benefs;
        try {
            benefs = BeneficiarioJDBC.getInstance().obtenerListadoBeneficiario(dato, tipoDato);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return benefs;
    }
    
    private ArrayList<Persona> consultarFamiliares(Long OIDBenef){
        ArrayList<Persona> famis;
        try {
            famis=BeneficiarioJDBC.getInstance().datosFamiliares(OIDBenef);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return famis;
    }

    private Beneficiario consultarBeneficiario(String dni) {
        Beneficiario beneficiario = null;
        
        try {
            beneficiario = BeneficiarioJDBC.getInstance().obtenerBeneficiario(dni);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return beneficiario;
    }

    private boolean comprobarDatos(String[] datos) {
        boolean validos = true;
        vista.getPanelDatos().setColorLabels(Color.black);

        if (!TestDatos.isNombre(datos[Beneficiario.NOMBRE_ID])) {
            vista.getPanelDatos().setColorLabelNombre(Color.red);
            validos = false;
        }
        if (!TestDatos.isNombre(datos[Beneficiario.APELLIDOS_ID])) {
            vista.getPanelDatos().setColorLabelApellidos(Color.red);
            validos = false;
        }
        if (!TestDatos.isDNI(datos[Beneficiario.NIF_ID])) {
            vista.getPanelDatos().setColorLabelNIF(Color.red);
            validos = false;
        }
        if (!TestDatos.isFecha(datos[Beneficiario.FECHA_DE_NACIMIENTO_ID])) {
            vista.getPanelDatos().setColorLabelFechaNacimiento(Color.red);
            validos = false;
        }

        if (!TestDatos.isNombre(datos[Beneficiario.NACIONALIDAD_ID])) {
            vista.getPanelDatos().setColorLabelNacionalidad(Color.red);
            validos = false;
        }
        if (!TestDatos.isNombre(datos[Beneficiario.PROFESION_ID])) {
            vista.getPanelDatos().setColorLabelProfesion(Color.red);
            validos = false;
        }
        if (!TestDatos.isNombre(datos[Beneficiario.OCUPACION_ID])) {
            vista.getPanelDatos().setColorLabelOcupacion(Color.red);
            validos = false;
        }
        if (!TestDatos.isTelefonoOFax(datos[Beneficiario.TELEFONO_FIJO_ID])) {
            vista.getPanelDatos().setColorLabelTelefonoFijo(Color.red);
            validos = false;
        }
        if (!TestDatos.isTelefonoOFax(datos[Beneficiario.TELEFONO_MOVIL_ID])) {
            vista.getPanelDatos().setColorLabelTelefonoMovil(Color.red);
            validos = false;
        }
        if (!TestDatos.isDomicilio(datos[Beneficiario.DOMICILIO_ID])) {
            vista.getPanelDatos().setColorLabelDomicilio(Color.red);
            validos = false;
        }
        if (!TestDatos.isNombre(datos[Beneficiario.LOCALIDAD_ID])) {
            vista.getPanelDatos().setColorLabelLocalidad(Color.red);
            validos = false;
        }
        if (!TestDatos.isCodigoPostal(datos[Beneficiario.CP_ID])) {
            vista.getPanelDatos().setColorLabelCP(Color.red);
            validos = false;
        }
        if (!TestDatos.isNombre(datos[Beneficiario.OBSERVACIONES_ID])) {
            vista.getPanelDatos().setColorLabelObservaciones(Color.red);
            validos = false;
        }
        if (!TestDatos.isNombre(datos[Beneficiario.SITUACION_ECONOMICA_ID])) {
            vista.getPanelDatos().setColorLabelSituacionEconomica(Color.red);
            validos = false;
        }
        if (!TestDatos.isOnlyDigit(datos[Beneficiario.VIVIENDA_ALQUILER_ID])) {
            vista.getPanelDatos().setColorLabelViviendaPrecio(Color.red);
            validos = false;
        }
        if (!TestDatos.isNombre(datos[Beneficiario.VIVIENDA_OBSERVACIONES_ID])) {
            vista.getPanelDatos().setColorLabelViviendaEspecificacion(Color.red);
            validos = false;
        }

        return validos;
    }

    //Listener de la barra de navigacion
    class ListenerBarraNavigacion implements ActionListener {

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

    // Listeners de los botones
    class btBuscarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarVistaBuscar();
        }
    }

    class btNuevoBeneficiarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            tiposAyuda = ControladorAyuda.getInstance(null).obtenerTiposAyuda();
            vista.getPanelDatos().actualizarTiposAyuda(tiposAyuda);
            mostrarVistaNuevoBeneficiario();
        }
    }

    class btBorrarBeneficiarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            vista.getPanelDatos().limpiarFormulario();
        }
    }

    class btGuardarBeneficiarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String[] datos = vista.getPanelDatos().getDatosPersonales();
            if (benef != null) {
                //TODO Modificar beneficiario
            } else {
                boolean exito = insertarBeneficiario(datos);

                if (exito) {
                    vista.getPanelDatos().setTextLabelError("Beneficiario añadido correctamente.");
                } else {
                    vista.getPanelDatos().setTextLabelError("El beneficiario no ha sido añadido.");
                }
            }
        }
    }

    class btBuscarBeneficiariosListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String dato = vista.getPanelBuscar().getTextBusquedaBeneficiarios();
            String tipoDato = vista.getPanelBuscar().getTipoDatoBusqueda();
            beneficiarios = buscarBeneficiario(dato, tipoDato);
            TableModel tabla = new TableModel() {

                @Override
                public int getRowCount() {
                    return beneficiarios.size();
                }

                @Override
                public int getColumnCount() {
                    return columnasBuscarBeneficiario.length;
                }

                @Override
                public String getColumnName(int columnIndex) {
                    return columnasBuscarBeneficiario[columnIndex];
                }

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return String.class;
                }

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }

                @Override
                public Object getValueAt(int rowIndex, int columnIndex) {
                    switch (columnIndex) {
                        case 0:
                            return beneficiarios.get(rowIndex).getNIF();
                        case 1:
                            return beneficiarios.get(rowIndex).getNombre() + " " + beneficiarios.get(rowIndex).getApellidos();
                        case 2:
                            return formateador.format(beneficiarios.get(rowIndex).getFechaDENacimiento());
                        case 3:
                            return beneficiarios.get(rowIndex).getLocalidad();
                        case 4:
                            return beneficiarios.get(rowIndex).getTelefonoMovil();
                        default:
                            return "";
                    }
                }

                @Override
                public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void addTableModelListener(TableModelListener l) {
                }

                @Override
                public void removeTableModelListener(TableModelListener l) {
                }
            };

            vista.getPanelBuscar().getTablaBusquedaBeneficiario().setModel(tabla);

        }
    }

    class btVerBeneficiarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (vista.getPanelBuscar().getTablaBusquedaBeneficiario().getSelectedRow() != -1) {
                benef = beneficiarios.get(vista.getPanelBuscar().getTablaBusquedaBeneficiario().getSelectedRow());
                tiposAyuda = ControladorAyuda.getInstance(null).obtenerTiposAyuda();
                benef.setFamilia(consultarFamiliares(benef.getOID()));
                vista.getPanelDatos().actualizarTiposAyuda(tiposAyuda);
                vista.getPanelDatos().actualizarDatosGenerales(benef);
                actualizarTablaAyuda();
                actualizarTablaFamiliares();
                mostrarVistaModificarBeneficiario();
            }
        }
    }

    class BtGuardarIntervencionesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean datosCorrectos = true;
            
            if (vista.getPanelDatos().getCbTiposAyuda().getSelectedItem() == null) {
                datosCorrectos = false;
            }
            
            try {
                Integer.parseInt(vista.getPanelDatos().getTextImporteBeneficiario().getText());
            } catch (Exception e) {
                vista.getPanelDatos().setTextLabelErrorAyuda("El importe debe ser un numero.");
                datosCorrectos = false;
            }
            
            if (vista.getPanelDatos().getTextImporteBeneficiario().getText().equals("")) {
                datosCorrectos = false;
            }
            
            if (vista.getPanelDatos().getTextObservacionesIntervencionBeneficiario().getText().equals("")) {
                datosCorrectos = false;
            }
            
            if (!datosCorrectos) {
                vista.getPanelDatos().setTextLabelErrorAyuda("Error : la ayuda no ha sido anadido.");
            } else {
                Ayuda ayuda = new Ayuda();
                ayuda.setBeneficiarioDeAyuda(benef);
                ayuda.setFecha(new Date());
                
                ayuda.setImporte(Integer.parseInt(vista.getPanelDatos().getTextImporteBeneficiario().getText()));
                ayuda.setObservaciones(vista.getPanelDatos().getTextObservacionesIntervencionBeneficiario().getText());
                ayuda.setTipo_ayuda((TipoAyuda)vista.getPanelDatos().getCbTiposAyuda().getSelectedItem());
                ayuda.setVoluntarioQueOtorga(ControladorPrincipal.getInstance().getVoluntario());
                
                boolean exito = ControladorAyuda.getInstance(null).registrarAyuda(ayuda);
                
                if (exito) {
                    benef = consultarBeneficiario(benef.getNIF());
                    actualizarTablaAyuda();
                    vista.getPanelDatos().limpiarCamposAyuda();
                } else {
                    vista.getPanelDatos().setTextLabelErrorAyuda("Error : la ayuda no ha sido anadido.");
                }
            }
        }
    }
    
    class BtEliminarIntervencionesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            // TODO
        }   
    }
    
    class TablaAyudaListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            if (vista.getPanelDatos().getTbIntervenciones().getSelectedRow() != -1) {
                ayudaSeleccionada = benef.getAyudasPrestadas().get(vista.getPanelDatos().getTbIntervenciones().getSelectedRow());
                actualizarCampoAyuda();
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }
        
    }
}
