/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author psylock
 */
public class ModeloBeneficiarioBuscar {
   
    
    //... Member variable defining state of view.
    String dniBeneficiario;
    int criterioBusqueda;
    
    
    //============================================================== constructor
    /** Constructor */
    public ModeloBeneficiarioBuscar() {
        reset();
    }
    
    //==================================================================== reset
    /** Reset to initial value. */
    public final void reset() {
       this.dniBeneficiario = "";
       this.criterioBusqueda = -1;
    }

    //================================================================= setValue
    
    public void setDNIBeneficiario(String value) {
       this.dniBeneficiario = value;
    }
    
    public void setCriterio(int value){
        this.criterioBusqueda = value;
    }
    
    //================================================================= getValue

    public String getDNIBeneficiario() {
        return this.dniBeneficiario;
    }
    
    public int getCriterio(){
        return this.criterioBusqueda;
    }
}
