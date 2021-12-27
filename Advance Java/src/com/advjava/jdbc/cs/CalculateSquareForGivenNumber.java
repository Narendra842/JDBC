package com.advjava.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace procedure p_getsquare (x in number, square out number)
2  as
3  begin
4  square:=x *x;
5* end;*/

public class CalculateSquareForGivenNumber {
      private static final String FIND_SQUARE="{call P_GETSQUARE(?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int square=0;
		try(Scanner sc = new Scanner(System.in);)
		{
			System.out.print("Enter Number To Calculate Square\t:");
			square=sc.nextInt();
		}//try
		catch(Exception e)
		{
          e.printStackTrace();
		}
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				CallableStatement cs  = con.prepareCall(FIND_SQUARE);)
		{
//			Register Out Parameters Values
			cs.registerOutParameter(2, Types.INTEGER);
//			Register In Parameters
			cs.setInt(1, square);
//			Execute The Procedure
			cs.execute();
//			Hold Result 
			int result= cs.getInt(2);
			System.out.println("Square is\t:"+result);
			
		}//TRY
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
