/**
 ** NOMBRE CLASE: 
 **	  C_Persona.java
 **
 ** DESCRIPCION:
 **       Abstracción de una Persona 
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
 ** 	000 - Abril 23, 2012 - PLB - Creacion 
 **
 ** NOTAS:
 **   
 **
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author PLB
 */
public class C_Persona extends Colaborador{
    
    private int OID;
    private String DNI;
    private String Nombre;
    private String Apellidos;
    private Date FechaDeNacimiento;
    private char Sexo;
    
    // para facilitar la recuperacion de los campos de la interfaz en los controladores
    public static int DNI_ID = 5;
    public static int NOMBRE_ID = 6;
    public static int APELLIDOS_ID = 7;
    public static int FECHA_DE_NACIMIENTO_ID = 8;
    public static int SEXO_ID = 9;
    
    /**
     * Constructor por defecto
     */
    public C_Persona(){
    }
        
    /**
     * Obtener el OID de la Persona
     * @return OID
     */
    public int getOIDPersona() {
        return OID;
    }
    
    /**
     * Asignar un OID a una Persona
     * @param OID
     */
    public void setOIDPersona(int OID) {
        this.OID = OID;
    }
    
    /**
     * Obtener el DNI de la Persona
     * @return DNI
     */
    public String getDNI(){
        return DNI;
    }
    
    /**
     * Asignar el DNI a una Persona
     * @param DNI
     */
    public void setDNI(String DNI){
        this.DNI = DNI;
    }
    
    /**
     * Obtener el Nombre de la Persona
     * @return Nombre
     */
    public String getNombre(){
        return Nombre;
    }
    
    /**
     * Asignar el Nombre a una Persona
     * @param Nombre
     */
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
    
    /**
     * Obtener los Apellidos de la Persona
     * @return Apellidos
     */
    public String getApellidos(){
        return Apellidos;
    }
    
    /**
     * Asignar los Apellidos a una Persona
     * @param Apellidos
     */
    public void setApellidos(String Apellidos){
        this.Apellidos = Apellidos;
    }
    
    /**
     * Obtener la Fecha de Nacimiento de la Persona
     * @return FechaDeNacimiento
     */
    public Date getFechaDeNacimiento(){
        return FechaDeNacimiento;
    }
    
    /**
     * Asignar la Fecha de Nacimiento a una Persona
     * @param FechaDeNacimiento
     */
    public void setFechaDeNacimiento(Date FechaDeNacimiento){
        this.FechaDeNacimiento = FechaDeNacimiento;
    }
    
    /**
     * Obtener el Sexo de la Persona
     * @return Sexo
     */
    public char getSexo(){
        return Sexo;
    }
    
    /**
     * Asignar el Sexo a una Persona
     * @param Sexo
     */
    public void setSexo(char Sexo){
        this.Sexo = Sexo;
    }
}
