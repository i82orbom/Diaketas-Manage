
package Controladores.Colaborador;

import JDBC.CuotaJDBC;
import JDBC.SocioJDBC;
import Modelo.Cuota;
import Modelo.Socio;
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

    public boolean anadirCuota (Cuota cuota){
        // TODO comprobar datos
		Cuota cuota_activa = null;

        try {
            cuota_activa = SocioJDBC.getInstance().obtenerCuotaActiva(cuota.getSocio());
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        boolean exito;
		if(cuota_activa.getSocio()!=null){
			try {
				exito = CuotaJDBC.getInstance().cancelarCuota(cuota_activa);
				if (!exito)
					return false;
			} catch (SQLException ex) {
				Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
				return false;
			}
		}
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
            cuotas = CuotaJDBC.getInstance().historialCuotas(s);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cuotas;
    }
	
	public boolean cancelarCuota (Cuota c) {
		if (c.getFechaUltimoPago().equals(c.getFechaInicio())) {
			try {
				return CuotaJDBC.getInstance().eliminarCuota(c);
			} catch (SQLException ex) {
				Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
			}
		} 
		else {
			try {
				return CuotaJDBC.getInstance().cancelarCuota(c);
			} catch (SQLException ex) {
				Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return true;
	}
}
