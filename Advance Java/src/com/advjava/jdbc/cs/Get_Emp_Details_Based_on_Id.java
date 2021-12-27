package com.advjava.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*
CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS_BY_ID
2  (ID IN NUMBER, NAME OUT VARCHAR2, DESG OUT VARCHAR2, SALARY OUT NUMBER)
3  AS
4  BEGIN
5  SELECT ENAME, JOB, SAL INTO NAME, DESG, SALARY FROM EMP WHERE EMPNO=ID;
6* END;*/

public class Get_Emp_Details_Based_on_Id {
    private static final String SERCH_BY_EMP_ID="{call  P_GET_EMP_DETAILS_BY_ID(?,?,?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int number=0;
		try(Scanner sc = new Scanner(System.in);)
		{
			if(sc!=null) {
			System.out.println("Enter Emp Number to Serch Record\t:");
			number = sc.nextInt();
			}//if
			
		}//try
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				CallableStatement cs = con.prepareCall(SERCH_BY_EMP_ID);)
		{
//			Register Out Parameters
			if(cs!=null) {
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.INTEGER);
			}//if
//			Register In Parameters
			if(cs!=null)
			cs.setInt(1, number);
//			Execute The Procedure
			if(cs!=null)
				cs.execute();
			
			String empName= cs.getString(2);
			String empDesg= cs.getString(3);
			int empSalary = cs.getInt(4);
//			Display The Result
			System.out.println("Employe Name= "+empName);
			System.out.println("Employe Designation= "+empDesg);
			System.out.println("Employee Salary= "+empSalary );
			
		}//try
		catch(SQLException sql)
		{
			if(sql.getErrorCode()==1403)
			{
				System.err.println("No Employee Found For Given Id");
			}//if
			else
			sql.printStackTrace();
			
		}//catch
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch

	}//main

}//class
