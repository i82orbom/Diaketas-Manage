/**
 ** NOMBRE CLASE: 
 **	  ColaboracionJDBC.java
 **
 ** DESCRIPCION:
 **       Abstracción JDBC de Colaboración 
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

import Modelo.Colaboracion;
import Modelo.Colaborador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PLB
 */
public class ColaboracionJDBC {
    
    /**
     * Instancia ColaboracionJDBC
     */
    private static ColaboracionJDBC instancia;
    
    /**
     * Constructor
     */
    private ColaboracionJDBC(){  
    }
    
    /**
     * Devuelve una instancia de la clase, sino la devuelve
     * @return 
     */
    public static ColaboracionJDBC getInstance(){
        if(instancia == null)
             instancia = new ColaboracionJDBC();
        return instancia;

    }
    
    /**
     * Añadir una Colaboracion en el sistema
     * @param c
     * @return true Si se ha podido añadir la colaboracion
     * @throws SQLException 
     */
    public boolean añadirColaboracion(Colaboracion c) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
        
        String sql = "INSERT INTO Colaboracion (Cantidad, Fecha, DNIoCIF, OID) VALUES ('"+c.getImporte()+"','"+c.getFecha()+"','"+c.getOIDColaborador()+"','"+c.getOIDColaboracion()+"')";
        boolean exito = driver.insertar(sql);
        
        return exito;
        
    }
    
    /**
     * Elimina una Colaboracion del sistema
     * @param c
     * @return true Si se ha podido eliminar
     * @throws SQLException 
     */
    public boolean eliminarColaboracion(Colaboracion c) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
        
        String sql = "DELETE FROM Colaboracion WHERE OID= '"+c.getOIDColaboracion()+"'";
        boolean exito = driver.insertar(sql);
        
        return exito;
    }
    
    /**
     * Muestra un listado de todas las Colaboraciones en un intervalo de tiempo
     * @param c
     * @param FechaInicio
     * @param FechaFin
     * @return listaColaboraciones Listado con las Colaboraciones
     * @throws SQLException 
     */
    public ArrayList<Colaboracion> HistorialColaboraciones(Colaborador c, Date FechaInicio, Date FechaFin) throws SQLException{
     
        DriverJDBC driver = DriverJDBC.getInstance();
        
        String sql = "SELECT * FROM Colaboracion c, Movimiento m WHERE c.OIDColaborador='"+c.getOIDColaborador()+"' AND m.Fecha>='"+FechaInicio+"' AND m.Fecha<='"+FechaFin+"'";
        ResultSet rs = driver.seleccionar(sql);
        ArrayList<Colaboracion> listaColaboraciones = new ArrayList<Colaboracion>();
        Colaboracion colaboracion = null;
        
        if(rs.next()){
            colaboracion = new Colaboracion();
            colaboracion.setFecha(rs.getDate("Fecha"));
            colaboracion.setImporte(rs.getInt("Importe"));
            colaboracion.setConcepto(rs.getString("Concepto"));
            
            listaColaboraciones.add(colaboracion);
        }
        return listaColaboraciones;
    }
    
    
}
