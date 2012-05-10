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
        String usuario = socio.getUsuario();
        String contrasena = socio.getContrasena();
        
        String sql = "INSERT INTO Colaborador (Direccion, Localidad, Provincia, codigoPostal, TelefonoFijo, TelefonoMovil, Email) VALUES ('"+socio.getDireccion()+"','"+socio.getLocalidad()+"','"+socio.getProvincia()+"','"+socio.getCP()+"','"+socio.getTelefonoFijo()+"','"+socio.getTelefonoMovil()+"','"+socio.getEmail()+"')";
        boolean exito = driver.insertar(sql);
        String sql2 = "INSERT INTO C_Persona (DNI, Nombre, Apellidos, FechaDeNacimiento) VALUES ('"+socio.getDNI()+"','"+socio.getNombre()+"','"+socio.getApellidos()+"','"+socio.getFechaDeNacimiento()+"','"+socio.getSexo()+"')";
        boolean exito2 = driver.insertar(sql2);
        String sql3 = "INSERT INTO Socio (usuario, contrasena) VALUES ('"+usuario+"','"+contrasena+"')"; 
        boolean exito3 = driver.insertar(sql3);
        
        return (exito && exito2 && exito3);
    }
    
    /**
     * Modifica los datos de un Socio del sistema
     * @param socio
     * @return true Si se han modificado correctamente los datos del Socio
     * @throws SQLException 
     */
    public boolean modificarDatosSocio(Socio socio) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
        String usuario = socio.getUsuario();
        String contrasena = socio.getContrasena();
        
        String sql = "UPDATE Colaborador SET Direccion='"+socio.getDireccion()+"', Localidad='"+socio.getLocalidad()+"', Provincia='"+socio.getProvincia()+"', codigoPostal='"+socio.getCP()+"',TelefonoFijo='"+socio.getTelefonoFijo()+"', TelefonoMovil='"+socio.getTelefonoMovil()+"', Email='"+socio.getEmail()+"WHERE OID="+socio.getOIDColaborador()+"'";
        boolean exito = driver.insertar(sql);
        String sql2 = "UPDATE C_Persona SET DNI='"+socio.getDNI()+"', Nombre='"+socio.getNombre()+"', Apellidos='"+socio.getApellidos()+"', FechaDeNacimiento='"+socio.getFechaDeNacimiento()+"WHERE OID="+socio.getOIDPersona()+"'";
        boolean exito2 = driver.insertar(sql2);
        String sql3 = "UPDATE Socio SET usuario='"+usuario+"', contrasena='"+contrasena+"WHERE OID="+socio.getOIDSocio()+"'";
        boolean exito3 = driver.insertar(sql3);
        
        return (exito && exito2 && exito3);
    }
    
    /**
     * Elimina a un Socio del sistema
     * @param socio
     * @return true Si se ha podido eliminar al Socio
     * @throws SQLException 
     */
    public boolean eliminarSocio(Socio socio) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
        
        String sql = "UPDATE Colaboracion SET OID=OID_Anonimo WHERE OID='"+socio.getOIDSocio()+"'";
        boolean exito = driver.insertar(sql);
        
        String sql2 = "UPDATE PagoCuota SET OID=OID_Anonimo WHERE OIDSocio='"+socio.getOIDSocio()+"'";
        boolean exito2 = driver.insertar(sql2);
        
        String sql3 = "UPDATE Cuota SET fechaFin=fechaUltimoPago WHERE OIDSocio='"+socio.getOIDSocio()+"'";
        boolean exito3 = driver.insertar(sql3);
        
        String sql4 = "UPDATE Cuota SET OIDSocio=OID_Anonimo WHERE OIDSocio='"+socio.getOIDSocio()+"'";
        boolean exito4 = driver.insertar(sql4);
        
        String sql5 = "DELETE FROM Socio WHERE OIDSocio='"+socio.getOIDSocio()+"'";
        boolean exito5 = driver.insertar(sql5);
        
        String sql6 = "DELETE FROM C_Persona WHERE OID='"+socio.getOIDPersona()+"'";
        boolean exito6 = driver.insertar(sql6);
        
        String sql7 = "DELETE FROM Colaborador WHERE OID='"+socio.getOIDColaborador()+"'";
        boolean exito7 = driver.insertar(sql7);
        
        return (exito && exito2 && exito3 && exito4 && exito5 && exito6 && exito7);
    }
    
    /**
     * Consultar los datos de un Socio
     * @param DNI
     * @return socio El socio que se buscaba
     * @throws SQLException 
     */
    public Socio obtenerSocio(String DNI) throws SQLException{
      
        DriverJDBC driver = DriverJDBC.getInstance();
        
        String sql = "SELECT * FROM Colaborador c, C_Persona p, Socio s WHERE (p.DNI='"+DNI+"') AND c.OID=p.OIDColaborador AND s.OIDC_Persona=p.OID";
        
        ResultSet rs = driver.seleccionar(sql);
        Socio socio = null;
        
        if(rs.next()){
            socio = new Socio();
            socio.setUsuario(rs.getString("usuario"));
            socio.setContrasena(rs.getString("contrasena"));
                
            socio.setDNI(rs.getString("DNI"));
            socio.setNombre(rs.getString("Nombre"));
            socio.setApellidos(rs.getString("Apellidos"));
            socio.setFechaDeNacimiento(rs.getDate("FechaDeNacimiento"));
           
            char bufSexo[] = new char[1];
            try {
                rs.getCharacterStream("Sexo").read(bufSexo);
            } catch (IOException ex) {
                Logger.getLogger(SocioJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            socio.setSexo(bufSexo[0]);
            
            socio.setCP(rs.getString("CodigoPostal"));
            socio.setDireccion(rs.getString("Direccion"));
            socio.setEmail(rs.getString("Email"));
            socio.setLocalidad(rs.getString("Localidad"));
            socio.setProvincia(rs.getString("Provincia"));
            socio.setTelefonoFijo(rs.getString("TelefonoFijo"));
            socio.setTelefonoMovil(rs.getString("TelefonoMovil"));
            }
               
            return socio;
        
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
        String sql = "SELECT * FROM Socio s, C_Persona p WHERE "+tipoBusqueda+"='"+valor+"' AND s.OIDC_Persona=p.OID";
        
        ResultSet rs = driver.seleccionar(sql);
        ArrayList<Socio> listaSocios = new ArrayList<Socio>();
        Socio socio = null;
        
        if(rs.next()){
            socio = new Socio();
            socio.setUsuario(rs.getString("usuario"));
            socio.setContrasena(rs.getString("contrasena"));
            
            socio.setDNI(rs.getString("DNI"));
            socio.setNombre(rs.getString("Nombre"));
            socio.setApellidos(rs.getString("Apellidos"));
            socio.setFechaDeNacimiento(rs.getDate("FechaDeNacimiento"));
           
            char bufSexo[] = new char[1];
            try {
                rs.getCharacterStream("Sexo").read(bufSexo);
            } catch (IOException ex) {
                Logger.getLogger(SocioJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            socio.setSexo(bufSexo[0]);
            
            socio.setCP(rs.getString("CodigoPostal"));
            socio.setDireccion(rs.getString("Direccion"));
            socio.setEmail(rs.getString("Email"));
            socio.setLocalidad(rs.getString("Localidad"));
            socio.setProvincia(rs.getString("Provincia"));
            socio.setTelefonoFijo(rs.getString("TelefonoFijo"));
            socio.setTelefonoMovil(rs.getString("TelefonoMovil"));
            
            listaSocios.add(socio);
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
        Date fechaActual = null;
        Cuota c = new Cuota();
                
        String sql = "SELECT * FROM Cuota c WHERE c.OIDSocio='"+socio.getOIDSocio()+"' AND (c.FechaFin > '"+fechaActual.getTime()+"' OR c.FechaFin <> NULL)";
        ResultSet rs = driver.seleccionar(sql);
        
        if(rs.next()){
            c.setCantidad(rs.getDouble("Cantidad"));
            c.setFechaFin(rs.getDate("fechaFin"));
            c.setFechaInicial(rs.getDate("fechaInicial"));
            c.setFechaUltimoPago(rs.getDate("fechaUltimoPago"));
            c.setIntervaloPagos(rs.getDate("intervaloPagos"));
        }
        return c;
    }

}
