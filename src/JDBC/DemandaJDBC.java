/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import Modelo.Demanda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author raul
 */
public class DemandaJDBC {
    
    private static DemandaJDBC instancia;
    
    public DemandaJDBC getInstance(){
        if (instancia == null) 
            instancia = new DemandaJDBC();
        return instancia;
    }
    
    private DemandaJDBC(){
    
    }
    
    public boolean InsertarDemanda (Demanda demanda)throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
        boolean exito=true;
        String sql = "INSERT INTO Demanda(OID,OIDSector,OIDBeneficiario,OIDVoluntario,DescripcionVidaLaboral,Fecha) VALUES ('"+demanda.getOID()+"','"+demanda.getIdSector().getOID() +"','"+demanda.getIdBeneficiario().getOID() +"','"+demanda.getIdVoluntario().getOID()+"','"+demanda.getDescripcionValidaLaboral()+"','"+demanda.getFecha()+"')";                                                            
        
        driver.inicioTransaccion();
        try{
            driver.insertar(sql);
            driver.commit();
        }
        catch(SQLException ex){
            driver.rollback();
            throw ex;
        }  
        finally {
            driver.finTransaccion();
        }
        return exito;
    }
    public boolean EliminarDemanda (Demanda demanda) throws SQLException{
    
        boolean exito = true;
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "DELETE FROM Demanda WHERE OID = '"+demanda.getOID()+"'";
        
        driver.inicioTransaccion();
        try {
            driver.eliminar(sql);
            driver.commit();
        }
        catch (SQLException ex){
            driver.rollback();
            throw ex;
        }
        return exito;
    }
   /*
    public ArrayList<Demanda> FiltrarDemandas(String TipoBusqueda , String Valor) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
        ArrayList<Demanda> lista_demandas = new ArrayList<Demanda>();
        ResultSet resultado;
        Demanda temp;
        String sql = "SELECT * FROM Demanadas WHERE "+TipoBusqueda+" = "+Valor+"";
    
        try{
            driver.conectar();
            resultado = driver.seleccionar(sql);
            
            while (resultado.next()){
                temp = new Demanda();
                temp.setOID(resultado.getLong("OID"));
                temp.getIdSector().setOID(resultado.getInt("OIDSector"));
            }
        }
        catch (SQLException ex){
            throw ex;
        }    
            
    }
  */
   
}
