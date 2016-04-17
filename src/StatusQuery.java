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
	    try {
	        Connection con = DriverManager.getConnection(this.server,"app","app");  
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT s.ListingStatus FROM STATUS s GROUP BY s.ListingStatus");

	        while (rs.next()) {
	        	Status newStatus = new Status(rs.getString("ListingStatus"));
	        	this.tableOutput.add(newStatus);
	        }
	      } catch(SQLException e) {
	        System.out.println("SQL exception occured" + e.toString() );
	      }
	}
	
	public List<Status> getTableOutput() {
		List<Status> tableOutput = this.tableOutput;
		return tableOutput;
	}

}
