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
 *      009 - May 23, 2012 - JAEG - Modificada sentencia SQL del metodo obtenerListadoBeneficiario()
 *
 ** NOTAS:
 **
 **
 */

package JDBC;

import Modelo.*;
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
		if(instancia == null) instancia = new BeneficiarioJDBC();
		return instancia;
	}

	public boolean anadirBeneficiario (Beneficiario beneficiario) throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance();
		String sql1 = "INSERT INTO persona (NIF,Nombre,Apellidos,FechaNacimiento,CP,TelefonoFijo,TelefonoMovil,Domicilio,Localidad) VALUES "
					+ "('"+beneficiario.getNIF()+"','"+beneficiario.getNombre()+"','"+beneficiario.getApellidos()+"','"+formatter.format(beneficiario.getFechaDENacimiento())+"','"+beneficiario.getCP()+"','"+beneficiario.getTelefonoFijo()+"','"+beneficiario.getTelefonoMovil()+"','"+beneficiario.getDomicilio()+"','"+beneficiario.getLocalidad()+"')";
		String sql2 = "INSERT INTO beneficiario (OID,EstadoCivil,Nacionalidad,NivelDeEstudio,Observaciones,Ocupacion,Profesion,SituacionEconomica,Vivienda,ViviendaAlquiler,ViviendaObservaciones) VALUES "
					+ "(LAST_INSERT_ID(),'"+beneficiario.getEstadoCivil()+"','"+beneficiario.getNacionalidad()+"','"+beneficiario.getNivelDeEstudio()+"','"+beneficiario.getObservaciones()+"','"+beneficiario.getOcupacion()+"','"+beneficiario.getProfesion()+"','"+beneficiario.getSituacionEconomica()+"','"+beneficiario.getVivienda()+"','"+beneficiario.getViviendaAlquiler()+"','"+beneficiario.getViviendaObservaciones()+"')";

		try{
			driver.inicioTransaccion();
			driver.insertar(sql1);
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

	public Beneficiario obtenerBeneficiario(String DNI) throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance() ;
		String sql = "SELECT * FROM beneficiario b, persona p WHERE (p.NIF='"+DNI+"') AND p.NIF=b.NIF";
		Beneficiario benef = null ;

		try {
			driver.conectar();
			ResultSet resultado = driver.seleccionar(sql);

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
		}
		catch (SQLException ex){
			throw ex;
		}
		finally{
			driver.desconectar();
		}

		benef.setAyudasPrestadas(obtenerAyudasBeneficiario(benef.getNIF()));

		return benef;
	}

	public boolean modificarDatosBeneficiario (Beneficiario beneficiario) throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance() ;
		String sql1 = "UPDATE persona SET NIF='"+beneficiario.getNIF()+"',Nombre='"+beneficiario.getNombre()+"',Apellidos='"+beneficiario.getApellidos()+"',FechaNacimiento='"+beneficiario.getFechaDENacimiento()+"',CP='"+beneficiario.getCP()+"',TelefonoFijo='"+beneficiario.getTelefonoFijo()+"',TelefonoMovil='"+beneficiario.getTelefonoMovil()+"',Domicilio='"+beneficiario.getDomicilio()+"',Localidad='"+beneficiario.getLocalidad()+"WHERE OID ="+beneficiario.getOID()+"'";
		String sql2 = "UPDATE beneficiario SET NIF='"+beneficiario.getNIF()+"',Nacionalidad='"+beneficiario.getNacionalidad()+"',EstadoCivil='"+beneficiario.getEstadoCivil()+"',NivelDeEstudio='"+beneficiario.getNivelDeEstudio()+"',Observaciones='"+beneficiario.getObservaciones()+"',Ocupacion='"+beneficiario.getOcupacion()+"',Profesion='"+beneficiario.getProfesion()+"',SituacionEconomica='"+beneficiario.getSituacionEconomica()+"',Vivienda='"+beneficiario.getVivienda()+"',ViviendaAlquiler='"+beneficiario.getViviendaAlquiler()+"',ViviendaObservaciones='"+beneficiario.getViviendaObservaciones()+"WHERE OID ="+beneficiario.getOID()+"'";

		try {
			driver.inicioTransaccion();
			driver.actualizar(sql1);
			driver.actualizar(sql2);
			driver.commit();
		}
		catch (SQLException ex){
			throw ex;
		}
		finally {
			driver.finTransaccion();
		}

		return true;
	}

	public ArrayList<Beneficiario> obtenerListadoBeneficiario(String dato, String tipoDato) throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance() ;
		String sql = "SELECT * FROM beneficiario b, persona p WHERE "+tipoDato+" LIKE '%"+dato+"%' AND p.OID=b.OID";
		ArrayList<Beneficiario> listadoBeneficiarios = new ArrayList<Beneficiario>();

		try {
			Beneficiario temp;
			driver.conectar();
			ResultSet resultados = driver.seleccionar(sql);

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
				temp.setEstadoCivil(resultados.getString("EstadoCivil"));

				listadoBeneficiarios.add(temp);
			}
		}
		catch (SQLException ex){
			throw ex;
		}
		finally{
			driver.desconectar();
		}

			// Se añaden las ayudas de los beneficiarios
		for(Beneficiario b:listadoBeneficiarios)
			b.setAyudasPrestadas(obtenerAyudasBeneficiario(b.getNIF()));

		return listadoBeneficiarios;
	}

	public ArrayList<Ayuda> obtenerAyudasBeneficiario(String nif) throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance();
		String sql = "SELECT tp.Titulo, a.*, p2.Nombre, p2.Apellidos  FROM tipoAyuda tp, ayuda a, persona p, persona p2 WHERE p.NIF='"+nif+"' AND a.OID_Bene=p.OID AND tp.OID=a.TipoAyudaOID AND p2.OID=a.OID_Volun";
		ArrayList<Ayuda> ayudas = new ArrayList<Ayuda>();

		try {
			driver.conectar();
			ResultSet resultados = driver.seleccionar(sql);
			while(resultados.next()){
				Ayuda aux = new Ayuda();
				aux.setBeneficiarioDeAyuda(null);
				aux.setFecha(resultados.getDate("Fecha"));
				aux.setImporte(resultados.getFloat("Importe"));
				aux.setObservaciones(resultados.getString("Observaciones"));
				TipoAyuda auxTp = new TipoAyuda();
				auxTp.setTitulo(resultados.getString("Titulo"));
				aux.setTipo_ayuda(auxTp);
				Voluntario auxVol = new Voluntario();
				auxVol.setNombre(resultados.getString("Nombre"));
				auxVol.setApellidos(resultados.getString("Apellidos"));
				aux.setVoluntarioQueOtorga(auxVol);
				ayudas.add(aux);
			}
		}
		catch (SQLException ex){
			throw ex;
		}
		finally {
			driver.desconectar();
		}

		return ayudas;
	}

	public boolean borrarBeneficiario (String DNI) throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance() ;
		String sql1 = "DELETE FROM Beneficiario WHERE NIF='"+DNI+"'";
		String sql2 = "DELETE from familiar WHERE beneficiarioNIF='"+DNI+"'";
		String sql3 = "DELETE from persona WHERE NIF='"+DNI+"'";

		try {
			driver.inicioTransaccion();
			driver.eliminar(sql1);
			driver.eliminar(sql2);
			driver.eliminar(sql3);
			driver.commit();
		}
		catch (SQLException ex){
			throw ex;
		}
		finally{
			driver.finTransaccion();
		}

		return true;
	}

	public boolean añadirFamiliar (Beneficiario b, String beneficiarioNIF, String paren) throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance() ;
		String sql1 = "INSERT INTO persona (NIF,Nombre,Apellidos,FechaNacimiento,CP,TelefonoFijo,TelefonoMovil,Domicilio,Localidad) VALUES ('"
				+b.getNIF()+"','"+b.getNombre()+"','"+b.getApellidos()+"','"+b.getFechaDENacimiento()+"','"+b.getCP()+"','"+b.getTelefonoFijo()+"','"+b.getTelefonoMovil()+"','"+b.getDomicilio()+"','"+b.getLocalidad()+"' )";
		String sql2 = "INSERT INTO familia (PersonaNIF1, PersonaNIF2) VALUES ('"+beneficiarioNIF+"','"+b.getNIF()+"')";

		try {
			driver.inicioTransaccion();
			driver.insertar(sql1);
			driver.insertar(sql2);
			driver.commit();
		}
		catch (SQLException ex){
			throw ex;
		}
		finally{
			driver.finTransaccion();
		}

		return true;
	}

	public ArrayList<Persona> datosFamiliares(String beneficiario_DNI) throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance() ;
		String sql = "SELECT * FROM persona p, familia f WHERE (f.PersonaNIF1='"+beneficiario_DNI+"') AND p.NIF=f.PersonaNIF2";
		ArrayList<Persona> familiares = new ArrayList<Persona>();

		try {
			driver.conectar();
			ResultSet resultados = driver.seleccionar(sql);

			while(resultados.next()){
				Persona temp = null;
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
		}
		catch (SQLException ex){
			throw ex;
		}
		finally{
			driver.desconectar();
		}

		return familiares;
	}

	public boolean eliminarDatosfamiliar(String familiarDNI, String beneficiarioDNI) throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance() ;
		String sentencia = "DELETE FROM familia WHERE PersonaNIF2= '"+familiarDNI+"' AND PersonaNIF1 = '"+beneficiarioDNI+"'";
		String sentencia2 = "DELETE FROM persona WHERE NIF= '"+familiarDNI+"'";

		try{
			driver.conectar();
			driver.eliminar(sentencia);
			driver.eliminar(sentencia2);
		}
		catch (SQLException ex){
			throw ex;
		}
		finally{
			driver.desconectar();
		}

		return true;
	}

}
