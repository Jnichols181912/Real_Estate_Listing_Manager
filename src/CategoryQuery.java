import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryQuery extends Query{

	protected List<Category> tableOutput;
	
	public CategoryQuery() {
		super();
		this.tableOutput = new ArrayList<Category>();
	}
	
	public void getCountries() {
	    try {
	        Connection con = DriverManager.getConnection(this.server,"app","app");  
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT c.CategoryName FROM CATEGORY c GROUP BY c.CategoryName");

	        while (rs.next()) {
	        	Category newCategory = new Category(rs.getString("CategoryName"));
	        	this.tableOutput.add(newCategory);
	        }
	      } catch(SQLException e) {
	        System.out.println("SQL exception occured" + e.toString() );
	      }
	}
	
	public List<Category> getTableOutput() {
		List<Category> tableOutput = this.tableOutput;
		return tableOutput;
	}
}
