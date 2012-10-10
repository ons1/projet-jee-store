<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="java.util.*" %>
<%@ page import="model.Panier" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenue dans la caserne FranZaKikess</title>
</head>
<body>
	<!-- LOGIN -->
	<%if(session.getAttribute("user") == null || request.getAttribute("erreur") != null){ %>
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
	<%}
	else { %>
	<%
		Panier mon_panier = (Panier) session.getAttribute("panier");
	%>
	<!-- ACCUEIL -->
	<table border="1" width="100%">
		<tr>
			<td><a href="index.jsp">Accueil</a></td>
			<td><a href="articles.jsp">Articles</a></td>
			<td><a href="commande.jsp">Mes commandes</a></td>
			<td><a href="panier.jsp">Panier</a></td>
			<td>Adherent <%= session.getAttribute("user") %> <a href="Disconnect">Se déconnecter</a></td> 
		</tr>
	</table>
	<h1>Bienvenue sur le site des adherents de l'association</h1>
	<%} %>
	
	<!-- LOGIN -->
</body>
</html>