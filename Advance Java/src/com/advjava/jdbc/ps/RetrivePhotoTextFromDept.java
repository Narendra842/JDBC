//package com.advjava.jdbc.ps;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class RetrivePhotoTextFromDept {
	private static final String RETRIVE_DEPARTMENT="SELECT ID,NAME,CITY,PHOTO,RESUME FROM DEPARTMENT WHERE ID=?";
	public static void main(String[] args) {
		int id=0;
		try(Scanner sc= new Scanner(System.in);)
		{
			System.out.println("Enter Your id To View \t:");
			id=sc.nextInt();
		}//try
		
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				PreparedStatement ps = con.prepareStatement(RETRIVE_DEPARTMENT);
				)
		{
//			Set Values parameter to ps
				ps.setInt(1, id);
				{
				try (ResultSet rs = ps.executeQuery();)
				{
					 if(rs!=null)
					 {
							if(rs.next())
						{
						    try(InputStream is = rs.getBinaryStream(4);
						    OutputStream os = new FileOutputStream("dRetrive.jpg");
						   Reader rd = rs.getCharacterStream(5);
						    Writer wt = new FileWriter("dRetrive.txt");)
				           { 
							System.out.println(rs.getInt(1)+ " "+rs.getString(2)+ " "+rs.getString(3));
							IOUtils.copy(is, os);
							System.out.println("Photo is retrived and stored as dRetrive.jpg ");
							IOUtils.copy(rd,wt);
							System.out.println("Resume File is  retrived and stored as dRetrive.txt");
						}//try
					}//if
					else
					{
						System.out.println("No Record Found For Giver ID");
					}//else
						
				 }//if
			}//try	
		}//if
		}//try
		 
		 catch(SQLException sql)
		{
			 sql.printStackTrace();
		} //catch
		catch(FileNotFoundException fnf)
		{
			System.err.println("Given File Is Not Found Check Entered address once");
		}//catch
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
	}//main

}//class
