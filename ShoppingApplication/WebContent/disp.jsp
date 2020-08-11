<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
int pid = Integer.parseInt(request.getParameter("pid"));
int uid = Integer.parseInt(request.getParameter("uid"));
int actQuan = Integer.parseInt(request.getParameter("quan"));
String pname = request.getParameter("pname");

int price = Integer.parseInt(request.getParameter("price"));
String name = (String)session.getAttribute("username");
String status = request.getParameter("cartBtn");

System.out.print("reached");

if(status.equals("update")){
	System.out.print("reached update");
	request.setAttribute("pname",pname);
	request.setAttribute("pid",pid);
	request.setAttribute("quan",actQuan);
	request.setAttribute("price",price);
	 RequestDispatcher dispatcher = request.getRequestDispatcher("addProducts.jsp");
     dispatcher.forward(request, response);
//	int upQuan = Integer.parseInt(request.getParameter("upQuan"));
	//String query = "UPDATE  products Set quantity = ?  where pid = ?;";
    //PreparedStatement pstmt = con.prepareStatement(query);

    //pstmt.setInt(1, upQuan);
    //pstmt.setInt(2, pid);
    //pstmt.execute();
    //request.setAttribute("category", 0);
    //response.sendRedirect("list");
}else{
	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/shopping", "root", "root");
	int quantity = Integer.parseInt(request.getParameter("points"));
	
	if(quantity>actQuan){
		
		session.setAttribute("alert", "yes");
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
	} else{
	request.setAttribute("addedCart", "Added to Cart");
	price = quantity*price;
	Statement s=con.createStatement();
	s.executeUpdate("INSERT INTO `orders`(pid,uid,pname,quantity,status,price,name) VALUE ('"+pid+"','"+uid+"','"+pname+"',"+quantity+",'"+status+"','"+price+"','"+name+"')");
	if(status.equals("cart")){
		 RequestDispatcher dispatcher = request.getRequestDispatcher("list?refresh=1");
    dispatcher.forward(request, response);
	}else{
		 RequestDispatcher dispatcher = request.getRequestDispatcher("checkout.jsp");
         dispatcher.forward(request, response);
	}
	}
}
%>

</body>
</html>