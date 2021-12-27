package com.advjava.jdbc.ps;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class RetriveTextFile {
	/*table name :- EMP_DETAIL
	 * 
	 * 
	*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;  
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Reader rd=null;
		Writer wt=null;
		    
		try {
			int id=0;
//			Read inputs From user
		sc=new Scanner(System.in);
		if(sc!=null) {
		System.out.print("Enter Your Id To Download Resume\t:");
		id=sc.nextInt();
		 }//if 
//		Establish the Connection 
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
//		Create PreparedStatement Object
		if(con!=null)
		ps=con.prepareStatement("SELECT * FROM EMP_DETAIL WHERE EMPID=?");
//		set values to query param
		if(ps!=null)
		{
			ps.setInt(1, id);
//			Process the Result Set
			rs=ps.executeQuery();
			if(rs!=null) {
				if(rs.next()) {
			rd=rs.getCharacterStream(4);
			wt=new FileWriter("resume.txt");
			System.out.println(rs.getInt(1)+""+rs.getString(2)+"  "+rs.getString(3));
//			perform File Copying operation
			IOUtils.copy(rd, wt);
			System.out.println("Text File is retrived and stored into resume.txt ");
				} //if
				else
				{
					System.out.println("Record is not found !!!!!1");
				}//else
			}//if
	 }//if		
 }//try
		catch(SQLException sql)
		{
			sql.printStackTrace();
		} //catch
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
		finally
		{
			try {
				if(wt!=null)
					wt.close();
			} //try
			catch(Exception e)
			{
				e.printStackTrace();
			}//catch
			try {
				if(rd!=null)
					rd.close();
			} //try
			catch(Exception e)
			{
				e.printStackTrace();
			}//catch
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
		}//finally


	}//main

}//
