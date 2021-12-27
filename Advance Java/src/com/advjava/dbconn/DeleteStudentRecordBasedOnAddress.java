package com.advjava.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteStudentRecordBasedOnAddress {
	public static void main(String[] args) {
		
		Scanner sc=null;
		Connection con=null;
		Statement stmt=null;
		try {
			sc=new Scanner(System.in);
			String delAdd=null;
			if(sc!=null)
			{
				System.out.println("---Enter Student Address To Delete The Record\n");
				System.out.print("Enter Address which you want to Delete\t:");
				delAdd= sc.next().toUpperCase();
				//convert user input to sql Query
				delAdd="'"+delAdd+"'";
			}
			// LOad Jdbc Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Establish Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			if(con!=null)
				// Create statement Object
			stmt=con.createStatement();
			// Prepare SQL QUERY 
			// DELETE FROM STUDENT WHERE SADDRESS='NANDED';
			String query= "DELETE FROM STUDENT WHERE SADDRESS="+delAdd;
			int count=0;
			  if(stmt!=null)
			  {
				 count=stmt.executeUpdate(query);
				 if(count==1)
					 System.out.println(count+ "   Record  deleted");
				 else if(count!=0)
					 System.out.println(count+ "   Records  deleted");
				 else
					 System.out.println("No Record Found To Delete");
			  } // if
		}  //try
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}//catch
		catch(ClassNotFoundException cfs)
		{
			cfs.printStackTrace();
		}//catch
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
		finally
		{
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
		}//main
		
		
	}//class


