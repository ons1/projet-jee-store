<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="java.util.*"%>
<%@ page import="model.Panier"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inscription</title>
</head>
<body>
	<h1>Veuillez vous inscrire :</h1>

	<form name="inscription" action="subscription" method="post">
		<table border="1">
			<tr>
				<td align="left"><label for="id">*Identifiant : </label></td>
				<td align="right"><input type="text" name="id"></td>
			</tr>

			<tr>
				<td align="left"><label for="password1">*Mot de passe :
				</label></td>
				<td align="right"><input type="password" name="password1"></td>
			</tr>
			<tr>
				<td align="left"><label for="password2">*Mot de passe
						(confirmation) : </label></td>
				<td align="right"><input type="password" name="password2"></td>
			</tr>

			<tr>
				<td align="left"><label for="nom">Nom de famille : </label></td>
				<td align="right"><input type="text" name="nom"></td>
			</tr>

			<tr>
				<td align="left"><label for="prenom">Prenom : </label></td>
				<td align="right"><input type="text" name="prenom"></td>
			</tr>
			<tr>
				<td align="left"><label for="adresse">Adresse (rue) : </label></td>
				<td align="right"><input type="text" name="adresse"></td>
			</tr>

			<tr>
				<td align="left"><label for="cp">Code postal : </label></td>
				<td align="right"><input type="text" name="cp"></td>
			</tr>
			<tr>
				<td align="left"><label for="ville">Ville : </label></td>
				<td align="right"><input type="text" name="ville"></td>
			</tr>
			<tr>
				<td align="left"><label for="pays">Pays : </label></td>
				<td align="right"><input type="text" name="pays"></td>
			</tr>
		</table>
		<input type="submit" value="Enregistrer">

		</table>
	</form>
	<p>* Champ obligatoire</p>
</body>
</html>