package com.product;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateProduct")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pid = Integer.parseInt(request.getParameter("pid"));
		int upQuan = Integer.parseInt(request.getParameter("addQuantity"));
		int price = Integer.parseInt(request.getParameter("price"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/shopping", "root", "root");
			String query = "UPDATE  products Set quantity = ? , price = ?  where pid = ?;";
			PreparedStatement pstmt = con.prepareStatement(query);
		    pstmt.setInt(1, upQuan);
		    pstmt.setInt(2, price);
		    pstmt.setInt(3, pid);
		    pstmt.execute();
		    request.setAttribute("category", 0);
		    response.sendRedirect("list?refresh=1");
		}catch(Exception e) {
			System.out.print(e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
