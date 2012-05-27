
package Controladores.BolsaDeTrabajo;

import Controladores.ControladorPrincipal;
import JDBC.BeneficiarioJDBC;
import JDBC.C_EmpresaJDBC;
import JDBC.OfertaJDBC;
import JDBC.SectorJDBC;
import Modelo.Demanda;
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
 **	  ControladorDemanda
 **
 ** DESCRIPCION:
 **
 **
 **
 ** DESARROLLADO POR:
 **		Juan Antonio Aranda Ortega
 **
 ** SUPERVISADO POR:
 **
 **
 ** HISTORIA:
 ** 	000 - 26 May 2012 - ARS - Creacion
 *
 *
 ** NOTAS:
 **
 **
 */
public class ControladorDemanda {

	private static ControladorDemanda instancia;
	private VistaBolsaTrabajo vista;

	private ControladorDemanda(VistaBolsaTrabajo pvista){
		/**
		* Establece como ventana padre la pasada como parámetro
		*/
		vista = pvista;

		// Botones
		vista.getNuevaDemanda().getbtGuardar().addActionListener(new ControladorDemanda.ListenerBtGuardarDemanda());
		vista.getNuevaDemanda().getbtLimpiar().addActionListener(new ControladorDemanda.ListenerBtLimpiar());
	
	}

	public static ControladorDemanda getInstance(VistaBolsaTrabajo panelOferta){
		if (instancia == null) instancia = new ControladorDemanda(panelOferta);
		return instancia;
	}

	/* Métodos del controlador */
	public boolean insertarDemanda(Demanda demanda){
		return true;
	}

	public Demanda obtenerDatosDemanda(int oid){
		Demanda demanda = new Demanda();


		return demanda;
	}

	public boolean actualizarDemanda(Demanda demanda){

		return true;
	}
        
	public boolean eliminarDemanda(Demanda demanda){
		boolean exito = true;

		return exito;
	}
/*	public ArrayList<Demanda> obtenerListaDemanda(String tipoBusqueda, String valor){
		ArrayList<Demanda> listaDemandas = new ArrayList<Demanda>();

		try{
			listaDemandas = DemandaJDBC.getInstance().filtrarDemandas(tipoBusqueda,valor);
		}
		catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Error al obtener la lista de demandas:\n"+ex.getMessage());
		}

		return listaDemandas;
	}
*/
	public ArrayList<Demanda> filtrarDemanda(String s){
		ArrayList<Demanda> a = new ArrayList<Demanda>();
		return a;
	}



	/* Clases para ActionListener */
	public class ListenerBtGuardarDemanda implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Guardar Demanda");
			Demanda demanda = new Demanda();	// Se crea el objeto demanda

			demanda.setDescripcionValidaLaboral(vista.getNuevaDemanda().gettaHistoriaLaboral());
			
		//	demanda.setIdSector(SectorJDBC.getInstance().getOID(vista.getNuevaDemanda().getcbSector()));
			try { demanda.setIdBeneficiario(BeneficiarioJDBC.getInstance().obtenerBeneficiario((vista.getNuevaDemanda().getTextNIF())).getOID());
			} catch (SQLException ex){
			}
			demanda.setIdVoluntario(ControladorPrincipal.getInstance().getVoluntario().getOID());

			demanda.setFecha(new Date());	// Fecha actual

			insertarDemanda(demanda);			// Se envia el objeto al controlador
		}
	}

	public class ListenerBtModificarDemanda implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Modificar Demanda");
			Demanda  demanda = new Demanda();		// Se crea el objeto demanda

//			demanda.setOID();
			demanda.setDescripcionValidaLaboral(vista.getNuevaDemanda().gettaHistoriaLaboral());
			
		//	demanda.setIdSector(SectorJDBC.getInstance().getOID(vista.getNuevaDemanda().getcbSector()));
			try { demanda.setIdBeneficiario(BeneficiarioJDBC.getInstance().obtenerBeneficiario((vista.getNuevaDemanda().getTextNIF())).getOID());
			} catch (SQLException ex){
			}
			demanda.setIdVoluntario(ControladorPrincipal.getInstance().getVoluntario().getOID());

			demanda.setFecha(new Date());	// Fecha actual

			insertarDemanda(demanda);
		}
	}

	public class ListenerBtLimpiar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			vista.getNuevaDemanda().setTextNIF("");
			vista.getNuevaDemanda().setTaHistoriaLaboral("");
		}
	}

	public class ListenerBtEliminarDemanda implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Eliminar Demanda");
//			eliminarDemanda(demanda);
		}
	}

}
