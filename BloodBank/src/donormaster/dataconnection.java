package donormaster;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dataconnection {
	public static Connection doconnect()
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/javaproject","root","");
			System.out.println("connection established");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String []args)
	{
		doconnect();
	}

}
