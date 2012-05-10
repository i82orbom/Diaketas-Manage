/**
 ** NOMBRE CLASE: 
 **	  Colaborador.java
 **
 ** DESCRIPCION:
 **       Abstracción de un Colaborador
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

/**
 *
 * @author PLB
 */
public abstract class Colaborador{
    
    private int OID;
    private String Direccion;
    private String Localidad;
    private String Provincia;
    private String CodigoPostal;
    private String TelefonoFijo;
    private String TelefonoMovil;
    private String Email;
    
    // para facilitar la recuperacion de los campos de la interfaz en los controladores
    public static int DIRECCION_ID = 0;
    public static int LOCALIDAD_ID = 1;
    public static int PROVINCIA_ID = 2;
    public static int CP_ID = 3;
    public static int TEL1_ID = 4;
    public static int TEL2_ID = 5;
    public static int EMAIL_ID = 6;
    
    
    /**
     * Constructor por defecto
     */
    public Colaborador() {
    }
    
    /**
     * Obtener el OID del Colaborador
     * @return OID
     */
    public int getOIDColaborador() {
        return OID;
    }
    
    /**
     * Asignar el OID a un Colaborador
     * @param OID
     */
    public void setOIDColaborador(int OID) {
        this.OID = OID;
    }
    
    /**
     * Obtener la dirección de un Colaborador
     * @return Direccion
     */
    public String getDireccion(){
        return Direccion;
    }
    
    /**
     * Asignar una dirección a un Colaborador
     * @param Direccion
     */
    public void setDireccion(String Direccion){
        this.Direccion = Direccion;
    }
    
    /**
     * Obtener la Localidad de un Colaborador
     * @return Localidad
     */
    public String getLocalidad(){
        return Localidad;
    }
    
    /**
     * Asignar una Localidad a un Colaborador
     * @param Localidad
     */
    public void setLocalidad(String Localidad){
        this.Localidad = Localidad;
    }
    
    /**
     * Obtener la Provincia de un Colaborador
     * @return Provincia
     */
    public String getProvincia(){
        return Provincia;
    }
    
    /**
     * Asignar una Provincia a un Colaborador
     * @param Provincia
     */
    public void setProvincia(String Provincia){
        this.Provincia = Provincia;
    }
    
    /**
     * Obtener el CP de un Colaborador
     * @return CodigoPostal
     */
    public String getCP(){
        return CodigoPostal;
    }
    
    /**
     * Asignar un CP a un Colaborador
     * @param CodigoPostal
     */
    public void setCP(String CodigoPostal){
        this.CodigoPostal = CodigoPostal;
    }
    
    /**
     * Obtener el TelefonoFijo de la Empresa
     * @return TelefonoFijo
     */
    public String getTelefonoFijo(){
        return TelefonoFijo;
    }
    
    /**
     * Asignar el TelefonoFijo a una Empresa
     * @param TelefonoFijo
     */
    public void setTelefonoFijo(String TelefonoFijo){
        this.TelefonoFijo = TelefonoFijo;
    }
    
    /**
     * Obtener el TelefonoMovil de la Empresa
     * @return TelefonoMovil
     */
    public String getTelefonoMovil(){
        return TelefonoMovil;
    }
    
    /**
     * Asignar el TelefonoMovil a una Empresa
     * @param TelefonoMovil
     */
    public void setTelefonoMovil(String TelefonoMovil){
        this.TelefonoMovil = TelefonoMovil;
    }
    
    /**
     * Obtener el Email de un Colaborador
     * @return Email
     */
    public String getEmail(){
        return Email;
    }
    
    /**
     * Asignar un Email a un Colaborador
     * @param Email
     */
    public void setEmail(String Email){
        this.Email = Email;
    }
}
