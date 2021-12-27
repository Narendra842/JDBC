import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Get_Student_Details_By_Given_Char {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Initial Character of Name(Place % after Character)\t:");
		String name= sc.next();
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		
		PreparedStatement ps = con.prepareStatement("SELECT SID, SNAME,SADDRESS FROM STUDENT WHERE SNAME LIKE ?");
		
		ps.setString(1, name);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+ " "+rs.getString(2)+ " "+rs.getString(3));
		}
		
		rs.close();
		ps.close();
		con.close();
		sc.close();

	}

}
