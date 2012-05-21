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

    public static String panelInicio = "inicio";
    public static String panelDemandaInicio = "demandasInicio";
    public static String panelOfertasInicio = "ofertasInicio";
    public static String panelConsultarOfertas = "consultarofertas";
    public static String panelConsultarDemandas = "consultardemandas";
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
	
	public PanelConsultarDemanda getConsultarDemanda(){
            return this.panelConsultarDemanda;
        }
	
	public PanelConsultarOferta getConsultarOferta(){
            return this.panelConsultarOferta;
        }
	
	public PanelDemandanteBuscar getDemandanteBuscar(){
            return this.panelDemandanteBuscar;
        }
	
	public PanelDemandasInicio getDemandasInicio(){
            return this.panelDemandasInicio;
        }
	
	public PanelModificarDemanda getModificarDemanda(){
            return this.panelModificarDemanda;
        }
	
	public PanelModificarOferta getModificarOferta(){
            return this.panelModificarOferta;
        }
	
	public PanelNuevaDemanda getNuevaDemanda(){
            return this.panelNuevaDemanda;
        }
	
	public PanelNuevaOferta getNuevaOferta(){
            return this.panelNuevaOferta;
        }
	
	public PanelOfertasBuscar getOfertasBuscar(){
            return this.panelOfertasBuscar;
        }
	
	public PanelOfertasInicio getOfertasInicio(){
            return this.panelOfertasInicio1;
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
        panelConsultarDemanda = new Vistas.Paneles.BolsaTrabajo.PanelConsultarDemanda();
        panelConsultarOferta = new Vistas.Paneles.BolsaTrabajo.PanelConsultarOferta();
        panelDemandanteBuscar = new Vistas.Paneles.BolsaTrabajo.PanelDemandanteBuscar();
        panelModificarDemanda = new Vistas.Paneles.BolsaTrabajo.PanelModificarDemanda();
        panelModificarOferta = new Vistas.Paneles.BolsaTrabajo.PanelModificarOferta();
        panelNuevaDemanda = new Vistas.Paneles.BolsaTrabajo.PanelNuevaDemanda();
        panelNuevaOferta = new Vistas.Paneles.BolsaTrabajo.PanelNuevaOferta();
        panelOfertasBuscar = new Vistas.Paneles.BolsaTrabajo.PanelOfertasBuscar();
        panelDemandasInicio = new Vistas.Paneles.BolsaTrabajo.PanelDemandasInicio();
        panelOfertasInicio1 = new Vistas.Paneles.BolsaTrabajo.PanelOfertasInicio();

        setPreferredSize(new java.awt.Dimension(1000, 600));
        setLayout(new java.awt.BorderLayout());
        add(barraDeNavegacion, java.awt.BorderLayout.PAGE_START);

        panelBolsaTrabajo.setLayout(new java.awt.CardLayout());
        panelBolsaTrabajo.add(panelBolsaTrabajoInicio, "inicio");
        panelBolsaTrabajo.add(panelConsultarDemanda, "consultardemandas");
        panelBolsaTrabajo.add(panelConsultarOferta, "consultarofertas");
        panelBolsaTrabajo.add(panelDemandanteBuscar, "card5");
        panelBolsaTrabajo.add(panelModificarDemanda, "card7");
        panelBolsaTrabajo.add(panelModificarOferta, "card8");
        panelBolsaTrabajo.add(panelNuevaDemanda, "card9");
        panelBolsaTrabajo.add(panelNuevaOferta, "card10");
        panelBolsaTrabajo.add(panelOfertasBuscar, "card11");
        panelBolsaTrabajo.add(panelDemandasInicio, "demandasInicio");
        panelBolsaTrabajo.add(panelOfertasInicio1, "card12");

        add(panelBolsaTrabajo, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Vistas.BarraDeNavegacion barraDeNavegacion;
    private javax.swing.JPanel panelBolsaTrabajo;
    private Vistas.Paneles.BolsaTrabajo.PanelBolsaTrabajoInicio panelBolsaTrabajoInicio;
    private Vistas.Paneles.BolsaTrabajo.PanelConsultarDemanda panelConsultarDemanda;
    private Vistas.Paneles.BolsaTrabajo.PanelConsultarOferta panelConsultarOferta;
    private Vistas.Paneles.BolsaTrabajo.PanelDemandanteBuscar panelDemandanteBuscar;
    private Vistas.Paneles.BolsaTrabajo.PanelDemandasInicio panelDemandasInicio;
    private Vistas.Paneles.BolsaTrabajo.PanelModificarDemanda panelModificarDemanda;
    private Vistas.Paneles.BolsaTrabajo.PanelModificarOferta panelModificarOferta;
    private Vistas.Paneles.BolsaTrabajo.PanelNuevaDemanda panelNuevaDemanda;
    private Vistas.Paneles.BolsaTrabajo.PanelNuevaOferta panelNuevaOferta;
    private Vistas.Paneles.BolsaTrabajo.PanelOfertasBuscar panelOfertasBuscar;
    private Vistas.Paneles.BolsaTrabajo.PanelOfertasInicio panelOfertasInicio1;
    // End of variables declaration//GEN-END:variables
}
