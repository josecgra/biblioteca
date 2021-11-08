package es.eoi.controlador;

import java.io.IOException;
import java.sql.SQLException;
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
import es.eoi.modelo.Usuario;
import es.eoi.modelo.UsuarioDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		UsuarioDAO udao = new UsuarioDAO();
		Usuario usu = null;
		String msgerr = "";
		String pagina = "home.jsp";
		HttpSession sesion = request.getSession();
		
		try {
			usu = udao.login(email, pass);
			if (usu == null) {
				msgerr = "ERR: Usuario no registrado";
				request.setAttribute("msgerr", msgerr);
				pagina = "index.jsp";
			} else {
				LibroDAO ldao = new LibroDAO();
				List<Libro> libros = ldao.getLibros(usu.getId());
				request.setAttribute("libros", libros);
				sesion.setAttribute("usuario", usu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(pagina);
		rd.forward(request, response);
		
	}

}
