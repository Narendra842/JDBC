package com.advjava.jdbc.other;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Batch_Processing_Ps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
        		PreparedStatement ps1 = con.prepareStatement("INSERT INTO STUDENT(SID,SNAME,SADDRESS,SAVG) VALUES (?,?,?,?);");
        				PreparedStatement ps2 = con.prepareStatement("UPDATE STUDENT SET SNAME=? WHERE SADDRESS=?");)
        {
        	if(ps1!=null)
        	{
        		ps1.setInt(1, 12292);
        		ps1.setString(2,"rajeh");
        		ps1.setString(3, "parli");
        		ps1.setFloat(4, 88.34F);
        	}//if
        	if(ps2!=null)
        	{
        		ps2.setString(1, "Vik");
        		ps2.setString(2, "LATUR");
        	}//if

        	
        	int result1= ps1.executeUpdate();
        	int result2=ps2.executeUpdate();
        	int sum=0;
         
        	 System.out.println("No. of Records Are Update Are \t:"+sum);
			
        		
        }//try
        catch(SQLException sql)
        {
        	sql.printStackTrace();
        }//catch
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}//main

}//class
