package com.advjava.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GetDeptDetailsBasedOnDeptNo {
	        
	 public static void main(String[] args) {
		 
		 Scanner sc=null;
		 Connection con = null;
		 Statement stmt=null;
		 ResultSet rs=null;
		 try {
		 sc=new Scanner(System.in);
		 System.out.print("Enter Department Number:\t");
		 int deptno = sc.nextInt();
		 
		 // Load jdbc driver class
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 // Establish the Connection
		 con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 // create statement object
		 stmt= con.createStatement();
		 // Prepare Sql Query
		 // "SELECT EMPNO,ENAME,DEPTNO,JOB FROM EMP WHERE  DEPTNO=20
		 rs=stmt.executeQuery("SELECT EMPNO, ENAME,DEPTNO,JOB FROM EMP WHERE DEPTNO="+deptno+"ORDER BY JOB");
		 boolean flag=false;
		 while(rs.next())
		 {
			 flag=true;
			 System.out.println(rs.getInt(1)+ "  "+rs.getString(2)+"  "+rs.getString(3)+ "  "+rs.getString(4)); 
		 }//while
		
		 if(flag!=true)
			 System.out.println("Records Not Found");
     }//try
		 catch (SQLException se)
		 {
			se.printStackTrace();
		 } //catch
		 catch(ClassNotFoundException cfe)
		 {
			 cfe.printStackTrace();
		 }//catch
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		finally {
			try {
				if(rs!=null)
					rs.close();
			}//try
			catch(SQLException se)
			{
				se.printStackTrace();
				   
			}// catch
			try {
				if(stmt!=null)
					stmt.close();
			}//try
			catch(SQLException se)
			{
				se.printStackTrace();
				   
			}// catch
			try {
				if(con!=null)
					con.close();
			}//try
			catch(SQLException se)
			{
				se.printStackTrace();
	      	}// catch
			try {
				if(sc!=null)
					sc.close();
			      }//try
			catch(Exception e)
			{
				e.printStackTrace();
		    }// catch
			
		 }//finally
	}//main
}//class
