package com.register;

import java.io.IOException;

import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.*;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("username1");
		String password=request.getParameter("password1");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		System.out.print(name+" "+password+" "+phone+" "+address);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/shopping", "root", "root");
			Statement s=con.createStatement();
		    s.executeUpdate("INSERT INTO `users`(name,password,phone,address) VALUE ('"+name+"','"+password+"','"+phone+"','"+address+"')");
		    RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
		    request.setAttribute("registered", name);
		    requestDispatcher.forward(request,response);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
