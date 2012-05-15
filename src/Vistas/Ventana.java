/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Vistas.Paneles.Beneficiario.VistaBeneficiario;
import Vistas.Paneles.Voluntario.VistaVoluntario;
import java.awt.CardLayout;

/**
 *
 * @author psylock, Raphael Colleau
 */
public class Ventana extends javax.swing.JFrame {

    /* identificador de los paneles en el CardLayout 
       anadir una linea aqui y poner, en el design el nombre en el parametro CardName*/ 
    public static String panelLogin = "login";
    public static String panelInicio = "inicio";
    public static String panelVoluntario = "voluntario";
    public static String panelBeneficiario = "beneficiario";
    
    /**
     * Creates new form Raiz
     */
    public Ventana() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    // mostrar un panel con el CardLayoud
    public void showPanel (String panel) {
        CardLayout cardLayout =  (CardLayout)getContentPane().getLayout();
        cardLayout.show(getContentPane(), panel);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vistaLogin = new Vistas.VistaLogin();
        vistaInicial = new Vistas.VistaInicial();
        vistaVoluntario = new Vistas.Paneles.Voluntario.VistaVoluntario();
        vistaBeneficiario = new Vistas.Paneles.Beneficiario.VistaBeneficiario();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Diaketas");
        setBackground(new java.awt.Color(135, 170, 235));
        setBounds(new java.awt.Rectangle(0, 0, 1000, 600));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setName("Diaketas");
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 650));
        getContentPane().setLayout(new java.awt.CardLayout());
        getContentPane().add(vistaLogin, "login");
        getContentPane().add(vistaInicial, "inicio");
        getContentPane().add(vistaVoluntario, "voluntario");
        getContentPane().add(vistaBeneficiario, "beneficiario");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void initialize(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("default".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // getter vista
    public VistaVoluntario getPanelVoluntario() {
        return this.vistaVoluntario;
    }

    public VistaInicial getVistaInicial() {
        return this.vistaInicial;
    }

    public VistaLogin getVistaLogin() {
        return this.vistaLogin;
    }
    
    public VistaBeneficiario getVistaBeneficiario(){
        return this.vistaBeneficiario;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Vistas.Paneles.Beneficiario.VistaBeneficiario vistaBeneficiario;
    private Vistas.VistaInicial vistaInicial;
    private Vistas.VistaLogin vistaLogin;
    private Vistas.Paneles.Voluntario.VistaVoluntario vistaVoluntario;
    // End of variables declaration//GEN-END:variables
}
