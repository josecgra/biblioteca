<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="es.eoi.modelo.Usuario"%>
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
		Usuario usu = (Usuario)session.getAttribute("usuario");
	%>
	<h2>Tu Perfil</h2>
	
	<form action="MiPerfil" method="post">
		
		<label>Nombre: </label>
		<input type="text" name="nombre" value="<%=usu.getNombre() %>" required>
		<label>Apellidos: </label>
		<input type="text" name="apellidos" value="<%=usu.getApellidos() %>" required>
		<label>Domicilio: </label>
		<input type="text" name="domicilio" value="<%=usu.getDomicilio() %>" required>
		<label>Telefono: </label>
		<input type="tel" name="tel" value="<%=usu.getTel() %>" required>
		<label>Email: </label>
		<input type="email" name="email" value="<%=usu.getEmail() %>" required>
		<label>Password: </label>
		<input type="password" name="pass" value="<%=usu.getPass() %>" required>
		<label>Rol: </label>
		<input type="text" name="rol" value="<%=usu.getRol() %>" required>
		<input type="hidden" name="id" value="<%=usu.getId() %>">
		<input type="submit" value="Actualizar">
		
	</form>
	<a href="MiPerfil?opcion=b">Darme de baja</a>
</body>
</html>