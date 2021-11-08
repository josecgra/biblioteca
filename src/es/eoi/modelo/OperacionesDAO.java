package es.eoi.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.eoi.servicios.Conexion;

public class OperacionesDAO {

	private Connection con;
	private PreparedStatement pst;
	private PreparedStatement pst1;
	private PreparedStatement pst2;
	private ResultSet rs;
	private UsuarioDAO udao;
	private LibroDAO ldao;

	// Realiza la operación de préstamo de un libro a un usuario
	// validará que si el usuario está sancionado, su fecha sea mayor o igual a
	// la fecha actual. En caso contrario no podrá prestarsele el libro.
	// Si todo está correcto se le prestará el libro 5 días.
	// los retornos serán:
	// 0: prestamo OK
	// 1: prestamo KO - libro no disponible
	// 2: prestamo KO - usuario sancionado
	public int prestar(int usuario_id, int libro_id) throws SQLException {

		int ret = 0;
		ldao = new LibroDAO();
		udao = new UsuarioDAO();
		Libro lib = ldao.getLibro(libro_id);
		Usuario usu = udao.getUsuario(usuario_id);

		// valido si el libro está disponible
		if (lib.getDisponible() > 0) {

			// compruebo si el usuario está sancionado
			Date hoy = new Date(System.currentTimeMillis());
			if (usu.getFecha() != null) {
				if (usu.getFecha().compareTo(hoy) <= 0) {
					reservar(usuario_id, libro_id);
				} else {
					ret = 2;
				}
			} else {
				reservar(usuario_id, libro_id);
			}
		} else {
			ret = 1;
		}

		return ret;
	}

	// Realiza la operación de devolución de un libro a un usuario
	// validará que si el usuario ha devuelto el libro más tarde que la fecha límite
	// éste será sancionado.
	// los retornos serán:
	// true: devolución OK - sin sanción
	// false: devolución OK - con sanción
	public boolean devolver(int usuario_id, int libro_id) throws SQLException {

		boolean dev = true;
		con = Conexion.getInstance().getConnection();
		String sql = "DELETE FROM prestamos WHERE usuario_id = ? AND libro_id = ?";
		String sql1 = "SELECT * FROM prestamos WHERE usuario_id = ? AND libro_id = ?";
		String sql2 = "UPDATE libros SET disponible = ? WHERE id = ?";

		Date hoy = new Date(System.currentTimeMillis());
		pst1 = con.prepareStatement(sql1);
		pst1.setInt(1, usuario_id);
		pst1.setInt(2, libro_id);

		rs = pst1.executeQuery();

		if (rs.next()) {
			if (rs.getDate("devolucion").compareTo(hoy) < 0) {
				int diferencia = (int) ChronoUnit.DAYS.between(rs.getDate("devolucion").toLocalDate(),
						hoy.toLocalDate());
				int diassancion = diferencia * 3;
				sancionar(usuario_id, diassancion);
				dev = false;
			}
		}

		con.setAutoCommit(false);
		// Tabla prestamos
		pst = con.prepareStatement(sql);
		pst.setInt(1, usuario_id);
		pst.setInt(2, libro_id);
		pst.executeUpdate();

		// Tabla libros
		ldao = new LibroDAO();
		Libro lib = ldao.getLibro(libro_id);
		pst2 = con.prepareStatement(sql2);
		pst2.setInt(1, lib.getDisponible() + 1);
		pst2.setInt(2, libro_id);
		pst2.executeUpdate();
		con.commit();

		return dev;
	}

	// Procede a sancionar al usuario
	// La sanción consistirá en una penalización de 3 días por cada día de retraso
	// en la devolución
	private void sancionar(int usuario_id, int diassancion) throws SQLException {

		// calculamos fecha de sanción sumandole los días de sanción a la fecha actual
		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.sql.Date(System.currentTimeMillis()));
		cal.add(Calendar.DAY_OF_MONTH, diassancion);
		Date fecha = new java.sql.Date(cal.getTimeInMillis());

		UsuarioDAO udao = new UsuarioDAO();
		Usuario usu = udao.getUsuario(usuario_id);
		usu.setFecha(fecha);
		int numsanciones = usu.getSanciones();
		numsanciones++;
		usu.setSanciones(numsanciones);

		udao.updateUsu(usu);

	}

	// Realiza la operacion de reserva en la BBDD
	// dara el alta del registro en la tabla prestamos y actualizará el dato de
	// disponibles
	// en la tabla libros. Al modificar dos tablas deberemos usar transacciones.
	private int reservar(int usuario_id, int libro_id) throws SQLException {

		int ret = 1;
		con = Conexion.getInstance().getConnection();
		String sql = "INSERT INTO prestamos(usuario_id,libro_id,fecha,devolucion) VALUES (?,?,?,?)";
		String sql1 = "UPDATE libros SET disponible = ? WHERE id = ?";

		// calculamos fecha de devolución (5 dias de prestamo)
		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.sql.Date(System.currentTimeMillis()));
		cal.add(Calendar.DAY_OF_MONTH, 5);
		Date devolucion = new java.sql.Date(cal.getTimeInMillis());

		con.setAutoCommit(false);
		// Tabla prestamos
		pst = con.prepareStatement(sql);
		pst.setInt(1, usuario_id);
		pst.setInt(2, libro_id);
		pst.setDate(3, new java.sql.Date(System.currentTimeMillis()));
		pst.setDate(4, devolucion);

		ret = pst.executeUpdate();

		if (ret == 1) {
			// Tabla libros
			ldao = new LibroDAO();
			Libro lib = ldao.getLibro(libro_id);

			pst1 = con.prepareStatement(sql1);
			pst1.setInt(1, lib.getDisponible() - 1);
			pst1.setInt(2, libro_id);

			if (pst1.executeUpdate() == 1) {
				con.commit();
			} else {
				con.rollback();
				ret = 0;
			}
		} else {
			con.rollback();
			ret = 0;
		}

		con.setAutoCommit(true);

		return ret;

	}

	// Obtenemos los libros prestados a un usuario
	public List<Prestamo> getPrestamosOfUsuario(int usuario_id) throws SQLException {

		con = Conexion.getInstance().getConnection();
		String sql = "SELECT * FROM prestamos WHERE usuario_id = ?";

		pst = con.prepareStatement(sql);
		pst.setInt(1, usuario_id);
		rs = pst.executeQuery();

		Prestamo pre = null;
		List<Prestamo> lista = new ArrayList<Prestamo>();

		while (rs.next()) {
			pre = new Prestamo();
			pre.setFecha(rs.getDate("fecha"));
			pre.setLibro_id(rs.getInt("libro_id"));
			pre.setDevolucion(rs.getDate("devolucion"));
			lista.add(pre);
		}
		return lista;
	}

}
