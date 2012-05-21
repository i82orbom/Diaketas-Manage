/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores.Colaborador;

import Controladores.ControladorPrincipal;
import Controladores.TestDatos;
import Controladores.Voluntario.ControladorVoluntario;
import JDBC.ColaboracionJDBC;
import JDBC.CuotaJDBC;
import JDBC.PagoCuotaJDBC;
import JDBC.SocioJDBC;
import Modelo.*;
import Vistas.BarraDeNavegacion;
import Vistas.Paneles.Socio.VistaSocio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 ** NOMBRE CLASE: 
 **	  ControladorSocio
 **
 ** DESCRIPCION:
 **       Controlador del panel de coladores de typo socio
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
 ** 	000 - 1 mai 2012 - RC - Creacion y estructura
 ** 	001 - 4 mai 2012 - RC - Metodos con JDBC
 *      002 - 5 mai 2012 - RC - comproba datos
 **
 ** NOTAS:
 **   
 **
 */
public class ControladorSocio{

    /**
     * PATRON DE DISEÑO SINGLETON
     */
    private static ControladorSocio instancia = null;

    // TODO vista
    public static ControladorSocio getInstance(VistaSocio pvista) {

        if (instancia == null) {
            instancia = new ControladorSocio(pvista);
        }
        return instancia;

    }
    
    private VistaSocio vista;


    public ControladorSocio(VistaSocio pvista) {
        vista = pvista;

        vista.getPanelSocioDatos().getBtGuardarDatosSocio().addActionListener(new btGuardarDatosSocioListener());
    }
 
   
    public boolean anadirSocio(String[] datos) {
        if (!comprobarDatos(datos))
            return false;
        
        Socio socio = new Socio();
        socio.setUsuario(datos[Socio.USUARIO_ID]);
        // cryptar password en MD5
        socio.setContrasena(ControladorVoluntario.genContrasena());
        
        socio.setDNI(datos[Socio.DNI_ID]);
        socio.setNombre(datos[Socio.NOMBRE_ID]);
        socio.setApellidos(datos[Socio.APELLIDOS_ID]);
        socio.setSexo(datos[Socio.SEXO_ID].charAt(0));
        socio.setFechaDeNacimiento(Date.valueOf(datos[Socio.FECHA_DE_NACIMIENTO_ID]));
            
        socio.setEmail(datos[Socio.EMAIL_ID]);
        socio.setDireccion(datos[Socio.DIRECCION_ID]);
        socio.setLocalidad(datos[Socio.LOCALIDAD_ID]);
        socio.setProvincia(datos[Socio.PROVINCIA_ID]);
        socio.setCP(datos[Socio.CP_ID]);
        
        boolean exito;
        try {
             exito = SocioJDBC.getInstance().añadirSocio(socio);
        } catch (SQLException ex) {
            exito = false;
            Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exito;
    }
    
    public Socio obtenerSocio (String DNI) {
        Socio socio = null;
        try {
            socio = SocioJDBC.getInstance().obtenerSocio(DNI);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return socio;
    }
    
    public boolean modificarSocio (String[] datos) {
        if (!comprobarDatos(datos))
            return false;
        
        // TODO test password MD5
                
        Socio socio = new Socio();
        socio.setUsuario(datos[Socio.USUARIO_ID]);
        
        socio.setDNI(datos[Socio.DNI_ID]);
        socio.setNombre(datos[Socio.NOMBRE_ID]);
        socio.setApellidos(datos[Socio.APELLIDOS_ID]);
        socio.setSexo(datos[Socio.SEXO_ID].charAt(0));
        socio.setFechaDeNacimiento(Date.valueOf(datos[Socio.FECHA_DE_NACIMIENTO_ID]));
            
        socio.setEmail(datos[Socio.EMAIL_ID]);
        socio.setDireccion(datos[Socio.DIRECCION_ID]);
        socio.setLocalidad(datos[Socio.LOCALIDAD_ID]);
        socio.setProvincia(datos[Socio.PROVINCIA_ID]);
        socio.setCP(datos[Socio.CP_ID]);
        
        boolean exito;
        try {
             exito = SocioJDBC.getInstance().modificarDatosSocio(socio);
        } catch (SQLException ex) {
            exito = false;
            Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exito;
    }
    
    public boolean eliminarSocio (Socio socio) {
        try {
            return SocioJDBC.getInstance().eliminarSocio(socio);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public ArrayList<Socio> buscarSocio (String tipoBusqueta, String valor) {
        ArrayList<Socio> socios;
        try {
            socios = SocioJDBC.getInstance().obtenerListadoSocios(tipoBusqueta, valor);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorC_Persona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return socios;
    }
    
    public boolean cancelarCuota (Cuota c) {
        if (c.getFechaUltimoPago() == c.getFechaInicio()) {
            try {
                return CuotaJDBC.getInstance().eliminarCuota(c);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                return CuotaJDBC.getInstance().cancelarCuota(c);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public ArrayList<Movimiento> historialPagosCuotas (Socio socio, Date fechaInicio, Date fechaFin) {
        ArrayList<PagoCuota> pagosCuotas = null;
        
        try {
            pagosCuotas = PagoCuotaJDBC.getInstance().HistorialPagosCuotas(socio, fechaInicio, fechaFin);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (pagosCuotas == null)
            return null;
        
        ArrayList<Colaboracion> pagosColaboraciones = null;
        
        try {
            pagosColaboraciones = ColaboracionJDBC.getInstance().HistorialColaboraciones(socio, fechaInicio, fechaFin);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (pagosColaboraciones == null)
            return null;
        
        ArrayList<Movimiento> pagos = new ArrayList<Movimiento>();
        pagos.addAll(pagosCuotas);
        pagos.addAll(pagosColaboraciones);
        
        return pagos;
    }
    
    // proba los datos pero no sabemos cual son los datos que no son correcto
    private boolean comprobarDatos (String[] datos) {
        // cada campo debe ser not null
        for (int i=0; i<datos.length; i++) {
            if (datos[i].length() < 1)
                return false;
        }
        
        if (!TestDatos.isDNI(datos[Socio.DNI_ID]))
            return false;
        
        if (!TestDatos.isCodigoPostal(datos[Socio.CP_ID]))
            return false;
        
        if (!TestDatos.isEmail(datos[Socio.EMAIL_ID]))
            return false;
        
        if (!TestDatos.isOnlyLetter(datos[Socio.NOMBRE_ID]))
            return false;
        
        if (!TestDatos.isOnlyLetter(datos[Socio.APELLIDOS_ID]))
            return false;
        
        if (!TestDatos.isOnlyLetter(datos[Socio.DIRECCION_ID]))
            return false;
        
        if (!TestDatos.isOnlyLetter(datos[Socio.LOCALIDAD_ID]))
            return false;
        
        if (!TestDatos.isOnlyLetter(datos[Socio.PROVINCIA_ID]))
            return false;
        
        if (!TestDatos.isOnlyLetterOrDigit(datos[Socio.USUARIO_ID]))
            return false;
        
        return true;
    }
       
    /**
        * Listener controlador del boton Guardar datos socio
    */
    public class btGuardarDatosSocioListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                    System.out.println("btguardar");
                    String[] datos = new String[14];
                    
                    datos[Socio.NOMBRE_ID] = vista.getPanelSocioDatos().obtenerNombreSocio();
                    datos[Socio.APELLIDOS_ID] = vista.getPanelSocioDatos().obtenerApellidosSocio();
                    datos[Socio.EMAIL_ID] = vista.getPanelSocioDatos().obtenerCorreoSocio();
                    datos[Socio.DNI_ID] = vista.getPanelSocioDatos().obtenerDNISocio();
                    datos[Socio.SEXO_ID] = vista.getPanelSocioDatos().obtenerSexoSocio();
                    try {
                        datos[Socio.FECHA_DE_NACIMIENTO_ID] = vista.getPanelSocioDatos().obtenerFNSocio();
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorSocio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    datos[Socio.DIRECCION_ID] = vista.getPanelSocioDatos().obtenerDomicilioSocio();
                    datos[Socio.LOCALIDAD_ID] = vista.getPanelSocioDatos().obtenerLocalidadSocio();
                    datos[Socio.PROVINCIA_ID] = vista.getPanelSocioDatos().obtenerProvinciaSocio();
                    datos[Socio.CP_ID] = vista.getPanelSocioDatos().obtenerCPSocio();
                    datos[Socio.TEL1_ID] = vista.getPanelSocioDatos().obtenerTelfSocio();
                    datos[Socio.TEL2_ID] = vista.getPanelSocioDatos().obtenerMovilSocio();
                    datos[Socio.USUARIO_ID] = vista.getPanelSocioDatos().obtenerUsuarioSocio();
                    
                    String password = ControladorPrincipal.getInstance().md5(vista.getPanelSocioDatos().obtenerDNISocio()+ControladorPrincipal.getInstance().getSalto());
                    datos[Socio.CONTRASENA_ID] = password;
                    
                    boolean exito = anadirSocio(datos);
                    if (exito) {
                            vista.getPanelSocioDatos().setTextLabelError("Socio añadido correctamente.");
                    } else {
                            vista.getPanelSocioDatos().setTextLabelError("El Socio no ha sido añadido.");
                    }
              }        
        }
        
        private boolean testDatos(Socio socio) {

            //comporbar que los elementos de un socio no son nulos
                if(socio.getNombre().equals("") || socio.getApellidos().equals("")|| socio.getDNI().equals("")|| socio.getDireccion().equals("")|| socio.getProvincia().equals("")|| socio.getLocalidad().equals("")|| socio.getCP().equals("") || socio.getUsuario().equals(""))
                    return false;  
                
                if (!TestDatos.isDNI(socio.getDNI()))
                    return false;

                if (!TestDatos.isCodigoPostal(socio.getCP()))
                    return false;
                
                if (!TestDatos.isOnlyLetter(socio.getNombre()))
                    return false;
                
                if (!TestDatos.isOnlyLetter(socio.getApellidos()))
                    return false;
                
                if (!TestDatos.isOnlyLetter(socio.getDireccion()))
                    return false;

                if (!TestDatos.isOnlyLetter(socio.getLocalidad()))
                    return false;
                
                if (!TestDatos.isOnlyLetter(socio.getProvincia()))
                    return false;

                if (!TestDatos.isOnlyLetterOrDigit(socio.getUsuario()))
                    return false;

                return true;
        }
        
        public class ListenerBtLimpiarDatosSocio implements ActionListener{
                String[] datos;
                @Override
                public void actionPerformed(ActionEvent e) {
                    datos = new String[19];
                    for(int i=0; i<19; i++)
                      datos[i]="";
                      //vista.escribirDatosGeneralesSocioDatos(datos);
                }
        }
}
