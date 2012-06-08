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
import javax.swing.*;

/**
 *
 * @author psylock
 *
 * HISTORIAL:
 *      000 - 23 May 2012 - JAEG - Añadida funcion para insertar los datos generales. Eliminado "lugar de nacimiento" (dato no existente en el modelo)
 *      001 - 29 May 2012 - RC - Adicion getter y labelErrorAyuda
 *      002 - 30 May 2012 - JAEG - Añadida consulta de familiares del beneficiario
 *      003 - 30 May 2012 - RC - adicion boton limpiar campos de ayuda
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
    public JTable getTbFamiliares(){
        return tbFamiliares;
    }

    public void setTextLabelErrorAyuda(String text) {
        labelErrorAyuda.setText(text);
        labelErrorAyuda.setVisible(true);
    }

    public JLabel getLabelErrorAyuda() {
        return labelErrorAyuda;
    }

    public JButton getBtLimpiarCamposAyuda() {
        return btLimpiarCamposAyuda;
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

	public JButton getBtGuardarFamiliarBeneficiario() {
		return btGuardarFamiliarBeneficiario;
	}
	
	public JButton getBtEliminarFamiliarBeneficiario() {
		return btEliminarFamiliarBeneficiario;
	}
	
	public JTextField getTextNIFFamiliarBeneficiario() {
		return textNIFFamiliarBeneficiario;
	}
	
	public JComboBox getCbParentescoBeneficiario() {
		return cbParentescoBeneficiario;
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
        btLimpiarCamposAyuda = new javax.swing.JButton();
        PanelSituacionFamiliar = new javax.swing.JPanel();
        cbParentescoBeneficiario = new javax.swing.JComboBox();
        jLabel57 = new javax.swing.JLabel();
        btEliminarFamiliarBeneficiario = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbFamiliares = new javax.swing.JTable();
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

        btGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/guardar.png"))); // NOI18N

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

        javax.swing.GroupLayout PanelDatosLayout = new javax.swing.GroupLayout(PanelDatos);
        PanelDatos.setLayout(PanelDatosLayout);
        PanelDatosLayout.setHorizontalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addComponent(labelDatosPersonales)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDatosLayout.createSequentialGroup()
                                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelNacionalidad, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelNIF, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelNivelEstudios, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelTelefonoFijo, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelDomicilio, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelLocalidad, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelObservaciones, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelTipoVivienda, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelViviendaEspecificacion, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelDatosLayout.createSequentialGroup()
                                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cbNivelEstudios, 0, 137, Short.MAX_VALUE)
                                            .addComponent(textNacionalidad)
                                            .addComponent(textNombre)
                                            .addComponent(textNIF)
                                            .addComponent(textTelefonoFijo))
                                        .addGap(28, 28, 28)
                                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelEstadoCivil)
                                            .addComponent(labelFechaNacimiento)
                                            .addComponent(labelApellidos)
                                            .addComponent(labelProfesion)
                                            .addComponent(labelOcupacion)
                                            .addComponent(labelTelefonoMovil))
                                        .addGap(18, 18, 18)
                                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(PanelDatosLayout.createSequentialGroup()
                                                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(textOcupacion, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                                        .addComponent(textTelefonoMovil))
                                                    .addComponent(textProfesion, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(75, 75, 75)
                                                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(labelSituacionEconomica, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(PanelDatosLayout.createSequentialGroup()
                                        .addComponent(textLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(labelCP)
                                        .addGap(21, 21, 21)
                                        .addComponent(textCP, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(textObservaciones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                        .addComponent(textDomicilio, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(PanelDatosLayout.createSequentialGroup()
                                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanelDatosLayout.createSequentialGroup()
                                                .addComponent(cbTipoVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(37, 37, 37)
                                                .addComponent(labelViviendaPrecio)
                                                .addGap(18, 18, 18)
                                                .addComponent(textPrecioVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(textEspecificarTipoVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(83, 83, 83)
                                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanelDatosLayout.createSequentialGroup()
                                                .addComponent(btGuardar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btBorrar))
                                            .addComponent(labelError, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(labelVivienda))
                        .addContainerGap())))
        );
        PanelDatosLayout.setVerticalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDatosPersonales)
                .addGap(18, 18, 18)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNombre)
                            .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textNIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNIF)
                            .addComponent(labelFechaNacimiento)
                            .addComponent(textFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNacionalidad)
                            .addComponent(textNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEstadoCivil)
                            .addComponent(cbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelApellidos)
                            .addComponent(textApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelSituacionEconomica)
                        .addGap(18, 18, 18)))
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNivelEstudios)
                            .addComponent(cbNivelEstudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelProfesion)
                            .addComponent(textProfesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDatosLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelOcupacion)
                                    .addComponent(textOcupacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelTelefonoFijo)
                                    .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(textTelefonoMovil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelTelefonoMovil))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDatosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textTelefonoFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDomicilio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelLocalidad)
                            .addComponent(textLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCP)
                            .addComponent(textCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelObservaciones))
                        .addGap(33, 33, 33)
                        .addComponent(labelVivienda)
                        .addGap(2, 2, 2)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTipoVivienda)
                            .addComponent(cbTipoVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelViviendaPrecio)
                            .addComponent(textPrecioVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btGuardar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btBorrar, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelViviendaEspecificacion)
                    .addComponent(textEspecificarTipoVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelError))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos Generales", PanelDatos);

        PanelIntervenciones.setBackground(new java.awt.Color(255, 255, 255));

        btGuardarIntervencionBeneficiario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/guardar.png"))); // NOI18N
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

        btLimpiarCamposAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/limpiar.png"))); // NOI18N
        btLimpiarCamposAyuda.setToolTipText("Limpiar los campos");

        javax.swing.GroupLayout PanelIntervencionesLayout = new javax.swing.GroupLayout(PanelIntervenciones);
        PanelIntervenciones.setLayout(PanelIntervencionesLayout);
        PanelIntervencionesLayout.setHorizontalGroup(
            PanelIntervencionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelIntervencionesLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(PanelIntervencionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10)
                    .addGroup(PanelIntervencionesLayout.createSequentialGroup()
                        .addGroup(PanelIntervencionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel59)
                            .addGroup(PanelIntervencionesLayout.createSequentialGroup()
                                .addGroup(PanelIntervencionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelIntervencionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(PanelIntervencionesLayout.createSequentialGroup()
                                            .addComponent(jLabel61)
                                            .addGap(32, 32, 32))
                                        .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel62))
                                .addGap(18, 18, 18)
                                .addGroup(PanelIntervencionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textObservacionesIntervencionBeneficiario)
                                    .addGroup(PanelIntervencionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cbTiposAyuda, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textImporteBeneficiario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))))
                            .addGroup(PanelIntervencionesLayout.createSequentialGroup()
                                .addGap(260, 260, 260)
                                .addComponent(labelErrorAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btGuardarIntervencionBeneficiario)
                                .addGap(18, 18, 18)
                                .addComponent(btEliminarIntervencionBeneficiario)
                                .addGap(18, 18, 18)
                                .addComponent(btLimpiarCamposAyuda)))
                        .addGap(0, 56, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelIntervencionesLayout.setVerticalGroup(
            PanelIntervencionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelIntervencionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(PanelIntervencionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(textImporteBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelIntervencionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(cbTiposAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(PanelIntervencionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(textObservacionesIntervencionBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PanelIntervencionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelIntervencionesLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(PanelIntervencionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btGuardarIntervencionBeneficiario, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btEliminarIntervencionBeneficiario, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btLimpiarCamposAyuda, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelIntervencionesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelErrorAyuda)
                        .addGap(23, 23, 23))))
        );

        jTabbedPane1.addTab("Intervenciones", PanelIntervenciones);

        PanelSituacionFamiliar.setBackground(new java.awt.Color(255, 255, 255));

        cbParentescoBeneficiario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Consanguíneo", "Cercano", "Lejano", "Tutor" }));

        jLabel57.setText("Parentesco");

        btEliminarFamiliarBeneficiario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/limpiar.png"))); // NOI18N

        tbFamiliares.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre y Apellidos", "Fecha nacimiento", "Parentesco", "NIF"
            }
        ));
        jScrollPane9.setViewportView(tbFamiliares);

        btGuardarFamiliarBeneficiario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/guardar.png"))); // NOI18N

        jLabel43.setForeground(new java.awt.Color(255, 0, 0));
        jLabel43.setText("Situación Socio/Familiar");

        jLabel56.setText("NIF/DNI/Pasaporte del familiar");

        javax.swing.GroupLayout PanelSituacionFamiliarLayout = new javax.swing.GroupLayout(PanelSituacionFamiliar);
        PanelSituacionFamiliar.setLayout(PanelSituacionFamiliarLayout);
        PanelSituacionFamiliarLayout.setHorizontalGroup(
            PanelSituacionFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSituacionFamiliarLayout.createSequentialGroup()
                .addGroup(PanelSituacionFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSituacionFamiliarLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(PanelSituacionFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelSituacionFamiliarLayout.createSequentialGroup()
                                .addComponent(jLabel56)
                                .addGap(18, 18, 18)
                                .addComponent(textNIFFamiliarBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel57)
                                .addGap(18, 18, 18)
                                .addComponent(cbParentescoBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel43))
                        .addGap(28, 28, 28)
                        .addComponent(btGuardarFamiliarBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btEliminarFamiliarBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 221, Short.MAX_VALUE))
                    .addGroup(PanelSituacionFamiliarLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane9)))
                .addContainerGap())
        );
        PanelSituacionFamiliarLayout.setVerticalGroup(
            PanelSituacionFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSituacionFamiliarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(PanelSituacionFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelSituacionFamiliarLayout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addGap(12, 12, 12)
                        .addGroup(PanelSituacionFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(textNIFFamiliarBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57)
                            .addComponent(cbParentescoBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btGuardarFamiliarBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEliminarFamiliarBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Situación Familiar", PanelSituacionFamiliar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
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
    private javax.swing.JButton btLimpiarCamposAyuda;
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
    private javax.swing.JTable tbFamiliares;
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
