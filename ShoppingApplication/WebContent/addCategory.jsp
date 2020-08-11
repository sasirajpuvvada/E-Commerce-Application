<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="navbar.jsp" %>
<meta charset="ISO-8859-1">
<link href="homestyle.css" rel="stylesheet" type="text/css">
<title>Add Category</title>
</head>
<body>
<h1 class="header">Add Category</h1>
<center><h1>Existing Categories</h1></center>


<%
if(request.getAttribute("added")!=null){
	String a = (String)request.getAttribute("added");
	System.out.print(a+" Category Added");
%>
	<h4>added Category</h4>
<% }%>



	<div class="addForm">
	<h1>Add Category</h1>
	<form action="addCategory" method="GET">
	<div class="textbox1">
	<input type="text" name="addCat" placeholder="Category Name" autocomplete="off" required="required"/>
	</div>
	<input class ="btn1" type="submit" value="Add Category"/>
	</form>
	</div>
</body>
</html>