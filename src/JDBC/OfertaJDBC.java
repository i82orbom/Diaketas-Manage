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

		return true;
    }

	public ArrayList<Oferta> filtartOfertas(String tipoBusqueda, String valor) throws SQLException{
		throw new SQLException("Not yet implemented");
	}

}