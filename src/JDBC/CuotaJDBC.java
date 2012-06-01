/**
 ** NOMBRE CLASE:
 **	  CuotaJDBC.java
 **
 ** DESCRIPCION:
 **       Abstracción JDBC de Cuota
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

import Controladores.TestDatos;
import Modelo.Cuota;
import Modelo.Socio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PLB
 */
public class CuotaJDBC {

    /**
     * Instancia CuotaJDBC
     */
    private static CuotaJDBC instancia;

    /**
     * Constructor
     */
    private CuotaJDBC(){
    }

    /**
     * Devuelve la instancia de la clase, sino la crea
     * @return intancia
     */
    public static CuotaJDBC getInstance(){
        if(instancia == null)
             instancia = new CuotaJDBC();
        return instancia;

    }

    /**
     * Añade una Cuota al sistema
     * @param c
     * @return true Si se ha podido añadir la Cuota
     * @throws SQLException
     */
    public boolean añadirCuota(Cuota c) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "INSERT INTO Cuota (Cantidad, IntervalosPagos, FechaInicio, FechaFin, OIDSocio) VALUES ('"+c.getCantidad()+"','"+c.getIntervaloPagos()+"','"+TestDatos.formatterBD.format(c.getFechaInicio())+"','"+TestDatos.formatterBD.format(c.getFechaFin())+"','"+c.getSocio().getOID()+"')";

        try{
            driver.inicioTransaccion();
            driver.insertar(sql);
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
     * Actualiza el último pago de una Cuota
     * @param c
     * @return true Si se ha podido actualizar el pago
     * @throws SQLException
     */
    public boolean actualizarUltimoPago(Cuota c) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "UPDATE Cuota SET FechaUltimoPago='"+c.getFechaUltimoPago()+"'+'"+c.getIntervaloPagos()+"' WHERE OIDSocio='"+c.getSocio().getOID()+"'";

        try{
            driver.inicioTransaccion();
            driver.actualizar(sql);
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
     * Atrasa el último pago de una Cuota
     * @param c
     * @return true Si se ha podido atrasar el último pago
     * @throws SQLException
     */
    public boolean atrasarUltimoPago(Cuota c) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "UPDATE Cuota SET FechaUltimoPago='"+c.getFechaUltimoPago()+"'-'"+c.getIntervaloPagos()+"' WHERE OIDSocio='"+c.getSocio().getOID()+"'";

        try{
            driver.inicioTransaccion();
            driver.actualizar(sql);
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
     * Cancela una Cuota del sistema
     * @param c
     * @return true Si se ha podido cancelar la Cuota
     * @throws SQLException
     */
    public boolean cancelarCuota(Cuota c) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();

        Date fechaActual = new Date();
        String sql = "UPDATE Cuota SET FechaFin='"+fechaActual.getTime()+" WHERE Cuota.OID='"+c.getOIDCuota()+"'";

        try{
            driver.inicioTransaccion();
            driver.actualizar(sql);
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
     * Eliminar una Cuota del sistema
     * @param c
     * @return true Si se ha podido eliminar la Cuota
     * @throws SQLException
     */
    public boolean eliminarCuota(Cuota c) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        String sql2 = "DELETE FROM Cuota WHERE Cuota.OID='"+c.getOIDCuota()+"'";

        try{
            driver.inicioTransaccion();
            driver.eliminar(sql2);
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
     * Comprobar todos los Impagos
     * @return listaCuotas Lista de los impagos
     * @throws SQLException
     */
    public ArrayList<Cuota> comprobarImpagos() throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        Date fechaActual = null;
        ArrayList<Cuota> listaCuotas = new ArrayList<Cuota>();

        String sql ="SELECT * FROM Cuota c, Socio s WHERE c.OIDSocio = s.OID AND c.fechaUltimoPago+c.intervaloPagos < '"+fechaActual.getTime()+"'";

        try {
            driver.conectar();
            ResultSet rs = driver.seleccionar(sql);

            if(rs.next()){
                Cuota cuota = new Cuota();
				cuota.setOIDCuota(rs.getLong("OID"));
				//cuota.setSocio(rs.getLong("OIDSocio"));
                cuota.setCantidad(rs.getDouble("Cantidad"));
                cuota.setFechaFin(rs.getDate("FechaFin"));
                cuota.setFechaInicial(rs.getDate("FechaInicio"));
                cuota.setFechaUltimoPago(rs.getDate("FechaUltimoPago"));
                cuota.setIntervaloPagos(rs.getInt("IntervalosPagos"));

                listaCuotas.add(cuota);
            }
        }
        catch (SQLException ex){
            throw ex;
	}
	finally{
		driver.desconectar();
	}
        return listaCuotas;
    }
    /**
     * Muestra el historial de Cuotas de un Socio
     * @param s
     * @return listaCuotas Listado de las cuotas
     * @throws SQLException
     */
    public ArrayList<Cuota> historialCuotas(Socio s) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        ArrayList<Cuota> listaCuotas = new ArrayList<Cuota>();

        String sql = "SELECT * FROM Cuota WHERE OIDSocio='"+s.getOID()+"'";
        try {
            driver.conectar();
            ResultSet rs = driver.seleccionar(sql);

            if(rs.next()){
                Cuota cuota = new Cuota();
				cuota.setOIDCuota(rs.getLong("OID"));
				cuota.setSocio(s);
                cuota.setCantidad(rs.getDouble("Cantidad"));
                cuota.setFechaFin(rs.getDate("FechaFin"));
                cuota.setFechaInicial(rs.getDate("FechaInicio"));
                cuota.setFechaUltimoPago(rs.getDate("FechaUltimoPago"));
                cuota.setIntervaloPagos(rs.getInt("IntervalosPagos"));

                listaCuotas.add(cuota);
            }
        }
        catch (SQLException ex){
            throw ex;
	}
	finally{
		driver.desconectar();
	}
        return listaCuotas;
    }

}

