package com.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

import java.sql.*;

public class ProductFilter {

	List<Product> listProducts = new ArrayList<>();
	
	public Pair<Integer,List<Product>> products(int start,int selectedId) {
		String sql,sql2;
		int count=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/shopping", "root", "root");
		    if(selectedId!=0) {
		    	sql = "SELECT * FROM products WHERE cid="+selectedId+" LIMIT "+start+",8";
		    	sql2 = "SELECT count(*) FROM products WHERE cid="+selectedId;
		    } else {
		    	sql = "SELECT * FROM products LIMIT "+start+",8";
		    	sql2 = "SELECT count(*) FROM products";
		    }
	        Statement statement = con.createStatement();
	        ResultSet result = statement.executeQuery(sql);
	        
	        while (result.next()) {
                int pid = result.getInt("pid");
                int cid = result.getInt("cid");
                String name = result.getString("name");
                int quantity = result.getInt("quantity");
                int price = result.getInt("price");
                Product prod = new Product(pid,cid, name,quantity,price);
                listProducts.add(prod);
                }
	        result = statement.executeQuery(sql2);
            result.next();
            count=result.getInt(1);
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Pair  p = new Pair(count,listProducts);
		
		return p;
	}

	public List<Product> products(int start,int selectedId, String sort,int sortOrder) {
		String sortType;
		if(sortOrder%2==0)
			sortType="ASC";
		else
			sortType="DESC";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/shopping", "root", "root");
		    Statement stmt = con.createStatement();
		    String sql="",sql2;
		    if(selectedId==0) {
		    sql = "SELECT * FROM products" +
	                   " ORDER BY "+sort+" "+sortType+" LIMIT "+start+",8";
		    sql2 = "SELECT count(*) FROM products";
		    } else {
		    	 sql = "SELECT * FROM products WHERE cid=" +selectedId+
		                   " ORDER BY "+sort+" "+sortType+" LIMIT "+start+",8";
		    	 sql2 = "SELECT count(*) FROM products WHERE cid=" +selectedId;
		    }
	      ResultSet result = stmt.executeQuery(sql);
	        while (result.next()) {
                int pid = result.getInt("pid");
                int cid = result.getInt("cid");
                String name = result.getString("name");
                int quantity = result.getInt("quantity");
                int price = result.getInt("price");
                Product prod = new Product(pid,cid, name,quantity,price);
                listProducts.add(prod);
            }  
	        result = stmt.executeQuery(sql2);
            result.next();
            System.out.print(result.getInt(1));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return listProducts;
	}

	public List<Product> search(int start,int selectedId, String sort,String str) {
		str=str+"%";
		str= "%"+str;
		String sql="",sql2;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/shopping", "root", "root");
		    if(selectedId==0) {
		    	sql = "SELECT * FROM products WHERE name LIKE '" + str + "'";
		    } else {
		    	sql = "SELECT * FROM products WHERE cid="+selectedId+" AND name LIKE '" + str + "'";
		    }
		    System.out.print(sql);
		    Statement stmt = con.createStatement();
		    ResultSet result = stmt.executeQuery(sql);
		    while (result.next()) {
                int pid = result.getInt("pid");
                int cid = result.getInt("cid");
                String name = result.getString("name");
                int quantity = result.getInt("quantity");
                int price = result.getInt("price");
                Product prod = new Product(pid,cid, name,quantity,price);
                listProducts.add(prod);
            }
//		    result = stmt.executeQuery(sql2);
//            result.next();
//            System.out.print(result.getInt(1));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return listProducts;
	}
	
	
}
