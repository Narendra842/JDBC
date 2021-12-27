package com.advjava.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertRecordBasedOnUserChoice {
         public static final String INSERT_QUERY="insert into student values(?,?,?,?,?) ";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			int count=0;
			// Read input from user
			sc=new Scanner(System.in);
			if(sc!=null) 
			{	
				System.out.print("\n How Many Record You Want to Insert\t:");
			    count=sc.nextInt();
	          }//if
			//Load Jdbc Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			if(con!=null)
				// Create PreparedStatemrnt Object
				ps=con.prepareStatement(INSERT_QUERY);
			int sNo=0;
			String sName=null,sAddress=null,sCourse=null;
			Float sAvg=0.0F;
			int result=0;
			if(ps!=null)
			{
				System.out.println("\n -------- Insert Your Record Below ---------\n");
			    for(int i=1; i<=count; i++)
			   {
				System.out.println("\n You are now inserting your    "+i+"   Record  out of    "+count+ " \n ");
				System.out.print("Enter Student Id \t:");
				sNo=sc.nextInt();
				System.out.print("Enter Student Name\t:");
				sName=sc.next().toUpperCase();sc.nextLine().toUpperCase();
				System.out.print("Enter Student Address\t:");
				sAddress=sc.next().toUpperCase();
				System.out.print("Enter Student Course\t:");
				sCourse=sc.next().toUpperCase();
				System.out.print("Enter Student Average \t:");
				sAvg=sc.nextFloat();
				//Set the Values of User to SQl Query
				ps.setInt(1, sNo);
				ps.setString(2, sName);
				ps.setString(3,sAddress);
				ps.setString(4, sCourse);
				ps.setFloat(5,sAvg );
				//Excecute Query With user Input
				result=ps.executeUpdate();
				if(result!=0)
					System.out.println("\n"+i+"  Record Inserted Successfully !!!!!!");
				else
					System.out.println(" Sorry !!!!!!Unable to Insert ");
				
				
			}//for
	   }//if
	} //try
		catch(SQLException sql)
		{
			sql.printStackTrace();
		} //catch
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		} //catch
		catch(Exception e)
		{
			e.printStackTrace();
		} //catch
		finally {
			try {
				if(ps!=null)
					ps.close();
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
		} //finally

  } //main
} //class
