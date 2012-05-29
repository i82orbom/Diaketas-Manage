/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Paneles.Beneficiario;

import Modelo.Beneficiario;
import Modelo.TipoAyuda;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author psylock
 *
 * HISTORIAL:
 *      000 - 23 May 2012 - JAEG - Añadida funcion para insertar los datos generales. Eliminado "lugar de nacimiento" (dato no existente en el modelo)
 *      001 - 29 May 2012 - RC - Adicion getter y labelErrorAyuda
 */
public class PanelBeneficiarioDatos extends javax.swing.JPanel {

    private SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Creates new form PanelBeneficiarioDatos
     */
    public PanelBeneficiarioDatos() {
        initComponents();
        labelError.setVisible(false);
        labelErrorAyuda.setVisible(false);
    }

    public void setTextLabelError(String text) {
        labelError.setText(text);
        labelError.setVisible(true);
    }
    
    public void setTextLabelErrorAyuda(String text) {
        labelErrorAyuda.setText(text);
        labelErrorAyuda.setVisible(true);
    }

    public void setColorLabelNombre(Color cf) {
        labelNombre.setForeground(cf);
    }

    public void setColorLabelApellidos(Color cf) {
        labelApellidos.setForeground(cf);
    }

    public void setColorLabelNIF(Color cf) {
        labelNIF.setForeground(cf);
    }

    public void setColorLabelFechaNacimiento(Color cf) {
        labelFechaNacimiento.setForeground(cf);
    }

    public void setColorLabelNacionalidad(Color cf) {
        labelNacionalidad.setForeground(cf);
    }

    public void setColorLabelProfesion(Color cf) {
        labelProfesion.setForeground(cf);
    }

    public void setColorLabelOcupacion(Color cf) {
        labelOcupacion.setForeground(cf);
    }

    public void setColorLabelTelefonoFijo(Color cf) {
        labelTelefonoFijo.setForeground(cf);
    }

    public void setColorLabelTelefonoMovil(Color cf) {
        labelTelefonoMovil.setForeground(cf);
    }

    public void setColorLabelDomicilio(Color cf) {
        labelDomicilio.setForeground(cf);
    }

    public void setColorLabelLocalidad(Color cf) {
        labelLocalidad.setForeground(cf);
    }

    public void setColorLabelCP(Color cf) {
        labelCP.setForeground(cf);
    }

    public void setColorLabelObservaciones(Color cf) {
        labelObservaciones.setForeground(cf);
    }

    public void setColorLabelSituacionEconomica(Color cf) {
        labelSituacionEconomica.setForeground(cf);
    }

    public void setColorLabelViviendaPrecio(Color cf) {
        labelViviendaPrecio.setForeground(cf);
    }

    public void setColorLabelViviendaEspecificacion(Color cf) {
        labelViviendaEspecificacion.setForeground(cf);
    }

    public void setColorLabels(Color cf) {
        labelNombre.setForeground(cf);
        labelNIF.setForeground(cf);
        labelApellidos.setForeground(cf);
        labelFechaNacimiento.setForeground(cf);
        labelNacionalidad.setForeground(cf);
        labelProfesion.setForeground(cf);
        labelOcupacion.setForeground(cf);
        labelTelefonoFijo.setForeground(cf);
        labelTelefonoMovil.setForeground(cf);
        labelDomicilio.setForeground(cf);
        labelLocalidad.setForeground(cf);
        labelCP.setForeground(cf);
        labelObservaciones.setForeground(cf);
        labelSituacionEconomica.setForeground(cf);
        labelViviendaPrecio.setForeground(cf);
        labelViviendaEspecificacion.setForeground(cf);

    }

    public void limpiarFormulario() {
        // Limpio los campos
        labelError.setVisible(false);
        textNombre.setText("");
        textApellidos.setText("");
        textNIF.setText("");
        textFechaNacimiento.setText("");
        textNacionalidad.setText("");
        cbEstadoCivil.setSelectedIndex(0);
        cbNivelEstudios.setSelectedIndex(0);
        textProfesion.setText("");
        textOcupacion.setText("");
        textTelefonoFijo.setText("");
        textTelefonoMovil.setText("");
        textDomicilio.setText("");
        textLocalidad.setText("");
        textCP.setText("");
        textObservaciones.setText("");
        textSituacionEconomica.setText("");

        cbTipoVivienda.setSelectedIndex(0);
        textPrecioVivienda.setText("");
        textEspecificarTipoVivienda.setText("");

        setColorLabels(Color.black);
    }
    
    public void limpiarCamposAyuda () {
        labelErrorAyuda.setVisible(false);
        textImporteBeneficiario.setText("");
        textObservacionesIntervencionBeneficiario.setText("");
        cbTiposAyuda.setSelectedIndex(0);
    }

    public JTable getTbIntervenciones() {
        return tbIntervenciones;
    }

    //Rellenar datos generales a partir de un Beneficiario
    public void actualizarDatosGenerales(Beneficiario b) {
        textNombre.setText(b.getNombre());
        textApellidos.setText(b.getApellidos());
        textNIF.setText(b.getNIF());
        textFechaNacimiento.setText(formateadorFecha.format(b.getFechaDENacimiento()));
        textNacionalidad.setText(b.getNacionalidad());
        textProfesion.setText(b.getProfesion());
        textOcupacion.setText(b.getOcupacion());
        textTelefonoFijo.setText(b.getTelefonoFijo());
        textTelefonoMovil.setText(b.getTelefonoMovil());
        textDomicilio.setText(b.getDomicilio());
        textLocalidad.setText(b.getLocalidad());
        textCP.setText(b.getCP());
        textObservaciones.setText(b.getObservaciones());
        textSituacionEconomica.setText(b.getSituacionEconomica());
        textPrecioVivienda.setText(Float.toString(b.getViviendaAlquiler()));
        textEspecificarTipoVivienda.setText(b.getViviendaObservaciones());
        cbEstadoCivil.setSelectedItem(b.getEstadoCivil());
        cbNivelEstudios.setSelectedItem(b.getNivelDeEstudio());
        cbTipoVivienda.setSelectedItem(b.getVivienda());
    }

    public void actualizarTiposAyuda(ArrayList<TipoAyuda> tiposAyuda) {
        cbTiposAyuda.removeAllItems();
        for (TipoAyuda ta : tiposAyuda) {
            cbTiposAyuda.addItem(ta);
        }
    }

    // getters de los campos
    public JButton getBtBorrar() {
        return btBorrar;
    }

    public JButton getBtGuardar() {
        return btGuardar;
    }

    public JComboBox getCbTiposAyuda() {
        return cbTiposAyuda;
    }

    public JTextField getTextImporteBeneficiario() {
        return textImporteBeneficiario;
    }

    public JTextField getTextObservacionesIntervencionBeneficiario() {
        return textObservacionesIntervencionBeneficiario;
    }

    public JButton getBtGuardarIntervencionBeneficiario() {
        return btGuardarIntervencionBeneficiario;
    }

    public JButton getBtEliminarIntervencionBeneficiario() {
        return btEliminarIntervencionBeneficiario;
    }

    public String[] getDatosPersonales() {
        String[] datos = new String[24];

        datos[Beneficiario.NOMBRE_ID] = textNombre.getText();
        datos[Beneficiario.APELLIDOS_ID] = textApellidos.getText();
        datos[Beneficiario.NIF_ID] = textNIF.getText();
        datos[Beneficiario.FECHA_DE_NACIMIENTO_ID] = textFechaNacimiento.getText();
        datos[Beneficiario.NACIONALIDAD_ID] = textNacionalidad.getText();
        datos[Beneficiario.ESTADO_CIVIL_ID] = cbEstadoCivil.getSelectedItem().toString();
        datos[Beneficiario.NIVELESTUDIOS_ID] = cbNivelEstudios.getSelectedItem().toString();
        datos[Beneficiario.PROFESION_ID] = textProfesion.getText();
        datos[Beneficiario.OCUPACION_ID] = textOcupacion.getText();
        datos[Beneficiario.TELEFONO_FIJO_ID] = textTelefonoFijo.getText();
        datos[Beneficiario.TELEFONO_MOVIL_ID] = textTelefonoMovil.getText();
        datos[Beneficiario.DOMICILIO_ID] = textDomicilio.getText();
        datos[Beneficiario.LOCALIDAD_ID] = textLocalidad.getText();
        datos[Beneficiario.CP_ID] = textCP.getText();
        datos[Beneficiario.OBSERVACIONES_ID] = textObservaciones.getText();
        datos[Beneficiario.SITUACION_ECONOMICA_ID] = textSituacionEconomica.getText();
        datos[Beneficiario.VIVIENDA_ID] = cbTipoVivienda.getSelectedItem().toString();
        datos[Beneficiario.VIVIENDA_ALQUILER_ID] = textPrecioVivienda.getText();
        datos[Beneficiario.VIVIENDA_OBSERVACIONES_ID] = textEspecificarTipoVivienda.getText();

        return datos;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelDatos = new javax.swing.JPanel();
        textTelefonoFijo = new javax.swing.JTextField();
        textCP = new javax.swing.JTextField();
        textApellidos = new javax.swing.JTextField();
        labelTelefonoFijo = new javax.swing.JLabel();
        cbEstadoCivil = new javax.swing.JComboBox();
        textProfesion = new javax.swing.JTextField();
        labelNombre = new javax.swing.JLabel();
        labelNacionalidad = new javax.swing.JLabel();
        labelNIF = new javax.swing.JLabel();
        labelNivelEstudios = new javax.swing.JLabel();
        cbNivelEstudios = new javax.swing.JComboBox();
        textNacionalidad = new javax.swing.JTextField();
        textNombre = new javax.swing.JTextField();
        textNIF = new javax.swing.JTextField();
        labelObservaciones = new javax.swing.JLabel();
        labelDomicilio = new javax.swing.JLabel();
        labelLocalidad = new javax.swing.JLabel();
        labelProfesion = new javax.swing.JLabel();
        labelCP = new javax.swing.JLabel();
        labelFechaNacimiento = new javax.swing.JLabel();
        labelEstadoCivil = new javax.swing.JLabel();
        textDomicilio = new javax.swing.JTextField();
        textLocalidad = new javax.swing.JTextField();
        textObservaciones = new javax.swing.JTextField();
        labelApellidos = new javax.swing.JLabel();
        labelDatosPersonales = new javax.swing.JLabel();
        labelVivienda = new javax.swing.JLabel();
        labelTipoVivienda = new javax.swing.JLabel();
        labelViviendaEspecificacion = new javax.swing.JLabel();
        labelViviendaPrecio = new javax.swing.JLabel();
        cbTipoVivienda = new javax.swing.JComboBox();
        textPrecioVivienda = new javax.swing.JTextField();
        textEspecificarTipoVivienda = new javax.swing.JTextField();
        labelSituacionEconomica = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        textSituacionEconomica = new javax.swing.JTextArea();
        btGuardar = new javax.swing.JButton();
        btBorrar = new javax.swing.JButton();
        labelOcupacion = new javax.swing.JLabel();
        textOcupacion = new javax.swing.JTextField();
        textTelefonoMovil = new javax.swing.JTextField();
        labelTelefonoMovil = new javax.swing.JLabel();
        labelError = new javax.swing.JLabel();
        textFechaNacimiento = new javax.swing.JFormattedTextField();
        PanelIntervenciones = new javax.swing.JPanel();
        btGuardarIntervencionBeneficiario = new javax.swing.JButton();
        btEliminarIntervencionBeneficiario = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbIntervenciones = new javax.swing.JTable();
        jLabel60 = new javax.swing.JLabel();
        textObservacionesIntervencionBeneficiario = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        textImporteBeneficiario = new javax.swing.JTextField();
        cbTiposAyuda = new javax.swing.JComboBox();
        labelErrorAyuda = new javax.swing.JLabel();
        PanelSituacionFamiliar = new javax.swing.JPanel();
        cbParentescoBeneficiario = new javax.swing.JComboBox();
        jLabel57 = new javax.swing.JLabel();
        btEliminarFamiliarBeneficiario = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        listDatosFamiliarBeneficiario = new javax.swing.JTable();
        btGuardarFamiliarBeneficiario = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        textNIFFamiliarBeneficiario = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setPreferredSize(new java.awt.Dimension(1000, 600));

        PanelDatos.setBackground(new java.awt.Color(255, 255, 255));

        labelTelefonoFijo.setText("Teléfono Fijo");

        cbEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Soltero", "Casado", "Viudo" }));
        cbEstadoCivil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoCivilActionPerformed(evt);
            }
        });

        labelNombre.setText("Nombre");

        labelNacionalidad.setText("Nacionalidad");

        labelNIF.setText("NIF/DNI/Pasaporte");

        labelNivelEstudios.setText("Nivel de estudios");

        cbNivelEstudios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Graduado Escolar", "Graduado Secundario", "Graduado Medio", "Graduado Superior", "Estudios Universitarios" }));

        labelObservaciones.setText("Observaciones");

        labelDomicilio.setText("Domicilio");

        labelLocalidad.setText("Localidad");

        labelProfesion.setText("Profesion");

        labelCP.setText("Código Postal");

        labelFechaNacimiento.setText("Nacido el");

        labelEstadoCivil.setText("Estado civil");

        labelApellidos.setText("Apellidos");

        labelDatosPersonales.setForeground(new java.awt.Color(255, 0, 0));
        labelDatosPersonales.setText("Datos personales");

        labelVivienda.setForeground(new java.awt.Color(255, 0, 0));
        labelVivienda.setText("Vivienda");

        labelTipoVivienda.setText("Tipo");

        labelViviendaEspecificacion.setText("Especificar");

        labelViviendaPrecio.setText("Precio");

        cbTipoVivienda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alquilada", "Cedida", "Otro" }));

        labelSituacionEconomica.setText("Descripción de la situación económica");

        textSituacionEconomica.setColumns(20);
        textSituacionEconomica.setRows(5);
        jScrollPane7.setViewportView(textSituacionEconomica);

        btGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/save_f2.png"))); // NOI18N

        btBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/limpiar.png"))); // NOI18N

        labelOcupacion.setText("Ocupación");

        textOcupacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textOcupacionActionPerformed(evt);
            }
        });

        textTelefonoMovil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTelefonoMovilActionPerformed(evt);
            }
        });

        labelTelefonoMovil.setText("Teléfono Móvil");

        labelError.setForeground(new java.awt.Color(255, 51, 51));
        labelError.setText("Mensaje de error");

        textFechaNacimiento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        org.jdesktop.layout.GroupLayout PanelDatosLayout = new org.jdesktop.layout.GroupLayout(PanelDatos);
        PanelDatos.setLayout(PanelDatosLayout);
        PanelDatosLayout.setHorizontalGroup(
            PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PanelDatosLayout.createSequentialGroup()
                .add(14, 14, 14)
                .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(PanelDatosLayout.createSequentialGroup()
                        .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, labelNacionalidad)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, labelNIF)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, labelNombre)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, labelNivelEstudios)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, labelTelefonoFijo)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, labelDomicilio)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, labelLocalidad)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, labelObservaciones)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, labelTipoVivienda)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, labelViviendaEspecificacion))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(PanelDatosLayout.createSequentialGroup()
                                .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(PanelDatosLayout.createSequentialGroup()
                                        .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                            .add(cbNivelEstudios, 0, 137, Short.MAX_VALUE)
                                            .add(textNacionalidad)
                                            .add(textNombre)
                                            .add(textNIF)
                                            .add(textTelefonoFijo))
                                        .add(28, 28, 28)
                                        .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(labelEstadoCivil)
                                            .add(labelFechaNacimiento)
                                            .add(labelApellidos)
                                            .add(labelProfesion)
                                            .add(labelOcupacion)
                                            .add(labelTelefonoMovil))
                                        .add(18, 18, 18)
                                        .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(PanelDatosLayout.createSequentialGroup()
                                                .add(cbEstadoCivil, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(labelSituacionEconomica, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 244, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                            .add(PanelDatosLayout.createSequentialGroup()
                                                .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, textFechaNacimiento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                    .add(textApellidos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 307, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                                .add(0, 0, Short.MAX_VALUE))
                                            .add(PanelDatosLayout.createSequentialGroup()
                                                .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                    .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                                        .add(textOcupacion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                                        .add(textTelefonoMovil))
                                                    .add(textProfesion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(jScrollPane7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                                    .add(PanelDatosLayout.createSequentialGroup()
                                        .add(textLocalidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 138, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(29, 29, 29)
                                        .add(labelCP)
                                        .add(21, 21, 21)
                                        .add(textCP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, textObservaciones, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, textDomicilio)))
                                .addContainerGap(76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(PanelDatosLayout.createSequentialGroup()
                                .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(PanelDatosLayout.createSequentialGroup()
                                        .add(cbTipoVivienda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(37, 37, 37)
                                        .add(labelViviendaPrecio)
                                        .add(18, 18, 18)
                                        .add(textPrecioVivienda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 95, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(textEspecificarTipoVivienda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 400, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(139, 139, 139)
                                .add(btGuardar)
                                .add(51, 51, 51)
                                .add(btBorrar)
                                .addContainerGap(141, Short.MAX_VALUE))))
                    .add(PanelDatosLayout.createSequentialGroup()
                        .add(labelDatosPersonales)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(PanelDatosLayout.createSequentialGroup()
                        .add(labelVivienda)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(labelError, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 351, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(21, 21, 21))))
        );
        PanelDatosLayout.setVerticalGroup(
            PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PanelDatosLayout.createSequentialGroup()
                .addContainerGap()
                .add(labelDatosPersonales)
                .add(18, 18, 18)
                .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(PanelDatosLayout.createSequentialGroup()
                        .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(labelNombre)
                            .add(textNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(6, 6, 6)
                        .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(textNIF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(labelNIF)
                            .add(labelFechaNacimiento)
                            .add(textFechaNacimiento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(labelNacionalidad)
                            .add(textNacionalidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(labelEstadoCivil)
                            .add(cbEstadoCivil, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(labelSituacionEconomica)))
                    .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(labelApellidos)
                        .add(textApellidos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(PanelDatosLayout.createSequentialGroup()
                        .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(labelNivelEstudios)
                            .add(cbNivelEstudios, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(labelProfesion)
                            .add(textProfesion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(PanelDatosLayout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(labelOcupacion)
                                    .add(textOcupacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(labelTelefonoFijo)
                                    .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(textTelefonoMovil, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(labelTelefonoMovil))))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, PanelDatosLayout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(textTelefonoFijo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(textDomicilio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(labelDomicilio))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(labelLocalidad)
                            .add(textLocalidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(labelCP)
                            .add(textCP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(textObservaciones, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(labelObservaciones))
                        .add(33, 33, 33)
                        .add(labelVivienda)
                        .add(2, 2, 2)
                        .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(labelTipoVivienda)
                            .add(cbTipoVivienda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(labelViviendaPrecio)
                            .add(textPrecioVivienda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(labelViviendaEspecificacion)
                            .add(textEspecificarTipoVivienda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(PanelDatosLayout.createSequentialGroup()
                        .add(jScrollPane7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(146, 146, 146)
                        .add(labelError)
                        .add(18, 18, 18)
                        .add(PanelDatosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(btGuardar)
                            .add(btBorrar))))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos Generales", PanelDatos);

        PanelIntervenciones.setBackground(new java.awt.Color(255, 255, 255));

        btGuardarIntervencionBeneficiario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/save_f2.png"))); // NOI18N
        btGuardarIntervencionBeneficiario.setToolTipText("Guardar");

        btEliminarIntervencionBeneficiario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/borrar.png"))); // NOI18N
        btEliminarIntervencionBeneficiario.setToolTipText("Eliminar ayuda");

        jLabel59.setForeground(new java.awt.Color(255, 0, 0));
        jLabel59.setText("Intervenciones realizadas");

        tbIntervenciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Concepto", "Importe", "Observaciones", "Voluntario"
            }
        ));
        jScrollPane10.setViewportView(tbIntervenciones);

        jLabel60.setText("Importe");

        jLabel62.setText("Observaciones");

        jLabel61.setText("Concepto");

        labelErrorAyuda.setForeground(new java.awt.Color(255, 0, 51));
        labelErrorAyuda.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelErrorAyuda.setText("error");

        org.jdesktop.layout.GroupLayout PanelIntervencionesLayout = new org.jdesktop.layout.GroupLayout(PanelIntervenciones);
        PanelIntervenciones.setLayout(PanelIntervencionesLayout);
        PanelIntervencionesLayout.setHorizontalGroup(
            PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PanelIntervencionesLayout.createSequentialGroup()
                .add(27, 27, 27)
                .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane10)
                    .add(PanelIntervencionesLayout.createSequentialGroup()
                        .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel59)
                            .add(PanelIntervencionesLayout.createSequentialGroup()
                                .add(260, 260, 260)
                                .add(labelErrorAyuda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 414, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(57, 57, 57)
                                .add(btGuardarIntervencionBeneficiario)
                                .add(38, 38, 38)
                                .add(btEliminarIntervencionBeneficiario))
                            .add(PanelIntervencionesLayout.createSequentialGroup()
                                .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                        .add(PanelIntervencionesLayout.createSequentialGroup()
                                            .add(jLabel61)
                                            .add(32, 32, 32))
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel60))
                                    .add(jLabel62))
                                .add(18, 18, 18)
                                .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(textObservacionesIntervencionBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 769, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(textImporteBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(cbTiposAyuda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                        .add(0, 54, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelIntervencionesLayout.setVerticalGroup(
            PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, PanelIntervencionesLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel59)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel60)
                    .add(textImporteBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel61)
                    .add(cbTiposAyuda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(12, 12, 12)
                .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel62)
                    .add(textObservacionesIntervencionBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(PanelIntervencionesLayout.createSequentialGroup()
                        .add(20, 20, 20)
                        .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, btGuardarIntervencionBeneficiario)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, btEliminarIntervencionBeneficiario))
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, PanelIntervencionesLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(labelErrorAyuda)
                        .add(23, 23, 23))))
        );

        jTabbedPane1.addTab("Intervenciones", PanelIntervenciones);

        PanelSituacionFamiliar.setBackground(new java.awt.Color(255, 255, 255));

        cbParentescoBeneficiario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Consanguíneo", "Cercano", "Lejano", "Tutor" }));

        jLabel57.setText("Parentesco");

        btEliminarFamiliarBeneficiario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/limpiar.png"))); // NOI18N

        listDatosFamiliarBeneficiario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre y Apellidos", "Fecha nacimiento", "Parentesco", "Ocupacion"
            }
        ));
        jScrollPane9.setViewportView(listDatosFamiliarBeneficiario);

        btGuardarFamiliarBeneficiario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/save_f2.png"))); // NOI18N

        jLabel43.setForeground(new java.awt.Color(255, 0, 0));
        jLabel43.setText("Situación Socio/Familiar");

        jLabel56.setText("NIF/DNI/Pasaporte del familiar");

        org.jdesktop.layout.GroupLayout PanelSituacionFamiliarLayout = new org.jdesktop.layout.GroupLayout(PanelSituacionFamiliar);
        PanelSituacionFamiliar.setLayout(PanelSituacionFamiliarLayout);
        PanelSituacionFamiliarLayout.setHorizontalGroup(
            PanelSituacionFamiliarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PanelSituacionFamiliarLayout.createSequentialGroup()
                .add(PanelSituacionFamiliarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(PanelSituacionFamiliarLayout.createSequentialGroup()
                        .add(26, 26, 26)
                        .add(PanelSituacionFamiliarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(PanelSituacionFamiliarLayout.createSequentialGroup()
                                .add(jLabel56)
                                .add(18, 18, 18)
                                .add(textNIFFamiliarBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 139, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(jLabel57)
                                .add(18, 18, 18)
                                .add(cbParentescoBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jLabel43))
                        .add(28, 28, 28)
                        .add(btGuardarFamiliarBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(btEliminarFamiliarBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 215, Short.MAX_VALUE))
                    .add(PanelSituacionFamiliarLayout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jScrollPane9)))
                .addContainerGap())
        );
        PanelSituacionFamiliarLayout.setVerticalGroup(
            PanelSituacionFamiliarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PanelSituacionFamiliarLayout.createSequentialGroup()
                .add(22, 22, 22)
                .add(PanelSituacionFamiliarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(PanelSituacionFamiliarLayout.createSequentialGroup()
                        .add(jLabel43)
                        .add(12, 12, 12)
                        .add(PanelSituacionFamiliarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel56)
                            .add(textNIFFamiliarBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel57)
                            .add(cbParentescoBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(btGuardarFamiliarBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btEliminarFamiliarBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 12, Short.MAX_VALUE)
                .add(jScrollPane9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 438, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Situación Familiar", PanelSituacionFamiliar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 987, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 581, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

	private void cbEstadoCivilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoCivilActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_cbEstadoCivilActionPerformed

	private void textOcupacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textOcupacionActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_textOcupacionActionPerformed

	private void textTelefonoMovilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTelefonoMovilActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_textTelefonoMovilActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelDatos;
    private javax.swing.JPanel PanelIntervenciones;
    private javax.swing.JPanel PanelSituacionFamiliar;
    private javax.swing.JButton btBorrar;
    private javax.swing.JButton btEliminarFamiliarBeneficiario;
    private javax.swing.JButton btEliminarIntervencionBeneficiario;
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btGuardarFamiliarBeneficiario;
    private javax.swing.JButton btGuardarIntervencionBeneficiario;
    private javax.swing.JComboBox cbEstadoCivil;
    private javax.swing.JComboBox cbNivelEstudios;
    private javax.swing.JComboBox cbParentescoBeneficiario;
    private javax.swing.JComboBox cbTipoVivienda;
    private javax.swing.JComboBox cbTiposAyuda;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelApellidos;
    private javax.swing.JLabel labelCP;
    private javax.swing.JLabel labelDatosPersonales;
    private javax.swing.JLabel labelDomicilio;
    private javax.swing.JLabel labelError;
    private javax.swing.JLabel labelErrorAyuda;
    private javax.swing.JLabel labelEstadoCivil;
    private javax.swing.JLabel labelFechaNacimiento;
    private javax.swing.JLabel labelLocalidad;
    private javax.swing.JLabel labelNIF;
    private javax.swing.JLabel labelNacionalidad;
    private javax.swing.JLabel labelNivelEstudios;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelObservaciones;
    private javax.swing.JLabel labelOcupacion;
    private javax.swing.JLabel labelProfesion;
    private javax.swing.JLabel labelSituacionEconomica;
    private javax.swing.JLabel labelTelefonoFijo;
    private javax.swing.JLabel labelTelefonoMovil;
    private javax.swing.JLabel labelTipoVivienda;
    private javax.swing.JLabel labelVivienda;
    private javax.swing.JLabel labelViviendaEspecificacion;
    private javax.swing.JLabel labelViviendaPrecio;
    private javax.swing.JTable listDatosFamiliarBeneficiario;
    private javax.swing.JTable tbIntervenciones;
    private javax.swing.JTextField textApellidos;
    private javax.swing.JTextField textCP;
    private javax.swing.JTextField textDomicilio;
    private javax.swing.JTextField textEspecificarTipoVivienda;
    private javax.swing.JFormattedTextField textFechaNacimiento;
    private javax.swing.JTextField textImporteBeneficiario;
    private javax.swing.JTextField textLocalidad;
    private javax.swing.JTextField textNIF;
    private javax.swing.JTextField textNIFFamiliarBeneficiario;
    private javax.swing.JTextField textNacionalidad;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textObservaciones;
    private javax.swing.JTextField textObservacionesIntervencionBeneficiario;
    private javax.swing.JTextField textOcupacion;
    private javax.swing.JTextField textPrecioVivienda;
    private javax.swing.JTextField textProfesion;
    private javax.swing.JTextArea textSituacionEconomica;
    private javax.swing.JTextField textTelefonoFijo;
    private javax.swing.JTextField textTelefonoMovil;
    // End of variables declaration//GEN-END:variables
}
