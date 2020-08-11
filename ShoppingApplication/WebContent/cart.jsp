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
<title>My Cart</title>
<link href="homestyle.css" rel="stylesheet" type="text/css">
</head>
<body>

<%
if(session.getAttribute("username")==null)
	response.sendRedirect("index.jsp");
%>



<%@ include file="navbar.jsp" %>

<h1 class="header"><%= session.getAttribute("username") %>'s Cart</h1>

<sql:setDataSource var = "ordersdata" driver = "com.mysql.cj.jdbc.Driver"
         url = "jdbc:mysql://localhost:3305/shopping"
         user = "root"  password = "root"/>
         
         
         <c:set var = "user" value ="${sid}"/>
         
		
         <sql:query dataSource = "${ordersdata}" var = "result">
            SELECT * from orders where uid=? and status='cart';
            <sql:param value = "${user}" />
         </sql:query>
         <form action="cartBuy" method="GET">
         
         <table class="content-table">
         <thead>
         <tr>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Status</th>
            <th>Buy Now</th>
         </tr>
         </thead>
         <tbody>
         <c:forEach var = "order" items = "${result.rows}">
         <tr>
               <td> <h3><c:out value = "${order.pname}"/></h3> </td>
               <td> <c:out value = "${order.quantity}"/></td>
               <td> <c:out value = "${order.price}"/></td>
                <td><c:out value = "${order.status}"/></td>
                <td> <input type="checkbox" checked="true" value="${order.oid}" name="selected"/> </td>
         </tr>
         </c:forEach>
         </tbody>
         </table>
         
         <center><input type="submit" value="Buy Selected items" class="buyAll"></center>
		</form>
</body>
</html>