
package Controladores.BolsaDeTrabajo;

import Modelo.Oferta;
import Modelo.Sector;
import Vistas.BarraDeNavegacion;
import Vistas.Paneles.BolsaTrabajo.VistaBolsaTrabajo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

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
		vista.getNuevaOferta().getBTGuardar().addActionListener(new ListenerBtGuardarOferta());
		vista.getNuevaOferta().getBTGuardarSector().addActionListener(new ListenerBtGuardarSector());
		vista.getNuevaOferta().getBTLimpiar().addActionListener(new ListenerBtLimpiar());
		vista.getNuevaOferta().getBTEliminarSector().addActionListener(new ListenerBtEliminarSector());
	}

	public static ControladorOferta getInstance(VistaBolsaTrabajo panelOferta){
		if (instancia == null) instancia = new ControladorOferta(panelOferta);
		return instancia;
	}

	public boolean insertarOferta(String[] s){
		return true;
	}

	public boolean obtenerDatosOferta(Oferta of){
		return true;
	}

	public boolean actualizarOferta(String[] s, Oferta of){

		return true;
	}

	public ArrayList<Oferta> obtenerListaOfertas(String tipoBusqueda, String valor){
		ArrayList<Oferta> listaOfertas = new ArrayList<Oferta>();

/*		try{
			listaOfertas = OfertaJDBC.getInstance().filtartOfertas(tipoBusqueda,valor);
		}
		catch (SQLException ex){

		}
*/
		return listaOfertas;
	}

	public ArrayList<Oferta> filtrarOfertas(String s	){
		ArrayList<Oferta> a = new ArrayList<Oferta>();
		return a;
	}

	public boolean eliminarOferta(Oferta oferta){
		boolean exito = true;

		return exito;
	}

	public boolean insertarSector(String nombre){
		Sector temp = new Sector();
		temp.setDescripcion(nombre);

		return true;
	}

	public boolean eliminarSector(Sector sector){
		return true;
	}

	/* Clases para ActionListener */
	public class ListenerBtGuardarOferta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Guardar Oferta");
		}
	}

	public class ListenerBtGuardarSector implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Guardar Sector");
		}
	}

	public class ListenerBtLimpiar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Limpiar Formulario");
		}
	}

	public class ListenerBtEliminarSector implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Eliminar");
		}
	}

}
