/**
 ** NOMBRE CLASE:
 **	  VoluntarioJDBC.java
 **
 ** DESCRIPCION:
 **       Abstracción JDBC de Voluntario
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
 *      001 - Mar 27, 2012 - FBR - Implementación de los métodos
 *      002 - Mar 30, 2012 - AAN - Reestructuracion para la tabla persona
 *      003 - Abr 14, 2012 - AAN - Modificacion sentencias SQL
 *      004 - May 16, 2012 - RC  - Revision y testo de todos los metodos con los OID y adicion de getOIDVoluntarioFromPersona(String DNI)
 *		005 - May 24, 2012 - ARS - Revisión para conectar y desconectar en las consultas
 **
 ** NOTAS:
 **
 **
 */
package JDBC;

import Controladores.TestDatos;
import Modelo.Voluntario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VoluntarioJDBC {

    private static VoluntarioJDBC instancia;

    private VoluntarioJDBC() {
    }

    public static VoluntarioJDBC getInstance() {
        if (instancia == null) {
            instancia = new VoluntarioJDBC();
        }
        return instancia;
    }

    public boolean anadirVoluntario(Voluntario voluntario) throws SQLException {
        DriverJDBC driver = DriverJDBC.getInstance();
        boolean exito = true;

        String sql1 = "INSERT INTO persona (NIF,Nombre,Apellidos,CP,TelefonoFijo,TelefonoMovil,Domicilio,Localidad,FechaNacimiento) VALUES ('"
                + voluntario.getNIF() + "','" + voluntario.getNombre() + "','" + voluntario.getApellidos() + "','" + voluntario.getCP() + "','" + voluntario.getTelefonoFijo() + "','" + voluntario.getTelefonoMovil() + "','" + voluntario.getDomicilio() + "','" + voluntario.getLocalidad() + "','" + TestDatos.formatterBD.format(voluntario.getFechaDENacimiento()) + "')";
        String sql2 = "INSERT INTO voluntario (OID,Password) VALUES (LAST_INSERT_ID(),'" + voluntario.getPassword() + "')";

        try {
            driver.inicioTransaccion();
            driver.insertar(sql1);
            driver.insertar(sql2);
            driver.commit();
        } catch (SQLException ex) {
            driver.rollback();
            exito = false;
            throw ex;
        } finally {
            driver.finTransaccion();
        }

        return exito;
    }

    public boolean borrarVoluntario(Voluntario voluntario) throws SQLException {
        DriverJDBC driver = DriverJDBC.getInstance();
        boolean exito = true;

        String sql1 = "DELETE FROM voluntario WHERE OID='" + voluntario.getOID() + "'";
        String sql2 = "DELETE from persona WHERE OID='" + voluntario.getOID() + "'";

        try {
            driver.inicioTransaccion();
            driver.eliminar(sql1);
            driver.eliminar(sql2);

            driver.commit();
        } catch (SQLException ex) {
            driver.rollback();
            exito = false;
            throw ex;
        } finally {
            driver.finTransaccion();
        }

        return exito;
    }

    public boolean modificarDatosVoluntario(Voluntario voluntario) throws SQLException {
        DriverJDBC driver = DriverJDBC.getInstance();
        boolean exito = true;

        String sql1 = "UPDATE persona SET NIF='" + voluntario.getNIF() + "',Nombre='" + voluntario.getNombre() + "',Apellidos='" + voluntario.getApellidos() + "',FechaNacimiento='" + TestDatos.formatterBD.format(voluntario.getFechaDENacimiento()) + "',CP='" + voluntario.getCP() + "',TelefonoFijo='" + voluntario.getTelefonoFijo() + "',TelefonoMovil='" + voluntario.getTelefonoMovil() + "',Domicilio='" + voluntario.getDomicilio() + "',Localidad='" + voluntario.getLocalidad() + "' WHERE OID ='" + voluntario.getOID() + "'";
        String sql2 = "";
        if (voluntario.getPassword() != null) {
            sql2 = "UPDATE voluntario SET Password='" + voluntario.getPassword() + "' WHERE OID ='" + voluntario.getOID() + "'";
        }

        try {
            driver.inicioTransaccion();
            driver.actualizar(sql1);
            if (voluntario.getPassword() != null) {
                driver.actualizar(sql2);
            }
            driver.commit();
        } catch (SQLException ex) {
            driver.rollback();
            exito = false;
            throw ex;
        } finally {
            driver.finTransaccion();
        }

        return exito;
    }

    public ArrayList<Voluntario> obtenerListadoVoluntario(String dato, String tipoDato) throws SQLException {
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "SELECT * FROM voluntario v, persona p WHERE " + tipoDato + " LIKE '%" + dato + "%' AND p.OID=v.OID";
        ResultSet resultados;
        ArrayList<Voluntario> listadoVoluntarios = new ArrayList<Voluntario>();
        Voluntario temp;

        try {
            driver.conectar();
            resultados = driver.seleccionar(sql);

            while (resultados.next()) {
                temp = new Voluntario();
                temp.setPassword(resultados.getString("Password"));
                temp.setNIF(resultados.getString("NIF"));
                temp.setNombre(resultados.getString("Nombre"));
                temp.setApellidos(resultados.getString("Apellidos"));
                temp.setFechaDENacimiento(resultados.getDate("FechaNacimiento"));
                temp.setCP(resultados.getString("CP"));
                temp.setTelefonoFijo(resultados.getString("TelefonoFijo"));
                temp.setTelefonoMovil(resultados.getString("TelefonoMovil"));
                temp.setDomicilio(resultados.getString("Domicilio"));
                temp.setLocalidad(resultados.getString("Localidad"));
                temp.setOID(resultados.getLong("OID"));
                listadoVoluntarios.add(temp);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            driver.desconectar();
        }

        return listadoVoluntarios;
    }

    public Voluntario obtenerVoluntario(String DNI) throws SQLException {
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "SELECT * FROM voluntario v, persona p WHERE (p.NIF='" + DNI + "') AND p.OID=v.OID";
        Voluntario voluntario = null;
        ResultSet resultado;

        try {
            driver.conectar();
            resultado = driver.seleccionar(sql);
            if (resultado.next()) {
                voluntario = new Voluntario();
                voluntario.setPassword(resultado.getString("Password"));
                voluntario.setNIF(resultado.getString("NIF"));
                voluntario.setNombre(resultado.getString("Nombre"));
                voluntario.setApellidos(resultado.getString("Apellidos"));
                voluntario.setCP(resultado.getString("CP"));
                voluntario.setTelefonoFijo(resultado.getString("TelefonoFijo"));
                voluntario.setTelefonoMovil(resultado.getString("TelefonoMovil"));
                voluntario.setDomicilio(resultado.getString("Domicilio"));
                voluntario.setLocalidad(resultado.getString("Localidad"));
                voluntario.setFechaDENacimiento(resultado.getDate("FechaNacimiento"));
                voluntario.setOID(resultado.getLong("OID"));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            driver.desconectar();
        }

        return voluntario;
    }
      public Voluntario obtenerVoluntario(Long OID) throws SQLException {
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "SELECT * FROM voluntario v, persona p WHERE (p.OID='" + OID + "') AND p.OID=v.OID";
        Voluntario voluntario = null;
        ResultSet resultado;

        try {
            driver.conectar();
            resultado = driver.seleccionar(sql);
            if (resultado.next()) {
                voluntario = new Voluntario();
                voluntario.setPassword(resultado.getString("Password"));
                voluntario.setNIF(resultado.getString("NIF"));
                voluntario.setNombre(resultado.getString("Nombre"));
                voluntario.setApellidos(resultado.getString("Apellidos"));
                voluntario.setCP(resultado.getString("CP"));
                voluntario.setTelefonoFijo(resultado.getString("TelefonoFijo"));
                voluntario.setTelefonoMovil(resultado.getString("TelefonoMovil"));
                voluntario.setDomicilio(resultado.getString("Domicilio"));
                voluntario.setLocalidad(resultado.getString("Localidad"));
                voluntario.setFechaDENacimiento(resultado.getDate("FechaNacimiento"));
                voluntario.setOID(resultado.getLong("OID"));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            driver.desconectar();
        }

        return voluntario;
    }

}
