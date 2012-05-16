package Controladores.Colaborador;

import JDBC.CuotaJDBC;
import JDBC.SocioJDBC;
import Modelo.Cuota;
import Modelo.Socio;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 ** NOMBRE CLASE: 
 **	  ControladorCuota
 **
 ** DESCRIPCION:
 **       Controlador del panel de cuotas
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
 **
 ** NOTAS:
 **   
 **
 */
public class ControladorCuota {

    /**
     * PATRON DE DISEÑO SINGLETON
     */
    private static ControladorCuota instancia = null;

    public static ControladorCuota getInstance() {

        if (instancia == null) {
            instancia = new ControladorCuota();
        }
        return instancia;

    }
    

    public ControladorCuota() {
    }

    public boolean anadirCuota (String[] datos, Socio s){
        // TODO comprobar datos
        
        Cuota cuota;
        try {
            cuota = SocioJDBC.getInstance().obtenerCuotaActiva(s);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        boolean exito;
        
        try {
            exito = CuotaJDBC.getInstance().cancelarCuota(cuota);
            if (!exito)
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        cuota = new Cuota();
        cuota.setCantidad(Integer.parseInt(datos[Cuota.CANTIDAD]));
        cuota.setFechaFin(Date.valueOf(datos[Cuota.FECHA_FIN]));
        cuota.setFechaInicial(Date.valueOf(datos[Cuota.FECHA_INICIO]));
        cuota.setFechaUltimoPago(Date.valueOf(datos[Cuota.FECHA_ULTIMO_PAGO]));
        cuota.setIntervaloPagos(Date.valueOf(datos[Cuota.INTERFALO_PAGOS]));
        
        try {
            return CuotaJDBC.getInstance().añadirCuota(cuota);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public ArrayList<Cuota> comprobarImpagos (Socio s) {
        ArrayList<Cuota> cuotas = null;
        try {
            cuotas = CuotaJDBC.getInstance().comprobarImpagos();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cuotas;
    }
    
    public ArrayList<Cuota> verHistorialCuotas (Socio s) {
        ArrayList<Cuota> cuotas = null;
        
         
        try {
            CuotaJDBC.getInstance().historialCuotas(s);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cuotas;
    }
}
