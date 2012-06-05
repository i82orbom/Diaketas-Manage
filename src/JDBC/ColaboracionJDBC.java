/**
 ** NOMBRE CLASE:
 **	  ColaboracionJDBC.java
 **
 ** DESCRIPCION:
 **       Abstracción JDBC de Colaboración
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
import Modelo.Colaboracion;
import Modelo.Colaborador;
import Modelo.Voluntario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PLB
 */
public class ColaboracionJDBC {

    /**
     * Instancia ColaboracionJDBC
     */
    private static ColaboracionJDBC instancia;

    /**
     * Constructor
     */
    private ColaboracionJDBC(){
    }

    /**
     * Devuelve una instancia de la clase, sino la devuelve
     * @return
     */
    public static ColaboracionJDBC getInstance(){
        if(instancia == null)
             instancia = new ColaboracionJDBC();
        return instancia;

    }

    /**
     * Añadir una Colaboracion en el sistema
     * @param c
     * @return true Si se ha podido añadir la colaboracion
     * @throws SQLException
     */
    public boolean añadirColaboracion(Colaboracion c) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "INSERT INTO Movimiento (Cantidad, Concepto, Fecha) VALUES ('"+c.getImporte()+"','"+c.getConcepto()+"','"+TestDatos.formatterBD.format(c.getFecha())+"')";
        String sql2 = "INSERT INTO Colaboracion (OID, OIDVoluntario, OIDColaborador) VALUES (LAST_INSERT_ID(),'"+c.getVoluntario().getOID()+"','"+c.getColaborador().getOID()+"')";

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
     * Elimina una Colaboracion del sistema
     * @param c
     * @return true Si se ha podido eliminar
     * @throws SQLException
     */
    public boolean eliminarColaboracion(Colaboracion c) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "DELETE FROM Colaboracion WHERE OID= '"+c.getOIDColaboracion()+"'";

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
     * Muestra un listado de todas las Colaboraciones en un intervalo de tiempo
     * @param c
     * @param FechaInicio
     * @param FechaFin
     * @return listaColaboraciones Listado con las Colaboraciones
     * @throws SQLException
     */
    public ArrayList<Colaboracion> HistorialColaboraciones(Colaborador c, Date FechaInicio, Date FechaFin) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        Voluntario vol = new Voluntario();
        String sql = "SELECT * FROM Colaboracion c, Movimiento m, Voluntario v WHERE c.OIDVoluntario = v.OID AND c.OID=m.OID AND c.OIDColaborador='"+c.getOID()+"' AND m.Fecha>='"+TestDatos.formatterBD.format(FechaInicio)+"' AND m.Fecha<='"+TestDatos.formatterBD.format(FechaFin)+"'";
		System.out.println(sql);
		
        ArrayList<Colaboracion> listaColaboraciones = new ArrayList<Colaboracion>();
        Colaboracion colaboracion = null;

        try {
            driver.conectar();
            ResultSet rs = driver.seleccionar(sql);

            while(rs.next()){
                colaboracion = new Colaboracion();
				colaboracion.setColaborador(c);
				colaboracion.setVoluntario(VoluntarioJDBC.getInstance().obtenerVoluntario(rs.getLong("OIDVoluntario")));
				colaboracion.setVoluntario(vol);
                colaboracion.setFecha(rs.getDate("Fecha"));
                colaboracion.setImporte(rs.getInt("Cantidad"));
                colaboracion.setConcepto(rs.getString("Concepto"));

                listaColaboraciones.add(colaboracion);
            }
        }
        catch (SQLException ex){
            throw ex;
	}
	finally{
		driver.desconectar();
	}
        return listaColaboraciones;
    }

	public boolean comprobarSiColaboracion(Long oid) throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance();
		String sql = "SELECT * FROM Colaboracion c WHERE c.OIDVoluntario='"+oid+"'";
		boolean existe=false;
        try {
            driver.conectar();
            ResultSet rs = driver.seleccionar(sql);

            if(rs.next()){
               existe=true;
			}
		}
		catch (SQLException ex){
				throw ex;
		}
		finally{
			driver.desconectar();
		}
			return existe;
	}
	
}
