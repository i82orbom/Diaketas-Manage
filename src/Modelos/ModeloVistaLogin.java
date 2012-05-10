/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author psylock
 */
public class ModeloVistaLogin {
   
    
    //... Member variable defining state of view.
    String usuario;
    String pass;
    
    
    //============================================================== constructor
    /** Constructor */
    public ModeloVistaLogin() {
        reset();
    }
    
    //==================================================================== reset
    /** Reset to initial value. */
    public final void reset() {
       this.usuario = "";
       this.pass = "";
    }

    //================================================================= setValue
    
    public void setUsuario(String value) {
       this.usuario = value;
    }
    
    public void setPass(String value){
        this.pass = value;
    }
    
    //================================================================= getValue

    public String getUsuario() {
        return this.usuario;
    }
    
    public String getPass(){
        return this.pass;
    }
}
