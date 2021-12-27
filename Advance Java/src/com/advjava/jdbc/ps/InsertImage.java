package com.advjava.jdbc.ps;

import java.io.FileInputStream;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
/*table name :- ACTOR
SEQUENCE NAME:- SEQ_ACTOR_ID

*/
public class InsertImage {
	public static final String INSERT_PHOTO="insert into actor values (SEQ_ACTOR_ID.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		
		String actName=null,actAdd=null,actPhotoLoc=null;
		try(Scanner sc= new Scanner(System.in))
		{
			if(sc!=null)
			{
				System.out.println("\n --- program to insert text image using blob data type -----\n");	
				System.out.print("Enter Actor Name\t:");
				actName=sc.next();
				System.out.print("Enter Actor address\t:");
				actAdd=sc.next();
				System.out.print("Enter Actor Photo Location");
				actPhotoLoc=sc.next();
		  }//try
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			  PreparedStatement ps = con.prepareStatement(INSERT_PHOTO); 
				InputStream is = new FileInputStream(actPhotoLoc);)
		{
			if(ps!=null)
			{
				
				ps.setString(1, actName);
				ps.setString(2, actAdd);
				ps.setBinaryStream(3, is);
			}//if
			int count=0;
			if(ps!=null)
				count=ps.executeUpdate();
				if(count==0)
					System.out.println("Record is not Inserted ");
				else
					System.out.println("Record is  Inserted !!!");
	
			
		}//try
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		}//main
		
	}//class



