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
        String[] datos = new String[24];

        datos[C_Empresa.NOMBRE_ID] = jTextNombre.getText();
        datos[C_Empresa.CIF_ID] = jTextCIF.getText();
        datos[C_Empresa.DIRECCION_WEB_ID] = jTextDireccionWeb.getText();
        datos[C_Empresa.TELEFONO1_ID] = jTextTelefonoFijo.getText();
        datos[C_Empresa.TELEFONO2_ID] = jTextTelefonoMovil.getText();
        datos[C_Empresa.DIRECCION_ID] = jTextDireccion.getText();
        datos[C_Empresa.LOCALIDAD_ID] = jTextLocalidad.getText();
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
        cbFechaFinalEmpresa = new javax.swing.JComboBox();
        cbFechaInicialEmpresa = new javax.swing.JComboBox();
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

        setBackground(new java.awt.Color(255, 255, 255));

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

        jLabelError.setText("Mensaje de Error");

        javax.swing.GroupLayout PanelDatosEmpresaLayout = new javax.swing.GroupLayout(PanelDatosEmpresa);
        PanelDatosEmpresa.setLayout(PanelDatosEmpresaLayout);
        PanelDatosEmpresaLayout.setHorizontalGroup(
            PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDatosEmpresaLayout.createSequentialGroup()
                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextDireccion)
                            .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                                .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextTelefonoFijo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                            .addComponent(jTextCP, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFax))
                                        .addGap(18, 18, 18)
                                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                                                .addComponent(jLabelTelefonoMovil)
                                                .addGap(19, 19, 19)
                                                .addComponent(jTextTelefonoMovil, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                                                .addComponent(jLabelEmail)
                                                .addGap(19, 19, 19)
                                                .addComponent(jTextEmail))))
                                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                                        .addComponent(jTextLocalidad, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelProvincia)
                                        .addGap(23, 23, 23)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(98, 98, 98))
                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextDireccionWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextCIF, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabelDatosEmpresa))
                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabelDatosContacto))
                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(jLabelError)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                    .addGap(445, 445, 445)
                    .addComponent(btGuardarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btLimpiarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(388, Short.MAX_VALUE)))
        );
        PanelDatosEmpresaLayout.setVerticalGroup(
            PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDatosEmpresaLayout.createSequentialGroup()
                .addGap(42, 42, 42)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelDatosContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
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
                .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextTelefonoMovil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTelefonoMovil))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelEmail))
                        .addGap(120, 120, 120))
                    .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTelefonoFijo)
                            .addComponent(jTextTelefonoFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFax))
                        .addGap(39, 39, 39)
                        .addComponent(jLabelError)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelDatosEmpresaLayout.createSequentialGroup()
                    .addGap(376, 376, 376)
                    .addGroup(PanelDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btLimpiarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btGuardarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addContainerGap(61, Short.MAX_VALUE)))
        );

        jTabbedPane4.addTab("Datos", PanelDatosEmpresa);

        PanelColaboracionesEmpresa.setBackground(new java.awt.Color(255, 255, 255));

        btGuardarColaboracionesEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/guardar.png"))); // NOI18N

        btBuscarColaboracionesEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/buscar.png"))); // NOI18N

        btEliminarColaboracionesEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/borrar.png"))); // NOI18N

        cbFechaFinalEmpresa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fecha Final" }));

        cbFechaInicialEmpresa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fecha inicial" }));

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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
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
                            .addComponent(jLabelAñadirColaboracion)))
                    .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelColaboracionesEmpresaLayout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelConcepto)
                                .addComponent(jLabelImporte))
                            .addGap(18, 18, 18)
                            .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PanelColaboracionesEmpresaLayout.createSequentialGroup()
                                    .addComponent(jTextImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(190, 190, 190)
                                    .addComponent(jLabelFecha)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jTextConcepto)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelColaboracionesEmpresaLayout.createSequentialGroup()
                            .addGap(52, 52, 52)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelColaboracionesEmpresaLayout.createSequentialGroup()
                        .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btBuscarColaboracionesEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGuardarColaboracionesEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btEliminarColaboracionesEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbFechaInicialEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFechaFinalEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
            .addGroup(PanelColaboracionesEmpresaLayout.createSequentialGroup()
                .addGap(369, 369, 369)
                .addComponent(jLabelErrorColaboraciones)
                .addContainerGap())
        );
        PanelColaboracionesEmpresaLayout.setVerticalGroup(
            PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelColaboracionesEmpresaLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(cbFechaInicialEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbFechaFinalEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(PanelColaboracionesEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btBuscarColaboracionesEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEliminarColaboracionesEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(btGuardarColaboracionesEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(PanelColaboracionesEmpresaLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabelColaboraciones, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
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
                    .addComponent(jTextConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jLabelErrorColaboraciones)
                .addGap(60, 60, 60))
        );

        jTabbedPane4.addTab("Colaboraciones", PanelColaboracionesEmpresa);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JComboBox cbFechaFinalEmpresa;
    private javax.swing.JComboBox cbFechaInicialEmpresa;
    private javax.swing.JFormattedTextField jFormattedTextFecha;
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
