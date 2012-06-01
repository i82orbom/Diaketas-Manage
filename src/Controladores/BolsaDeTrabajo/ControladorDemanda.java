
package Controladores.BolsaDeTrabajo;

import Controladores.ControladorBolsaTrabajo;
import Controladores.ControladorErrores;
import Controladores.ControladorPrincipal;
import Controladores.TestDatos;
import JDBC.BeneficiarioJDBC;
import JDBC.DemandaJDBC;
import JDBC.SectorJDBC;
import Modelo.Beneficiario;
import Modelo.Demanda;
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
        private Demanda demandaConsultada;
	ArrayList<Demanda> listaDemandas;
        String[] columnNames = {"DNI", "Nombre y Apellidos", "Sector", "Fecha de demanda"};
        
	private ControladorDemanda(VistaBolsaTrabajo pvista){
		/**
		* Establece como ventana padre la pasada como parámetro
		*/
		vista = pvista;

		// Botones
                // Nueva Demanda
		vista.getDemandaDatos().getbtGuardar().addActionListener(new ControladorDemanda.ListenerBtGuardarDemanda());
		vista.getDemandaDatos().getbtLimpiar().addActionListener(new ControladorDemanda.ListenerBtLimpiar());

		// Modificar Demanda
		vista.getDemandaDatos().getBTModificar().addActionListener(new ListenerBtModificarDemanda());
		vista.getDemandaDatos().getBTGuardarCambios().addActionListener(new ListenerBtActualizarDemanda());
		vista.getDemandaDatos().getBTEliminar().addActionListener(new ListenerBtEliminarDemanda());

		// Buscar Demandas
		vista.getDemandaBuscar().getBTBuscar().addActionListener(new ListenerBtBuscarDemanda());
		vista.getDemandaBuscar().getBTEliminar().addActionListener(new ListenerBtEliminarDemandaBuscada());
		vista.getDemandaBuscar().getBTBuscar().addActionListener(new ListenerBtConsultarDemanda());
	}

	public static ControladorDemanda getInstance(VistaBolsaTrabajo panelDemanda){
		if (instancia == null) instancia = new ControladorDemanda(panelDemanda);
		return instancia;
	}
        
        private void actualizarTablaDemandas(){
            TableModel tableModel = new TableModel() {
                @Override
                public int getRowCount() {
                    return listaDemandas.size();
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
                            return listaDemandas.get(fil).getIdBeneficiario().getNIF();
                        case 1:
                            return listaDemandas.get(fil).getIdBeneficiario().getNombre()+listaDemandas.get(fil).getIdBeneficiario().getApellidos();
                        case 2:
                            return listaDemandas.get(fil).getIdSector().getDescripcion();
                        case 3:
                            return TestDatos.formatter.format(listaDemandas.get(fil).getFecha());
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
            vista.getDemandaBuscar().getTablaBusquedaDemandante().setModel(tableModel);

            
        } 
	/* Métodos del controlador */
	public boolean insertarDemanda(Demanda demanda){
		try{
			DemandaJDBC.getInstance().InsertarDemanda(demanda);

                }
		catch (SQLException ex){
			ControladorErrores.mostrarError("Error al insertar una demanda\n"+ex);
			return false;
		}
                vista.getDemandaDatos().getlabelError().setForeground(Color.black);
                vista.getDemandaDatos().getlabelError().setText("Demanda añadida correctamente");
		return true;
	}

	public Demanda obtenerDatosDemanda(int oid){
		Demanda demanda = new Demanda();
		return demanda;
	}

	public void actualizarDemanda(Demanda de){

		try{
			DemandaJDBC.getInstance().ActualizarDemanda(de);
			vista.getOfertaDatos().getlabelError().setText("La demanda ha sido actualizada");
		}
		catch (SQLException ex){
			ControladorErrores.mostrarError("La demanda no ha podido actualizarse:\n"+ex);
		}
	}

	public ArrayList<Demanda> obtenerListaDemandas(String DNI, String sectorDesc, int antiguedad){
		Long beneficiarioOID = -1l, sectorOID = -1l;

		if (!DNI.equals("")){
			Beneficiario beneficiario = null;
			try{ beneficiario = BeneficiarioJDBC.getInstance().obtenerBeneficiario(DNI);
			} catch (SQLException ex){
				ControladorErrores.mostrarError("Error al obtener el Beneficiario:\n"+ex);
			}
			beneficiarioOID = beneficiario.getOID();
		}

		if (!sectorDesc.equals("")){
			Sector sector = null;
			try{ sector = SectorJDBC.getInstance().ConsultarSector(sectorDesc);
			} catch (SQLException ex){
				ControladorErrores.mostrarError("Error al obtener el sector:\n"+ex);
			}
			sectorOID = sector.getOID();
		}

		try{
			listaDemandas = DemandaJDBC.getInstance().FiltrarDemandas(DNI, sectorDesc, antiguedad);
			actualizarTablaDemandas();
		} catch (SQLException ex){
			ControladorErrores.mostrarError("Error al obtener la lista de ofertas:\n"+ex.getMessage());
		}

		return listaDemandas;
	}

	public ArrayList<Demanda> filtrarDemandas(String s){
		ArrayList<Demanda> a = new ArrayList<Demanda>();
		return a;
	}

	public void eliminarDemanda(Demanda demanda){
                if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar la demanda?", "Eliminar Demanda", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    try{
                            DemandaJDBC.getInstance().EliminarDemanda(demanda);
                            ControladorErrores.mostrarMensaje("La demanda ha sido eliminada");
                    }
                    catch (SQLException ex){
                            ControladorErrores.mostrarError("La demanda no se ha eliminad:\n"+ex);
                    }
		}
		listaDemandas.remove(demanda);
		demandaConsultada = null;
		actualizarTablaDemandas();
		ControladorBolsaTrabajo.getInstance(null).mostrarBuscarDemanda();
                
	}



        public void setColorLabels(Color c){
		vista.getDemandaDatos().getLabelNif().setForeground(c);
                vista.getDemandaDatos().getLabelSector().setForeground(c);
                vista.getDemandaDatos().getLabelHistorialLaboral().setForeground(c);
	}
        
	/* Clases para ActionListener */


        
	public class ListenerBtGuardarDemanda implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Demanda  demanda = new Demanda();		// Se crean los objetos
                        Beneficiario beneficiario = null;
                        boolean exito = true;
                        
                        /*Comprobamos los datos*/
                        setColorLabels(Color.black);
                        
                        vista.getDemandaDatos().getlabelError().setForeground(Color.red);
                        vista.getDemandaDatos().getlabelError().setText(""); 
                        
                        String DNI = vista.getDemandaDatos().getTextoNIF();
                        String HistorialLaboral = vista.getDemandaDatos().getTextoHistoriaLaboral();
                        demanda.setDescripcionValidaLaboral(vista.getDemandaDatos().getTextoHistoriaLaboral());
            		demanda.setFecha(new Date());	// Fecha actual
                        
                        if(!TestDatos.isDNI(DNI)){
                            exito = false; vista.getDemandaDatos().getLabelNif().setForeground(Color.red);
                        }
                        if (!TestDatos.isNombre(HistorialLaboral)) {
                            exito = false; vista.getDemandaDatos().getLabelHistorialLaboral().setForeground(Color.red);
			}
                        if(!exito){
                            vista.getDemandaDatos().getlabelError().setForeground(Color.red);
                            vista.getDemandaDatos().getlabelError().setText("Los datos no son válidos");
                        }else{			// Se envia el objeto al controlador
                            try{
                                beneficiario = BeneficiarioJDBC.getInstance().obtenerBeneficiario(DNI);
                            }catch(SQLException ex){
                                exito = false; ControladorErrores.mostrarError("Error al obtener el beneficiario");
                            }
                            if (beneficiario==null){
                                    vista.getDemandaDatos().getlabelError().setText("El beneficiario no esta registrado");
                                    vista.getDemandaDatos().getLabelNif().setForeground(Color.red);
                                    exito = false;
                            }
                            if (exito){
					demanda.setIdBeneficiario(beneficiario);
					String descSector = vista.getDemandaDatos().getcbSector().getSelectedItem().toString();
					try{ demanda.setIdSector(SectorJDBC.getInstance().ConsultarSector(descSector));}
					catch (SQLException ex){ControladorErrores.mostrarError("Hubo un error con el sector:\n"+ex);}
					demanda.setDescripcionValidaLaboral(HistorialLaboral);
					demanda.setIdVoluntario(ControladorPrincipal.getInstance().getVoluntario());
					demanda.setFecha(new Date());
					if (!insertarDemanda(demanda)){			// Se envia el objeto al controlador
                                                vista.getDemandaDatos().getlabelError().setForeground(Color.red);
						vista.getDemandaDatos().getlabelError().setText("La demanda no ha sido añadida");
					}
					else {
						vista.getOfertaDatos().getlabelError().setText("Se ha añadido una oferta");
					}
				}
                        }
		}
	}

	public class ListenerBtLimpiar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			vista.getDemandaDatos().setTextNIF("");
                        vista.getDemandaDatos().setTextoHistoriaLaboral("");
                        vista.getDemandaDatos().getlabelError().setText("");
                        setColorLabels(Color.black);
		}
	}

	public class ListenerBtModificarDemanda implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Modificar Demanda");
			ControladorBolsaTrabajo.getInstance(null).mostrarModificarDemanda(demandaConsultada);
		}
	}

	public class ListenerBtActualizarDemanda implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Actualizar Demanda");
                Beneficiario beneficiario = null;
                boolean exito = true;

                /*Comprobamos los datos*/
                setColorLabels(Color.black);

                vista.getDemandaDatos().getlabelError().setForeground(Color.red);
                vista.getDemandaDatos().getlabelError().setText(""); 

                String DNI = vista.getDemandaDatos().getTextoNIF();
                String HistorialLaboral = vista.getDemandaDatos().getTextoHistoriaLaboral();

                if(!TestDatos.isDNI(DNI)){
                    exito = false; vista.getDemandaDatos().getLabelNif().setForeground(Color.red);
                }
                if (!TestDatos.isNombre(HistorialLaboral)) {
                    exito = false; vista.getDemandaDatos().getLabelHistorialLaboral().setForeground(Color.red);
                }
                if(!exito){
                    vista.getDemandaDatos().getlabelError().setForeground(Color.red);
                    vista.getDemandaDatos().getlabelError().setText("Los datos no son válidos");
                }else{			// Se envia el objeto al controlador
                    try{
                        beneficiario = BeneficiarioJDBC.getInstance().obtenerBeneficiario(DNI);
                    }catch(SQLException ex){
                        exito = false; ControladorErrores.mostrarError("Error al consultar beneficiario");
                    }
                    if (beneficiario==null){
                            vista.getDemandaDatos().getlabelError().setText("El beneficiario no esta registrado");
                            vista.getDemandaDatos().getLabelNif().setForeground(Color.red);
                            exito = false;
                    }
                    if (exito){
                        demandaConsultada.setIdBeneficiario(beneficiario);
                        String descSector = vista.getDemandaDatos().getcbSector().getSelectedItem().toString();
                        try{ demandaConsultada.setIdSector(SectorJDBC.getInstance().ConsultarSector(descSector));}
                        catch (SQLException ex){ControladorErrores.mostrarError("Hubo un error con el sector:\n"+ex);}
                        demandaConsultada.setDescripcionValidaLaboral(HistorialLaboral);
                        demandaConsultada.setIdVoluntario(ControladorPrincipal.getInstance().getVoluntario());
                        demandaConsultada.setFecha(new Date());
                        vista.getDemandaDatos().getlabelError().setText("");
                        actualizarDemanda(demandaConsultada);
                    }
                }
            }
	}

	public class ListenerBtEliminarDemanda implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Eliminar Demanda");
			eliminarDemanda(demandaConsultada);
		}
	}

	public class ListenerBtBuscarDemanda implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Buscar Demanda");
			String empresa = vista.getDemandaBuscar().getTextBusquedaDemandanteDNI();
			String sectorDesc = vista.getDemandaBuscar().getTextoSector();
			int antiguedad = vista.getDemandaBuscar().getAntiguedad();

			obtenerListaDemandas(empresa, sectorDesc, antiguedad);
		}
	}

	public class ListenerBtEliminarDemandaBuscada implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Eliminar Demanda");

                        if (vista.getDemandaBuscar().getTablaBusquedaDemandante().getSelectedRow() != -1) {
                            demandaConsultada = listaDemandas.get(vista.getDemandaBuscar().getTablaBusquedaDemandante().getSelectedRow());
			}
			if (demandaConsultada!=null) eliminarDemanda(demandaConsultada);
			else ControladorErrores.mostrarAlerta("No hay ninguna demanda seleccionada.");
		}
	}

	public class ListenerBtConsultarDemanda implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Consultar Oferta");
                if (vista.getDemandaBuscar().getTablaBusquedaDemandante().getSelectedRow() != -1) {
                    demandaConsultada = listaDemandas.get(vista.getDemandaBuscar().getTablaBusquedaDemandante().getSelectedRow());
                    ControladorBolsaTrabajo.getInstance(null).mostrarConsultarDemandas(demandaConsultada);
                }
            }
	}

}
