/**
 ** NOMBRE CLASE: 
 **	  DriverJDBC.java
 **
 ** DESCRIPCION:
 **       Driver de comunicación con JDBC
 **       
 **
 ** DESARROLLADO POR:
 *        Francisco José Beltrán Rodriguez (FBR)
 *	   
 **        
 ** SUPERVISADO POR:
 **        Adolfo Arcoya Nieto (AAN)  
 **
 ** HISTORIA:
 ** 	000 - Mar 24, 2012 - FBR - Creacion
 **     001 - Mar 25, 2012 - FBR - Implementación de los métodos
 *      002 - Mar 26, 2012 - FBR - Modificación de diversos métodos
 **     003 - Mar 26, 2012 - FBR - Modificación del constructor de la clase, getInstance ahora es public
 *      
 **
 ** NOTAS:
 **   
 **
 */
package JDBC;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jobero
 */
public class DriverJDBC {
    
    
    private Connection conex;
    private String hostBD;
    private static DriverJDBC instancia;
    private String nombreBD;
    private String password;
    private String usuarioBD;
    private Statement statement;
  
    
    
    
    //Falta mirar bien los close!!
    
    private DriverJDBC(){
        
    }
    
    //Antes de conectar se debera haber configurado
    public boolean conectar(){
        
   
        //Siempre usaremos mysql
       try {
            Class.forName("com.mysql.jdbc.Driver");
            conex = DriverManager.getConnection("jdbc:mysql://"+hostBD+"/"+nombreBD , usuarioBD, password);
            
            statement = conex.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        } catch (ClassNotFoundException ex1){
            System.out.println(ex1.toString());
            return false;
        }
       
        return true;
    }
    
    public boolean desconectar(){
        try {
            conex.close();
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }
    
    public void configurar(String hostBD, String nombreBD, String password, String usuarioBD){
        
        this.hostBD=hostBD;
        this.nombreBD=nombreBD;
        this.password=password;
        this.usuarioBD=usuarioBD;
        
    }
    
    
    public boolean actualizar(String sentencia) throws SQLException{
        
        /*
        tabla = statement.executeQuery(sentencia_busqueda);
	//Para que fuera mas correcto deberíamos comprobar si rs.next devuelve algo (a lo mejor nadie se llama Juan)
	if( tabla.next() == false)
            return false;
	*/
        
	//statement.executeUpdate("UPDATE socio SET telefono='111' WHERE id="+id);
        statement.executeUpdate(sentencia);
        
        return true;
    }
    
    public boolean eliminar(String sentencia) throws SQLException{
        
        boolean exito= actualizar(sentencia);
        
        return exito;
    }
    
    public static DriverJDBC getInstance(){
        
        if(instancia == null)
		instancia = new DriverJDBC();
	return instancia;
    }
    
    public boolean insertar(String sentencia) throws SQLException{
         statement.executeUpdate(sentencia);
         
        return true;
    }
    
    public ResultSet seleccionar (String cadena) throws SQLException{
        
        return statement.executeQuery(cadena);
    }
    
    
}

