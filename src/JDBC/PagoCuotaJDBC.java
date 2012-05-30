/**
 ** NOMBRE CLASE:
 **	  PagoCuotaJDBC.java
 **
 ** DESCRIPCION:
 **       Abstracción JDBC de un Pago de una Cuota
 **
 **
 ** DESARROLLADO POR:
 **       Francisco José Legaza Bailon (PLB)
 **
 **
 ** SUPERVISADO POR:
 **
 **
 ** HISTORIA:
 ** 	000 - Abril 26, 2012 - PLB - Creacion
 **
 **
 **
 ** NOTAS:
 **
 **
 */
package JDBC;

import Modelo.PagoCuota;
import Modelo.Socio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PLB
 */
public class PagoCuotaJDBC {

    /**
     * Instancia PagoCuotaJDBC
     */
    private static PagoCuotaJDBC instancia;

    /**
     * Constructor
     */
    private PagoCuotaJDBC(){
    }

    /**
     * Devuelve la instancia de la clase, si no existe la crea.
     * @return instancia
     */
    public static PagoCuotaJDBC getInstance(){
        if(instancia == null)
             instancia = new PagoCuotaJDBC();
        return instancia;

    }

    /**
     * Añadir un PagoCuota al sistema
     * @param pc
     * @return true Si se ha podido añadir el PagoCuota
     * @throws SQLException
     */
    public boolean añadirPagoCuota(PagoCuota pc) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "INSERT INTO Movimiento (importe, fecha, concepto) VALUES ('"+pc.getImporte()+"','"+pc.getFecha()+"','"+pc.getConcepto()+"')";
        String sql2 = "INSERT INTO PagoCuota (OIDSocio, OIDVoluntario, OID) VALUES ('"+pc.getOIDSocio()+"','"+pc.getOIDVoluntario()+"',LAST_INSERT_ID())";

        try{
            driver.inicioTransaccion();
            driver.insertar(sql);
            driver.insertar(sql2);
            driver.commit();

        }
        catch (SQLException ex){
            driver.rollback();
            throw ex;
	}
        finally {
            driver.finTransaccion();
	}
        return true;
    }

    /**
     * Elimina un PagoCuota del sistema
     * @param pc
     * @return true Si se ha podido eliminar el PagoCuota
     * @throws SQLException
     */
    public boolean eliminarPagoCuota(PagoCuota pc) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "DELETE FROM PagoCuota WHERE OID='"+pc.getOIDPagoCuota()+"'";


        try{
            driver.inicioTransaccion();
            driver.eliminar(sql);
            driver.commit();
        }
        catch (SQLException ex){
            driver.rollback();
            throw ex;
	}
        finally {
            driver.finTransaccion();
	}
        return true;
    }

    /**
     * Muestra el historial de todos los Pagos de una Cuota
     * @param s
     * @param fechaInicio
     * @param fechaFin
     * @return listaPagosCuotas Listado de los pagos
     * @throws SQLException
     */
    public ArrayList<PagoCuota> HistorialPagosCuotas (Socio s, Date fechaInicio, Date fechaFin) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        ArrayList<PagoCuota> listaPagosCuotas = new ArrayList<PagoCuota>();
        String sql = "SELECT * FROM PagoCuota p, Movimiento m, Voluntario v WHERE p.OID=m.OID AND p.OIDSocio='"+s.getOID()+"' AND p.OIDVoluntario=v.OID";

        try {
            driver.conectar();
            ResultSet rs = driver.seleccionar(sql);

            if(rs.next()){
                PagoCuota PagoC = new PagoCuota();
                PagoC.setConcepto(rs.getString("Concepto"));
                PagoC.setFecha(rs.getDate("Fecha"));
                PagoC.setImporte(rs.getFloat("Importe"));

                listaPagosCuotas.add(PagoC);
            }
        }
        catch (SQLException ex){
            throw ex;
	}
	finally{
		driver.desconectar();
	}
        return listaPagosCuotas;
    }
}
