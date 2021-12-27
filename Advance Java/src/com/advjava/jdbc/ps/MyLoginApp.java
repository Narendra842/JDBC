package com.advjava.jdbc.ps;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
/*table name :-USERLIST

*/
public class MyLoginApp {
	public static final String query="SELECT COUNT(*) FROM USERLIST WHERE USERNAME=? AND PASSWORD=? ";
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null; 

		//read input from user
		sc=new Scanner(System.in);
		String uName=null, pWord=null;
		if(sc!=null)
		{
			System.out.println("---Login Application to Perform Validation-------\n");
			System.out.print("Enter User Name\t:");
			uName=sc.next();
			System.out.print("Enter Password\t:");
			pWord=sc.next();
			
		} //if
		//Load jdbc driver class
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		//Establish the connection
		con=DriverManager.getConnection("jdbc:mysql:///student","root","root");
		if(con!=null)
			ps=con.prepareStatement(query);
		int count=0;
		if(ps!=null)
		{
			ps.setString(1, uName);
		    ps.setString(2, pWord);
		    
             rs=ps.executeQuery();
            rs.next();
            count=rs.getInt(1);
            if(count!=0)
            	System.out.println("\n valid Credintials ");
            else
            	System.out.println("\n Invalid Credintials !!!!");
            	    
		} //if
		rs.close();
		ps.close();
		con.close();
		sc.close();
 		
	}//main
		
		
		
	} //class

