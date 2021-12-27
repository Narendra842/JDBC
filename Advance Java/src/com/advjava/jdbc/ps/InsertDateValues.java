package com.advjava.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InsertDateValues {

	public static final String QUERY ="INSERT INTO CITIZEN_DETAILS VALUES (?,?,?,?,?)";
	
	public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException,Exception {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
			//read input from user
			sc=new Scanner(System.in);
			int cId=0;
			String cName=null;
			String cDob=null, cDom=null, cDoj=null;
			if(sc!=null) {
			System.out.print("Enter Citizen Id\t:");
			cId=sc.nextInt();
			System.out.print("Enter Citizen Name\t:");
			cName=sc.next();
			System.out.print("Enter Citize Date Of Birth (dd-mm-yyyy)\t:");
			cDob=sc.next();
			System.out.print("Enter Citizen Date Of Marriage (mm-dd-yyyy)\t:");
			cDom=sc.next();
			System.out.print("Enter Citizen Date Of Joinning(yyyy-mm-dd)\t:");
			cDoj=sc.next();
		  }// if 
//         Convert cDob date values to java.sql.Date Class (dd-mm-yyyy)
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date uDob = sdf1.parse(cDob);
			long ms = uDob.getTime();
			java.sql.Date sqldob=new java.sql.Date(ms);
//         Convert cDom date values to java.sql.Date Class (mm-dd-yyyy)
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
			java.util.Date uDom = sdf2.parse(cDom);
			java.sql.Date sqldom = new java.sql.Date(uDom.getTime());
//          convert cDoj date values to java.sql.Date Class (yyyy-mm-dd)
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date uDoj = sdf3.parse(cDoj);
			java.sql.Date sqldoj = new java.sql.Date(uDoj.getTime());
			
//			Load JDBC DRiver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Establish the Connection
			con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
//			Create Prepared Statement Obj.
			ps=con.prepareStatement(QUERY);
			if(ps!=null)
			{
				ps.setInt(1,cId );
				ps.setString(2,cName );
				ps.setDate(3, sqldob);
				ps.setDate(4, sqldom);
				ps.setDate(5, sqldoj);
				
				int res=ps.executeUpdate();
				if(res!=0)
					System.out.println("Detais Inserted Successfully !!!!!");
				else
					System.out.println("Sorry !! Unable to Insert");
			} //if
			sc.close();
      }//main
 }//class