/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import Modelo.Demanda;
import Modelo.Beneficiario;
import Modelo.Voluntario;
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
        finally {
            driver.finTransaccion();
        }
        return exito;
    }
   
    public ArrayList<Demanda> FiltrarDemandas(String TipoBusqueda , String Valor) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();
        BeneficiarioJDBC bene = BeneficiarioJDBC.getInstance();
        VoluntarioJDBC volun = VoluntarioJDBC.getInstance();
        ArrayList<Demanda> lista_demandas = new ArrayList<Demanda>();
        ResultSet resultado, resultado_1;
        Demanda temp;
        Beneficiario bene_aux;
        Voluntario volun_aux;
        String sql = "SELECT * FROM Demanadas WHERE "+TipoBusqueda+" = "+Valor+"";
    
        try{
            driver.conectar();
            resultado = driver.seleccionar(sql);
          
            while (resultado.next()){
                temp = new Demanda();
                temp.setOID(resultado.getLong("OID"));
                /*
                temp.getIdSector().setOID(resultado.getInt("OIDSector"));
                temp.getIdVoluntario().setOID(resultado.getLong("OIDVoluntario"));
                temp.getIdBeneficiario().setOID(resultado.getLong("OIDBeneficiario"));
                */
                temp.setDescripcionValidaLaboral(resultado.getString("DescripcionVidaLaboral"));
                temp.setFecha(resultado.getDate("Fecha"));
                
                /*____ Obtenemos el sector ____*/
                String sql1 = "SELECT * FROM Sector WHERE OID = '"+resultado.getInt("OIDSector")+"'"; 
                resultado_1=driver.seleccionar(sql1);
                temp.getIdSector().setDescripcion(resultado_1.getString("Descripcion"));
                
                /*____ Obtenemos el beneficiario ____*/
                sql1 = "SELECT NIF FROM Persona WHERE OID = '"+resultado.getInt("OIDBeneficiario") +"'";
                resultado_1 = driver.seleccionar(sql1);
                bene_aux = bene.obtenerBeneficiario(resultado_1.getString("NIF"));
                temp.setIdBeneficiario(bene_aux);
                
                /*____ Obtenemos el voluntario ____*/
                sql1 = "SELECT NIF FROM Persona WHERE OID = '"+resultado.getInt("OIDVoluntario") +"'";
                resultado_1 = driver.seleccionar(sql1);
                volun_aux = volun.obtenerVoluntario(resultado_1.getString("NIF"));
                temp.setIdVoluntario(volun_aux);
                
                /*____ Añadimos la demanda al arraylist ____*/
                
                lista_demandas.add(temp);
            }
            return lista_demandas; 
        }
        catch (SQLException ex){
            throw ex;
        }  
        finally {
            driver.desconectar();
        }
           
    }
    
    public boolean ActualizarDemanda (Demanda demanda) throws SQLException {
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql1 = "UPDATE Demanda SET OIDSector = "+demanda.getIdSector().getOID()+",OIDVoluntario = "+demanda.getIdVoluntario().getOID()+",DescripcionVidaLaboral = '"+demanda.getDescripcionValidaLaboral()+"',Fecha= '"+demanda.getFecha()+"'";
        
        try {
            driver.conectar();
            driver.actualizar(sql1);
            
        }
        catch (SQLException ex){
            throw ex;
        }
        finally {
            driver.desconectar();
        }
        return true;
    }
   
}
