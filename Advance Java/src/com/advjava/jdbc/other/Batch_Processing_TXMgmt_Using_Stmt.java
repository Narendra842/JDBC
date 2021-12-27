package com.advjava.jdbc.other;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Batch_Processing_TXMgmt_Using_Stmt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\n-------------Welcome To Money Transfer App------------\n");
		int srcAcc=0, destAcc=0;
		float amount=0.0F;
		try(Scanner sc = new Scanner(System.in);)
		{
			System.out.print("\n Enter Your Account Number\t:");
			srcAcc= sc.nextInt();
			System.out.print("\n Enter Account no Where you want to transfer \t:");
			destAcc= sc.nextInt();
			System.out.print("\n Enter Amount To Transfer\t:");
			amount = sc.nextFloat();
		}//try
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
				Statement stmt = con.createStatement();	)
		{
//			Disable Commit
			con.setAutoCommit(false);
//			Add Batch
			stmt.addBatch("UPDATE BANK_ACCOUNT SET BALANCE=BALANCE-"+amount+"WHERE ACCNO="+srcAcc);
			stmt.addBatch("UPDATE BANK_ACCOUNT SET BALANCE=BALANCE+"+amount+"WHERE ACCNO="+destAcc);
//			Execute Batch
			int result[] = stmt.executeBatch();
			boolean flag=true;
			for(int i=0; i<result.length; i++)
			{
				if(result[i]==0)
				{
					flag=false;
					break;
				}//if			
			}//for
			
			if(flag)
			{
//				commit Tx
				con.commit();
				System.out.println("\nTransaction is Successfully");
			}//if
			else
			{
				con.rollback();
				System.err.println("\n Transaction is Unsuccessfull !!!!!!!!");
			}//else
			
		}//try
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
	
	}//main

}//class
