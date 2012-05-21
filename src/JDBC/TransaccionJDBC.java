package JDBC;

import java.sql.Connection;
import java.sql.SQLException;

public class TransaccionJDBC{
    private static TransaccionJDBC instancia;
	private Connection conexion = null;
	private boolean oldEstadoAutoCommit;
	private int oldEstadoTransaccionIsolation;

	private TransaccionJDBC(){
	}

	public static TransaccionJDBC getInstance(){
		if (instancia==null) instancia = new TransaccionJDBC();
		return instancia;
	}

	public void configurar(Connection conexion) throws SQLException {
		try {
			this.configurar(conexion, Connection.TRANSACTION_READ_COMMITTED);
		}
		catch (SQLException ex){
			throw ex;
		}
	}

	public void configurar(Connection connection, int isolation) throws SQLException {
		try {
			this.conexion = connection;
			this.oldEstadoTransaccionIsolation = this.conexion.getTransactionIsolation();
			this.conexion.setTransactionIsolation(isolation);
		} catch (SQLException ex) {
			throw ex;
		}

	}

	public void commit() throws SQLException {
		try {
			this.conexion.commit();
		} catch (SQLException e) {
			throw e;
		}

	}

	public void end() throws SQLException {
		try {
			// Restauramos estado de autoComit
			this.conexion.setAutoCommit(this.oldEstadoAutoCommit);
			this.conexion.setTransactionIsolation(this.oldEstadoTransaccionIsolation);
			this.conexion.close();
		} catch (SQLException e) {
			throw e;
		}
	}

	public void rollback() throws SQLException {
		try {
			this.conexion.rollback();
		} catch (SQLException e) {
			throw e;
		}
	}

	public void start() throws SQLException {
		try {
			this.oldEstadoAutoCommit =				// Guardamos antiguo estado de
					this.conexion.getAutoCommit();	// autocomit para restaurarlo
			this.conexion.setAutoCommit(false);		// y ponemos autocomit a falso
		} catch (SQLException e) {
			throw e;
		}
	}

}
