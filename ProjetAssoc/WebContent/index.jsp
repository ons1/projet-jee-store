<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenue dans la caserne FranZaKikess</title>
</head>
<body>
	<h1>LOGIN</h1>
	<% // Affichage du message d'erreur s'il existe
      if (request.getAttribute("erreur") != null) { %>
            <strong>Erreur : <%= (String) request.getAttribute("erreur")%></strong>
            <br>
            <p>Veuillez ressayer !</p>
            <%} %>
	<form name="identification" action="ControlAccess" method="post">
	<label for="user">Identifiant : </label>
	<input type="text" name="user"><br>
	<label for="password">Mot de passe : </label>
	<input type="password" name="password">
	<br>
	<input type="submit" value="OK">
	</form>
	<a href="subscription.jsp">Pas encore inscrit ?</a>
</body>
</html>