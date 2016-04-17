import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressQuery extends Query{
	
	protected List<Address> tableOutput;
	
		public AddressQuery() {
			super();
			this.tableOutput = new ArrayList<Address>();
		}

		public void getCountries() {
		    try {
		        Connection con = DriverManager.getConnection(this.server,"app","app");  
		        Statement stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT a.Country FROM ADDRESS a GROUP BY a.Country");

		        while (rs.next()) {
		        	Address newAddress = new Address();
		        	newAddress.setCountry(rs.getString("Country"));
		        	this.tableOutput.add(newAddress);
		        }
		      } catch(SQLException e) {
		        System.out.println("SQL exception occured" + e.toString() );
		      }
		}
		
		public void getStates() {
			try {
				Connection con = DriverManager.getConnection(this.server,"app","app");    
		        Statement stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT a.State FROM ADDRESS a where a.Country = "
		        								 + "\"United States\" GROUP BY a.State");
		        
		        while (rs.next()) {
		        	Address newAddress = new Address();
		        	newAddress.setCountry(rs.getString("State"));
		        	this.tableOutput.add(newAddress);
		        }
		      } catch(SQLException e) {
		        System.out.println("SQL exception occured" + e.toString() );
		      }
		}

		public void getCities(Address address) {
			try {
				Connection con = DriverManager.getConnection(this.server,"app","app");    
		        Statement stmt = con.createStatement();
		        ResultSet rs = null;
		        
		        if (address.stateExists()) {
		        	rs = stmt.executeQuery("SELECT a.City FROM ADDRESS a where a.State = \""
		        								 	 + address.getState() + "\" GROUP BY a.City");
		        } else {
		        	rs = stmt.executeQuery("SELECT a.City FROM ADDRESS a where a.Country = \""
		        									 +  address.getCountry() + "\" GROUP BY a.City");
		        }

		        while (rs.next()) {
		        	Address newAddress = new Address();
		        	newAddress.setCountry(rs.getString("City"));
		        	this.tableOutput.add(newAddress);
		        }
		      } catch(SQLException e) {
		        System.out.println("SQL exception occured" + e.toString() );
		      }
		}
		
		public void getPostalCode(Address address) {
			try {
				Connection con = DriverManager.getConnection(this.server,"app","app");    
		        Statement stmt = con.createStatement();
		        ResultSet rs = null;
		        
		        if (address.cityExists() && address.stateExists()){
		        	rs = stmt.executeQuery("SELECT a.PostalCode FROM ADDRESS a where a.State = \""
		        						   + address.getState() + "\" AND a.City = \""
		        						   + address.getCity() + "\" GROUP BY a.PostalCode");
		        } else if (address.cityExists() && !address.stateExists()) {
		        	rs = stmt.executeQuery("SELECT a.PostalCode FROM ADDRESS a where a.Country = \""
			   				   			   + address.getCountry() + "\" AND a.City = \""
			   				   			   + address.getCity() + "\" GROUP BY a.PostalCode");
		        } else if (address.stateExists()) {
		        	rs = stmt.executeQuery("SELECT a.PostalCode FROM ADDRESS a where a.State = \""
				   			   			   + address.getState() + "\" GROUP BY a.PostalCode");
		        } else if (address.countryExists()) {
		        	rs = stmt.executeQuery("SELECT a.PostalCode FROM ADDRESS a where a.Country = \""
	   			   			   			   + address.getCountry() + "\" GROUP BY a.PostalCode");
		        }

		        while (rs.next()) {
		        	Address newAddress = new Address();
		        	newAddress.setCountry(rs.getString("PostalCode"));
		        	this.tableOutput.add(newAddress);
		        }
		      } catch(SQLException e) {
		        System.out.println("SQL exception occured" + e.toString() );
		      }
		}
		
		public void getAddresses(Address address) {
			try {
				Connection con = DriverManager.getConnection(this.server,"app","app");    
		        Statement stmt = con.createStatement();
		        ResultSet rs = null;
		        
		        if (address.postalCodeExists()) {
		        	rs = stmt.executeQuery("SELECT a.Address FROM ADDRESS a where a.PostalCode = \""
		        						   + address.getPostalCode() + "\" GROUP BY a.Address");
		        } else if (address.cityExists() && address.stateExists()){
		        	rs = stmt.executeQuery("SELECT a.Address FROM ADDRESS a where a.State = \""
		        						   + address.getState() + "\" AND a.City = \""
		        						   + address.getCity() + "\" GROUP BY a.Address");
		        } else if (address.cityExists() && !address.stateExists()) {
		        	rs = stmt.executeQuery("SELECT a.Address FROM ADDRESS a where a.Country = \""
 						   				   + address.getCountry() + "\" AND a.City = \""
 						   				   + address.getCity() + "\" GROUP BY a.Address");
		        } else if (address.stateExists()) {
		        	rs = stmt.executeQuery("SELECT a.Address FROM ADDRESS a where a.State = \""
			   				   			   + address.getState() + "\" GROUP BY a.Address");
		        } else if (address.countryExists()) {
		        	rs = stmt.executeQuery("SELECT a.Address FROM ADDRESS a where a.Country = \""
				   			   			   + address.getCountry() + "\" GROUP BY a.Address");
		        }

		        while (rs.next()) {
		        	Address newAddress = new Address();
		        	newAddress.setCountry(rs.getString("Address"));
		        	this.tableOutput.add(newAddress);
		        }
		      } catch(SQLException e) {
		        System.out.println("SQL exception occured" + e.toString() );
		      }
			//(select a.fullstreetaddress from address a group by a.fullstreetaddress)
		}
		
		public List<Address> getTableOutput() {
			List<Address> tableOutput = this.tableOutput;
			return tableOutput;
		}
}
