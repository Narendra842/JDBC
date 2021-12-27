package com.advjava.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*
CREATE OR REPLACE PROCEDURE P_AUTHENTICATION
2  (USERID IN VARCHAR, USERPWD IN VARCHAR, RESULT OUT VARCHAR)
3  AS
4  COUNTS  NUMBER(1);
5  BEGIN
6  SELECT COUNT(*) INTO COUNTS FROM USERLIST WHERE USERNAME=USERID AND PASSWORD=USERPWD;
7  IF(COUNTS<>0) THEN
8  RESULT:='LOG IN SUCCESSFULL !!!!';
9  ELSE
10  RESULT:= 'LOG IN FAIL, USERNAME OR PASSWORD IS INVALID';
11  END IF;
12 END;*/

public class Log_In_App {
     private static final String Authentication_Procedure="{call P_AUTHENTICATION(?,?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String uName=null, uPwd=null;
		try(Scanner sc = new Scanner(System.in);)
		{
			if(sc!=null)
			{
				System.out.print("Enter User Name\t:");
				uName= sc.next();
				System.out.println("Enter Password \t :");
				uPwd=sc.next();
			}//if
		}//try
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				CallableStatement cs = con.prepareCall(Authentication_Procedure);)
		 {
//			Register Out Parameters
			if(cs!=null)
			cs.registerOutParameter(3, Types.VARCHAR);
//			Register In Parameters
			if(cs!=null) {
			cs.setString(1, uName);
			cs.setString(2, uPwd);
			}//if
//			Execute the Query
			if(cs!=null)
				cs.execute();
//			Display the Result's
			
			String Result = cs.getString(3);
			
			System.out.println(Result);
			
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
