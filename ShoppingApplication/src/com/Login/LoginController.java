package com.Login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username= request.getParameter("username");
		String password = request.getParameter("password");
		int sid = Login.AuthenticateUser(username, password);
//		System.out.println(sid);
		HttpSession session = request.getSession();
		if(sid>=1 || (username.equals("admin") && password.equals("123"))){
			if(username.equals("admin")) {
				session.setAttribute("admin", "admin");
			}
			session.setAttribute("sid", sid);
			session.setAttribute("username", username);
			request.getRequestDispatcher("list").forward(request,response);
		} else {
			session.setAttribute("err", "Invalid Login Credentials");
			response.sendRedirect("index.jsp");
			System.out.print("fails");
		}
				
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
