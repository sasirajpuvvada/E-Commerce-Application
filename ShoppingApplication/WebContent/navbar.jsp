<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover {
  background-color: #111;
}
</style>
</head>
<body>

<ul>
  <li><a href="list?refresh=1">Home</a></li>
  <% if(session.getAttribute("username").equals("admin")) { %>
  <li><a href="ListReviewData">Review</a></li>
  <li><a href="adminStatus.jsp">Order Status</a></li>
  <% } else { %>
  <li><a href="myorders.jsp">My Orders</a></li>
  <li><a href="cart.jsp">Cart</a></li>
  <%} %>
  <li><a href="logout.jsp">Log Out</a></li>
</ul>

</body>
</html>
