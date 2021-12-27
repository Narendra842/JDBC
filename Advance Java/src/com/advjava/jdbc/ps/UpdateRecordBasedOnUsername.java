package com.advjava.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateRecordBasedOnUsername {
	public static final String UPDATE_USERPWD="UPDATE USERLIST SET PASSWORD=? WHERE USERNAME=?";

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		String name=null, pwd=null;
		System.out.print("Enter Your User name to update password\t:");
		name=sc.next();
		System.out.print("Enter New Password");
		pwd=sc.next();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		PreparedStatement ps = con.prepareStatement(UPDATE_USERPWD);
		ps.setString(1, pwd);
		ps.setString(2, name);
		  int count=ps.executeUpdate();
		   if(count!=0)
			   System.out.println("your password updated successfully !!!!!");
		   else
			   System.out.println("No User Name Found To Update Password");
		   
		   ps.close();
		   con.close();
		   sc.close();
		  
		}

}
