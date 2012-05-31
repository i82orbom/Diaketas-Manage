
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
	private void rellenarcbSectores(){
		vista.getOfertaDatos().getcbSector().removeAllItems();
		try {
			ArrayList<Sector> sectores = SectorJDBC.getInstance().ListadoSectores();
			for (int i=0;i<sectores.size();i++)
				vista.getOfertaDatos().getcbSector().addItem(sectores.get(i).getDescripcion());
		}
		catch (SQLException ex){ ControladorErrores.mostrarAlerta("Error al Obtener los sectores:\n"+ex); }
	}
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


	/*______ Métodos del controlador ______*/
	public void insertarSector(Sector sector){
		try{
			SectorJDBC.getInstance().InsertarSector(sector);
			vista.getOfertaDatos().getlabelError().setText("El nuevo sector ha sido añadido");
		}catch (SQLException ex){
			ControladorErrores.mostrarError("Error al guardar sector:\n"+ex);
			vista.getOfertaDatos().getlabelError().setText("El sector no ha sido añadido");
		}

		rellenarcbSectores();
	}

	public void eliminarSector(String desc){
		Sector sector;
		try {
			sector = SectorJDBC.getInstance().ConsultarSector(desc);

			try{
				SectorJDBC.getInstance().EliminarSector(sector);
				vista.getOfertaDatos().getlabelError().setText("Sector eliminado correctamente");
			}
			catch (SQLException ex){
				ControladorErrores.mostrarError("Error al eliminar sector:\n"+ex);
			}
			rellenarcbSectores();
		}
		catch (SQLException ex){
			ControladorErrores.mostrarError("Error al obtener sector:\n"+ex);
		}

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

	public boolean actualizarOferta(Oferta of){

		return true;
	}

	public ArrayList<Oferta> obtenerListaOfertas(String CIF, String sector, String antiguedad){
		try{
			listaOfertas = OfertaJDBC.getInstance().filtrarOfertas(CIF,sector,antiguedad);
		}
		catch (SQLException ex){
			ControladorErrores.mostrarError("Error al obtener la lista de ofertas:\n"+ex.getMessage());
		}

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

		return listaOfertas;
	}

	public boolean eliminarOferta(Oferta oferta){
		JOptionPane.showConfirmDialog(vista, vista);
		boolean exito = true;

		return exito;
	}

	/*_____ Funciones para mostrar vistas _____*/
	public void mostrarOfertaNueva(){
		vista.getOfertaDatos().getlabelError().setText("");

		vista.getOfertaDatos().getBTGuardar().setVisible(true);
		vista.getOfertaDatos().getBTLimpiar().setVisible(true);
		vista.getOfertaDatos().getBTModificar().setVisible(false);
		vista.getOfertaDatos().getBTGuardarCambios().setVisible(false);
		vista.getOfertaDatos().getBTEliminar().setVisible(false);

		vista.getOfertaDatos().getBTGuardarSector().setVisible(true);
		vista.getOfertaDatos().getBTEliminarSector().setVisible(true);

		vista.getOfertaDatos().getTextCIF().setEnabled(true);
		vista.getOfertaDatos().getcbSector().setEnabled(true);
		vista.getOfertaDatos().getTextNuevoSector().setVisible(true);
		vista.getOfertaDatos().gettaDescripcionOferta().setEditable(true);
		vista.getOfertaDatos().gettextNPuestos().setEditable(true);
		vista.getOfertaDatos().getcbTipoContrato().setEnabled(true);
		vista.getOfertaDatos().gettextDuracionContrato().setEditable(true);
		vista.getOfertaDatos().gettaCualificacion().setEditable(true);

		rellenarcbSectores();
	}
	public void mostrarOfertaConsultada(Oferta oferta){
		vista.getOfertaDatos().getlabelError().setText("");

		vista.getOfertaDatos().getBTGuardar().setVisible(false);
		vista.getOfertaDatos().getBTLimpiar().setVisible(false);
		vista.getOfertaDatos().getBTModificar().setVisible(true);
		vista.getOfertaDatos().getBTGuardarCambios().setVisible(false);
		vista.getOfertaDatos().getBTEliminar().setVisible(true);

		vista.getOfertaDatos().getBTGuardarSector().setVisible(false);
		vista.getOfertaDatos().getBTEliminarSector().setVisible(false);

		vista.getOfertaDatos().getTextCIF().setEnabled(false);
		vista.getOfertaDatos().getTextCIF().setText(oferta.getEmpresa().getCIF());
		vista.getOfertaDatos().getcbSector().setEnabled(false);
		vista.getOfertaDatos().getcbSector().setSelectedItem(oferta.getSector().getDescripcion());
		vista.getOfertaDatos().getTextNuevoSector().setVisible(false);
		vista.getOfertaDatos().gettaDescripcionOferta().setEditable(false);
		vista.getOfertaDatos().gettaDescripcionOferta().setText(oferta.getDescripcionOferta());
		vista.getOfertaDatos().gettextNPuestos().setEditable(false);
		vista.getOfertaDatos().gettextNPuestos().setText(oferta.getPlazasOfertadas()+"");
		vista.getOfertaDatos().getcbTipoContrato().setEnabled(false);
		vista.getOfertaDatos().getcbTipoContrato().setSelectedItem(oferta.getTipoContrato());
		vista.getOfertaDatos().gettextDuracionContrato().setEditable(false);
		vista.getOfertaDatos().gettextDuracionContrato().setText(oferta.getDuracionContrato()+"");
		vista.getOfertaDatos().gettaCualificacion().setEditable(false);
		vista.getOfertaDatos().gettaCualificacion().setText(oferta.getCualificacionRequerida());

		ControladorBolsaTrabajo.getInstance(null).mostrarNuevaOferta();
	}
	public void mostrarOfertaModificar(){
		vista.getOfertaDatos().getlabelError().setText("");

		vista.getOfertaDatos().getBTGuardar().setVisible(false);
		vista.getOfertaDatos().getBTLimpiar().setVisible(false);
		vista.getOfertaDatos().getBTModificar().setVisible(false);
		vista.getOfertaDatos().getBTGuardarCambios().setVisible(true);
		vista.getOfertaDatos().getBTEliminar().setVisible(true);

		vista.getOfertaDatos().getBTGuardarSector().setVisible(true);
		vista.getOfertaDatos().getBTEliminarSector().setVisible(true);

		vista.getOfertaDatos().getTextCIF().setEnabled(true);
		vista.getOfertaDatos().getcbSector().setEnabled(true);
		vista.getOfertaDatos().getTextNuevoSector().setVisible(true);
		vista.getOfertaDatos().gettaDescripcionOferta().setEditable(true);
		vista.getOfertaDatos().gettextNPuestos().setEditable(true);
		vista.getOfertaDatos().getcbTipoContrato().setEnabled(true);
		vista.getOfertaDatos().gettextDuracionContrato().setEditable(true);
		vista.getOfertaDatos().gettaCualificacion().setEditable(true);

		rellenarcbSectores();

		ControladorBolsaTrabajo.getInstance(null).mostrarModificarOferta();
	}
	public void mostrarOfertaBuscar(){
		rellenarcbSectores();
		vista.getOfertaBuscar().getcbSector().addItem("Cualquiera");
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
					oferta.setEmpresa(empresa);
					String descSector = vista.getOfertaDatos().getcbSector().getSelectedItem().toString();
					try{ oferta.setSector(SectorJDBC.getInstance().ConsultarSector(descSector));}
					catch (SQLException ex){ControladorErrores.mostrarError("Hubo un error con el sector:\n"+ex);}
					oferta.setDescripcionOferta(descripcion);
					oferta.setDuracionContrato(Integer.parseInt(duracion));
					oferta.setPlazasOfertadas(Integer.parseInt(nPuestos));
					oferta.setTipoContrato(vista.getOfertaDatos().getTextoTipoContrato());
					oferta.setCualificacionRequerida(cualificacion);
					oferta.setVoluntario(ControladorPrincipal.getInstance().getVoluntario());
					oferta.setFecha(new Date());

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
			mostrarOfertaModificar();
		}
	}

	public class ListenerBtActualizarOferta
		implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
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
			String CIFEmpresa = vista.getOfertaBuscar().gettextoCIFEmpresa();
			String sectorDesc = vista.getOfertaBuscar().getcbSector().getSelectedItem().toString();
			String antiguedad = vista.getOfertaBuscar().getAntiguedad();

			listaOfertas = obtenerListaOfertas(CIFEmpresa, sectorDesc, antiguedad);
		}
	}

	public class ListenerBtEliminarOfertaBuscada
		implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (vista.getOfertaBuscar().gettablaBusquedaOferta().getSelectedRow() != -1) {
				if (eliminarOferta(listaOfertas.get(vista.getOfertaBuscar().gettablaBusquedaOferta().getSelectedRow()))){
					ControladorErrores.mostrarMensaje("La oferta ha sido borrada");
				}
			}
		}
	}

	public class ListenerBtConsultarOferta
		implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (vista.getOfertaBuscar().gettablaBusquedaOferta().getSelectedRow() != -1) {
                ofertaConsultada = listaOfertas.get(vista.getOfertaBuscar().gettablaBusquedaOferta().getSelectedRow());
				mostrarOfertaConsultada(ofertaConsultada);
			}
		}
	}

}
