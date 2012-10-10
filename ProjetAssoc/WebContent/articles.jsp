<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Collection"%>
<%@ page import="model.Article"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- ARTICLES -->
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
	<h1>Catalogue des articles</h1>
	<table border="1" width="100%">
		<tr>
			<td>Code</td>
			<td>Nom</td>
			<td>Prix</td>
			<td>Stock</td>
		</tr>
		<%
				Collection<Article> list_article = (Collection<Article>)request.getAttribute("list_article");
				for(Article art:list_article){
			%>
		<tr>
			<td><%= art.getCode() %></td>
			<td><%= art.getNom() %></td>
			<td><%= art.getPrix() %></td>
			<td><%= art.getStock() %></td>
			<td>
				<form name="addArt<%=art.getCode() %>" action="panier" method="post">
					<input type="submit" value="Commander"> 
					<input type="hidden" name="action" value="ajouter">
					<input type="hidden" name="code" value="<%=art.getCode()%>"> 
				</form>
			</td>
		</tr>
		<%} %>
	</table>
</body>
</html>