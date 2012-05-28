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
import Modelo.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OfertaJDBC {
    private static OfertaJDBC instancia;

    private OfertaJDBC(){
    }

    public static OfertaJDBC getInstance(){
        if(instancia == null)
             instancia = new OfertaJDBC();
        return instancia;

    }

    public boolean insertarOferta (Oferta oferta)throws SQLException{
        boolean exito = true;
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql1 = "INSERT INTO Oferta (cualificacionRequerida, descripcionOferta ,duracionContrato, fecha, plazasOfertadas, tipoContrato, idSector, idEmpresa, idVoluntario) VALUES ('"+oferta.getCualificacionRequerida()+"','"+oferta.getDescripcionOferta()+"','"+oferta.getDuracionContrato()+"','"+oferta.getFecha()+"','"+oferta.getPlazasOfertadas()+"','"+oferta.getTipoContrato()+"','"+oferta.getSector().getOID()+"','"+oferta.getEmpresa().getOIDEmpresa()+"','"+oferta.getVoluntario().getOID()+"')";

        try{
			driver.inicioTransaccion();
			driver.insertar(sql1);
			driver.commit();
		}
		catch (SQLException ex){
			driver.rollback();
			throw ex;
		}
		finally {
			driver.finTransaccion();
		}

		return exito;
    }
    
    public boolean eliminarOferta (Oferta oferta) throws SQLException{
        boolean exito = true;
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql1 = "DELETE  FROM oferta WHERE OID = '"+oferta.getOID()+"'";

        try{
			driver.inicioTransaccion();
			driver.insertar(sql1);
			driver.commit();
		}
		catch (SQLException ex){
			driver.rollback();
			throw ex;
		}
		finally {
			driver.finTransaccion();
		}

		return exito;
    
    }
        
    public boolean obtenerOferta (Oferta oferta)throws SQLException{
        boolean exito = true;
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql1 = " SELECT * FROM Oferta WHERE OID = '"+oferta.getOID()+"'";

        try{
			driver.inicioTransaccion();
			driver.insertar(sql1);
			driver.commit();
		}
		catch (SQLException ex){
			driver.rollback();
			throw ex;
		}
		finally {
			driver.finTransaccion();
		}

		return exito;
    }

	public ArrayList<Oferta> filtrarOfertas (String empresa, String sector,String antiguedad)throws SQLException{
            DriverJDBC driver = DriverJDBC.getInstance(); 
            ArrayList<Oferta> lista_oferta = new ArrayList<Oferta>();
            return lista_oferta;
        }

}