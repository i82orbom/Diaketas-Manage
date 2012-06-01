
package Controladores.Colaborador;

import Controladores.TestDatos;
import JDBC.CuotaJDBC;
import JDBC.PagoCuotaJDBC;
import JDBC.SocioJDBC;
import Modelo.Cuota;
import Modelo.PagoCuota;
import Modelo.Socio;
import Modelo.Voluntario;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 ** NOMBRE CLASE:
 **	  ControladorPagoCuota
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
 ** 	000 - 5 mai 2012 - RC - Creacion y metodos
 *      001 - 6 mai 2012 - RC - relleno de metodos
 *      002 - 7 mai 2012 - RC - interface actionListener
 **
 ** NOTAS:
 **
 **
 */
public class ControladorPagoCuota {

    /**
     * PATRON DE DISEÑO SINGLETON
     */
    private static ControladorPagoCuota instancia = null;

    public static ControladorPagoCuota getInstance() {

        if (instancia == null) {
            instancia = new ControladorPagoCuota();
        }
        return instancia;

    }


    public ControladorPagoCuota() {
    }

    public boolean anadirPagoCuota (PagoCuota pago) {

        Cuota cuota;
        try {
            cuota = SocioJDBC.getInstance().obtenerCuotaActiva(pago.getSocio());
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPagoCuota.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        try {
            CuotaJDBC.getInstance().actualizarUltimoPago(cuota);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPagoCuota.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        try {
            PagoCuotaJDBC.getInstance().añadirPagoCuota(pago);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPagoCuota.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public boolean eliminarPagoCuota (PagoCuota pc) {
        Socio s = new Socio();
        s.setOID(pc.getOID());
        Cuota c;
        try {
            c = SocioJDBC.getInstance().obtenerCuotaActiva(s);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPagoCuota.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        try {
            boolean exito = CuotaJDBC.getInstance().atrasarUltimoPago(c);
            if(!exito)
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPagoCuota.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        try {
            boolean exito = PagoCuotaJDBC.getInstance().eliminarPagoCuota(pc);
            if(!exito)
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPagoCuota.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }
}
