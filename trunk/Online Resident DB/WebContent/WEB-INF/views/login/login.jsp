<html>
<head><title>Login page</title></head>
<body>
<form method="POST" action='<%= response.encodeURL("j_security_check") %>' >
<table>
	<tr> 
		<td>Username:</td>
		<td colspan='2'><input type="text" name="j_username"></td>
	</tr>
	<tr>
		<td>Password:</td>
		<td><input type="password" name="j_password"></td>
		<td><input type="submit" value="Log In"></td>
	</tr>
</table>
</form>
</body>
</html>