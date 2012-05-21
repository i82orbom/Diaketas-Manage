
package Controladores.Colaborador;

import Controladores.TestDatos;
import Controladores.Voluntario.ControladorVoluntario;
import JDBC.C_PersonaJDBC;
import Modelo.C_Persona;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 ** NOMBRE CLASE:
 **	  ControladorC_Persona
 **
 ** DESCRIPCION:
 **       Controlador del panel de coladores de typo persona
 **
 **
 ** DESARROLLADO POR:
 *          Raphael Colleau (RC)
 **
 **
 ** SUPERVISADO POR:
 **
 **
 ** HISTORIA:
 ** 	000 - 1 mai 2012 - RC - Creacion
 *      001 - 2 mai 2012 - RC - adicion de metodos
 *      002 - 3 mai 2012 - RC - relleno metodos
 *      003 - 5 mai 2012 - RC - comproba datos
 **
 ** NOTAS:
 **
 **
 */
public class ControladorC_Persona {

    /**
     * PATRON DE DISEÑO SINGLETON
     */
    private static ControladorC_Persona instancia = null;

    /* TODO vista
    public static ControladorC_Persona getInstance(PanelColaboradorC_Persona pvista) {

        if (instancia == null) {
            instancia = new ControladorC_Persona(pvista);
        }
        return instancia;

    }

    private PanelColaboradorC_Persona vista;

    public ControladorC_Persona(PanelColaboradorC_Persona vista) {
        this.vista = vista;
    }
    */

    public boolean anadirC_Persona(String[] datos) {
        if (!ComprobarDatos(datos))
            return false;

        C_Persona tempP = new C_Persona();

        tempP.setDNI(datos[C_Persona.DNI_ID]);
        tempP.setNombre(datos[C_Persona.NOMBRE_ID]);
        tempP.setApellidos(datos[C_Persona.APELLIDOS_ID]);
        tempP.setSexo(datos[C_Persona.SEXO_ID].charAt(0));
        tempP.setFechaDeNacimiento(Date.valueOf(datos[C_Persona.FECHA_DE_NACIMIENTO_ID]));

        tempP.setEmail(datos[C_Persona.EMAIL_ID]);
        tempP.setDireccion(datos[C_Persona.DIRECCION_ID]);
        tempP.setLocalidad(datos[C_Persona.LOCALIDAD_ID]);
        tempP.setProvincia(datos[C_Persona.PROVINCIA_ID]);
        tempP.setCP(datos[C_Persona.CP_ID]);
        tempP.setTelefonoFijo(datos[C_Persona.TELEFONO1_ID]);
        tempP.setTelefonoMovil(datos[C_Persona.TELEFONO2_ID]);

        boolean resulto = false;
        try {
            resulto = C_PersonaJDBC.getInstance().añadirC_Persona(tempP);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resulto;
    }

    public C_Persona obtenerC_Persona (String DNI) {
        C_Persona tempP;

        try {
            tempP = C_PersonaJDBC.getInstance().obtenerC_Persona(DNI);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
            tempP = null;
        }

        return tempP;
    }

    public boolean modificarC_Persona (String[] datos) {
        if (!ComprobarDatos(datos))
            return false;

        C_Persona tempP = new C_Persona();

        tempP.setDNI(datos[C_Persona.DNI_ID]);
        tempP.setNombre(datos[C_Persona.NOMBRE_ID]);
        tempP.setApellidos(datos[C_Persona.APELLIDOS_ID]);
        tempP.setSexo(datos[C_Persona.SEXO_ID].charAt(0));
        tempP.setFechaDeNacimiento(Date.valueOf(datos[C_Persona.FECHA_DE_NACIMIENTO_ID]));

        tempP.setEmail(datos[C_Persona.EMAIL_ID]);
        tempP.setDireccion(datos[C_Persona.DIRECCION_ID]);
        tempP.setLocalidad(datos[C_Persona.LOCALIDAD_ID]);
        tempP.setProvincia(datos[C_Persona.PROVINCIA_ID]);
        tempP.setCP(datos[C_Persona.CP_ID]);
        tempP.setTelefonoFijo(datos[C_Persona.TELEFONO1_ID]);
        tempP.setTelefonoMovil(datos[C_Persona.TELEFONO2_ID]);

        boolean resulto = false;
        try {
            resulto = C_PersonaJDBC.getInstance().modificarDatosC_Persona(tempP);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resulto;
    }

    public boolean eliminarC_Persona (C_Persona tempP) {
        boolean resulto = false;

        try {
            resulto = C_PersonaJDBC.getInstance().eliminarC_Persona(tempP);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resulto;
    }

    public ArrayList<C_Persona> buscarC_Persona (String tipoBusqueta, String valor) {
        ArrayList<C_Persona> personas;
        try {
            personas = C_PersonaJDBC.getInstance().buscarC_Persona(tipoBusqueta, valor);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return personas;
    }

    public boolean cambiarC_PersonaSocio(C_Persona tempP, String usuarioN) {

        // TODO validar usuario

        // if usuarioValido
        String contrasena = ControladorVoluntario.genContrasena();

        boolean resulto = false;

        try {
            C_PersonaJDBC.getInstance().hacerSocio(tempP, usuarioN, contrasena);
            resulto = true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resulto;
    }

    // proba los datos pero no sabemos cual son los datos que no son correcto
    private boolean ComprobarDatos (String[] datos) {
        // cada campo debe ser not null
        for (int i=0; i<datos.length; i++) {
            if (datos[i].length() < 1)
                return false;
        }

        if (!TestDatos.isDNI(datos[C_Persona.DNI_ID]))
            return false;

        if (!TestDatos.isCodigoPostal(datos[C_Persona.CP_ID]))
            return false;

        if (!TestDatos.isEmail(datos[C_Persona.EMAIL_ID]))
            return false;

        if (!TestDatos.isOnlyLetter(datos[C_Persona.NOMBRE_ID]))
            return false;

        if (!TestDatos.isOnlyLetter(datos[C_Persona.APELLIDOS_ID]))
            return false;

        if (!TestDatos.isOnlyLetter(datos[C_Persona.DIRECCION_ID]))
            return false;

        if (!TestDatos.isOnlyLetter(datos[C_Persona.LOCALIDAD_ID]))
            return false;

        if (!TestDatos.isOnlyLetter(datos[C_Persona.PROVINCIA_ID]))
            return false;

        return true;
    }
}
