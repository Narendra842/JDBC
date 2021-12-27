package com.advjava.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InserRecordIntoStudentTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\n -- INSERTING RECORD INTO STUDENT TABLE\n");
		Scanner sc=null;
		Connection con=null;
		Statement stmt=null;
		try {
			sc=new Scanner(System.in);
			int sid=0;   
			String sname=null,sadd=null,scourse=null;
			float savg=0.0F;
			if(sc!=null)
			{
				// read input from user
				System.out.print("Enter Student Id\t\t\t:");
				sid=sc.nextInt();
				System.out.print("Enter Student Name\t\t:");
				sname=sc.next().toUpperCase();
				System.out.print("Enter Student Address\t\t:");
				sadd=sc.next().toUpperCase();
				System.out.print("Enter  Student Course\t\t:");
				scourse=sc.next().toUpperCase();
				System.out.print("Enter Student Average\t\t:");
				savg=sc.nextFloat();
			}
			//Convert User Input Into Sql Query
			sname="'"+sname+"'";
			sadd="'"+sadd+"'";
			scourse="'"+scourse+"'";
			// Load JDBC Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish The Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Create Statement Object
			if(con!=null)
				stmt=con.createStatement();
			// prepare Sql Query:-
			// INSERT INTO STUDENT VALUES(8,'NARESH','THANE','BCOM',56.88)
			String query= "INSERT INTO STUDENT VALUES("+sid+","+sname+","+sadd+","+scourse+","+savg+")";
			int count=0;
			if(stmt!=null)
			{
				count=stmt.executeUpdate(query);
				if(count!=0)
					System.out.println("\nRecord Inserted Succesfully!!!!!\n");
				else
					System.out.println("\nSorry ! Recorded Not Inserted\n");
			} //if
			
	  	}// try
		catch(SQLException sql){
			if(sql.getErrorCode()==1)
				System.err.println("\nDuplicate or Empty Value Can not be Inserted to PK coloum sno");
			if(sql.getErrorCode()>=900 && sql.getErrorCode()<=1000)
				System.err.println("\nSQL Query Not Written Properly, Please Recheeck it ");
			else if(sql.getErrorCode()==12899)
				System.err.println("\nEntered One Of the Above Value Is Too Large!!!!!!!!!1");
			else {
				System.err.println("Unknown Problem is Occured ");
				sql.printStackTrace();
			   }
		} // catch
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		} //catch
		catch(Exception e)
		{
			e.printStackTrace();
		}// catch
		finally {
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
		} //finally

	} //main

} //class
