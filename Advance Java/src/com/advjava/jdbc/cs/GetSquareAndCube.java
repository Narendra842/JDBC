package com.advjava.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace procedure p_getsquareandcube (x in number, square out number, cube out number)
2  as
3  begin
4  square:=x *x;
5  cube:=x*x*x;
6   end;
*/

public class GetSquareAndCube {
     private static final String GET_SQUARE_AND_CUBE="{call P_GETSQUAREANDCUBE(?,?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int no=0;
		try(Scanner sc = new Scanner(System.in);)
		{
			System.out.println("----Application To Find Square and Cube Of given Number-----\n");
			System.out.print("Enter Number To find both square and cube\t:");
			no = sc.nextInt();
			System.out.println();
		}//try
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				CallableStatement cs = con.prepareCall(GET_SQUARE_AND_CUBE);)
		{
//			Register Out Parameters
			cs.registerOutParameter(2, Types.INTEGER);
			cs.registerOutParameter(3, Types.INTEGER);
//			Register In parameters
			cs.setInt(1, no);
//			Execute procedure
			cs.execute();
			
			int square=0;
			int cube=0;
			
			square=cs.getInt(2);
			cube=cs.getInt(3);
			
			System.out.println("\n Square of  "+no+"    is  "+square);
			System.out.println("\n Cube of  "+no+"    is  "+cube);
			
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
