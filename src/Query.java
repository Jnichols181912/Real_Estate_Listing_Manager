import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public abstract class Query {

	protected List<Array> tableOutput;
	protected String server;
	
	public Query() {
		this.tableOutput = new ArrayList<Array>();
		this.server = "jdbc:derby://localhost:1527/junkdb;create=true";
	}
}
