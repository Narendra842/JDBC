package com.advjava.jdbc.rowset;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleJDBCRowSet;

public class Rowset_OracleJdbcRowset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(OracleJDBCRowSet jrowset= new OracleJDBCRowSet();)
		{
			jrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			jrowset.setUsername("system");
			jrowset.setPassword("manager");
			jrowset.setCommand("SELECT * FROM STUDENT");
			jrowset.execute();
			
			 while(jrowset.next())
			 {
				 System.out.println(jrowset.getInt(1)+"  "+jrowset.getString(2)+"   "+jrowset.getString(3)+"  "+jrowset.getString(4)) ;
			 }//while
			
		}//try
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}//catch
		catch(Exception e )
		{
			e.printStackTrace();
		}//catch

	}//main

}//class
