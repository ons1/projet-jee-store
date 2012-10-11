<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<!-- Commande -->
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
	<h1>Mes commandes</h1>
</body>
</html>