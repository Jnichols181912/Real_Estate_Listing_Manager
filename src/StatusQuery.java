import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusQuery extends Query {
	
	protected List<Status> tableOutput;
	
	public StatusQuery() {
		
		super();
		this.tableOutput = new ArrayList<Status>();
	}

	public void getCountries() {
		
		Connection con = null;
		
		try {
			Driver d = new ClientDriver();
	        con = d.connect(server, null);  
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT s.ListingStatus FROM STATUS s GROUP BY s.ListingStatus");

	        while (rs.next()) {
	        	Status newStatus = new Status(rs.getString("ListingStatus"));
	        	this.tableOutput.add(newStatus);
	        }
	    } catch(SQLException e) {
	        e.printStackTrace();;
	    } finally {
	    	try {
	    		if (con != null)
	    		con.close();
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}
	    }
	}
	
	public List<Status> getTableOutput() {
		
		List<Status> tableOutput = this.tableOutput;
		return tableOutput;
	}

}
