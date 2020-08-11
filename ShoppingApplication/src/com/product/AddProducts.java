package com.product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddProducts
 */
@WebServlet("/AddProducts")
public class AddProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.print("category"+request.getParameter("addCid"));
		int cid = Integer.parseInt(request.getParameter("addCid"));
		String pname= request.getParameter("addProd");
		int quantity = Integer.parseInt(request.getParameter("addQuantity"));
		int price = Integer.parseInt(request.getParameter("addPrice"));
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/shopping", "root", "root");
			Statement s=con.createStatement();
		    s.executeUpdate("INSERT INTO `products`(cid,name,quantity,price) VALUE ('"+cid+"','"+pname+"','"+quantity+"','"+price+"')");
		    request.setAttribute("added", pname);
		    RequestDispatcher requestDispatcher = request.getRequestDispatcher("addProducts.jsp");
		    requestDispatcher.forward(request, response);
			
		} catch(Exception e) {
			System.out.print(e);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
