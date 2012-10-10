<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="java.util.*" %>
<%@ page import="model.Panier" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inscription</title>>
</head>
<body>
<h1>Veuillez vous inscrire :</h1>

<form name="inscription" action="Subscription" method="post">
		<label for="id">Identifiant : </label>
		<input type="text" name="id" ><br>
		<label for="password1">Mot de passe : </label>
		<input type="password" name="password1"><br>
		<label for="password2">Mot de passe (confirmation) : </label>
		<input type="password" name="password2"><br>
		<label for="nom">Nom de famille : </label>
		<input type="text" name="nom"><br>
		<label for="prenom">Prenom : </label>
		<input type="text" name="prenom"><br>
		<label for="adresse">Adresse (rue) : </label>
		<input type="text" name="adresse"><br>
		<label for="cp">Code postal : </label>
		<input type="text" name="cp"><br>
		<label for="ville">Ville : </label>
		<input type="text" name="ville"><br>
		<label for="pays">Pays : </label>
		<input type="text" name="pays"><br>
		<input type="submit" value="Enregistrer">
</form>
<p> * Champ obligatoire </p>
</body>
</html>