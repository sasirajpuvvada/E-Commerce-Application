<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>


<%
if(session.getAttribute("username")==null)
	response.sendRedirect("index.jsp");
System.out.print(session.getAttribute("username"));
%>

<meta charset="ISO-8859-1">
<title>Welcome to Shopping Center</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" type="text/javascript"></script>

<script type="text/javascript">

function myFunc(quantity,pid){
	var k = "points"+pid;
	var inpQuantity = document.getElementById(k).value;
	if(inpQuantity>quantity){
		alert("invalid");
		return false;
	}
}

$(document).ready(function(){
	
	
	
	
	//$("#up").hide();
	$("#option").change(function(){
	
		var cid = document.getElementById('option').value;
		
		$.ajax({
			type:"GET",
			data:{cid:cid},
			url:"list",
			
		}); 
		
	});
	
	$("#").change(function(){

	$.ajax({
		type:"POST",
		url:"list",
		success:function(result){
			
		},
		error : function(e){
		alert("failed referesh");
		}
	});
	});
	
});




</script>


<link href="homestyle.css" rel="stylesheet" type="text/css">


</head>
<body>

<br/>


<%
if(session.getAttribute("username")!=null){%>
<%@ include file="navbar.jsp" %>
<%} %>
<br>
<br>
<pre>
<h2 class="header">Hey <%= session.getAttribute("username") %>!</h2>
</pre>
<center>
<% if(session.getAttribute("username").equals("admin")){ %>
<div class="addItems">
<a href="addCategory.jsp">Add Category</a>
<a href="addProducts.jsp">Add Products</a>
</div>
<%} %>
<h1>Our Products</h1></center>

<%
if(request.getAttribute("addedCart")!=null){
	out.print(request.getAttribute("addedCart"));
}
%>

<div align="center">
    <form action="list" method="GET">
        Select a Category:&nbsp;
        <select name="category" id="option">
        	<option value="0">All</option>
            <c:forEach items="${listCategory}" var="category">
                <option value="${category.id}"
                    <c:if test="${category.id eq selectedCatId}">selected="selected"</c:if>
                    >
                    ${category.name}
                </option>
            </c:forEach>
        </select>
        <br/><br/>
        <input type="submit" value="Submit" />
        <br><br>
    </form>
</div>

<form action="list" method="GET">
<input type="hidden" name="show" value="${selectedCatId}"/>
<p><button  id="sort" name="sort" value="name">Sort By Name</button>&nbsp; &nbsp;<button  id="sort" name="sort" value="price">Sort By Price</button><% if(request.getAttribute("sortorder")!=null){ if(request.getAttribute("sortorder").equals("increasing")) { %> <span class="triangle-up"></span> <%} else { %> <span class="triangle-down"></span> <%}} %> </p>
<p><input name="searchtxt" type="text" autocomplete="off">&nbsp;<button  id="sort" name="sort" value="search">Search</button></p>
</form>
<% int cat = (Integer)session.getAttribute("selectedCatId"); %>
<center>
<div class="pagination">
<% if((Integer)request.getAttribute("setPage")==1) { %>
<a class="active" href="list?page=1&cat=<%=cat%>">1</a>
<a href="list?page=2&cat=<%=cat%>">2</a>
<%} else { %>
<a  href="list?page=1&cat=<%=cat%>">1</a>
<a class="active" href="list?page=2&cat=<%=cat%>">2</a>
<%} %>
</div>
</center>


<div class="row">
	<c:forEach items="${listProducts}" var="products">
	<div class="column">
		<div class="card">
		<form action="disp.jsp" method="GET">
                <h3>${products.pname}</h3>
                <p>price = &#8377;${products.price}</p>
                
                <input type="hidden" name="pid" value="${products.pid}"/>
                <input type="hidden" name="uid" value="${sid}"/>
                <input type="hidden" name="pname" value="${products.pname}"/>
                <input type="hidden" name="price" value="${products.price}"/>
                <input type="hidden" name="quan" value="${products.quantity}"/>
                
                
                <% if(!session.getAttribute("username").equals("admin")){ %>
                <p>Left out Stock: <b> ${products.quantity} </b> </p>
               <p> <input type="number" value="1" id="points${products.pid}" name="points" step="1" min="1" > </p>
               <% } else { %>
               <p>Quantity:- ${products.quantity}</p> 
                <%} %>
               	
                <%  
                if(!session.getAttribute("username").equals("admin")){ %>
                <p><button onclick="return myFunc(${products.quantity},${products.pid})" id="cartBtn" name="cartBtn" value="cart">Add to Cart</button></p>
                <p><button  id="cartBtn" name="cartBtn" value="pending">Buy Now</button></p>
                <% } else { %>
                <p><input type="submit" name="cartBtn" value="update" id="edit"/> 
                <%}%>
                
         </form>
         </div>
     </div>
    </c:forEach>
    

    
</div>


</body>
</html>