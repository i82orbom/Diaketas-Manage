
package Controladores.BolsaDeTrabajo;

import Controladores.ControladorBolsaTrabajo;
import Controladores.ControladorErrores;
import Controladores.ControladorPrincipal;
import Controladores.TestDatos;
import JDBC.*;
import Modelo.*;
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

		// Buscar Ofertas
		vista.getDemandaBuscar().getBTBuscar().addActionListener(new ListenerBtBuscarDemanda());
		vista.getDemandaBuscar().getBTEliminar().addActionListener(new ListenerBtEliminarDemandaBuscada());
		vista.getDemandaBuscar().getBTBuscar().addActionListener(new ListenerBtConsultarDemanda());
	}

	public static ControladorDemanda getInstance(VistaBolsaTrabajo panelDemanda){
		if (instancia == null) instancia = new ControladorDemanda(panelDemanda);
		return instancia;
	}
        private void actualizarTablaDemandas(){
            
            
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

	public boolean actualizarDemanda(Demanda de){

		return true;
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

	public boolean eliminarDemanda(Demanda demanda){
		JOptionPane.showConfirmDialog(vista, vista);
		boolean exito = true;

		return exito;
	}

	
	public boolean validarDatosDemanda(Demanda demanda){
		return true;
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

			demandaConsultada.setDescripcionValidaLaboral(vista.getDemandaDatos().getTextoHistoriaLaboral());
/*			ofertaConsultada.setDescripcionOferta(vista.getOfertaDatos().getTextoDescripcionOferta());
			ofertaConsultada.setDuracionContrato(Integer.parseInt(vista.getOfertaDatos().getTextoNPuestos()));
			ofertaConsultada.setPlazasOfertadas(Integer.parseInt(vista.getOfertaDatos().getTextoNPuestos()));
			ofertaConsultada.setTipoContrato(vista.getOfertaDatos().getTextoTipoContrato());
//			ofertaConsultada.setIdSector(SectorJDBC.getInstance().getOID(vista.getOfertaDatos().getcbSector()));*/
			try { demandaConsultada.setIdBeneficiario(BeneficiarioJDBC.getInstance().obtenerBeneficiario(vista.getDemandaDatos().getTextoNIF()));
			} catch (SQLException ex){
			}
			demandaConsultada.setIdVoluntario(ControladorPrincipal.getInstance().getVoluntario());
			demandaConsultada.setFecha(new Date());	// Fecha actual

			actualizarDemanda(demandaConsultada);			// Se envia el objeto al controlador
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
				if (eliminarDemanda(listaDemandas.get(vista.getDemandaBuscar().getTablaBusquedaDemandante().getSelectedRow()))){
					ControladorErrores.mostrarMensaje("La demanda ha sido borrada");
				}
			}
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
