
package Controladores;

import Controladores.BolsaDeTrabajo.*;
import Vistas.BarraDeNavegacion;
import Vistas.Paneles.BolsaTrabajo.VistaBolsaTrabajo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 ** NOMBRE CLASE:
 **	  ControladorBeneficiario
 **
 **
 **
 **
 ** DESARROLLADO POR:
 *          Raúl López Jiménez (RLJ)
 **
 **
 ** SUPERVISADO POR:
 *			Antonio Rodríguez Segura
 **
 **
 ** HISTORIA:
 ** 	000 - 21 May 2012 - RLJ - Creacion del controlador
 *		001 - 26 May 2012 - ARS -
 **
 ** NOTAS:
 **
 **
 */
public class ControladorBolsaTrabajo {

    private static ControladorBolsaTrabajo instancia;
    private VistaBolsaTrabajo vista;

    public static ControladorBolsaTrabajo getInstance (VistaBolsaTrabajo panelBolsaTrabajo){
		if(instancia == null){
			instancia = new ControladorBolsaTrabajo(panelBolsaTrabajo);
		}
		return instancia;
    }

    private ControladorBolsaTrabajo(VistaBolsaTrabajo pvista){
        vista = pvista;

        vista.getBarraDeNavigacion().setListener(new ListenerBarraNavigacion());

        this.mostrarVistaInicio();

		ControladorOferta.getInstance(vista);
		ControladorDemanda.getInstance(vista);

        vista.getBolsaTrabajoInicio().anadirListenerbtDemandasEmpleo(new btDemandasInicioListener());
        vista.getBolsaTrabajoInicio().anadirListenerbtOfertasEmpleo(new btOfertasInicioListener());
        vista.getDemandasInicio().anadirListenerbtConsultarDemanda(new btConsultarDemandaListener());
        vista.getDemandasInicio().anadirListenerbtNuevaDemanda(new btNuevaDemandaListener ());
        vista.getOfertasInicio().anadirListenerbtBuscarOfertas(new btBuscarOfertas());
        vista.getOfertasInicio().anadirListenerbtNuevaOferta(new btNuevaOferta());
    }

/* __________________________    IMPLEMENTACION DE MOSTAR VISTAS    ________________*/
    public void mostrarVistaInicio(){
		vista.showPanel(VistaBolsaTrabajo.PanelInicio);
		vista.getBarraDeNavigacion().setTextLabelNivel1("Bolsa de Trabajo");
    }

	// Paneles para demandas
    public void mostrarDemandasInicio(){
        vista.showPanel(VistaBolsaTrabajo.PanelDemandaInicio);
        vista.getBarraDeNavigacion().setTextLabelNivel2("Demandas");
    }

    public void mostarNuevaDemanda(){
        vista.showPanel(VistaBolsaTrabajo.PanelNuevaDemanda);
        vista.getBarraDeNavigacion().setTextLabelNivel3("Nueva Demanda");
    }

    public void mostrarConsultarDemandas(){
        vista.showPanel(VistaBolsaTrabajo.PanelConsultarDemandas);
        vista.getBarraDeNavigacion().setTextLabelNivel3("Consultar Demanda");
    }

    public void mostrarModificarDemanda() {
        vista.showPanel(VistaBolsaTrabajo.PanelModificarDemanda);
        vista.getBarraDeNavigacion().setTextLabelNivel3("Modificar Demanda");
    }

    public void mostrarBuscarDemanda() {
        vista.showPanel(VistaBolsaTrabajo.PanelBuscarDemandas);
        vista.getBarraDeNavigacion().setTextLabelNivel3("Buscar Demanda");
    }

	// Paneles para ofertas
	public void mostrarOfertasInicio(){
        vista.showPanel(VistaBolsaTrabajo.PanelOfertasInicio);
        vista.getBarraDeNavigacion().setTextLabelNivel2("Ofertas");
    }

    public void mostrarNuevaOferta() {
		vista.showPanel(VistaBolsaTrabajo.PanelDatosOferta);
		vista.getBarraDeNavigacion().setTextLabelNivel3("Nueva Oferta");
    }

    public void mostrarConsultarOfertas(){
        vista.showPanel(VistaBolsaTrabajo.PanelDatosOferta);
        vista.getBarraDeNavigacion().setTextLabelNivel3("Consultar Ofertas");
    }

    public void mostrarModificarOferta() {
        vista.showPanel(VistaBolsaTrabajo.PanelDatosOferta);
        vista.getBarraDeNavigacion().setTextLabelNivel3("Modificar Oferta");
    }

	public void mostrarBuscarOferta() {
        vista.showPanel(VistaBolsaTrabajo.PanelBuscarOfertas);
        vista.getBarraDeNavigacion().setTextLabelNivel3("Buscar Oferta");
    }


	/* ____________________________    IMPLEMENTACION DE ACTION LISTENER    ________________________*/

	class btDemandasInicioListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae){
			mostrarDemandasInicio();
		}
	}

	class btConsultarDemandaListener implements ActionListener {

		@Override
		public void actionPerformed (ActionEvent ea){
			mostrarBuscarDemanda();
		}
	}

	class btNuevaDemandaListener implements ActionListener {

		@Override
		public void actionPerformed (ActionEvent ea){
			mostarNuevaDemanda();
		}
	}

	class btOfertasInicioListener implements ActionListener {

		@Override
		public void actionPerformed (ActionEvent ea){
			mostrarOfertasInicio();
		}
	}

	class btBuscarOfertas implements ActionListener {

		@Override
		public void actionPerformed (ActionEvent ea){
			mostrarBuscarOferta();
		}
	}

	class btNuevaOferta implements ActionListener {

		@Override
		public void actionPerformed (ActionEvent ea){
			mostrarNuevaOferta();
		}
	}

	class ListenerBarraNavigacion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equalsIgnoreCase(BarraDeNavegacion.TO_VISTA_INICIAL)) {
				mostrarVistaInicio();
				ControladorPrincipal.getInstance().mostrarVistaInicio();
			}

			if (ae.getActionCommand().equalsIgnoreCase(BarraDeNavegacion.TO_NIVEL1)) {
				mostrarVistaInicio();
			}
			if (ae.getActionCommand().equalsIgnoreCase(BarraDeNavegacion.TO_NIVEL2)) {
				if (vista.getBarraDeNavigacion().getTextLebelNivel2().equalsIgnoreCase("Demandas")){
					mostrarDemandasInicio();
				}
				else
					mostrarOfertasInicio();
			}
		}
	}

}
