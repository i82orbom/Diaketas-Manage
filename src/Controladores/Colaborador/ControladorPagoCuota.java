
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
    private static ControladorCuota instancia = null;

    public static ControladorCuota getInstance() {

        if (instancia == null) {
            instancia = new ControladorCuota();
        }
        return instancia;

    }


    public ControladorPagoCuota() {
    }

    public boolean anadirPagoCuota (String[] datos, Voluntario v) {

        if (!ComprobarDatos(datos))
            return false;

        Socio socio;
        try {
            socio = SocioJDBC.getInstance().obtenerSocio(v.getNIF());
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPagoCuota.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        Cuota cuota;
        try {
            cuota = SocioJDBC.getInstance().obtenerCuotaActiva(socio);
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

        PagoCuota pc = new PagoCuota();
        pc.setConcepto(datos[PagoCuota.CONCEPTO_ID]);
        pc.setFecha(Date.valueOf(datos[PagoCuota.CONCEPTO_ID]));
        pc.setImporte(Integer.parseInt(datos[PagoCuota.IMPORTE_ID]));
        pc.setOIDVoluntario((v.getOID().intValue()));
        pc.setOIDSocio(socio.getOIDSocio());


        try {
            PagoCuotaJDBC.getInstance().añadirPagoCuota(pc);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPagoCuota.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public boolean eliminarPagoCuota (PagoCuota pc) {
        Socio s = new Socio();
        s.setOIDSocio(pc.getOIDSocio());
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

    private boolean ComprobarDatos (String[] datos) {
        // cada campo debe ser not null
        for (int i=0; i<datos.length; i++) {
            if (datos[i].length() < 1)
                return false;
        }

        if (!TestDatos.isOnlyDigit(datos[PagoCuota.IMPORTE_ID]))
            return false;

        return true;
    }

}
