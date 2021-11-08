package es.eoi.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static Conexion instancia;
	private Connection connection;

	private String url = "jdbc:mysql://localhost:3306/biblioteca?useSSL=false&serverTimezone=UTC";
	private String username = "root";
	private String password = "1234";

	private Conexion() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException ex) {
			System.out.println("Conexion a BD incorrecta : " + ex.getMessage());
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static Conexion getInstance() throws SQLException {
		if (instancia == null) {
			instancia = new Conexion();
		} else if (instancia.getConnection().isClosed()) {
			instancia = new Conexion();
		}

		return instancia;
	}
}
