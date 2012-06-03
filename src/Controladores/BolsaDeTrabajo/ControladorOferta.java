
package Controladores.BolsaDeTrabajo;

import Controladores.ControladorBolsaTrabajo;
import Controladores.ControladorErrores;
import Controladores.ControladorPrincipal;
import Controladores.TestDatos;
import JDBC.C_EmpresaJDBC;
import JDBC.DemandaJDBC;
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
	private ArrayList<Oferta> listaOfertas;
	String[] columnNames = {"CIF", "Razón Social", "Sector", "Fecha de oferta"};

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

	/*_____ Métodos privados _____*/
	private void limpiarFormulario(){
		vista.getOfertaDatos().setTextoCIF("");
		vista.getOfertaDatos().setTextoNuevoSector("");
		vista.getOfertaDatos().setTextoDescripcionOferta("");
		vista.getOfertaDatos().setTextoNPuestos("");
		vista.getOfertaDatos().setTextoDuracionContrato("");
		vista.getOfertaDatos().setTextoCualificacion("");
		vista.getOfertaDatos().getlabelError().setText("");
	}
	private void setColorLabels(Color c){
		vista.getOfertaDatos().getlabelCIF().setForeground(c);
		vista.getOfertaDatos().getlabelSector().setForeground(c);
		vista.getOfertaDatos().getlabelDescripcionOferta().setForeground(c);
		vista.getOfertaDatos().getlabelNPuestos().setForeground(c);
		vista.getOfertaDatos().getlabelTipoContrato().setForeground(c);
		vista.getOfertaDatos().getlabelDuracionContrato().setForeground(c);
		vista.getOfertaDatos().getlabelCualificacion().setForeground(c);
	}

	private void actualizarTablaOfertas(){
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


	/*______ Métodos del controlador ______*/
	public void insertarSector(Sector sector){
		try{
			SectorJDBC.getInstance().InsertarSector(sector);
			vista.getOfertaDatos().getlabelError().setText("El nuevo sector ha sido añadido");
		}catch (SQLException ex){
			ControladorErrores.mostrarError("Error al guardar sector:\n"+ex);
			vista.getOfertaDatos().getlabelError().setText("El sector no ha sido añadido");
		}

		try {
			vista.getOfertaDatos().getcbSector().removeAllItems();
			ArrayList<Sector> sectores = SectorJDBC.getInstance().ListadoSectores();
			for (int i=0;i<sectores.size();i++)
				vista.getOfertaDatos().getcbSector().addItem(sectores.get(i).getDescripcion());
		}catch (SQLException ex){ ControladorErrores.mostrarAlerta("Error al Obtener los sectores:\n"+ex); }
	}

	public void eliminarSector(String desc){
		Sector sector=null;
		boolean tieneOfertas=false, tieneDemandas=false;
		try {
			sector = SectorJDBC.getInstance().ConsultarSector(desc);
		} catch (SQLException ex){
			ControladorErrores.mostrarError("Error al obtener sector:\n"+ex);
		}


		if (sector!=null){
/*			try {
				tieneDemandas = DemandaJDBC.getInstance().ConsultarDemandaSector(sector.getOID());
			} catch (SQLException ex){
				ControladorErrores.mostrarError("Error al obtener demandas del sector:\n"+ex);
			}
*/
			try {
				tieneOfertas = OfertaJDBC.getInstance().ConsultarOfertaSector(sector.getOID());
			} catch (SQLException ex){
				ControladorErrores.mostrarError("Error al obtener ofertas del sector:\n"+ex);
			}
		}

		if (tieneDemandas){
			vista.getOfertaDatos().getlabelError().setText("El sector no puede ser eliminado. Hay demandas en este sector.");
		}
		else if (tieneOfertas){
			vista.getOfertaDatos().getlabelError().setText("El sector no puede ser eliminado. Hay ofertas en este sector.");
		}
		else {
			try {
				SectorJDBC.getInstance().EliminarSector(sector);
				vista.getOfertaDatos().getlabelError().setText("Sector eliminado correctamente");
			}
			catch (SQLException ex){
				ControladorErrores.mostrarError("Error al eliminar sector:\n"+ex);
			}
		}

		// Recargo la lista de sectores
		try {
			vista.getOfertaDatos().getcbSector().removeAllItems();
			ArrayList<Sector> sectores = SectorJDBC.getInstance().ListadoSectores();
			for (int i=0;i<sectores.size();i++)
				vista.getOfertaDatos().getcbSector().addItem(sectores.get(i).getDescripcion());
		}catch (SQLException ex){ ControladorErrores.mostrarAlerta("Error al Obtener los sectores:\n"+ex); }
	}

	public void insertarOferta(Oferta oferta){
		try{
			OfertaJDBC.getInstance().insertarOferta(oferta);
			vista.getOfertaDatos().getlabelError().setText("La oferta ha sido añadida");
		}
		catch (SQLException ex){
			ControladorErrores.mostrarError("Error al insertar una oferta\n"+ex);
			vista.getOfertaDatos().getlabelError().setText("La oferta no ha sido añadida");
		}
	}

	public Oferta obtenerDatosOferta(int oid){
		Oferta oferta = new Oferta();


		return oferta;
	}

	public void actualizarOferta(Oferta of){
		try{
			OfertaJDBC.getInstance().ActualizarOferta(of);
			vista.getOfertaDatos().getlabelError().setText("La oferta ha sido actualizada");
		}
		catch (SQLException ex){
			ControladorErrores.mostrarError("La oferta no ha podido actualizarse:\n"+ex);
		}
	}

	public void obtenerListaOfertas(String empresaNombre, String sectorDesc, String antiguedad){
		Long empresaOID = -1l, sectorOID = -1l;

		if (!empresaNombre.equals("")){
			C_Empresa empresa = null;
			try{ empresa = C_EmpresaJDBC.getInstance().consultarC_Empresa(empresaNombre);
			} catch (SQLException ex){
				ControladorErrores.mostrarError("Error al obtener la empresa:\n"+ex);
			}
			if (empresa!=null) empresaOID = empresa.getOID();
//			else empresaOID = 0l;
		}

		if (!sectorDesc.equals("")){
			Sector sector = null;
			try{ sector = SectorJDBC.getInstance().ConsultarSector(sectorDesc);
			} catch (SQLException ex){
				ControladorErrores.mostrarError("Error al obtener el sector:\n"+ex);
			}
			if (sector!=null) sectorOID = sector.getOID();
//			else sectorOID = 0l;
		}

		try {
			listaOfertas = OfertaJDBC.getInstance().filtrarOfertas(empresaOID, sectorOID, antiguedad);
			actualizarTablaOfertas();
		} catch (SQLException ex){
			ControladorErrores.mostrarError("Error al obtener la lista de ofertas:\n"+ex.getMessage());
		}

	}

	public void eliminarOferta(Oferta oferta){
		if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar la oferta?", "Eliminar Oferta", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			try{
				OfertaJDBC.getInstance().eliminarOferta(oferta);
				ControladorErrores.mostrarMensaje("La oferta ha sido eliminada");
			}
			catch (SQLException ex){
				ControladorErrores.mostrarError("La oferta no se ha eliminad:\n"+ex);
			}
		}
		listaOfertas.remove(oferta);
		ofertaConsultada = null;
		actualizarTablaOfertas();
		ControladorBolsaTrabajo.getInstance(null).mostrarBuscarOferta();
	}

	/* Clases para ActionListener */
	public class ListenerBtGuardarSector
		implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Sector sector = new Sector();
			String sectorNuevo = vista.getOfertaDatos().getTextoNuevoSector();
			if (!TestDatos.isOnlyLetter(sectorNuevo)){
				vista.getOfertaDatos().getlabelError().setText("Error en los datos para añadir sector");
			}else {
				sector.setDescripcion(sectorNuevo);
				insertarSector(sector);
			}
		}
	}

	public class ListenerBtEliminarSector
		implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String sector = vista.getOfertaDatos().getcbSector().getSelectedItem().toString();
			eliminarSector(sector);
		}
	}

	public class ListenerBtGuardarOferta
		implements ActionListener{
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
					String descSector = vista.getOfertaDatos().getcbSector().getSelectedItem().toString();
					try{ oferta.setSector(SectorJDBC.getInstance().ConsultarSector(descSector));}
					catch (SQLException ex){ControladorErrores.mostrarError("Hubo un error con el sector:\n"+ex);}
					oferta.setEmpresa(empresa);
					oferta.setVoluntario(ControladorPrincipal.getInstance().getVoluntario());

					oferta.setDescripcionOferta(descripcion);
					oferta.setDuracionContrato(Integer.parseInt(duracion));
					oferta.setPlazasOfertadas(Integer.parseInt(nPuestos));
					oferta.setTipoContrato(vista.getOfertaDatos().getTextoTipoContrato());
					oferta.setCualificacionRequerida(cualificacion);
					oferta.setFecha(new Date());

					vista.getOfertaDatos().getlabelError().setText("");
					insertarOferta(oferta);
				}
			}
		}
	}

	public class ListenerBtLimpiar
		implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			limpiarFormulario();
			setColorLabels(Color.black);
		}
	}

	public class ListenerBtModificarOferta
		implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ControladorBolsaTrabajo.getInstance(null).mostrarModificarOferta(ofertaConsultada);
		}
	}

	public class ListenerBtActualizarOferta
		implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
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
					ofertaConsultada.setEmpresa(empresa);
					String descSector = vista.getOfertaDatos().getcbSector().getSelectedItem().toString();
					try{ ofertaConsultada.setSector(SectorJDBC.getInstance().ConsultarSector(descSector));}
					catch (SQLException ex){ControladorErrores.mostrarError("Hubo un error con el sector:\n"+ex);}
					ofertaConsultada.setDescripcionOferta(descripcion);
					ofertaConsultada.setDuracionContrato(Integer.parseInt(duracion));
					ofertaConsultada.setPlazasOfertadas(Integer.parseInt(nPuestos));
					ofertaConsultada.setTipoContrato(vista.getOfertaDatos().getTextoTipoContrato());
					ofertaConsultada.setCualificacionRequerida(cualificacion);
					ofertaConsultada.setVoluntario(ControladorPrincipal.getInstance().getVoluntario());
					ofertaConsultada.setFecha(new Date());

					vista.getOfertaDatos().getlabelError().setText("");
					actualizarOferta(ofertaConsultada);
				}
			}
		}
	}

	public class ListenerBtEliminarOferta
		implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			eliminarOferta(ofertaConsultada);
		}
	}

	public class ListenerBtBuscarOferta
		implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String empresa = vista.getOfertaBuscar().gettextoCIFEmpresa();
			String sectorDesc = vista.getOfertaBuscar().getTextoSector();
			String antiguedad = vista.getOfertaBuscar().getAntiguedad()+"";
System.out.println("Antiguedad seleccionada: "+antiguedad);

			obtenerListaOfertas(empresa, sectorDesc, antiguedad);
		}
	}

	public class ListenerBtEliminarOfertaBuscada
		implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (vista.getOfertaBuscar().gettablaBusquedaOferta().getSelectedRow() != -1) {
				ofertaConsultada = listaOfertas.get(vista.getOfertaBuscar().gettablaBusquedaOferta().getSelectedRow());
			}
			if (ofertaConsultada!=null) eliminarOferta(ofertaConsultada);
			else ControladorErrores.mostrarAlerta("No hay ninguna oferta seleccionada.");
		}
	}

	public class ListenerBtConsultarOferta
		implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (vista.getOfertaBuscar().gettablaBusquedaOferta().getSelectedRow() != -1) {
                ofertaConsultada = listaOfertas.get(vista.getOfertaBuscar().gettablaBusquedaOferta().getSelectedRow());
				ControladorBolsaTrabajo.getInstance(null).mostrarConsultarOferta(ofertaConsultada);
			}
		}
	}

}
