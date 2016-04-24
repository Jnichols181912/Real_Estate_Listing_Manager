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
		
		Connection con = null;
		
		try {
			Driver d = new ClientDriver();
	        con = d.connect(server, null);  
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT c.CategoryName FROM CATEGORY c GROUP BY c.CategoryName");

	        while (rs.next()) {
	        	Category newCategory = new Category(rs.getString("CategoryName"));
	        	this.tableOutput.add(newCategory);
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
	
	public List<Category> getTableOutput() {
		
		List<Category> tableOutput = this.tableOutput;
		return tableOutput;
	}
}
