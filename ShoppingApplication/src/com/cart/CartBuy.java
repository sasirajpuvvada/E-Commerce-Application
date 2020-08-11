package com.cart;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cartBuy")
public class CartBuy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int[] oid = Arrays.stream(request.getParameterValues("selected")).mapToInt(Integer::parseInt).toArray();
		
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/shopping", "root", "root");
			for(int a:oid) {
			String query = "UPDATE  orders Set status = 'pending'  where oid = ?;";
	        PreparedStatement pstmt = con.prepareStatement(query);
	        pstmt.setInt(1,a);
	        pstmt.execute();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("checkout.jsp");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
