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



	<form action="Login" method="post">
		<label>Email:</label>
		<input type="email" name="email">
		<label>Contraseña:</label>
		<input type="password" name="pass">
		<input type="submit" value="Login">
		<span style="color: red"><%=request.getAttribute("msgerr")==null ? "" : request.getAttribute("msgerr") %></span>
		<a style="color: green" href='altaUsuario.jsp'><%=request.getAttribute("msgerr")==null ? "" : "Registrar" %></a>
	</form>
</body>
</html>