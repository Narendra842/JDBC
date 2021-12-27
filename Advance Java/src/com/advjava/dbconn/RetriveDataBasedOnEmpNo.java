
package com.advjava.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RetriveDataBasedOnEmpNo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con =null; 
		Statement stmt=null;
		ResultSet rs = null;
				
		
		try {
			sc = new Scanner(System.in);
			System.out.print("Enter Your Employee Number to Find Record ");
			int empNo= sc.nextInt();
			// Load JDBC Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Establish the Connection
		    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		    // Create STatement Object
		    stmt=con.createStatement();
		    //prepare query
		    String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE EMPNO="+empNo;
		    // send and execute Query
		    rs=stmt.executeQuery(query);
		    if(rs.next())
		    	System.out.println(""+rs.getInt(1)+" "+rs.getString(2)+ " "+rs.getString(3)+" "+rs.getDouble(4));
		    else
		    	System.out.println("No record Found");
			  }  // try
            catch(SQLException se)
		{
            	se.printStackTrace();
		}
		catch(ClassNotFoundException cfe)
		{
			cfe.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rs!=null)
					rs.close();
				 }
			catch(SQLException sql)
			    {
				sql.printStackTrace();
				 }

			try {
				if(stmt!=null)
					stmt.close();
				 }
			catch(SQLException sql)
			    {
				sql.printStackTrace();
				 }
			try {
				if(con!=null)
					con.close();
				 }
			catch(SQLException sql)
			    {
				sql.printStackTrace();
				 }
			try {
				if(sc!=null)
					sc.close();
				 }
			catch(Exception e)
			    {
				e.printStackTrace();
				 }
		}// finally
	}// main
}// Class
