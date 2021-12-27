package com.advjava.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			sc=new Scanner(System.in);
			String userName=null, userPwd=null;
			if(sc!=null)
			{
			//read input from user
			System.out.print("Enter Your User Name\t:");
			userName=sc.nextLine();
			System.out.print("Enter Your Password\t:");
			userPwd=sc.nextLine();
		 }//if 
         //convert User value into sql Format
			userName="'"+userName+"'";
			userPwd="'"+userPwd+"'";
			//Load JDBC Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			if(con!=null)
				//Creating statement Object
				stmt=con.createStatement();
			// prepare Sql Query
			// SELECT COUNT(*) FROM USERLIST WHERE USERNAME=NARENDRA AND PASSWORD=JOSHI
			String query="SELECT COUNT (*) FROM USERLIST WHERE USERNAME="+userName+"AND PASSWORD="+userPwd;
			System.out.println("\n"+query);
			if(stmt!=null)
			{
				// Process the Result Set
				rs=stmt.executeQuery(query);
				int count=0;
				rs.next();
				count=rs.getInt(1);
				if(count==0)
					System.out.println("\n Given User Id And Password is Not Found");
				else
					System.out.println("\n Login Successfully !!!!!!!!!");
			} //if
		} //try
	    catch(SQLException se)
			{
	    	 if(se.getErrorCode()==903)
	    		 System.err.println("\n Given Table is not Found In Database!!!");
	    	 else
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
			}//finally
	}//main
 
} //class
