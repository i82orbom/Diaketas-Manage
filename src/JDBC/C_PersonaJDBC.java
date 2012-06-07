/**
 ** NOMBRE CLASE:
 **	  C_PersonaJDBC.java
 **
 ** DESCRIPCION:
 **       Abstracción JDBC de Empresa
 **
 **
 ** DESARROLLADO POR:
 **       Francisco José Legaza Bailon (PLB)
 **
 **
 ** SUPERVISADO POR:
 **
 **
 ** HISTORIA:
 ** 	000 - Abril 26, 2012 - PLB - Creacion
 **
 **
 **
 ** NOTAS:
 **
 **
 */
package JDBC;

import Controladores.TestDatos;
import Modelo.C_Persona;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PLB
 */
public class C_PersonaJDBC {

    /**
     * Instancia C_PersonaJDBC
     */
    private static C_PersonaJDBC instancia;
	private static int OID_Anonimo = 0; 
    /**
     * Constructor
     */
    private C_PersonaJDBC(){
    }

    /**
     * Devuelve la instancia de la clase, sino la crea
     * @return Instancia
     */
    public static C_PersonaJDBC getInstance(){
        if(instancia == null)
             instancia = new C_PersonaJDBC();
        return instancia;

    }

    /**
     * Añade un Colaborador Persona al sistema
     * @param persona
     * @return true Si se ha podido añadir el Colaborador Persona
     * @throws SQLException
     */
    public boolean añadirC_Persona(C_Persona persona) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        String sql1 = "INSERT INTO Colaborador (Direccion, Localidad, Provincia, CP, TelefonoFijo, TelefonoMovil, Email) VALUES ('"+persona.getDireccion()+"','"+persona.getLocalidad()+"','"+persona.getProvincia()+"','"+persona.getCP()+"','"+persona.getTelefonoFijo()+"','"+persona.getTelefonoMovil()+"','"+persona.getEmail()+"')";
        String sql2 = "INSERT INTO C_Persona (OID, DNI, Nombre, Apellidos, FechaNacimiento,Sexo) VALUES (LAST_INSERT_ID(),'"+persona.getDNI()+"','"+persona.getNombre()+"','"+persona.getApellidos()+"','"+TestDatos.formatterBD.format(persona.getFechaDeNacimiento())+"','"+persona.getSexo()+"')";
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

    /**
     * Modifica los datos de un Colaborador Persona
     * @param persona
     * @return true Si se han podido modificar los datos
     * @throws SQLException
     */
    public boolean modificarDatosC_Persona(C_Persona persona) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "UPDATE Colaborador SET Direccion='"+persona.getDireccion()+"', Localidad='"+persona.getLocalidad()+"', Provincia='"+persona.getProvincia()+"', CP='"+persona.getCP()+"', TelefonoFijo='"+persona.getTelefonoFijo()+"', TelefonoMovil='"+persona.getTelefonoMovil()+"', Email='"+persona.getEmail()+"'WHERE OID='"+persona.getOID()+"'";
        String sql2 = "UPDATE C_Persona SET DNI='"+persona.getDNI()+"', Nombre='"+persona.getNombre()+"', Apellidos='"+persona.getApellidos()+"', FechaNacimiento='"+TestDatos.formatterBD.format(persona.getFechaDeNacimiento())+"', Sexo ='"+ persona.getSexo() +"'WHERE OID='"+persona.getOID()+"'";
		System.out.println(sql2);

        try{
            driver.inicioTransaccion();
            driver.actualizar(sql);
            driver.actualizar(sql2);
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

    /**
     * Elimina a un Colaborador Persona del sistema
     * @param persona
     * @return true Si se ha podido eliminar al Colaborador
     * @throws SQLException
     */
    public boolean eliminarC_Persona(C_Persona persona) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "UPDATE Colaboracion SET OIDColaborador="+OID_Anonimo+" WHERE OID='"+persona.getOID()+"'";
        String sql2 = "DELETE FROM C_Persona WHERE OID='"+persona.getOID()+"'";
        String sql3 = "DELETE FROM Colaborador WHERE OID='"+persona.getOID()+"'";

        try{
            driver.inicioTransaccion();
            driver.actualizar(sql);
            driver.eliminar(sql2);
            driver.eliminar(sql3);
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

    /**
     * Consultar los datos de un Colaborador Persona
     * @param DNI
     * @return Persona Colaborador Persona que se queria consultar
     * @throws SQLException
     */
    public C_Persona obtenerC_Persona(String DNI) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();

        String sql = "SELECT * FROM Colaborador c, C_Persona p WHERE (p.DNI='"+DNI+"') AND c.OID=p.OID";
        C_Persona Persona = null;


        try {
            driver.conectar();
            ResultSet rs = driver.seleccionar(sql);


            if(rs.next()){
                Persona = new C_Persona();
				Persona.setOID(rs.getLong("OID"));
                Persona.setDNI(rs.getString("DNI"));
                Persona.setNombre(rs.getString("Nombre"));
                Persona.setApellidos(rs.getString("Apellidos"));
                Persona.setFechaDeNacimiento(rs.getDate("FechaNacimiento"));
				char bufSexo[] = new char[1];
                try {
                    rs.getCharacterStream("Sexo").read(bufSexo);
                } catch (IOException ex) {
                    Logger.getLogger(C_PersonaJDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
                Persona.setSexo(bufSexo[0]);

                Persona.setCP(rs.getString("CP"));
                Persona.setDireccion(rs.getString("Direccion"));
                Persona.setEmail(rs.getString("Email"));
                Persona.setLocalidad(rs.getString("Localidad"));
                Persona.setProvincia(rs.getString("Provincia"));
                Persona.setTelefonoFijo(rs.getString("TelefonoFijo"));
                Persona.setTelefonoMovil(rs.getString("TelefonoMovil"));
            }
        }
        catch (SQLException ex){
            throw ex;
	}
	finally{
		driver.desconectar();
	}

        return Persona;
    }

    /**
     * Obtener un listado de todos los Colaboradores Persona del sistema
     * @param tipoBusqueda
     * @param valor
     * @return listaC_Persona Listado de los Colaboradores Persona del sistema
     * @throws SQLException
     */
    public ArrayList<C_Persona> buscarC_Persona(String tipoBusqueda, String valor) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "SELECT * FROM C_Persona p, Colaborador c, Socio s WHERE "+tipoBusqueda+" LIKE '%"+valor+"%' AND p.OID=c.OID AND p.OID!=s.OID AND c.OID!=s.OID AND c.OID!=0 AND p.OID!=0 AND s.OID!=0";
        ArrayList<C_Persona> listaC_Persona = new ArrayList<C_Persona>();
       

        try {
            driver.conectar();
            ResultSet resultados = driver.seleccionar(sql);

			while(resultados.next()){
				if(resultados.getLong("OID")!=0){
					C_Persona Persona = new C_Persona();
					Persona.setOID(resultados.getLong("OID"));
					Persona.setDNI(resultados.getString("DNI"));
					Persona.setNombre(resultados.getString("Nombre"));
					Persona.setApellidos(resultados.getString("Apellidos"));
					Persona.setFechaDeNacimiento(resultados.getDate("FechaNacimiento"));

					char bufSexo[] = new char[1];
					try {
						resultados.getCharacterStream("Sexo").read(bufSexo);
					} catch (IOException ex) {
						Logger.getLogger(C_PersonaJDBC.class.getName()).log(Level.SEVERE, null, ex);
					}
					Persona.setSexo(bufSexo[0]);

					Persona.setCP(resultados.getString("CP"));
					Persona.setDireccion(resultados.getString("Direccion"));
					Persona.setEmail(resultados.getString("Email"));
					Persona.setLocalidad(resultados.getString("Localidad"));
					Persona.setProvincia(resultados.getString("Provincia"));
					Persona.setTelefonoFijo(resultados.getString("TelefonoFijo"));
					Persona.setTelefonoMovil(resultados.getString("TelefonoMovil"));

					listaC_Persona.add(Persona);
				}
            }
        }
        catch (SQLException ex){
            throw ex;
	}
	finally{
		driver.desconectar();
	}

        return listaC_Persona;

    }

    /**
     * Convertir un Colaborador Persona en un Socio del sistema
     * @param persona
     * @param usuarioN
     * @param contrasena
     * @return true Si se ha podido convertir en Socio
     * @throws SQLException
     */
    public boolean hacerSocio(C_Persona persona, String usuarioN, String contrasena) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "INSERT INTO Socio (OID, usuario, contrasena) VALUES ('"+persona.getOID()+"','"+usuarioN+"','"+contrasena+"')";

        try{
            driver.inicioTransaccion();
            driver.insertar(sql);
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



}
