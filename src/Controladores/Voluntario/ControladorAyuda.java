
package Controladores.Voluntario;

import Controladores.ControladorErrores;
import Controladores.TestDatos;
import JDBC.AyudaJDBC;
import Modelo.Ayuda;
import Modelo.TipoAyuda;
import Vistas.Paneles.Voluntario.PanelVoluntarioAyudas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 ** NOMBRE CLASE:
 **	  ControladorAyuda
 **
 ** DESCRIPCION:
 **
 **
 **
 ** DESARROLLADO POR:
 *          Raphael Colleau (RC)
 **
 **
 ** SUPERVISADO POR:
 **
 **
 ** HISTORIA:
 ** 	000 - 16 mai 2012 - RC - Creacion
 ** 	001 - 23 mai 2012 - RC - Adicion metodos relacionados con modelo
 **
 ** NOTAS:
 **
 **
 */
public class ControladorAyuda {

    /**
     * PATRON DE DISEÑO SINGLETON
     */
    private static ControladorAyuda instancia = null;

    public static ControladorAyuda getInstance(PanelVoluntarioAyudas pvista) {

        if (instancia == null) {
            instancia = new ControladorAyuda(pvista);
        }
        return instancia;
    }

    private PanelVoluntarioAyudas vista;
    // list de tipo de ayuda para la tabla
    private ArrayList<TipoAyuda> tiposAyuda;
    // tipo de ayuda seleccionado en el comboBox
    private TipoAyuda tipoAyudaSeleccionado;
    
    private ArrayList<Ayuda> ayudas;

    private String[] columnNamesTipoAyuda = {"Titulo", "Descripcion", "Monetaria"};
    private String[] columnNamesAyuda = {"Concepto", "DNI"};

    public ControladorAyuda(PanelVoluntarioAyudas vista) {
        this.vista = vista;

        this.vista.getBtnGuardarTipoAyuda().addActionListener(new BtGuardarTipoAyudaListener());
        this.vista.getBtnEliminarTipoAyuda().addActionListener(new BtBorrarTipoAyudaListener());
        this.vista.getBtnBuscarAyudas().addActionListener(new BtBuscarAyudaListener());
    }

    public void actualizarTipoAyuda() {
        try {
            tiposAyuda = AyudaJDBC.getInstance().obtenerDatosTipoAyuda();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
        }

        TableModel tableModel = new TableModel() {

            @Override
            public int getRowCount() {
                return tiposAyuda.size();
            }

            @Override
            public int getColumnCount() {
                return columnNamesTipoAyuda.length;
            }

            @Override
            public String getColumnName(int i) {
                return columnNamesTipoAyuda[i];
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
                        return tiposAyuda.get(row).getTitulo();
                    case 1:
                        return tiposAyuda.get(row).getDescripcion();
                    case 2:
                        if (tiposAyuda.get(row).isMonetaria()) {
                            return "Si";
                        } else {
                            return "No";
                        }
                }
                return "";
            }

            @Override
            public void setValueAt(Object o, int row, int col) {
            }

            @Override
            public void addTableModelListener(TableModelListener tl) {
            }

            @Override
            public void removeTableModelListener(TableModelListener tl) {
            }
        };

        vista.getTablaTiposAyuda().setModel(tableModel);

        ComboBoxModel cbModel = new ComboBoxModel() {

            @Override
            public void setSelectedItem(Object o) {
                tipoAyudaSeleccionado = (TipoAyuda)o;
            }

            @Override
            public Object getSelectedItem() {
                return tipoAyudaSeleccionado;
            }

            @Override
            public int getSize() {
                return tiposAyuda.size();
            }

            @Override
            public Object getElementAt(int i) {
                return tiposAyuda.get(i);
            }

            @Override
            public void addListDataListener(ListDataListener ll) {
            }

            @Override
            public void removeListDataListener(ListDataListener ll) {
            }
        };
        vista.getCbTipoAyuda().setModel(cbModel);
    }
    
    private void actualizarTablaAyudas () {
        TableModel model = new TableModel() {

            @Override
            public int getRowCount() {
                return ayudas.size();
            }

            @Override
            public int getColumnCount() {
                return columnNamesAyuda.length;
            }

            @Override
            public String getColumnName(int i) {
                return columnNamesAyuda[i];
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
                        return ayudas.get(row).getImporte();
                    case 1:
                        return ayudas.get(row).getBeneficiarioDeAyuda().getNIF();
                }
                return "";
            }

            @Override
            public void setValueAt(Object o, int i, int i1) {
            }

            @Override
            public void addTableModelListener(TableModelListener tl) {
            }

            @Override
            public void removeTableModelListener(TableModelListener tl) {
            }
        };
        vista.getTablaAyudas().setModel(model);
    }

    // TODO Metodos JDBC
    public boolean registrarAyuda(Ayuda ayuda) {
        boolean exito;

            // TODO test ayuda.getBeneficiarioDeAyuda().getNIF() exite
            // TODO test datos
        try {
            exito = AyudaJDBC.getInstance().registrarDatosAyuda(ayuda, ayuda.getBeneficiarioDeAyuda(), ayuda.getVoluntarioQueOtorga());
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        if (ayuda.getTipo_ayuda().isMonetaria()) {
            exito = ControladorContabilidad.getInstance(null).registrarGastoAyuda(ayuda.getImporte(), ayuda.getObservaciones(), ayuda);
        }

        return exito;
    }

    private boolean datosNuevoTipoAyuda (TipoAyuda tipoAyuda) {
        boolean exito = false;

        boolean tipoAyudaExiste = true;
        try {
            tipoAyudaExiste = AyudaJDBC.getInstance().comprobarTipoAyuda(tipoAyuda.getTitulo());
        } catch (SQLException ex) {
            // TODO pop up error
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!tipoAyudaExiste) {
            try {
                exito = AyudaJDBC.getInstance().insertarTipoAyuda(tipoAyuda);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ControladorErrores.mostrarError("El tipo de ayuda ya existe en el sistema");
        }

        return exito;
    }

    public ArrayList<TipoAyuda> obtenerTiposAyuda () {
        ArrayList<TipoAyuda> temp_tiposAyuda;
        try {
            temp_tiposAyuda = AyudaJDBC.getInstance().obtenerDatosTipoAyuda();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return temp_tiposAyuda;
    }

    public boolean modificarAyuda (Ayuda ayuda) {
        boolean exito = false;

        try {
            exito = AyudaJDBC.getInstance().modificarDatosAyuda(ayuda, ayuda.getVoluntarioQueOtorga());
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exito;
    }
    
    public ArrayList<Ayuda> buscarAyudas (Date fechaIni, Date fechaFin, float importeMin, float importeMax, String tipoAyuda) {
        ArrayList<Ayuda> t_ayudas = new ArrayList<Ayuda>();
        
        try {
            t_ayudas = AyudaJDBC.getInstance().buscarAyudas(fechaIni, fechaFin, importeMin, importeMax, tipoAyuda);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return t_ayudas;
    }

    private boolean modificarTypoAyuda (TipoAyuda tipoAyuda) {
        boolean exito = false;

        try {
            exito = AyudaJDBC.getInstance().modificarDatosTipoAyuda(tipoAyuda);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exito;
    }

    public boolean eliminarAyuda(Ayuda ayuda) {
        boolean exito = false;

        try {
            exito = AyudaJDBC.getInstance().eliminarDatosAyuda(ayuda.getOID());
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exito;
    }

    private boolean eliminarTipoAyuda(TipoAyuda tipoAyuda) {
        boolean exito = false;
        try {
            exito = AyudaJDBC.getInstance().eliminarDatosTipoAyuda(tipoAyuda.getOID());
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exito;
    }
    // TODO Listeners de los botones

    class BtGuardarTipoAyudaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean datosCorrectos = true;
            vista.getLabelErrorTipoAyuda().setVisible(false);

            if (vista.getTextTipoAyuda().getText().equals("")) {
                datosCorrectos = false;
            }

            if (vista.getDescripcionTipoAyuda().getText().equals("")) {
                datosCorrectos = false;
            }

            if (datosCorrectos) {
                TipoAyuda tipoAyuda = new TipoAyuda();
                tipoAyuda.setTitulo(vista.getTextTipoAyuda().getText());
                tipoAyuda.setDescripcion(vista.getDescripcionTipoAyuda().getText());
                tipoAyuda.setMonetaria(vista.getIsMonetaria().isSelected());

                if (datosNuevoTipoAyuda(tipoAyuda)) {
                    vista.getTextTipoAyuda().setText("");
                    vista.getDescripcionTipoAyuda().setText("");
                    vista.getIsMonetaria().setSelected(false);
                    actualizarTipoAyuda();
                }
            } else {
                vista.setTextErrorTipoAyuda("Error : El tipo de ayuda no ha sido anadido.");
            }
        }
    }

    class BtBorrarTipoAyudaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            vista.getLabelErrorTipoAyuda().setVisible(false);
            if (vista.getTablaTiposAyuda().getSelectedRow() == -1) {
                vista.setTextErrorTipoAyuda("Selecciona un tipo de ayuda en la tabla.");
            } else {
                if (JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar el tipo de ayuda?", "Eliminar tipo de ayuda", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    TipoAyuda tipoAyuda = tiposAyuda.get(vista.getTablaTiposAyuda().getSelectedRow());
                    if (eliminarTipoAyuda(tipoAyuda)) {
                        actualizarTipoAyuda();
                    } else {
                        vista.setTextErrorTipoAyuda("Error : El tipo de ayuda no ha sido eliminado.");
                    }
                }
            }
        }

    }

    class BtBuscarAyudaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean datosCorrectos = true;
            vista.getLabelErrorAyuda().setVisible(false);
            
            Date fechaInicio = null;
            Date fechaFin = null;
            float importeMin = 0;
            float importeMax = 0;
            try {
                fechaInicio = TestDatos.formatter.parse(vista.getFechaInicioAyudas().getText());
                fechaFin = TestDatos.formatter.parse(vista.getFechaFinAyudas().getText());
            } catch (Exception e) {
                vista.setTextErrorAyuda("Fecha dd/mm/aaaa");
                datosCorrectos = false;
            }
            
            try {
                importeMin = Float.parseFloat(vista.getMinimoImporte().getText());
                importeMax = Float.parseFloat(vista.getMaximoImporte().getText());
            } catch (Exception e) {
                vista.setTextErrorAyuda("Importe debe ser numero"); 
                datosCorrectos = false;
            }
            
            if (vista.getCbTipoAyuda().getSelectedIndex() == -1) {
                vista.setTextErrorAyuda("Selecionna un tipo de ayuda");
                datosCorrectos = false;
            }
            
            if (datosCorrectos) {
                ayudas = buscarAyudas(fechaInicio, fechaFin, importeMin, importeMax, vista.getCbTipoAyuda().getSelectedItem().toString());
                actualizarTablaAyudas();
            }
        }

    }
}
