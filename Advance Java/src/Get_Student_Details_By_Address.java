import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;
/*
CREATE OR REPLACE PROCEDURE P_GET_STUD_BY_ADD
2  (
3  address in varchar2,
4  details out SYS_REFCURSOR)
5  AS
6  BEGIN
7  OPEN DETAILS FOR
8  SELECT SID, SNAME, SCOURSE , SADDRESS FROM STUDENT WHERE SADDRESS=ADDRESS;
9* END;*/

public class Get_Student_Details_By_Address {
     private static final String GET_STUD_DETAILS="{call P_GET_STUD_BY_ADD(?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props=null;
		String city=null;
		try( Scanner sc = new Scanner(System.in);
				InputStream is = new FileInputStream("src/com/advjava/jdbc/common/driverInfo.properties");)
		{
//		Get Input From User
			System.out.println("Enter Address/city To Search \t:");
			city = sc.next().toUpperCase();
		
			
		props= new Properties();
		 props.load(is);
			
		}//try
		catch(IOException io)
		{
			io.printStackTrace();
		}//catch
		
         try(Connection con =DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.user"), props.getProperty("jdbc.password"));
        		  CallableStatement cs = con.prepareCall(GET_STUD_DETAILS);)  
        		 {
        	 //register Out Parameter
        	 cs.registerOutParameter(2, OracleTypes.CURSOR);
//        	 Register IN Parameters
        	 cs.setString(1,city);
        	 
        	cs.executeQuery();
        	
        	ResultSet rs = (ResultSet)cs.getObject(2);
//        	Process Result set
        	 boolean flag=false;
        	 while(rs.next())
        	 {
        		 flag=true;
        		 System.out.println(rs.getInt(1)+ "  "+rs.getString(2)+ "  "+rs.getString(3)+ "  "+rs.getString(4));
        	 }//while
        	 
        	 if(!flag)
        		 System.out.println("No Record Found For Given Address !!!!!!!!!!!");
        	 
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
