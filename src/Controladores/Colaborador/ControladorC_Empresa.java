
package Controladores.Colaborador;

import Controladores.TestDatos;
import Controladores.Voluntario.ControladorColaboracion;
import JDBC.C_EmpresaJDBC;
import Modelo.C_Empresa;
import Modelo.Colaboracion;
import Vistas.Paneles.Colaboradores.VistaColaboradores;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 ** NOMBRE CLASE: *	ControladorC_Empresa
 *
 * DESCRIPCION:
 *      Controlador del panel de coladores de typo empresa
 *
 *
 * DESARROLLADO POR:
 *      Raphael Colleau (RC)
 *      Francisco Jose Legaza Bailón (PLB)
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

    
    public static ControladorC_Empresa getInstance(VistaColaboradores pvista) {

        if (instancia == null) {
            instancia = new ControladorC_Empresa(pvista);
        }
        return instancia;

    }

    

    C_Empresa C_Empresa_temp;
    
    Colaboracion Colaboracion_temp;
    
    private VistaColaboradores vista;

    public ControladorC_Empresa(VistaColaboradores pvista) {
        this.vista = pvista;
        
        vista.getPanelEmpresaInicio().anadirListenerbtNuevaEmpresa(new btNuevaEmpresaListener());
        vista.getPanelEmpresaInicio().anadirListenerbtBuscarEmpresa(new btBuscadorEmpresaListener());
        
        vista.getPanelEmpresaDatos().getBtGuardarEmpresa().addActionListener(new btGuardarDatosEmpresaListener());
        vista.getPanelEmpresaDatos().getBtLimpiarEmpresa().addActionListener(new btLimpiarDatosEmpresaListener());
        vista.getPanelEmpresaDatos().getBtGuardarColaboracionesEmpresa().addActionListener(new btGuardarColaboracionesEmpresaListener());
        vista.getPanelEmpresaDatos().getBtEliminarColaboracionesEmpresa().addActionListener(new btEliminarColaboracionesEmpresaListener());
        vista.getPanelEmpresaDatos().getBtBuscarColaboracionesEmpresa().addActionListener(new btBuscarColaboracionesEmpresaListener());
        
        vista.getPanelEmpresaBuscar().getBtBuscarEmpresa().addActionListener(new btBuscarEmpresaListener());
        vista.getPanelEmpresaBuscar().getBtConsultarEmpresa().addActionListener(new btConsultarEmpresaListener());
        vista.getPanelEmpresaBuscar().getBtEliminarEmpresa().addActionListener(new btEliminarEmpresaListener());
        
        anadirKeyListener();
        
        // mostrarVistaInicio();
    }

    
    /**
     * 
     * @param datos
     * @return 
     */
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
        tempE.setTelefonoFijo(datos[C_Empresa.TELEFONO1_ID]);
        tempE.setTelefonoMovil(datos[C_Empresa.TELEFONO2_ID]);

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
    
    /**
     * 
     * @param CIF
     * @return 
     */
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

    /**
     * 
     * @param datos
     * @return 
     */
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
        tempE.setTelefonoFijo(datos[C_Empresa.TELEFONO1_ID]);
        tempE.setTelefonoMovil(datos[C_Empresa.TELEFONO2_ID]);

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

    /**
     * 
     * @param tempE
     * @return 
     */
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

    /**
     * 
     * @param tipoBusqueta
     * @param valor
     * @return 
     */
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

    /**
     * 
     * @param datos
     * @return 
     */
    private boolean ComprobarDatos (String[] datos) {
        
        vista.getPanelEmpresaDatos().setColorLabels(Color.black);

        if (!TestDatos.isCIF(datos[C_Empresa.CIF_ID])){
            vista.getPanelEmpresaDatos().setColorLabelCIF(Color.red);
            return false;
        }

        if (!TestDatos.isCodigoPostal(datos[C_Empresa.CP_ID])){
            vista.getPanelEmpresaDatos().setColorLabelCP(Color.red);
            return false;
        }

        if (!TestDatos.isEmail(datos[C_Empresa.EMAIL_ID])){
            vista.getPanelEmpresaDatos().setColorLabelEmail(Color.red);
            return false;
        }

        if (!TestDatos.isTelefonoOFax(datos[C_Empresa.TELEFONO1_ID])){
            vista.getPanelEmpresaDatos().setColorLabelTelefonoFijo(Color.red);
            return false;
        }
        
        if (!TestDatos.isTelefonoOFax(datos[C_Empresa.TELEFONO2_ID])){
            vista.getPanelEmpresaDatos().setColorLabelTelefonoMovil(Color.red);
            return false;
        }

        if (!TestDatos.isOnlyLetter(datos[C_Empresa.NOMBRE_ID])){
            vista.getPanelEmpresaDatos().setColorLabelNombre(Color.red);
            return false;
        }

        if (!TestDatos.isOnlyLetter(datos[C_Empresa.DIRECCION_ID])){
            vista.getPanelEmpresaDatos().setColorLabelDireccion(Color.red);
            return false;
        }
        
        if (!TestDatos.isOnlyLetter(datos[C_Empresa.DIRECCION_WEB_ID])){
            vista.getPanelEmpresaDatos().setColorLabelDireccion(Color.red);
            return false;
        }

        if (!TestDatos.isOnlyLetter(datos[C_Empresa.LOCALIDAD_ID])){
            vista.getPanelEmpresaDatos().setColorLabelLocalidad(Color.red);
            return false;
        }

        if (!TestDatos.isOnlyLetter(datos[C_Empresa.PROVINCIA_ID])){
            vista.getPanelEmpresaDatos().setColorLabelProvincia(Color.red);
            return false;
        }

        if (datos[C_Empresa.TELEFONO2_ID].length() > 0 && !TestDatos.isTelefonoOFax(datos[C_Empresa.TELEFONO2_ID]))
            return false;

        if (datos[C_Empresa.FAX_ID].length() > 0 && !TestDatos.isTelefonoOFax(datos[C_Empresa.FAX_ID]))
            return false;

        return true;
    }

    private void anadirKeyListener() {
        
        
    }
    
    /* BOTONES PANEL_EMPRESA_INICIO */
    class btNuevaEmpresaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
    
    class btBuscadorEmpresaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
    
    /* BOTONES PANEL_EMPRESA_DATOS */
    class btBuscarColaboracionesEmpresaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) { 
        }
    }

    class btEliminarColaboracionesEmpresaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            vista.getPanelEmpresaDatos().getLabelErrorColaboraciones().setVisible(false);
            if (vista.getPanelEmpresaDatos().getTbColaboracionesEmpresa().getSelectedRow() != -1) {
                if (JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar la colaboracion?", "Eliminar Colaboracion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (ControladorColaboracion.getInstance().eliminarColaboracion(Colaboracion_temp)) {
                        vista.getPanelEmpresaDatos().setTextLabelErrorColaboraciones("La colaboracion ha sido eliminada del sistema.");
                    } else {
                        vista.getPanelEmpresaDatos().setTextLabelErrorColaboraciones("Error : la colaboracion no ha sido eliminada del sistema.");
                    }
                }
            } else {
                vista.getPanelEmpresaDatos().setTextLabelErrorColaboraciones("Selecciona una ayuda.");
            }
        }
    }

    class btGuardarColaboracionesEmpresaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            boolean datosValidos=true;
            
            if(vista.getPanelEmpresaDatos().getTextImporte().getText().equals("")){
                vista.getPanelEmpresaDatos().setColorLabelImporte(Color.red);
                datosValidos = false;
            }
            
            if(vista.getPanelEmpresaDatos().getTextConcepto().getText().equals("")){
                vista.getPanelEmpresaDatos().setColorLabelConcepto(Color.red);
                datosValidos = false;
            }
            
            if(vista.getPanelEmpresaDatos().getTextFecha().getText().equals("")){
                vista.getPanelEmpresaDatos().setColorLabelFecha(Color.red);
                datosValidos = false;
            }
            
            if(!datosValidos){
                vista.getPanelEmpresaDatos().setTextLabelErrorColaboraciones("Los campos en rojo están mal");
            }else{
                if(Colaboracion_temp==null){
                    Colaboracion c = new Colaboracion();
                    c.setImporte(Float.parseFloat(vista.getPanelEmpresaDatos().getTextImporte().getText()));
                    c.setConcepto(vista.getPanelEmpresaDatos().getTextConcepto().getText());
                    c.setFecha(new Date());
                    
                    boolean exito = ControladorColaboracion.getInstance().anadirColaboracion(c);
                    if (exito)
                        vista.getPanelEmpresaDatos().setTextLabelErrorColaboraciones("Colaboración añadida");
                    else
                        vista.getPanelEmpresaDatos().setTextLabelErrorColaboraciones("Colaboracion no añadida");
                }else{
                    Colaboracion_temp.setImporte(Float.parseFloat(vista.getPanelEmpresaDatos().getTextImporte().getText()));
                    Colaboracion_temp.setConcepto(vista.getPanelEmpresaDatos().getTextConcepto().getText());
                    Colaboracion_temp.setFecha(new Date());
                    
                    boolean exito = ControladorColaboracion.getInstance().anadirColaboracion(Colaboracion_temp);
                    if (exito)
                        vista.getPanelEmpresaDatos().setTextLabelErrorColaboraciones("Colaboración modificada");
                    else
                        vista.getPanelEmpresaDatos().setTextLabelErrorColaboraciones("Colaboracion no modificada");
                }
            }
            
        }
    }

    class btLimpiarDatosEmpresaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            vista.getPanelEmpresaDatos().limpiarCampos();
        }
    }

    class btGuardarDatosEmpresaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            String[] datos = vista.getPanelEmpresaDatos().getDatosPersonales();
            if (C_Empresa_temp != null) {
                
                boolean exito = modificarC_Empresa(datos);
                if (exito) 
                    vista.getPanelEmpresaDatos().setTextLabelError("Beneficiario modificado correctamente.");
                else 
                    vista.getPanelEmpresaDatos().setTextLabelError("El beneficiario no ha sido modificado.");
                
            } else {
                
                boolean exito = anadirC_Empresa(datos);
                if (exito)
                    vista.getPanelEmpresaDatos().setTextLabelError("Beneficiario añadido correctamente.");
                else
                    vista.getPanelEmpresaDatos().setTextLabelError("El beneficiario no ha sido añadido.");   
            }
   
        }
    }

    /* BOTONES PANEL_EMPRESA_BUSCAR */
    class btConsultarEmpresaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    class btEliminarEmpresaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {            
        }
    }
    
    class btBuscarEmpresaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
}
