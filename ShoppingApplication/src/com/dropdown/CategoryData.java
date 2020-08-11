package com.dropdown;

import java.sql.*;
import java.util.*;

import com.mysql.*;

public class CategoryData {
	
	public List<Category> list() {
        List<Category> listCategory = new ArrayList<>();
         
        try  {
        	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/shopping", "root", "root");
            String sql = "SELECT * FROM category";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
             
            while (result.next()) {
                int id = result.getInt("cid");
                String name = result.getString("cname");
                Category category = new Category(id, name);
                 
                listCategory.add(category);
            }          
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  catch(Exception e) {
        	e.printStackTrace();
        }
         
        return listCategory;
    }

}
