/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import Controladores.TestDatos;
import Modelo.Demanda;
import Modelo.Beneficiario;
import Modelo.Sector;
import Modelo.Voluntario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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


 /*
   public ArrayList<Demanda> FiltrarDemandas (Long beneficiario, Long sector, String antiguedad)throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance();
		Date fecha_limite;
		String sql = "SELECT * FROM Demanda d WHERE '1'='1' ";

		if (beneficiario>0) sql += " AND d.oidbeneficiario = "+beneficiario;
		if (sector>0) sql += " AND d.OIDsector = "+sector+"";


//		s.descripcion= '"+sector+"' AND s.OID = o.OIDSector AND (CURDATE()- '"+antiguedad+"')> o.Fecha";
		ArrayList<Demanda> lista_demanda = new ArrayList<Demanda>();

		try{
			Demanda temp;
			Sector tempSector = new Sector();
			Beneficiario tempBeneficiario = new Beneficiario();

			driver.conectar();
			ResultSet resultados = driver.seleccionar(sql);

			while(resultados.next()){
				temp = new Demanda();
				temp.setOID(resultados.getLong("OID"));
                                temp.setDescripcionValidaLaboral(resultados.getString("DescripcionVidaLaboral"));
				temp.setFecha(resultados.getDate("Fecha"));

				tempBeneficiario.setOID(resultados.getLong("OIDbeneficiario"));
				tempBeneficiario.setNIF("NIF");
				temp.setIdBeneficiario(tempBeneficiario);

				tempSector.setOID(resultados.getLong("OIDsector"));
				tempSector.setDescripcion("Decripcion");
				temp.setIdSector(tempSector);

				lista_demanda.add(temp);
			}
		}
		catch (SQLException ex){
			throw ex;
		}
		finally {
			driver.desconectar();
		}



		return lista_demanda;
	}


   */
    public ArrayList<Demanda> FiltrarDemandas(long OIDBeneficiario , long OIDSector , int meses) throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance();
		ResultSet resultado;

		ArrayList<Demanda> listaDemandas = new ArrayList<Demanda>();
		Beneficiario beneficiario;
		Voluntario voluntario;
		Sector sector;
		Demanda temp;

		String sql = "SELECT * FROM Demanda WHERE '1'='1'";
		if( OIDBeneficiario > 0) {
			sql += " AND OIDBeneficiario="+OIDBeneficiario;
		}
		if( OIDSector > 0) {
			sql += " AND OIDSector="+OIDSector;
		}
		sql+=" AND Fecha<=DATE_SUB(CURDATE(), INTERVAL "+meses+" MONTH);";

		try{
			driver.conectar();
			resultado = driver.seleccionar(sql);
			System.out.println("Consulta: "+sql);

			while (resultado.next()){
				beneficiario = new Beneficiario();
				voluntario = new Voluntario();
				sector = new Sector();

				//____ Datos de la demanda ____
				temp = new Demanda();
				temp.setOID(resultado.getLong("OID"));
				temp.setDescripcionValidaLaboral(resultado.getString("DescripcionVidaLaboral"));
				temp.setFecha(resultado.getDate("Fecha"));

				beneficiario.setOID(resultado.getLong("OIDBeneficiario"));
				temp.setBeneficiario(beneficiario);

//				voluntario.setOID(resultado.getLong("OIDVoluntario"));
//				temp.setVoluntario(voluntario);

				sector.setOID(resultado.getLong("OIDSector"));
				temp.setSector(sector);

				//____ Añadimos la demanda al arraylist ____
				listaDemandas.add(temp);
			}

			for (Demanda d:listaDemandas){
				sector = d.getSector();
				beneficiario = d.getBeneficiario();
//				voluntario = d.getVoluntario();

				//____ Obtenemos el sector ____
				String sql1 = "SELECT * FROM Sector WHERE OID ="+sector.getOID();
				resultado = driver.seleccionar(sql1);
				sector.setDescripcion(resultado.getString("Descripcion"));

				//____ Obtenemos el beneficiario ____
				sql1 = "SELECT NIF FROM Persona WHERE OID = "+beneficiario.getOID();
				resultado = driver.seleccionar(sql1);
				beneficiario = BeneficiarioJDBC.getInstance().obtenerBeneficiario(resultado.getString("NIF"));

				//____ Obtenemos el voluntario ____
/*				sql1 = "SELECT NIF FROM Persona WHERE OID = "+voluntario.getOID();
				resultado = driver.seleccionar(sql1);
				voluntario = VoluntarioJDBC.getInstance().obtenerVoluntario(resultado_1.getString("NIF"));
				temp.setVoluntario(voluntario);
*/

			}
		}
		catch (SQLException ex){
			throw ex;
		}
		finally {
			driver.desconectar();
		}

		return listaDemandas;
    }

    public boolean ActualizarDemanda (Demanda demanda) throws SQLException {
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql1 = "UPDATE Demanda SET OIDSector = "+demanda.getSector().getOID()+",OIDVoluntario = "+demanda.getVoluntario().getOID()+",DescripcionVidaLaboral = '"+demanda.getDescripcionValidaLaboral()+"',Fecha= '"+demanda.getFecha()+"'";

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

    public boolean ConsultarDemandaSector (int oid) throws SQLException {

        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "SELECT * FROM Sector WHERE OID ="+oid;
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
