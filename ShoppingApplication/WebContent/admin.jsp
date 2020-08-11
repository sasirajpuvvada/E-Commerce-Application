<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
</head>
<body>
<center><h1>Admin Login</h1></center>
<pre>
<form action="LoginController" method="POST">
Enter Username: <input type="text" name="username"/>
Enter Password: <input type="password" name="password"/>
<input type="submit" value="Login"/>
</form>
</pre>
</body>
</html>