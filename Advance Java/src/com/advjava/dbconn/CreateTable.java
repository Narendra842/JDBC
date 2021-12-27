package com.advjava.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String query="CREATE TABLE INDIA (no number(20), Name varchar(20))";
		
		// Load JDBC Driver Class
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// Esatablish the Connection
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		// create statement object
		Statement stmt = con.createStatement();
		// Execute Query
		ResultSet rs = stmt.executeQuery(query);
        if(rs!=null)
		System.out.println("Table is Created !!!!!!!!");
        else
        	System.out.println("Table Is Not Created");
        
        rs.close();
        stmt.close();
        con.close();
		
		
		

	}

}
