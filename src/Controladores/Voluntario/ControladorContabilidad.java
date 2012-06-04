
package Controladores.Voluntario;

import Controladores.TestDatos;
import JDBC.AyudaJDBC;
import JDBC.C_EmpresaJDBC;
import JDBC.C_PersonaJDBC;
import JDBC.MovimientoJDBC;
import Modelo.*;
import Vistas.Paneles.Voluntario.PanelVoluntarioContabilidad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 ** NOMBRE CLASE:
 **	  ControladorContabilidad
 **
 ** DESCRIPCION:
 **
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
 ** 	000 - 16 mai 2012 - RC - Creacion
 ** 	001 - 22 mai 2012 - RC - Adicion metodos relacionados con modelo
 **
 ** NOTAS:
 **
 **
 */
public class ControladorContabilidad {

    /**
     * PATRON DE DISEÑO SINGLETON
     */
    private static ControladorContabilidad instancia = null;

    public static ControladorContabilidad getInstance(PanelVoluntarioContabilidad pvista) {

        if (instancia == null) {
            instancia = new ControladorContabilidad(pvista);
        }
        return instancia;
    }
        
    private PanelVoluntarioContabilidad vista;
    
    private ArrayList<Movimiento> gastos;
    private ArrayList<Movimiento> ingresos;
	
	String[] columnasGastos = {"Fecha","Gasto","Dni"};
	String[] columnasIngresos = {"Fecha","Ingreso","Dni"};
	
	private SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
	
	/*
	 * El arrayList de ingresos debe estar rellenado
	 */
	private void actualizarTablaIngresos(){
		vista.getTbIngresos().setModel(new TableModel() {

			@Override
			public int getRowCount() {
				return ingresos.size();
			}

			@Override
			public int getColumnCount() {
				return columnasIngresos.length;
			}

			@Override
			public String getColumnName(int columnIndex) {
				return columnasIngresos[columnIndex];
			}

			@Override
			public Class<?> getColumnClass(int i) {
				return String.class;
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
			Colaboracion col;
			C_Empresa emp;
			C_Persona per;
				switch(columnIndex){
					case 0:
						return formateador.format(ingresos.get(rowIndex).getFecha());
					case 1:
						return ingresos.get(rowIndex).getImporte();
					case 2:
						if(ingresos.get(rowIndex).getClass() == Colaboracion.class){
							col = (Colaboracion)ingresos.get(rowIndex);
							try{
								emp = C_EmpresaJDBC.getInstance().obtenerC_Empresa(col.getOIDColaborador());
								return emp.getCIF();
							}
							catch(SQLException ex){
								Logger.getLogger(ControladorContabilidad.class.getName()).log(Level.SEVERE, null, ex);
							}
							try{
								per = C_PersonaJDBC.getInstance().obtenerC_Persona(col.getOIDColaborador().toString());
								return per.getDNI();
							}
							catch(SQLException ex){
								Logger.getLogger(ControladorContabilidad.class.getName()).log(Level.SEVERE, null, ex);
							}
							return "";
						}
						
					default: return "";
				}
			}

			@Override
			public void setValueAt(Object o, int i, int i1) {
				
			}

			@Override
			public void addTableModelListener(TableModelListener tl) {
				
			}

			@Override
			public void removeTableModelListener(TableModelListener tl) {
				
			}
			
		});
	}

	private void actualizarTablaGastos(){
		vista.getTbGastos().setModel(new TableModel() {

			@Override
			public int getRowCount() {
				return gastos.size();
			}

			@Override
			public int getColumnCount() {
				return columnasGastos.length;
			}

			@Override
			public String getColumnName(int columnIndex) {
				return columnasGastos[columnIndex];
			}

			@Override
			public Class<?> getColumnClass(int i) {
				return String.class;
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
			Gasto gas;
			Ayuda ayu;
				switch(columnIndex){/*
					case 0:
						return formateador.format(gastos.get(rowIndex).getFecha());
					case 1:
						return gastos.get(rowIndex).getImporte();
					case 2:
						if(gastos.get(rowIndex).getClass() == Gasto.class){
							gas = (Gasto)gastos.get(rowIndex);
							try{
								ayu = AyudaJDBC.getInstance().obtenerAyuda(gas.getOIDAyuda());
								return ayu.getBeneficiarioDeAyuda().getNIF();
							}
							catch(SQLException ex){
								Logger.getLogger(ControladorContabilidad.class.getName()).log(Level.SEVERE, null, ex);
							}
							return "";
						}
						*/
					default: return "";
				}
			}

			@Override
			public void setValueAt(Object o, int i, int i1) {
				
			}

			@Override
			public void addTableModelListener(TableModelListener tl) {
				
			}

			@Override
			public void removeTableModelListener(TableModelListener tl) {
				
			}
			
		});	
	}
	
	private void obtenerIngresos(Date fechaInicial, Date fechaFin) {
        
        try {
            ingresos = MovimientoJDBC.getInstance().obtenerDatosIngresos(fechaInicial, fechaFin);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorContabilidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void obtenerGastos(Date fechaInicial, Date fechaFin) {
        
        try {
            gastos = MovimientoJDBC.getInstance().obtenerDatosGastos(fechaInicial, fechaFin);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorContabilidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	private float obtenerBalance(Date fechaInicial, Date fechaFin){
		if(ingresos.size() <= 0)
			obtenerIngresos(fechaInicial,fechaFin);
		if(gastos.size() <= 0)
			obtenerGastos(fechaInicial,fechaFin);
		
		float t_ing=0,t_gas=0;
		for(int i=0;i<ingresos.size();i++)
			t_ing = t_ing + ingresos.get(i).getImporte();
		for(int i=0;i<gastos.size();i++)
			t_gas = t_gas + gastos.get(i).getImporte();
		return t_ing - t_gas;
	}
	
    public ControladorContabilidad(PanelVoluntarioContabilidad vista) {
        this.vista = vista;
        
        vista.getBtBuscar().addActionListener(new BtBuscarContabilidad());
        vista.getFieldFechaFin().setText(TestDatos.formatter.format(new Date()));
    }

    // Metodos JDBC
    public boolean registrarGasto(float importe, String concepto) {
        // TODO comprobar datos

        Movimiento movimiento = new Movimiento();
        movimiento.setImporte(importe);
        movimiento.setConcepto(concepto);
        movimiento.setTipo('G');
        //movimiento.setFecha(SimpleDateFormat.getDateInstance());

        boolean exito;
        try {
            exito = MovimientoJDBC.getInstance().registrarDatosGasto(movimiento);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorContabilidad.class.getName()).log(Level.SEVERE, null, ex);
            exito = false;
        }

        return exito;
    }

    public boolean registrarGastoAyuda(Ayuda ayuda) {
        Movimiento movimiento = new Movimiento();
        movimiento.setImporte(ayuda.getImporte());
        movimiento.setConcepto(ayuda.getObservaciones());
      //  movimiento.setTipo('G');
        //movimiento.setFecha(SimpleDateFormat.getDateInstance());

        boolean exito;
      /*  try {
            exito = MovimientoJDBC.getInstance().registrarDatosGastoAyuda(movimiento, ayuda);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorContabilidad.class.getName()).log(Level.SEVERE, null, ex);
            exito = false;
        }
*/
        return true;
    }    

    // TODO Listeners de los botones
    
    class BtBuscarContabilidad implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Date fechaInicio = new Date();
            Date fechaFin = new Date();
            try { 
                fechaInicio = TestDatos.formatter.parse(vista.getFieldFechaInicio().getText());
                fechaFin = TestDatos.formatter.parse(vista.getFieldFechaFin().getText()); 
            } catch (Exception e) {
                vista.setTextLabelError("Fecha : dd/MM/YYYY");
            }
            
            vista.getCuadroBalance().setText(Float.toString(obtenerBalance(fechaInicio, fechaFin)));
        }
       
    }
}
