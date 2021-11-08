package es.eoi.modelo;

import java.sql.Date;

public class Prestamo {
	private int id;
	private int usuario_id;
	private int libro_id;
	private Date fecha;
	private Date devolucion;
	
	public Prestamo() {
		
	}
	
	public Prestamo(int id, int usuario_id, int libro_id, Date fecha, Date devolucion) {
		super();
		this.id = id;
		this.usuario_id = usuario_id;
		this.libro_id = libro_id;
		this.fecha = fecha;
		this.devolucion = devolucion;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	public int getLibro_id() {
		return libro_id;
	}

	public void setLibro_id(int libro_id) {
		this.libro_id = libro_id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getDevolucion() {
		return devolucion;
	}

	public void setDevolucion(Date devolucion) {
		this.devolucion = devolucion;
	}
	
	
	
}
