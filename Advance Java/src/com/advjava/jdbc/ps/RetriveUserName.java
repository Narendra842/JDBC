package com.advjava.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
/*table name :- USERLIST


*/
public class RetriveUserName {
//	SQL QUERY FOR MY APPLICATION
	  public static final String  FETCH_USERNAME= "SELECT *  FROM USERLIST WHERE USERNAME=?";

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		
			// Read Input from user
			sc=new Scanner(System.in);
			String initName=null;
			if(sc!=null)
			{
				System.out.println("Enter User Name That you to View \t:");
				initName=sc.next();
			}
//		load JDBC Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
//		Establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
//			Create Prepared Statement object
			if(con!=null)
				ps=con.prepareStatement(FETCH_USERNAME);
				
			    ps.setString(1, initName);
//				create result set object	    
				rs=ps.executeQuery();
				int count=0;
				
				while(rs.next())
				{
					
				  count=1;
				   System.out.println(rs.getString(1)+ "  "+rs.getString(2));
				}
				if(count==0)
					System.out.println(" No Record To show ");
			
			rs.close();
			ps.close();
			con.close();
			sc.close();
	}//main
}//class


