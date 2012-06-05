/**
 ** /**
 ** NOMBRE CLASE: 
 **	  MovimientoJDBC.java
 **
 ** DESCRIPCION:
 **       AbstracciÃ³n JDBC de Movimiento
 **       
 **
 ** DESARROLLADO POR:
 *        Francisco JosÃ© BeltrÃ¡n Rodriguez (FBR)
 *	   
 **        
 ** SUPERVISADO POR:
 **       Adolfo Arcoya Nieto (AAN)  
 **
 ** HISTORIA:
 ** 	000 - Mar 24, 2012 - FBR - Creacion e implementaciÃ³n de los  mÃ©todos
 **     001 - Mar 25, 2012 - FBR - Implementacion de los metodos
 *      002 - Mar 26, 2012 - FBR - Implementacion de los mÃ©todos 
 **     003 - Jun 01, 2012 - FBR - ImplementaciÃ³n de las transacciones y revision de los mÃ©todos de registrar
 *      004 - Jun 04, 2012 - FBR - Cambios en los métodos
 *      005 - Jun 05, 2012 - RC - Correciones sentencias sql 
 **
 ** NOTAS:
 **   
 **
 */
package JDBC;

import Controladores.TestDatos;
import Modelo.Ayuda;
import Modelo.Movimiento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jobero
 */
public class MovimientoJDBC {
    
     private static MovimientoJDBC instancia;
    
     private MovimientoJDBC(){
        
        
     }
     
     public static MovimientoJDBC getInstance(){

            if(instancia == null)
                    instancia = new MovimientoJDBC();
            return instancia;

     }
     
     public float obtenerBalance(ArrayList<Movimiento> ingresos, ArrayList<Movimiento> gastos){
         
          float ingresosSuma = 0;
          float gastosSuma = 0;
                  
          if (ingresos != null){
            for (int i = 0; i < ingresos.size(); ++i){
                   ingresosSuma += ingresos.get(i).getImporte();
             }
          
          }
          
          if (gastos != null){
            for (int i = 0; i < gastos.size(); ++i){
                gastosSuma += gastos.get(i).getImporte();
             }
           
            gastosSuma *= -1;
          }
          
         return (ingresosSuma + gastosSuma);
     }
     
     public ArrayList<Movimiento> obtenerDatosGastos(Date fecha_inicial, Date fecha_final) throws SQLException{
         
        ArrayList<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
        
        DriverJDBC driver = DriverJDBC.getInstance();
        ResultSet rs;
        
        String consulta = "SELECT m.OID, Cantidad, Concepto, Fecha FROM Movimiento m join Gasto on m.OID= Gasto.OID WHERE Fecha BETWEEN '" + TestDatos.formatterBD.format(fecha_inicial) + "' AND '" + TestDatos.formatterBD.format(fecha_final) + "'";
        
        try{
            driver.conectar();
            rs = driver.seleccionar(consulta);
            
            while(rs.next()){
                Movimiento m = new Movimiento();
                
                m.setConcepto(rs.getString("Concepto"));
                m.setFecha(rs.getDate("Fecha"));
                m.setImporte(rs.getFloat("Cantidad"));
                m.setOID(rs.getLong("OID"));
                
                // Un movimiento tiene ayudas asociadas, que en este caso habría que añadirlas, pero no nos interesa para hacer los gastos
                // Habría que cojer el ID de este movimiento y consultar en ayudas para rellenar el array que tiene cada movimiento
                
                listaMovimientos.add(m);
            }
            
        }
        catch (SQLException e){
            Logger.getLogger(MovimientoJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
        driver.desconectar();
        return listaMovimientos;         
     }
     
    public ArrayList<Movimiento> obtenerDatosIngresos(Date fecha_inicial, Date fecha_final) throws SQLException{
         
          
        ArrayList<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
        
        DriverJDBC driver = DriverJDBC.getInstance();
        ResultSet rs;
        
        String consulta = "SELECT m.OID, Cantidad, Concepto, Fecha FROM Movimiento m join PagoCuota on m.OID= PagoCuota.OID WHERE Fecha BETWEEN '" + TestDatos.formatterBD.format(fecha_inicial) + "' AND '" + TestDatos.formatterBD.format(fecha_final) + "'";

        try{
            driver.conectar();
            rs = driver.seleccionar(consulta);
            
            while(rs.next()){
                Movimiento m = new Movimiento();
                
                m.setConcepto(rs.getString("Concepto"));
                m.setFecha(rs.getDate("Fecha"));
                m.setImporte(rs.getFloat("Cantidad"));
                m.setOID(rs.getLong("OID"));
                
                // En este caso no tiene ayudas asociadas el movimiento ya que es un ingreso
                listaMovimientos.add(m);
            }
            
            consulta = "SELECT m.OID, Cantidad, Concepto, Fecha FROM Movimiento m join Colaboracion on m.OID= Colaboracion.OID WHERE Fecha BETWEEN '" + TestDatos.formatterBD.format(fecha_inicial) + "' AND '" + TestDatos.formatterBD.format(fecha_final) + "'";
            rs = driver.seleccionar(consulta);
            while(rs.next()){
                Movimiento m = new Movimiento();
                
                m.setConcepto(rs.getString("Concepto"));
                m.setFecha(rs.getDate("Fecha"));
                m.setImporte(rs.getFloat("Cantidad"));
                m.setOID(rs.getLong("OID"));
                
                // En este caso no tiene ayudas asociadas el movimiento ya que es un ingreso            
                listaMovimientos.add(m);
            }
        }
        catch (SQLException e){
            Logger.getLogger(MovimientoJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
        
        driver.desconectar();
        return listaMovimientos;
    }
     
    
     
     public boolean registrarDatosGastoAyuda(Movimiento movimiento, Ayuda ayuda) throws SQLException{
         
         DriverJDBC driver = DriverJDBC.getInstance() ;
         String sentencia ;
        
         boolean exito;
         
         ResultSet rs;
        
         sentencia = "INSERT INTO movimiento (Cantidad, Concepto, Fecha) VALUES ('"+movimiento.getImporte()+"','"+movimiento.getConcepto()+"','"+TestDatos.formatterBD.format(movimiento.getFecha())+"' )";
         
         try {
            driver.inicioTransaccion();
            exito = driver.insertar(sentencia); 
            driver.commit();
        } catch (SQLException ex) {
            driver.rollback();
            exito = false;
            throw ex;
        } finally {
            driver.finTransaccion();
        }
            
         movimiento.setOID(MovimientoJDBC.getInstance().getOIDUltimoMovimiento());
         sentencia = "INSERT INTO Gasto (OID, OIDAyuda) VALUES ('" + movimiento.getOID() + "','" + ayuda.getOID() + "')";
          try {
            driver.inicioTransaccion();
            exito = driver.insertar(sentencia); 
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
     
     public long getOIDUltimoMovimiento() throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();

        String sentencia = "SELECT MAX(OID) FROM Movimiento";
        long oid_resultado = -1;    
        try{
        driver.conectar();
        ResultSet resultado = driver.seleccionar(sentencia);
        
        while (resultado.next()) {
            oid_resultado = resultado.getLong(1);
        }
        
        driver.desconectar();
        }
        catch (SQLException e){
            return -1;
        }
        
        return oid_resultado;
    }
            
}
