
package Controladores.Voluntario;

import JDBC.ColaboracionJDBC;
import Modelo.Colaboracion;
import Modelo.Colaborador;
import Vistas.Paneles.Socio.VistaSocio;
import Vistas.Paneles.Voluntario.VistaVoluntario;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 ** NOMBRE CLASE:
 **	  ControladorColaborador
 **
 ** DESCRIPCION:
 **
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
 *      002 - 5 mai 2012 - RC - relleno de metodos
 **
 ** NOTAS:
 **
 **
 */
public class ControladorColaboracion {

    /** PATRON DE DISEÑO SINGLETON */

    private static ControladorColaboracion instancia;

    
    public static ControladorColaboracion getInstance(){
        if (instancia == null)
            instancia = new ControladorColaboracion();

        return instancia;

    }
    //private VistaVoluntario vistaVoluntario;
    //private VistaSocio vistaSocio;
	private ControladorColaboracion () {}

    public boolean anadirColaboracion (Colaboracion colaboracion){
		boolean exito=true;
        try {
            exito=ColaboracionJDBC.getInstance().añadirColaboracion(colaboracion);
        } catch (SQLException ex) {
			exito=false;
            Logger.getLogger(ControladorColaboracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exito;
    }

    public boolean eliminarColaboracion (Colaboracion c) {
        try {
            return ColaboracionJDBC.getInstance().eliminarColaboracion(c);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorColaboracion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public ArrayList<Colaboracion> historialColaboraciones (Colaborador c, Date fechaInicio, Date fechaFin) {
        try {
            return ColaboracionJDBC.getInstance().HistorialColaboraciones(c, fechaInicio, fechaFin);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorColaboracion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
