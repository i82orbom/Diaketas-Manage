/**
 ** NOMBRE CLASE: 
 **	  Socio.java
 **
 ** DESCRIPCION:
 **       Abstracción de un Socio
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
public class Socio extends C_Persona{
    
    private int OID;
    private String usuario;
    private String contrasena;
    
    public static int USUARIO_ID = 10;
    public static int CONTRASENA_ID = 11;
    
    /**
     * Constructor por defecto
     */
    public Socio(){
    }
        
    /**
     * Obtener el OID del Socio
     * @return OID
     */
    public int getOIDSocio() {
        return OID;
    }
    
    /**
     * Asignar un OID a un Socio
     * @param OID
     */
    public void setOIDSocio(int OID) {
        this.OID = OID;
    }
    
    /**
     * Obtener el usuario del Socio
     * @return usuario
     */
    public String getUsuario() {
        return usuario;
    }
    
    /**
     * Asignar un usuario a un Socio
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Obtener la contraseña del Socio
     * @return contrasena
     */
    public String getContrasena() {
        return contrasena;
    }
    
    /**
     * Asignar una contraseña a un Socio
     * @param contrasena
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
