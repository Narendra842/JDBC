package com.advjava.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;
/*
CREATE OR REPLACE PROCEDURE P_STUDENT_BY_INITIALCHAR
2  (
3  SNAME IN VARCHAR,
4  RESULT OUT SYS_REFCURSOR)
5  AS
6  BEGIN
7  OPEN  RESULT FOR
8   SELECT ID, NAME,CITY, COURSE, MARKS FROM STUDENT WHERE SNAME LIKE SNAME;
9* END;*/

public class Get_Stud_Details_By_Initial_Char {
      private static final String GET_STUD_DETAILS="{call P_STUDENT_BY_INITIALCHAR(?,?)} ";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String initName=null;
       try(Scanner sc = new Scanner(System.in);)
       {
    	   if(sc!=null) {
    		System.out.println("\n \tNote :- Please add (%) symbol After Initial Character");
    	   System.out.print("\n Enter Initial Character Of Name To Serch Record \t :");
    		initName = sc.next().toUpperCase();
    	   }//if
       }//try
       catch(Exception e)
       {
    	   e.printStackTrace();
       }
       
      try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
    		  CallableStatement cs = con.prepareCall(GET_STUD_DETAILS);)
      {
//    	  Register Out Parameters
    	  if(cs!=null)
    	  cs.registerOutParameter(2, OracleTypes.CURSOR);
//    	  Register In Parameters
    	  if(cs!=null)
    		  cs.setString(1, initName);
//    	  Execute Procedure
    	   if(cs!=null)
    	  cs.execute();
//    	  
    	  ResultSet rs = (ResultSet)cs.getObject(2);	  
    	  boolean flag=false;
//    	  Process the Result Set
    	  System.out.println("\n");
    	  while(rs.next())
    	  {
    		  flag=true;
    		  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+ " "+rs.getString(3)+ "  "+rs.getString(4)+ ""+rs.getString(5) );
    	  }//while
    	  if(!flag)
    		  System.out.println("No Matching Result Found !!!!!!!!");
    	  
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
