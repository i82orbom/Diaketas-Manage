/**
 ** NOMBRE CLASE: 
 **	  AsociacionJDBC.java
 **
 ** DESCRIPCION:
 **       Abstracción JDBC de Asociación
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
 **
 ** NOTAS:
 **   
 **
 */
package JDBC;

import Modelo.Asociacion;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jobero
 */
public class AsociacionJDBC {
    
    private static AsociacionJDBC instancia;
    
    private AsociacionJDBC(){   
    }
    
    public static AsociacionJDBC getInstance(){

         if(instancia == null)
              instancia = new AsociacionJDBC();
         return instancia;

    }
    
    public Asociacion obtenerDatosAsociacion() throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance() ;
           
        String sql = "SELECT * FROM asociacion ";
            
        ResultSet resultado = driver.seleccionar(sql);
        Asociacion asociacion = null ;
           
        if(resultado.next()){
            asociacion = new Asociacion();
            asociacion.setNombre(resultado.getString("Nombre"));
            asociacion.setDireccion(resultado.getString("Direccion"));
        }
               
        return asociacion;
        
        
    }
    
    
    
}
