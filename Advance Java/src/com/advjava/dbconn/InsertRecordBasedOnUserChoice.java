package com.advjava.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertRecordBasedOnUserChoice {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String uname=null,pwd=null, query=null;
		Scanner sc = new Scanner(System.in);
		System.out.print("\n How Many Record You Want TO Insert\t:");
		int no = sc.nextInt();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 Statement stmt = con.createStatement();
		 System.out.println("\n -------Insert Record Below----------");
		 for(int i=1; i<=no; i++)
		 {
			  System.out.print("\n ");
			 System.out.print("\n Enter Your UName \t:");
			 uname=sc.next();
			 
			 System.out.print("\n Enter Password\t:");
			 pwd=sc.next();
			 uname="'"+uname+"'";
			 pwd="'"+pwd+"'";
			 
			  query="INSERT INTO USERLIST VALUES ("+uname+","+pwd+")";
			int count=0;
			count=stmt.executeUpdate(query);
			  if(count!=0)
				  System.out.println("\n your    "+i+"    Record Inserted Successfully !!!!!!!!!! \n ");
			  else
				  System.out.println("\n Record Not Inserted\n");
		 } // for
		 
		 System.out.println("Are You Want To View Your Record ????? \n 1)Yes \n 2)No \n \t Type In 1 or 2");
		 int number = sc.nextInt();
		 
		    if(number==1)
		    {
		    	ResultSet rs = stmt.executeQuery("SELECT * FROM USERLIST");
		    	System.out.println("\n Your Record Is As Follow -------->>>>>> \n ");
		    	System.out.println("USER NAME \t\t PASSWORD");
		    	System.out.println("==============================");
		    	while(rs.next())
		    		 
		    	   System.out.println(rs.getString(1)+"  \t\t ||   "+rs.getString(2));
		    	   
		    }
		    	else
		    		System.out.println("Press Any Key To Exit");

		 
		 stmt.close();
		 con.close();
		 sc.close();
	}

}
