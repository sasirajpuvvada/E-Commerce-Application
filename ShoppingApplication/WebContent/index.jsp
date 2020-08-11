<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login page</title>
<link href="index.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="login-box">
<h1>Login</h1>
<form action="LoginController" method="POST">
<div class="textbox">
<input type="text" name="username" placeholder="Username" autocomplete="off"/>
</div>
<div class="textbox">
<input type="password" name="password" placeholder="Password"/>
</div>
<input class ="btn" type="submit" value="Login"/>
</form>
<a href="register.jsp" class ="btn">Register</a>
</div>
<br>
 
<% if(request.getAttribute("registered")!=null){
	out.println("hey <b>"+request.getAttribute("registered")+"!</b> You have registered Sucessfully. Now please Login");
}%>
<% if(session.getAttribute("err")!=null){
	out.println("<b>"+session.getAttribute("err")+"!</b>");
}%>
</body>
</html>                                                              