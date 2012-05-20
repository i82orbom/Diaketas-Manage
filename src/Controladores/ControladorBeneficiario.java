
package Controladores;

import Controladores.Voluntario.ControladorVoluntario;
import Vistas.BarraDeNavegacion;
import Vistas.Paneles.Beneficiario.VistaBeneficiario;
import JDBC.BeneficiarioJDBC;
import Modelo.Beneficiario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
 ** 	000 - 20 Mar 2012 - MOB - Creacion
 **		001 - 18 May 2012 - ARS - Cambios para añadir beneficiario
 *		002 - 20 May 2012 - ARS - Comprobar datos del beneficiario
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

		vista.getPanelDatos().getBtGuardar().addActionListener(new btGuardarBeneficiarioListener());
        vista.getPanelDatos().getBtBorrar().addActionListener(new btBorrarBeneficiarioListener());

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
			JOptionPane.showMessageDialog(null, "Error al añadir beneficiario:\n"+se.getMessage());
            System.err.println(se.getMessage());
            return false;
        }

        return true;
	}

	private boolean comprobarDatos(String[] datos){
		boolean validos = true;

		if (!TestDatos.isNombre(datos[Beneficiario.NOMBRE_ID])){
			vista.getPanelDatos().setColorLabelNombre(Color.red);
			validos = false;
		}
		if (!TestDatos.isNombre(datos[Beneficiario.APELLIDOS_ID])){
			vista.getPanelDatos().setColorLabelApellidos(Color.red);
			validos = false;
		}
		if (!TestDatos.isDNI(datos[Beneficiario.NIF_ID])){
			vista.getPanelDatos().setColorLabelNIF(Color.red);
			validos = false;
		}
		if (!TestDatos.isFecha(datos[Beneficiario.FECHA_DE_NACIMIENTO_ID])){
			vista.getPanelDatos().setColorLabelFechaNacimiento(Color.red);
			validos = false;
		}
		if (!TestDatos.isNombre(datos[Beneficiario.LUGAR_NACIMIENTO_ID])){
			vista.getPanelDatos().setColorLabelLugarNacimiento(Color.red);
			validos = false;
		}
		if (!TestDatos.isNombre(datos[Beneficiario.NACIONALIDAD_ID])){
			vista.getPanelDatos().setColorLabelNacionalidad(Color.red);
			validos = false;
		}
		if (!TestDatos.isNombre(datos[Beneficiario.PROFESION_ID])){
			vista.getPanelDatos().setColorLabelProfesion(Color.red);
			validos = false;
		}
		if (!TestDatos.isNombre(datos[Beneficiario.OCUPACION_ID])){
			vista.getPanelDatos().setColorLabelOcupacion(Color.red);
			validos = false;
		}
		if (!TestDatos.isTelefonoOFax(datos[Beneficiario.TELEFONO_FIJO_ID])){
			vista.getPanelDatos().setColorLabelTelefonoFijo(Color.red);
			validos = false;
		}
		if (!TestDatos.isTelefonoOFax(datos[Beneficiario.TELEFONO_MOVIL_ID])){
			vista.getPanelDatos().setColorLabelTelefonoMovil(Color.red);
			validos = false;
		}
		if (!TestDatos.isDomicilio(datos[Beneficiario.DOMICILIO_ID])){
			vista.getPanelDatos().setColorLabelDomicilio(Color.red);
			validos = false;
		}
		if (!TestDatos.isNombre(datos[Beneficiario.LOCALIDAD_ID])){
			vista.getPanelDatos().setColorLabelLocalidad(Color.red);
			validos = false;
		}
		if (!TestDatos.isCodigoPostal(datos[Beneficiario.CP_ID])){
			vista.getPanelDatos().setColorLabelCP(Color.red);
			validos = false;
		}
		if (!TestDatos.isNombre(datos[Beneficiario.OBSERVACIONES_ID])){
			vista.getPanelDatos().setColorLabelObservaciones(Color.red);
			validos = false;
		}
		if (!TestDatos.isNombre(datos[Beneficiario.SITUACION_ECONOMICA_ID])){
			vista.getPanelDatos().setColorLabelSituacionEconomica(Color.red);
			validos = false;
		}
		if (!TestDatos.isOnlyDigit(datos[Beneficiario.VIVIENDA_ALQUILER_ID])){
			vista.getPanelDatos().setColorLabelViviendaPrecio(Color.red);
			validos = false;
		}
		if (!TestDatos.isNombre(datos[Beneficiario.VIVIENDA_OBSERVACIONES_ID])){
			vista.getPanelDatos().setColorLabelViviendaEspecificacion(Color.red);
			validos = false;
		}

		return validos;
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

	// Listeners de los botones
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

	class btBorrarBeneficiarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            vista.getPanelDatos().limpiarFormulario();
        }
    }

	class btGuardarBeneficiarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
			String[] datos = vista.getPanelDatos().getDatosPersonales();
			boolean exito = insertarBeneficiario(datos);

			if (exito) {
				vista.getPanelDatos().setTextLabelError("Beneficiario añadido correctamente.");
			} else {
				vista.getPanelDatos().setTextLabelError("El beneficiario no ha sido añadido.");
			}
        }

	}

}
