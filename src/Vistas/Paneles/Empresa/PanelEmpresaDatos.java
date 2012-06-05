/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Paneles.Empresa;

import Modelo.C_Empresa;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Francisco
 */
public class PanelEmpresaDatos extends javax.swing.JPanel {

    /**
     * Creates new form PanelEmpresaDatos
     */
    public PanelEmpresaDatos() {
        initComponents();
        jLabelError.setVisible(false);
        jLabelErrorColaboraciones.setVisible(false);
    }

    public JLabel getLabelError() {
        return jLabelError;
    }

    public void setTextLabelError(String text){
        jLabelError.setText(text);
        jLabelError.setVisible(true);
    }

    public JLabel getLabelErrorColaboraciones(){
        return jLabelErrorColaboraciones;
    }

    public void setTextLabelErrorColaboraciones(String text){
        jLabelErrorColaboraciones.setText(text);
        jLabelErrorColaboraciones.setVisible(true);
    }

    /* CAMPOS */
    public JTextField getTextNombre(){
        return jTextNombre;
    }

    public void setColorLabelNombre(Color cf) {
        jLabelNombre.setForeground(cf);
    }

    public JTextField getTextCIF(){
        return jTextCIF;
    }

    public void setColorLabelCIF(Color cf) {
        jLabelCIF.setForeground(cf);
    }

    public JTextField getTextDireccionWeb(){
        return jTextDireccionWeb;
    }

    public void setColorLabelDireccionWeb(Color cf) {
        jLabelDireccionWeb.setForeground(cf);
    }

    public JTextField getTextDireccion(){
        return jTextDireccion;
    }

    public void setColorLabelDireccion(Color cf) {
        jLabelDireccion.setForeground(cf);
    }

    public JTextField getTextLocalidad(){
        return jTextLocalidad;
    }

    public void setColorLabelLocalidad(Color cf) {
        jLabelLocalidad.setForeground(cf);
    }

    public JTextField getTextProvincia(){
        return jTextProvincia;
    }

    public void setColorLabelProvincia(Color cf) {
        jLabelProvincia.setForeground(cf);
    }

    public JTextField getTextCP(){
        return jTextCP;
    }

    public void setColorLabelCP(Color cf) {
        jLabelCP.setForeground(cf);
    }

    public JTextField getTextTelefonoFijo(){
        return jTextTelefonoFijo;
    }

    public void setColorLabelTelefonoFijo(Color cf) {
        jLabelTelefonoFijo.setForeground(cf);
    }

    public JTextField getTextTelefonoMovil(){
        return jTextTelefonoMovil;
    }

    public void setColorLabelTelefonoMovil(Color cf) {
        jLabelTelefonoMovil.setForeground(cf);
    }

    public JTextField getTextFax(){
        return jTextFax;
    }

    public void setColorLabelFax(Color cf) {
        jLabelFax.setForeground(cf);
    }

    public JTextField getTextEmail(){
        return jTextEmail;
    }

    public void setColorLabelEmail(Color cf) {
        jLabelEmail.setForeground(cf);
    }

    public JTextField getTextImporte(){
        return jTextImporte;
    }

    public void setColorLabelImporte(Color cf){
        jLabelImporte.setForeground(cf);
    }

    public JTextField getTextConcepto(){
        return jTextConcepto;
    }

    public void setColorLabelConcepto(Color cf){
        jLabelConcepto.setForeground(cf);
    }

    public JTextField getTextFecha(){
        return jFormattedTextFecha;
    }

    public void setColorLabelFecha(Color cf){
        jLabelFecha.setForeground(cf);
    }
    
    public JTextField getTextFechaInicial(){
        return jFormattedTextFechaInicial;
    }

    public void setColorLabelFechaInicial(Color cf){
        jLabelFechaInicial.setForeground(cf);
    }
    
    public JTextField getTextFechaFinal(){
        return jFormattedTextFechaFinal;
    }

    public void setColorLabelFechaFinal(Color cf){
        jLabelFechaFinal.setForeground(cf);
    }

    public void setColorLabels(Color cf){
        jLabelNombre.setForeground(cf);
        jLabelCIF.setForeground(cf);
        jLabelDireccionWeb.setForeground(cf);
        jLabelCP.setForeground(cf);
        jLabelLocalidad.setForeground(cf);
        jLabelProvincia.setForeground(cf);
        jLabelDireccion.setForeground(cf);
        jLabelTelefonoFijo.setForeground(cf);
        jLabelTelefonoMovil.setForeground(cf);
        jLabelFax.setForeground(cf);
        jLabelEmail.setForeground(cf);
    }

    public void limpiarCampos(){
        setColorLabels(Color.black);
        jLabelError.setVisible(false);
        jLabelErrorColaboraciones.setVisible(false);
        jTextNombre.setText("");
        jTextCIF.setText("");
        jTextDireccionWeb.setText("");
        jTextDireccion.setText("");
        jTextProvincia.setText("");
        jTextLocalidad.setText("");
        jTextFax.setText("");
        jTextEmail.setText("");
        jTextTelefonoFijo.setText("");
        jTextTelefonoMovil.setText("");
    }

    public String[] getDatosPersonales() {
        String[] datos = new String[20];

        datos[C_Empresa.NOMBRE_ID] = jTextNombre.getText();
        datos[C_Empresa.CIF_ID] = jTextCIF.getText();
        datos[C_Empresa.DIRECCION_WEB_ID] = jTextDireccionWeb.getText();
        datos[C_Empresa.TELEFONO1_ID] = jTextTelefonoFijo.getText();
        datos[C_Empresa.TELEFONO2_ID] = jTextTelefonoMovil.getText();
        datos[C_Empresa.DIRECCION_ID] = jTextDireccion.getText();
        datos[C_Empresa.LOCALIDAD_ID] = jTextLocalidad.getText();
        datos[C_Empresa.PROVINCIA_ID] = jTextProvincia.getText();
        datos[C_Empresa.CP_ID] = jTextCP.getText();
        datos[C_Empresa.FAX_ID] = jTextFax.getText();
        datos[C_Empresa.EMAIL_ID] = jTextEmail.getText();

        return datos;
    }

    /* BOTONES */
    public JButton getBtGuardarEmpresa(){
        return btGuardarEmpresa;
    }

    public JButton getBtLimpiarEmpresa(){
        return btLimpiarEmpresa;
    }

    public JButton getBtGuardarColaboracionesEmpresa(){
        return btGuardarColaboracionesEmpresa;
    }

    public JButton getBtBuscarColaboracionesEmpresa(){
        return btBuscarColaboracionesEmpresa;
    }

    public JButton getBtEliminarColaboracionesEmpresa(){
        return btEliminarColaboracionesEmpresa;
    }

    public JTable getTbColaboracionesEmpresa(){
        return jTableColaboracionesEmpresa;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane4 = new javax.swing.JTabbedPane();
        PanelDatosEmpresa = new javax.swing.JPanel();
        btGuardarEmpresa = new javax.swing.JButton();
        btLimpiarEmpresa = new javax.swing.JButton();
        jTextNombre = new javax.swing.JTextField();
        jTextCIF = new javax.swing.JTextField();
        jTextDireccionWeb = new javax.swing.JTextField();
        jTextDireccion = new javax.swing.JTextField();
        jTextLocalidad = new javax.swing.JTextField();
        jTextProvincia = new javax.swing.JTextField();
        jTextCP = new javax.swing.JTextField();
        jTextTelefonoFijo = new javax.swing.JTextField();
        jTextFax = new javax.swing.JTextField();
        jTextTelefonoMovil = new javax.swing.JTextField();
        jTextEmail = new javax.swing.JTextField();
        jLabelDatosContacto = new javax.swing.JLabel();
        jLabelDatosEmpresa = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelCIF = new javax.swing.JLabel();
        jLabelDireccionWeb = new javax.swing.JLabel();
        jLabelDireccion = new javax.swing.JLabel();
        jLabelLocalidad = new javax.swing.JLabel();
        jLabelCP = new javax.swing.JLabel();
        jLabelTelefonoFijo = new javax.swing.JLabel();
        jLabelFax = new javax.swing.JLabel();
        jLabelTelefonoMovil = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jLabelProvincia = new javax.swing.JLabel();
        jLabelError = new javax.swing.JLabel();
        PanelColaboracionesEmpresa = new javax.swing.JPanel();
        btGuardarColaboracionesEmpresa = new javax.swing.JButton();
        btBuscarColaboracionesEmpresa = new javax.swing.JButton();
        btEliminarColaboracionesEmpresa = new javax.swing.JButton();
        jTextConcepto = new javax.swing.JTextField();
        jTextImporte = new javax.swing.JTextField();
        jFormattedTextFecha = new javax.swing.JFormattedTextField();
        jLabelColaboraciones = new javax.swing.JLabel();
        jLabelAñadirColaboracion = new javax.swing.JLabel();
        jLabelImporte = new javax.swing.JLabel();
        jLabelFecha = new javax.swing.JLabel();
        jLabelConcepto = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableColaboracionesEmpresa = new javax.swing.JTable();
        jLabelErrorColaboraciones = new javax.swing.JLabel();
        jLabelFechaInicial = new javax.swing.JLabel();
        jLabelFechaFinal = new javax.swing.JLabel();
        jFormattedTextFechaFinal = new javax.swing.JFormattedTextField();
        jFormattedTextFechaInicial = new javax.swing.JFormattedTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1000, 516));

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));

        PanelDatosEmpresa.setBackground(new java.awt.Color(255, 255, 255));

        btGuardarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/guardar.png"))); // NOI18N

        btLimpiarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/limpiar.png"))); // NOI18N

        jLabelDatosContacto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDatosContacto.setText("Datos de Contacto");

        jLabelDatosEmpresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDatosEmpresa.setText("Datos de Empresa");

        jLabelNombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelNombre.setText("Nombre");

        jLabelCIF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelCIF.setText("CIF");

        jLabelDireccionWeb.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelDireccionWeb.setText("DireccionWeb");

        jLabelDireccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelDireccion.setText("Direccion");

        jLabelLocalidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelLocalidad.setText("Localidad");

        jLabelCP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelCP.setText("Código Postal");

        jLabelTelefonoFijo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelTelefonoFijo.setText("Teléfono Fijo");

        jLabelFax.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelFax.setText("FAX");

        jLabelTelefonoMovil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelTelefonoMovil.setText("Móvil");

        jLabelEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelEmail.setText("E-mail");

        jLabelProvincia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelProvincia.setText("Provincia");

        jLabelError.setForeground(new java.awt.Color(255, 0, 0));
        jLabelError.setText("Mensaje de Error");
        jLabelError.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout PanelDatosEmpresaLayout = new javax.swing.GroupLayout(PanelDatosEmpresa);
        PanelDatosEmpresa.setLayout(PanelDatosEmpresaLayout);
        PanelDatosEmpresaLayout.setHorizontalGroup(
            PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabelDatosEmpresa))
                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabelDatosContacto))
                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                        .addGap(338, 338, 338)
                        .addComponent(btGuardarEmpresa)
                        .addGap(18, 18, 18)
                        .addComponent(btLimpiarEmpresa)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNombre)
                    .addComponent(jLabelCIF)
                    .addComponent(jLabelDireccionWeb)
                    .addComponent(jLabelDireccion)
                    .addComponent(jLabelLocalidad)
                    .addComponent(jLabelCP)
                    .addComponent(jLabelTelefonoFijo)
                    .addComponent(jLabelFax))
                .addGap(67, 67, 67)
                .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextDireccion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDatosEmpresaLayout.createSequentialGroup()
                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                                .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextTelefonoFijo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                    .addComponent(jTextCP, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFax))
                                .addGap(18, 18, 18)
                                .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                                        .addComponent(jLabelEmail)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                                        .addComponent(jLabelTelefonoMovil)
                                        .addGap(19, 19, 19)
                                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                                                .addComponent(jTextEmail)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                                                .addComponent(jTextTelefonoMovil, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                                .addGap(72, 72, 72))))))
                            .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                                .addComponent(jTextLocalidad, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelProvincia)
                                .addGap(23, 23, 23)))
                        .addComponent(jTextProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextDireccionWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextCIF, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(98, 98, 98))
        );
        PanelDatosEmpresaLayout.setVerticalGroup(
            PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDatosEmpresaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabelDatosEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                        .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextCIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCIF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextDireccionWeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelDireccionWeb)))
                    .addComponent(jLabelNombre))
                .addGap(28, 28, 28)
                .addComponent(jLabelDatosContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDireccion)
                    .addComponent(jTextDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelLocalidad)
                            .addComponent(jTextLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCP)))
                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelProvincia))
                        .addGap(30, 30, 30)))
                .addGap(9, 9, 9)
                .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTelefonoFijo)
                            .addComponent(jTextTelefonoFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFax)))
                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextTelefonoMovil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTelefonoMovil))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelEmail))))
                .addGap(18, 18, 18)
                .addComponent(jLabelError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btGuardarEmpresa)
                    .addComponent(btLimpiarEmpresa))
                .addGap(28, 28, 28))
        );

        jTabbedPane4.addTab("Datos", PanelDatosEmpresa);

        PanelColaboracionesEmpresa.setBackground(new java.awt.Color(255, 255, 255));

        btGuardarColaboracionesEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/guardar.png"))); // NOI18N

        btBuscarColaboracionesEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/buscar.png"))); // NOI18N

        btEliminarColaboracionesEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/borrar.png"))); // NOI18N

        jFormattedTextFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        jLabelColaboraciones.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelColaboraciones.setText("Colaboraciones");

        jLabelAñadirColaboracion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelAñadirColaboracion.setText("Añadir Colaboración");

        jLabelImporte.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelImporte.setText("Importe");

        jLabelFecha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelFecha.setText("Fecha");

        jLabelConcepto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelConcepto.setText("Concepto");

        jTableColaboracionesEmpresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Importe", "Fecha", "Concepto", "Confirmado por"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableColaboracionesEmpresa);
        jTableColaboracionesEmpresa.getColumnModel().getColumn(0).setResizable(false);
        jTableColaboracionesEmpresa.getColumnModel().getColumn(1).setResizable(false);

        jLabelErrorColaboraciones.setText("Mensaje de Error");

        jLabelFechaInicial.setText("Fecha Inicial");

        jLabelFechaFinal.setText("Fecha Final");

        jFormattedTextFechaFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        jFormattedTextFechaInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        javax.swing.GroupLayout PanelColaboracionesEmpresaLayout = new javax.swing.GroupLayout(PanelColaboracionesEmpresa);
        PanelColaboracionesEmpresa.setLayout(PanelColaboracionesEmpresaLayout);
        PanelColaboracionesEmpresaLayout.setHorizontalGroup(
            PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelColaboracionesEmpresaLayout.createSequentialGroup()
                .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelColaboracionesEmpresaLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelColaboraciones)
                            .addGroup(PanelColaboracionesEmpresaLayout.createSequentialGroup()
                                .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelAñadirColaboracion)
                                    .addGroup(PanelColaboracionesEmpresaLayout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabelConcepto)
                                            .addComponent(jLabelImporte))
                                        .addGap(18, 18, 18)
                                        .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanelColaboracionesEmpresaLayout.createSequentialGroup()
                                                .addComponent(jTextImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(108, 108, 108)
                                                .addComponent(jLabelFecha)
                                                .addGap(18, 18, 18)
                                                .addComponent(jFormattedTextFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jTextConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addComponent(btGuardarColaboracionesEmpresa))))
                    .addGroup(PanelColaboracionesEmpresaLayout.createSequentialGroup()
                        .addGap(404, 404, 404)
                        .addComponent(jLabelErrorColaboraciones)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelColaboracionesEmpresaLayout.createSequentialGroup()
                .addGap(0, 55, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelColaboracionesEmpresaLayout.createSequentialGroup()
                        .addComponent(btBuscarColaboracionesEmpresa)
                        .addGap(18, 18, 18)
                        .addComponent(btEliminarColaboracionesEmpresa))
                    .addGroup(PanelColaboracionesEmpresaLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabelFechaFinal))
                    .addGroup(PanelColaboracionesEmpresaLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabelFechaInicial))
                    .addComponent(jFormattedTextFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        PanelColaboracionesEmpresaLayout.setVerticalGroup(
            PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelColaboracionesEmpresaLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabelColaboraciones, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(PanelColaboracionesEmpresaLayout.createSequentialGroup()
                        .addComponent(jLabelFechaInicial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelFechaFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btBuscarColaboracionesEmpresa)
                            .addComponent(btEliminarColaboracionesEmpresa))
                        .addGap(0, 49, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelColaboracionesEmpresaLayout.createSequentialGroup()
                        .addComponent(jLabelAñadirColaboracion, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelImporte)
                            .addComponent(jLabelFecha)
                            .addComponent(jTextImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelConcepto)
                            .addComponent(jTextConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btGuardarColaboracionesEmpresa, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(37, 37, 37)
                .addComponent(jLabelErrorColaboraciones)
                .addGap(29, 29, 29))
        );

        jTabbedPane4.addTab("Colaboraciones", PanelColaboracionesEmpresa);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelColaboracionesEmpresa;
    private javax.swing.JPanel PanelDatosEmpresa;
    private javax.swing.JButton btBuscarColaboracionesEmpresa;
    private javax.swing.JButton btEliminarColaboracionesEmpresa;
    private javax.swing.JButton btGuardarColaboracionesEmpresa;
    private javax.swing.JButton btGuardarEmpresa;
    private javax.swing.JButton btLimpiarEmpresa;
    private javax.swing.JFormattedTextField jFormattedTextFecha;
    private javax.swing.JFormattedTextField jFormattedTextFechaFinal;
    private javax.swing.JFormattedTextField jFormattedTextFechaInicial;
    private javax.swing.JLabel jLabelAñadirColaboracion;
    private javax.swing.JLabel jLabelCIF;
    private javax.swing.JLabel jLabelCP;
    private javax.swing.JLabel jLabelColaboraciones;
    private javax.swing.JLabel jLabelConcepto;
    private javax.swing.JLabel jLabelDatosContacto;
    private javax.swing.JLabel jLabelDatosEmpresa;
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelDireccionWeb;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelError;
    private javax.swing.JLabel jLabelErrorColaboraciones;
    private javax.swing.JLabel jLabelFax;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelFechaFinal;
    private javax.swing.JLabel jLabelFechaInicial;
    private javax.swing.JLabel jLabelImporte;
    private javax.swing.JLabel jLabelLocalidad;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelProvincia;
    private javax.swing.JLabel jLabelTelefonoFijo;
    private javax.swing.JLabel jLabelTelefonoMovil;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTableColaboracionesEmpresa;
    private javax.swing.JTextField jTextCIF;
    private javax.swing.JTextField jTextCP;
    private javax.swing.JTextField jTextConcepto;
    private javax.swing.JTextField jTextDireccion;
    private javax.swing.JTextField jTextDireccionWeb;
    private javax.swing.JTextField jTextEmail;
    private javax.swing.JTextField jTextFax;
    private javax.swing.JTextField jTextImporte;
    private javax.swing.JTextField jTextLocalidad;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextProvincia;
    private javax.swing.JTextField jTextTelefonoFijo;
    private javax.swing.JTextField jTextTelefonoMovil;
    // End of variables declaration//GEN-END:variables
}
