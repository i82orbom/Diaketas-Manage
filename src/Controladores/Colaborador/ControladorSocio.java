
package Controladores.Colaborador;

import Controladores.ControladorColaboradores;
import Controladores.ControladorPrincipal;
import Controladores.TestDatos;
import Controladores.Voluntario.ControladorColaboracion;
import JDBC.ColaboracionJDBC;
import JDBC.CuotaJDBC;
import JDBC.PagoCuotaJDBC;
import JDBC.SocioJDBC;
import Modelo.*;
import Vistas.Paneles.Colaboradores.VistaColaboradores;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
	ArrayList<Cuota> cuotas = null;
	
	private String[] columnNames = {"DNI", "Nombre", "Direccion", "Localidad", "Teléfono", "Movil", "CP"};
	private String[] columnNamesCuotas = {"Cantidad", "Fecha Inicio", "Fecha Final", "Intervalo de Pagos", "Ulitmo Pago"};

	public ControladorSocio(VistaColaboradores pvista) {
		
		vista = pvista;
		//ControladorColaboracion.getInstance(vista.getVistaSocio());
		
		vista.getPanelSocioDatos().getBtGuardarDatosSocio().addActionListener(new btGuardarDatosSocioListener());
		vista.getPanelSocioDatos().getBtBorrarDatosSocio().addActionListener(new btBorrarDatosSocioListener());
		vista.getPanelSocioDatos().getBtGuardarColaboracion().addActionListener(new btGuardarColaboracionesListener());
		vista.getPanelSocioDatos().getBtGuardarCuotaSocio().addActionListener(new btGuardarCuotaListener());
		
		vista.getPanelSocioBuscar().getBtBuscarSocio().addActionListener(new btBuscarSocioListener());
		vista.getPanelSocioBuscar().getBtEliminarSocio().addActionListener(new btEliminarSocioListener());
		vista.getPanelSocioBuscar().getBtConsultarSocio().addActionListener(new btConsultarSocioListener());
		
		anadirKeyListener();
	}
    
	public boolean anadirSocio(Socio socio) {
		if (obtenerSocio(socio.getDNI())!= null) {
            JOptionPane.showMessageDialog(null, "Un socio con este DNI ya existe.");
            return false;
        }
		if (comprobarUsuarioUnico(socio.getUsuario())) {
            JOptionPane.showMessageDialog(null, "Un socio con este Usuario ya existe.");
            return false;
        } 
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
	
	public boolean comprobarUsuarioUnico (String DNI) {
		boolean existe=false;
		try {
			existe = SocioJDBC.getInstance().comprobarUsuarioUnico(DNI);
		} catch (SQLException ex) {
			Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
		}
		return existe;
	}
	
	public boolean modificarSocio (Socio socio) {
		if (obtenerSocio(socio.getDNI())!= null && !socio_temp.getDNI().equals(socio.getDNI()) ) {
            JOptionPane.showMessageDialog(null, "Un socio con este DNI ya existe.");
            return false;
        }
		if (comprobarUsuarioUnico(socio.getUsuario()) && !socio_temp.getUsuario().equals(socio.getUsuario())) {
            JOptionPane.showMessageDialog(null, "Un socio con este Usuario ya existe.");
            return false;
        } 
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
		} catch (SQLException ex) {
			Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

		return socios;
	}
	public Cuota obtenerCuota (Socio socio) {
		Cuota cuota = null;
		try {
			cuota = SocioJDBC.getInstance().obtenerCuotaActiva(socio);
		} catch (SQLException ex) {
			Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return cuota;
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
		return true;
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

	/**
		* Listener controlador del boton Guardar datos socio
	*/
	public class btGuardarDatosSocioListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
            boolean datosCorrectos = true;

			if( !TestDatos.isNombre( vista.getPanelSocioDatos().getTextNombre().getText()) ) {
                vista.getPanelSocioDatos().getTextNombre().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isNombre( vista.getPanelSocioDatos().getTextApellidos().getText()) ) {
                vista.getPanelSocioDatos().getTextApellidos().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isDNI( vista.getPanelSocioDatos().getTextDNI().getText()) ) {
                vista.getPanelSocioDatos().getTextDNI().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isFecha( vista.getPanelSocioDatos().getTextFN().getText())) {
                vista.getPanelSocioDatos().getTextFN().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isEmail( vista.getPanelSocioDatos().getTextEmail().getText()) || vista.getPanelSocioDatos().getTextEmail().getText().equals("")) {
                vista.getPanelSocioDatos().getTextEmail().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isDomicilio( vista.getPanelSocioDatos().getTextDomicilio().getText()) ) {
                vista.getPanelSocioDatos().getTextDomicilio().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isNombre( vista.getPanelSocioDatos().getTextLocalidad().getText()) ) {
                vista.getPanelSocioDatos().getTextLocalidad().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isNombre( vista.getPanelSocioDatos().getTextProvincia().getText()) ) {
                vista.getPanelSocioDatos().getTextProvincia().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isCodigoPostal( vista.getPanelSocioDatos().getTextCP().getText()) ) {
                vista.getPanelSocioDatos().getTextCP().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isTelefonoOFax( vista.getPanelSocioDatos().getTextTelfFijo().getText()) || vista.getPanelSocioDatos().getTextTelfFijo().getText().equals("")) {
                vista.getPanelSocioDatos().getTextTelfFijo().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isTelefonoOFax( vista.getPanelSocioDatos().getTextTelMovil().getText()) || vista.getPanelSocioDatos().getTextTelMovil().getText().equals("")) {
                vista.getPanelSocioDatos().getTextTelMovil().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isNombre(vista.getPanelSocioDatos().getTextNombre().getText()) ) {
                vista.getPanelSocioDatos().getTextNombre().setForeground(Color.RED);
                datosCorrectos = false;
            }
			
			if (!datosCorrectos) {
                vista.getPanelSocioDatos().setTextLabelError("Los campos en rojo tienes errores.");
            } else {

			if(socio_temp==null){
				Socio socio = new Socio();
				socio.setNombre(vista.getPanelSocioDatos().getTextNombre().getText());
				socio.setApellidos(vista.getPanelSocioDatos().getTextApellidos().getText());
				socio.setDNI(vista.getPanelSocioDatos().getTextDNI().getText());
				try {
					socio.setFechaDeNacimiento(TestDatos.formatter.parse(vista.getPanelSocioDatos().getTextFN().getText()));
				} catch (ParseException ex) {
					Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
				}
				socio.setEmail(vista.getPanelSocioDatos().getTextEmail().getText());
				System.out.println(vista.getPanelSocioDatos().getSexo());
				socio.setSexo(vista.getPanelSocioDatos().getSexo());
				socio.setDireccion(vista.getPanelSocioDatos().getTextDomicilio().getText());
				socio.setProvincia(vista.getPanelSocioDatos().getTextProvincia().getText());
				socio.setLocalidad(vista.getPanelSocioDatos().getTextLocalidad().getText());
				socio.setCP(vista.getPanelSocioDatos().getTextCP().getText());
				socio.setTelefonoFijo(vista.getPanelSocioDatos().getTextTelfFijo().getText());
				socio.setTelefonoMovil(vista.getPanelSocioDatos().getTextTelMovil().getText());
				socio.setUsuario(vista.getPanelSocioDatos().getTextUsuario().getText());

				String password = ControladorPrincipal.getInstance().md5(vista.getPanelSocioDatos().getTextDNI().getText() + ControladorPrincipal.getInstance().getSalto());
                socio.setContrasena(password);		
				
				boolean exito = anadirSocio(socio);
				if (exito) {
						socio_temp = obtenerSocio(socio.getDNI());
						vista.getPanelSocioDatos().setTextLabelError("Socio añadido correctamente.");
				} else {
						vista.getPanelSocioDatos().setTextLabelError("El Socio no ha sido añadido.");
				}
			}
			else{
				Socio socio = new Socio();
				socio.setOID(socio_temp.getOID());
				socio.setNombre(vista.getPanelSocioDatos().getTextNombre().getText());
				socio.setApellidos(vista.getPanelSocioDatos().getTextApellidos().getText());
				socio.setDNI(vista.getPanelSocioDatos().getTextDNI().getText());
				try {
					socio.setFechaDeNacimiento(TestDatos.formatter.parse(vista.getPanelSocioDatos().getTextFN().getText()));
				} catch (ParseException ex) {
					Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
				}
				socio.setEmail(vista.getPanelSocioDatos().getTextEmail().getText());
				socio.setSexo(vista.getPanelSocioDatos().getSexo());
				socio.setDireccion(vista.getPanelSocioDatos().getTextDomicilio().getText());
				socio.setProvincia(vista.getPanelSocioDatos().getTextProvincia().getText());
				socio.setLocalidad(vista.getPanelSocioDatos().getTextLocalidad().getText());
				socio.setCP(vista.getPanelSocioDatos().getTextCP().getText());
				socio.setTelefonoFijo(vista.getPanelSocioDatos().getTextTelfFijo().getText());
				socio.setTelefonoMovil(vista.getPanelSocioDatos().getTextTelMovil().getText());
				socio.setUsuario(vista.getPanelSocioDatos().getTextUsuario().getText());

				String password = ControladorPrincipal.getInstance().md5(vista.getPanelSocioDatos().getTextDNI().getText() + ControladorPrincipal.getInstance().getSalto());
                socio.setContrasena(password);		
				boolean exito = modificarSocio(socio);
				if (exito) {
						vista.getPanelSocioDatos().setTextLabelError("Socio modificado correctamente.");
				} else {
						vista.getPanelSocioDatos().setTextLabelError("El Socio no ha sido modificado.");
				}
			}
			}
		}
	}
	private void anadirKeyListener () {
        TextKeyListener keyListener = new TextKeyListener();
        
        vista.getPanelSocioDatos().getTextNombre().addKeyListener(keyListener);
        vista.getPanelSocioDatos().getTextApellidos().addKeyListener(keyListener);
        vista.getPanelSocioDatos().getTextDNI().addKeyListener(keyListener);
        vista.getPanelSocioDatos().getTextFN().addKeyListener(keyListener);
        vista.getPanelSocioDatos().getTextEmail().addKeyListener(keyListener);
        vista.getPanelSocioDatos().getTextDomicilio().addKeyListener(keyListener);
        vista.getPanelSocioDatos().getTextLocalidad().addKeyListener(keyListener);
        vista.getPanelSocioDatos().getTextProvincia().addKeyListener(keyListener);
        vista.getPanelSocioDatos().getTextCP().addKeyListener(keyListener);
		vista.getPanelSocioDatos().getTextTelMovil().addKeyListener(keyListener);
		vista.getPanelSocioDatos().getTextTelfFijo().addKeyListener(keyListener);
		vista.getPanelSocioDatos().getTextUsuario().addKeyListener(keyListener);
		vista.getPanelSocioDatos().getTextCantidadColaboracion().addKeyListener(keyListener);
		vista.getPanelSocioDatos().getTextFechaColaboracion().addKeyListener(keyListener);
		vista.getPanelSocioDatos().getTextConceptoColaboracion().addKeyListener(keyListener);

    }
    
    // Cuando el usuario escribe, cambia el color en negro si habia errores y el color estaba rojo
    class TextKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent ke) {
            if (ke.getSource().getClass() == JTextField.class || ke.getSource().getClass() == JFormattedTextField.class) {
                ((JTextField)ke.getSource()).setForeground(Color.black);
            }
        }

        @Override
        public void keyPressed(KeyEvent ke) {
        }

        @Override
        public void keyReleased(KeyEvent ke) {
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
				if(JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar el Voluntario?", "Eliminar Voluntario", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
					boolean exito = eliminarSocio(socio_temp);
					if (exito) {
							vista.getPanelSocioDatos().setTextLabelError("Socio eliminado correctamente.");
					} else {
							vista.getPanelSocioDatos().setTextLabelError("El Socio no ha sido eliminado.");
					}
				}
			}
		}

	}
	
	/**
		* Listener controlador del boton Buscar Colaboracion socio
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
			else if(tipo.equals("Código Postal"))
				tipo="CP";
			else if(tipo.equals("Dirección"))
				tipo="Direccion";
						
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
                            return socios.get(row).getDireccion();
                        case 3:
                            return socios.get(row).getLocalidad();
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
	
	public class btEliminarSocioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String DNI=null;
			Socio s = null;
			
			int filaSeleccionada = vista.getPanelSocioBuscar().getTablaBusqueda().getSelectedRow();
			if(filaSeleccionada!=-1)
				DNI = vista.getPanelSocioBuscar().getTablaBusqueda().getValueAt(filaSeleccionada, 0).toString();
			if(DNI!=null){
				s = obtenerSocio(DNI);
				if(JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar el Voluntario?", "Eliminar Voluntario", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
					if (s!=null && eliminarSocio(s)) {
						for (int i=0; i<socios.size(); i++){
							System.out.println("socios: "+socios.get(i).getOID());
						}
							
						if(socios.remove(s))
							System.out.println("El socio a sido eliminado");
						else
							System.out.println("El socio no a sido eliminado");							

						actualizarTablaBuscar();
						vista.getPanelSocioBuscar().setTextLabelError("El Socio ha sido eliminado del sistema.");
					}
					else {
						vista.getPanelSocioBuscar().setTextLabelError("Error : el Socio no ha sido eliminado del sistema.");
					}
				}
			}
		}		
	}
	
	void actualizarTablaBuscar(){
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
						return socios.get(row).getDireccion();
					case 3:
						return socios.get(row).getLocalidad();
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
	public class btConsultarSocioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String DNI=null;
			
			int filaSeleccionada = vista.getPanelSocioBuscar().getTablaBusqueda().getSelectedRow();
			if(filaSeleccionada!=-1)
				DNI = vista.getPanelSocioBuscar().getTablaBusqueda().getValueAt(filaSeleccionada, 0).toString();
			if(DNI!=null){
				socio_temp = obtenerSocio(DNI);
				vista.getPanelSocioDatos().modificarSocio(socio_temp);
				cuotas = ControladorCuota.getInstance().verHistorialCuotas(socio_temp);
				if(cuotas!=null)
					actualizarTablaCuotas();
			
				ControladorColaboradores.getInstance(vista).mostrarVistaModificarSocio();
			}
		}
	}
	public void nuevoSocio() {
		socio_temp = null;
		String[] datos = new String[25];
		for(int i=0; i<25; i++)
		datos[i]="";
		vista.getPanelSocioDatos().escribirSocioDatos(datos);
		vista.getPanelSocioDatos().getTextNombre().setForeground(Color.BLACK);
		vista.getPanelSocioDatos().getTextApellidos().setForeground(Color.BLACK);
		vista.getPanelSocioDatos().getTextDNI().setForeground(Color.BLACK);
		vista.getPanelSocioDatos().getTextFN().setForeground(Color.BLACK);
		vista.getPanelSocioDatos().getTextEmail().setForeground(Color.BLACK);
		vista.getPanelSocioDatos().getTextDomicilio().setForeground(Color.BLACK);
		vista.getPanelSocioDatos().getTextLocalidad().setForeground(Color.BLACK);
		vista.getPanelSocioDatos().getTextProvincia().setForeground(Color.BLACK);
		vista.getPanelSocioDatos().getTextCP().setForeground(Color.BLACK);
		vista.getPanelSocioDatos().getTextTelfFijo().setForeground(Color.BLACK);
		vista.getPanelSocioDatos().getTextTelMovil().setForeground(Color.BLACK);
		vista.getPanelSocioDatos().getTextUsuario().setForeground(Color.BLACK);
	}
	
	/**
		* Listener controlador del boton Guardar Cuota socio
	*/
	public class btGuardarCuotaListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean datosCorrectos = true;
			
			if( !TestDatos.isMoney( vista.getPanelSocioDatos().getTextCantidadCuota().getText()) ) {
                vista.getPanelSocioDatos().getTextCantidadCuota().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isOnlyDigit( vista.getPanelSocioDatos().getTextIntervaloCuotaSocio().getText()) ) {
                vista.getPanelSocioDatos().getTextIntervaloCuotaSocio().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isFecha( vista.getPanelSocioDatos().getTextFechaIniCuotaSocio().getText()) ) {
                vista.getPanelSocioDatos().getTextFechaIniCuotaSocio().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isFecha( vista.getPanelSocioDatos().getTextFechaFinCuotaSocio().getText()) ) {
                vista.getPanelSocioDatos().getTextFechaFinCuotaSocio().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if(!datosCorrectos){
                vista.getPanelSocioDatos().setTextLabelErrorCuota("Los campos en rojo tienes errores.");
			}
			else{
				vista.getPanelSocioDatos().setTextLabelErrorCuotaVisible(false);
				Cuota cuota = new Cuota();
				
				cuota.setCantidad(Double.parseDouble(vista.getPanelSocioDatos().getTextCantidadCuota().getText()));
				cuota.setIntervaloPagos(Integer.parseInt(vista.getPanelSocioDatos().getTextIntervaloCuotaSocio().getText()));
				try {
					cuota.setFechaInicial(TestDatos.formatter.parse(vista.getPanelSocioDatos().getTextFechaIniCuotaSocio().getText()));
				} catch (ParseException ex) {
					Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
				}
				try {
					cuota.setFechaFin(TestDatos.formatter.parse(vista.getPanelSocioDatos().getTextFechaFinCuotaSocio().getText()));
				} catch (ParseException ex) {
					Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
				}
				try {
					cuota.setFechaUltimoPago(TestDatos.formatter.parse(vista.getPanelSocioDatos().getTextFechaIniCuotaSocio().getText()));
				} catch (ParseException ex) {
					Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
				}
				cuota.setSocio(socio_temp);
				if(cuota.getFechaInicio().after(cuota.getFechaFin())){
					vista.getPanelSocioDatos().setTextLabelErrorCuota("La fecha inicial es superior a la fecha final");
				}
				else{
					ControladorCuota.getInstance().anadirCuota(cuota);
					cuota = obtenerCuota(socio_temp);
					cuotas = ControladorCuota.getInstance().verHistorialCuotas(socio_temp);
					if(cuotas==null)
						cuotas = new ArrayList<Cuota>();
					cuotas.add(cuota);
					actualizarTablaCuotas();
					vista.getPanelSocioDatos().setTextLabelErrorCuota("La cuota ha sido añadida");
				}
			}
		}
	}
	void actualizarTablaCuotas(){
		TableModel tableModel = new TableModel() {

			@Override
			public int getRowCount() {
				return cuotas.size();
			}

			@Override
			public int getColumnCount() {
				return columnNamesCuotas.length;
			}

			@Override
			public String getColumnName(int i) {
				return columnNamesCuotas[i];
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
						return cuotas.get(row).getCantidad();
					case 1:
						return cuotas.get(row).getFechaInicio();
					case 2:
						return cuotas.get(row).getFechaFin();
					case 3:
						return cuotas.get(row).getIntervaloPagos();
					case 4:
						return cuotas.get(row).getFechaUltimoPago();
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
		vista.getPanelSocioDatos().getTablaCuotas().setModel(tableModel);
	}
	
	/**
		* Listener controlador del boton Guardar Colaboracion socio
	*/
	
	public class btGuardarColaboracionesListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		boolean datosCorrectos=true;			
			if( !TestDatos.isMoney( vista.getPanelSocioDatos().getTextCantidadColaboracion().getText()) ) {
                vista.getPanelSocioDatos().getTextCantidadColaboracion().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isFecha( vista.getPanelSocioDatos().getTextFechaColaboracion().getText()) ) {
                vista.getPanelSocioDatos().getTextFechaColaboracion().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isDomicilio( vista.getPanelSocioDatos().getTextConceptoColaboracion().getText()) ) {
                vista.getPanelSocioDatos().getTextConceptoColaboracion().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if (!datosCorrectos) {
                vista.getPanelSocioDatos().setTextLabelErrorColaboracion("Los campos en rojo tienes errores.");
            }
			else {
				vista.getPanelSocioDatos().setTextLabelErrorColaboracionVisible(false);
				//Tipo es colaboracion
				if(vista.getPanelSocioDatos().getTipoColaboracion().equals("Colaboracion")){
					Colaboracion colaboracion = new Colaboracion();
					colaboracion.setImporte(Float.parseFloat(vista.getPanelSocioDatos().getTextCantidadColaboracion().getText()));
					colaboracion.setConcepto(vista.getPanelSocioDatos().getTextConceptoColaboracion().getText());
					try {	
						colaboracion.setFecha(TestDatos.formatter.parse(vista.getPanelSocioDatos().getTextFechaColaboracion().getText()));				
					} catch (ParseException ex) {
						Logger.getLogger(ControladorColaboracion.class.getName()).log(Level.SEVERE, null, ex);
					}
					socio_temp = obtenerSocio(vista.getPanelSocioDatos().getTextDNI().getText());
					colaboracion.setOIDColaborador(socio_temp.getOID());
					colaboracion.setOIDVoluntario(ControladorPrincipal.getInstance().getVoluntario().getOID());

					ControladorColaboracion.getInstance().anadirColaboracion(colaboracion);
					vista.getPanelSocioDatos().setTextLabelErrorColaboracion("La colaboracion ha sido añadida");
				}
				//Tipo es cuota
				else{
					PagoCuota pago= new PagoCuota();
					pago.setImporte(Float.parseFloat(vista.getPanelSocioDatos().getTextCantidadColaboracion().getText()));
					pago.setConcepto(vista.getPanelSocioDatos().getTextConceptoColaboracion().getText());
					try {	
						pago.setFecha(TestDatos.formatter.parse(vista.getPanelSocioDatos().getTextFechaColaboracion().getText()));				
					} catch (ParseException ex) {
						Logger.getLogger(ControladorColaboracion.class.getName()).log(Level.SEVERE, null, ex);
					}
					socio_temp = obtenerSocio(vista.getPanelSocioDatos().getTextDNI().getText());
					pago.setSocio(socio_temp);
					pago.setVoluntario(ControladorPrincipal.getInstance().getVoluntario());

					ControladorPagoCuota.getInstance().anadirPagoCuota(pago);
					vista.getPanelSocioDatos().setTextLabelErrorColaboracion("El pago de la cuota ha sido añadido");
				}
			}
		}
	}
}
