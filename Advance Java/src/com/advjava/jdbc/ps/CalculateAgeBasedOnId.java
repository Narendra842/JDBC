package com.advjava.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CalculateAgeBasedOnId {
	public static final String FIND_AGE="SELECT CNAME,  (SYSDATE-DOB)/365.0 FROM CITIZEN_DETAILS WHERE CID=?";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
//			Read Input From User
			sc=new Scanner(System.in);
			int id=0;
			if(sc!=null)
			{
				System.out.print("Enter Your Id to Find Age\t:");
				id=sc.nextInt();
			}//if
//			Load jdbc Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Establish the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			if(con!=null)
				ps=con.prepareStatement(FIND_AGE);
			if(ps!=null)
			{
//				Set VAlue to query parameters
				ps.setInt(1, id);
//				Execute the pre-complied query
				if(ps!=null)
			       rs=ps.executeQuery();
			    if(rs!=null)
//				process the result set
			  if(rs.next())
			  {
				  String name=rs.getString(1);
				  Float age=rs.getFloat(2);
				  System.out.println("\n Person Name is\t:"+name);
				  System.out.println(" Person Age is\t:"+age);
		     }//if
			  else
				  System.out.println("\n Given User Id Not Found");
			}//if

		}//try
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}//catch
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}//catch
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
		finally {
			try {
				if(rs!=null)
					rs.close();
			} //try
			catch(SQLException sql)
			{
				sql.printStackTrace();
			}//catch
			try {
				if(ps!=null)
					ps.close();
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
		} //finally
	}//main
} //class
