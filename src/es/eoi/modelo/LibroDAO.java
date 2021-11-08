package es.eoi.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.eoi.servicios.Conexion;

public class LibroDAO {

	private Connection con;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	// Obtenemos un libro a partir del ID
	public Libro getLibro(int id) throws SQLException {
		
		con = Conexion.getInstance().getConnection();
		String sql = "SELECT * FROM libros WHERE id = ?";

		pst = con.prepareStatement(sql);
		pst.setInt(1, id);

		rs = pst.executeQuery();

		Libro lib = null;

		if (rs.next()) {
			lib = new Libro();
			lib.setId(rs.getInt(1));
			lib.setTitulo(rs.getString(2));
			lib.setFecha(rs.getDate(3));
			lib.setAutor(rs.getString(4));
			lib.setCategoria(rs.getString(5));
			lib.setEdicion(rs.getString(6));
			lib.setIdioma(rs.getString(7));
			lib.setPaginas(rs.getString(8));
			lib.setDescripcion(rs.getString(9));
			lib.setEjemplares(rs.getInt(10));
			lib.setStock(rs.getInt(11));
			lib.setDisponible(rs.getInt(12));
		}

		return lib;

	}

	// Obtenemos todos los libros disponibles
		public List<Libro> getLibros() throws SQLException {
			
			con = Conexion.getInstance().getConnection();
			String sql = "SELECT * FROM libros WHERE disponible > 0";
			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			Libro lib = null;
			List<Libro> lista = new ArrayList<Libro>();
			
			while(rs.next()) {
				lib = new Libro();
				
				lib.setId(rs.getInt(1));
				lib.setTitulo(rs.getString(2));
				lib.setFecha(rs.getDate(3));
				lib.setAutor(rs.getString(4));
				lib.setCategoria(rs.getString(5));
				lib.setEdicion(rs.getString(6));
				lib.setIdioma(rs.getString(7));
				lib.setPaginas(rs.getString(8));
				lib.setDescripcion(rs.getString(9));
				lib.setEjemplares(rs.getInt(10));
				lib.setStock(rs.getInt(11));
				lib.setDisponible(rs.getInt(12));
				
				lista.add(lib);
			}
			
			return lista;
		}
	
	
	// Obtenemos todos los libros disponibles y que no esten reservados por el usuario
	public List<Libro> getLibros(int usuario_id) throws SQLException {
		
		con = Conexion.getInstance().getConnection();
		String sql = "SELECT * FROM libros WHERE disponible > 0 AND id not in (SELECT libro_id FROM prestamos WHERE usuario_id = ?)";
		
		pst = con.prepareStatement(sql);
		pst.setInt(1, usuario_id);
		rs = pst.executeQuery();
		
		Libro lib = null;
		List<Libro> lista = new ArrayList<Libro>();
		
		while(rs.next()) {
			lib = new Libro();
			
			lib.setId(rs.getInt(1));
			lib.setTitulo(rs.getString(2));
			lib.setFecha(rs.getDate(3));
			lib.setAutor(rs.getString(4));
			lib.setCategoria(rs.getString(5));
			lib.setEdicion(rs.getString(6));
			lib.setIdioma(rs.getString(7));
			lib.setPaginas(rs.getString(8));
			lib.setDescripcion(rs.getString(9));
			lib.setEjemplares(rs.getInt(10));
			lib.setStock(rs.getInt(11));
			lib.setDisponible(rs.getInt(12));
			
			lista.add(lib);
		}
		
		return lista;
	}

	// Insertamos en BBDD un libro a partir del objeto lib
	public int addLibro(Libro lib) throws SQLException {
		
		con = Conexion.getInstance().getConnection();
		String sql = "INSERT INTO libros(titulo,fecha,autor,categoria,edicion,idioma,paginas,descripcion,ejemplares,stock,disponible) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		pst = con.prepareStatement(sql);
		pst.setString(1, lib.getTitulo());
		pst.setDate(2, lib.getFecha());
		pst.setString(3, lib.getAutor());
		pst.setString(4, lib.getCategoria());
		pst.setString(5, lib.getEdicion());
		pst.setString(6, lib.getIdioma());
		pst.setString(7, lib.getPaginas());
		pst.setString(8, lib.getDescripcion());
		pst.setInt(9, lib.getEjemplares());
		pst.setInt(10, lib.getStock());
		pst.setInt(11, lib.getDisponible());
		
		
		return pst.executeUpdate();
	}

	// Borramos de la BBDD un libro a partir del ID
	public int delLibro(int id) throws SQLException {
		con = Conexion.getInstance().getConnection();
		String sql = "DELETE FROM libros WHERE id = ?";
		
		pst = con.prepareStatement(sql);
		pst.setInt(1, id);
		
		return pst.executeUpdate();
	}

	// Actualizamos en BBDD un libro a partir del objeto lib
	public int updateLib(Libro lib) throws SQLException {
		con = Conexion.getInstance().getConnection();
		String sql = "UPDATE libros SET titulo=?,fecha=?,autor=?,categoria=?,edicion=?,idioma=?,paginas=?,descripcion=?,ejemplares=?,stock=?,disponible=? WHERE id = ?";
		
		pst = con.prepareStatement(sql);
		pst.setString(1, lib.getTitulo());
		pst.setDate(2, lib.getFecha());
		pst.setString(3, lib.getAutor());
		pst.setString(4, lib.getCategoria());
		pst.setString(5, lib.getEdicion());
		pst.setString(6, lib.getIdioma());
		pst.setString(7, lib.getPaginas());
		pst.setString(8, lib.getDescripcion());
		pst.setInt(9, lib.getEjemplares());
		pst.setInt(10, lib.getStock());
		pst.setInt(11, lib.getDisponible());
		pst.setInt(12, lib.getId());
		
		return pst.executeUpdate();
	}
	
	// Obtenemos los libros prestados a un usuario
	public List<Libro> getLibrosOfUsuario(int usuario_id) throws SQLException {
		
		con = Conexion.getInstance().getConnection();
		String sql = "SELECT * FROM prestamos WHERE usuario_id = ?";
		
		pst = con.prepareStatement(sql);
		pst.setInt(1, usuario_id);
		rs = pst.executeQuery();
		
		Libro lib = null;
		List<Libro> lista = new ArrayList<Libro>();
		
		while(rs.next()) {
			LibroDAO ldao = new LibroDAO();
			lib = ldao.getLibro(rs.getInt("libro_id"));
			lista.add(lib);
		}
		return lista;
	}

}
