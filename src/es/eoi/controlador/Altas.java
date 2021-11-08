package es.eoi.controlador;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.eoi.modelo.Libro;
import es.eoi.modelo.LibroDAO;
import es.eoi.modelo.OperacionesDAO;
import es.eoi.modelo.Usuario;
import es.eoi.modelo.UsuarioDAO;

/**
 * Servlet implementation class Altas
 */
@WebServlet("/Altas")
public class Altas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Altas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operacion = request.getParameter("operacion");
		int libro_id = Integer.parseInt(request.getParameter("libro_id"));
		HttpSession sesion = request.getSession();
		Usuario usu = (Usuario)sesion.getAttribute("usuario");
		String pagina = "home.jsp";
		String msginfo = "";
		
		OperacionesDAO ops = new OperacionesDAO();
		LibroDAO ldao = new LibroDAO();
		try {
			switch(operacion) {
			case "r":
				int r = ops.prestar(usu.getId(), libro_id);
				switch(r) {
				case 0:
					msginfo="Libro " + ldao.getLibro(libro_id).getTitulo() + " prestado";
					break;
				case 1:
					msginfo="Libro " + ldao.getLibro(libro_id).getTitulo() + " no disponible";
					break;
				case 2:
					msginfo="Sancionado! Usted no puede reservar hasta el " + usu.getFecha();
					break;
				}
				request.setAttribute("msginfo", msginfo);
				break;
			case "d":
				ops.devolver(usu.getId(), libro_id);
				msginfo="Libro " + ldao.getLibro(libro_id).getTitulo() + " devuelto";
				request.setAttribute("msginfo", msginfo);
				break;
			case "e":
				Libro lib = ldao.getLibro(libro_id);
				request.setAttribute("libro", lib);
				pagina = "milibro.jsp";
			}
			
			List<Libro> libros = ldao.getLibros(usu.getId());
			request.setAttribute("libros", libros);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(pagina);
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		UsuarioDAO udao = new UsuarioDAO();
		LibroDAO ldao = new LibroDAO();
		
		String opcion = request.getParameter("opcion");
		String pagina = "home.jsp";
		
		HttpSession sesion = request.getSession();
		
		String msginfo = "";
		
		try {
			
			List<Libro> libros;
			
			switch(opcion) {
				case "l":
				case "e":
					int id=0;
					if (opcion.equals("e")) {
						id = Integer.parseInt(request.getParameter("id"));
					}
					String titulo = request.getParameter("titulo");
					String fechaS = request.getParameter("fecha");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date fecha = new Date(sdf.parse(fechaS).getTime());
					String autor = request.getParameter("autor");
					String categoria = request.getParameter("categoria");
					String edicion = request.getParameter("edicion");
					String idioma = request.getParameter("idioma");
					String paginas = request.getParameter("paginas");
					String descripcion = request.getParameter("descripcion");
					int ejemplares = Integer.parseInt(request.getParameter("ejemplares"));
					int stock = Integer.parseInt(request.getParameter("stock"));
					int disponible = Integer.parseInt(request.getParameter("disponible"));
					
					Libro lib = new Libro();
					lib.setTitulo(titulo);
					lib.setFecha(fecha);
					lib.setAutor(autor);
					lib.setCategoria(categoria);
					lib.setEdicion(edicion);
					lib.setIdioma(idioma);
					lib.setPaginas(paginas);
					lib.setDescripcion(descripcion);
					lib.setEjemplares(ejemplares);
					lib.setStock(stock);
					lib.setDisponible(disponible);
					
					if (opcion.equals("e")) {
						lib.setId(id);
						ldao.updateLib(lib);
						msginfo = "Libro " + lib.getTitulo() + " Actualizado";
					} else {
						ldao.addLibro(lib);
						msginfo = "Libro " + lib.getTitulo() + " Creado";
					}
					Usuario u = (Usuario)sesion.getAttribute("usuario");
					libros = ldao.getLibros(u.getId());
					request.setAttribute("libros", libros);
					request.setAttribute("msginfo", msginfo);
					break;
				case "u":
					String nombre = request.getParameter("nombre");
					String apellidos = request.getParameter("apellidos");
					String domicilio = request.getParameter("domicilio");
					String tel = request.getParameter("tel");
					String email = request.getParameter("email");
					String pass = request.getParameter("pass");
					String rol = request.getParameter("rol");
					
					Usuario usu = new Usuario();
					usu.setNombre(nombre);
					usu.setApellidos(apellidos);
					usu.setDomicilio(domicilio);
					usu.setTel(tel);
					usu.setSanciones(0);
					usu.setFecha(null);
					usu.setEmail(email);
					usu.setPass(pass);
					usu.setRol(rol);
					
					udao.addUsuario(usu);
					msginfo = "Alta de usuario " + usu.getNombre() + " " + usu.getApellidos() + " - " + usu.getRol() + " OK";
					request.setAttribute("msginfo", msginfo);
					sesion.setAttribute("usuario", usu);
					libros = ldao.getLibros();
					request.setAttribute("libros", libros);
					break;
			}
			
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(pagina);
		rd.forward(request, response);
		
	}

}
