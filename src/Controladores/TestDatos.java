
package Controladores;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 ** NOMBRE CLASE:
 **	  TestDatos
 **
 ** DESCRIPCION:
 **       Para comprobar los datos de entrada
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
 ** 	000 -  4 May 2012 - RC - Creacion + metodos
 ** 	001 -  5 May 2012 - RC - Adicion metodos
 *	002 - 20 May 2012 - ARS - Metodos para nombre, direccion y fecha
 **
 ** NOTAS:
 **
 **
 */
public class TestDatos {

    /**
     * Formato de la fecha para formularios
     */
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * Formato de la fecha para la base de datos
     */
    public static SimpleDateFormat formatterBD = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean isDNI(String DNI) {
        if (DNI.length() != 9) {
            return false;
        }

        for (int i = 0; i < 8; i++) {
            if (Character.isLetter(DNI.charAt(i))) {
                return false;
            }
        }

        if (!Character.isLetter(DNI.charAt(8))) {
            return false;
        }

        return true;
    }

    public static boolean isCIF(String CIF) {
        if (CIF.length() != 9) {
            return false;
        }

        if (!Character.isLetter(CIF.charAt(0))) {
            return false;
        }

        for (int i = 1; i < 8; i++) {
            if (!Character.isDigit(CIF.charAt(i))) {
                return false;
            }
        }

        if (!Character.isLetterOrDigit(CIF.charAt(8))) {
            return false;
        }

        return true;
    }

    public static boolean isTelefonoOFax(String numero) {
        if (numero.length() < 9 || numero.length()>15) {
            return false;
        }

        for (int i = 0; i < numero.length(); i++) {
            if (!Character.isDigit(numero.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isCodigoPostal(String cp) {
        if (cp.length() != 5) {
            return false;
        }

        for (int i = 0; i < cp.length(); i++) {
            if (!Character.isDigit(cp.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isEmail(String email) {
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());
        return m.matches();
    }

    public static boolean isDireccionWeb(String d){
        for(int i=0; i<d.length(); i++){
            if(!Character.isLetterOrDigit(d.charAt(i)) && d.charAt(i) != '.'){
                return false;
            }
        }
        return true;
    }
    
    public static boolean isOnlyLetter(String s) {
        // Definición isLetter de Unicode: [\\p{IsL}]
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetter(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOnlyDigit(String s) {
        if (s.length() < 1) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOnlyLetterOrDigit(String s) {
        if (s.length() < 1) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetterOrDigit(s.charAt(i)) && s.charAt(i) != ',' && s.charAt(i) != '.') {
                return false;
            }
        }
        return true;
    }

    /**
     * Comprueba si la cadena tiene el formato de un nombre, incluyendo letras
     * (incluyendo caracteres especiales) y guiones.
     *
     * @param s cadena a comprobar
     * @return True si la cadena es válida
     */
    public static boolean isNombre(String s) {
        Pattern p = Pattern.compile("[[\\p{IsL}] -]+");
        Matcher m = p.matcher(s.toUpperCase());
        return m.matches();
    }

    /**
     * Comprueba si la cadena tiene formato para ser una direccion. Esto incluye
     * letras, numeros, comas, puntos, guines y /
     *
     * @param s cadena a comprobar
     * @return True si la cadena es válida
     */
    public static boolean isDomicilio(String s) {
        Pattern p = Pattern.compile("[[\\p{IsL}]0-9 /,.-]+");
        Matcher m = p.matcher(s.toUpperCase());
        return m.matches();
    }

    /**
     * Comprueba que la cadena es una fecha con el formato dia/mes/año,
     * representando el día y el mes con 2 digitos y el año con 4 digitos
     *
     * @param s cadena a comprobar
     * @return True si la cadena es una fecha válida
     */
    public static boolean isFecha(String fecha) {
        Pattern p = Pattern.compile("\\d\\d/\\d\\d/\\d\\d\\d\\d$");
        Matcher m = p.matcher(fecha.toUpperCase());
        return m.matches();
    }

    public static boolean isMoney(String numero) {
        int num_Comas = 0;
        boolean correcto = true;

        for (int i = 0; i < numero.length(); i++) {
            if (!Character.isDigit(numero.charAt(i)) && numero.charAt(i) != ',' && numero.charAt(i) != '.') {
                correcto = false;
            }
            if (numero.charAt(i) == ',' || numero.charAt(i) == '.') {
                num_Comas++;
            }
        }
        if (num_Comas > 1 && correcto) {
            correcto = false;
        }

        return correcto;
    }

}
