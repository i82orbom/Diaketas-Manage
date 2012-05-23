/**
 ** NOMBRE CLASE:
 **	  BeneficiarioJDBC.java
 **
 ** DESCRIPCION:
 **       Abstracción JDBC de Beneficiario
 **
 **
 ** DESARROLLADO POR:
 *        Francisco José Beltrán Rodriguez (FBR)
 *
 **
 ** SUPERVISADO POR:
 **       Adolfo Arcoya Nieto (AAN)
 **
 ** HISTORIA:
 ** 	000 - Mar 24, 2012 - FBR - Creacion
 **     001 - Mar 25, 2012 - FBR - Implementacion de los metodos
 **     002 - Mar 26, 2012 - FBR - Implementacion de los métodos
 **     003 - Mar 26, 2012 - FBR - Corregidos errores en las sentencias de añadirFamiliar() y datosFamiliares()
 *      004 - Mar 27, 2012 - FBR - Ampliación e implementación de métodos
 *      005 - Mar 30, 2012 - AAN - Reestructuracion para usar la tabla persona
 **     006 - Abr 14, 2012 - AAN - Modificacion sentencias SQL
 *      007 - Abr 15, 2012 - FBR - Modifiacion de sentencias SQL y métodos
 **     008 - May 18, 2012 - ARS - Modifiacion de sentencias SQL para incluir OID. Se puede añadir beneficiario bien
 *
 ** NOTAS:
 **
 **
 */

package JDBC;

import Modelo.Beneficiario;
import Modelo.Persona;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BeneficiarioJDBC {

	private static BeneficiarioJDBC instancia;
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	private BeneficiarioJDBC(){
	}

	public static BeneficiarioJDBC getInstance(){
		if(instancia == null)
				instancia = new BeneficiarioJDBC();
		return instancia;
	}

	public boolean anadirBeneficiario (Beneficiario beneficiario) throws SQLException{

		DriverJDBC driver = DriverJDBC.getInstance();
		boolean exito = true;
		try{
			driver.inicioTransaccion();

			String sql = "INSERT INTO persona (NIF,Nombre,Apellidos,FechaNacimiento,CP,TelefonoFijo,TelefonoMovil,Domicilio,Localidad) VALUES "
					+ "('"+beneficiario.getNIF()+"','"+beneficiario.getNombre()+"','"+beneficiario.getApellidos()+"','"+formatter.format(beneficiario.getFechaDENacimiento())+"','"+beneficiario.getCP()+"','"+beneficiario.getTelefonoFijo()+"','"+beneficiario.getTelefonoMovil()+"','"+beneficiario.getDomicilio()+"','"+beneficiario.getLocalidad()+"')";

			String sql2 = "INSERT INTO beneficiario (OID,EstadoCivil,Nacionalidad,NivelDeEstudio,Observaciones,Ocupacion,Profesion,SituacionEconomica,Vivienda,ViviendaAlquiler,ViviendaObservaciones) VALUES "
					+ "(LAST_INSERT_ID(),'"+beneficiario.getEstadoCivil()+"','"+beneficiario.getNacionalidad()+"','"+beneficiario.getNivelDeEstudio()+"','"+beneficiario.getObservaciones()+"','"+beneficiario.getOcupacion()+"','"+beneficiario.getProfesion()+"','"+beneficiario.getSituacionEconomica()+"','"+beneficiario.getVivienda()+"','"+beneficiario.getViviendaAlquiler()+"','"+beneficiario.getViviendaObservaciones()+"')";

			driver.insertar(sql);
			driver.insertar(sql2);

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

	public Beneficiario obtenerBeneficiario(String DNI) throws SQLException{

		DriverJDBC driver = DriverJDBC.getInstance() ;

		String sql = "SELECT * FROM beneficiario b, persona p WHERE (p.NIF='"+DNI+"') AND p.NIF=b.NIF";

		ResultSet resultado = driver.seleccionar(sql);
		Beneficiario benef = null ;

		if(resultado.next()){
			benef = new Beneficiario();
			benef.setNIF(resultado.getString("NIF"));
			benef.setNombre(resultado.getString("Nombre"));
			benef.setApellidos(resultado.getString("Apellidos"));
			benef.setFechaDENacimiento(resultado.getDate("FechaNacimiento"));
			benef.setCP(resultado.getString("CP"));
			benef.setTelefonoFijo(resultado.getString("TelefonoFijo"));
			benef.setTelefonoMovil(resultado.getString("TelefonoMovil"));
			benef.setDomicilio(resultado.getString("Domicilio"));
			benef.setLocalidad(resultado.getString("Localidad"));
			benef.setEstadoCivil(resultado.getString("EstadoCivil"));
			benef.setNacionalidad(resultado.getString("Nacionalidad"));
			benef.setNivelDeEstudio(resultado.getString("NivelDeEstudio"));
			benef.setObservaciones(resultado.getString("Observaciones"));
			benef.setOcupacion(resultado.getString("Ocupacion"));
			benef.setProfesion(resultado.getString("Profesion"));
			benef.setSituacionEconomica(resultado.getString("SituacionEconomica"));
			benef.setVivienda(resultado.getString("Vivienda"));
			benef.setViviendaAlquiler(resultado.getFloat("ViviendaAlquiler"));
			benef.setViviendaObservaciones(resultado.getString("ViviendaObservaciones"));
		}

		return benef;
	}

	public boolean modificarDatosBeneficiario (Beneficiario beneficiario) throws SQLException{

		DriverJDBC driver = DriverJDBC.getInstance() ;
		String nivel_estudio = beneficiario.getNivelDeEstudio();
		String nivel_estudio_cadena = nivel_estudio.toString();

		String sql = "UPDATE persona SET NIF='"+beneficiario.getNIF()+"',Nombre='"+beneficiario.getNombre()+"',Apellidos='"+beneficiario.getApellidos()+"',FechaNacimiento='"+beneficiario.getFechaDENacimiento()+"',CP='"+beneficiario.getCP()+"',TelefonoFijo='"+beneficiario.getTelefonoFijo()+"',TelefonoMovil='"+beneficiario.getTelefonoMovil()+"',Domicilio='"+beneficiario.getDomicilio()+"',Localidad='"+beneficiario.getLocalidad()+"WHERE OID ="+beneficiario.getOID()+"'";

		boolean exito = driver.actualizar(sql);

		String sql2 = "UPDATE beneficiario SET NIF='"+beneficiario.getNIF()+"',Nacionalidad='"+beneficiario.getNacionalidad()+"',EstadoCivil='"+beneficiario.getEstadoCivil()+"',NivelDeEstudio='"+nivel_estudio_cadena+"',Observaciones='"+beneficiario.getObservaciones()+"',Ocupacion='"+beneficiario.getOcupacion()+"',Profesion='"+beneficiario.getProfesion()+"',SituacionEconomica='"+beneficiario.getSituacionEconomica()+"',Vivienda='"+beneficiario.getVivienda()+"',ViviendaAlquiler='"+beneficiario.getViviendaAlquiler()+"',ViviendaObservaciones='"+beneficiario.getViviendaObservaciones()+"WHERE OID ="+beneficiario.getOID()+"'";

		boolean exito2 = driver.actualizar(sql2);

		return exito && exito2;
	}

	public ArrayList<Beneficiario> obtenerListadoBeneficiario(String dato, String tipoDato) throws SQLException{

		DriverJDBC driver = DriverJDBC.getInstance() ;
		String sql = "SELECT * FROM beneficiario b, persona p WHERE "+tipoDato+"='"+dato+"' AND p.NIF=b.NIF";

		ResultSet resultados = driver.seleccionar(sql);
		ArrayList<Beneficiario> listadoBeneficiarios = new ArrayList<Beneficiario>();
		Beneficiario temp = null;

		while(resultados.next()){
			temp = new Beneficiario();
			temp.setNIF(resultados.getString("NIF"));
			temp.setNivelDeEstudio(resultados.getString("NivelDeEstudio"));
			temp.setObservaciones(resultados.getString("Observaciones"));
			temp.setOcupacion(resultados.getString("Ocupacion"));
			temp.setProfesion(resultados.getString("Profesion"));
			temp.setSituacionEconomica(resultados.getString("SituacionEconomica"));
			temp.setVivienda(resultados.getString("Vivienda"));
			temp.setViviendaAlquiler(resultados.getFloat("ViviendaAlquiler"));
			temp.setViviendaObservaciones(resultados.getString("ViviendaObservaciones"));
			temp.setNacionalidad(resultados.getString("Nacionalidad"));
			temp.setNombre(resultados.getString("Nombre"));
			temp.setApellidos(resultados.getString("Apellidos"));
			temp.setFechaDENacimiento(resultados.getDate("FechaNacimiento"));
			temp.setCP(resultados.getString("CP"));
			temp.setTelefonoFijo(resultados.getString("TelefonoFijo"));
			temp.setTelefonoMovil(resultados.getString("TelefonoMovil"));
			temp.setDomicilio(resultados.getString("Domicilio"));
			temp.setLocalidad(resultados.getString("Localidad"));


			listadoBeneficiarios.add(temp);
		}
		return listadoBeneficiarios;
	}

	public boolean borrarBeneficiario (String DNI) throws SQLException{

		DriverJDBC driver = DriverJDBC.getInstance() ;

		String sql = "DELETE FROM Beneficiario WHERE NIF='"+DNI+"'";

		boolean exito = driver.eliminar(sql);

		if(exito){
			sql = "DELETE from familiar WHERE beneficiarioNIF='"+DNI+"'";
			exito = driver.eliminar(sql);
			sql = "DELETE from persona WHERE NIF='"+DNI+"'";
			exito = driver.eliminar(sql);
		}

		return exito;
	}

	public boolean añadirFamiliar (Beneficiario b, String beneficiarioNIF, String paren) throws SQLException{

		DriverJDBC driver = DriverJDBC.getInstance() ;
		String sentencia, sentencia2 ;

		sentencia = "INSERT INTO persona (NIF,Nombre,Apellidos,FechaNacimiento,CP,TelefonoFijo,TelefonoMovil,Domicilio,Localidad) VALUES ('"
				+b.getNIF()+"','"+b.getNombre()+"','"+b.getApellidos()+"','"+b.getFechaDENacimiento()+"','"+b.getCP()+"','"+b.getTelefonoFijo()+"','"+b.getTelefonoMovil()+"','"+b.getDomicilio()+"','"+b.getLocalidad()+"' )";

		boolean exito = driver.insertar(sentencia);

		if(exito==true){
			sentencia2 = "INSERT INTO familia (PersonaNIF1, PersonaNIF2) VALUES ('"+beneficiarioNIF+"','"+b.getNIF()+"')";
			driver.insertar(sentencia2);
		}else{
			System.out.println("Error al realizar el INSERT en la base de datos");
			System.exit(1);
		}

		return true;
	}

	public ArrayList<Persona> datosFamiliares(String beneficiario_DNI) throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance() ;
		String sql = "SELECT * FROM persona p, familia f WHERE (f.PersonaNIF1='"+beneficiario_DNI+"') AND p.NIF=f.PersonaNIF2";
		ResultSet resultados = driver.seleccionar(sql);
		ArrayList<Persona> familiares = new ArrayList<Persona>();

		Persona temp = null;

		while(resultados.next()){
			temp = new Beneficiario();
			temp.setNIF(resultados.getString("NIF"));
			temp.setNombre(resultados.getString("Nombre"));
			temp.setApellidos(resultados.getString("Apellidos"));
			temp.setFechaDENacimiento(resultados.getDate("FechaNacimiento"));
			temp.setCP(resultados.getString("CP"));
			temp.setTelefonoFijo(resultados.getString("TelefonoFijo"));
			temp.setTelefonoMovil(resultados.getString("TelefonoMovil"));
			temp.setDomicilio(resultados.getString("Domicilio"));
			temp.setLocalidad(resultados.getString("Localidad"));

			familiares.add(temp);
		}

		return familiares;
	}

	public boolean eliminarDatosfamiliar(String familiarDNI, String beneficiarioDNI) throws SQLException{

		DriverJDBC driver = DriverJDBC.getInstance() ;

		String sentencia = "DELETE FROM familia WHERE PersonaNIF2= '"+familiarDNI+"' AND PersonaNIF1 = '"+beneficiarioDNI+"'";

		boolean exito = driver.eliminar(sentencia);

		if(exito==false){
			System.out.println("Error al realizar el DELETE en la base de datos");
			System.exit(1);

		}
		else {
			String sentencia2 = "DELETE FROM persona WHERE NIF= '"+familiarDNI+"'";
			boolean exito2 = driver.eliminar(sentencia2);

		}
		return true;
	}

}
