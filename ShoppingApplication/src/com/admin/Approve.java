package com.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Approve
 */
@WebServlet("/approve")
public class Approve extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String status= request.getParameter("approval");
		int oid = Integer.parseInt(request.getParameter("oid"));
		System.out.println(status+" "+oid);
		try {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/shopping", "root", "root");
	    String query = "UPDATE  orders Set status = ?  where oid = ?;";
        PreparedStatement pstmt = con.prepareStatement(query);

        pstmt.setString(1, status);
        pstmt.setInt(2, oid);
        pstmt.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("ListReviewData");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
