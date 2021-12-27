package com.advjava.dbconn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RetriveFullTable {
	 public static void main(String[] args) throws Exception {
		 
		 // Loading JDBC Driver Class
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 // Creating Connection Object to Establish Connection
		 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 //  Statement Object Creation
		 Statement stmt = con.createStatement();
		 //
		 ResultSet rs = stmt.executeQuery("Select * from student");
		 while(rs.next())
		 {
			 System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"  \t\t \t"+rs.getString(3)+"\t "
		                                     +rs.getString(4));
		 }
		 rs.close();
		 stmt.close();
		 con.close();
		
	}

}
