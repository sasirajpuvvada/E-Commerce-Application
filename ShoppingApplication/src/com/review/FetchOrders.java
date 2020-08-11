package com.review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class FetchOrders {
	
	public List<OrderModel> fetchOrders(int start) {
		List<OrderModel> listOrders = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/shopping", "root", "root");
			String sql = "SELECT * FROM orders WHERE status='pending' LIMIT "+start+",8";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
            	
            	int oid = result.getInt("oid");
            	int pid = result.getInt("pid"); 
            	int uid = result.getInt("uid");
            	String pname = result.getString("pname");
            	int quantity = result.getInt("quantity");
            	String status = result.getString("status");
            	String name = result.getString("name");
            	int price = result.getInt("price");
            	OrderModel order = new OrderModel(oid,pid,uid,pname,quantity,status,name,price);
            	listOrders.add(order);
            }
            
			
		} catch(Exception e) {
			System.out.print(e);
		}
		
		return listOrders;
	}

}
