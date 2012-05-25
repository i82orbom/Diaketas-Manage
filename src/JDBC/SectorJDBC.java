/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

/**
 *
 * @author Alberto
 */
import Controladores.TestDatos;
import Modelo.Sector;
import Modelo.Oferta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SectorJDBC {
    private static SectorJDBC instancia;

    private SectorJDBC(){
    }
    
    public static SectorJDBC getInstance(){
        if(instancia == null)
             instancia = new SectorJDBC();
        return instancia;
    
    }
    
    public ResultSet obtenerSector (Sector sector )throws SQLException{
        DriverJDBC driver = DriverJDBC.getInstance();
	Boolean exito;
        ResultSet rs;
        
        String sql1 = "SELECT DESCRIPCION FROM Sector WHERE OID = '"+sector.getDescripcion()+"'";
        rs = driver.seleccionar(sql1);
        
        try{
			driver.inicioTransaccion();
			driver.insertar(sql1);
			driver.commit();
		}
		catch (SQLException ex){
			driver.rollback();
			exito = false;
			throw ex;
		}
		finally {
			driver.finTransaccion();
		}

		return rs;
    }
    
    
}
