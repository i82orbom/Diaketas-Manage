
/**
 ** NOMBRE CLASE:
 **	  DriverJDBC.java
 **
 ** DESCRIPCION:
 **       Driver de comunicación con JDBC
 **
 **
 ** DESARROLLADO POR:
 *        Francisco José Beltrán Rodriguez (FBR)
 *
 **
 ** SUPERVISADO POR:
 **        Adolfo Arcoya Nieto (AAN)
 *		   Antonio Rodríguez Segura (ARS)
 **
 ** HISTORIA:
 ** 	000 - Mar 24, 2012 - FBR - Creacion
 **     001 - Mar 25, 2012 - FBR - Implementación de los métodos
 *      002 - Mar 26, 2012 - FBR - Modificación de diversos métodos
 **     003 - Mar 26, 2012 - FBR - Modificación del constructor de la clase, getInstance ahora es public
 *		004 - May 21, 2012 - ARS - Añadidos métodos para transacciones
 **
 ** NOTAS:
 **
 **
 */
package JDBC;

import java.sql.*;

public class DriverJDBC {
    private static DriverJDBC instancia;
    private Connection conexion;
    private String hostBD;
    private String nombreBD;
    private String password;
    private String usuarioBD;
    private Statement statement;

	// Variables para restaurar tras transacciones
	private boolean oldEstadoAutoCommit;
	private int oldEstadoTransaccionIsolation;

    private DriverJDBC(){
        this.hostBD="127.0.0.1";
        this.nombreBD="Diaketas";
        this.password="diaketas";
        this.usuarioBD="diaketas";
    }

    public static DriverJDBC getInstance(){
		if(instancia == null) instancia = new DriverJDBC();
		return instancia;
    }

	public Connection getConexion(){
		return conexion;
	}

    public boolean conectar() throws SQLException{
		try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://"+hostBD+"/"+nombreBD , usuarioBD, password);
            statement = conexion.createStatement();
		} catch (SQLException exSQL) {
			System.err.println(exSQL);
			throw exSQL;
		} catch (ClassNotFoundException exClass){
			System.err.println(exClass);
			return false;
		}

		return true;
    }

    public boolean desconectar(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return false;
        }

        return true;
    }

    public boolean insertar(String sentencia) throws SQLException{
		statement.executeUpdate(sentencia);
		return true;
    }

	public boolean actualizar(String sentencia) throws SQLException{
        statement.executeUpdate(sentencia);
        return true;
    }

    public ResultSet seleccionar (String cadena) throws SQLException{
        return statement.executeQuery(cadena);
    }

    public boolean eliminar(String sentencia) throws SQLException{
        boolean exito= actualizar(sentencia);
        return exito;
    }

	// Métodos para transacciones

	/**
	 * Indica que empieza una transacción, que acabará con el
	 * método finTranssacion(), y que no actualizará la base
	 * de datos hasta hacer commit.
	 * @throws SQLException errores en la base de datos
	 */
	public void inicioTransaccion() throws SQLException {
		try {
			this.conectar();
			this.oldEstadoTransaccionIsolation = this.conexion.getTransactionIsolation();
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			this.oldEstadoAutoCommit = this.conexion.getAutoCommit();
			this.conexion.setAutoCommit(false);
		} catch (SQLException e) {
			throw e;
		}
	}

	/**
	 * Actualización de la base de datos con las consultas hechas
	 * @throws SQLException errores en la base de datos
	 */
	public void commit() throws SQLException {
		try {
			this.conexion.commit();
		} catch (SQLException e) {
			throw e;
		}
	}

	/**
	 * Deshace las consultas que no se han cometido en la
	 * base de datos con un comit()
	 * @throws SQLException errores en la base de datos
	 */
	public void rollback() throws SQLException {
		try {
			this.conexion.rollback();
		} catch (SQLException e) {
			throw e;
		}
	}

	/**
	 * Finaliza una trasaccion, cerrando la conexión con la base
	 * de datos, y restaurando el estado anterior para AutoCommit
	 * y para TransactionIsolation
	 * @throws SQLException errores en la base de datos
	 */
	public void finTransaccion() throws SQLException {
		try {
			// Restauramos estado de autoComit y de Isolation
			this.conexion.setAutoCommit(this.oldEstadoAutoCommit);
			this.conexion.setTransactionIsolation(oldEstadoTransaccionIsolation);
			this.desconectar();
		} catch (SQLException e) {
			throw e;
		}
	}

}

