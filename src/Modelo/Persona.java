/**
 ** NOMBRE CLASE: 
 **	  Persona.java
 **
 ** DESCRIPCION:
 **       Abstracción de Persona
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
 *      001 - Mar 29, 2012 - FBR - Se ha añadido un vector para indicar los familiares
 **
 ** NOTAS:
 **   
 **
 */
package Modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jobero
 */
public abstract class Persona {
    
    private String apellidos;
    private int CP;
    private String domicilio;
    private Date fechaDENacimiento;
    private String localidad;
    private String NIF;
    private String nombre;
    private int telefonoFijo;
    private int telefonoMovil;
    private ArrayList<Persona> familia;
    
    private Asociacion asociacion;

    /**
     * Constructor por defecto
     */
    public Persona() {
    }

    /**
     * 
     * @return
     */
    public int getCP() {
        return CP;
    }

    /**
     * 
     * @param CP
     */
    public void setCP(int CP) {
        this.CP = CP;
    }

    /**
     * 
     * @return
     */
    public String getNIF() {
        return NIF;
    }

    /**
     * 
     * @param NIF
     */
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    /**
     * 
     * @return
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * 
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * 
     * @return
     */
    public Asociacion getAsociacion() {
        return asociacion;
    }

    /**
     * 
     * @param asociacion
     */
    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    /**
     * 
     * @return
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * 
     * @param domicilio
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * 
     * @return
     */
    public Date getFechaDENacimiento() {
        return fechaDENacimiento;
    }

    /**
     * 
     * @param fechaDENacimiento
     */
    public void setFechaDENacimiento(Date fechaDENacimiento) {
        this.fechaDENacimiento = fechaDENacimiento;
    }

    /**
     * 
     * @return
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * 
     * @param localidad
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * 
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return
     */
    public int getTelefonoFijo() {
        return telefonoFijo;
    }

    /**
     * 
     * @param telefonoFijo
     */
    public void setTelefonoFijo(int telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    /**
     * 
     * @return
     */
    public int getTelefonoMovil() {
        return telefonoMovil;
    }

    /**
     * 
     * @param telefonoMovil
     */
    public void setTelefonoMovil(int telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }
    
    
    
}
