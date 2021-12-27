 package com.advjava.dbconn;

import java.sql.DriverManager;
import java.sql.Connection;

public class TestConn   {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		// Creating Driver Object
		
		oracle.jdbc.driver.OracleDriver driver = new oracle.jdbc.driver.OracleDriver();
		
		// Registaring Driver Object
		
		DriverManager.registerDriver(driver);
		
		// Establishing Connection 
		
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
	
		if(conn==null)
			System.out.println("---Connection is not established------");
		else
			System.out.println("----Connection is Established--------");

	}

}
