<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="navbar.jsp" %>
<title>Add Products</title>
<link href="homestyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<% if(request.getAttribute("pname")!=null){ %>
<h1 class="header">Update Products</h1>
<%} else { %>
<h1 class="header">Add Products</h1>
<% } %>
<%
if(request.getAttribute("added")!=null){
	String a = (String)request.getAttribute("added");
	out.print(a+" Product Added");
}
%>

<sql:setDataSource var = "ordersdata" driver = "com.mysql.cj.jdbc.Driver"
         url = "jdbc:mysql://localhost:3305/shopping"
         user = "root"  password = "root"/>


 <sql:query dataSource = "${ordersdata}" var = "result">
            SELECT * from category;
         </sql:query>
         
        

		
		<% if(request.getAttribute("pname")!=null){ %>
         
         <div class="addForm">
		<form action="updateProduct" method="GET">
		<input type="hidden" name="pid" value="${pid}"/>
		<div class="textbox1">
		Product Name:- <b>${pname}</b>
		</div>
		<div class="textbox1">
		Update Quantity<input type="number" name="addQuantity" value="${quan}"/>
		</div>
		<div class="textbox1">
		Update Quantity<input type="number" name="price" value="${price}"/>
		</div>
		<input class ="btn1" type="submit" value="Update Product"/>
		</form>
		</div>
         <% } else { %>
         <div class="addForm">
		<form action="AddProducts" method="GET">
		<div class="textbox1">
		<select name="addCid" required="required" class="sec">
		<option value="" selected>Choose Category</option>
		<c:forEach var = "category" items = "${result.rows}">
               <option value="${category.cid}"> ${category.cname}</option>
         </c:forEach>
		</select>
		</div>
		<div class="textbox1">
		<input type="text" name="addProd" placeholder="Product Name" autocomplete="off" required="required"/>
		</div>
		<div class="textbox1">
		<input type="number" name="addQuantity" placeholder="Product Quantity" autocomplete="off" min="1" required="required"/>
		</div>
		<div class="textbox1">
		<input type="number" name="addPrice" placeholder="Product Price" autocomplete="off" min="1" required="required"/>
		</div>
		<input class ="btn1" type="submit" value="Add Product"/>
		</form>
		</div>
         <% } %>
</body>
</html>