/**
 ** NOMBRE CLASE: 
 **	  CuotaJDBC.java
 **
 ** DESCRIPCION:
 **       Abstracción JDBC de Cuota
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PLB
 */
public class CuotaJDBC {
    
    /**
     * Instancia CuotaJDBC
     */
    private static CuotaJDBC instancia;
    
    /**
     * Constructor
     */
    private CuotaJDBC(){  
    }
    
    /**
     * Devuelve la instancia de la clase, sino la crea
     * @return intancia
     */
    public static CuotaJDBC getInstance(){
        if(instancia == null)
             instancia = new CuotaJDBC();
        return instancia;

    }
    
    /**
     * Añade una Cuota al sistema
     * @param c
     * @return true Si se ha podido añadir la Cuota
     * @throws SQLException 
     */
    public boolean añadirCuota(Cuota c) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "INSERT INTO Cuota (Importe, intervaloPagos, fechaInicial, fechaFin, OIDSocio) VALUES ('"+c.getCantidad()+"','"+c.getIntervaloPagos()+"','"+c.getFechaInicio()+"','"+c.getFechaFin()+"','"+c.getOIDSocio()+"')";
        boolean exito = driver.insertar(sql);
        
        return exito;
    }
    
    public  boolean modificarFechaFin(){
        
        DriverJDBC driver = DriverJDBC.getInstance();
        
        return true;
    }
    
    
    /**
     * Actualiza el último pago de una Cuota
     * @param c
     * @return true Si se ha podido actualizar el pago
     * @throws SQLException 
     */
    public boolean actualizarUltimoPago(Cuota c) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "UPDATE Cuota SET fechaUltimoPago='"+c.getFechaUltimoPago()+"'+'"+c.getIntervaloPagos()+"' WHERE OIDSocio='"+c.getOIDSocio()+"'";
        boolean exito = driver.insertar(sql);
        
        return exito;
    }
    
    /**
     * Atrasa el último pago de una Cuota
     * @param c
     * @return true Si se ha podido atrasar el último pago
     * @throws SQLException 
     */
    public boolean atrasarUltimoPago(Cuota c) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "UPDATE Cuota SET fechaUltimoPago='"+c.getFechaUltimoPago()+"'-'"+c.getIntervaloPagos()+"' WHERE OIDSocio='"+c.getOIDSocio()+"'";
        boolean exito = driver.insertar(sql);
        
        return exito;
    }
    
    /**
     * Cancela una Cuota del sistema
     * @param c
     * @return true Si se ha podido cancelar la Cuota
     * @throws SQLException 
     */
    public boolean cancelarCuota(Cuota c) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
        
        Date fechaActual = null;
        String sql = "UPDATE Cuota SET FechaFin='"+fechaActual.getTime()+" WHERE Cuota.OID='"+c.getOIDCuota()+"'";
        boolean exito = driver.insertar(sql);
        
        return exito;
    }
    
    /**
     * Eliminar una Cuota del sistema
     * @param c
     * @return true Si se ha podido eliminar la Cuota
     * @throws SQLException 
     */
    public boolean eliminarCuota(Cuota c) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
          
        String sql2 = "DELETE FROM Cuota WHERE Cuota.OID='"+c.getOIDCuota()+"'";
        boolean exito = driver.insertar(sql2);
        
        return exito;
        
    }
    
    /**
     * Comprobar todos los Impagos
     * @return listaCuotas Lista de los impagos
     * @throws SQLException 
     */
    public ArrayList<Cuota> comprobarImpagos() throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
        Date fechaActual = null;
        ArrayList<Cuota> listaCuotas = new ArrayList<Cuota>();
        
        String sql ="SELECT * FROM Cuota c, Socio s WHERE c.OIDSocio = s.OID AND c.fechaUltimoPago+c.intervaloPagos < '"+fechaActual.getTime()+"'";  
        ResultSet rs = driver.seleccionar(sql);
        
        if(rs.next()){
            Cuota cuota = new Cuota();
            cuota.setCantidad(rs.getDouble("Cantidad"));
            cuota.setFechaFin(rs.getDate("fechaFin"));
            cuota.setFechaInicial(rs.getDate("fechaInicial"));
            cuota.setFechaUltimoPago(rs.getDate("fechaUltimoPago"));
            cuota.setIntervaloPagos(rs.getDate("intervaloPagos"));
            
            listaCuotas.add(cuota);
        }
        
        return listaCuotas;
    }
    /**
     * Muestra el historial de Cuotas de un Socio
     * @param s
     * @return listaCuotas Listado de las cuotas
     * @throws SQLException 
     */
    public ArrayList<Cuota> historialCuotas(Socio s) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
        ArrayList<Cuota> listaCuotas = new ArrayList<Cuota>();
        
        String sql = "SELECT * FROM Cuota WHERE OIDSocio='"+s.getOIDSocio()+"'";
        ResultSet rs = driver.seleccionar(sql);
        
        if(rs.next()){
            Cuota cuota = new Cuota();
            cuota.setCantidad(rs.getDouble("Cantidad"));
            cuota.setFechaFin(rs.getDate("fechaFin"));
            cuota.setFechaInicial(rs.getDate("fechaInicial"));
            cuota.setFechaUltimoPago(rs.getDate("fechaUltimoPago"));
            cuota.setIntervaloPagos(rs.getDate("intervaloPagos"));
            
            listaCuotas.add(cuota);
        }
        
        return listaCuotas;
    }
    
}

