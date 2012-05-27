
package Controladores.BolsaDeTrabajo;

import Controladores.ControladorPrincipal;
import Controladores.TestDatos;
import JDBC.C_EmpresaJDBC;
import JDBC.OfertaJDBC;
import Modelo.Oferta;
import Modelo.Sector;
import Vistas.Paneles.BolsaTrabajo.VistaBolsaTrabajo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

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
 *		001 - 27 May 2012 - ARS - Implementacion de action listener
 *
 *
 ** NOTAS:
 **
 **
 */
public class ControladorOferta {

	private static ControladorOferta instancia;
	private VistaBolsaTrabajo vista;
	private Oferta ofertaConsultada;
	ArrayList<Oferta> listaOfertas;

	private ControladorOferta(VistaBolsaTrabajo pvista){
		/* Establece como ventana padre la pasada como parámetro */
		vista = pvista;

		/* ______________  Botones  ______________ */
		// Sector
		vista.getOfertaDatos().getBTGuardarSector().addActionListener(new ListenerBtGuardarSector());
		vista.getOfertaDatos().getBTEliminarSector().addActionListener(new ListenerBtEliminarSector());

		// Nueva Oferta
		vista.getOfertaDatos().getBTGuardar().addActionListener(new ListenerBtGuardarOferta());
		vista.getOfertaDatos().getBTLimpiar().addActionListener(new ListenerBtLimpiar());

		// Modificar Oferta
		vista.getOfertaDatos().getBTModificar().addActionListener(new ListenerBtModificarOferta());
		vista.getOfertaDatos().getBTGuardarCambios().addActionListener(new ListenerBtActualizarOferta());
		vista.getOfertaDatos().getBTEliminar().addActionListener(new ListenerBtEliminarOferta());

		// Buscar Ofertas
		vista.getOfertasBuscar().getBTBuscar().addActionListener(new ListenerBtBuscarOferta());
		vista.getOfertasBuscar().getBTEliminar().addActionListener(new ListenerBtEliminarOferta());
		vista.getOfertasBuscar().getBTConsultar().addActionListener(new ListenerBtConsultarOferta());

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

	public ArrayList<Oferta> obtenerListaOfertas(String CIF, String sector, String antiguedad){
		ArrayList<Oferta> listaOfertas = new ArrayList<Oferta>();

		try{
			listaOfertas = OfertaJDBC.getInstance().filtartOfertas(sector,antiguedad);
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
	public class ListenerBtGuardarSector implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Guardar Sector");
			Sector sector = new Sector();
			sector.setDescripcion(vista.getOfertaDatos().getcbSector());
			insertarSector(sector);
		}
	}

	public class ListenerBtEliminarSector implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Eliminar Sector");
			Sector sector = new Sector();
			eliminarSector(sector);
		}
	}

	public class ListenerBtGuardarOferta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Guardar Oferta");
			Oferta  oferta = new Oferta();		// Se crea el objeto oferta
/*
			oferta.setCualificacionRequerida(vista.getOfertaDatos().gettaCualificacion());
			oferta.setDescripcionOferta(vista.getOfertaDatos().gettaDescripcionOferta());
			oferta.setDuracionContrato(Integer.parseInt(vista.getOfertaDatos().getTextNPuestos()));
			oferta.setPlazasOfertadas(Integer.parseInt(vista.getOfertaDatos().getTextNPuestos()));
			oferta.setTipoContrato(vista.getOfertaDatos().getcbTipoContrato());

//			oferta.setIdSector(SectorJDBC.getInstance().getOID(vista.getOfertaDatos().getcbSector()));
			try { oferta.setIdEmpresa(C_EmpresaJDBC.getInstance().obtenerC_Empresa((vista.getOfertaDatos().getTextCIF())).getOIDEmpresa());
			} catch (SQLException ex){ }
			oferta.setIdVoluntario(ControladorPrincipal.getInstance().getVoluntario().getOID());

			oferta.setFecha(new Date());	// Fecha actual
*/
			insertarOferta(oferta);			// Se envia el objeto al controlador
		}
	}

	public class ListenerBtLimpiar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			vista.getOfertaDatos().setTextCIF("");
			vista.getOfertaDatos().setTextNuevoSector("");
			vista.getOfertaDatos().settaDescripcionOferta("");
			vista.getOfertaDatos().setTextNPuestos("");
			vista.getOfertaDatos().setTextDuracionContrato("");
			vista.getOfertaDatos().settaCualificacion("");
		}
	}

	public class ListenerBtModificarOferta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Modificar Oferta");
			// Poner campos activos
		}
	}

	public class ListenerBtActualizarOferta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Actualizar Oferta");

			ofertaConsultada.setCualificacionRequerida(vista.getOfertaDatos().gettaCualificacion());
			ofertaConsultada.setDescripcionOferta(vista.getOfertaDatos().gettaDescripcionOferta());
			ofertaConsultada.setDuracionContrato(Integer.parseInt(vista.getOfertaDatos().getTextNPuestos()));
			ofertaConsultada.setPlazasOfertadas(Integer.parseInt(vista.getOfertaDatos().getTextNPuestos()));
			ofertaConsultada.setTipoContrato(vista.getOfertaDatos().getcbTipoContrato());
//			ofertaConsultada.setIdSector(SectorJDBC.getInstance().getOID(vista.getOfertaDatos().getcbSector()));
			try { ofertaConsultada.setEmpresa(C_EmpresaJDBC.getInstance().obtenerC_Empresa((vista.getOfertaDatos().getTextCIF())));
			} catch (SQLException ex){
			}
			ofertaConsultada.setVoluntario(ControladorPrincipal.getInstance().getVoluntario());
			ofertaConsultada.setFecha(new Date());	// Fecha actual

			actualizarOferta(ofertaConsultada);			// Se envia el objeto al controlador
		}
	}

	public class ListenerBtEliminarOferta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Eliminar Oferta");
			eliminarOferta(ofertaConsultada);
		}
	}

	public class ListenerBtBuscarOferta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Buscar Oferta");

			String CIFEmpresa = vista.getOfertasBuscar().gettextCIFEmpresa();
			String sectorBusqueda = vista.getOfertasBuscar().getcbSector();
			String antiguedad = vista.getOfertasBuscar().getcbAntiguedad();
			final String[] columnNames = {"CIF", "Razón Social", "Sector", "Fecha de oferta"};
			listaOfertas = obtenerListaOfertas(CIFEmpresa, sectorBusqueda, antiguedad);

			TableModel tableModel = new TableModel() {

				@Override
				public int getRowCount() {
					return listaOfertas.size();
				}

				@Override
				public int getColumnCount() {
					return columnNames.length;
				}

				@Override
				public String getColumnName(int i) {
					return columnNames[i];
				}

				@Override
				public Class<?> getColumnClass(int i) {
					return String.class;
				}

				@Override
				public boolean isCellEditable(int i, int i1) {
					return false;
				}

				@Override
				public Object getValueAt(int fil, int col) {
					switch (col) {
						case 0:
							return listaOfertas.get(fil).getEmpresa().getCIF();
						case 1:
							return listaOfertas.get(fil).getDescripcionOferta();
						case 2:
							return listaOfertas.get(fil).getSector().getDescripcion();
						case 3:
							return TestDatos.formatter.format(listaOfertas.get(fil).getFecha());
					}
					return "";
				}

				@Override
				public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
					throw new UnsupportedOperationException("Not supported yet.");
				}

				@Override
				public void addTableModelListener(TableModelListener l) {
					throw new UnsupportedOperationException("Not supported yet.");
				}

				@Override
				public void removeTableModelListener(TableModelListener l) {
					throw new UnsupportedOperationException("Not supported yet.");
				}

			};

			vista.getOfertasBuscar().gettablaBusquedaOferta().setModel(tableModel);
		}
	}

	public class ListenerBtConsultarOferta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Consultar Oferta");
			if (vista.getOfertasBuscar().gettablaBusquedaOferta().getSelectedRow() != -1) {
                ofertaConsultada = listaOfertas.get(vista.getOfertasBuscar().gettablaBusquedaOferta().getSelectedRow());
                vista.getOfertaDatos().consultarOferta(ofertaConsultada);
				vista.showPanel(VistaBolsaTrabajo.PanelOfertaDatos);		// Revisar esta linea
            }

		}
	}

}
