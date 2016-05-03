/**
 * 
 */

/**
 * @author allen
 *
 */

import java.sql.*;

import org.apache.derby.jdbc.ClientDriver;
import org.apache.derby.jdbc.EmbeddedDriver;

import java.util.Scanner;

public abstract class User {

	protected Connection conn;
	
	public User() {
		conn = null;
	}
	
	public void run() {
		
		int choice;
		
		conn = null;
		
		try {
			// Step 1:  connect to database server
			//Driver d = new ClientDriver();
			
			Driver d = new EmbeddedDriver();
			String url = "jdbc:derby://localhost:5555/RealEstateDb;create = true;";
			conn = d.connect(url, null);

			// Make a menu selection
			printMenuAndExecute();

			
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
	
	abstract void printMenuAndExecute() throws SQLException;
}
