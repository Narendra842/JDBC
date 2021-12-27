package com.advjava.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateRecordBasedOnSid {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con=null;
		Statement stmt=null;
		
			try {
				sc=new Scanner(System.in);
				int sid=0;
				String sname=null, sadd=null;
				float savg=0.0F;
				if(sc!=null)
				{
				//Read Input From User
				System.out.println("--Update Student Info. Based on Student id\t:"+"\n");
				System.out.print("\nEnter Student ID to update record\t\t:");
				sid=sc.nextInt();
				System.out.print("\nEnter New Student Name To Update\t:");
				sname=sc.next().toUpperCase();
				System.out.print("\nEnter New Student Address\t\t:");
				sadd=sc.next().toUpperCase();
				System.out.print("\nEnter New Student Average Marks\t\t:");
				savg=sc.nextFloat();
				//convert User Input Values to SQL QUERY Format
				sname="'"+sname+"',";
				sadd="'"+sadd+"',";
				
				}
				// load jdbc driver Class
				Class.forName("oracle.jdbc.driver.OracleDriver");
				// Establish Connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				// create Statement Object
				stmt=con.createStatement();
				//Prepare SQL Query to Process
				// INSERT INTO STUDENT SET SNAME='KETAN',SADDRESS='JALNA',SAVG=67 WHERE SID=3;
				String query="UPDATE STUDENT SET SNAME="+sname+"SADDRESS="+sadd+"SAVG="+savg+"WHERE SID="+sid;
				int count=0;
				 if(stmt!=null)
				 {
					 count=stmt.executeUpdate(query);
					 if(count!=0)
						 System.out.println("\nYour Information Updateded Successfully !!!!!\n");
					 else
						 System.out.println("\nGiven Student ID is Not Found Unable To Update the Record !!!!!!\n");
				 }// if
				
		  } //try
			catch(SQLException sql)
			{
				sql.printStackTrace();
			} //catch
			catch(ClassNotFoundException cnfe)
			{
				cnfe.printStackTrace();
			} //catch
			catch(Exception e)
			{
				e.printStackTrace();
			} //catch
			finally {
				try {
					if(stmt!=null)
						stmt.close();
				} //try
				catch(SQLException sql)
				{
					sql.printStackTrace();
				}//catch
				try {
					if(con!=null)
						con.close();
				} //try
				catch(SQLException sql)
				{
					sql.printStackTrace();
				}//catch
				try {
					if(sc!=null)
						sc.close();
				} //try
				catch(Exception e)
				{
					e.printStackTrace();
				}//catch
			}//finally
	}//main

}//class
