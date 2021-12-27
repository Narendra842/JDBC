package com.advjava.jdbc.ps;

import java.io.FileReader;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
/*table name :-EMP_DETAIL
SEQUENCE NAME:- EMP_SEQ_ID

*/

public class InsertTextFile {
	public static final String INSERT_VAL="INSERT INTO EMP_DETAIL VALUES (EMP_SEQ_ID.NEXTVAL,?,?,?)";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		Reader rd=null;
		
		try {
//			Read input from user
			sc=new Scanner(System.in);
			String empName=null, empAdd=null, empResume=null;
			if(sc!=null)
			{
				System.out.println("\n --- program to insert text files using clob data type -----\n");
				System.out.print("Enter Employee Name\t:");
				empName=sc.next();
				System.out.print("Enter Employee Address\t:");
				empAdd=sc.next();
				System.out.print("Enter Location of Resume to  Insert  :");
				empResume=sc.next();			
			} //if
//			Establish The connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
//			create Prepared Statement Object
			if(con!=null)
			ps=con.prepareStatement(INSERT_VAL);
//			Create ReaderStream Object
			rd= new FileReader(empResume);
//			set values to parameters
			if(ps!=null) {
			ps.setString(1, empName);
			ps.setString(2, empAdd);
            ps.setCharacterStream(3, rd);
//            Execute the Query
            int count= ps.executeUpdate();
              if(count!=0)
            	  System.out.println("Your Record Inserted Successfully !!!!!!!!! ");
              else
            	  System.out.println("Record Not inserted");
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
				if(rd!=null)
					rd.close();
			} //try
			catch(Exception e)
			{
				e.printStackTrace();
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

}//class
