import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class RetriveDateFromCitizenTable {
//	Sql Query 
	public static final String GET_DATE_VALUE="SELECT CID,CNAME,DOB,DOM,DOJ FROM CITIZEN_DETAILS ";
	public static void main(String[] args) {	
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
//			LOad JDBC Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Establish The Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
//		 Create Prepared Statement Object
			ps=con.prepareStatement(GET_DATE_VALUE);
//			Process The Result
			rs=ps.executeQuery();
			while(rs.next())
			{
				int cNo= rs.getInt(1);
				String cName=rs.getString(2);
				java.sql.Date dob = rs.getDate(3);
				java.sql.Date dom = rs.getDate(4);
				java.sql.Date doj = rs.getDate(5);
//				Convert Sql.Date to String l Date value
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				
				String sdob= sdf.format(dob);
				String sdom= sdf.format(dom);
				String sdoj= sdf.format(doj);
//				Print THe Result Set
				System.out.println(cNo+ "  \t "+cName+" \t\t\t  "+sdob+ " \t  "+sdom+ " \t "+sdoj  );
				} //while
		}///try
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}//catch
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}//catch
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
		finally {
			try {
				if(rs!=null)
					rs.close();
			}//try
			catch(SQLException sql)
			{
				sql.printStackTrace();
			}//catch
			try {
				if(ps!=null)
					ps.close();
			}//try
			catch(SQLException sql)
			{
				sql.printStackTrace();
			}//catch
			try {
				if(con!=null)
					con.close();
			}//try
			catch(SQLException sql)
			{
				sql.printStackTrace();
			}//catch
		}//finally
		
	} //main

}//Class
