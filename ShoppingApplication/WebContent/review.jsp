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
<title>Review Items</title>
<link href="homestyle.css" rel="stylesheet" type="text/css">
</head>
<body>

<%
if(session.getAttribute("username")==null)
	response.sendRedirect("index.jsp");
%>

<%@ include file="navbar.jsp" %>



<h2 class="header">Hey <%= session.getAttribute("username") %> </h2>
         
 <center>
<div class="pagination">
<% if((Integer)request.getAttribute("setPage")==1) { %>
<a class="active" href="ListReviewData?page=1">1</a>
<a href="ListReviewData?page=2">2</a>
<%} else { %>
<a  href="ListReviewData?page=1">1</a>
<a class="active" href="ListReviewData?page=2">2</a>
<%} %>
</div>
</center>
         
         <div class="row">
         <c:forEach var = "order" items = "${listOrders}">
         <div class="column">
			<div class="card">
			<form action="approve" method="GET">
                <h5><c:out value = "${order.pname}"/></h5>
                <p>Ordered by:${order.name}</p>
                <p>Quantity: <c:out value = "${order.quantity}"/></p>
                <p>Price: &#8377;<c:out value = "${order.price}"/></p>
                <p><c:out value = "${order.status}"/></p>
                <input type="hidden" name="oid" value="${order.oid}"/>
               
                <input type="submit" value="approve" name="approval" class="approve"> <input type="submit" value="decline" name="approval" class="decline">
                
             </form>
            </div>
     		</div>
         </c:forEach>
		</div>



</body>
</html>