/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import Controladores.TestDatos;
import Modelo.Beneficiario;
import Modelo.Demanda;
import Modelo.Voluntario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author raul
 */
public class DemandaJDBC {

	private static DemandaJDBC instancia;

	public static DemandaJDBC getInstance(){
		if (instancia == null)
			instancia = new DemandaJDBC();
		return instancia;
	}

	private DemandaJDBC(){
	}

	public boolean InsertarDemanda (Demanda demanda)throws SQLException{

		DriverJDBC driver = DriverJDBC.getInstance();
		boolean exito=true;
		String sql = "INSERT INTO Demanda(OIDSector,OIDBeneficiario,OIDVoluntario,DescripcionVidaLaboral,Fecha) VALUES ("+demanda.getSector().getOID() +","+demanda.getBeneficiario().getOID() +","+demanda.getVoluntario().getOID()+",'"+demanda.getDescripcionValidaLaboral()+"','"+TestDatos.formatterBD.format(demanda.getFecha())+"')";

		try{
			driver.conectar();
			driver.insertar(sql);
		}
		catch(SQLException ex){
			throw ex;
		}
		finally {
			driver.desconectar();
		}
		return exito;
	}

	public void EliminarDemanda (Long OID) throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance();
		String sql = "DELETE FROM Demanda WHERE OID = "+OID;

		try {
			driver.conectar();
			driver.eliminar(sql);
		}
		catch (SQLException ex){
			throw ex;
		}
		finally {
			driver.desconectar();
		}
	}

	public ArrayList<Demanda> FiltrarDemandas(long OIDBeneficiario , long OIDSector , int meses) throws SQLException {
		DriverJDBC driver = DriverJDBC.getInstance();
		ResultSet resultados;
		ArrayList<Demanda> listaDemandas= new ArrayList<Demanda>();
		Demanda temp;

		String sql = "SELECT * FROM Demanda WHERE '1'='1'";
		if( OIDBeneficiario > 0) {
				sql += " AND OIDBeneficiario="+OIDBeneficiario;
		}
		if( OIDSector > 0) {
				sql += " AND OIDSector="+OIDSector;
		}
		sql+=" AND Fecha<=DATE_SUB(CURDATE(), INTERVAL "+meses+" MONTH);";
		try {
			driver.conectar();
			resultados = driver.seleccionar(sql);

			while (resultados.next()) {
				temp = new Demanda();
				temp.setBeneficiario(BeneficiarioJDBC.getInstance().obtenerBeneficiario(resultados.getLong("OIDBeneficiario")));
				temp.setVoluntario(VoluntarioJDBC.getInstance().obtenerVoluntario(resultados.getLong("OIDVoluntario")));
				temp.setSector(SectorJDBC.getInstance().ConsultarSector(resultados.getLong("OIDSector")));
				temp.setDescripcionValidaLaboral(resultados.getString("DescripcionVidaLaboral"));
				temp.setFecha(resultados.getDate("Fecha"));
				temp.setOID(resultados.getLong("OID"));
				listaDemandas.add(temp);
			}
		} catch (SQLException ex) {
			throw ex;
		} finally {
			driver.desconectar();
		}

		return listaDemandas;
	}

	public boolean ActualizarDemanda (Demanda demanda) throws SQLException {
		DriverJDBC driver = DriverJDBC.getInstance();
		String sql1 = "UPDATE Demanda SET OIDSector = "+demanda.getSector().getOID()+",OIDVoluntario = "+demanda.getVoluntario().getOID()+",DescripcionVidaLaboral = '"+demanda.getDescripcionValidaLaboral()+"',Fecha= CURDATE() WHERE OID = '"+demanda.getOID()+"';";
		try {
			driver.conectar();
			driver.actualizar(sql1);

		}
		catch (SQLException ex){
			throw ex;
		}
		finally {
			driver.desconectar();
		}
		return true;
	}

	public Demanda ObtenerDemanda (int OID) throws SQLException {

		DriverJDBC driver =  DriverJDBC.getInstance();
		BeneficiarioJDBC bene = BeneficiarioJDBC.getInstance();
		VoluntarioJDBC volun = VoluntarioJDBC.getInstance();
		String sql = "SELECT * FROM Demanda WHERE OID="+OID;
		ResultSet resultado,resultado_1 ;
		Demanda temp = new Demanda();
		Beneficiario bene_aux = new Beneficiario();
		Voluntario volun_aux = new Voluntario();

		try{
			driver.conectar();
			resultado = driver.seleccionar(sql);

			temp.setOID(resultado.getLong("OID"));
			temp.setDescripcionValidaLaboral(resultado.getString("DescripcionVidaLaboral"));
			temp.setFecha(resultado.getDate("Fecha"));

			/*____ Obtenemos el sector ____*/
			String sql1 = "SELECT * FROM Sector WHERE OID = '"+resultado.getInt("OIDSector")+"'";
			resultado_1=driver.seleccionar(sql1);
			temp.getSector().setDescripcion(resultado_1.getString("Descripcion"));

			/*____ Obtenemos el beneficiario ____*/
			sql1 = "SELECT NIF FROM Persona WHERE OID = '"+resultado.getInt("OIDBeneficiario") +"'";
			resultado_1 = driver.seleccionar(sql1);
			bene_aux = bene.obtenerBeneficiario(resultado_1.getString("NIF"));
			temp.setBeneficiario(bene_aux);

			/*____ Obtenemos el voluntario ____*/
			sql1 = "SELECT NIF FROM Persona WHERE OID = '"+resultado.getInt("OIDVoluntario") +"'";
			resultado_1 = driver.seleccionar(sql1);
			volun_aux = volun.obtenerVoluntario(resultado_1.getString("NIF"));
			temp.setVoluntario(volun_aux);

		}
		catch (SQLException ea){
			throw ea;
		}
		finally {
			driver.desconectar();
		}
		return temp;
	}

	public boolean ConsultarDemandaSector (long oid) throws SQLException {
		DriverJDBC driver = DriverJDBC.getInstance();
		String sql = "SELECT * FROM demanda WHERE OIDSector ="+oid;
		ResultSet resultado;
		boolean exito;

		try{
			driver.conectar();
			resultado=driver.seleccionar(sql);
			if(resultado.next()) exito = true;
			else exito = false;
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
