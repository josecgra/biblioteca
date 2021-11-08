package es.eoi.modelo;

import java.sql.Date;

public class Usuario {
	
	private int id;
	
	private String nombre;
	private String apellidos;
	private String domicilio;
	private String tel;
	private int sanciones;
	private Date fecha;
	private String email;
	private String pass;
	private String rol;
	
	public Usuario() {
		
	}
	
	public Usuario(int id, String nombre, String apellidos, String domicilio, String tel, int sanciones, Date fecha, String email, String pass, String rol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		this.tel = tel;
		this.sanciones = sanciones;
		this.fecha = fecha;
		this.email = email;
		this.pass = pass;
		this.rol = rol;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getSanciones() {
		return sanciones;
	}

	public void setSanciones(int sanciones) {
		this.sanciones = sanciones;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	
	
}
