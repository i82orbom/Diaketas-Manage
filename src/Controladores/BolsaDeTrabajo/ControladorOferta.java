
package Controladores.BolsaDeTrabajo;

import Controladores.ControladorBolsaTrabajo;
import Controladores.ControladorErrores;
import Controladores.ControladorPrincipal;
import Controladores.TestDatos;
import JDBC.C_EmpresaJDBC;
import JDBC.OfertaJDBC;
import JDBC.SectorJDBC;
import Modelo.C_Empresa;
import Modelo.Oferta;
import Modelo.Sector;
import Vistas.Paneles.BolsaTrabajo.VistaBolsaTrabajo;
import java.awt.Color;
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
		vista.getOfertaBuscar().getBTBuscar().addActionListener(new ListenerBtBuscarOferta());
		vista.getOfertaBuscar().getBTEliminar().addActionListener(new ListenerBtEliminarOfertaBuscada());
		vista.getOfertaBuscar().getBTConsultar().addActionListener(new ListenerBtConsultarOferta());

	}

	public static ControladorOferta getInstance(VistaBolsaTrabajo panelOferta){
		if (instancia == null) instancia = new ControladorOferta(panelOferta);
		return instancia;
	}

	/* Métodos del controlador */
	public boolean insertarOferta(Oferta oferta){
		try{
			OfertaJDBC.getInstance().insertarOferta(oferta);
		}
		catch (SQLException ex){
			ControladorErrores.mostrarError("Error al insertar una oferta\n"+ex);
			return false;
		}

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
			listaOfertas = OfertaJDBC.getInstance().filtrarOfertas(CIF,sector,antiguedad);
		}
		catch (SQLException ex){
			ControladorErrores.mostrarError("Error al obtener la lista de ofertas:\n"+ex.getMessage());
		}

		return listaOfertas;
	}

	public ArrayList<Oferta> filtrarOfertas(String s){
		ArrayList<Oferta> a = new ArrayList<Oferta>();
		return a;
	}

	public boolean eliminarOferta(Oferta oferta){
		JOptionPane.showConfirmDialog(vista, vista);
		boolean exito = true;

		return exito;
	}

	public boolean insertarSector(Sector sector){
		System.out.print(this);
		try{
			SectorJDBC.getInstance().InsertarSector(sector);
		}
		catch (SQLException ex){
			ControladorErrores.mostrarError("Error al guardar sector:\n"+ex);
		}
		return true;
	}

	public boolean eliminarSector(Sector sector){
		return true;
	}

	public boolean validarDatosOferta(Oferta oferta){
		return true;
	}

	public void limpiarFormulario(){
		vista.getOfertaDatos().setTextoCIF("");
		vista.getOfertaDatos().setTextoNuevoSector("");
		vista.getOfertaDatos().setTextoDescripcionOferta("");
		vista.getOfertaDatos().setTextoNPuestos("");
		vista.getOfertaDatos().setTextoDuracionContrato("");
		vista.getOfertaDatos().setTextoCualificacion("");
		vista.getOfertaDatos().getlabelError().setText("");
	}
	public void setColorLabels(Color c){
		vista.getOfertaDatos().getlabelCIF().setForeground(c);
		vista.getOfertaDatos().getlabelSector().setForeground(c);
		vista.getOfertaDatos().getlabelDescripcionOferta().setForeground(c);
		vista.getOfertaDatos().getlabelNPuestos().setForeground(c);
		vista.getOfertaDatos().getlabelTipoContrato().setForeground(c);
		vista.getOfertaDatos().getlabelDuracionContrato().setForeground(c);
		vista.getOfertaDatos().getlabelCualificacion().setForeground(c);
	}

	/* Clases para ActionListener */
	public class ListenerBtGuardarSector implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
//			System.out.println("Guardar Sector");
			Sector sector = new Sector();
			String sectorNuevo = vista.getOfertaDatos().getTextoNuevoSector();
			if (!TestDatos.isOnlyLetter(sectorNuevo))
				vista.getOfertaDatos().getlabelError().setText("Error en los datos para añadir sector");
			else {
				sector.setDescripcion(sectorNuevo);
				if (insertarSector(sector)) ControladorBolsaTrabajo.getInstance(null).mostrarNuevaOferta();
				else vista.getOfertaDatos().getlabelError().setText("Error al añadir sector");
			}
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
			Oferta oferta = new Oferta();		// Se crean los objetos
			C_Empresa empresa = null;
			boolean exito = true;

			/* ________ Comprobamos los datos _______ */
			setColorLabels(Color.black);
			vista.getOfertaDatos().getlabelError().setText("");

			String CIF = vista.getOfertaDatos().getTextoCIF();
			String descripcion = vista.getOfertaDatos().getTextoDescripcionOferta();
			String duracion = vista.getOfertaDatos().getTextoDuracionContrato();
			String nPuestos = vista.getOfertaDatos().getTextoNPuestos();
			String cualificacion = vista.getOfertaDatos().getTextoCualificacion();

			if (!TestDatos.isCIF(CIF)){
				exito = false; vista.getOfertaDatos().getlabelCIF().setForeground(Color.red);
			}
			if (!TestDatos.isNombre(descripcion)) {
				exito = false; vista.getOfertaDatos().getlabelDescripcionOferta().setForeground(Color.red);
			}
			if (!TestDatos.isOnlyDigit(nPuestos)) {
				exito = false; vista.getOfertaDatos().getlabelNPuestos().setForeground(Color.red);
			}
			if (!TestDatos.isOnlyDigit(duracion)) {
				exito = false; vista.getOfertaDatos().getlabelDuracionContrato().setForeground(Color.red);
			}
			if (!TestDatos.isNombre(cualificacion)) {
				exito = false; vista.getOfertaDatos().getlabelCualificacion().setForeground(Color.red);
			}

			if (!exito){
				vista.getOfertaDatos().getlabelError().setText("Los datos no son válidos");
			}
			else {
				try {
					empresa = C_EmpresaJDBC.getInstance().obtenerC_Empresa(CIF);
				} catch (SQLException ex){
					exito = false; ControladorErrores.mostrarError("Error al consultar empresa:\n"+ex);
				}
				if (empresa==null){
					vista.getOfertaDatos().getlabelError().setText("La empresa no está registrada");
					vista.getOfertaDatos().getlabelCIF().setForeground(Color.red);
					exito = false;
				}

				if (exito){
					oferta.setEmpresa(empresa);
					int sector = vista.getOfertaDatos().getcbSector().getSelectedIndex();
					oferta.setSector(ControladorBolsaTrabajo.getInstance(null).getSectores().get(sector));
					oferta.setDescripcionOferta(descripcion);
					oferta.setDuracionContrato(Integer.parseInt(duracion));
					oferta.setPlazasOfertadas(Integer.parseInt(nPuestos));
					oferta.setTipoContrato(vista.getOfertaDatos().getTextoTipoContrato());
					oferta.setCualificacionRequerida(cualificacion);
					oferta.setVoluntario(ControladorPrincipal.getInstance().getVoluntario());
					oferta.setFecha(new Date());
					if (!insertarOferta(oferta)){			// Se envia el objeto al controlador
						vista.getOfertaDatos().getlabelError().setText("La oferta no ha sido añadida");
					}
					else {
						vista.getOfertaDatos().getlabelError().setText("Se ha añadido una oferta");
					}
				}
			}
//			System.out.println(oferta.getSector().getOID()+": " + oferta.getSector().getDescripcion());
//			System.out.println(oferta.getEmpresa().getCIF()+": " +oferta.getEmpresa().getOID()+": "+ oferta.getEmpresa().getEmail());
		}
	}

	public class ListenerBtLimpiar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			limpiarFormulario();
			setColorLabels(Color.black);
		}
	}

	public class ListenerBtModificarOferta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Modificar Oferta");
			ControladorBolsaTrabajo.getInstance(vista).mostrarModificarOferta(ofertaConsultada);
		}
	}

	public class ListenerBtActualizarOferta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Actualizar Oferta");

			ofertaConsultada.setCualificacionRequerida(vista.getOfertaDatos().getTextoCualificacion());
			ofertaConsultada.setDescripcionOferta(vista.getOfertaDatos().getTextoDescripcionOferta());
			ofertaConsultada.setDuracionContrato(Integer.parseInt(vista.getOfertaDatos().getTextoNPuestos()));
			ofertaConsultada.setPlazasOfertadas(Integer.parseInt(vista.getOfertaDatos().getTextoNPuestos()));
			ofertaConsultada.setTipoContrato(vista.getOfertaDatos().getTextoTipoContrato());
//			ofertaConsultada.setIdSector(SectorJDBC.getInstance().getOID(vista.getOfertaDatos().getcbSector()));
			try { ofertaConsultada.setEmpresa(C_EmpresaJDBC.getInstance().obtenerC_Empresa((vista.getOfertaDatos().getTextoCIF())));
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

			String CIFEmpresa = vista.getOfertaBuscar().gettextoCIFEmpresa();
			int sectorBusquedaPos = vista.getOfertaBuscar().getcbSector().getSelectedIndex();
			String sectorBusqueda;
			if (sectorBusquedaPos == 0) sectorBusqueda = "";
			else sectorBusqueda = ControladorBolsaTrabajo.getInstance(null).getSectores().get(sectorBusquedaPos-1).getOID()+"";
			String antiguedad = vista.getOfertaBuscar().getAntiguedad();

//			System.out.println("CIF: "+CIFEmpresa+" Sector: "+sectorBusqueda+" Antiguedad: "+antiguedad);

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
				}

				@Override
				public void addTableModelListener(TableModelListener l) {
				}

				@Override
				public void removeTableModelListener(TableModelListener l) {
				}

			};

			vista.getOfertaBuscar().gettablaBusquedaOferta().setModel(tableModel);
		}
	}

	public class ListenerBtEliminarOfertaBuscada implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Eliminar Oferta");

			if (vista.getOfertaBuscar().gettablaBusquedaOferta().getSelectedRow() != -1) {
				if (eliminarOferta(listaOfertas.get(vista.getOfertaBuscar().gettablaBusquedaOferta().getSelectedRow()))){
					ControladorErrores.mostrarMensaje("La oferta ha sido borrada");
				}
			}
		}
	}

	public class ListenerBtConsultarOferta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Consultar Oferta");
			if (vista.getOfertaBuscar().gettablaBusquedaOferta().getSelectedRow() != -1) {
                ofertaConsultada = listaOfertas.get(vista.getOfertaBuscar().gettablaBusquedaOferta().getSelectedRow());
				ControladorBolsaTrabajo.getInstance(vista).mostrarConsultarOferta(ofertaConsultada);
			}
		}
	}

}
