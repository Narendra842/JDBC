package com.advjava.jdbc.resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scrollable_Resultset_Using_Statement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
   try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
		    Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		     ResultSet rs = stmt.executeQuery("SELECT EMPNO, ENAME,JOB,SAL FROM EMP");)
   {
//	   get rs from Top To Bottom
	   if(rs!=null) {
		   System.out.println("\n  Record from top to bottom\n ");
	   while(rs.next())
	       {
		   System.out.println(rs.getRow()+"  "+rs.getInt(1)+ "  "+rs.getString(2)+ " "+rs.getString(3)+ " "+rs.getFloat(4));
	       }//while
	   rs.afterLast();
	   }//if
	   System.out.println("\n  Record from bottom to top\n ");
	   if(rs!=null) {
//		   bottom to top
		   while(rs.previous())
		   {
			   System.out.println(rs.getRow()+"  "+rs.getInt(1)+ "  "+rs.getString(2)+ " "+rs.getString(3)+ " "+rs.getFloat(4));
		   }//while
	   }//if
	   
//	   Moving Cursor Randomly
	   if(rs!=null)
	   {
		   rs.first();//moves cursor to first position
		   System.out.println(rs.getRow()+"  "+rs.getInt(1)+ "  "+rs.getString(2)+ " "+rs.getString(3)+ " "+rs.getFloat(4));
		   
		   rs.last(); //move cursor to last position
		   System.out.println(rs.getRow()+"  "+rs.getInt(1)+ "  "+rs.getString(2)+ " "+rs.getString(3)+ " "+rs.getFloat(4));
		   
		   rs.relative(-10);//move cursor position from current position to given previous position
		   System.out.println(rs.getRow()+"  "+rs.getInt(1)+ "  "+rs.getString(2)+ " "+rs.getString(3)+ " "+rs.getFloat(4));
		   
		   rs.relative(4);//move cursor position from current position to given next position
		   System.out.println(rs.getRow()+"  "+rs.getInt(1)+ "  "+rs.getString(2)+ " "+rs.getString(3)+ " "+rs.getFloat(4));
		   
		   rs.absolute(6); //moves cursor to exact given position (starts with starting position  )
		   System.out.println(rs.getRow()+"  "+rs.getInt(1)+ "  "+rs.getString(2)+ " "+rs.getString(3)+ " "+rs.getFloat(4));
		   
		   rs.absolute(-13);  //moves cursor to exact given position (starts with last position  )
		   System.out.println(rs.getRow()+"  "+rs.getInt(1)+ "  "+rs.getString(2)+ " "+rs.getString(3)+ " "+rs.getFloat(4));
		   
		   
		   
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
