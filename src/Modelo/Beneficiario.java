/**
 ** NOMBRE CLASE: 
 **	  Beneficiario.java
 **
 ** DESCRIPCION:
 **       Abstracción de Beneficiario
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

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Jobero
 */
public class Beneficiario extends Persona implements Identificable,Serializable {
    
    private Long OID;
    private String estadoCivil;
    private String nacionalidad;
    private String nivelDeEstudio;
    private String observaciones;
    private String ocupacion;
    private String profesion;
    private String situacionEconomica;
    private String vivienda;
    private float viviendaAlquiler;
    private String viviendaObservaciones;
   
    private ArrayList<Ayuda> ayudasPrestadas;
	
	private void writeObject(ObjectOutputStream aOutputStream) throws IOException{
		
		aOutputStream.defaultWriteObject();
		
	}
	
	/**
     * Constructor por defecto
     */
    public Beneficiario() {
    }

    /**
     * 
     * @return
     */
    public ArrayList<Ayuda> getAyudasPrestadas() {
        return ayudasPrestadas;
    }

    /**
     * 
     * @param ayudasPrestadas
     */
    public void setAyudasPrestadas(ArrayList<Ayuda> ayudasPrestadas) {
        this.ayudasPrestadas = ayudasPrestadas;
    }

    /**
     * 
     * @return
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * 
     * @param estadoCivil
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

   


    /**
     * 
     * @return
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * 
     * @param nacionalidad
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * 
     * @return
     */
    public String getNivelDeEstudio() {
        return nivelDeEstudio;
    }

    /**
     * 
     * @param nivelDeEstudio
     */
    public void setNivelDeEstudio(String nivelDeEstudio) {
        this.nivelDeEstudio = nivelDeEstudio;
    }

    /**
     * 
     * @return
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * 
     * @param observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * 
     * @return
     */
    public String getOcupacion() {
        return ocupacion;
    }

    /**
     * 
     * @param ocupacion
     */
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    /**
     * 
     * @return
     */
    public String getProfesion() {
        return profesion;
    }

    /**
     * 
     * @param profesion
     */
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    /**
     * 
     * @return
     */
    public String getSituacionEconomica() {
        return situacionEconomica;
    }

    /**
     * 
     * @param situacionEconomica
     */
    public void setSituacionEconomica(String situacionEconomica) {
        this.situacionEconomica = situacionEconomica;
    }

    /**
     * 
     * @return
     */
    public String getVivienda() {
        return vivienda;
    }

    /**
     * 
     * @param vivienda
     */
    public void setVivienda(String vivienda) {
        this.vivienda = vivienda;
    }

    /**
     * 
     * @return
     */
    public float getViviendaAlquiler() {
        return viviendaAlquiler;
    }

    /**
     * 
     * @param viviendaAlquiler
     */
    public void setViviendaAlquiler(float viviendaAlquiler) {
        this.viviendaAlquiler = viviendaAlquiler;
    }

    /**
     * 
     * @return
     */
    public String getViviendaObservaciones() {
        return viviendaObservaciones;
    }

    /**
     * 
     * @param viviendaObservaciones
     */
    public void setViviendaObservaciones(String viviendaObservaciones) {
        this.viviendaObservaciones = viviendaObservaciones;
    }

	@Override
	public void setOID(Long OID) {
		this.OID = OID;
	}

	@Override
	public Long getOID() {
		return OID;
	}
    
}
