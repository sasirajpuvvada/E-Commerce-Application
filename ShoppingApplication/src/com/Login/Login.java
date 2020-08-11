package com.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {

	
	public static int  AuthenticateUser(String username,String password) {
		
		try {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/shopping", "root", "root");
		String query = "select * from users where name=? and password =?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1,username);
		ps.setString(2,password);
//		System.out.println("next");
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getInt("id");
		} else {
			return 0;
		}

	} catch(Exception e) {
		System.out.println(e);
	}
		return 0;
		
	}

}
