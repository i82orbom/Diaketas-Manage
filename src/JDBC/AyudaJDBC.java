/**
 ** NOMBRE CLASE: 
 **	  AyudaJDBC.java
 **
 ** DESCRIPCION:
 **       Abstracción JDBC de Ayuda
 **       
 **
 ** DESARROLLADO POR:
 *        Francisco José Beltrán Rodriguez (FBR)
 *          Raphael Colleau (RC)
 *	   
 **        
 ** SUPERVISADO POR:
 **       Adolfo Arcoya Nieto (AAN)
 **
 ** HISTORIA:
 ** 	000 - Mar 24, 2012 - FBR - Creacion 
 **     001 - Mar 26, 2012 - FBR - Implementacion de los metodos
 **     002 - Mar 29, 2012 - AAN - Correccion consultas SQL
 *      003 - Abr 15, 2012 - FBR - Corrección consultas SQL
 *      004 - Mayo 27, 2012 - RC - Corrección consultas SQL
 *      005 - Mayo 29, 2012 - RC - Corrección consultas SQL registrar datos ayuda
 *      006 - Mayo 30, 2012 - RC - Corrección consultas SQL modificar datos ayuda
 *      007 - Jun 4, 2012 - RC - cambio consultas SQL buscarAyudas
 *      
 **
 ** NOTAS:
 **   
 **
 */


package JDBC;

import Controladores.TestDatos;
import Modelo.Ayuda;
import Modelo.Beneficiario;
import Modelo.TipoAyuda;
import Modelo.Voluntario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class AyudaJDBC {

    private static AyudaJDBC instancia;

    private AyudaJDBC() {
    }

    public static AyudaJDBC getInstance() {

        if (instancia == null) {
            instancia = new AyudaJDBC();
        }
        return instancia;

    }

    //Mirar
    public ArrayList<Ayuda> buscarAyudas(Date fechaIni, Date fechaFin, float importeIni, float importeFin, String tipoAyuda) throws SQLException {

        ArrayList<Ayuda> lista_ayudas = new ArrayList<Ayuda>();
        DriverJDBC driver = DriverJDBC.getInstance();

        String sentencia = "SELECT * FROM ayuda a, tipoayuda t WHERE a.Fecha>='" + TestDatos.formatterBD.format(fechaIni) + "' AND a.Fecha<='" + TestDatos.formatterBD.format(fechaFin) + "' AND a.Importe>=" + importeIni + " AND a.Importe<=" + importeFin + " AND t.OID = a.TipoAyudaOID AND t.Titulo = '" + tipoAyuda + "'";

        driver.conectar();
        ResultSet resultado = driver.seleccionar(sentencia);
        Ayuda temp;
        while (resultado.next()) {
            temp = new Ayuda();

            temp.setOID(resultado.getLong("OID"));
            temp.setFecha(resultado.getDate("Fecha"));
            temp.setObservaciones(resultado.getString("Observaciones"));
            temp.setImporte(resultado.getFloat("Importe"));
            temp.setBeneficiarioDeAyuda(BeneficiarioJDBC.getInstance().obtenerBeneficiario(resultado.getLong("OID_Bene")));
            lista_ayudas.add(temp);
        }
        
        driver.desconectar();
        return lista_ayudas;
    }
    
    public long getOIDUltimaAyuda() throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance();

        String sentencia = "SELECT MAX(OID) FROM Ayuda";

        driver.conectar();
        ResultSet resultado = driver.seleccionar(sentencia);
        long oid_resultado = -1;
        while (resultado.next()) {
            oid_resultado = resultado.getLong(1);
        }
        
        driver.desconectar();
    
        return oid_resultado;
    }

    public boolean comprobarTipoAyuda(String titulo) throws SQLException {

        DriverJDBC driver = DriverJDBC.getInstance();

        String sentencia = "SELECT Titulo FROM tipoayuda WHERE Titulo='" + titulo + "' LIMIT 1";
        driver.conectar();
        ResultSet resultados = driver.seleccionar(sentencia);


        if (resultados.next()) {
            driver.desconectar();
            return true;
        } else {
            driver.desconectar();
            return false;
        }


    }

    public Ayuda obtenerAyuda(TipoAyuda tipoAyuda, long oidBenef, Date fecha) throws SQLException {

        DriverJDBC driver = DriverJDBC.getInstance();

        Ayuda ayuda = null;
        String sql = "SELECT * FROM ayuda WHERE Fecha='" + TestDatos.formatterBD.format(fecha) + "' AND OID_Bene='" + oidBenef + "' AND TipoAyudaOID='" + tipoAyuda.getOID() + "' LIMIT 1";
        driver.conectar();
        ResultSet resultados = driver.seleccionar(sql);
        
        while (resultados.next()) {
            ayuda = new Ayuda();
            ayuda.setOID(resultados.getLong("OID"));
            ayuda.setFecha(resultados.getDate("Fecha"));
            ayuda.setImporte(resultados.getFloat("Importe"));
            ayuda.setObservaciones("Observaciones");

        }
		driver.desconectar();
        return ayuda;
    }
	
	public Ayuda obtenerAyuda(Long oid) throws SQLException {

        DriverJDBC driver = DriverJDBC.getInstance();

        Ayuda ayuda = null;
        String sql = "SELECT * FROM ayuda WHERE OID='" + oid;
        driver.conectar();
        ResultSet resultados = driver.seleccionar(sql);
        driver.desconectar();

        while (resultados.next()) {
            ayuda = new Ayuda();
            ayuda.setOID(resultados.getLong("OID"));
            ayuda.setFecha(resultados.getDate("Fecha"));
            ayuda.setImporte(resultados.getFloat("Importe"));
            ayuda.setObservaciones("Observaciones");

        }
        return ayuda;
    }

    public ArrayList<TipoAyuda> obtenerDatosTipoAyuda() throws SQLException {

        DriverJDBC driver = DriverJDBC.getInstance();
        driver.conectar();
        ArrayList<TipoAyuda> tiposAyuda = new ArrayList<TipoAyuda>();

        String sql = "SELECT * FROM tipoayuda";
        ResultSet resultados = driver.seleccionar(sql);
        TipoAyuda temp;

        while (resultados.next()) {
            temp = new TipoAyuda();
            temp.setOID(resultados.getString("OID"));
            temp.setDescripcion(resultados.getString("Descripcion"));
            temp.setTitulo(resultados.getString("Titulo"));
            if ("1".equals(resultados.getString("Monetaria"))) {
                temp.setMonetaria(true);
            } else {
                temp.setMonetaria(false);
            }

            tiposAyuda.add(temp);

        }
        resultados.close();
        driver.desconectar();
        return tiposAyuda;
    }

    public boolean insertarTipoAyuda(TipoAyuda t) throws SQLException {

        DriverJDBC driver = DriverJDBC.getInstance();
        char monetaria;
        if (t.isMonetaria()) {
            monetaria = '1';
        } else {
            monetaria = '0';
        }

        String sql = "INSERT INTO tipoayuda (Descripcion, Monetaria,Titulo) VALUES ('" + t.getDescripcion() + "','" + monetaria + "','" + t.getTitulo() + "')";
        driver.conectar();
        boolean exito = driver.insertar(sql);
        driver.desconectar();
        return exito;

    }

    public boolean eliminarDatosAyuda(long ayudaOID) throws SQLException {

        DriverJDBC driver = DriverJDBC.getInstance();
        driver.conectar();
        String sql = "DELETE FROM modificacionayuda WHERE AyudaOID ='" + ayudaOID + "'";
        boolean exito = driver.eliminar(sql);

        if (exito) {
            String sql2 = "DELETE FROM Gasto WHERE OIDAyuda='" + ayudaOID + "'";
            exito = driver.eliminar(sql2);
			if(exito){
				String sql3 = "DELETE FROM ayuda WHERE OID='" + ayudaOID + "'";
				exito = driver.eliminar(sql3);
			}
        }
        driver.desconectar();
        return exito;
    }

    public boolean eliminarDatosTipoAyuda(String tipoayudaOID) throws SQLException {

        DriverJDBC driver = DriverJDBC.getInstance();
        driver.conectar();
        String sql = "DELETE FROM tipoayuda WHERE OID ='" + tipoayudaOID + "'";
        boolean exito = driver.eliminar(sql);
        driver.desconectar();
        return exito;

    }

    public boolean modificarDatosAyuda(Ayuda temp, Voluntario voluntario) throws SQLException {

        DriverJDBC driver = DriverJDBC.getInstance();
        Float importe = new Float(temp.getImporte());

        String sql = "UPDATE ayuda SET Importe='" + importe.toString() + "',TipoAyudaOID='" + temp.getTipo_ayuda().getOID() + "',Observaciones='" + temp.getObservaciones() + "' WHERE OID='" + temp.getOID() + "'";
        driver.conectar();
        boolean exito = driver.actualizar(sql);
        Date fecha_actual = new Date();


        if (exito) {
            String sql2 = "INSERT INTO modificacionayuda (AyudaOID,OID_Volun,Fecha) VALUES ('" + temp.getOID() + "','" + voluntario.getOID() + "','" + TestDatos.formatterBD.format(fecha_actual) + "')";
            exito = driver.insertar(sql2);
        } else {
            System.out.println("Error al realizar el UPDATE en la base de datos");
            System.exit(1);
        }
        driver.desconectar();
        return exito;
    }

    public boolean modificarDatosTipoAyuda(TipoAyuda t) throws SQLException {

        DriverJDBC driver = DriverJDBC.getInstance();

        char monetaria;
        if (t.isMonetaria()) {
            monetaria = '1';
        } else {
            monetaria = '0';
        }

        String sql = "UPDATE tipoayuda SET Titulo='" + t.getTitulo() + "',Descripcion='" + t.getDescripcion() + "',Monetaria='" + monetaria + "'WHERE OID='" + t.getOID() + "'";
        driver.conectar();
        boolean exito = driver.actualizar(sql);
        driver.desconectar();
        return exito;
    }

    public boolean registrarDatosAyuda(Ayuda ayuda) {

        DriverJDBC driver = DriverJDBC.getInstance();
        Float importe = ayuda.getImporte();
        Beneficiario ben = ayuda.getBeneficiarioDeAyuda();
        Voluntario vol = ayuda.getVoluntarioQueOtorga();
        boolean exito = false;  
	String sql1 = "INSERT INTO Movimiento (cantidad, fecha, concepto) VALUES ('"+ayuda.getImporte()+"','"+TestDatos.formatterBD.format(ayuda.getFecha())+"','"+ayuda.getTipo_ayuda().getTitulo()+"')";
		
        String sql2 = "INSERT INTO ayuda (Fecha,Importe,Observaciones,TipoAyudaOID,OID_Volun,OID_Bene) VALUES ('" + TestDatos.formatterBD.format(ayuda.getFecha()) + "','" + importe.toString() + "','" + ayuda.getObservaciones() + "','" + ayuda.getTipo_ayuda().getOID() + "','" + vol.getOID() + "','" + ben.getOID() + "')";
        try{
        driver.conectar();
        exito = driver.insertar(sql1);
	exito = driver.insertar(sql2);
		
        ayuda.setOID(this.getOIDUltimaAyuda());				
	String sql3 = "INSERT INTO Gasto (OIDAyuda) VALUES ("+ayuda.getOID()+")";
	exito = driver.insertar(sql3);
        
        
       
        
        driver.desconectar();
        }
        catch (SQLException e ){
                exito = false;
        }
        
        return exito;

    }
}
