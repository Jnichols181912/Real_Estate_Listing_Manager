import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Query {

	protected List<Object> tableOutput;
	protected String server;
	
	public Query() {
		this.tableOutput = new ArrayList<Object>();
		this.server = "jdbc:derby://localhost:1527/junkdb;create=false";
	}
}
