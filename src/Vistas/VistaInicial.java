/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.awt.event.ActionListener;

/**
 *
 * @author psylock
 */
public class VistaInicial extends javax.swing.JPanel {

    /**
     * Creates new form VistaInicial
     */
    public VistaInicial() {
        initComponents();
    }
    
     /** LISTENERS */
    
    public void anadirListenerbtBeneficiario(ActionListener listener){
        this.btBeneficiario.addActionListener(listener);
    }

    public void anadirListenerbtBolsaTrabajo(ActionListener listener){
        this.btBolsaTrabajo.addActionListener(listener);
    }
    
    public void anadirListenerbtSocio(ActionListener listener){
        this.btSocio.addActionListener(listener);
    }
    
    public void anadirListenerbtVoluntario(ActionListener listener) {
        this.btVoluntario.addActionListener(listener);
    }
    
    public void anadirListenerbtDesconectase(ActionListener listener) {
        this.btDesconectarse.addActionListener(listener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btSocio = new javax.swing.JButton();
        btBeneficiario = new javax.swing.JButton();
        btVoluntario = new javax.swing.JButton();
        btBolsaTrabajo = new javax.swing.JButton();
        btDesconectarse = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btSocio.setBackground(new java.awt.Color(255, 255, 255));
        btSocio.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        btSocio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/socio.png"))); // NOI18N
        btSocio.setText("COLABORADORES");
        btSocio.setToolTipText("Acceso a Colaboradores");
        btSocio.setActionCommand("entrarAPanelSocios");
        btSocio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btSocio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSocio.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btSocio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSocioActionPerformed(evt);
            }
        });
        add(btSocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 250, 190));

        btBeneficiario.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        btBeneficiario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/bene.png"))); // NOI18N
        btBeneficiario.setText("BENEFICIARIOS");
        btBeneficiario.setActionCommand("entrarAPanelBeneficiarios");
        btBeneficiario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btBeneficiario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(btBeneficiario, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, 250, 190));

        btVoluntario.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        btVoluntario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/imgVolunt.jpg"))); // NOI18N
        btVoluntario.setText("VOLUNTARIOS");
        btVoluntario.setActionCommand("entrarAPanelVoluntarios");
        btVoluntario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btVoluntario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(btVoluntario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 250, 190));

        btBolsaTrabajo.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        btBolsaTrabajo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/imgBolsa.jpg"))); // NOI18N
        btBolsaTrabajo.setText("BOLSA DE TRABAJO");
        btBolsaTrabajo.setActionCommand("entrarAPanelBolsaTrabajo");
        btBolsaTrabajo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btBolsaTrabajo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(btBolsaTrabajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 330, 250, 190));

        btDesconectarse.setText("Desconectarse");
        add(btDesconectarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 30, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSocioActionPerformed
        
    }//GEN-LAST:event_btSocioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBeneficiario;
    private javax.swing.JButton btBolsaTrabajo;
    private javax.swing.JButton btDesconectarse;
    private javax.swing.JButton btSocio;
    private javax.swing.JButton btVoluntario;
    // End of variables declaration//GEN-END:variables
}
