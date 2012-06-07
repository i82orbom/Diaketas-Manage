
package Controladores.Colaborador;

import Controladores.ControladorColaboradores;
import Controladores.TestDatos;
import Controladores.Voluntario.ControladorVoluntario;
import JDBC.C_PersonaJDBC;
import Modelo.C_Persona;
import Modelo.Colaboracion;
import Modelo.Movimiento;
import Vistas.Paneles.Colaboradores.VistaColaboradores;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 ** NOMBRE CLASE:
 **	  ControladorC_Persona
 **
 ** DESCRIPCION:
 **       Controlador del panel de coladores de typo persona
 **
 **
 ** DESARROLLADO POR:
 *          Raphael Colleau (RC)
 **
 **
 ** SUPERVISADO POR:
 **
 **
 ** HISTORIA:
 ** 	000 - 1 mai 2012 - RC - Creacion
 *      001 - 2 mai 2012 - RC - adicion de metodos
 *      002 - 3 mai 2012 - RC - relleno metodos
 *      003 - 5 mai 2012 - RC - comproba datos
 **
 ** NOTAS:
 **
 **
 */
public class ControladorC_Persona {

    /**
     * PATRON DE DISEÑO SINGLETON
     */
    private static ControladorC_Persona instancia = null;

    public static ControladorC_Persona getInstance(VistaColaboradores pvista) {

        if (instancia == null) {
            instancia = new ControladorC_Persona(pvista);
        }
        return instancia;

    }
	
	VistaColaboradores vista;
	C_Persona colaborador_temp = null;
	ArrayList<C_Persona> personas = null;
	ArrayList<Colaboracion> colaboraciones = new ArrayList<Colaboracion>();
	ArrayList<Movimiento> mov = new ArrayList<Movimiento>();
	
	private String[] columnNames = {"DNI", "Nombre", "Direccion", "Localidad", "Teléfono", "Movil", "Codigo Postal"};
	private String[] columnNamesColaboraciones = {"Cantidad", "Fecha", "Concepto", "Confirmada por"};

    public ControladorC_Persona(VistaColaboradores pvista) {
		vista=pvista;
		
		vista.getPanelColaboradorDatos().getBtGuardarDatosColaborador().addActionListener(new btGuardarColaboradorListener());
		vista.getPanelColaboradorDatos().getBtBorrarDatosColaborador().addActionListener(new btBorrarColaboradorListener());
		
		vista.getPanelColaboradorBuscar().getBtBuscarColaborador().addActionListener(new btBuscarColaboradorListener());
		vista.getPanelColaboradorBuscar().getBtConsultarColaborador().addActionListener(new btConsultarColaboradorListener());
		vista.getPanelColaboradorBuscar().getBtEliminarColaborador().addActionListener(new btEliminarColaboradorListener());
    }
    

    public boolean anadirC_Persona(C_Persona persona) {
		if (obtenerPersona(persona.getDNI())!= null) {
            JOptionPane.showMessageDialog(null, "Un colaborador con este DNI ya existe.");
            return false;
        }
        boolean resulto = false;
        try {
            resulto = C_PersonaJDBC.getInstance().añadirC_Persona(persona);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resulto;
    }

    public C_Persona obtenerC_Persona (String DNI) {
        C_Persona tempP;

        try {
            tempP = C_PersonaJDBC.getInstance().obtenerC_Persona(DNI);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
            tempP = null;
        }

        return tempP;
    }

    public boolean modificarC_Persona (C_Persona persona) {
        if (obtenerPersona(persona.getDNI()).getOID() != persona.getOID()) {
            JOptionPane.showMessageDialog(null, "Un colaborador con este DNI ya existe.");
            return false;
        }
        boolean resulto = false;
        try {
            resulto = C_PersonaJDBC.getInstance().modificarDatosC_Persona(persona);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resulto;
    }

    public boolean eliminarC_Persona (C_Persona tempP) {
        boolean resulto = false;

        try {
            resulto = C_PersonaJDBC.getInstance().eliminarC_Persona(tempP);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resulto;
    }

    public ArrayList<C_Persona> buscarC_Persona (String tipoBusqueta, String valor) {
        ArrayList<C_Persona> personas;
        try {
            personas = C_PersonaJDBC.getInstance().buscarC_Persona(tipoBusqueta, valor);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return personas;
    }

    public boolean cambiarC_PersonaSocio(C_Persona tempP, String usuarioN) {

        // TODO validar usuario

        // if usuarioValido
        String contrasena = ControladorVoluntario.genContrasena();

        boolean resulto = false;

        try {
            C_PersonaJDBC.getInstance().hacerSocio(tempP, usuarioN, contrasena);
            resulto = true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resulto;
    }
	
	public C_Persona obtenerPersona(String DNI){
		C_Persona persona = null;
		try {
			persona = C_PersonaJDBC.getInstance().obtenerC_Persona(DNI);
		} catch (SQLException ex) {
			Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
		}
		return persona;
	}
	
	public class btGuardarColaboradorListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean datosCorrectos = true;
			if( !TestDatos.isNombre( vista.getPanelColaboradorDatos().getTextNombre().getText()) ) {
                vista.getPanelColaboradorDatos().getTextNombre().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isNombre( vista.getPanelColaboradorDatos().getTextApellidos().getText()) ) {
                vista.getPanelColaboradorDatos().getTextApellidos().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isDNI( vista.getPanelColaboradorDatos().getTextDNI().getText()) ) {
                vista.getPanelColaboradorDatos().getTextDNI().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isFecha( vista.getPanelColaboradorDatos().getTextFN().getText())) {
                vista.getPanelColaboradorDatos().getTextFN().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isEmail( vista.getPanelColaboradorDatos().getTextEmail().getText()) || vista.getPanelColaboradorDatos().getTextEmail().getText().equals("")) {
                vista.getPanelColaboradorDatos().getTextEmail().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isDomicilio( vista.getPanelColaboradorDatos().getTextDomicilio().getText()) ) {
                vista.getPanelColaboradorDatos().getTextDomicilio().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isNombre( vista.getPanelColaboradorDatos().getTextLocalidad().getText()) ) {
                vista.getPanelColaboradorDatos().getTextLocalidad().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isNombre( vista.getPanelColaboradorDatos().getTextProvincia().getText()) ) {
                vista.getPanelColaboradorDatos().getTextProvincia().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isCodigoPostal( vista.getPanelColaboradorDatos().getTextCP().getText()) ) {
                vista.getPanelColaboradorDatos().getTextCP().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isTelefonoOFax( vista.getPanelColaboradorDatos().getTextTelfFijo().getText()) || vista.getPanelColaboradorDatos().getTextTelfFijo().getText().equals("")) {
                vista.getPanelColaboradorDatos().getTextTelfFijo().setForeground(Color.RED);
                datosCorrectos = false;
            }
			if( !TestDatos.isTelefonoOFax( vista.getPanelColaboradorDatos().getTextTelMovil().getText()) || vista.getPanelColaboradorDatos().getTextTelMovil().getText().equals("")) {
                vista.getPanelColaboradorDatos().getTextTelMovil().setForeground(Color.RED);
                datosCorrectos = false;
            }
		
			if (!datosCorrectos) {
                vista.getPanelColaboradorDatos().setTextLabelError("Los campos en rojo tienes errores.");
            } else {
				if(colaborador_temp==null){
					C_Persona colaborador = new C_Persona();
					colaborador.setNombre(vista.getPanelColaboradorDatos().getTextNombre().getText());
					colaborador.setApellidos(vista.getPanelColaboradorDatos().getTextApellidos().getText());
					colaborador.setDNI(vista.getPanelColaboradorDatos().getTextDNI().getText());
					try {
						colaborador.setFechaDeNacimiento(TestDatos.formatter.parse(vista.getPanelColaboradorDatos().getTextFN().getText()));
					} catch (ParseException ex) {
						Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
					}
					colaborador.setEmail(vista.getPanelColaboradorDatos().getTextEmail().getText());
					colaborador.setSexo(vista.getPanelColaboradorDatos().getSexo());
					colaborador.setDireccion(vista.getPanelColaboradorDatos().getTextDomicilio().getText());
					colaborador.setProvincia(vista.getPanelColaboradorDatos().getTextProvincia().getText());
					colaborador.setLocalidad(vista.getPanelColaboradorDatos().getTextLocalidad().getText());
					colaborador.setCP(vista.getPanelColaboradorDatos().getTextCP().getText());
					colaborador.setTelefonoFijo(vista.getPanelColaboradorDatos().getTextTelfFijo().getText());
					colaborador.setTelefonoMovil(vista.getPanelColaboradorDatos().getTextTelMovil().getText());
					boolean exito = anadirC_Persona(colaborador);
					if (exito) {
							colaborador_temp = obtenerC_Persona(colaborador.getDNI());
							vista.getPanelColaboradorDatos().getJtabbedPaneColaborador().setEnabledAt(1, true);
							vista.getPanelColaboradorDatos().setTextLabelError("Colaborador añadido correctamente.");
					} else {
							vista.getPanelColaboradorDatos().setTextLabelError("El colaborador no ha sido añadido.");
					}
				}
				else{
					
					colaborador_temp.setNombre(vista.getPanelColaboradorDatos().getTextNombre().getText());
					colaborador_temp.setApellidos(vista.getPanelColaboradorDatos().getTextApellidos().getText());
					colaborador_temp.setDNI(vista.getPanelColaboradorDatos().getTextDNI().getText());
					try {
						colaborador_temp.setFechaDeNacimiento(TestDatos.formatter.parse(vista.getPanelColaboradorDatos().getTextFN().getText()));
					} catch (ParseException ex) {
						Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
					}
					colaborador_temp.setEmail(vista.getPanelColaboradorDatos().getTextEmail().getText());
					colaborador_temp.setSexo(vista.getPanelColaboradorDatos().getSexo());
					colaborador_temp.setDireccion(vista.getPanelColaboradorDatos().getTextDomicilio().getText());
					colaborador_temp.setProvincia(vista.getPanelColaboradorDatos().getTextProvincia().getText());
					colaborador_temp.setLocalidad(vista.getPanelColaboradorDatos().getTextLocalidad().getText());
					colaborador_temp.setCP(vista.getPanelColaboradorDatos().getTextCP().getText());
					colaborador_temp.setTelefonoFijo(vista.getPanelColaboradorDatos().getTextTelfFijo().getText());
					colaborador_temp.setTelefonoMovil(vista.getPanelColaboradorDatos().getTextTelMovil().getText());
					boolean exito = modificarC_Persona(colaborador_temp);
					if (exito) {
							vista.getPanelColaboradorDatos().setTextLabelError("Colaborador modificado correctamente.");
					} else {
							vista.getPanelColaboradorDatos().setTextLabelError("El colaborador no ha sido modificado.");
					}
				}
			}
		}
	}
	
	public class btBorrarColaboradorListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(colaborador_temp==null){
				String[] datos = new String[12];
				for(int i=0; i<12; i++)
				datos[i]="";
				vista.getPanelColaboradorDatos().escribirColaboradorDatos(datos);
			}
			else{
				if(JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar el Colaborador?", "Eliminar Colaborador", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
					boolean exito = eliminarC_Persona(colaborador_temp);
					if (exito) {
						String[] datos = new String[12];
						for(int i=0; i<12; i++)
						datos[i]="";
						vista.getPanelColaboradorDatos().escribirColaboradorDatos(datos);
						colaborador_temp=null;
						vista.getPanelColaboradorDatos().setTextLabelError("Colaborador eliminado correctamente.");
					} else {
							vista.getPanelColaboradorDatos().setTextLabelError("El colaborador no ha sido eliminado.");
					}
				}
			}
		}	
	}
	
	public class btBuscarColaboradorListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String valor = vista.getPanelColaboradorBuscar().getValorBusquedaColaborador();
			String tipo = vista.getPanelColaboradorBuscar().getTipoBusquedaColaborador();
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
						
			personas = buscarC_Persona(tipo, valor);
			actualizarTablaBuscar();
		}
	}
	public void actualizarTablaBuscar(){
		TableModel tableModel = new TableModel() {
				
				
                @Override
                public int getRowCount() {
                    return personas.size();
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
                            return personas.get(row).getDNI();
                        case 1:
                            return personas.get(row).getNombre();
                        case 2:
                            return personas.get(row).getDireccion();
                        case 3:
                            return personas.get(row).getLocalidad();
                        case 4:
                            return personas.get(row).getTelefonoFijo();
						case 5:
                            return personas.get(row).getTelefonoMovil();
						case 6:
                            return personas.get(row).getCP();
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
            
            vista.getPanelColaboradorBuscar().getTablaBusqueda().setModel(tableModel);
	}
	
	public class btConsultarColaboradorListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int filaSeleccionada = vista.getPanelColaboradorBuscar().getTablaBusqueda().getSelectedRow();
			if(filaSeleccionada!=-1)
				colaborador_temp = personas.get(filaSeleccionada);
				vista.getPanelColaboradorDatos().modificarColaborador(colaborador_temp);
							
				ControladorColaboradores.getInstance(vista).mostrarVistaModificarColaborador();
		}
	}		
	public class btEliminarColaboradorListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			C_Persona persona = null;
			
			int filaSeleccionada = vista.getPanelColaboradorBuscar().getTablaBusqueda().getSelectedRow();
			if(filaSeleccionada!=-1)
				persona = personas.get(filaSeleccionada);

			if(JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar el Voluntario?", "Eliminar Voluntario", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				if (persona!=null && eliminarC_Persona(persona)) {
					personas.remove(filaSeleccionada);
					actualizarTablaBuscar();
					vista.getPanelColaboradorBuscar().setTextLabelError("El Socio ha sido eliminado del sistema.");
				}
				else {
					vista.getPanelColaboradorBuscar().setTextLabelError("Error : el Socio no ha sido eliminado del sistema.");
				}
			}
			
		}
	}
}
