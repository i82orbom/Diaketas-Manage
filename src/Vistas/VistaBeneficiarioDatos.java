/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Modelos.ModeloBeneficiarioDatos;
import Modelos.ModeloVistaLogin;
import java.awt.event.ActionListener;

/**
 *
 * @author psylock
 */
public class VistaBeneficiarioDatos extends javax.swing.JPanel {

    private static ModeloBeneficiarioDatos modelo;
    /**
     * Creates new form VistaBeneficiarioDatos
     */
    public VistaBeneficiarioDatos() {
        initComponents();
        VistaBeneficiarioDatos.modelo = new ModeloBeneficiarioDatos();
    }
    
    public ModeloBeneficiarioDatos getModelo(){
        return VistaBeneficiarioDatos.modelo;
    }
    
    public void setModelo(ModeloBeneficiarioDatos model){
        VistaBeneficiarioDatos.modelo = model;
    }
    
    public void actualizarModelo(){
        VistaBeneficiarioDatos.modelo.setApellidos(textApellidosBeneficiario.getText());
        VistaBeneficiarioDatos.modelo.setCp(textCPBeneficiario.getText());
        VistaBeneficiarioDatos.modelo.setDni(textNIFBeneficiario.getText());
        VistaBeneficiarioDatos.modelo.setDniFamiliar(textNIFFamiliarBeneficiario.getText());
        VistaBeneficiarioDatos.modelo.setDomicilio(textDomicilioBeneficiario.getText());
        VistaBeneficiarioDatos.modelo.setEspecificacionTipoVivienda((String)cbTipoViviendaBeneficiario.getItemAt(cbTipoViviendaBeneficiario.getSelectedIndex()));
        VistaBeneficiarioDatos.modelo.setEstadoCivil((String)cbEstadoCivilBeneficiario.getItemAt(cbEstadoCivilBeneficiario.getSelectedIndex()));
        VistaBeneficiarioDatos.modelo.setFechaNacimiento(textFechaNacimientoBeneficiario.getText());
        VistaBeneficiarioDatos.modelo.setLugarNacimiento(textProvinciaNacimientoBeneficiario.getText());
        VistaBeneficiarioDatos.modelo.setNacionalidad(textNacionalidadBeneficiario.getText());
        VistaBeneficiarioDatos.modelo.setNivelEstudios((String)cbNivelEstudiosBeneficiario.getItemAt(cbNivelEstudiosBeneficiario.getSelectedIndex()));
        VistaBeneficiarioDatos.modelo.setNombre(textNombreBeneficiario.getText());
        VistaBeneficiarioDatos.modelo.setObservaciones(textObservacionesBeneficiario.getText());
        VistaBeneficiarioDatos.modelo.setObservacionesIntervencion(textObservacionesIntervencionBeneficiario.getText());
        VistaBeneficiarioDatos.modelo.setParentesco((String)cbParentescoBeneficiario.getItemAt(cbParentescoBeneficiario.getSelectedIndex()));
        VistaBeneficiarioDatos.modelo.setPrecioVivienda(textPrecioViviendaBeneficiario.getText());
        VistaBeneficiarioDatos.modelo.setProfesion(textProfesionBeneficiario.getText());
        VistaBeneficiarioDatos.modelo.setTelefono(textTelefonoBeneficiario.getText());
        VistaBeneficiarioDatos.modelo.setTipoVivienda((String)cbTipoViviendaBeneficiario.getItemAt(cbTipoViviendaBeneficiario.getSelectedIndex()));
        VistaBeneficiarioDatos.modelo.setObservacionesFamiliar(textObservacionesFamiliarBeneficiario.getText());
        VistaBeneficiarioDatos.modelo.setImporteIntervencion(textImporteBeneficiario.getText());
        VistaBeneficiarioDatos.modelo.setConceptoIntervencion(textConceptoBeneficiario.getText());
    }
    
    public void volcarModelo(){
        
        
        textApellidosBeneficiario.setText(VistaBeneficiarioDatos.modelo.getApellidos());
        textCPBeneficiario.setText(VistaBeneficiarioDatos.modelo.getCp());
        textNIFBeneficiario.setText(VistaBeneficiarioDatos.modelo.getDni());
        textNIFFamiliarBeneficiario.setText(VistaBeneficiarioDatos.modelo.getDniFamiliar());
        textDomicilioBeneficiario.setText(VistaBeneficiarioDatos.modelo.getDomicilio());
        cbTipoViviendaBeneficiario.setSelectedItem(VistaBeneficiarioDatos.modelo.getTipoVivienda());
        cbEstadoCivilBeneficiario.setSelectedItem(VistaBeneficiarioDatos.modelo.getEstadoCivil());
        textFechaNacimientoBeneficiario.setText(VistaBeneficiarioDatos.modelo.getFechaNacimiento());
        textProvinciaNacimientoBeneficiario.setText(VistaBeneficiarioDatos.modelo.getLugarNacimiento());
        textNacionalidadBeneficiario.setText(VistaBeneficiarioDatos.modelo.getNacionalidad());
        cbNivelEstudiosBeneficiario.setSelectedItem(VistaBeneficiarioDatos.modelo.getNivelEstudios());
        textNombreBeneficiario.setText(VistaBeneficiarioDatos.modelo.getNombre());
        textObservacionesBeneficiario.setText(VistaBeneficiarioDatos.modelo.getObservaciones());
        textObservacionesIntervencionBeneficiario.setText(VistaBeneficiarioDatos.modelo.getObservacionesIntervencion());
        cbParentescoBeneficiario.setSelectedItem(VistaBeneficiarioDatos.modelo.getParentesco());
        textPrecioViviendaBeneficiario.setText(VistaBeneficiarioDatos.modelo.getPrecioVivienda());
        textProfesionBeneficiario.setText(VistaBeneficiarioDatos.modelo.getProfesion());
        textTelefonoBeneficiario.setText(VistaBeneficiarioDatos.modelo.getTelefono());
        cbTipoViviendaBeneficiario.setSelectedItem(VistaBeneficiarioDatos.modelo.getTipoVivienda());
        textObservacionesFamiliarBeneficiario.setText(VistaBeneficiarioDatos.modelo.getObservacionesFamiliar());
        textImporteBeneficiario.setText(VistaBeneficiarioDatos.modelo.getImporteIntervencion());
        textConceptoBeneficiario.setText(VistaBeneficiarioDatos.modelo.getConceptoIntervencion());
    }
    
     // LISTENERS
    public void anadirListenerbtEliminarBeneficiario(ActionListener listener){
        this.btEliminarBeneficiario.addActionListener(listener);
    }
    
    public void anadirListenerbtEliminarFamiliarBeneficiario(ActionListener listener){
        this.btEliminarFamiliarBeneficiario.addActionListener(listener);
    }
    

    public void anadirListenerbtEliminarIntervencionBeneficiario(ActionListener listener){
        this.btEliminarIntervencionBeneficiario.addActionListener(listener);
    }
    
    public void anadirListenerbtGuardarBeneficiario(ActionListener listener){
        this.btGuardarBeneficiario.addActionListener(listener);
    }
    
    public void anadirListenerbtGuardarFamiliarBeneficiario(ActionListener listener){
        this.btGuardarFamiliarBeneficiario.addActionListener(listener);
    }
    
    public void anadirListenerbtGuardarIntervencionBeneficiario(ActionListener listener){
        this.btGuardarIntervencionBeneficiario.addActionListener(listener);
    }
    
    public void anadirListenernavToBeneficiariosFromBeneficiarioDatos(ActionListener listener){
        this.navToBeneficiariosFromBeneficiarioDatos.addActionListener(listener);
    }
    
    public void anadirListenernavToMainFromBeneficiarioDatos(ActionListener listener){
        this.navToMainFromBeneficiarioDatos.addActionListener(listener);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        separadorBeneficiario3 = new javax.swing.JSeparator();
        senalaBeneficiario5 = new javax.swing.JLabel();
        navToMainFromBeneficiarioDatos = new javax.swing.JButton();
        navToBeneficiariosFromBeneficiarioDatos = new javax.swing.JButton();
        senalaBeneficiario6 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelDatosGenerales = new javax.swing.JPanel();
        textTelefonoBeneficiario = new javax.swing.JTextField();
        textCPBeneficiario = new javax.swing.JTextField();
        textApellidosBeneficiario = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        cbEstadoCivilBeneficiario = new javax.swing.JComboBox();
        textProfesionBeneficiario = new javax.swing.JTextField();
        textProvinciaNacimientoBeneficiario = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        cbNivelEstudiosBeneficiario = new javax.swing.JComboBox();
        textNacionalidadBeneficiario = new javax.swing.JTextField();
        textNombreBeneficiario = new javax.swing.JTextField();
        textNIFBeneficiario = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        textDomicilioBeneficiario = new javax.swing.JTextField();
        textLocalidadBeneficiario = new javax.swing.JTextField();
        textObservacionesBeneficiario = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        textFechaNacimientoBeneficiario = new javax.swing.JFormattedTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        cbTipoViviendaBeneficiario = new javax.swing.JComboBox();
        textPrecioViviendaBeneficiario = new javax.swing.JTextField();
        textEspecificarTipoVivienda = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btGuardarBeneficiario = new javax.swing.JButton();
        btEliminarBeneficiario = new javax.swing.JButton();
        PanelSituacionFamiliar = new javax.swing.JPanel();
        cbParentescoBeneficiario = new javax.swing.JComboBox();
        jLabel57 = new javax.swing.JLabel();
        btEliminarFamiliarBeneficiario = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        listDatosFamiliarBeneficiario = new javax.swing.JTable();
        textObservacionesFamiliarBeneficiario = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        btGuardarFamiliarBeneficiario = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        textNIFFamiliarBeneficiario = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        PanelIntervenciones = new javax.swing.JPanel();
        btGuardarIntervencionBeneficiario = new javax.swing.JButton();
        btEliminarIntervencionBeneficiario = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel60 = new javax.swing.JLabel();
        textObservacionesIntervencionBeneficiario = new javax.swing.JTextField();
        textConceptoBeneficiario = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        textImporteBeneficiario = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setPreferredSize(new java.awt.Dimension(1000, 600));

        senalaBeneficiario5.setForeground(new java.awt.Color(153, 153, 153));
        senalaBeneficiario5.setText(">");

        navToMainFromBeneficiarioDatos.setForeground(new java.awt.Color(0, 0, 255));
        navToMainFromBeneficiarioDatos.setText("DIAKETAS");
        navToMainFromBeneficiarioDatos.setActionCommand("navToMainFromBeneficiarioDatos");
        navToMainFromBeneficiarioDatos.setBorderPainted(false);

        navToBeneficiariosFromBeneficiarioDatos.setForeground(new java.awt.Color(0, 0, 255));
        navToBeneficiariosFromBeneficiarioDatos.setText("Beneficiarios");
        navToBeneficiariosFromBeneficiarioDatos.setActionCommand("navToBeneficiariosFromBeneficiarioDatos");
        navToBeneficiariosFromBeneficiarioDatos.setBorderPainted(false);

        senalaBeneficiario6.setForeground(new java.awt.Color(153, 153, 153));
        senalaBeneficiario6.setText(">");

        jLabel33.setText("Datos");

        PanelDatosGenerales.setBackground(new java.awt.Color(255, 255, 255));

        jLabel49.setText("Telefono");

        cbEstadoCivilBeneficiario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Soltero", "Casado", "Viudo" }));

        jLabel35.setText("Nombre");

        jLabel38.setText("Nacionalidad");

        jLabel37.setText("NIF/DNI/Pasaporte");

        jLabel39.setText("Nivel de estudios");

        cbNivelEstudiosBeneficiario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Graduado Escolar", "Graduado Secundario", "Graduado Medio", "Graduado Superior", "Estudios Universitarios" }));

        jLabel42.setText("Observaciones");

        jLabel40.setText("Domicilio");

        jLabel41.setText("Localidad");

        jLabel46.setText("en");

        jLabel47.setText("Profesion");

        jLabel48.setText("CP");

        jLabel44.setText("Nacido el");

        jLabel45.setText("Estado civil");

        jLabel55.setText("Apellidos");

        jLabel34.setForeground(new java.awt.Color(255, 0, 0));
        jLabel34.setText("Datos personales");

        jLabel50.setForeground(new java.awt.Color(255, 0, 0));
        jLabel50.setText("Vivienda");

        jLabel51.setText("Tipo");

        jLabel52.setText("Especificar");

        jLabel53.setText("Precio");

        cbTipoViviendaBeneficiario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alquilada", "Cedida", "Otro" }));

        jLabel54.setText("Descripción de la situación económica");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane7.setViewportView(jTextArea1);

        btGuardarBeneficiario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/save_f2.png"))); // NOI18N

        btEliminarBeneficiario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/limpiar.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout PanelDatosGeneralesLayout = new org.jdesktop.layout.GroupLayout(PanelDatosGenerales);
        PanelDatosGenerales.setLayout(PanelDatosGeneralesLayout);
        PanelDatosGeneralesLayout.setHorizontalGroup(
            PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PanelDatosGeneralesLayout.createSequentialGroup()
                .add(14, 14, 14)
                .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel34)
                    .add(PanelDatosGeneralesLayout.createSequentialGroup()
                        .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel38)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel37)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel35)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel39)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel40)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel41)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel42)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel51)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel52))
                            .add(jLabel50))
                        .add(34, 34, 34)
                        .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(PanelDatosGeneralesLayout.createSequentialGroup()
                                .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(textNacionalidadBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 137, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                        .add(textLocalidadBeneficiario)
                                        .add(textNombreBeneficiario)
                                        .add(textDomicilioBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 137, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(textNIFBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 137, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(28, 28, 28)
                                .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(jLabel49)
                                    .add(jLabel48)
                                    .add(jLabel45)
                                    .add(jLabel44)
                                    .add(jLabel55))
                                .add(18, 18, 18)
                                .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(PanelDatosGeneralesLayout.createSequentialGroup()
                                        .add(textFechaNacimientoBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(12, 12, 12)
                                        .add(jLabel46)
                                        .add(18, 18, 18)
                                        .add(textProvinciaNacimientoBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 117, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(textTelefonoBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(textCPBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(textApellidosBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(textEspecificarTipoVivienda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(PanelDatosGeneralesLayout.createSequentialGroup()
                                .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(textObservacionesBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 403, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(PanelDatosGeneralesLayout.createSequentialGroup()
                                        .add(cbNivelEstudiosBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 171, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel47)
                                        .add(18, 18, 18)
                                        .add(textProfesionBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(PanelDatosGeneralesLayout.createSequentialGroup()
                                        .add(255, 255, 255)
                                        .add(cbEstadoCivilBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .add(150, 150, 150)
                                .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel54)
                                    .add(jScrollPane7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(PanelDatosGeneralesLayout.createSequentialGroup()
                                .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(PanelDatosGeneralesLayout.createSequentialGroup()
                                        .add(cbTipoViviendaBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(51, 51, 51)
                                        .add(jLabel53)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(textPrecioViviendaBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 95, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(2, 2, 2))
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, PanelDatosGeneralesLayout.createSequentialGroup()
                                        .add(btGuardarBeneficiario)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                                .add(btEliminarBeneficiario)))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelDatosGeneralesLayout.setVerticalGroup(
            PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PanelDatosGeneralesLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel34)
                .add(18, 18, 18)
                .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(PanelDatosGeneralesLayout.createSequentialGroup()
                        .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel35)
                            .add(textNombreBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(6, 6, 6)
                        .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(textNIFBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel37)
                            .add(jLabel44)
                            .add(jLabel46)
                            .add(textProvinciaNacimientoBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(textFechaNacimientoBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel55)
                        .add(textApellidosBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(PanelDatosGeneralesLayout.createSequentialGroup()
                        .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel38)
                            .add(textNacionalidadBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel45)
                            .add(cbEstadoCivilBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel39)
                            .add(cbNivelEstudiosBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel47)
                            .add(textProfesionBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(13, 13, 13)
                        .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel40)
                            .add(textDomicilioBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel49)
                            .add(textTelefonoBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel41)
                            .add(textLocalidadBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel48)
                            .add(textCPBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(textObservacionesBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel42))
                        .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(PanelDatosGeneralesLayout.createSequentialGroup()
                                .add(33, 33, 33)
                                .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel51)
                                    .add(cbTipoViviendaBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel53)
                                    .add(textPrecioViviendaBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(PanelDatosGeneralesLayout.createSequentialGroup()
                                .add(18, 18, 18)
                                .add(jLabel50)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(textEspecificarTipoVivienda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel52)))
                    .add(PanelDatosGeneralesLayout.createSequentialGroup()
                        .add(jLabel54)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jScrollPane7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 241, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 87, Short.MAX_VALUE)
                .add(PanelDatosGeneralesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, btGuardarBeneficiario)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, btEliminarBeneficiario))
                .add(46, 46, 46))
        );

        jTabbedPane1.addTab("Datos Generales", PanelDatosGenerales);

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

        jLabel58.setText("Observaciones");

        btGuardarFamiliarBeneficiario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/save_f2.png"))); // NOI18N

        jLabel43.setForeground(new java.awt.Color(255, 0, 0));
        jLabel43.setText("Situación Socio/Familiar");

        jLabel56.setText("NIF/DNI/Pasaporte del familiar");

        org.jdesktop.layout.GroupLayout PanelSituacionFamiliarLayout = new org.jdesktop.layout.GroupLayout(PanelSituacionFamiliar);
        PanelSituacionFamiliar.setLayout(PanelSituacionFamiliarLayout);
        PanelSituacionFamiliarLayout.setHorizontalGroup(
            PanelSituacionFamiliarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PanelSituacionFamiliarLayout.createSequentialGroup()
                .add(18, 18, 18)
                .add(jScrollPane9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(PanelSituacionFamiliarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(btEliminarFamiliarBeneficiario)
                    .add(btGuardarFamiliarBeneficiario)))
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
                        .add(cbParentescoBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(PanelSituacionFamiliarLayout.createSequentialGroup()
                        .add(jLabel43)
                        .addContainerGap(792, Short.MAX_VALUE))
                    .add(PanelSituacionFamiliarLayout.createSequentialGroup()
                        .add(jLabel58)
                        .add(18, 18, 18)
                        .add(textObservacionesFamiliarBeneficiario)
                        .add(91, 91, 91))))
        );
        PanelSituacionFamiliarLayout.setVerticalGroup(
            PanelSituacionFamiliarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PanelSituacionFamiliarLayout.createSequentialGroup()
                .add(22, 22, 22)
                .add(jLabel43)
                .add(12, 12, 12)
                .add(PanelSituacionFamiliarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel56)
                    .add(textNIFFamiliarBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel57)
                    .add(cbParentescoBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(PanelSituacionFamiliarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 280, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(PanelSituacionFamiliarLayout.createSequentialGroup()
                        .add(btGuardarFamiliarBeneficiario)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btEliminarFamiliarBeneficiario)))
                .add(72, 72, 72)
                .add(PanelSituacionFamiliarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel58)
                    .add(textObservacionesFamiliarBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Situación Familiar", PanelSituacionFamiliar);

        PanelIntervenciones.setBackground(new java.awt.Color(255, 255, 255));

        btGuardarIntervencionBeneficiario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/save_f2.png"))); // NOI18N

        btEliminarIntervencionBeneficiario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/limpiar.png"))); // NOI18N

        jLabel59.setForeground(new java.awt.Color(255, 0, 0));
        jLabel59.setText("Intervenciones realizadas");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Fecha", "Concepto", "Importe", "Observaciones"
            }
        ));
        jScrollPane10.setViewportView(jTable3);

        jLabel60.setText("Importe");

        jLabel62.setText("Observaciones");

        jLabel61.setText("Concepto");

        org.jdesktop.layout.GroupLayout PanelIntervencionesLayout = new org.jdesktop.layout.GroupLayout(PanelIntervenciones);
        PanelIntervenciones.setLayout(PanelIntervencionesLayout);
        PanelIntervencionesLayout.setHorizontalGroup(
            PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PanelIntervencionesLayout.createSequentialGroup()
                .add(27, 27, 27)
                .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPane10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
                    .add(PanelIntervencionesLayout.createSequentialGroup()
                        .add(jLabel62)
                        .add(18, 18, 18)
                        .add(textObservacionesIntervencionBeneficiario)
                        .add(39, 39, 39))
                    .add(jLabel59)
                    .add(PanelIntervencionesLayout.createSequentialGroup()
                        .add(763, 763, 763)
                        .add(btGuardarIntervencionBeneficiario)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btEliminarIntervencionBeneficiario))
                    .add(PanelIntervencionesLayout.createSequentialGroup()
                        .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(PanelIntervencionesLayout.createSequentialGroup()
                                .add(jLabel61)
                                .add(32, 32, 32))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel60))
                        .add(18, 18, 18)
                        .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(textConceptoBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 717, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(textImporteBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        PanelIntervencionesLayout.setVerticalGroup(
            PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, PanelIntervencionesLayout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .add(jLabel59)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 244, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(24, 24, 24)
                .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel60)
                    .add(textImporteBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(12, 12, 12)
                .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(textConceptoBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel61))
                .add(12, 12, 12)
                .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel62)
                    .add(textObservacionesIntervencionBeneficiario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(20, 20, 20)
                .add(PanelIntervencionesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, btGuardarIntervencionBeneficiario)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, btEliminarIntervencionBeneficiario))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Intervenciones", PanelIntervenciones);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 941, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(separadorBeneficiario3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(navToMainFromBeneficiarioDatos)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(senalaBeneficiario5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(navToBeneficiariosFromBeneficiarioDatos)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(senalaBeneficiario6)
                        .add(26, 26, 26)
                        .add(jLabel33)
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(senalaBeneficiario5)
                    .add(navToMainFromBeneficiarioDatos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(navToBeneficiariosFromBeneficiarioDatos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(senalaBeneficiario6)
                    .add(jLabel33))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(separadorBeneficiario3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelDatosGenerales;
    private javax.swing.JPanel PanelIntervenciones;
    private javax.swing.JPanel PanelSituacionFamiliar;
    private javax.swing.JButton btEliminarBeneficiario;
    private javax.swing.JButton btEliminarFamiliarBeneficiario;
    private javax.swing.JButton btEliminarIntervencionBeneficiario;
    private javax.swing.JButton btGuardarBeneficiario;
    private javax.swing.JButton btGuardarFamiliarBeneficiario;
    private javax.swing.JButton btGuardarIntervencionBeneficiario;
    private javax.swing.JComboBox cbEstadoCivilBeneficiario;
    private javax.swing.JComboBox cbNivelEstudiosBeneficiario;
    private javax.swing.JComboBox cbParentescoBeneficiario;
    private javax.swing.JComboBox cbTipoViviendaBeneficiario;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable listDatosFamiliarBeneficiario;
    private javax.swing.JButton navToBeneficiariosFromBeneficiarioDatos;
    private javax.swing.JButton navToMainFromBeneficiarioDatos;
    private javax.swing.JLabel senalaBeneficiario5;
    private javax.swing.JLabel senalaBeneficiario6;
    private javax.swing.JSeparator separadorBeneficiario3;
    private javax.swing.JTextField textApellidosBeneficiario;
    private javax.swing.JTextField textCPBeneficiario;
    private javax.swing.JTextField textConceptoBeneficiario;
    private javax.swing.JTextField textDomicilioBeneficiario;
    private javax.swing.JTextField textEspecificarTipoVivienda;
    private javax.swing.JFormattedTextField textFechaNacimientoBeneficiario;
    private javax.swing.JTextField textImporteBeneficiario;
    private javax.swing.JTextField textLocalidadBeneficiario;
    private javax.swing.JTextField textNIFBeneficiario;
    private javax.swing.JTextField textNIFFamiliarBeneficiario;
    private javax.swing.JTextField textNacionalidadBeneficiario;
    private javax.swing.JTextField textNombreBeneficiario;
    private javax.swing.JTextField textObservacionesBeneficiario;
    private javax.swing.JTextField textObservacionesFamiliarBeneficiario;
    private javax.swing.JTextField textObservacionesIntervencionBeneficiario;
    private javax.swing.JTextField textPrecioViviendaBeneficiario;
    private javax.swing.JTextField textProfesionBeneficiario;
    private javax.swing.JTextField textProvinciaNacimientoBeneficiario;
    private javax.swing.JTextField textTelefonoBeneficiario;
    // End of variables declaration//GEN-END:variables
}
