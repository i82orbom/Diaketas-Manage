
package Controladores.BolsaDeTrabajo;

import Controladores.ControladorPrincipal;
import JDBC.C_EmpresaJDBC;
import JDBC.OfertaJDBC;
import JDBC.SectorJDBC;
import Modelo.Oferta;
import Modelo.Sector;
import Vistas.Paneles.BolsaTrabajo.VistaBolsaTrabajo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
 **		Antonio Rodríguez Segura
 **
 ** SUPERVISADO POR:
 **
 **
 ** HISTORIA:
 ** 	000 - 24 May 2012 - ARS - Creacion
 *
 *
 ** NOTAS:
 **
 **
 */
public class ControladorOferta {

	private static ControladorOferta instancia;
	private VistaBolsaTrabajo vista;

	private ControladorOferta(VistaBolsaTrabajo pvista){
		/**
		* Establece como ventana padre la pasada como parámetro
		*/
		vista = pvista;

		// Botones
		vista.getDatosOferta().getBTGuardar().addActionListener(new ListenerBtGuardarOferta());
		vista.getDatosOferta().getBTGuardarSector().addActionListener(new ListenerBtGuardarSector());
		vista.getDatosOferta().getBTLimpiar().addActionListener(new ListenerBtLimpiar());
		vista.getDatosOferta().getBTEliminarSector().addActionListener(new ListenerBtEliminarSector());
	}

	public static ControladorOferta getInstance(VistaBolsaTrabajo panelOferta){
		if (instancia == null) instancia = new ControladorOferta(panelOferta);
		return instancia;
	}

	/* Métodos del controlador */
	public boolean insertarOferta(Oferta oferta){
		return true;
	}

	public Oferta obtenerDatosOferta(int oid){
		Oferta oferta = new Oferta();


		return oferta;
	}

	public boolean actualizarOferta(Oferta of){

		return true;
	}

	public ArrayList<Oferta> obtenerListaOfertas(String tipoBusqueda, String valor){
		ArrayList<Oferta> listaOfertas = new ArrayList<Oferta>();

		try{
			listaOfertas = OfertaJDBC.getInstance().filtartOfertas(tipoBusqueda,valor);
		}
		catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Error al obtener la lista de ofertas:\n"+ex.getMessage());
		}

		return listaOfertas;
	}

	public ArrayList<Oferta> filtrarOfertas(String s){
		ArrayList<Oferta> a = new ArrayList<Oferta>();
		return a;
	}

	public boolean eliminarOferta(Oferta oferta){
		boolean exito = true;

		return exito;
	}

	public boolean insertarSector(Sector sector){
		return true;
	}

	public boolean eliminarSector(Sector sector){
		return true;
	}

	public boolean validarDatosOferta(Oferta oferta){
		return true;
	}

	/* Clases para ActionListener */
	public class ListenerBtGuardarOferta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Guardar Oferta");
			Oferta  oferta = new Oferta();		// Se crea el objeto oferta

			oferta.setCualificacionRequerida(vista.getDatosOferta().gettaCualificacion());
			oferta.setDescripcionOferta(vista.getDatosOferta().gettaDescripcionOferta());
			oferta.setDuracionContrato(Integer.parseInt(vista.getDatosOferta().getTextNPuestos()));
			oferta.setPlazasOfertadas(Integer.parseInt(vista.getDatosOferta().getTextNPuestos()));
			oferta.setTipoContrato(vista.getDatosOferta().getcbTipoContrato());

//			oferta.setIdSector(SectorJDBC.getInstance().getOID(vista.getDatosOferta().getcbSector()));
			try { oferta.setIdEmpresa(C_EmpresaJDBC.getInstance().obtenerC_Empresa((vista.getDatosOferta().getTextCIF())).getOIDEmpresa());
			} catch (SQLException ex){ }
			oferta.setIdVoluntario(ControladorPrincipal.getInstance().getVoluntario().getOID());

			oferta.setFecha(new Date());	// Fecha actual

			insertarOferta(oferta);			// Se envia el objeto al controlador
		}
	}

	public class ListenerBtModificarOferta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Modificar Oferta");
			Oferta  oferta = new Oferta();		// Se crea el objeto oferta

//			oferta.setOID();
			oferta.setCualificacionRequerida(vista.getDatosOferta().gettaCualificacion());
			oferta.setDescripcionOferta(vista.getDatosOferta().gettaDescripcionOferta());
			oferta.setDuracionContrato(Integer.parseInt(vista.getDatosOferta().getTextNPuestos()));
			oferta.setPlazasOfertadas(Integer.parseInt(vista.getDatosOferta().getTextNPuestos()));
			oferta.setTipoContrato(vista.getDatosOferta().getcbTipoContrato());
//			oferta.setIdSector(SectorJDBC.getInstance().getOID(vista.getDatosOferta().getcbSector()));
			try { oferta.setIdEmpresa(C_EmpresaJDBC.getInstance().obtenerC_Empresa((vista.getDatosOferta().getTextCIF())).getOIDEmpresa());
			} catch (SQLException ex){
			}
			oferta.setIdVoluntario(ControladorPrincipal.getInstance().getVoluntario().getOID());

			oferta.setFecha(new Date());	// Fecha actual

			insertarOferta(oferta);			// Se envia el objeto al controlador
		}
	}

	public class ListenerBtLimpiar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			vista.getDatosOferta().setTextCIF("");
			vista.getDatosOferta().setTextNuevoSector("");
			vista.getDatosOferta().settaDescripcionOferta("");
			vista.getDatosOferta().setTextNPuestos("");
			vista.getDatosOferta().setTextDuracionContrato("");
			vista.getDatosOferta().settaCualificacion("");
		}
	}

	public class ListenerBtEliminarOferta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Eliminar Oferta");
//			eliminarOferta(oferta);
		}
	}

	public class ListenerBtGuardarSector implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Guardar Sector");
			Sector sector = new Sector();
			sector.setDescripcion(vista.getDatosOferta().getcbSector());
			insertarSector(sector);
		}
	}

	public class ListenerBtEliminarSector implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Eliminar");
		}
	}

}
