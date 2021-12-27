package com.advjava.jdbc.ps;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
/*table name :-ACTOR


*/

public class RetriveImage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int actId=0;
	try(Scanner sc= new Scanner(System.in);)
	{
		
		if(sc!=null)
		{
			System.out.println("Enter Actror Id\t:");
			actId=sc.nextInt();
		}//if
	}//try
	catch(Exception e)
	{
		e.printStackTrace();
	}//catch
	try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			 PreparedStatement ps = con.prepareStatement("SELECT * FROM ACTOR WHERE ID=?");
			   ) {
//		sETTING VALUES TO QUERY PARAMATER
		ps.setInt(1, actId);
//		Execute the Query
		try(ResultSet rs = ps.executeQuery();)
		{
			if(rs!=null) {
				if(rs.next())
				{
					try(InputStream is = rs.getBinaryStream(4);
							OutputStream os = new FileOutputStream("retrive.jpg");)
					{
						System.out.println(rs.getInt(1)+""+rs.getString(2)+""+rs.getString(3));
//						perform file copy operation using stream
						IOUtils.copy(is, os);
                        System.out.println("photo retived and stored to retrive.jpg");										
					} //try-3
				} //if
				else
				{
					System.out.println("Record Not FOund");
				}//else
			}//if
		}//try-2
	}//try-1
	catch(SQLException sql)
	{
		sql.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	} //main

}//class
