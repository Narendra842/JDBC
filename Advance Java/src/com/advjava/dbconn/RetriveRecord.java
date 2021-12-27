package com.advjava.dbconn;
import java.sql.*;
import java.util.*;

public class RetriveRecord {

	public static void main(String[] args)  throws Exception{
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Student Address to Serarch record \t:");
		String add = sc.next();
		
		 add="'"+add+"'";
		 
		 String query="SELECT * FROM STUDENT WHERE SADDRESS="+add;
		
		// Loading driver class
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// Establishing Connection
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","manager");
		// Creating Statement Object
		Statement stmt = con.createStatement();
		//For Operation Performing on Table RS Object 
		ResultSet rs = stmt.executeQuery(query);
		
		boolean flag=true;
		while(rs.next())
		{
			flag=false;
			System.out.println();
			System.out.println(rs.getInt(1)+" || "+rs.getString(2)+" ||  "+rs.getString(3)+"  ||  "+rs.getString(4));
		}
		if(flag==true)
			System.out.println("No Record Fond !!!!!!!!!!!!!!");
		
		rs.close();
		stmt.close();
		con.close();
		sc.close();
	}

}
