/**
 ** NOMBRE CLASE:
 **	  SocioJDBC.java
 **
 ** DESCRIPCION:
 **       Abstracción JDBC de Socio
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
import Modelo.Cuota;
import Modelo.Socio;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PLB
 */
public class SocioJDBC {

    /**
     * Instancia SocioJDBC
     *
     */
    private static SocioJDBC instancia;
	private static int OID_Anonimo = 0; 
    /**
     * Constructor
     *
     */
    private SocioJDBC(){
    }

    /**
     * Devuelve la instancia de la clase, sino la crea
     * @return
     */
    public static SocioJDBC getInstance(){
        if(instancia == null)
             instancia = new SocioJDBC();
        return instancia;
    }

    /**
     * Añade un Socio al sistema
     * @param socio
     * @return true Si se ha conseguido añadir el socio
     * @throws SQLException
     */
    public boolean añadirSocio(Socio socio) throws SQLException{
	DriverJDBC driver = DriverJDBC.getInstance();
	String sql1 = "INSERT INTO Colaborador (Direccion, Localidad, Provincia, CP, TelefonoFijo, TelefonoMovil, Email) VALUES ('"+socio.getDireccion()+"','"+socio.getLocalidad()+"','"+socio.getProvincia()+"','"+socio.getCP()+"','"+socio.getTelefonoFijo()+"','"+socio.getTelefonoMovil()+"','"+socio.getEmail()+"')";
        String sql2 = "INSERT INTO C_Persona (OID, DNI, Nombre, Apellidos, FechaNacimiento,Sexo) VALUES (LAST_INSERT_ID(),'"+socio.getDNI()+"','"+socio.getNombre()+"','"+socio.getApellidos()+"','"+TestDatos.formatterBD.format(socio.getFechaDeNacimiento())+"','"+socio.getSexo()+"')";
        String sql3 = "INSERT INTO Socio (OID, usuario, contrasena) VALUES (LAST_INSERT_ID(),'"+socio.getUsuario()+"','"+socio.getContrasena()+"')";

        try{
                driver.inicioTransaccion();
                driver.insertar(sql1);
                driver.insertar(sql2);
                driver.insertar(sql3);
                driver.commit();
        }
        catch (SQLException ex){
                driver.rollback();
                throw ex;
        }
        finally{
                driver.finTransaccion();
        }

        return true;
    }

    /**
     * Modifica los datos de un Socio del sistema
     * @param socio
     * @return true Si se han modificado correctamente los datos del Socio
     * @throws SQLException
     */
    public boolean modificarDatosSocio(Socio socio) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();

        String sql = "UPDATE Colaborador SET Direccion='"+socio.getDireccion()+"', Localidad='"+socio.getLocalidad()+"', Provincia='"+socio.getProvincia()+"', CP='"+socio.getCP()+"',TelefonoFijo='"+socio.getTelefonoFijo()+"', TelefonoMovil='"+socio.getTelefonoMovil()+"', Email='"+socio.getEmail()+"'WHERE OID='"+socio.getOID()+"'";
        String sql2 = "UPDATE C_Persona SET DNI='"+socio.getDNI()+"', Nombre='"+socio.getNombre()+"', Apellidos='"+socio.getApellidos()+"', FechaNacimiento='"+TestDatos.formatterBD.format(socio.getFechaDeNacimiento())+"', Sexo='"+socio.getSexo()+"'WHERE OID='"+socio.getOID()+"'";
        String sql3 = "UPDATE Socio SET usuario='"+socio.getUsuario()+"', contrasena='"+socio.getContrasena()+"'WHERE OID='"+socio.getOID()+"'";

        try{
                driver.inicioTransaccion();
                driver.actualizar(sql);
                driver.actualizar(sql2);
                driver.actualizar(sql3);
                driver.commit();
        }
        catch (SQLException ex){
                driver.rollback();
                throw ex;
        }
        finally{
                driver.finTransaccion();
        }
        return true;
    }

    /**
     * Elimina a un Socio del sistema
     * @param socio
     * @return true Si se ha podido eliminar al Socio
     * @throws SQLException
     */
    public boolean eliminarSocio(Socio socio) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
		
        String sql = "UPDATE Colaboracion SET OID="+OID_Anonimo+" WHERE OID='"+socio.getOID()+"'";
        String sql2 = "UPDATE PagoCuota SET OID="+OID_Anonimo+" WHERE OIDSocio='"+socio.getOID()+"'";
        String sql3 = "UPDATE Cuota SET fechaFin=fechaUltimoPago WHERE OIDSocio='"+socio.getOID()+"'";
        String sql4 = "UPDATE Cuota SET OIDSocio="+OID_Anonimo+" WHERE OIDSocio='"+socio.getOID()+"'";
        String sql5 = "DELETE FROM Socio WHERE OID='"+socio.getOID()+"'";
        String sql6 = "DELETE FROM C_Persona WHERE OID='"+socio.getOID()+"'";
        String sql7 = "DELETE FROM Colaborador WHERE OID='"+socio.getOID()+"'";

        try{
                driver.inicioTransaccion();
                driver.actualizar(sql);
                driver.actualizar(sql2);
                driver.actualizar(sql3);
                driver.actualizar(sql4);
                driver.eliminar(sql5);
                driver.eliminar(sql6);
                driver.eliminar(sql7);
                driver.commit();
        }
        catch (SQLException ex){
                driver.rollback();
                throw ex;
        }
        finally{
                driver.finTransaccion();
        }
        return true;
    }

    /**
     * Consultar los datos de un Socio
     * @param DNI
     * @return socio El socio que se buscaba
     * @throws SQLException
     */
    public Socio obtenerSocio(String DNI) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();

        String sql = "SELECT * FROM Colaborador c, C_Persona p, Socio s WHERE (p.DNI='"+DNI+"') AND c.OID=p.OID AND s.OID=p.OID AND s.OID=c.OID";
        Socio socio = null;

         try {
            driver.conectar();
            ResultSet rs = driver.seleccionar(sql);

            if(rs.next()){
                socio = new Socio();
				socio.setOID(rs.getLong("OID"));
                socio.setUsuario(rs.getString("Usuario"));
                socio.setContrasena(rs.getString("contrasena"));

                socio.setDNI(rs.getString("DNI"));
                socio.setNombre(rs.getString("Nombre"));
                socio.setApellidos(rs.getString("Apellidos"));
                socio.setFechaDeNacimiento(rs.getDate("FechaNacimiento"));

                char bufSexo[] = new char[1];
                try {
                    rs.getCharacterStream("Sexo").read(bufSexo);
                } catch (IOException ex) {
                    Logger.getLogger(SocioJDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
                socio.setSexo(bufSexo[0]);

                socio.setCP(rs.getString("CP"));
                socio.setDireccion(rs.getString("Direccion"));
                socio.setEmail(rs.getString("Email"));
                socio.setLocalidad(rs.getString("Localidad"));
                socio.setProvincia(rs.getString("Provincia"));
                socio.setTelefonoFijo(rs.getString("TelefonoFijo"));
                socio.setTelefonoMovil(rs.getString("TelefonoMovil"));
            }
        }
        catch (SQLException ex){
            throw ex;
	}
	finally{
		driver.desconectar();
	}
            return socio;

    }
    /**
     * Comprueba si un usuario ya exito o no
     * @param Usuario
     * @return true si existe ya ese usuario en el sistema
     * @throws SQLException 
     */
    public boolean comprobarUsuarioUnico(String Usuario) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();

        String sql = "SELECT * FROM Colaborador c, C_Persona p, Socio s WHERE (s.Usuario='"+Usuario+"') AND c.OID=p.OID AND s.OID=p.OID AND s.OID=c.OID";
        boolean existe = true;

        try {
            driver.conectar();
            ResultSet rs = driver.seleccionar(sql);

            if(rs.next()){
                existe = true;
            }else
                existe = false;
        }
        catch (SQLException ex){
            throw ex;
	}
	finally{
            driver.desconectar();
	}
        return existe;
    }

    /**
     * Obtener un listado de todos los Socios del Sistema
     * @param tipoBusqueda
     * @param valor
     * @return listaSocios Listado de los Socios del sistema
     * @throws SQLException
     */
    public ArrayList<Socio> obtenerListadoSocios(String tipoBusqueda, String valor) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "SELECT * FROM Socio s, C_Persona p, Colaborador c WHERE "+tipoBusqueda+" LIKE '%"+valor+"%' AND s.OID=p.OID AND s.OID=c.OID AND p.OID=c.OID";
        ArrayList<Socio> listaSocios = new ArrayList<Socio>();
        Socio socio = null;

        try {
            driver.conectar();
            ResultSet rs = driver.seleccionar(sql);

            while(rs.next()){
                socio = new Socio();
				socio.setOID(rs.getLong("OID"));
                socio.setUsuario(rs.getString("usuario"));
                socio.setContrasena(rs.getString("contrasena"));

                socio.setDNI(rs.getString("DNI"));
                socio.setNombre(rs.getString("Nombre"));
                socio.setApellidos(rs.getString("Apellidos"));
                socio.setFechaDeNacimiento(rs.getDate("FechaNacimiento"));

                char bufSexo[] = new char[1];
                try {
                    rs.getCharacterStream("Sexo").read(bufSexo);
                } catch (IOException ex) {
                    Logger.getLogger(SocioJDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
                socio.setSexo(bufSexo[0]);

                socio.setCP(rs.getString("CP"));
                socio.setDireccion(rs.getString("Direccion"));
                socio.setEmail(rs.getString("Email"));
                socio.setLocalidad(rs.getString("Localidad"));
                socio.setProvincia(rs.getString("Provincia"));
                socio.setTelefonoFijo(rs.getString("TelefonoFijo"));
                socio.setTelefonoMovil(rs.getString("TelefonoMovil"));

                listaSocios.add(socio);
            }
        }
        catch (SQLException ex){
            throw ex;
	}
	finally{
		driver.desconectar();
	}
        return listaSocios;

    }

    /**
     * Obtener la cuota activa de un Socio
     * @param socio
     * @return c La cuota activa del socio
     * @throws SQLException
     */
    public Cuota obtenerCuotaActiva(Socio socio) throws SQLException{

        DriverJDBC driver = DriverJDBC.getInstance();
        Cuota c = new Cuota();
		
        String sql = "SELECT * FROM Cuota c WHERE c.OIDSocio='"+socio.getOID()+"' AND (c.FechaFin > CURDATE() OR c.FechaFin IS NOT NULL)";
        try {
            driver.conectar();
            ResultSet rs = driver.seleccionar(sql);

            if(rs.next()){
				c.setOIDCuota(rs.getLong("OID"));
				c.setSocio(socio);
                c.setCantidad(rs.getDouble("Cantidad"));
                c.setFechaFin(rs.getDate("FechaFin"));
                c.setFechaInicial(rs.getDate("FechaInicio"));
                c.setFechaUltimoPago(rs.getDate("FechaUltimoPago"));
                c.setIntervaloPagos(rs.getInt("IntervalosPagos"));
				c.setSocio(socio);
            }
        }
        catch (SQLException ex){
            throw ex;
	}
	finally{
		driver.desconectar();
	}
        return c;
    }

}
