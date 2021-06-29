<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<fieldset>
		<legend>Login Form</legend>
		<form action="" method="post">
			<table>
				<tr>
					<td>Admin id</td>
					<td>:</td>
					<td> <input type="number" name="id" required="required"> </td>
				</tr>
				<tr>
					<td>Password</td>
					<td>:</td>
					<td> <input type="password" name="password" required="required"> </td>
				</tr>
				<tr>
					<td> <input type="submit" value="Login"> </td>
					
				</tr>
			</table>
		</form>
	</fieldset>
</body>
</html>