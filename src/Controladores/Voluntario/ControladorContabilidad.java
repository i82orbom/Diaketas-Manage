
package Controladores.Voluntario;

import JDBC.MovimientoJDBC;
import Modelo.Ayuda;
import Modelo.Movimiento;
import Vistas.Paneles.Voluntario.PanelVoluntarioContabilidad;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public ControladorContabilidad(PanelVoluntarioContabilidad vista) {
        this.vista = vista;
    }

    // TODO Metodos JDBC
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

    public boolean registrarGastoAyuda(float importe, String concepto, Ayuda ayuda) {
        Movimiento movimiento = new Movimiento();
        movimiento.setImporte(importe);
        movimiento.setConcepto(concepto);
        movimiento.setTipo('G');
        //movimiento.setFecha(SimpleDateFormat.getDateInstance());

        boolean exito;
        try {
            exito = MovimientoJDBC.getInstance().registrarDatosGastoAyuda(movimiento, ayuda);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorContabilidad.class.getName()).log(Level.SEVERE, null, ex);
            exito = false;
        }

        return exito;
    }

    public ArrayList<Movimiento> obtenerIngresos(Date fechaInicial, Date fechaFin) {
        ArrayList<Movimiento> ingresos = null;
        try {
            ingresos = MovimientoJDBC.getInstance().obtenerDatosIngresos(fechaInicial, fechaFin);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorContabilidad.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ingresos;
    }

    public ArrayList<Movimiento> obtenerGastos(Date fechaInicial, Date fechaFin) {
        ArrayList<Movimiento> gastos = null;
        try {
            gastos = MovimientoJDBC.getInstance().obtenerDatosGastos(fechaInicial, fechaFin);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorContabilidad.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gastos;
    }

    public float obtenerContabilidad(Date fechaInicial, Date fechaFin) {
        float balance = 0;
        try {
            balance = MovimientoJDBC.getInstance().obtenerBalance(fechaInicial, fechaFin);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorContabilidad.class.getName()).log(Level.SEVERE, null, ex);
        }

        return balance;
    }
    // TODO Listeners de los botones
}
