import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*; 

public class login {
	
	public static void main(String [] args) throws ClassNotFoundException
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your username:");
		String username = sc.nextLine();
		System.out.println("Enter your password");
		String password = sc.nextLine();
	
		connect(username, password);
		
		
		
	}
	public static void connect(String userid, String passwd) throws ClassNotFoundException 
	{ 
		try 
		{
		//Class.forName ("org.postgresql.Driver"); // load server
		Connection conn = DriverManager.getConnection( "jdbc:postgresql://[2601:244:8301:5110:9dba:8424:b917:7df7]:5432/project", "postgres", "postgres");
		Statement stmt = conn.createStatement(); // create Statement object... 
		ResultSet rset = stmt.executeQuery("select employeeid from employee");
		while(rset.next())
		{
			System.out.println(rset.getString("employeeid" + "cats"));
		}
		
		

		stmt.close(); // close Statement and release resources
		conn.close(); // close Connection and release resources
		}
		catch (SQLException sqle) 
		{ 
			System.out.println("SQLException : " + sqle);// handle exceptions
		}
	}
}
