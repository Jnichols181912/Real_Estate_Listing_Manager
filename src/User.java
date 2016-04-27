import java.sql.*;
import org.apache.derby.jdbc.ClientDriver;
import java.util.Scanner;

public abstract class User {

	private Connection conn;
	
	public User() {
		conn = null;
	}
	
	public void run() {
		
		int choice;
		conn = null;
		try {
			// Step 1:  connect to database server
			Driver d = new ClientDriver();
			String url = "jdbc:derby://localhost:5555/RealEstateDb; create=false";
			conn = d.connect(url, null);

			// Make a menu selection
			printMenuAndExicute();

			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// Step 4: Disconnect from the server
			try {
				if(conn != null)
					conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	abstract void printMenuAndExicute();
}
