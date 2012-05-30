/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

/**
 *
 * @author Alberto
 */
import Controladores.TestDatos;
import Modelo.Sector;
import Modelo.Oferta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SectorJDBC {
    private static SectorJDBC instancia;

    private SectorJDBC(){
    }
    
    public static SectorJDBC getInstance(){
        if(instancia == null)
             instancia = new SectorJDBC();
        return instancia;
    
    }
    
    public boolean EliminarSector(Sector sector) throws SQLException{
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "DELETE FROM Sector WHERE OID = "+sector.getOID();
        try {
            driver.inicioTransaccion();
            driver.seleccionar(sql);
            driver.commit();
        }
        catch (SQLException ea){
            driver.rollback();
            throw ea;
        }
        finally{
            driver.finTransaccion();
        }
        return true;
    }
    
    public boolean InsertarSector(Sector sector) throws SQLException{
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "INSERT INTO Sector (Descripcion) VALUES ('"+sector.getDescripcion()+"')";
        
        try{
            driver.inicioTransaccion();
            driver.insertar(sql);
            driver.commit();
        }
        catch (SQLException ea){
            driver.rollback();
            throw ea;
        }
        finally{
            driver.finTransaccion();
        }
        return true;
    }
    public ArrayList<Sector> ListadoSectores () throws SQLException{
        DriverJDBC driver = DriverJDBC.getInstance();
        ArrayList<Sector> lista_sectores = new ArrayList<Sector>();
        Sector Temp;
        String sql = "SELECT * FROM Sector";
        ResultSet resultado;
        try{
        
            driver.conectar();
            resultado = driver.seleccionar(sql);
            
            while (resultado.next()){
                Temp = new Sector();
                Temp.setOID(resultado.getInt("OID"));
                Temp.setDescripcion(resultado.getString("Descripcion"));
                lista_sectores.add(Temp);
            }    
        }
        catch (SQLException ea){
            throw ea;
        }
        finally {
            driver.desconectar();
        }
        return lista_sectores;
    }
    public int ConsultarSector(String nombre) throws SQLException{
        DriverJDBC driver = DriverJDBC.getInstance();
        ResultSet resultado;
        String sql = "SELECT OID FROM descripcion = "+nombre;
        try{
            driver.conectar();
            resultado=driver.seleccionar(sql);
        }
        catch(SQLException ea){
            throw ea;
        }
        finally {
            driver.desconectar();
        }
        return resultado.getInt("OID");
    }
    
}
