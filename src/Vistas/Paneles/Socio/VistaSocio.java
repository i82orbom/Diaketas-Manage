/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Paneles.Socio;

import Vistas.BarraDeNavegacion;
import java.awt.CardLayout;

/**
 *
 * @author raphaelcolleau
 */
public class VistaSocio extends javax.swing.JPanel {

    /* identificador de los paneles en el CardLayout */
    public static String panelInicio = "inicio";
    public static String panelBuscar = "buscar";
    public static String panelDatos = "datos";



    /**
     * Creates new form PanelVoluntario
     */
    public VistaSocio() {
        initComponents();
    }

    // getter panel
    public BarraDeNavegacion getBarraDeNavegacion() {
        //System.out.println("Barra de navegacion Vista socio");
        return barraDeNavegacion;
    }

    public PanelSocioInicio getPanelSocioInicio() {
        return panelSocioInicio;
    }

    public PanelSocioDatos getPanelSocioDatos() {
        return panelSocioDatos;
    }

    public PanelSocioBuscar getPanelSocioBuscar() {
        return panelSocioBuscar;
    }
  
    // mostrar un panel con el CardLayoud
    public void showPanel (String panel) {
        CardLayout cardLayout =  (CardLayout)panelSocio.getLayout();
        cardLayout.show(panelSocio, panel);
        System.out.println("ShowPanel vista Socio"); 
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barraDeNavegacion = new Vistas.BarraDeNavegacion();
        panelSocio = new javax.swing.JPanel();
        panelSocioBuscar = new Vistas.Paneles.Socio.PanelSocioBuscar();
        panelSocioInicio = new Vistas.Paneles.Socio.PanelSocioInicio();
        panelSocioDatos = new Vistas.Paneles.Socio.PanelSocioDatos();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setLayout(new java.awt.BorderLayout());

        barraDeNavegacion.setAlignmentX(10.0F);
        add(barraDeNavegacion, java.awt.BorderLayout.PAGE_START);

        panelSocio.setMaximumSize(new java.awt.Dimension(1000, 550));
        panelSocio.setMinimumSize(new java.awt.Dimension(1000, 550));
        panelSocio.setPreferredSize(new java.awt.Dimension(1000, 550));
        panelSocio.setLayout(new java.awt.CardLayout());
        panelSocio.add(panelSocioBuscar, "buscar");
        panelSocio.add(panelSocioInicio, "inicio");
        panelSocio.add(panelSocioDatos, "datos");

        add(panelSocio, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Vistas.BarraDeNavegacion barraDeNavegacion;
    private javax.swing.JPanel panelSocio;
    private Vistas.Paneles.Socio.PanelSocioBuscar panelSocioBuscar;
    private Vistas.Paneles.Socio.PanelSocioDatos panelSocioDatos;
    private Vistas.Paneles.Socio.PanelSocioInicio panelSocioInicio;
    // End of variables declaration//GEN-END:variables

}
