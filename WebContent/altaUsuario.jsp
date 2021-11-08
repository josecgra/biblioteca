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
	
	<h2>Registro de Usuario</h2>
	
	<form action="Altas" method="post">
		
		<label>Nombre: </label>
		<input type="text" name="nombre" required>
		<label>Apellidos: </label>
		<input type="text" name="apellidos"required>
		<label>Domicilio: </label>
		<input type="text" name="domicilio" required>
		<label>Telefono: </label>
		<input type="tel" name="tel" required>
		<label>Email: </label>
		<input type="email" name="email" required>
		<label>Password: </label>
		<input type="password" name="pass" required>
		<label>Rol: </label>
		<input type="text" name="rol" placeholder="admin | visitante" required>
		<input type="hidden" name="opcion" value="u">
		<input type="submit" value="Registrar">
		
	</form>
</body>
</html>