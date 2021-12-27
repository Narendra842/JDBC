package com.advjava.jdbc.other;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Batch_Processing_Stmt {
	public static void main(String[] args) {
		try(Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				Statement stmt = con.createStatement();)
		{
//			Add Queries to batch
	//		stmt.addBatch("INSERT INTO STUDENT(sid,sname,saddress,savg) VALUES(99,'VIJAY','MUMBAI',90.00)");
			stmt.addBatch("UPDATE STUDENT SET SAVG=90.00 WHERE SADDRESS='LUR'");
			stmt.addBatch("DELETE FROM STUDENT WHERE SNAME='VIJAY'");
//			Execute the Batch
			int result[]=stmt.executeBatch();
//			Process The Batch
			int sum=0;
			for(int i=0; i<result.length; i++)
			{
				//System.out.println(result[1]);
				sum=sum+result[i];
			}
			System.out.println("No. Of Record Are Affected \t"+sum);
			
			
			
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
