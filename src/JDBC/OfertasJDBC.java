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
import Modelo.Oferta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OfertasJDBC {
    private static OfertasJDBC instancia;

    private OfertasJDBC(){
    }
    
    public static OfertasJDBC getInstance(){
        if(instancia == null)
             instancia = new OfertasJDBC();
        return instancia;
    
    }
    
    public boolean insertarOferta (Oferta oferta)throws SQLException{
        DriverJDBC driver = DriverJDBC.getInstance();
	boolean exito = true;
        
        String sql1 = "INSERT INTO Oferta (cualificacionRequerida, descripcionOferta ,duracionContrato, fecha, plazasOfertadas, tipoContrato, idSector, idEmpresa, idVoluntario) VALUES ('"+oferta.getCualificacionRequerida()+"','"+oferta.getDescripcionOferta()+"','"+oferta.getDuracionContrato()+"','"+oferta.getFecha()+"','"+oferta.getPlazasOfertadas()+"','"+oferta.getTipoContrato()+"','"+oferta.getIdSector()+"','"+oferta.getIdEmpresa()+"','"+oferta.getIdVoluntario()+"')";
        
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

		return exito;
    }
    
    
}