/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Vistas.BarraDeNavegacion;
import Vistas.Paneles.Beneficiario.VistaBeneficiario;
import JDBC.BeneficiarioJDBC;
import Modelo.Beneficiario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 ** NOMBRE CLASE:
 **	  ControladorBeneficiario
 **
 ** DESCRIPCION:
 **
 **
 **
 ** DESARROLLADO POR:
 *          Mario Orozco Borrego (MOB)
 **
 **
 ** SUPERVISADO POR:
 **
 **
 ** HISTORIA:
 ** 	000 - 20 May 2012 - MOB - Creacion
 **		001 - 18 May 2012 - ARS - Cambios para añadir beneficiario
 **
 ** NOTAS:
 **
 **
 */
public class ControladorBeneficiario {
	/** PATRON DE DISEÑO SINGLETON */

	private static ControladorBeneficiario instancia;

	public static ControladorBeneficiario getInstance(VistaBeneficiario panelVoluntario){
		if (instancia == null)
			instancia = new ControladorBeneficiario(panelVoluntario);

		return instancia;

	}

	private VistaBeneficiario vista;

	/**
		* Constructor de la clase
		*/
	private ControladorBeneficiario(VistaBeneficiario pvista){

	/**
		* Establece como ventana padre la pasada como parámetro
		*/
	vista = pvista;

		// anadir listener
		vista.getBarraDeNavigacion().setListener(new ListenerBarraNavigacion());


		// al principio mostrar la vista de inicio
		mostrarVistaInicio();

		vista.getPanelInicio().anadirListenerbtBuscarBeneficiario(new btBuscarListener());
		vista.getPanelInicio().anadirListenerbtNuevoBeneficiario(new btNuevoBeneficiarioListener());
	}

	// mostrar la vista que queremos y actualizacion de la barra de navigacion
	private void mostrarVistaInicio(){
		vista.showPanel(VistaBeneficiario.panelInicio);
		vista.getBarraDeNavigacion().setTextLabelNivel1("Beneficiario");
	}

	private void mostrarVistaBuscar(){
		vista.showPanel(VistaBeneficiario.panelBuscar);
		vista.getBarraDeNavigacion().setTextLabelNivel1("Beneficiario");
		vista.getBarraDeNavigacion().setTextLabelNivel2("Buscar");
	}

	private void mostrarVistaNuevoBeneficiario(){
		vista.showPanel(VistaBeneficiario.panelDatos);
		vista.getBarraDeNavigacion().setTextLabelNivel1("Beneficiario");
		vista.getBarraDeNavigacion().setTextLabelNivel2("Nuevo Beneficiario");
	}

	private boolean insertarBeneficiario(String[] datos){

        if (this.comprobarDatos(datos) == false) {
            return false;
        }

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setNIF(datos[Beneficiario.NIF_ID]);
        beneficiario.setNombre(datos[Beneficiario.NOMBRE_ID]);
        beneficiario.setApellidos(datos[Beneficiario.APELLIDOS_ID]);
        try {
            beneficiario.setFechaDENacimiento(TestDatos.formatter.parse(datos[Beneficiario.FECHA_DE_NACIMIENTO_ID]));
        } catch (ParseException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        }
		beneficiario.setDomicilio(datos[Beneficiario.DOMICILIO_ID]);
        beneficiario.setCP(Integer.parseInt(datos[Beneficiario.CP_ID]));
        beneficiario.setLocalidad(datos[Beneficiario.LOCALIDAD_ID]);
        beneficiario.setTelefonoMovil(Integer.parseInt(datos[Beneficiario.TELEFONO_MOVIL_ID]));
        beneficiario.setTelefonoFijo(Integer.parseInt(datos[Beneficiario.TELEFONO_FIJO_ID]));
		beneficiario.setEstadoCivil(datos[Beneficiario.ESTADO_CIVIL_ID]);

		beneficiario.setNacionalidad(datos[Beneficiario.NACIONALIDAD_ID]);
		beneficiario.setNivelDeEstudio(datos[Beneficiario.NIVELESTUDIOS_ID]);
		beneficiario.setObservaciones(datos[Beneficiario.OBSERVACIONES_ID]);
		beneficiario.setOcupacion(datos[Beneficiario.OCUPACION_ID]);
		beneficiario.setProfesion(datos[Beneficiario.PROFESION_ID]);
		beneficiario.setSituacionEconomica(datos[Beneficiario.SITUACION_ECONOMICA_ID]);
		beneficiario.setVivienda(datos[Beneficiario.VIVIENDA_ID]);
		Float alquiler = Float.parseFloat(datos[Beneficiario.VIVIENDA_ALQUILER_ID]);
		beneficiario.setViviendaAlquiler(alquiler);
		beneficiario.setViviendaObservaciones(datos[Beneficiario.VIVIENDA_OBSERVACIONES_ID]);

        try {
            BeneficiarioJDBC.getInstance().anadirBeneficiario(beneficiario);
        } catch (SQLException se) {
            System.err.print(se.getMessage());
            return false;
        }

        return true;

	}

	private boolean comprobarDatos(String[] datos){
		return true;
	}

	//Listener de la barra de navigacion
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
		}
	}

	// listeners de los botones
	class btBuscarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			mostrarVistaBuscar();
		}
	}

	class btNuevoBeneficiarioListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			mostrarVistaNuevoBeneficiario();
		}
	}

}
