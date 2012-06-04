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
import Modelo.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class OfertaJDBC {
    private static OfertaJDBC instancia;

    private OfertaJDBC(){
    }

    public static OfertaJDBC getInstance(){
        if(instancia == null)
             instancia = new OfertaJDBC();
        return instancia;

    }

    public boolean insertarOferta (Oferta oferta)throws SQLException{
        boolean exito = true;
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql1 = "INSERT INTO Oferta (cualificacionRequerida, descripcionOferta ,duracionContrato, fecha, plazasOfertadas, tipoContrato, oidSector, oidEmpresa, oidVoluntario) VALUES ('"
				+oferta.getCualificacionRequerida()+"','"+oferta.getDescripcionOferta()+"','"+oferta.getDuracionContrato()+"','"+TestDatos.formatterBD.format(oferta.getFecha())+"','"+oferta.getPlazasOfertadas()+"','"+oferta.getTipoContrato()+"','"+oferta.getSector().getOID()+"','"+oferta.getEmpresa().getOID()+"','"+oferta.getVoluntario().getOID()+"')";

        try{
			driver.inicioTransaccion();
			driver.insertar(sql1);
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

    public boolean eliminarOferta (Oferta oferta) throws SQLException{
        boolean exito = true;
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql1 = "DELETE  FROM oferta WHERE OID = '"+oferta.getOID()+"'";

        try{
			driver.inicioTransaccion();
			driver.eliminar(sql1);
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

    public boolean obtenerOferta (String oferta)throws SQLException{
        boolean exito = true;
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql1 = " SELECT * FROM Oferta WHERE OID = '"+oferta+"'";

        try{
			driver.inicioTransaccion();
			driver.seleccionar(sql1);
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

	public ArrayList<Oferta> filtrarOfertas (Long empresa, Long sector, String antiguedad)throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance();
		Calendar fecha = Calendar.getInstance();
                ArrayList<Oferta> Lista_ofertas = new ArrayList<Oferta>();
                Oferta temp;
                Sector temp2;
                C_Empresa temp3;
                System.out.println(empresa);
                System.out.println("sector"+sector);
                int meses = Integer.parseInt(antiguedad);
                meses = meses * -1;
                fecha.add(Calendar.MONTH, meses);
                
		String sql = "SELECT * FROM Oferta o, C_Empresa c, Sector s WHERE o.OIDEmpresa=c.OID AND o.OIDSector=s.OID";
                if (empresa>0) sql.concat(" AND o.OIDEmpresa = "+empresa);
		if (sector>0){ sql.concat(" AND o.OIDSector = "+sector+" AND s.OID = "+sector);}
                if (meses>0) sql.concat(" AND o.Fecha <= "+fecha);
		try{

			driver.conectar();
			ResultSet resultados, resultado_1;
                        resultados = driver.seleccionar(sql);
                        temp2 = new Sector();
                        temp3 = new C_Empresa();
                        temp = new Oferta();
                               

			while(resultados.next()){
                            
                                /* obtener datos oferta*/
                               
				temp = new Oferta();
                                
				temp.setOID(resultados.getLong("OID"));
				temp.setCualificacionRequerida(resultados.getString("CualificacionRequerida"));
				temp.setDescripcionOferta(resultados.getString("DescripcionOferta"));
				temp.setDuracionContrato(resultados.getInt("DuracionContrato"));
				temp.setFecha(resultados.getDate("Fecha"));
				temp.setPlazasOfertadas(resultados.getInt("PlazasOfertadas"));
				temp.setTipoContrato(resultados.getString("TipoContrato"));
                                temp2.setDescripcion(resultados.getString("Descripcion"));
                                temp3.setNombre(resultados.getString("Nombre"));
                                temp.setSector(temp2);
                                temp.setEmpresa(temp3);
                                System.out.print("sector 2"+resultados.getString("OIDSector"));
				Lista_ofertas.add(temp);
                         
			}
		}
		catch (SQLException ex){
			throw ex;
		}
		finally {
			driver.desconectar();
		}



		return Lista_ofertas;
	}

	public boolean ActualizarOferta (Oferta oferta) throws SQLException{
		DriverJDBC driver = DriverJDBC.getInstance();
		String sql = "UPDATE Oferta SET OID='"+oferta.getOID()+"',CualificacionRequerida='"+oferta.getCualificacionRequerida()+"',DescripcionOferta='"+oferta.getDescripcionOferta()+"',DuracionContrato='"+oferta.getDuracionContrato()+"',Fecha='"+oferta.getFecha()+"',PlazasOfertadas='"+oferta.getPlazasOfertadas()+"',TipoContrato='"+oferta.getTipoContrato()+"',OIDSector='"+oferta.getSector().getOID()+"',OIDEmpresa='"+oferta.getEmpresa().getOID()+"',OIDVoluntario='"+oferta.getVoluntario().getOID()+"'";

		try {
			driver.conectar();
			driver.actualizar(sql);
        }
        catch (SQLException ex){
            throw ex;
        }
        finally {
            driver.desconectar();
        }

        return true;
	}
        
        public boolean ConsultarOfertaSector (Long oid) throws SQLException {
    
        DriverJDBC driver = DriverJDBC.getInstance();
        String sql = "SELECT * FROM Oferta WHERE OIDSector ="+oid;
        ResultSet resultado;
        boolean exito;
        
        try{
            driver.conectar();
            resultado=driver.seleccionar(sql);
            if(resultado.next())
                exito=true;
            else
                exito = false;
        }
        catch( SQLException ea){
            throw(ea);
        }
        finally{
            driver.desconectar();
        }
        return exito;
    }
   
}