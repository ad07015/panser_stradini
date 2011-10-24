<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Login page</title></head>
<body>
<!-- <img src="stradini_logo.png" align="middle" alt="stradini logo" /> -->
<form method="POST" action='<%= response.encodeURL("j_security_check") %>' >
	<table style="margin-left: auto; margin-right: auto; margin-top: auto; margin-bottom: auto;">
		<tr> 
			<td>Lietotājvārds:</td>
			<td colspan='2'><input type="text" name="j_username"></td>
		</tr>  
		<tr>   
			<td>Parole:</td>
			<td><input type="password" name="j_password"></td>
			<td><input type="submit" value="Log In"></td>
		</tr>
	</table>
</form>
</body>
</html>