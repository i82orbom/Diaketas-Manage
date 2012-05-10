/**
 ** NOMBRE CLASE: 
 **	  C_Empresa.java
 **
 ** DESCRIPCION:
 **       Abstracción de una Empresa 
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
public class C_Empresa extends Colaborador{
    
    private int OID;
    private String CIF;
    private String Nombre;
    private String DireccionWeb;
    private String FAX;
    
    // para facilitar la recuperacion de los campos de la interfaz en los controladores
    public static int CIF_ID = 7;
    public static int NOMBRE_ID = 8;
    public static int DIRECCION_WEB_ID = 9;
    public static int FAX_ID = 10;
    
    
    /**
     * Constructor por defecto
     */
    public C_Empresa() {
    }
    
    /**
     * Obtener el OID de la Empresa
     * @return OID
     */
    public int getOIDEmpresa() {
        return OID;
    }
    
    /**
     * Asignar un OID a una Empresa
     * @param OID
     */
    public void setOIDEmpresa(int OID) {
        this.OID = OID;
    }
    
    /**
     * Obtener el CIF de la Empresa
     * @return CIF
     */
    public String getCIF(){
        return CIF;
    }
    
    /**
     * Asignar el CIF a una Empresa
     * @param CIF
     */
    public void setCIF(String CIF){
        this.CIF = CIF;
    }
    
    /**
     * Obtener el Nombre de la Empresa
     * @return Nombre
     */
    public String getNombre(){
        return Nombre;
    }
    
    /**
     * Asignar el Nombre a una Empresa
     * @param Nombre
     */
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
    
    /**
     * Obtener la Dirección Web de la Empresa
     * @return DirecciónWeb
     */
    public String getDireccionWeb(){
        return DireccionWeb;
    }
    
    /**
     * Asignar la Dirección Web a una Empresa
     * @param DireccionWeb
     */
    public void setDireccionWeb(String DireccionWeb){
        this.DireccionWeb = DireccionWeb;
    }
    
    /**
     * Obtener el Fax de la Empresa
     * @return FAX
     */
    public String getFax(){
        return FAX;
    }
    
    /**
     * Asignar el Fax a una Empresa
     * @param Fax
     */
    public void setFax(String Fax){
        this.FAX = Fax;
    }
    
}
