
package Controladores.Voluntario;

import Controladores.TestDatos;
import JDBC.ColaboracionJDBC;
import Modelo.Colaboracion;
import Modelo.Colaborador;
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

    /*
    public static ControladorColaboracion getInstance(PanelVoluntarioColaboracion pvista){
        if (instancia == null)
            instancia = new ControladorColaboracion(pvista);

        return instancia;

    }

    private PanelVoluntarioColaboracion vista;

    public ControladorColaboracion(PanelVoluntarioColaboracion vista) {
        this.vista = vista;
    }*/

    public Colaboracion anadirColaboracion (Colaboracion colaboracion){

        Colaboracion c = new Colaboracion();

        c.setConcepto(colaboracion.getConcepto());
        c.setFecha(colaboracion.getFecha());
        c.setImporte(colaboracion.getImporte());


        try {
            ColaboracionJDBC.getInstance().añadirColaboracion(c);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorColaboracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
