<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	
	<h2>Registro de Libro</h2>
	
	<form action="Altas" method="post">
		
		<label>Titulo: </label>
		<input type="text" name="titulo" required>
		<label>Fecha: </label>
		<input type="date" name="fecha" required>
		<label>Autor: </label>
		<input type="text" name="autor" required>
		<label>Categoria: </label>
		<input type="text" name="categoria" required>
		<label>Edicion: </label>
		<input type="text" name="edicion" required>
		<label>Idioma: </label>
		<input type="text" name="idioma" required>
		<label>Paginas: </label>
		<input type="text" name="paginas" required>
		<label>Descripcion: </label>
		<input type="text" name="descripcion" required>
		<label>Ejemplares: </label>
		<input type="number" name="ejemplares" required>
		<label>Stock: </label>
		<input type="number" name="stock" required>
		<label>Disponible: </label>
		<input type="number" name="disponible" required>
		<input type="hidden" name="opcion" value="l">
		<input type="submit" value="Registrar">
		
	</form>
</body>
</html>