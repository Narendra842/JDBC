package com.advjava.jdbc.other;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Parameter_MetaData {
	private static final String STUD_INSERT="INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties prop=null;
		try(InputStream is= new FileInputStream("src/com/advjava/jdbc/common/driverInfo.properties");){
			prop= new Properties();
			prop.load(is);
		}
		catch(IOException io)
		{
			io.printStackTrace();
		}
		
		try(Connection con = DriverManager.getConnection(prop.getProperty("jdbc.url"),prop.getProperty("jdbc.user"), prop.getProperty("jdbc.password"));
				PreparedStatement ps = con.prepareStatement(STUD_INSERT))
        {
	ParameterMetaData pamd= ps.getParameterMetaData();
	  int paraCount = pamd.getParameterCount();
	    for(int i=1; i<=paraCount; i++)
	    {
	    	System.out.println("Parameter Mode"+pamd.getParameterMode(paraCount));
	    	System.out.println("Parameter Type Name"+pamd.getParameterTypeName(paraCount));
	    	System.out.println("Parameter Scale"+pamd.getScale(paraCount));
	    }//for
		
      }//try
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}//main

}//class
