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

/*table name :-CUSTOMER
SEQUENCE NAME:- SEQ_CUSTID

*/

public class InsertBothPhotoTextIntoCustomer {
	public static final String INSERT_PHOTO_FILE="INSERT INTO CUSTOMER VALUES (SEQ_CUSTID.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		
	String name=null,address=null,file=null, photo=null;
	try(Scanner sc = new Scanner(System.in);)
	{

		if(sc!=null) {
		System.out.println("Enter  You Name\t:");
		name =sc.next();
		System.out.println("Enter  You Address\t:");
		address =sc.next();
		System.out.println("Enter Location To Upload Resume  \t:");
		file =sc.next();
		System.out.println("Enter Location to ulpoad  Your Passport Photo\t:");
		photo =sc.next();
		}//if
	}//try
	catch(Exception e)
	{
		e.printStackTrace();
	}//catch
	
	try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
		  PreparedStatement ps = con.prepareStatement(INSERT_PHOTO_FILE);
			Reader rd = new FileReader(file); InputStream is = new FileInputStream(photo);)
	{
//		Set values to para
		if(ps!=null) {
		ps.setString(1,name);
		ps.setString(2, address);
		ps.setCharacterStream(3, rd);
		ps.setBinaryStream(4, is);
		}//if
		int result=0;
		if(ps!=null)
		result=	ps.executeUpdate();
		if(result!=0)
			System.out.println("Record Inserted");
		else
			System.out.println("Record Not Inserted");
	}//try
	catch(SQLException sql)
	{
		sql.printStackTrace();
	}//catch
	catch(Exception e)
	{
		e.printStackTrace();
	 }//catch
   } //main
 }//class
