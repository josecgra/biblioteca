<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="es.eoi.modelo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/estilos.css">
<script type="text/javascript" src="js/script.js" defer></script>
<title>El Rincón de Lili</title>
<style>
	table {
		font-family: Arial, Helvetica, sans-serif;
		font-size: 0.8em;
  		border-collapse: collapse;
  		width: 100%;
	}
	th, td {
		border: 1px solid #ddd;
  		padding: 8px;
	}
	tr:nth-child(even){background-color: #f2f2f2;}
	tr:hover {background-color: #ddd;}
	
	th {
		 padding-top: 12px;
		 padding-bottom: 12px;
		 text-align: left;
		 background-color: #04AA6D;
		 color: white;
	}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<%
		List<Prestamo> prestamos = (List<Prestamo>)request.getAttribute("prestusu");
		LibroDAO ldao = new LibroDAO();
	%>
	
	<h2>Listado de Libros Prestados</h2>
	<table>
		<tr>
			<th>TITULO</th>
			<th>FECHA</th>
			<th>DEVOLUCION</th>
			<th>ACCION</th>
		</tr>
			<% 
				if (prestamos != null) {
					for(Prestamo p : prestamos) {
			%>
			<tr>
			<td><%=ldao.getLibro(p.getLibro_id()).getTitulo() %></td>
			<td><%=p.getFecha() %></td>
			<td><%=p.getDevolucion() %></td>
			<td><a href="Altas?operacion=d&libro_id=<%=p.getLibro_id() %>">Devolver</a></td>
			</tr>
			<%
					}
				}
			%>
	</table>
	
</body>
</html>