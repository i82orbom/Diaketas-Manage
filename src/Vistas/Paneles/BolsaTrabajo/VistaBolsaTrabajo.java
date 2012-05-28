/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Paneles.BolsaTrabajo;

import Vistas.BarraDeNavegacion;
import java.awt.CardLayout;

/**
 *
 * @author raul
 */
public class VistaBolsaTrabajo extends javax.swing.JPanel {

	public static String PanelInicio = "inicio";
	public static String PanelDemandaInicio = "demandaInicio";
	public static String PanelDemandaBuscar = "demandaBuscar";
	public static String PanelDemandaDatos = "demandaDatos";
	public static String PanelOfertaInicio = "ofertaInicio";
	public static String PanelOfertaBuscar = "ofertaBuscar";
	public static String PanelOfertaDatos = "ofertaDatos";

	/**
	 * Creates new form VistaBolsaTrabajo
	 */
	public VistaBolsaTrabajo() {
		initComponents();
	}

	public void showPanel (String panel) {
		CardLayout cardLayout =  (CardLayout)panelBolsaTrabajo.getLayout();
		cardLayout.show(panelBolsaTrabajo, panel);
	}

	public BarraDeNavegacion getBarraDeNavigacion() {
		return this.barraDeNavegacion;
	}

	public PanelBolsaTrabajoInicio getBolsaTrabajoInicio(){
		return this.panelBolsaTrabajoInicio;
	}

	/* ____ Paneles para ofertas ____ */
	public PanelOfertaInicio getOfertaInicio(){
		return this.panelOfertaInicio;
	}

	public PanelOfertaBuscar getOfertaBuscar(){
		return this.panelOfertaBuscar;
	}

	public PanelOfertaDatos getOfertaDatos(){
		return this.panelOfertaDatos;
	}

	/* ____ Paneles para demandas ____ */
	public PanelDemandaInicio getDemandasInicio(){
		return this.panelDemandaInicio;
	}

	public PanelDemandaDatos getDemandaDatos(){
		return this.panelDemandaDatos;
	}

	public PanelDemandaBuscar getDemandaBuscar(){
		return this.panelDemandaBuscar;
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
        panelBolsaTrabajo = new javax.swing.JPanel();
        panelBolsaTrabajoInicio = new Vistas.Paneles.BolsaTrabajo.PanelBolsaTrabajoInicio();
        panelDemandaInicio = new Vistas.Paneles.BolsaTrabajo.PanelDemandaInicio();
        panelDemandaBuscar = new Vistas.Paneles.BolsaTrabajo.PanelDemandaBuscar();
        panelDemandaDatos = new Vistas.Paneles.BolsaTrabajo.PanelDemandaDatos();
        panelOfertaInicio = new Vistas.Paneles.BolsaTrabajo.PanelOfertaInicio();
        panelOfertaBuscar = new Vistas.Paneles.BolsaTrabajo.PanelOfertaBuscar();
        panelOfertaDatos = new Vistas.Paneles.BolsaTrabajo.PanelOfertaDatos();

        setPreferredSize(new java.awt.Dimension(1000, 600));
        setLayout(new java.awt.BorderLayout());
        add(barraDeNavegacion, java.awt.BorderLayout.PAGE_START);

        panelBolsaTrabajo.setLayout(new java.awt.CardLayout());
        panelBolsaTrabajo.add(panelBolsaTrabajoInicio, "inicio");
        panelBolsaTrabajo.add(panelDemandaInicio, "demandaInicio");
        panelBolsaTrabajo.add(panelDemandaBuscar, "demandaBuscar");
        panelBolsaTrabajo.add(panelDemandaDatos, "demandaDatos");
        panelBolsaTrabajo.add(panelOfertaInicio, "ofertaInicio");
        panelBolsaTrabajo.add(panelOfertaBuscar, "ofertaBuscar");
        panelBolsaTrabajo.add(panelOfertaDatos, "ofertaDatos");

        add(panelBolsaTrabajo, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Vistas.BarraDeNavegacion barraDeNavegacion;
    private javax.swing.JPanel panelBolsaTrabajo;
    private Vistas.Paneles.BolsaTrabajo.PanelBolsaTrabajoInicio panelBolsaTrabajoInicio;
    private Vistas.Paneles.BolsaTrabajo.PanelDemandaInicio panelDemandaInicio;
    private Vistas.Paneles.BolsaTrabajo.PanelDemandaBuscar panelDemandaBuscar;
    private Vistas.Paneles.BolsaTrabajo.PanelDemandaDatos panelDemandaDatos;
    private Vistas.Paneles.BolsaTrabajo.PanelOfertaInicio panelOfertaInicio;
    private Vistas.Paneles.BolsaTrabajo.PanelOfertaBuscar panelOfertaBuscar;
    private Vistas.Paneles.BolsaTrabajo.PanelOfertaDatos panelOfertaDatos;
    // End of variables declaration//GEN-END:variables
}
