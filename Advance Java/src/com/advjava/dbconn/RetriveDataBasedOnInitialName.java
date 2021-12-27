package com.advjava.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RetriveDataBasedOnInitialName { 
	  public static void main(String[] args) throws ClassNotFoundException, SQLException {
		  
		  Scanner sc = new Scanner(System.in);
		  
		  System.out.print("Enter Inital Character Of Name To Find Record:\t");
		  String findName  = sc.next().toUpperCase();
		  findName="'"+findName+"%'";
		  // Prepare Query 
		  String query = "SELECT * FROM STUDENT WHERE SNAME like "+findName;
		  // Load JDBC driver Class
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  // Establish Connection Between DB And JAva APp
		  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","manager");
		  // Create Statement Object
		  Statement stmt = con.createStatement();
		  // Execute Query
		  ResultSet rs = stmt.executeQuery(query);
		  boolean flag= false;
		  while(rs.next())
		  {
			  flag=true;
			  System.out.println(rs.getString(1)+"  "+rs.getString(2)+" "+rs.getString(3)+"  "+rs.getString(4));
		  } // While
		  
		  if(!flag)
			  System.out.println("No Matching Record Found !!!!!!");
		  rs.close();
		  stmt.close();
		  con.close();
		  sc.close();
		  
		}
	
	}
