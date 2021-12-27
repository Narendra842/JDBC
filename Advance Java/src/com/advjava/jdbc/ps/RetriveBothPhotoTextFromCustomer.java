package com.advjava.jdbc.ps;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class RetriveBothPhotoTextFromCustomer {
	private static final String RETRIVE_PHOTO_TEXT="SELECT NAME, CITY, INVOICE,PHOTO FROM CUSTOMER WHERE ID=?";
	public static void main(String[] args) {
		
	int userId=0;
	try(Scanner sc= new Scanner(System.in);)
	{
		System.out.println("Enter Your User ID\t:");
		userId=sc.nextInt();
	}//try
	try(Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			PreparedStatement ps = con.prepareStatement(RETRIVE_PHOTO_TEXT);
			)
	{
		//set para values to ps
		if(ps!=null)
	 ps.setInt(1,userId);
		try(ResultSet rs = ps.executeQuery();)
		{
			if(rs!=null) {
				if(rs.next())
				{
					try(Reader rd= rs.getCharacterStream(3);
							Writer wt = new FileWriter("narendraRessume.txt");
							InputStream is = rs.getBinaryStream(4);
							FileOutputStream os = new FileOutputStream("E:/narendraphoto.jpg");)
					{
						System.out.println(rs.getString(1)+"  "+rs.getString(2));
						IOUtils.copy(rd, wt);
						System.out.println("Resume Is Downloaded, And Stord to narendraResume.txt");
						IOUtils.copy(is, os);
						System.out.println("Photo is Downloaded And Stored into narendraphoto.jpg");
						
					}//try
				}//if
				else
				{
					System.out.println("No Give Id's Record Found");
				}
				
			}//if
			
		}//try
 
	 
	} //try
	catch(SQLException sql)
	{
		sql.printStackTrace();
	} //catch
	catch(Exception e)
	{
		e.printStackTrace();
	}//catch

}//main
}//class