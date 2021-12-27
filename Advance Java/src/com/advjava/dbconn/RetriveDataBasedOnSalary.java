package com.advjava.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RetriveDataBasedOnSalary {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method st
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Minimum Salary \t:");
		String min = sc.next();
		min="'"+min+"'";
		System.out.print("Enter Maximum Salary   \t:");
		String max = sc.next();
		max="'"+max+"'";
		String query= "SELECT * FROM EMP WHERE SAL BETWEEN"+min+"and"+max;
		
  
	   // Load JDBC Driver Class
	   Class.forName("oracle.jdbc.driver.OracleDriver");
	   // Establish The Connection
	   Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
	   //Create Statement Object
	   Statement stmt = con.createStatement();
	   // Execute Query
	   ResultSet rs = stmt.executeQuery(query);
	   
	   boolean flag= false;
	   
	   while(rs.next()!=false)
	   {
		   flag=true;
		   System.out.println(rs.getString("empno")+"  "+rs.getString("ename")+"   "
				                                               +rs.getString("job")+"   "+rs.getString("sal"));
	   }////End Of While
	   if(!flag)
	   System.out.println("No Record Found SOrry !!! Recheck in Database");
	   
	   rs.close();
	   stmt.close();
	   con.close();
	   sc.close();
	}
}
		    

    


