package com.advjava.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteStudentRecordBasedOnAvg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con=null;
		Statement stmt = null;
		try {
			sc=new Scanner(System.in);
	        float minMark=0.0F, maxMark=0.0F;
			if(sc!=null)
			 {
				// read Input From User
				System.out.println("---ENTER MINIMUM AND MAXIMUM MARKS TO DELETE RECORD---\n");
				System.out.print("Enter Mininum Marks \t:");
				minMark=sc.nextFloat();
				System.out.print("Enter Maximum Marks\t:");
				maxMark=sc.nextFloat();
			 } // IF 
			// Load Jdbc Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Establish Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			if(con!=null)
				// Create Statement Object
				stmt=con.createStatement();
			// prepare Sql Query
			// DELETE FROM STUDENT WHERE SAVG>65 AND SAVG<100
			String query="DELETE FROM STUDENT WHERE SAVG>="+minMark+" AND SAVG<="+maxMark;
			// execute this Query in DB Software
			int count =0;
			if(stmt!=null)
				count= stmt.executeUpdate(query);
			
			if(count==0)
				System.out.println("NO Given Records found to Delete");
			else
				System.out.println(count+"record Deleted");
		} //try
		catch(SQLException sql)
		{
			if(sql.getErrorCode()>=900 && sql.getErrorCode()<=1000)
				System.err.println("Sql Query Syntex is Wrong Check it Again");
			else 
			   sql.printStackTrace();
		} //catch
		catch(ClassNotFoundException cfe)
		{
			cfe.printStackTrace();
         } //catch
		catch(Exception e)
		{
			e.printStackTrace();
		} //catch
		finally
		{
			try {
				if(stmt!=null)
					stmt.close();
				} //try
			catch(SQLException sql)
			{
				sql.printStackTrace();
			} //catch
			try {
				if(con!=null)
					con.close();
				} //try
			catch(SQLException sql)
			{
				sql.printStackTrace();
			} //catch
			try {
				if(sc!=null)
					sc.close();
				} //try
			catch(Exception e)
			{
				e.printStackTrace();
			}//catch
		
		}// finally
	
	}//main
} //class
