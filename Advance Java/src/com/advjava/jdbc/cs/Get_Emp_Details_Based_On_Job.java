package com.advjava.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS_BY_JOB
2  (JOB1 IN VARCHAR,
3   JOB2 IN VARCHAR,
4   JOB3 IN VARCHAR,
5   RESULT OUT SYS_REFCURSOR)
6  AS
7  BEGIN
8  OPEN RESULT FOR
9  SELECT EMPNO, ENAME, JOB, SAL,DEPTNO FROM EMP WHERE JOB IN
10  (JOB1,JOB2,JOB3) ORDER BY JOB;
11* END;*/
public class Get_Emp_Details_Based_On_Job {
     private static final String GET_EMP_DETAILS_BY_JOB="{call P_GET_EMP_DETAILS_BY_JOB(?,?,?,?)} ";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String job1=null, job2=null, job3=null;
		try(Scanner sc = new Scanner(System.in);)
	{
		if(sc!=null)
		{
			System.out.print("Enter Designation 1\t:");
			job1= sc.next().toUpperCase();
			System.out.print("Enter Designation 2\t:");
			job2=sc.next().toUpperCase();
			System.out.print("Enter Designation 3\t:");
			job3=sc.next().toUpperCase();
		}//if
	}//try
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
		
		try(Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
				CallableStatement cs = con.prepareCall(GET_EMP_DETAILS_BY_JOB);)
		{
			
//			Register Out Parameters
			if(cs!=null)
			  cs.registerOutParameter(4, OracleTypes.CURSOR);
//			Register In Parameters
			  if(cs!=null) {
			cs.setString(1, job1);
			cs.setString(2, job2);
			cs.setString(3, job3);
			  }//if
//			  Execute The Procedure
			  cs.execute();
//			  Gather the result from out parameters
			  ResultSet rs = (ResultSet)cs.getObject(4);
			  System.out.println();
			  boolean flag=false;
//			  Process the Result Set
			   while(rs.next())
			   {
				   flag=true;
				   System.out.println(rs.getInt(1)+ "  "+rs.getString(2)+"  "+rs.getString(3)+ "  "+rs.getInt(4)+ "  "+rs.getString(5));
			   }//while
			   if(!flag)
				   System.err.println("\n No Give Employee Found For Selected Job");
			
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
