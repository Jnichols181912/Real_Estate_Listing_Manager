/**
 * 
 */

/**
 * @author allen
 *
 */

import java.sql.*;

//import org.apache.derby.jdbc.ClientDriver;
import org.apache.derby.jdbc.EmbeddedDriver;

public abstract class User {

	protected Connection conn;
	
	public User() {
		conn = null;
	}
	
	public void run() {
		
		conn = null;
		
		try {
			// Step 1:  connect to database server
			//Driver d = new ClientDriver();
			
			Driver d = new EmbeddedDriver();
			String url = "jdbc:derby:/home/allen/Dropbox/EclipseWorkspace/workspace/RealEstateListingManager/RealEstateDb";
			conn = d.connect(url, null);
			if (conn == null) {
				System.out.println("\nError establishing connection to DB");
				throw new SQLException();
			}
			
			// Make a menu selection
			printMenuAndExecute();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		} finally {
			// Step 4: Disconnect from the server
			try {
				if(conn != null)
					conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
	
	abstract void printMenuAndExecute() throws SQLException;
}
