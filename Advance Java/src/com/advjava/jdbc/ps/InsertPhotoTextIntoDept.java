package com.advjava.jdbc.ps;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
/*Table Name :- Department
Table Sequence :- SEQ_DEPTID*/

public class InsertPhotoTextIntoDept {
     private static final String INSERT_INTO_DEPARTMENT_TABLE="insert into department values(SEQ_DEPTID.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name=null, add=null,photo=null, resume=null;
		try(Scanner sc = new Scanner(System.in)){
			System.out.print("\nEnter Name  \t:");
			name=sc.next();
			System.out.print("\nEnter Address/city\t: ");
			add=sc.next();
			System.out.print("\nEnter Photo Locaion To Insert\t:");
			photo=sc.next();
			System.out.print("\nEnter Resume Location To Insert");
			resume= sc.next();
		} //try
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				PreparedStatement ps = con.prepareStatement(INSERT_INTO_DEPARTMENT_TABLE);
				Reader rd = new FileReader(resume);
				InputStream is = new FileInputStream(photo);)
		{
//			Set Param TO PS 
			if(ps!=null)
			{
				ps.setString(1, name);
				ps.setString(2,add);
				ps.setBinaryStream(3, is);
				ps.setCharacterStream(4,rd);
				
				int result=0;
				 if(ps!=null)
					 result=ps.executeUpdate();
				 if(result!=0)
					 System.out.println("\n \tHello Your Record Inserted with photo and Resume");
				
			}//if
		}//try
		catch(SQLException sql)
		{
		sql.printStackTrace();
		}//catch
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch

	}//main

}//class
