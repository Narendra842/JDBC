package com.advjava.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace procedure p_sum (x in number, y in number, z out number)
2  as
3  begin
4  z:=x+y;
5 end;
*/
public class FirstProcedureOfSum {
    private static final String PROCEDURE_SUM="{ call P_SUM(?,?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int first=0, second=0;
		try(Scanner sc = new Scanner(System.in);)
		{
			if(sc!=null)
			{
				System.out.println("Enter First Number (x)\t:");
				first=sc.nextInt();
				
				System.out.println("Enter Second Number (y)\t");
				second =sc.nextInt();
			}//if
		}//try
		
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
				CallableStatement cs = con.prepareCall(PROCEDURE_SUM);)
		{
//			Register Out Parameters
			cs.registerOutParameter(3,Types.INTEGER);
//			Register In Parameters
			cs.setInt(1, first);
			cs.setInt(2, second);
			
//			Excecute Query
			cs.execute();
//			Generate Result
			int result=0;
			result=cs.getInt(3);
			
			System.out.println("Sum is:"+result);
			
			
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}//catch
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
	}

}
