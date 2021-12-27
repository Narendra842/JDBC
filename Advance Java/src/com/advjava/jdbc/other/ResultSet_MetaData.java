package com.advjava.jdbc.other;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSet_MetaData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				PreparedStatement ps = con.prepareStatement("select sid, sname,saddress,savg from student");
				 ResultSet rs= ps.executeQuery();)
		{
			ResultSetMetaData rsmd = rs.getMetaData();
			
			 for(int i=1; i<=rsmd.getColumnCount(); i++)
			 {
				 System.out.print(rsmd.getColumnLabel(i)+"  \t ");
				
			 }//for
			 System.out.println();
			while(rs.next())
			{
				for(int i=1; i<=rsmd.getColumnCount(); i++)
				{
					System.out.print(rs.getString(i)+ "      ");
				
				}
				 System.out.println();
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

	}

}
