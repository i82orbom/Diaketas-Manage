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
import Modelo.C_Empresa;
import Modelo.Oferta;
import Modelo.Sector;
import Modelo.Voluntario;
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

	public ArrayList<Oferta> filtrarOfertas(Long empresaOID, Long sectorOID, int antiguedad)throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance();
		ArrayList<Oferta> listaOfertas = new ArrayList<Oferta>();
		Oferta oferta;

		String sql = "SELECT * FROM Oferta o WHERE Fecha>=DATE_SUB(CURDATE(), INTERVAL "+antiguedad+" MONTH)";
		if (empresaOID>0) sql.concat(" AND o.OIDEmpresa = "+empresaOID);
		if (sectorOID>0) sql.concat(" AND o.OIDSector = "+sectorOID);

		try{
			driver.conectar();
			ResultSet resultados;
			resultados = driver.seleccionar(sql);

			while(resultados.next()){
				oferta = new Oferta();

				oferta.setSector(new Sector());
				oferta.setEmpresa(new C_Empresa());
				oferta.setVoluntario(new Voluntario());

				/* obtener datos oferta*/
				oferta.setOID(resultados.getLong("OID"));
				oferta.setCualificacionRequerida(resultados.getString("CualificacionRequerida"));
				oferta.setDescripcionOferta(resultados.getString("DescripcionOferta"));
				oferta.setDuracionContrato(resultados.getInt("DuracionContrato"));
				oferta.setFecha(resultados.getDate("Fecha"));
				oferta.setPlazasOfertadas(resultados.getInt("PlazasOfertadas"));
				oferta.setTipoContrato(resultados.getString("TipoContrato"));

				oferta.getEmpresa().setOID(resultados.getLong("OIDEmpresa"));
				oferta.getSector().setOID(resultados.getLong("OIDSector"));
				oferta.getVoluntario().setOID(resultados.getLong("OIDVoluntario"));

				listaOfertas.add(oferta);
			}
		}
		catch (SQLException ex){
			throw ex;
		}
		finally {
			driver.desconectar();
		}

		for (Oferta o:listaOfertas){
			/* ______ empresa ______ */
			o.setEmpresa(C_EmpresaJDBC.getInstance().obtenerC_Empresa(o.getEmpresa().getOID()));
			/* ______ sector ______ */
			o.setSector(SectorJDBC.getInstance().ConsultarSector(o.getSector().getOID()));
			/* ______ voluntario ______ */
			o.setVoluntario(VoluntarioJDBC.getInstance().obtenerVoluntario(o.getSector().getOID()));
		}

		return listaOfertas;
	}

	public boolean ActualizarOferta (Oferta oferta) throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance();
		String sql = "UPDATE Oferta SET OID='"+oferta.getOID()+"',CualificacionRequerida='"+oferta.getCualificacionRequerida()+"',DescripcionOferta='"+oferta.getDescripcionOferta()+"',DuracionContrato='"+oferta.getDuracionContrato()+"',Fecha='"+oferta.getFecha()+"',PlazasOfertadas='"+oferta.getPlazasOfertadas()+"',TipoContrato='"+oferta.getTipoContrato()+"',OIDSector='"+oferta.getSector().getOID()+"',OIDEmpresa='"+oferta.getEmpresa().getOID()+"',OIDVoluntario='"+oferta.getVoluntario().getOID()+"'";

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

	public boolean ConsultarOfertaSector (Long oid) throws SQLException {
		DriverJDBC driver = DriverJDBC.getInstance();
		String sql = "SELECT * FROM Oferta WHERE OIDSector ="+oid;
		ResultSet resultado;
		boolean exito;

		try{
			driver.conectar();
			resultado=driver.seleccionar(sql);
			if(resultado.next())
				exito=true;
			else
				exito = false;
		}
		catch( SQLException ea){
			throw(ea);
		}
		finally{
			driver.desconectar();
		}
		return exito;
	}

}