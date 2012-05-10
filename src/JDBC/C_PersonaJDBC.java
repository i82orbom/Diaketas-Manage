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

import Modelo.C_Persona;
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
public class C_PersonaJDBC {
    
    /**
     * Instancia C_PersonaJDBC
     */
    private static C_PersonaJDBC instancia;
    
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
        
        String DNI = persona.getDNI();
        String Nombre = persona.getNombre();
        String Apellidos = persona.getApellidos();
        Date FechaDeNacimiento = persona.getFechaDeNacimiento();
        char Sexo = persona.getSexo();
        
        String sql = "INSERT INTO Colaborador (Direccion, Localidad, Provincia, codigoPostal, TelefojonoFijo, TelefojonoMovil, Email) VALUES ('"+persona.getDireccion()+"','"+persona.getLocalidad()+"','"+persona.getProvincia()+"','"+persona.getCP()+"','"+persona.getTelefonoFijo()+"','"+persona.getTelefonoMovil()+"','"+persona.getEmail()+"')";
        boolean exito = driver.insertar(sql);
        String sql2 = "INSERT INTO C_Persona (DNI, Nombre, Apellidos, FechaDeNacimiento) VALUES ('"+DNI+"','"+Nombre+"','"+Apellidos+"','"+FechaDeNacimiento+"','"+Sexo+"')";
        boolean exito2 = driver.insertar(sql2);
                
        return (exito && exito2);
    }
    
    /**
     * Modifica los datos de un Colaborador Persona
     * @param persona
     * @return true Si se han podido modificar los datos
     * @throws SQLException 
     */
    public boolean modificarDatosC_Persona(C_Persona persona) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
        String DNI = persona.getDNI();
        String Nombre = persona.getNombre();
        String Apellidos = persona.getApellidos();
        Date FechaDeNacimiento = persona.getFechaDeNacimiento();
        char Sexo = persona.getSexo();
        
        String sql = "UPDATE Colaborador SET Direccion='"+persona.getDireccion()+"', Localidad='"+persona.getLocalidad()+"', Provincia='"+persona.getProvincia()+"', codigoPostal='"+persona.getCP()+"', TelefonoFijo='"+persona.getTelefonoFijo()+"', TelefonoMovil='"+persona.getTelefonoMovil()+"', Email='"+persona.getEmail()+"WHERE OID="+persona.getOIDColaborador()+"'";
        boolean exito = driver.insertar(sql);
        String sql2 = "UPDATE C_Persona SET DNI='"+DNI+"', Nombre='"+Nombre+"', Apellidos='"+Apellidos+"', FechaDeNacimiento='"+FechaDeNacimiento+"WHERE OID="+persona.getOIDPersona()+"'";
        boolean exito2 = driver.insertar(sql2);
        
        return (exito && exito2);
    }
    
    /**
     * Elimina a un Colaborador Persona del sistema
     * @param persona
     * @return true Si se ha podido eliminar al Colaborador
     * @throws SQLException 
     */
    public boolean eliminarC_Persona(C_Persona persona) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
        
        String sql = "UPDATE Colaboracion SET OID=OID_Anonimo WHERE OID='"+persona.getOIDPersona()+"'";
        boolean exito = driver.insertar(sql);
        
        String sql2 = "DELETE FROM C_Persona WHERE OID='"+persona.getOIDPersona()+"'";
        boolean exito2 = driver.insertar(sql2);
        
        String sql3 = "DELETE FROM Colaborador WHERE OID='"+persona.getOIDPersona()+"'";
        boolean exito3 = driver.insertar(sql3);
        
        return (exito && exito2 && exito3);
    }
    
    /**
     * Consultar los datos de un Colaborador Persona
     * @param OIDPersona
     * @return Persona Colaborador Persona que se queria consultar
     * @throws SQLException 
     */
    public C_Persona obtenerC_Persona(String OIDPersona) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
        
        String sql = "SELECT * FROM Colaborador c, C_Persona p WHERE (p.OID='"+OIDPersona+"') AND c.OID=p.OIDColaborador";
        
        ResultSet rs = driver.seleccionar(sql);
        C_Persona Persona = null;
        
        if(rs.next()){
            Persona = new C_Persona();
                
            Persona.setDNI(rs.getString("DNI"));
            Persona.setNombre(rs.getString("Nombre"));
            Persona.setApellidos(rs.getString("Apellidos"));
            Persona.setFechaDeNacimiento(rs.getDate("FechaDeNacimiento"));
           
            char bufSexo[] = new char[1];
            try {
                rs.getCharacterStream("Sexo").read(bufSexo);
            } catch (IOException ex) {
                Logger.getLogger(C_PersonaJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            Persona.setSexo(bufSexo[0]);
            
            Persona.setCP(rs.getString("CodigoPostal"));
            Persona.setDireccion(rs.getString("Direccion"));
            Persona.setEmail(rs.getString("Email"));
            Persona.setLocalidad(rs.getString("Localidad"));
            Persona.setProvincia(rs.getString("Provincia"));
            Persona.setTelefonoFijo(rs.getString("TelefonoFijo"));
            Persona.setTelefonoMovil(rs.getString("TelefonoMovil"));
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
        String sql = "SELECT * FROM C_Persona p, Colaborador c WHERE "+tipoBusqueda+"='"+valor+"' AND p.OID=c.OID";
        
        ResultSet resultados = driver.seleccionar(sql);
        ArrayList<C_Persona> listaC_Persona = new ArrayList<C_Persona>();
        C_Persona Persona = null;
        
        if(resultados.next()){
            Persona = new C_Persona();
            Persona.setDNI(resultados.getString("DNI"));
            Persona.setNombre(resultados.getString("Nombre"));
            Persona.setApellidos(resultados.getString("Apellidos"));
            Persona.setFechaDeNacimiento(resultados.getDate("FechaDeNacimiento"));
           
            char bufSexo[] = new char[1];
            try {
                resultados.getCharacterStream("Sexo").read(bufSexo);
            } catch (IOException ex) {
                Logger.getLogger(C_PersonaJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            Persona.setSexo(bufSexo[0]);
            
            Persona.setCP(resultados.getString("CodigoPostal"));
            Persona.setDireccion(resultados.getString("Direccion"));
            Persona.setEmail(resultados.getString("Email"));
            Persona.setLocalidad(resultados.getString("Localidad"));
            Persona.setProvincia(resultados.getString("Provincia"));
            Persona.setTelefonoFijo(resultados.getString("TelefonoFijo"));
            Persona.setTelefonoMovil(resultados.getString("TelefonoMovil"));
            
            listaC_Persona.add(Persona);
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
        String sql = "INSERT INTO Socio (OIDC_Persona, OIDColaborador, usuario, contrasena) VALUES ('"+persona.getOIDPersona()+"','"+persona.getOIDColaborador()+"','"+usuarioN+"','"+contrasena+"')";
        boolean exito = driver.insertar(sql);
        
        return exito;
     
    }
    
    
    
}
