/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author psylock
 */
public class ModeloBeneficiarioDatos {

   
    
    //... Member variable defining state of view.
    String nombre;
    String apellidos;
    String dni;
    String fechaNacimiento;
    String lugarNacimiento;
    String nacionalidad;
    String estadoCivil;
    String nivelEstudios;
    String profesion;
    String domicilio;
    String telefono;
    String cp;
    String observaciones;
    
    String tipoVivienda;
    String precioVivienda;
    String especificacionTipoVivienda;
    
    
    String dniFamiliar;
    String parentesco;
    String observacionesFamiliar;
    
    String importeIntervencion;
    String conceptoIntervencion;
    String observacionesIntervencion;
    
    
  
    //============================================================== constructor
    /** Constructor */
    public ModeloBeneficiarioDatos() {
        reset();
    }
    
    //==================================================================== reset
    /** Reset to initial value. */
    public final void reset() {
        nombre = "";
        apellidos = "";
        dni = "";
        fechaNacimiento = "";
        lugarNacimiento = "";
        nacionalidad = "";
        estadoCivil = "";
        nivelEstudios = "";
        profesion = "";
        domicilio = "";
        telefono = "";
        cp = "";
        observaciones = "";

        tipoVivienda = "";
        precioVivienda = "";
        especificacionTipoVivienda = "";

        dniFamiliar = "";
        parentesco = "";
        observacionesIntervencion = "";
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDniFamiliar() {
        return dniFamiliar;
    }

    public void setDniFamiliar(String dniFamiliar) {
        this.dniFamiliar = dniFamiliar;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEspecificacionTipoVivienda() {
        return especificacionTipoVivienda;
    }

    public void setEspecificacionTipoVivienda(String especificacionTipoVivienda) {
        this.especificacionTipoVivienda = especificacionTipoVivienda;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNivelEstudios() {
        return nivelEstudios;
    }

    public void setNivelEstudios(String nivelEstudios) {
        this.nivelEstudios = nivelEstudios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObservacionesIntervencion() {
        return observacionesIntervencion;
    }

    public void setObservacionesIntervencion(String observacionesIntervencion) {
        this.observacionesIntervencion = observacionesIntervencion;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getPrecioVivienda() {
        return precioVivienda;
    }

    public void setPrecioVivienda(String precioVivienda) {
        this.precioVivienda = precioVivienda;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }
   
      public String getConceptoIntervencion() {
        return conceptoIntervencion;
    }

    public void setConceptoIntervencion(String conceptoIntervencion) {
        this.conceptoIntervencion = conceptoIntervencion;
    }

    public String getImporteIntervencion() {
        return importeIntervencion;
    }

    public void setImporteIntervencion(String importeIntervencion) {
        this.importeIntervencion = importeIntervencion;
    }

    public String getObservacionesFamiliar() {
        return observacionesFamiliar;
    }

    public void setObservacionesFamiliar(String observacionesFamiliar) {
        this.observacionesFamiliar = observacionesFamiliar;
    }
    
            
    
    
    
}
