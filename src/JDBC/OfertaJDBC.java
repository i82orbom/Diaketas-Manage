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
import java.sql.Date;
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
        String sql1 = "INSERT INTO Oferta (cualificacionRequerida, descripcionOferta ,duracionContrato, fecha, plazasOfertadas, tipoContrato, oidSector, oidEmpresa, oidVoluntario) VALUES ('"
				+oferta.getCualificacionRequerida()+"','"+oferta.getDescripcionOferta()+"','"+oferta.getDuracionContrato()+"','"+TestDatos.formatterBD.format(oferta.getFecha())+"','"+oferta.getPlazasOfertadas()+"','"+oferta.getTipoContrato()+"','"+oferta.getSector().getOID()+"','"+oferta.getEmpresa().getOID()+"','"+oferta.getVoluntario().getOID()+"')";

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
			driver.eliminar(sql1);
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

    public boolean obtenerOferta (String oferta)throws SQLException{
        boolean exito = true;
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql1 = " SELECT * FROM Oferta WHERE OID = '"+oferta+"'";

        try{
			driver.inicioTransaccion();
			driver.seleccionar(sql1);
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

	public ArrayList<Oferta> filtrarOfertas (Long empresa, Long sector, String antiguedad)throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance();
		Date fecha_limite;
		String sql = "SELECT * FROM Oferta o WHERE '1'='1' ";

		if (empresa>0) sql += " AND o.oidempresa = "+empresa;
		if (sector>0) sql += " AND o.OIDsector = "+sector+"";


//		s.descripcion= '"+sector+"' AND s.OID = o.OIDSector AND (CURDATE()- '"+antiguedad+"')> o.Fecha";
		ArrayList<Oferta> lista_oferta = new ArrayList<Oferta>();

		try{
			Oferta temp;
			Sector tempSector = new Sector();
			C_Empresa tempEmpresa = new C_Empresa();

			driver.conectar();
			ResultSet resultados = driver.seleccionar(sql);

			while(resultados.next()){
				temp = new Oferta();
				temp.setOID(resultados.getLong("OID"));
				temp.setCualificacionRequerida(resultados.getString("CualificacionRequerida"));
				temp.setDescripcionOferta(resultados.getString("DescripcionOferta"));
				temp.setDuracionContrato(resultados.getInt("DuracionContrato"));
				temp.setFecha(resultados.getDate("Fecha"));
				temp.setPlazasOfertadas(resultados.getInt("PlazasOfertadas"));
				temp.setTipoContrato(resultados.getString("TipoContrato"));

				tempEmpresa.setOID(resultados.getLong("OIDempresa"));
				tempEmpresa.setCIF("CIF");
				temp.setEmpresa(tempEmpresa);

				tempSector.setOID(resultados.getLong("OIDsector"));
				tempSector.setDescripcion("Decripcion");
				temp.setSector(tempSector);

				lista_oferta.add(temp);
			}
		}
		catch (SQLException ex){
			throw ex;
		}
		finally {
			driver.desconectar();
		}



		return lista_oferta;
	}

	public boolean ActualizarOferta (Oferta oferta) throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance();
		String sql = "UPDATE Oferta SET OID='"+oferta.getOID()+"',cualificacionRequerida='"+oferta.getCualificacionRequerida()+"',descripcionOferta='"+oferta.getDescripcionOferta()+"',duracionContrato='"+oferta.getDuracionContrato()+"',fecha='"+oferta.getFecha()+"',plazasOfertadas='"+oferta.getPlazasOfertadas()+"',tipoContrato='"+oferta.getTipoContrato()+"',sector='"+oferta.getSector()+"',empresa='"+oferta.getEmpresa()+"',voluntario='"+oferta.getVoluntario()+"'";

		try {
			driver.conectar();
			driver.actualizar(sql);
        }
        catch (SQLException ex){
            throw ex;
        }
        finally {
            driver.desconectar();
        }

        return true;
	}

}
