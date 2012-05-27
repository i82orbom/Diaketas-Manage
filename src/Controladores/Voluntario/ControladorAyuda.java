
package Controladores.Voluntario;

import JDBC.AyudaJDBC;
import JDBC.MovimientoJDBC;
import Modelo.Ayuda;
import Modelo.TipoAyuda;
import Vistas.Paneles.Voluntario.PanelVoluntarioAyudas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 ** NOMBRE CLASE:
 **	  ControladorAyuda
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
 ** 	000 - 16 mai 2012 - RC - Creacion
 ** 	001 - 23 mai 2012 - RC - Adicion metodos relacionados con modelo
 **
 ** NOTAS:
 **
 **
 */
public class ControladorAyuda {

    /**
     * PATRON DE DISEÑO SINGLETON
     */
    private static ControladorAyuda instancia = null;

    public static ControladorAyuda getInstance(PanelVoluntarioAyudas pvista) {

        if (instancia == null) {
            instancia = new ControladorAyuda(pvista);
        }
        return instancia;
    }
    
    private PanelVoluntarioAyudas vista;

    public ControladorAyuda(PanelVoluntarioAyudas vista) {
        this.vista = vista;
        
        this.vista.getBtnGuardarTipoAyuda().addActionListener(new BtGuardarTipoAyudaListener());
    }

    // TODO Metodos JDBC
    private boolean registrarAyuda(Ayuda ayuda) {
        boolean exito;
        
            // TODO test ayuda.getBeneficiarioDeAyuda().getNIF() exite
            // TODO test datos
        try {
            exito = AyudaJDBC.getInstance().registrarDatosAyuda(ayuda, ayuda.getBeneficiarioDeAyuda().getNIF(), ayuda.getVoluntarioQueOtorga().getNIF());
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        if (ayuda.getTipo_ayuda().isMonetaria()) {
            exito = ControladorContabilidad.getInstance(null).registrarGastoAyuda(ayuda.getImporte(), ayuda.getObservaciones(), ayuda);
        }
        
        return exito;
    }
    
    private boolean datosNuevoTipoAyuda (TipoAyuda tipoAyuda) {
        boolean exito = false;
        
        boolean tipoAyudaExiste = true;
        try {   
            tipoAyudaExiste = AyudaJDBC.getInstance().comprobarTipoAyuda(tipoAyuda.getTitulo());
        } catch (SQLException ex) {
            // TODO pop up error
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!tipoAyudaExiste) {
            try {
                exito = AyudaJDBC.getInstance().insertarTipoAyuda(tipoAyuda);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return exito;
    }
    
    public ArrayList<TipoAyuda> obtenerTiposAyuda () {
        ArrayList<TipoAyuda> tiposAyuda;
        try {
            tiposAyuda = AyudaJDBC.getInstance().obtenerDatosTipoAyuda();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return tiposAyuda;
    }
    
    private boolean modificarAyuda (Ayuda ayuda) {
        boolean exito = false;
        
            // TODO test si tipoayuda existe
        try {   
            exito = AyudaJDBC.getInstance().modificarDatosAyuda(ayuda, ayuda.getVoluntarioQueOtorga());
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exito;
    }
    
    private boolean modificarTypoAyuda (TipoAyuda tipoAyuda) {
        boolean exito = false;
        
        try {    
            exito = AyudaJDBC.getInstance().modificarDatosTipoAyuda(tipoAyuda);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exito;
    }

    private boolean eliminarAyuda(Ayuda ayuda) {
        boolean exito = false;

        try {
            // TODO jdbc ayuda
            exito = AyudaJDBC.getInstance().eliminarDatosAyuda(ayuda.getOID());
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exito;
    }

    private boolean eliminarTipoAyuda(TipoAyuda tipoAyuda) {
        boolean exito = false;
        try {
            exito = AyudaJDBC.getInstance().eliminarDatosTipoAyuda(tipoAyuda.getOID());
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAyuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exito;
    }
    // TODO Listeners de los botones
    
    class BtGuardarTipoAyudaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean datosCorrectos = true;
            
            
            if (vista.getTextTipoAyuda().getText().equals("")) {
                datosCorrectos = false;
            }
            
            if (vista.getDescripcionTipoAyuda().getText().equals("")) {
                datosCorrectos = false;
            }
            
            if (datosCorrectos) {
                TipoAyuda tipoAyuda = new TipoAyuda();
                tipoAyuda.setTitulo(vista.getTextTipoAyuda().getText());
                tipoAyuda.setDescripcion(vista.getDescripcionTipoAyuda().getText());
                tipoAyuda.setMonetaria(vista.getIsMonetaria().isSelected());
                
                if (datosNuevoTipoAyuda(tipoAyuda)) {
                    System.out.println("tipoAyuda anadido");
                }
            }
        }
    }
    
    class BtGuardarAyudaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
}
