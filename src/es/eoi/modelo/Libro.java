package es.eoi.modelo;

import java.sql.Date;

public class Libro {
	
	private int id;
	
	private String titulo;
	private Date fecha;
	private String autor;
	private String categoria;
	private String edicion;
	private String idioma;
	private String paginas;
	private String descripcion;
	private int ejemplares;
	private int stock;
	private int disponible;
	
	public Libro() {
		
	}
	
	public Libro(int id, String titulo, Date fecha, String autor, String categoria, String edicion, String idioma,
			String paginas, String descripcion, int ejemplares, int stock, int disponible) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.fecha = fecha;
		this.autor = autor;
		this.categoria = categoria;
		this.edicion = edicion;
		this.idioma = idioma;
		this.paginas = paginas;
		this.descripcion = descripcion;
		this.ejemplares = ejemplares;
		this.stock = stock;
		this.disponible = disponible;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEdicion() {
		return edicion;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(int ejemplares) {
		this.ejemplares = ejemplares;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getDisponible() {
		return disponible;
	}

	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}
	
	
	
	
}
