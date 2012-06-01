
package Controladores.Colaborador;

import Controladores.TestDatos;
import JDBC.C_EmpresaJDBC;
import Modelo.C_Empresa;
import Vistas.Paneles.Colaboradores.VistaColaboradores;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    private VistaColaboradores vista;

    public ControladorC_Empresa(VistaColaboradores pvista) {
        this.vista = pvista;
        
        vista.getPanelEmpresaInicio().anadirListenerbtNuevoEmpresa(new btNuevaEmpresaListener());
        vista.getPanelEmpresaInicio().anadirListenerbtBuscarEmpresa(new btBuscarEmpresaListener());
        
        vista.getPanelEmpresaDatos().getBtGuardarEmpresa().addActionListener(new btGuardarDatosEmpresaListener());
        vista.getPanelEmpresaDatos().getBtLimpiarEmpresa().addActionListener(new btLimpiarDatosEmpresaListener());
        vista.getPanelEmpresaDatos().getBtGuardarColaboracionesEmpresa().addActionListener(new btGuardarColaboracionesEmpresaListener());
        vista.getPanelEmpresaDatos().getBtEliminarColaboracionesEmpresa().addActionListener(new btEliminarColaboracionesEmpresaListener());
        vista.getPanelEmpresaDatos().getBtBuscarColaboracionesEmpresa().addActionListener(new btBuscarColaboracionesEmpresaListener());
        
        vista.getPanelEmpresaBuscar().getBtBuscarEmpresa().addActionListener(new btBuscadorEmpresaListener());
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
    
    class btBuscarColaboracionesEmpresaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    class btEliminarColaboracionesEmpresaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    class btGuardarColaboracionesEmpresaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
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
            
            
            
            
            
            
        }
    }

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
    
    class btBuscadorEmpresaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    class btNuevaEmpresaListener implements ActionListener {

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
