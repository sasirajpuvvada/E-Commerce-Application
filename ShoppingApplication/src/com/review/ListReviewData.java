package com.review;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ListReviewData")
public class ListReviewData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page = request.getParameter("page")==null ? 1 : Integer.parseInt(request.getParameter("page"));
		request.setAttribute("setPage", page);
		int start = (page-1)*8;
		List<OrderModel> listOrders = new FetchOrders().fetchOrders(start);
		request.setAttribute("listOrders", listOrders);
		RequestDispatcher dispatcher = request.getRequestDispatcher("review.jsp");
        dispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
