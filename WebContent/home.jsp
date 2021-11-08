<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="es.eoi.modelo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>El Rincón de Lili</title>
<link rel="stylesheet" href="css/estilos.css">
<script type="text/javascript" src="js/script.js" defer></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<%
		List<Libro> libros = (List<Libro>)request.getAttribute("libros");
		Usuario usu = (Usuario)session.getAttribute("usuario");
	%>
	<h2>Listado de Libros de la Biblioteca</h2>
	<span id="msginfo">
		<%=request.getAttribute("msginfo")==null ? "&nbsp;" : request.getAttribute("msginfo") %>
	</span>
	<table>
		<tr>
			<th>ID</th>
			<th>TITULO</th>
			<th>FECHA</th>
			<th>AUTOR</th>
			<th>CATEGORIA</th>
			<th>EDICION</th>
			<th>IDIOMA</th>
			<th>PAGINAS</th>
			<th>DESCRIPCION</th>
			<th>EJEMPLARES</th>
			<th>STOCK</th>
			<th>DISPONIBLE</th>
			<th>ACCION</th>
		</tr>
			<% 
				if (libros != null) {
					for(Libro l : libros) {
			%>
			<tr>
			<td><%=l.getId() %></td>
			<td><%=l.getTitulo() %></td>
			<td><%=l.getFecha() %></td>
			<td><%=l.getAutor() %></td>
			<td><%=l.getCategoria() %></td>
			<td><%=l.getEdicion() %></td>
			<td><%=l.getIdioma() %></td>
			<td><%=l.getPaginas() %></td>
			<td><%=l.getDescripcion() %></td>
			<td><%=l.getEjemplares() %></td>
			<td><%=l.getStock() %></td>
			<td><%=l.getDisponible() %></td>
			<td>
			<%
				if (usu.getRol().equals("admin")) {
			%>
			<a href="Altas?operacion=e&libro_id=<%=l.getId() %>">Editar</a>
			<%
				}
			%>
			<a href="Altas?operacion=r&libro_id=<%=l.getId() %>">Reservar</a>
			</td>
			</tr>
			<%
					}
				}
			%>
	</table>
</body>
</html>