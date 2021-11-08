<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="es.eoi.modelo.Libro"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/estilos.css">
<script type="text/javascript" src="js/script.js" defer></script>
<title>El Rincón de Lili</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%
		Libro lib = (Libro)request.getAttribute("libro");
	%>
	
	<h2>Editar Libro</h2>
	
	<form action="Altas" method="post">
		
		<label>Titulo: </label>
		<input type="text" name="titulo" value="<%=lib.getTitulo() %>" required>
		<label>Fecha: </label>
		<input type="date" name="fecha" value="<%=lib.getFecha() %>" required>
		<label>Autor: </label>
		<input type="text" name="autor" value="<%=lib.getAutor() %>" required>
		<label>Categoria: </label>
		<input type="text" name="categoria" value="<%=lib.getCategoria() %>" required>
		<label>Edicion: </label>
		<input type="text" name="edicion" value="<%=lib.getEdicion() %>" required>
		<label>Idioma: </label>
		<input type="text" name="idioma" value="<%=lib.getIdioma() %>" required>
		<label>Paginas: </label>
		<input type="text" name="paginas" value="<%=lib.getPaginas() %>" required>
		<label>Descripcion: </label>
		<input type="text" name="descripcion" value="<%=lib.getDescripcion() %>" required>
		<label>Ejemplares: </label>
		<input type="number" name="ejemplares" value="<%=lib.getEjemplares() %>" required>
		<label>Stock: </label>
		<input type="number" name="stock" value="<%=lib.getStock() %>" required>
		<label>Disponible: </label>
		<input type="number" name="disponible" value="<%=lib.getDisponible() %>" required>
		<input type="hidden" name="opcion" value="e">
		<input type="hidden" name="id" value="<%=lib.getId() %>">
		<input type="submit" value="Actualizar">
		
	</form>
</body>
</html>