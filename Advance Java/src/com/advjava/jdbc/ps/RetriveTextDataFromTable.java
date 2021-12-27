import java.sql.*;
import java.io.*;
import org.apache.commons.io.*;
class RetriveTextDataFromTable 
{
	public static void main(String[] args) 
	{
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			  PreparedStatement ps= con.prepareStatement("SELECT NAME, RESUME FROM DEPARTMENT WHERE ID=?");
			  ResultSet rs = ps.executeQuery();
			     Reader rd = rs.getCharacterStream(2);
				 Writer wr = new FileWriter("adc.txt");)
		{
					 ps.setInt(1,101);
					 if( rs.next())
			{
	                 	 System.out.println(rs.getString(1));
					     IOUtils.copy(rd,wr);
						 System.out.println("File Is Retrived And Stored Into abc.txt");
			}//if
			}//try
			catch(Exception e)
		{
				e.printStackTrace();
		}

	}//main
}//class
