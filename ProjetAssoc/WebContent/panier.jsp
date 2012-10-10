<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="java.util.*"%>
<%@ page import="model.Panier"%>
<%@ page import="model.LignePanier"%>
<%@ page import="model.Article"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- Panier -->
	<table border="1" width="100%">
		<tr>
			<td><a href="home">Accueil</a></td>
			<td><a href="articles">Articles</a></td>
			<td><a href="commande">Mes commandes</a></td>
			<td><a href="panier">Panier</a></td>
			<td>Adherent <%= session.getAttribute("user") %> <a
				href="disconnect">Se déconnecter</a></td>
		</tr>
	</table>
	<h1>Mon panier</h1>
	<% // Affichage du message d'erreur s'il existe
	      if (request.getAttribute("erreur") != null) { %>
	<strong>Erreur : <%= (String) request.getAttribute("erreur")%></strong>
	<br>
	<%} %>
	<table border="1" width="100%">
		<tr>
			<td>Code</td>
			<td>Nom</td>
			<td>Prix</td>
			<td>Quantite</td>
		</tr>
		<%
				Panier panier = (Panier)session.getAttribute("panier");
				for(LignePanier l:panier.getLignesPanier()){
			%>
		<tr>
			<td><%= l.getArticle().getCode() %></td>
			<td><%= l.getArticle().getNom() %></td>
			<td><%= l.getArticle().getPrix() %></td>
			<td>
				<form name="chgQte<%=l.getArticle().getCode() %>" action="panier" method="post">
					<input type="text" name="quantite" value="<%= l.getQuantite() %>">
					<input type="submit" value="maj"> 
					<input type="hidden" name="quantiteOld" value="<%= l.getQuantite() %>">
					<input type="hidden" name="action" value="majqte">
					<input type="hidden" name="code" value="<%=l.getArticle().getCode()%>"> 
				</form>
			</td>
			<td>
				<form name="remArt<%=l.getArticle().getCode() %>" action="panier" method="post">
					<input type="submit" value="Supprimer"> 
					<input type="hidden" name="quantite" value="<%= l.getQuantite() %>">
					<input type="hidden" name="action" value="supprimer">
					<input type="hidden" name="code" value="<%=l.getArticle().getCode()%>"> 
				</form>
			</td>
		</tr>	
		<%} %>
	</table>
	<form name="formCommander" action="panier" method="post">
		<input type="submit" value="Commander"> 
		<input type="hidden" name="action" value="commander">
	</form>
</body>
</html>