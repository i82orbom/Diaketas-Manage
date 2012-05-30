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
 **     002 - May 30, 2012 - JAEG - Añadido parametro parentesco
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
	private Long OID;
    private String NIF;
    private String nombre;
    private String apellidos;
    private Date fechaDENacimiento;
    private ArrayList<Persona> familia;
    private String parentesco;

    private String domicilio;
    private String CP;
    private String localidad;
    private String telefonoMovil;
    private String telefonoFijo;

	public static int  OID_ID = 0;
    public static int  NIF_ID = 1;
    public static int  NOMBRE_ID = 2;
    public static int  APELLIDOS_ID = 3;
    public static int  FECHA_DE_NACIMIENTO_ID = 4;
    public static int  DOMICILIO_ID = 5;
    public static int  CP_ID = 6;
    public static int  LOCALIDAD_ID = 7;
    public static int  TELEFONO_MOVIL_ID = 8;
    public static int  TELEFONO_FIJO_ID = 9;

	public void setOID(Long OID) {
         this.OID = OID;
     }

	public Long getOID() {
		return OID;
	}

    /**
     * Constructor por defecto
     */
    public Persona() {
    }

    /**
     *
     * @return
     */
    public String getCP() {
        return CP;
    }

    /**
     *
     * @param CP
     */
    public void setCP(String CP) {
        this.CP = CP;
    }

    public ArrayList<Persona> getFamilia() {
        return familia;
    }

    public void setFamilia(ArrayList<Persona> familia) {
        this.familia = familia;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
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
    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    /**
     *
     * @param telefonoFijo
     */
    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    /**
     *
     * @return
     */
    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    /**
     *
     * @param telefonoMovil
     */
    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

}
