package com.advjava.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetTotalRecordNo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement stmt =null;
		ResultSet rs=null;
		try {
			// load JDBC Driver Class
			Class.forName("oracle.j"
					+ ""
					+ "dbc.driver.OracleDriver");
			// Establish Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			// Create Statement Object
			if(con!=null)
			stmt=con.createStatement();
			// prepare Sql Query
			// Query = (SELECT COUNT (*) FROM EMP
			String query = "SELECT COUNT (*) FROM EMP";
			if(stmt!=null)
			rs=stmt.executeQuery(query);
			if(rs!=null)
			{
			 rs.next();
			//  System.out.println(rs.getInt(1));
			  System.out.println(rs.getInt("count(*)"));
			}
			
		} //try
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}//catch
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}//catch
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
		finally {
			
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException sql)
			{
				sql.printStackTrace();
			}
			try {
				if(stmt!=null)
					stmt.close();
			}
			catch(SQLException sql)
			{
				sql.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException sql)
			{
				sql.printStackTrace();
			}
		}//finally
			
	} //main
	
}	//class


