package com.advjava.jdbc.resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Scrollable_Resultset_Using_Ps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				PreparedStatement ps = con.prepareStatement("SELECT EMPNO,ENAME,JOB, SAL FROM EMP",
						                                                                   ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				 ResultSet rs = ps.executeQuery();)
		{
			if(rs!=null)
			{
				while(rs.next())
				{
					System.out.println(rs.getRow()+"    "+rs.getInt(1)+"      "+rs.getString(2)+"         "+rs.getString(3)+ "     "+rs.getFloat(4) );
				}//while
				while(rs.previous())
				{
					System.out.println(rs.getRow()+"    "+rs.getInt(1)+"      "+rs.getString(2)+"         "+rs.getString(3)+ "     "+rs.getFloat(4) );	
				}//while
//				Moving Cursor Randomly
				rs.last();
				  System.out.println(rs.getRow()+"    "+rs.getInt(1)+"      "+rs.getString(2)+"         "+rs.getString(3)+ "     "+rs.getFloat(4) );
				  
				rs.first();
				  System.out.println(rs.getRow()+"    "+rs.getInt(1)+"      "+rs.getString(2)+"         "+rs.getString(3)+ "     "+rs.getFloat(4) );
				  
				  rs.relative(4);
				  System.out.println(rs.getRow()+"    "+rs.getInt(1)+"      "+rs.getString(2)+"         "+rs.getString(3)+ "     "+rs.getFloat(4) );
				  
				  rs.relative(-2);
				  System.out.println(rs.getRow()+"    "+rs.getInt(1)+"      "+rs.getString(2)+"         "+rs.getString(3)+ "     "+rs.getFloat(4) );
				  
				  rs.absolute(12);
				  System.out.println(rs.getRow()+"    "+rs.getInt(1)+"      "+rs.getString(2)+"         "+rs.getString(3)+ "     "+rs.getFloat(4) );
				  
				  rs.absolute(-10);
				  System.out.println(rs.getRow()+"    "+rs.getInt(1)+"      "+rs.getString(2)+"         "+rs.getString(3)+ "     "+rs.getFloat(4) );
				
				
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
