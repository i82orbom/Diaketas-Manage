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

    private String usuario;
    private String contrasena;

    public static int USUARIO_ID = 12;
    public static int CONTRASENA_ID = 13;

    /**
     * Constructor por defecto
     */
    public Socio(){
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
