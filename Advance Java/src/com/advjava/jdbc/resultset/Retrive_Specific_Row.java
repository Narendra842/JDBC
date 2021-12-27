package com.advjava.jdbc.resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Retrive_Specific_Row {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int rowNo=0;
		try(Scanner sc = new Scanner(System.in);)
		{
			System.out.println("Enter Specefic Row Number To Get Record \t:");
			rowNo= sc.nextInt();
		}//try
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = stmt.executeQuery("SELECT SID,SNAME,SADDRESS,SCOURSE FROM STUDENT");)
		{
			if(rs!=null)
			{
				rs.absolute(rowNo);
				System.out.println(rs.getInt(1)+ "    "+rs.getString(2)+ "   "+rs.getString(3)+ "      "+rs.getString(4));
				
			}//if
		}//try
		
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch

	}//main

}//class
