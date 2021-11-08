package es.eoi.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.eoi.servicios.Conexion;

public class UsuarioDAO {
	
	private Connection con;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	// Obtenemos un usuario a partir del ID
	public Usuario getUsuario(int id) throws SQLException {
		
		con = Conexion.getInstance().getConnection();
		String sql = "SELECT * FROM usuarios WHERE id = ?";
		
		pst = con.prepareStatement(sql);
		pst.setInt(1, id);
		
		rs = pst.executeQuery();
		
		Usuario usu = null;
		
		if (rs.next()) {
			usu = new Usuario();
			usu.setId(rs.getInt(1));
			usu.setNombre(rs.getString(2));
			usu.setApellidos(rs.getString(3));
			usu.setDomicilio(rs.getString(4));
			usu.setTel(rs.getString(5));
			usu.setSanciones(rs.getInt(6));
			usu.setFecha(rs.getDate(7));
			usu.setEmail(rs.getString(8));
			usu.setPass(rs.getString(9));
			usu.setRol(rs.getString(10));
		}
		
		return usu;
	}

	// Obtenemos todos los usuarios
	public List<Usuario> getUsuarios() throws SQLException {
		
		con = Conexion.getInstance().getConnection();
		String sql = "SELECT * FROM usuarios";
		
		st = con.createStatement();
		rs = st.executeQuery(sql);
		
		Usuario usu = null;
		List<Usuario> lista = new ArrayList<Usuario>();
		
		while(rs.next()) {
			usu = new Usuario();
			
			usu.setId(rs.getInt(1));
			usu.setNombre(rs.getString(2));
			usu.setApellidos(rs.getString(3));
			usu.setDomicilio(rs.getString(4));
			usu.setTel(rs.getString(5));
			usu.setSanciones(rs.getInt(6));
			usu.setFecha(rs.getDate(7));
			usu.setEmail(rs.getString(8));
			usu.setPass(rs.getString(9));
			usu.setRol(rs.getString(10));
			
			lista.add(usu);
		}
		
		return lista;
	}

	// Insertamos en BBDD un usuario a partir del objeto usu
	public int addUsuario(Usuario usu) throws SQLException {
		
		con = Conexion.getInstance().getConnection();
		String sql = "INSERT INTO usuarios(nombre,apellidos,domicilio,tel,sanciones,fecha,email,pass,rol) VALUES (?,?,?,?,?,?,?,?,?)";
		
		pst = con.prepareStatement(sql);
		pst.setString(1, usu.getNombre());
		pst.setString(2, usu.getApellidos());
		pst.setString(3, usu.getDomicilio());
		pst.setString(4, usu.getTel());
		pst.setInt(5, usu.getSanciones());
		pst.setDate(6, usu.getFecha());
		pst.setString(7, usu.getEmail());
		pst.setString(8, usu.getPass());
		pst.setString(9, usu.getRol());
		
		return pst.executeUpdate();

	}

	// Borramos de la BBDD un usuario a partir del ID
	public int delUsuario(int id) throws SQLException {
		
		con = Conexion.getInstance().getConnection();
		String sql = "DELETE FROM usuarios WHERE id = ?";
		
		pst = con.prepareStatement(sql);
		pst.setInt(1, id);
		
		return pst.executeUpdate();
	}

	// Actualizamos en BBDD un usuario a partir del objeto usu
	public int updateUsu(Usuario usu) throws SQLException {
		
		con = Conexion.getInstance().getConnection();
		String sql = "UPDATE usuarios SET nombre=?,apellidos=?,domicilio=?,tel=?,sanciones=?,fecha=?,email=?,pass=?,rol=? WHERE id = ?";
		
		pst = con.prepareStatement(sql);
		pst.setString(1, usu.getNombre());
		pst.setString(2, usu.getApellidos());
		pst.setString(3, usu.getDomicilio());
		pst.setString(4, usu.getTel());
		pst.setInt(5, usu.getSanciones());
		pst.setDate(6, usu.getFecha());
		pst.setString(7, usu.getEmail());
		pst.setString(8, usu.getPass());
		pst.setString(9, usu.getRol());
		pst.setInt(10, usu.getId());
		
		return pst.executeUpdate();
	}
	
	// Devuelve el usuario logado si existe en la BBDD a partir del email y pass
	public Usuario login(String email, String pass) throws SQLException {
		
		con = Conexion.getInstance().getConnection();
		String sql = "SELECT * FROM usuarios WHERE email = ? AND pass = ?";
		
		pst = con.prepareStatement(sql);
		pst.setString(1, email);
		pst.setString(2, pass);
				
		rs = pst.executeQuery();
		Usuario usu = null;
		
		if (rs.next()) {
			usu = new Usuario();
			usu.setId(rs.getInt(1));
			usu.setNombre(rs.getString(2));
			usu.setApellidos(rs.getString(3));
			usu.setDomicilio(rs.getString(4));
			usu.setTel(rs.getString(5));
			usu.setSanciones(rs.getInt(6));
			usu.setFecha(rs.getDate(7));
			usu.setEmail(rs.getString(8));
			usu.setPass(rs.getString(9));
			usu.setRol(rs.getString(10));
		}
		
		return usu;
	}
	
}
