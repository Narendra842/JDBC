package com.advjava.jdbc.ps;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteUserBasedOnUserName {
	public static final String Delete_Rec="DELETE USERLIST WHERE USERNAME=?";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			//read input from user
			sc=new Scanner(System.in);
			String userName=null;
			if(sc!=null) {
			System.out.print("Enter User Name That You Want to remove From List\t:");
			userName=sc.next();
			} //if
			//Load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			int res=0;
			if(con!=null)
			{
				ps=con.prepareStatement(Delete_Rec);
				ps.setString(1, userName);
				
				res=ps.executeUpdate();
				if(res!=0)
					System.out.println(res+"    Record are Removed");
				else
					System.out.println("Record Not Found To Remove");					
			} //if
	} //try
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				if(ps!=null)
					ps.close();
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
			try {
				if(sc!=null)
					sc.close();
			  } 
			catch(Exception e)
			{
				e.printStackTrace();
			}
		} //finally	

}//main
}//class
