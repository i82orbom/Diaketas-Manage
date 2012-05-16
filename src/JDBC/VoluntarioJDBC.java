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
 **
 ** NOTAS:
 **   
 **
 */
package JDBC;

import Modelo.Voluntario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jobero
 */
public class VoluntarioJDBC {
    
    private static VoluntarioJDBC instancia;
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    
    private VoluntarioJDBC(){
        
    }
    
    public static VoluntarioJDBC getInstance(){
        
        if(instancia == null)
             instancia = new VoluntarioJDBC();
        return instancia;
        
    }
    
    public boolean anadirVoluntario (Voluntario voluntario) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance() ;
        Integer Cp = voluntario.getCP();
        String Cp_cadena = Cp.toString();
        Integer telefono_fijo = voluntario.getTelefonoFijo();
        String telefono_fijo_cadena = telefono_fijo.toString();
        Integer telefono_movil = voluntario.getTelefonoMovil();
        String telefono_movil_cadena = telefono_movil.toString();
        
        String sql = "INSERT INTO persona (NIF,Nombre,Apellidos,CP,TelefonoFijo,TelefonoMovil,Domicilio,Localidad,FechaNacimiento) VALUES ('"+voluntario.getNIF()+"','"+voluntario.getNombre()+"','"+voluntario.getApellidos()+"','"+Cp_cadena+"','"+telefono_fijo_cadena+"','"+telefono_movil_cadena+"','"+voluntario.getDomicilio()+"','"+voluntario.getLocalidad()+"','"+formatter.format(voluntario.getFechaDENacimiento())+"')";
	
        String sql2 = "INSERT INTO voluntario (OID,Password) VALUES (LAST_INSERT_ID(),'"+voluntario.getPassword()+"')";
        
        boolean exito = driver.insertar(sql);
        if (exito)
            exito = driver.insertar(sql2);
        
        return exito;
        
    }

    public boolean borrarVoluntario(String DNI) throws SQLException {

        DriverJDBC driver = DriverJDBC.getInstance();

        int OID = getOIDVoluntarioFromPersona(DNI);

        String sql = "DELETE FROM voluntario WHERE OID='" + OID + "'";

        boolean exito = driver.eliminar(sql);

        if (exito) {
            sql = "DELETE from persona WHERE NIF='" + DNI + "'";
            exito = driver.eliminar(sql);
        }

        return exito;
    }

    public boolean modificarDatosVoluntario(Voluntario voluntario) throws SQLException {

        DriverJDBC driver = DriverJDBC.getInstance();
        Integer Cp = voluntario.getCP();
        String Cp_cadena = Cp.toString();
        Integer telefono_fijo = voluntario.getTelefonoFijo();
        String telefono_fijo_cadena = telefono_fijo.toString();
        Integer telefono_movil = voluntario.getTelefonoMovil();
        String telefono_movil_cadena = telefono_movil.toString();


        String sql2 = "UPDATE persona SET NIF='" + voluntario.getNIF() + "',Nombre='" + voluntario.getNombre() + "',Apellidos='" + voluntario.getApellidos() + "',FechaNacimiento='" + voluntario.getFechaDENacimiento().toString() + "',CP='" + Cp_cadena + "',TelefonoFijo='" + telefono_fijo_cadena + "',TelefonoMovil='" + telefono_movil_cadena + "',Domicilio='" + voluntario.getDomicilio() + "',Localidad='" + voluntario.getLocalidad() + "' WHERE NIF =" + voluntario.getNIF() + "'";
        int OID = getOIDVoluntarioFromPersona(voluntario.getNIF());
        String sql = "UPDATE voluntario SET Password='" + voluntario.getPassword() + "' WHERE OID =" + OID + "'";

        boolean exito = driver.actualizar(sql);

        if (exito) {
            exito = driver.actualizar(sql2);
        }

        return exito;
    }

    public ArrayList<Voluntario> obtenerListadoVoluntario(String dato, String tipoDato) throws SQLException {

        DriverJDBC driver = DriverJDBC.getInstance();

        String sql = "SELECT * FROM voluntario v, persona p WHERE " + tipoDato + "='" + dato + "' AND p.OID=v.OID";

        ResultSet resultados = driver.seleccionar(sql);

        ArrayList<Voluntario> listadoVoluntarios = new ArrayList<Voluntario>();
        Voluntario temp;

        if (resultados.next()) {
            temp = new Voluntario();
            temp.setPassword(resultados.getString("Password"));
            temp.setNIF(resultados.getString("NIF"));
            temp.setNombre(resultados.getString("Nombre"));
            temp.setApellidos(resultados.getString("Apellidos"));
            temp.setFechaDENacimiento(resultados.getDate("FechaNacimiento"));
            temp.setCP(resultados.getInt("CP"));
            temp.setTelefonoFijo(resultados.getInt("TelefonoFijo"));
            temp.setTelefonoMovil(resultados.getInt("TelefonoMovil"));
            temp.setDomicilio(resultados.getString("Domicilio"));
            temp.setLocalidad(resultados.getString("Localidad"));
            listadoVoluntarios.add(temp);

        }

        return listadoVoluntarios;
    }

    public Voluntario obtenerVoluntario(String DNI) throws SQLException {

        DriverJDBC driver = DriverJDBC.getInstance();

        String sql = "SELECT * FROM voluntario v, persona p WHERE (p.NIF='" + DNI + "') AND p.OID=v.OID";


        ResultSet resultado = driver.seleccionar(sql);

        Voluntario voluntario = null;

        if (resultado.next()) {
            voluntario = new Voluntario();
            voluntario.setPassword(resultado.getString("Password"));
            voluntario.setNIF(resultado.getString("NIF"));
            voluntario.setNombre(resultado.getString("Nombre"));
            voluntario.setApellidos(resultado.getString("Apellidos"));
            voluntario.setCP(resultado.getInt("CP"));
            voluntario.setTelefonoFijo(resultado.getInt("TelefonoFijo"));
            voluntario.setTelefonoMovil(resultado.getInt("TelefonoMovil"));
            voluntario.setDomicilio(resultado.getString("Domicilio"));
            voluntario.setLocalidad(resultado.getString("Localidad"));
            voluntario.setFechaDENacimiento(resultado.getDate("FechaNacimiento"));
            voluntario.setOID(resultado.getLong("OID"));
        }

        return voluntario;

    }

    public int getOIDVoluntarioFromPersona(String DNI) {
        String sql = "SELECT * FROM persona p WHERE (p.NIF='" + DNI + "')";
        int OID = -1;

        try {
            ResultSet resultado = DriverJDBC.getInstance().seleccionar(sql);
            OID = resultado.getInt("OID");
        } catch (SQLException ex) {
            Logger.getLogger(VoluntarioJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return OID;
    }
}
