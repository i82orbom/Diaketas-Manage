/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Colaborador;

import Controladores.TestDatos;
import JDBC.C_EmpresaJDBC;
import Modelo.C_Empresa;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 ** NOMBRE CLASE: *	ControladorC_Empresa 
 * 
 * DESCRIPCION: 
 *      Controlador del panel de coladores de typo empresa
 * 
 * 
 * DESARROLLADO POR: 
 *      Raphael Colleau (RC) 
 * 
 * 
 * SUPERVISADO POR: 
 * 
 * 
 * HISTORIA: 
 *      000 - 1 mai 2012 - RC - Creacion 
 *      001 - 2 mai 2012 - RC - adicion de metodos
 *      002 - 3 mai 2012 - RC - relleno metodos
 *      003 - 5 mai 2012 - RC - comproba datos
 *      004 - 7 mai 2012 - RC - interface actionListener
 * 
 * NOTAS: 
 * 
 *
 */
public class ControladorC_Empresa {

    /**
     * PATRON DE DISEÑO SINGLETON
     */
    private static ControladorC_Empresa instancia = null;

    /* TODO vista
    public static ControladorC_Empresa getInstance(PanelColaboradorC_Empresa pvista) {

        if (instancia == null) {
            instancia = new ControladorC_Empresa(pvista);
        }
        return instancia;

    }
    
    private PanelColaboradorC_Empresa vista;

    public ControladorC_Empresa(PanelColaboradorC_Empresa vista) {
        this.vista = vista;
    }*/
    
    public boolean anadirC_Empresa(String[] datos) {
        
        if (!ComprobarDatos(datos)) 
            return false;
        
        C_Empresa tempE = new C_Empresa();
        
        tempE.setCIF(datos[C_Empresa.CIF_ID]);
        tempE.setNombre(datos[C_Empresa.NOMBRE_ID]); 
        
        tempE.setEmail(datos[C_Empresa.EMAIL_ID]);
        tempE.setDireccion(datos[C_Empresa.DIRECCION_ID]);
        tempE.setLocalidad(datos[C_Empresa.LOCALIDAD_ID]);
        tempE.setProvincia(datos[C_Empresa.PROVINCIA_ID]);
        tempE.setCP(datos[C_Empresa.CP_ID]);
        tempE.setTelefonoFijo(datos[C_Empresa.TEL1_ID]);
        tempE.setTelefonoMovil(datos[C_Empresa.TEL2_ID]);
                
        tempE.setDireccionWeb(datos[C_Empresa.DIRECCION_WEB_ID]);
        tempE.setFax(datos[C_Empresa.FAX_ID]);
        
        boolean exito = false;
        try {
            exito = C_EmpresaJDBC.getInstance().añadirC_Empresa(tempE);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exito;
    }
    
    public C_Empresa obtenerC_Empresa (String CIF) {
        C_Empresa tempE;
        
        try {
            tempE = C_EmpresaJDBC.getInstance().obtenerC_Empresa(CIF);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
            tempE = null;
        }
        
        return tempE;
    }
    
    public boolean modificarC_Empresa (String[] datos) {
        if (!ComprobarDatos(datos)) 
            return false;
        
        C_Empresa tempE = new C_Empresa();
        
        tempE.setCIF(datos[C_Empresa.CIF_ID]);
        tempE.setNombre(datos[C_Empresa.NOMBRE_ID]);
        
        tempE.setEmail(datos[C_Empresa.EMAIL_ID]);
        tempE.setDireccion(datos[C_Empresa.DIRECCION_ID]);
        tempE.setLocalidad(datos[C_Empresa.LOCALIDAD_ID]);
        tempE.setProvincia(datos[C_Empresa.PROVINCIA_ID]);
        tempE.setCP(datos[C_Empresa.CP_ID]);
        tempE.setTelefonoFijo(datos[C_Empresa.TEL1_ID]);
        tempE.setTelefonoMovil(datos[C_Empresa.TEL2_ID]);
        
        tempE.setDireccionWeb(datos[C_Empresa.DIRECCION_WEB_ID]);
        tempE.setFax(datos[C_Empresa.FAX_ID]);
        
        
        boolean exito = false;
        try {
            exito = C_EmpresaJDBC.getInstance().modificarDatosC_Empresa(tempE);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exito;
    }
    
    public boolean eliminarC_Empresa (C_Empresa tempE) {
        boolean exito;
        try {
            exito = C_EmpresaJDBC.getInstance().eliminarC_Empresa(tempE);
        } catch (SQLException ex) {
            exito = false;
            Logger.getLogger(ControladorC_Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exito;
    }
    
    public ArrayList<C_Empresa> buscarC_Empresa (String tipoBusqueta, String valor) {
        ArrayList<C_Empresa> personas;
        
        try {
            personas = C_EmpresaJDBC.getInstance().buscarC_Empresa(tipoBusqueta, valor);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return personas;
    }
    
    // proba los datos pero no sabemos cual son los datos que no son correcto
    private boolean ComprobarDatos (String[] datos) {
        // proba los datos que deben ser not null
        for (int i=0; i<8; i++) {
            if (datos[i].length() < 1)
                return false;
        }
        
        if (!TestDatos.isCIF(datos[C_Empresa.CIF_ID]))
            return false;
        
        if (!TestDatos.isCodigoPostal(datos[C_Empresa.CP_ID]))
            return false;
        
        if (!TestDatos.isEmail(datos[C_Empresa.EMAIL_ID]))
            return false;
        
        if (!TestDatos.isTelefonoOFax(datos[C_Empresa.TEL1_ID]))
            return false;  
        
        if (!TestDatos.isOnlyLetter(datos[C_Empresa.NOMBRE_ID]))
            return false;
        
        if (!TestDatos.isOnlyLetter(datos[C_Empresa.DIRECCION_ID]))
            return false;
        
        if (!TestDatos.isOnlyLetter(datos[C_Empresa.LOCALIDAD_ID]))
            return false;
        
        if (!TestDatos.isOnlyLetter(datos[C_Empresa.PROVINCIA_ID]))
            return false;
        
        
        
        if (datos[C_Empresa.TEL2_ID].length() > 0 && !TestDatos.isTelefonoOFax(datos[C_Empresa.TEL2_ID]))
            return false;
        
        if (datos[C_Empresa.FAX_ID].length() > 0 && !TestDatos.isTelefonoOFax(datos[C_Empresa.FAX_ID]))
            return false;
        
        return true;
    }
}
