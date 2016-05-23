package com.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbconnection {
	private static Connection con;
	
	public static Connection getConnection(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernatetest","root","root");
			//System.out.println("connection ok!");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	

}
