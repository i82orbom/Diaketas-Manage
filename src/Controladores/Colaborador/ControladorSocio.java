
package Controladores.Colaborador;

import Controladores.ControladorPrincipal;
import Controladores.TestDatos;
import Controladores.Voluntario.ControladorVoluntario;
import JDBC.ColaboracionJDBC;
import JDBC.CuotaJDBC;
import JDBC.PagoCuotaJDBC;
import JDBC.SocioJDBC;
import Modelo.*;
import Vistas.Paneles.Colaboradores.VistaColaboradores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 ** NOMBRE CLASE:
 **	  ControladorSocio
 **
 ** DESCRIPCION:
 **       Controlador del panel de coladores de typo socio
 **
 **
 ** DESARROLLADO POR:
 *          Raphael Colleau (RC) y Alberto Moreno Mantas
 **
 **
 ** SUPERVISADO POR:
 **
 **
 ** HISTORIA:
 ** 	000 - 1 mai 2012 - RC - Creacion y estructura
 ** 	001 - 4 mai 2012 - RC - Metodos con JDBC
 *      002 - 5 mai 2012 - RC - comproba datos
 **
 ** NOTAS:
 **
 **
 */
public class ControladorSocio{

	/**
		* PATRON DE DISEÑO SINGLETON
		*/
	private static ControladorSocio instancia = null;
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	// TODO vista
	public static ControladorSocio getInstance(VistaColaboradores pvista) {

		if (instancia == null) {
			instancia = new ControladorSocio(pvista);
		}
		return instancia;

	}

	private VistaColaboradores vista;
	Socio socio_temp = null;
	ArrayList<Socio> socios = null;
	private String[] columnNames = {"DNI", "Nombre", "Localidad", "Provincia", "Teléfono", "Movil", "CP"};

	public ControladorSocio(VistaColaboradores pvista) {
		vista = pvista;

		vista.getPanelSocioDatos().getBtGuardarDatosSocio().addActionListener(new btGuardarDatosSocioListener());
		vista.getPanelSocioDatos().getBtBorrarDatosSocio().addActionListener(new btBorrarDatosSocioListener());
		vista.getPanelSocioDatos().getBtGuardarCuotaSocio().addActionListener(new btGuardarCuotaSocioListener());
		
		vista.getPanelSocioBuscar().getBtBuscarSocio().addActionListener(new btBuscarSocioListener());
	}


	public boolean anadirSocio(String[] datos) {
		if (!comprobarDatos(datos)){
			System.out.println("Errores en los datos");
			return false;
		}
		Socio socio = new Socio();
		socio.setUsuario(datos[Socio.USUARIO_ID]);
		// cryptar password en MD5
		socio.setContrasena(ControladorVoluntario.genContrasena());

		socio.setDNI(datos[Socio.DNI_ID]);
		socio.setNombre(datos[Socio.NOMBRE_ID]);
		socio.setApellidos(datos[Socio.APELLIDOS_ID]);
		socio.setSexo(datos[Socio.SEXO_ID].charAt(0));
		socio.setFechaDeNacimiento(Date.valueOf(datos[Socio.FECHA_DE_NACIMIENTO_ID]));
		socio.setEmail(datos[Socio.EMAIL_ID]);
		socio.setDireccion(datos[Socio.DIRECCION_ID]);
		socio.setLocalidad(datos[Socio.LOCALIDAD_ID]);
		socio.setProvincia(datos[Socio.PROVINCIA_ID]);
		socio.setCP(datos[Socio.CP_ID]);
		socio.setTelefonoFijo(datos[Socio.TELEFONO1_ID]);
		socio.setTelefonoMovil(datos[Socio.TELEFONO2_ID]);

		boolean exito;
		try {
				exito = SocioJDBC.getInstance().añadirSocio(socio);
		} catch (SQLException ex) {
			exito = false;
			Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
		}

		return exito;
	}

	public Socio obtenerSocio (String DNI) {
		Socio socio = null;
		try {
			socio = SocioJDBC.getInstance().obtenerSocio(DNI);
		} catch (SQLException ex) {
			Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
		}
		return socio;
	}

	public boolean modificarSocio (String[] datos) {
		if (!comprobarDatos(datos))
			return false;

		// TODO test password MD5

		Socio socio = new Socio();
		socio.setUsuario(datos[Socio.USUARIO_ID]);

		socio.setDNI(datos[Socio.DNI_ID]);
		socio.setNombre(datos[Socio.NOMBRE_ID]);
		socio.setApellidos(datos[Socio.APELLIDOS_ID]);
		socio.setSexo(datos[Socio.SEXO_ID].charAt(0));
		socio.setFechaDeNacimiento(Date.valueOf(datos[Socio.FECHA_DE_NACIMIENTO_ID]));

		socio.setEmail(datos[Socio.EMAIL_ID]);
		socio.setDireccion(datos[Socio.DIRECCION_ID]);
		socio.setLocalidad(datos[Socio.LOCALIDAD_ID]);
		socio.setProvincia(datos[Socio.PROVINCIA_ID]);
		socio.setCP(datos[Socio.CP_ID]);
		socio.setTelefonoFijo(datos[Socio.TELEFONO1_ID]);
		socio.setTelefonoMovil(datos[Socio.TELEFONO2_ID]);
		boolean exito;
		try {
				exito = SocioJDBC.getInstance().modificarDatosSocio(socio);
		} catch (SQLException ex) {
			exito = false;
			Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
		}

		return exito;
	}

	public boolean eliminarSocio (Socio socio) {
		try {
			return SocioJDBC.getInstance().eliminarSocio(socio);
		} catch (SQLException ex) {
			Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public ArrayList<Socio> buscarSocio (String tipoBusqueta, String valor) {
		//ArrayList<Socio> socios;
		try {
			socios = SocioJDBC.getInstance().obtenerListadoSocios(tipoBusqueta, valor);
			System.out.println(socios.size());
		} catch (SQLException ex) {
			Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

		return socios;
	}

	public boolean cancelarCuota (Cuota c) {
		if (c.getFechaUltimoPago() == c.getFechaInicio()) {
			try {
				return CuotaJDBC.getInstance().eliminarCuota(c);
			} catch (SQLException ex) {
				Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			try {
				return CuotaJDBC.getInstance().cancelarCuota(c);
			} catch (SQLException ex) {
				Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return false;
	}

	public ArrayList<Movimiento> historialPagosCuotas (Socio socio, Date fechaInicio, Date fechaFin) {
		ArrayList<PagoCuota> pagosCuotas = null;

		try {
			pagosCuotas = PagoCuotaJDBC.getInstance().HistorialPagosCuotas(socio, fechaInicio, fechaFin);
		} catch (SQLException ex) {
			Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
		}

		if (pagosCuotas == null)
			return null;

		ArrayList<Colaboracion> pagosColaboraciones = null;

		try {
			pagosColaboraciones = ColaboracionJDBC.getInstance().HistorialColaboraciones(socio, fechaInicio, fechaFin);
		} catch (SQLException ex) {
			Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
		}

		if (pagosColaboraciones == null)
			return null;

		ArrayList<Movimiento> pagos = new ArrayList<Movimiento>();
		pagos.addAll(pagosCuotas);
		pagos.addAll(pagosColaboraciones);

		return pagos;
	}

	// proba los datos pero no sabemos cual son los datos que no son correcto
	private boolean comprobarDatos(String[] datos) {
		// cada campo debe ser not null
		for (int i=0; i<datos.length; i++) {
			if (datos[i].length() < 1)
				return false;
		}

		if (!TestDatos.isDNI(datos[Socio.DNI_ID]))
			return false;

		if (!TestDatos.isCodigoPostal(datos[Socio.CP_ID]))
			return false;

		if (!TestDatos.isEmail(datos[Socio.EMAIL_ID]))
			return false;

		if (!TestDatos.isNombre(datos[Socio.NOMBRE_ID]))
			return false;

		if (!TestDatos.isNombre(datos[Socio.APELLIDOS_ID]))
			return false;

		if (!TestDatos.isDomicilio(datos[Socio.DIRECCION_ID]))
			return false;

		if (!TestDatos.isNombre(datos[Socio.LOCALIDAD_ID]))
			return false;

		if (!TestDatos.isNombre(datos[Socio.PROVINCIA_ID]))
			return false;

		if (!TestDatos.isOnlyLetterOrDigit(datos[Socio.USUARIO_ID]))
			return false;

		return true;
	}
	private boolean comprobarDatos(Socio socio) {
		//comporbar que los elementos de un socio no son nulos
		if(socio.getNombre().equals("") || socio.getApellidos().equals("")|| socio.getDNI().equals("")|| socio.getDireccion().equals("")|| socio.getProvincia().equals("")|| socio.getLocalidad().equals("")|| socio.getCP().equals("") || socio.getUsuario().equals(""))
			return false;

		if (!TestDatos.isDNI(socio.getDNI()))
			return false;

		if (!TestDatos.isCodigoPostal(socio.getCP()))
			return false;

		if (!TestDatos.isOnlyLetter(socio.getNombre()))
			return false;

		if (!TestDatos.isOnlyLetter(socio.getApellidos()))
			return false;

		if (!TestDatos.isOnlyLetter(socio.getDireccion()))
			return false;

		if (!TestDatos.isOnlyLetter(socio.getLocalidad()))
			return false;

		if (!TestDatos.isOnlyLetter(socio.getProvincia()))
			return false;

		if (!TestDatos.isOnlyLetterOrDigit(socio.getUsuario()))
			return false;

		return true;
	}

	/**
		* Listener controlador del boton Guardar datos socio
	*/
	public class btGuardarDatosSocioListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] datos = new String[14];

			datos[Socio.NOMBRE_ID] = vista.getPanelSocioDatos().obtenerNombreSocio();
			datos[Socio.APELLIDOS_ID] = vista.getPanelSocioDatos().obtenerApellidosSocio();
			datos[Socio.EMAIL_ID] = vista.getPanelSocioDatos().obtenerCorreoSocio();
			datos[Socio.DNI_ID] = vista.getPanelSocioDatos().obtenerDNISocio();
			datos[Socio.SEXO_ID] = vista.getPanelSocioDatos().obtenerSexoSocio();
			try {
				datos[Socio.FECHA_DE_NACIMIENTO_ID] = vista.getPanelSocioDatos().obtenerFNSocio();
			} catch (ParseException ex) {
				Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
			}
			datos[Socio.DIRECCION_ID] = vista.getPanelSocioDatos().obtenerDomicilioSocio();
			datos[Socio.LOCALIDAD_ID] = vista.getPanelSocioDatos().obtenerLocalidadSocio();
			datos[Socio.PROVINCIA_ID] = vista.getPanelSocioDatos().obtenerProvinciaSocio();
			datos[Socio.CP_ID] = vista.getPanelSocioDatos().obtenerCPSocio();
			datos[Socio.TELEFONO1_ID] = vista.getPanelSocioDatos().obtenerTelfSocio();
			datos[Socio.TELEFONO2_ID] = vista.getPanelSocioDatos().obtenerMovilSocio();
			datos[Socio.USUARIO_ID] = vista.getPanelSocioDatos().obtenerUsuarioSocio();

			String password = ControladorPrincipal.getInstance().md5(vista.getPanelSocioDatos().obtenerDNISocio()+ControladorPrincipal.getInstance().getSalto());
			datos[Socio.CONTRASENA_ID] = password;
			if(socio_temp==null){
				System.out.println("anadiendoSocio");
				boolean exito = anadirSocio(datos);
				if (exito) {
						vista.getPanelSocioDatos().setTextLabelError("Socio añadido correctamente.");
				} else {
						vista.getPanelSocioDatos().setTextLabelError("El Socio no ha sido añadido.");
				}
			}
			else{
				boolean exito = modificarSocio(datos);
				if (exito) {
						vista.getPanelSocioDatos().setTextLabelError("Socio modificado correctamente.");
				} else {
						vista.getPanelSocioDatos().setTextLabelError("El Socio no ha sido modificado.");
				}
			}
		}
	}
	
	/**
		* Listener controlador del boton Eliminar socio
	*/
	public class btBorrarDatosSocioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(socio_temp==null){
				String[] datos = new String[25];
				for(int i=0; i<25; i++)
				datos[i]="";
				vista.getPanelSocioDatos().escribirSocioDatos(datos);
			}
			else{
				boolean exito = eliminarSocio(socio_temp);
				if (exito) {
						vista.getPanelSocioDatos().setTextLabelError("Socio eliminado correctamente.");
				} else {
						vista.getPanelSocioDatos().setTextLabelError("El Socio no ha sido eliminado.");
				}
			}
		}

	}
	
	/**
		* Listener controlador del boton Guardar Cuota socio
	*/
	public class btGuardarCuotaSocioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] datos= new String[5];
			datos[Cuota.CANTIDAD]= vista.getPanelSocioDatos().obtenerCantidadCuotaSocio();
			datos[Cuota.FECHA_FIN] = vista.getPanelSocioDatos().obtenerFechaFinCuotaSocio();
			datos[Cuota.FECHA_INICIO] = vista.getPanelSocioDatos().obtenerFechaIniCuotaSocio();
			datos[Cuota.INTERFALO_PAGOS] = vista.getPanelSocioDatos().obtenerIntervaloCuotaSocio();
			//datos[Cuota.FECHA_ULTIMO_PAGO] = vista.getPanelSocioDatos().obtenerFechaIniCuotaSocio();
			if(socio_temp!=null)
				ControladorCuota.getInstance(vista).anadirCuota(datos, socio_temp);
			else{
				socio_temp=obtenerSocio(vista.getPanelSocioDatos().obtenerDNISocio());
				if(socio_temp==null){
					System.out.println("No se a elegido un socio");
				}
				else
					ControladorCuota.getInstance(vista).anadirCuota(datos, socio_temp);
			}	
		}
	}
	
	/**
		* Listener controlador del boton Guardar Colaboracion socio
	*/
	
	public class btBuscarSocioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) { 
			String valor = vista.getPanelSocioBuscar().getValorBusquedaSocio();
			String tipo = vista.getPanelSocioBuscar().getTipoBusquedaSocio();
			if(tipo.equals("DNI/NIF/Pasaporte"))
				tipo="DNI";
			else if(tipo.equals("Teléfono Fijo"))
				tipo="TelefonoFijo";
			else if(tipo.equals("Movil"))
				tipo="TelefonoMovil";
			else if(tipo.equals("Dirección"))
				tipo="Domicilio";
			else if(tipo.equals("Código Postal"))
				tipo="CP";
			
			//System.out.println("tipo: "+tipo);
			
			socios = buscarSocio(tipo, valor);
			
			 TableModel tableModel = new TableModel() {

                @Override
                public int getRowCount() {
                    return socios.size();
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
                public Object getValueAt(int row, int col) {
                    switch (col) {
                        case 0:
                            return socios.get(row).getDNI();
                        case 1:
                            return socios.get(row).getNombre();
                        case 2:
                            return socios.get(row).getLocalidad();
                        case 3:
                            return socios.get(row).getDireccion();
                        case 4:
                            return socios.get(row).getTelefonoFijo();
						case 5:
                            return socios.get(row).getTelefonoMovil();
						case 6:
                            return socios.get(row).getCP();
                    }
                    return "";
                }

                @Override
                public void setValueAt(Object o, int row, int col) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void addTableModelListener(TableModelListener tl) {

                }

                @Override
                public void removeTableModelListener(TableModelListener tl) {

                }
            };
            
            vista.getPanelSocioBuscar().getTablaBusqueda().setModel(tableModel);
		}
	
	}
}
