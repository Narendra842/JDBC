package com.advjava.jdbc.other;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

public class Property_FIles {
	private static final String QUERY1="SELECT SID, SNAME, SADDRESS FROM STUDENT";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Properties props=null;
		try(InputStream is = new FileInputStream("src/com/advjava/jdbc/common/driverInfo.properties");)
		{
			props=new Properties();
			props.load(is);
		}//try
		catch(IOException io)
		{
			io.printStackTrace();
		}//catch


	try(Connection con = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.user"), props.getProperty("jdbc.password"));
			 CallableStatement cs = con.prepareCall(QUERY1);)
	{
	ResultSet rs = cs.executeQuery();
	while(rs.next())
	{
		System.out.println(rs.getInt(1)+ "  "+rs.getString(2)+ "   "+rs.getString(3)) ;
	}//while
		 
	}//try
	catch(Exception e)
	{
		e.printStackTrace();
	}//catch
	}//main
}//class
