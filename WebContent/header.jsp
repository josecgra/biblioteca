<%@page import="es.eoi.modelo.Usuario"%>
<%
	Usuario usu = (Usuario)session.getAttribute("usuario");
%>
<header>
	<h1>El Rincón de Lili</h1>
	<span id="bienvenida">Bienvenido <%=usu.getNombre() + " " + usu.getApellidos() + " - " + usu.getRol()  %></span>
	<nav>
		<li><a href="Home">Home</a></li>
		<%
			if (usu.getRol().equals("admin")) {
		%>
		<li><a href="altaLibro.jsp">Nuevo Libro</a></li>
		<%
			}
		%>
		<li><a href="Prestamos">Prestamos</a></li>
		<li><a href="MiPerfil">Mi Perfil</a></li>
		<li><a href="Logout">Cerrar sesión</a></li>
	</nav>
</header>