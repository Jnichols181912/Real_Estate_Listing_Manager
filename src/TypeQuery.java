import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeQuery extends Query {
	
	protected List<Type> tableOutput;
	
	public TypeQuery() {
		super();
		this.tableOutput = new ArrayList<Type>();
	}

	public void getCountries() {
		
		Connection con = null;
		
		try {
			Driver d = new ClientDriver();
	        con = d.connect(server, null);  
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT t.PropertyType FROM TYPE t GROUP BY t.PropertyType");

	        while (rs.next()) {
	        	Type newType = new Type(rs.getString("PropertyType"));
	        	this.tableOutput.add(newType);
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
	
	public List<Type> getTableOutput() {
		
		List<Type> tableOutput = this.tableOutput;
		return tableOutput;
	}
}
