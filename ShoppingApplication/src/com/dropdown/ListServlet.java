package com.dropdown;

import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.product.Product;
import com.product.ProductData;
import com.product.ProductFilter;


@WebServlet("/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryData categoryData;
	HttpSession session;
	boolean flag=false;
	String SortText = "";
	int sortOrder = -1;                  //for determining whether sort is ASC or DESC even for ASC
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		categoryData = new CategoryData();
		 session = request.getSession();
        try {
 
            List<Category> listCatagory = categoryData.list();
            request.setAttribute("listCategory", listCatagory);
//            request.setAttribute("tpages", 12);
            List<Product> listProducts = new ProductData().products();
            request.setAttribute("listProducts", listProducts);
            session.setAttribute("selectedCatId", 0);
            request.setAttribute("setPage", 1);
//            Gson gson = new Gson();
//    		String productsJson = gson.toJson(listProducts);
//    		
//    		PrintWriter out = response.getWriter();
//    		response.setContentType("application/json");
//    		response.setCharacterEncoding("UTF-8");
//    		out.write(productsJson);
//    		out.close();
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.print(request.getParameter("category"));
//		int cid = Integer.parseInt(request.getParameter("category")) ;
		if(request.getParameter("refresh")!=null)
			doPost(request,response);
		List<Product> listProducts=null;
		int cid = request.getParameter("category")==null? 0 : Integer.parseInt(request.getParameter("category"));
		session = request.getSession();
		session.setAttribute("selectedCatId", cid);
		request.setAttribute("listCategory", categoryData.list());
		int page1 = request.getParameter("page")==null? 1 : Integer.parseInt(request.getParameter("page"));
		request.setAttribute("setPage", page1);
		int page = (page1-1)*8;
		String sortValue = (String)request.getParameter("sort");
		if(flag==false &&request.getParameter("sort")!=null) {
			flag=true;
			SortText = sortValue;
			if(request.getParameter("sort")==null) {
				request.setAttribute("sortorder", null);
				if(request.getParameter("cat")!=null) {
					cid=Integer.parseInt(request.getParameter("cat"));
				}
				listProducts = new ProductFilter().products(page,cid).getValue();
				int count = new ProductFilter().products(page,cid).getKey();
				System.out.println("count="+count);
				request.setAttribute("tpages", count);
			} else {
				System.out.print("yes");
				cid = Integer.parseInt(request.getParameter("show"));
				if(request.getParameter("cat")!=null) {
					cid=Integer.parseInt(request.getParameter("cat"));
				}
				session.setAttribute("selectedCatId", cid);
				String sort = request.getParameter("sort");
				System.out.print("heyyyyyy"+sort);
				if(sort.equals("search")) {
					request.setAttribute("sortorder", null);
					String str = request.getParameter("searchtxt");
					listProducts = new ProductFilter().search(page,cid,sort,str);
				} else {
					if(page1==1)
					sortOrder++;
					if(sortOrder%2==0) request.setAttribute("sortorder", "increasing"); else request.setAttribute("sortorder", "decreasing");
					listProducts = new ProductFilter().products(page,cid,sort,sortOrder);
				}
				
			}
		} else if(flag==true && page1>1) {
			if(sortOrder%2==0) request.setAttribute("sortorder", "increasing"); else request.setAttribute("sortorder", "decreasing");
			listProducts = new ProductFilter().products(page,cid,SortText,sortOrder);
		} else {
			flag=false;
			if(request.getParameter("sort")==null) {
				request.setAttribute("sortorder", null);
				if(request.getParameter("cat")!=null) {
					cid=Integer.parseInt(request.getParameter("cat"));
				}
				listProducts = new ProductFilter().products(page,cid).getValue();
				int count = new ProductFilter().products(page,cid).getKey();
				System.out.println("count="+count);
				request.setAttribute("tpages", count);
			} else {
				System.out.print("yes");
				cid = Integer.parseInt(request.getParameter("show"));
				if(request.getParameter("cat")!=null) {
					cid=Integer.parseInt(request.getParameter("cat"));
				}
				session.setAttribute("selectedCatId", cid);
				String sort = request.getParameter("sort");
				System.out.print("heyyyyyy"+sort);
				if(sort.equals("search")) {
					request.setAttribute("sortorder", null);
					String str = request.getParameter("searchtxt");
					listProducts = new ProductFilter().search(page,cid,sort,str);
				} else {
					if(page1==1)
					sortOrder++;
					if(sortOrder%2==0) request.setAttribute("sortorder", "increasing"); else request.setAttribute("sortorder", "decreasing");;
					listProducts = new ProductFilter().products(page,cid,sort,sortOrder);
				}
				
			}
		}
		
		request.setAttribute("listProducts", listProducts);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
         dispatcher.forward(request, response);
	}
	

}
