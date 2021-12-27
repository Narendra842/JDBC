package com.advjava.jdbc.resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSet_Scrollable_SENSITIVE {
     private static final String GET_STUD_REC="SELECT SID, SNAME, SADDRESS, SAVG FROM STUDENT";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try(Connection con  = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
				PreparedStatement ps = con.prepareStatement(GET_STUD_REC, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
						ResultSet rs = ps.executeQuery();)
		{
		  int count=0;	
			while(rs.next())
			{
		   rs.refreshRow();
		   if(count==0)
		    Thread.sleep(20000);
		   System.out.println(rs.getInt(1)+" "+rs.getString(2)+ "  "+rs.getString(3)+ "  "+rs.getString(4));
		   count++;
			 }//while
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
