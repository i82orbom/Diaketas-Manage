
package Controladores;

import javax.swing.JOptionPane;

/**
 *
 * @author Antonio
 */
public class ControladorErrores {

	public static void mostrarMensaje(String mensaje){
		JOptionPane.showMessageDialog(null, mensaje);
	}

	public static void mostrarAlerta(String mensaje){
		JOptionPane.showMessageDialog(null, mensaje, "Alerta", JOptionPane.WARNING_MESSAGE);
	}

	public static void mostrarError(String mensaje){
		JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
