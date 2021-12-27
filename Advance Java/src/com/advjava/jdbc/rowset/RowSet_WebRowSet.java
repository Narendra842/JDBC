package com.advjava.jdbc.rowset;

import java.io.FileWriter;
import java.io.Writer;
import java.sql.SQLException;

import oracle.jdbc.rowset.OracleWebRowSet;

public class RowSet_WebRowSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(OracleWebRowSet wrowset= new OracleWebRowSet();)
		{
			wrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			wrowset.setUsername("system");
			wrowset.setPassword("manager");
			wrowset.setCommand("SELECT * FROM STUDENT");
			wrowset.execute();
			
			Writer writer = new FileWriter("web.xml");
			wrowset.writeXml(writer);
			System.out.println("Table Details/Data Saved In Web.xml File");
			
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
