package com.advjava.jdbc.other;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database_MetaData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props=null;
		try(InputStream io = new FileInputStream("src/com/advjava/jdbc/common/driverInfo.properties");)
		{
			props= new Properties();
			props.load(io);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try(Connection con= DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.user"), props.getProperty("jdbc.password"));
			)
		{
			DatabaseMetaData dbmd= null;
			if(con!=null)
				 dbmd=con.getMetaData();
			
			if(dbmd!=null)
			{
				System.out.println("DataBase Name              \t:"+dbmd.getDatabaseProductName());
				System.out.println("DataBase Major Version \t:"+dbmd.getDatabaseMajorVersion()+"\nDatabase Minor Version\t: "+dbmd.getDatabaseMinorVersion());
				System.out.println("Database Driver Name\t:"+dbmd.getDriverName());
				System.out.println("Current Database Driver Version\t:"+dbmd.getDriverVersion());
				System.out.println("Database User Name\t:"+dbmd.getUserName());
				System.out.println("Database Supporting KeyWord List"+dbmd.getSQLKeywords());
				
			}
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
