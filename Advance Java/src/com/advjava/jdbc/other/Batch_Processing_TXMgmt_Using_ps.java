package com.advjava.jdbc.other;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Batch_Processing_TXMgmt_Using_ps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props=null;
		int srcAcc=0, destAcc=0;
		float amt=0.0F;
		try(Scanner sc = new Scanner(System.in);
				InputStream is = new FileInputStream("src/com/advjava/jdbc/common/driverInfo.properties");)
		{
//			Create Property Object
			props= new Properties();
			props.load(is);
//			Get Input From User
			System.out.println("Enter Your Account Number\t:");
			srcAcc=sc.nextInt();
			System.out.println("Enter Destination Account no \t:");
			destAcc=sc.nextInt();
			System.out.println("How much amount u want to transfer\t:");
			amt=sc.nextFloat();	
		}//try
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}//catch
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
		try(Connection con = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.user"),
				                                                                          props.getProperty("jdbc.password"));
				PreparedStatement ps1= con.prepareStatement("UPDATE BANK_ACCOUNT SET BALANCE=BALANCE-? WHERE ACCNO=?");
				PreparedStatement ps2= con.prepareStatement("UPDATE BANK_ACCOUNT SET BALANCE=BALANCE+? WHERE ACCNO=?");
				 )
		{
//			Set Coomit TO False
			con.setAutoCommit(false);
//			Set Values To Param of ps1
			if(ps1!=null)
			{
				ps1.setFloat(1, amt);
				ps1.setInt(2, srcAcc);
			}//if
			if(ps2!=null)
			{
				ps2.setFloat(1, amt);
				ps2.setInt(2, destAcc);
			}//if
			int result1=0, result2=0;
//			Execute the Query
			result1= ps1.executeUpdate();
			result2=ps2.executeUpdate();
			if(result1==0 || result2==0)
			{
//				Rollback The Tx
				con.rollback();
				System.err.println("Transaction is Failed");
			}//if
			else
			{
//				Commit The Tx
				con.commit();
				System.out.println("Transaction is Completed !!!!!!1");
			}//else		
			
		}//try
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}//main

}//class
