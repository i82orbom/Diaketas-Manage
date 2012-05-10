/**
 ** NOMBRE CLASE: 
 **	  BeneficiarioJDBC.java
 **
 ** DESCRIPCION:
 **       Abstracción JDBC de Beneficiario
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
 **     001 - Mar 25, 2012 - FBR - Implementacion de los metodos
 **     002 - Mar 26, 2012 - FBR - Implementacion de los métodos 
 **     003 - Mar 26, 2012 - FBR - Corregidos errores en las sentencias de añadirFamiliar() y datosFamiliares()
 *      004 - Mar 27, 2012 - FBR - Ampliación e implementación de métodos
 *      005 - Mar 30, 2012 - AAN - Reestructuracion para usar la tabla persona
 **     006 - Abr 14, 2012 - AAN - Modificacion sentencias SQL
 *      007 - Abr 15, 2012 . FBR - Modifiacion de sentencias SQL y métodos
 ** NOTAS:
 **   
 **
 */

package JDBC;

import Modelo.Beneficiario;
import Modelo.Persona;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jobero
 */
    public class BeneficiarioJDBC extends JDBC {
    
        private static BeneficiarioJDBC instancia;
    
        private BeneficiarioJDBC(){
        
        
        }
    
        
        public boolean añadirFamiliar (Beneficiario b, String beneficiarioNIF, String paren) throws SQLException{

            DriverJDBC driver = DriverJDBC.getInstance() ;
            String sentencia, sentencia2 ;
            Date fecha ;
            fecha = b.getFechaDENacimiento();
            Integer cp = b.getCP();
            Integer telefono_fijo = b.getTelefonoFijo();
            Integer telefono_movil = b.getTelefonoMovil();
            
            sentencia = "INSERT INTO persona (NIF,Nombre,Apellidos,FechaNacimiento,CP,TelefonoFijo,TelefonoMovil,Domicilio,Localidad) VALUES ('"+b.getNIF()+"','"+b.getNombre()+"','"+b.getApellidos()+"','"+fecha.toString()+"','"+cp.toString()+"','"+telefono_fijo.toString()+"','"+telefono_movil.toString()+"','"+b.getDomicilio()+"','"+b.getLocalidad()+"' )";

            boolean exito = driver.insertar(sentencia);
            
            if(exito==true){
                sentencia2 = "INSERT INTO familia (PersonaNIF1, PersonaNIF2) VALUES ('"+beneficiarioNIF+"','"+b.getNIF()+"')";
                driver.insertar(sentencia2); 
            }else{
                System.out.println("Error al realizar el INSERT en la base de datos");
                System.exit(1);
            }    
            
            return true;
        }

        
        public ArrayList<Persona> datosFamiliares(String beneficiario_DNI) throws SQLException{
            DriverJDBC driver = DriverJDBC.getInstance() ;
            String sql = "SELECT * FROM persona p, familia f WHERE (f.PersonaNIF1='"+beneficiario_DNI+"') AND p.NIF=f.PersonaNIF2";
            ResultSet resultados = driver.seleccionar(sql);
            ArrayList<Persona> familiares = new ArrayList<Persona>();

            Persona temp = null;
            
            while(resultados.next()){
                temp = new Beneficiario();
                temp.setNIF(resultados.getString("NIF"));
                temp.setNombre(resultados.getString("Nombre"));
                temp.setApellidos(resultados.getString("Apellidos"));
                temp.setFechaDENacimiento(resultados.getDate("FechaNacimiento"));
                temp.setCP(resultados.getInt("CP"));
                temp.setTelefonoFijo(resultados.getInt("TelefonoFijo"));
                temp.setTelefonoMovil(resultados.getInt("TelefonoMovil"));
                temp.setDomicilio(resultados.getString("Domicilio"));
                temp.setLocalidad(resultados.getString("Localidad"));
                   
                familiares.add(temp);
            }
            
            return familiares;
        }
        
        
        public boolean eliminarDatosfamiliar(String familiarDNI, String beneficiarioDNI) throws SQLException{

            DriverJDBC driver = DriverJDBC.getInstance() ;
           
            String sentencia = "DELETE FROM familia WHERE PersonaNIF2= '"+familiarDNI+"' AND PersonaNIF1 = '"+beneficiarioDNI+"'";
            
            boolean exito = driver.eliminar(sentencia); 

            if(exito==false){
                System.out.println("Error al realizar el DELETE en la base de datos");
                System.exit(1);
                
            }
            else {
                String sentencia2 = "DELETE FROM persona WHERE NIF= '"+familiarDNI+"'";
                boolean exito2 = driver.eliminar(sentencia2);
                
            }    
            return true;
        }

        public static BeneficiarioJDBC getInstance(){

            if(instancia == null)
                    instancia = new BeneficiarioJDBC();
            return instancia;

        }
        
        
        public Beneficiario obtenerBeneficiario(String DNI) throws SQLException{
            
            DriverJDBC driver = DriverJDBC.getInstance() ;
           
            String sql = "SELECT * FROM beneficiario b, persona p WHERE (p.NIF='"+DNI+"') AND p.NIF=b.NIF";
            
            ResultSet resultado = driver.seleccionar(sql);
            Beneficiario benef = null ;
           
            if(resultado.next()){
                benef = new Beneficiario();
                benef.setNIF(resultado.getString("NIF"));
                benef.setNombre(resultado.getString("Nombre"));
                benef.setApellidos(resultado.getString("Apellidos"));
                benef.setFechaDENacimiento(resultado.getDate("FechaNacimiento"));
                benef.setCP(resultado.getInt("CP"));
                benef.setTelefonoFijo(resultado.getInt("TelefonoFijo"));
                benef.setTelefonoMovil(resultado.getInt("TelefonoMovil"));
                benef.setDomicilio(resultado.getString("Domicilio"));
                benef.setLocalidad(resultado.getString("Localidad"));
                benef.setEstadoCivil(resultado.getString("EstadoCivil"));
                benef.setNacionalidad(resultado.getString("Nacionalidad"));
                benef.setNivelDeEstudio(resultado.getString("NivelDeEstudio"));
                benef.setObservaciones(resultado.getString("Observaciones"));
                benef.setOcupacion(resultado.getString("Ocupacion"));
                benef.setProfesion(resultado.getString("Profesion"));
                benef.setSituacionEconomica(resultado.getString("SituacionEconomica"));
                benef.setVivienda(resultado.getString("Vivienda"));
                benef.setViviendaAlquiler(resultado.getFloat("ViviendaAlquiler"));
                benef.setViviendaObservaciones(resultado.getString("ViviendaObservaciones"));
            }
               
            return benef;
        }
        
        public boolean borrarBeneficiario (String DNI) throws SQLException{
            
            DriverJDBC driver = DriverJDBC.getInstance() ;
           
            String sql = "DELETE FROM Beneficiario WHERE NIF='"+DNI+"'";
            
            boolean exito = driver.eliminar(sql);
            
            if(exito){
                sql = "DELETE from familiar WHERE beneficiarioNIF='"+DNI+"'";
                exito = driver.eliminar(sql);
                sql = "DELETE from persona WHERE NIF='"+DNI+"'";
                exito = driver.eliminar(sql);
            }
            
            return exito;
        } 
        
        
        public ArrayList<Beneficiario> obtenerListadoBeneficiario(String dato, String tipoDato) throws SQLException{
            
            DriverJDBC driver = DriverJDBC.getInstance() ;
            String sql = "SELECT * FROM beneficiario b, persona p WHERE "+tipoDato+"='"+dato+"' AND p.NIF=b.NIF";

            ResultSet resultados = driver.seleccionar(sql);
            ArrayList<Beneficiario> listadoBeneficiarios = new ArrayList<Beneficiario>();
            Beneficiario temp = null;
            
            if(resultados.next()){
                temp = new Beneficiario();
                temp.setNIF(resultados.getString("NIF"));
                temp.setNivelDeEstudio(resultados.getString("NivelDeEstudio"));
                temp.setObservaciones(resultados.getString("Observaciones"));
                temp.setOcupacion(resultados.getString("Ocupacion"));
                temp.setProfesion(resultados.getString("Profesion"));
                temp.setSituacionEconomica(resultados.getString("SituacionEconomica"));
                temp.setVivienda(resultados.getString("Vivienda"));
                temp.setViviendaAlquiler(resultados.getFloat("ViviendaAlquiler"));
                temp.setViviendaObservaciones(resultados.getString("ViviendaObservaciones"));
                temp.setNacionalidad(resultados.getString("Nacionalidad"));
                temp.setNombre(resultados.getString("Nombre"));
                temp.setApellidos(resultados.getString("Apellidos"));
                temp.setFechaDENacimiento(resultados.getDate("FechaNacimiento"));
                temp.setCP(resultados.getInt("CP"));
                temp.setTelefonoFijo(resultados.getInt("TelefonoFijo"));
                temp.setTelefonoMovil(resultados.getInt("TelefonoMovil"));
                temp.setDomicilio(resultados.getString("Domicilio"));
                temp.setLocalidad(resultados.getString("Localidad"));
                
                   
                listadoBeneficiarios.add(temp);
            }
            return listadoBeneficiarios;
        } 
        
        public boolean modificarDatosBeneficiario (Beneficiario beneficiario) throws SQLException{
            
            DriverJDBC driver = DriverJDBC.getInstance() ;
            Integer Cp = beneficiario.getCP();
            String Cp_cadena = Cp.toString();
            Integer telefono_fijo = beneficiario.getTelefonoFijo();
            String telefono_fijo_cadena = telefono_fijo.toString();
            Integer telefono_movil = beneficiario.getTelefonoMovil();
            String telefono_movil_cadena = telefono_movil.toString();
            String nivel_estudio = beneficiario.getNivelDeEstudio();
            String nivel_estudio_cadena = nivel_estudio.toString();
            
            String sql2 = "UPDATE persona SET NIF='"+beneficiario.getNIF()+"',Nombre='"+beneficiario.getNombre()+"',Apellidos='"+beneficiario.getApellidos()+"',FechaNacimiento='"+beneficiario.getFechaDENacimiento()+"',CP='"+Cp_cadena+"',TelefonoFijo='"+telefono_fijo_cadena+"',TelefonoMovil='"+telefono_movil_cadena+"',Domicilio='"+beneficiario.getDomicilio()+"',Localidad='"+beneficiario.getLocalidad()+"WHERE NIF ="+beneficiario.getNIF()+"'";
            
            boolean exito2 = driver.actualizar(sql2);
            
            String sql = "UPDATE beneficiario SET NIF='"+beneficiario.getNIF()+"',Nacionalidad='"+beneficiario.getNacionalidad()+"',EstadoCivil='"+beneficiario.getEstadoCivil()+"',NivelDeEstudio='"+nivel_estudio_cadena+"',Observaciones='"+beneficiario.getObservaciones()+"',Ocupacion='"+beneficiario.getOcupacion()+"',Profesion='"+beneficiario.getProfesion()+"',SituacionEconomica='"+beneficiario.getSituacionEconomica()+"',Vivienda='"+beneficiario.getVivienda()+"',ViviendaAlquiler='"+beneficiario.getViviendaAlquiler()+"',ViviendaObservaciones='"+beneficiario.getViviendaObservaciones()+"WHERE NIF ="+beneficiario.getNIF()+"'";
            
            boolean exito = driver.actualizar(sql);
            
            return exito;
        }
    
        public boolean añadirBeneficiario (Beneficiario beneficiario) throws SQLException{
        
        DriverJDBC driver = DriverJDBC.getInstance() ;
        Integer Cp = beneficiario.getCP();
        String Cp_cadena = Cp.toString();
        Integer telefono_fijo = beneficiario.getTelefonoFijo();
        String telefono_fijo_cadena = telefono_fijo.toString();
        Integer telefono_movil = beneficiario.getTelefonoMovil();
        String telefono_movil_cadena = telefono_movil.toString();  
        String nivel_estudio = beneficiario.getNivelDeEstudio();
        String nivel_estudio_cadena = nivel_estudio.toString();
        Float vivienda_alquiler = beneficiario.getViviendaAlquiler();
        String vivienda_alquiler_cadena = String.valueOf(beneficiario.getViviendaAlquiler());
        String sql2 = "INSERT INTO persona (NIF,Nombre,Apellidos,FechaNacimiento,CP,TelefonoFijo,TelefonoMovil,Domicilio,Localidad) VALUES ('"+beneficiario.getNIF()+"','"+beneficiario.getNombre()+"','"+beneficiario.getApellidos()+"','"+beneficiario.getFechaDENacimiento()+"','"+Cp_cadena+"','"+telefono_fijo_cadena+"','"+telefono_movil_cadena+"','"+beneficiario.getDomicilio()+"','"+beneficiario.getLocalidad()+"')";
        boolean exito2 = driver.insertar(sql2);
        String sql = "INSERT INTO beneficiario (NIF,EstadoCivil,Nacionalidad,NivelDeEstudio,Observaciones,Ocupacion,Profesion,SituacionEconomica,Vivienda,ViviendaAlquiler,ViviendaObservaciones) VALUES ('"+beneficiario.getNIF()+"','"+beneficiario.getEstadoCivil()+"','"+beneficiario.getNacionalidad()+"','"+nivel_estudio_cadena+"','"+beneficiario.getObservaciones()+"','"+beneficiario.getOcupacion()+"','"+beneficiario.getProfesion()+"','"+beneficiario.getSituacionEconomica()+"','"+beneficiario.getVivienda()+"','"+vivienda_alquiler_cadena+"','"+beneficiario.getViviendaObservaciones()+"')";
        
        boolean exito=driver.insertar(sql);
        
        return exito;
        
    }
}
