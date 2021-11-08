<%@page import="es.eoi.modelo.Usuario"%>
<%
	Usuario usu = (Usuario)session.getAttribute("usuario");
%>
<header>
	<h1>El Rincón de Lili</h1>
	<span>Bienvenido <%=usu.getNombre() + " " + usu.getApellidos() + " - " + usu.getRol()  %></span>
	<nav>
		<a href="Home">Home</a>
		<%
			if (usu.getRol().equals("admin")) {
		%>
		<a href="altaLibro.jsp">Nuevo Libro</a>
		<%
			}
		%>
		<a href="Prestamos">Prestamos</a>
		<a href="MiPerfil">Mi Perfil</a>
		<a href="Logout">Cerrar sesión</a>
	</nav>
</header>