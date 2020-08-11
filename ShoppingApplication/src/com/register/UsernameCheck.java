package com.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checkusers")
public class UsernameCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String myuser = request.getParameter("myuser");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/shopping", "root", "root");
			String query = "select * from users where name=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,myuser);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.print("Already Exists");
			}
			} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
