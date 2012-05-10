/**
 ** NOMBRE CLASE: 
 **	  Asociacion.java
 **
 ** DESCRIPCION:
 **       Abstracción de Asociación
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
 *      
 **
 ** NOTAS:
 **   
 **
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Jobero
 */
public class Asociacion {
    
    private String nombre;
    private String direccion;
    
    private ArrayList<Persona> personas;
    private ArrayList<Movimiento> movimientosGenerados;
    private ArrayList<Ayuda> ayudas;

    /**
     * Constructor por defecto
     */
    public Asociacion() {
    }

    /**
     * @
     * @return 
     */
    public ArrayList<Ayuda> getAyudas() {
        return ayudas;
    }

    /**
     * 
     * @param ayudas
     */
    public void setAyudas(ArrayList<Ayuda> ayudas) {
        this.ayudas = ayudas;
    }

    /**
     * 
     * @return
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * 
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * 
     * @return
     */
    public ArrayList<Movimiento> getMovimientosGenerados() {
        return movimientosGenerados;
    }

    /**
     * 
     * @param movimientosGenerados
     */
    public void setMovimientosGenerados(ArrayList<Movimiento> movimientosGenerados) {
        this.movimientosGenerados = movimientosGenerados;
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
    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    /**
     * 
     * @param personas
     */
    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }
        
}
