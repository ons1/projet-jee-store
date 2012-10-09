<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="java.util.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	boolean allready_log = session.getAttribute("user") == null ? false:true;
%>
<%
	if(!allready_log){
	%>
	<h1>LOGIN</h1>
	<form name="identification" action="ControlAccess" method="post">
	Identifiant : <input type="text" name="user"><br>
	Mot de passe : <input type="password" name="password">
	<input type="submit" value="OK">
	</form>
	<% 
	}else{%>
	<h1>DEJA LOG ! </h1>
	<h2><%= session.getAttribute("user") %></h2>
	<h2><%= session.getAttribute("password") %></h2>
	<% 
	}%>
</body>
</html>