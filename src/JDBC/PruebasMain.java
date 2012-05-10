/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import Modelo.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author psylock
 */
public class PruebasMain {
    	
    public static void main (String ...args){
        
       /* DriverJDBC driver = DriverJDBC.getInstance();
        driver.configurar("127.0.0.1", "diaketas", "", "root");
       
		*/
		
		
		
		Beneficiario benef = new Beneficiario();
        benef.setNombre("Pepe");
        benef.setApellidos("Garcia Palos");
        benef.setNIF("12345678Y");
        benef.setCP(18000);
        benef.setDomicilio("C/Falsa 123");
        benef.setEstadoCivil("soltero");
        benef.setLocalidad("GRANADA");
        benef.setNacionalidad("ESPAÑOLA");
        benef.setTelefonoFijo(958131718);
        benef.setTelefonoMovil(630981148);
        benef.setNivelDeEstudio("bla");
        benef.setObservaciones("");
        benef.setOcupacion("");
        benef.setSituacionEconomica("");
        benef.setProfesion("");
        benef.setVivienda("");
        benef.setViviendaAlquiler(1);
        benef.setViviendaObservaciones("");
		
		try{
			Long oid = JDBC.genOID(benef);
			System.out.print("\nEl oid es: "+oid+".\n");
		}catch(java.io.IOException ex){
			System.err.print(ex.getMessage());
		}
		
		
				
				
				
	/*			
				
		java.util.Date fecha = new java.util.Date();
		System.out.print(fecha);
		
        if (!driver.conectar()){
            System.out.println("No se ha podido conectar a la base de datos");
            System.exit(-1);
        }
        
       BeneficiarioJDBC benf = BeneficiarioJDBC.getInstance();
        
        
        Familiar fam = new Familiar();
        fam.setApellidos("Garcia Perez");
        fam.setNombre("Antonio");
        fam.setFechaNacimiento("1990-04-3");
        
        fam.setNIF("12345678X");
        fam.setOcupacion("Estudiante");
        fam.setParentesco("Hijo");
   
       
        try {
            benf.añadirFamiliar(fam, "98765432Y");
        } catch (SQLException ex) {
           // Logger.getLogger(PruebasMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList<Familiar> familiares;
        try {
            familiares = benf.datosFamiliares("98765432Y");
            
            System.out.println(familiares);
        } catch (SQLException ex) {
            Logger.getLogger(PruebasMain.class.getName()).log(Level.SEVERE, null, ex);
        }
     */
     /*   
        AyudaJDBC ay = AyudaJDBC.getInstance();
        BeneficiarioJDBC be = BeneficiarioJDBC.getInstance();
        
        Ayuda ayud = new Ayuda();
        Asociacion asoc = new Asociacion();
        asoc.setNombre("DIAKETAS");
        //java.util.Date fecha = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(fecha.getTime());
        ayud.setAsociacionQueOtorga(asoc);
        ayud.setImporte(300);
        ayud.setFecha("2012-03-27");
        Beneficiario benef = new Beneficiario();
        benef.setNombre("Pepe");
        benef.setApellidos("Garcia Palos");
        benef.setNIF("12345678Y");
        benef.setAsociacion(asoc);
        benef.setCP(18000);
        benef.setDomicilio("C/Falsa 123");
        benef.setEstadoCivil("soltero");
        benef.setLocalidad("GRANADA");
        benef.setNacionalidad("ESPAÑOLA");
        benef.setFechaDENacimiento(sqldate);
        benef.setTelefonoFijo(958131718);
        benef.setTelefonoMovil(630981148);
        benef.setNivelDeEstudio("bla");
        benef.setObservaciones("");
        benef.setOcupacion("");
        benef.setSituacionEconomica("");
        benef.setProfesion("");
        benef.setVivienda("");
        benef.setViviendaAlquiler(1);
        benef.setViviendaObservaciones("");
        System.out.println(asoc.getNombre());
        ayud.setBeneficiarioDeAyuda(benef);
         TipoAyuda tip = new TipoAyuda();
         tip.setMonetaria(true);
         tip.setDescripcion("Ayuda de tipo monetaria");
         tip.setOID("1");
         tip.setTitulo("MONTERIA");
         try {
            be.añadirBeneficiario(benef);
            
        } catch (SQLException ex) {
            Logger.getLogger(PruebasMain.class.getName()).log(Level.SEVERE, null, ex);
        }
       /* try {
            ay.insertarTipoAyuda(tip);
            
        } catch (SQLException ex) {
            Logger.getLogger(PruebasMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        tip.setDescripcion("modificando");
        tip.setTitulo("JARJAR");
        try{
        ay.modificarDatosTipoAyuda(tip);
        } catch (SQLException ex) {
            Logger.getLogger(PruebasMain.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        /*java.util.Date fecha = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(fecha.getTime());
        Movimiento mov = new Movimiento();
        mov.setAsociacionQueGenera(asoc);
        mov.setConcepto("eres mu feo");
        mov.setFecha(sqldate);
        mov.setImporte(200);
        mov.setMovimientoID("1");
        mov.setTipo('I');
        
        MovimientoJDBC movi = MovimientoJDBC.getInstance();
        
        try{
        movi.registrarDatosGasto(mov);
        } catch (SQLException ex) {
            Logger.getLogger(PruebasMain.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
