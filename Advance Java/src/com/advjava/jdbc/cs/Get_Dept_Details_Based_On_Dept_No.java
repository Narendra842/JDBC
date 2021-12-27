package com.advjava.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*
CREATE OR REPLACE PROCEDURE P_GET_DEPT_DETAILS
2  ( DEPTNUMBER IN NUMBER,
3    DEPTNAME OUT VARCHAR2,
4    LOCATION OUT VARCHAR2)AS
5  BEGIN
6  SELECT  DNAME, LOC INTO  DEPTNAME, LOCATION FROM DEPT WHERE DEPTNO=DEPTNUMBER;
7* END;*/

public class Get_Dept_Details_Based_On_Dept_No {
	  private static final String GET_DEPT_DETAILS="{call P_GET_DEPT_DETAILS(?,?,?)}";
	public static void main(String[] args) {
		int deptNo=0;
		try(Scanner sc = new Scanner(System.in);)
		{
			if(sc!=null) {
			System.out.print("\n Enter Department Number\t:");
			deptNo=sc.nextInt();
			}//if
		}//try
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				CallableStatement cs = con.prepareCall(GET_DEPT_DETAILS);)
		{
			if(cs!=null) {
//			rEGISTER OUT PARAMETERS
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			}//if
//			Register IN Parameters
			if(cs!=null)
		     	cs.setInt(1, deptNo);
//			Execute procedure
			if(cs!=null)
			   cs.execute();
			
			System.out.println("\n Dept Name\t:    "+cs.getString(2));
			System.out.println("\n Location Name\t:    "+cs.getString(3));
		}//try
		catch(SQLException sql)
		{
			if(sql.getErrorCode()==1403)
				System.err.println("\n \t No Department No  found !!!!! ");
			else
			sql.printStackTrace();
		}//catch
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
	}//main

}//class
