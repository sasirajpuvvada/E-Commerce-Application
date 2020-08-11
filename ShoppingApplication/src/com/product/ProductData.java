package com.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import com.dropdown.Category;

public class ProductData {

	public List<Product> products() {
		
		List<Product> listProducts = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/shopping", "root", "root");
            String sql = "SELECT * FROM products LIMIT 0,8";
            String sql2 = "SELECT count(*) FROM products";
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
            
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return listProducts;
	}
	
}
