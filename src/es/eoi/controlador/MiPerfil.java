package es.eoi.controlador;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.eoi.modelo.Usuario;
import es.eoi.modelo.UsuarioDAO;

/**
 * Servlet implementation class MiPerfil
 */
@WebServlet("/MiPerfil")
public class MiPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MiPerfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opcion = request.getParameter("opcion");
		HttpSession sesion = request.getSession();
		Usuario usu = (Usuario)sesion.getAttribute("usuario");
		String pagina = "miperfil.jsp";
		String msgerr = "";
		UsuarioDAO udao = new UsuarioDAO();
		if (opcion != null) {
			switch(opcion) {
				case "b":
					try {
						udao.delUsuario(usu.getId());
						pagina = "index.jsp";
						msgerr = "Usuario dado de baja correctamente";
						request.setAttribute("msgerr", msgerr);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(pagina);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String domicilio = request.getParameter("domicilio");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String rol = request.getParameter("rol");
		int id = Integer.parseInt(request.getParameter("id"));
		
		Usuario usu = new Usuario();
		usu.setId(id);
		usu.setNombre(nombre);
		usu.setApellidos(apellidos);
		usu.setDomicilio(domicilio);
		usu.setTel(tel);
		usu.setSanciones(0);
		usu.setFecha(null);
		usu.setEmail(email);
		usu.setPass(pass);
		usu.setRol(rol);
		
		UsuarioDAO udao = new UsuarioDAO();
		HttpSession sesion = request.getSession();
		
		String pagina = "Home";
		String msginfo = "";
		
		try {
			udao.updateUsu(usu);
			sesion.setAttribute("usuario", usu);
			msginfo="Usuario " + usu.getNombre() + " " + usu.getApellidos() + " actualizado";
			request.setAttribute("msginfo", msginfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(pagina);
		rd.forward(request, response);
		
	}

}
