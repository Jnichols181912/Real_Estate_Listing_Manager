import java.sql.*;
import java.util.ArrayList;

/**
 * RegularUser is an innerclass of user that will be instantiated if the user
 * decides to enter regular user mode to execute regular queries
 * 
 * @author Allen Bui (x339y958), John Nichols()
 * @version 1
 */

public class RegularUser extends User{
	
	/**
	 * a constructor that isnt really necessary,
	 * but we added it for completeness. it ultimately calls 
	 * on the default constructor of the super class
	 */
	public RegularUser() {
		super();
	}

	@Override
	protected void printMenuAndExecute() throws SQLException{

		int choice = 0;
		boolean validChoice = false;
		
		// display the regular user screen
		clearConsole();
		printRegularUserMode();
		
		// display user options and loop until a valid selection has been made
		while (!validChoice) {
			System.out.println("Choose from one of the following options:"
	                           + "\n\t1:  Search listings by country"
	                           + "\n\t2:  Search listings by state"
	                           + "\n\t3:  Search listings by city"
	                           + "\n\t4:  Search listings by postal code"
	                           + "\n\t5:  Search listings by street address"
	                           + "\n\t6:  Search listings by list category (rent, purchase, etc.)"
	                           + "\n\t7:  Search listings by property type (bi-level, ranch, etc.)"
	                           + "\n\t8:  Search listings by number of bedrooms"
	                           + "\n\t9:  Search listings by number of bathrooms"
	                           + "\n\t10: Search listings by price"
	                           + "\n\t11: Search listings by MLS company"
	                           + "\n\t12: Cancel search");
			System.out.print("Your choice ==> ");

			try {
				choice = Integer.parseInt(scanner.nextLine());
				if (choice < 1 || choice > 12) throw new NumberFormatException();
				validChoice = true;
			} catch (NumberFormatException nfe) {
				clearConsole();
				printRegularUserMode();
				System.out.println("\nYour input needs to be an integer in range [1,12]\n");
			}
		}
		
		// clean the screen
		clearConsole();
		printRegularUserMode();
		
		// call the proper method to execute the user's requested query
		switch (choice) {
		
	  		case 1:  InputCountryQuery();
	  				 break;
	  					
	  		case 2:  InputStateQuery();
	  				 break;
	  				
	  		case 3:  InputCityQuery();
	  				 break;
	  				
	  		case 4:  InputPostalCodeQuery();
	  				 break;
	  				
	  		case 5:  InputAddressQuery();
	  				 break;
	  				
	  		case 6:  InputCategoryQuery();
	  				 break;
	  				
	  		case 7:  InputTypeQuery();
	  				 break;
	  				
	  		case 8:  InputNumberOfBedroomsQuery();
	  				 break;
	  				
	  		case 9:  InputNumberOfBathroomsQuery();
	  				 break;
	  				
	  		case 10: InputPriceQuery();
	  				 break;
	  				 
	  		case 11: InputCompanyQuery();
	  				 break;
	  				 
	  		case 12: return;
	  					
	  		default: System.out.println("Error handling ensures that code will never reach here... if somehow this line gets printed our program has some major issues");
	             	break;
		}
	}
	
	/**
	 * InputCountryQuery is a method that allows the user to search the 
	 * database according to a particular country
	 * @throws SQLException
	 */
	private void InputCountryQuery() throws SQLException {

		Statement stmt = conn.createStatement();
		
		/* * * * * * * * * * * * * * * * * * *
		 * User Interaction / Get user input *
		 * * * * * * * * * * * * * * * * * * */
		System.out.println("\n-----------------------------------------"
							+ "Search By Country"
							+ "-----------------------------------------\n");
	    System.out.print("Enter a country: ");
	    String country = scanner.nextLine().toUpperCase();
	    
	    // build the query string
	    String qry = "select m.MlsId, m.MlsName, l.listprice, a.StateOrProvince, a.City, a.FullStreetAddress "
	                 + "from MLS m, LISTING l, ADDRESS a "
	                 + "where a.Country = " + "'" + country + "' "
	                 + "and l.AddressId = a.AddressId "
	                 + "and l.MlsId = m.MlsId";
	    
	    // execute the query
	    ResultSet rs = stmt.executeQuery(qry);
	    
		/* * * * * * * * * * * * *
		 * Print resulting query *
		 * * * * * * * * * * * * */
    	System.out.println("\nMLS Id\t\tMLS Name\t\t\t\t\t\t\t Price\t\tCity\t\t\tState\t\t\tCountry\t\t\tAddress\n");
    	printLine();
    	while (rs.next()) {
    		int mlsId = rs.getInt("MlsId");
    		String mlsName = rs.getString("MlsName");
    		int price = rs.getInt("ListPrice");
    		String state = rs.getString("StateOrProvince");
    		String city = rs.getString("City");
    		String address = rs.getString("FullStreetAddress");
    		System.out.printf("%-8d \t%-50s \t%15d \t%-20s \t%-20s \t%-20s \t%-100s\n", mlsId, mlsName, price, city, state, country, address);
    	}
    	System.out.println( );
    	rs.close();
	}

	/**
	 * InputStateQuery is a method that allows a user to query a database by 
	 * a particular state or province.
	 * @throws SQLException
	 */
	private void InputStateQuery() throws SQLException {
		
		Statement stmt = conn.createStatement();
		
		/* * * * * * * * * * * * * * * * * * *
		 * User Interaction / Get user input *
		 * * * * * * * * * * * * * * * * * * */
		System.out.println("\n-----------------------------------------" 
							+ "Search By State" 
							+ "-----------------------------------------\n");
	    System.out.print("Enter a state or province: ");
	    String state = scanner.nextLine().toUpperCase();

	    // Build the query string
	    String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.City, a.FullStreetAddress "
	                 + "from MLS m, LISTING l, ADDRESS a "
	                 + "where a.StateOrProvince = " + "'" + state + "' "
	                 + "and l.AddressId = a.AddressId "
	                 + "and l.MlsId = m.MlsId";
	    
	    // execute the query
	    ResultSet rs = stmt.executeQuery(qry);
      
		/* * * * * * * * * * * * *
		 * Print resulting query *
		 * * * * * * * * * * * * */
    	System.out.println("\nMLS Id\t\tMLS Name\t\t\t\t\t\t\t Price\t\tCountry\t\t\tCity\t\t\tAddress\n");
    	printLine();
    	while (rs.next()) {
    		int mlsId = rs.getInt("MlsId");
    		String mlsName = rs.getString("MlsName");
    		int price = rs.getInt("ListPrice");
    		String country = rs.getString("Country");
    		String city = rs.getString("City");
    		String address = rs.getString("FullStreetAddress");
    		System.out.printf("%-8d \t%-50s \t%15d \t%-20s \t%-20s \t%-100s\n", mlsId, mlsName, price, country, city, address);
    	}
    	
    	System.out.println();
    	rs.close();
	}
	
	/**
	 * InputCityQuery is a method for querying all listings by a particular city
	 * @throws SQLException
	 */
	private void InputCityQuery() throws SQLException {
		
		Statement stmt = conn.createStatement();

		/* * * * * * * * * * * * * * * * * * *
		 * User Interaction / Get user input *
		 * * * * * * * * * * * * * * * * * * */
		System.out.println("\n-----------------------------------------" 
							+ "Search By City" 
							+ "-----------------------------------------\n");
		System.out.print("Enter a city: ");
		String city = scanner.nextLine().toUpperCase();

		
		// Build the query string
		String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.FullStreetAddress "
                   	 + "from MLS m, LISTING l, ADDRESS a "
                   	 + "where a.City = " + "'" + city + "' "
                   	 + "and l.AddressId = a.AddressId "
                   	 + "and l.MlsId = m.MlsId";
		
		// execute the query
		ResultSet rs = stmt.executeQuery(qry);

		/* * * * * * * * * * * * *
		 * Print resulting query *
		 * * * * * * * * * * * * */
		System.out.println("\nMLS Id\t\tMLS Name\t\t\t\t\t\t\t Price\t\tCity\t\t\tState\t\t\tCountry\t\t\tAddress\n");
		printLine();
		while (rs.next()) {
			int mlsId = rs.getInt("MlsId");
			String mlsName = rs.getString("MlsName");
			int price = rs.getInt("ListPrice");
			String country = rs.getString("Country");
			String state = rs.getString("StateOrProvince");
			String address = rs.getString("FullStreetAddress");
			System.out.printf("%-8d\t%-50s \t%15d\t\t%-20s\t%-20s\t%-20s\t%-100s\n",mlsId, mlsName, price, city, state, country, address);
		}

		System.out.println( );
		
		//close connection
		rs.close();
		
	}

	/**
	 * InputPostalCodeQuery is a method that allows a user to 
	 * search the database by a particular postal code
	 * @throws SQLException
	 */
	private void InputPostalCodeQuery() throws SQLException {
		
		Statement stmt = conn.createStatement();

		/* * * * * * * * * * * * * * * * * * *
		 * User Interaction / Get user input *
		 * * * * * * * * * * * * * * * * * * */
		int postalCode;
		System.out.println("\n-----------------------------------------" 
							+ "Search By Postal Code" 
							+ "-----------------------------------------\n");
		System.out.print("Enter a postal code: ");
		postalCode = scanner.nextInt( );	

		// Build the query string		 
		String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress "
                   	 + "from MLS m, LISTING l, ADDRESS a "
                   	 + "where a.PostalCode = " + postalCode + " "
                   	 + "and l.AddressId = a.AddressId "
                   	 + "and l.MlsId = m.MlsId";
		
		 // execute the query 
		ResultSet rs = stmt.executeQuery(qry);


		/* * * * * * * * * * * * *
		 * Print resulting query *
		 * * * * * * * * * * * * */
		System.out.println("\nMLS Id" + "\tMLS Name" + "\tPrice" + "\tCountry" + "\tState" + "\tCity" + "\tAddress\n");
		printLine();
		while (rs.next()) {
			String mlsId = rs.getString("MlsId");
			String mlsName = rs.getString("MlsName");
			int price = rs.getInt("ListPrice");
			String country = rs.getString("Country");
			String state = rs.getString("StateOrProvince");
			String city = rs.getString("City");
			String address = rs.getString("FullStreetAddress");
			System.out.println(mlsId + "\t" + mlsName + "\t" + price + "\t" + country + "\t" + state + "\t" + city + "\t" + address);
		}
		System.out.println();
		rs.close();
	}

	/**
	 * InputAddressQuery allows the user to query the database 
	 * by a particular address.
	 * @throws SQLException
	 */
	private void InputAddressQuery() throws SQLException {
   		   		
   		Statement stmt = conn.createStatement();

		/* * * * * * * * * * * * * * * * * * *
		 * User Interaction / Get user input *
		 * * * * * * * * * * * * * * * * * * */
		System.out.println("\n-----------------------------------------" 
							+ "Search By Street Address" 
							+ "-----------------------------------------\n");
   		System.out.print("\nEnter an address: ");
   		String address = scanner.nextLine().trim();
		
   		// Build the query string	
   		String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City\n"
                   	 + "from MLS m, LISTING l, ADDRESS a \n"
                   	 + "where a.fullstreetaddress = '" + address + "' "
                   	 + "and l.AddressId = a.AddressId "
                   	 + "and l.MlsId = m.MlsId";

   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);

		/* * * * * * * * * * * * *
		 * Print resulting query *
		 * * * * * * * * * * * * */
		System.out.println("\nMLS Id" + "\tMLS Name" + "\tPrice" + "\tCountry" + "\tState" + "\tCity");
		printLine();
		// Populate table with results
		while (rs.next()) {
			int mlsId = rs.getInt("MlsId");
			String mlsName = rs.getString("MlsName");
			int price = rs.getInt("ListPrice");
			String country = rs.getString("Country");
			String state = rs.getString("StateOrProvince");
			String city = rs.getString("City");
			System.out.println(mlsId + "\t" + mlsName + "\t" + price + "\t" + country + "\t" + state + "\t" + city);
		}
		
		System.out.println( );
		rs.close(); 		
   	}

	/**
	 * InputCategoryQuery is a method that allows the user to execute a database
	 * query by a particular category
	 * @throws SQLException
	 */
   	private void InputCategoryQuery() throws SQLException {
   	   		
   		Statement stmt = conn.createStatement();
   		
		/* * * * * * * * * * * * * * * * * * *
		 * User Interaction / Get user input *
		 * * * * * * * * * * * * * * * * * * */
		System.out.println("\n-----------------------------------------" 
							+ "Search By Category" 
							+ "-----------------------------------------");
   		int category = getCategoryChoice();
   		
   		// build the query string
   		String qry = "select m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress \n"
                   	 + "from MLS m, LISTING l, ADDRESS a, CATEGORY c "
                   	 + "where c.CategoryId = " + category + " "
                   	 + "and l.AddressId = a.AddressId "
                   	 + "and l.MlsId = m.MlsId";
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);

		/* * * * * * * * * * * * *
		 * Print resulting query *
		 * * * * * * * * * * * * */
		System.out.println("\nMLS Name\t\tPrice\t\tCountry\t\t\tState\t\t\tCity\t\t\tAddress");
		printLine();
	  
		while (rs.next()) {
			String mlsName = rs.getString("MlsName");
			int price = rs.getInt("ListPrice");
			String country = rs.getString("Country");
			String state = rs.getString("StateOrProvince");
			String city = rs.getString("City");
			String address = rs.getString("FullStreetAddress");
			System.out.printf("%-20s\t%-15d\t%-20s\t%-20s\t%-20s\t%-100s\n", mlsName, price, city, state, country,address);
		}
		
		System.out.println( );
		rs.close();
   	}
   	
   	/**
   	 * getCategoryChoice is a helper method that is used to display the various categories and allows
   	 * the user to select the desired category that he/she would like to search by
   	 * @return categoryId type int that is the primary key to a category the user chose to query by
   	 * @throws SQLException
   	 */
   	private int getCategoryChoice() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
   		
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
		 * Get all of the property categories that are in the DB *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

   		// build the query string
   		String qry = "Select * from CATEGORY";

   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);
   		
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
		 * Display the results to the user and let them choose *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
   		System.out.print("Category\n------------------");
	   	
   		// store the primary keys into an ArrayList
   		ArrayList<Integer> categoryId = new ArrayList<Integer>();
	   
   		int i = 0;
   		while (rs.next()) {
   			categoryId.add(rs.getInt("CategoryId"));
   			System.out.print("\n" + (i++ + 1) + ") " + rs.getString("CategoryName"));
   		}
	    
   		// force the user to select a valid option
   		int categoryChoice = 0;
   		boolean valid = false;
   		while (!valid) {
	   		try {
	   	   		System.out.print("\n\nPlease choose a category: ");
	   			categoryChoice = Integer.parseInt(scanner.nextLine());
	   			
	   			if(categoryChoice <= 0 || categoryChoice > (categoryId.size())) {
	   				throw new NumberFormatException();
	   			}
	   			valid = true;
	   		} catch(NumberFormatException nfe) {
	   			System.out.println("Your selection was invalid, please try again");
	   		}
   		}
   		
   		// return the chosen category
   		return categoryId.get(categoryChoice - 1);
   	}
   	
   	/**
   	 * InputTypeQuery is a method that allows a user to query the database by property type
   	 * @throws SQLException
   	 */
   	private void InputTypeQuery() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
   		
		/* * * * * * * * * * * * * * * * * * *
		 * User Interaction / Get user input *
		 * * * * * * * * * * * * * * * * * * */
		System.out.println("\n-----------------------------------------" 
							+ "Search By Property Type" 
							+ "-----------------------------------------\n");
		// get the property type by displaying the options to the user
   		int type = getTypeChoice();

   		// build the query string
   		String qry = "select distinct m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress \n"
                     + "from MLS m, LISTING l, ADDRESS a, TYPE t "
                     + "where l.TypeId = " + type
                     + " and l.AddressId = a.AddressId"
                     + " and l.MlsId = m.MlsId";
   		
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);

		/* * * * * * * * * * * * *
		 * Print resulting query *
		 * * * * * * * * * * * * */
		System.out.println("MLS Name\t\tPrice\t\tCountry\t\t\tState\t\t\tCity\t\t\tAddress");
		printLine();
		while (rs.next()) {
			String mlsName = rs.getString("MlsName");
			int price = rs.getInt("ListPrice");
			String country = rs.getString("Country");
			String state = rs.getString("StateOrProvince");
			String city = rs.getString("City");
			String address = rs.getString("FullStreetAddress");
			System.out.printf("%-20s\t%-15d\t%-20s\t%-20s\t%-20s\t%-100s\n",mlsName, price, city, state, country, address);
		}
		System.out.println( );
		rs.close();

   	}
   	
   	/**
   	 * getTypeChoice is a helper method that displays the property types that are currently 
   	 * in the databse and lets the user choose the type that they would like to search by
   	 * @return TypeId of type int which is the primary key of the particular type
   	 * @throws SQLException
   	 */
   	private int getTypeChoice() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
   		
		/* * * * * * * * * * * * * * * * * * * * * * * * * * *
		 * Get all of the property types that are in the DB  *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * */
   		
   		// build the query string
   		String qry = "Select * from TYPE";
   		
   		// execute the query for all property types
   		ResultSet rs = stmt.executeQuery(qry);

		/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
		 * Display the results to the user and let them choose *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
   		System.out.print("Property Type\n------------------");
   		
   		// store all primary keys into an ArrayList
   		ArrayList<Integer> typeId = new ArrayList<Integer>();
	   
   		int i = 0;
   		while (rs.next()) {
   			typeId.add(rs.getInt("typeId"));
   			System.out.print("\n" + (i++ + 1) + ") " + rs.getString("propertytype"));
   		}
   		
   		// force the user to select a valid option
   		int typeChoice = 0;
   		boolean valid = false;
   		while (!valid) {
	   		try {
	   	   		System.out.print("\n\nPlease choose a category: ");
	   			typeChoice = Integer.parseInt(scanner.nextLine());
	   			
	   			if(typeChoice <= 0 || typeChoice > (typeId.size())) {
	   				throw new NumberFormatException();
	   			}
	   			valid = true;
	   		} catch(NumberFormatException nfe) {
	   			System.out.println("Your selection was invalid, please try again");
	   		}
   		}
   		
   		return typeId.get(typeChoice - 1);
   	}
   	
   	/**
   	 * InputNumberOfBedroomsQuery is a method that allows the user to query
   	 * according to a desired number of bedrooms.
   	 * @throws SQLException
   	 */
   	private void InputNumberOfBedroomsQuery() throws SQLException {
      
   		Statement stmt = conn.createStatement();
   		
		/* * * * * * * * * * * * * * * * * * *
		 * User Interaction / Get user input *
		 * * * * * * * * * * * * * * * * * * */
		System.out.println("\n-----------------------------------------" 
							+ "Search By Number of Bedrooms"  
							+ "-----------------------------------------\n");
   		
   		int numberOfBedrooms = 0;
   		boolean valid = false;
   		
   		// force the user to enter a valid number
   		while(!valid) {
   	   		System.out.print("Please enter the number of bedrooms: ");
	   		try {
	   			numberOfBedrooms = Integer.parseInt(scanner.nextLine());
	   			if (numberOfBedrooms < 1) {
	   				throw new NumberFormatException();
	   			}
	   			valid = true;
	   		} catch(NumberFormatException nfe) {
	   			System.out.println("Invalid number of bathrooms. Positive integers only.");
	   		}
   		}
   		
   		// build the query string
   		String qry = "select distinct m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress \n"
                     + " from MLS m, LISTING l, ADDRESS a, TYPE t \n"
                     + " where l.Bedrooms = " + numberOfBedrooms
                     + " and l.AddressId = a.AddressId"
                     + " and l.MlsId = m.MlsId";
   		
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);

		/* * * * * * * * * * * * *
		 * Print resulting query *
		 * * * * * * * * * * * * */
		System.out.println("MLS Name\t\tPrice\t\tCity\t\t\tState\t\t\tCountry\t\t\tAddress");
		printLine();
		while (rs.next()) {
			String mlsName = rs.getString("MlsName");
			int price = rs.getInt("ListPrice");
			String country = rs.getString("Country");
			String state = rs.getString("StateOrProvince");
			String city = rs.getString("City");
		  	String address = rs.getString("FullStreetAddress");
		  	System.out.printf("%-20s\t%-15d\t%-20s\t%-20s\t%-20s\t%-100s\n", mlsName, price, country, state, city, address);
		}
		System.out.println();
		
		// close the resource
		rs.close();
   	}

   	/**
   	 * InputNumberOfBathroomsQuery is a method that allows the user to query
   	 * according to a desired number of bathrooms.
   	 * @throws SQLException
   	 */
   	private void InputNumberOfBathroomsQuery() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
   		
		/* * * * * * * * * * * * * * * * * * *
		 * User Interaction / Get user input *
		 * * * * * * * * * * * * * * * * * * */
   		System.out.println("\n-----------------------------------------" 
   							+ "Search By Number of Bathrooms" 
   							+ "-----------------------------------------");
   		
   		int numberOfBathrooms = 0;
   		boolean valid = false;
   		
   		// force the user to enter a valid number
   		while(!valid) {
   	   		System.out.print("Please enter the number of bathrooms: ");
	   		try {
	   			numberOfBathrooms = Integer.parseInt(scanner.nextLine());
	   			if (numberOfBathrooms < 0) {
	   				throw new NumberFormatException();
	   			}
	   			valid = true;
	   		} catch(NumberFormatException nfe) {
	   			System.out.println("Invalid number of bathrooms. Positive integers only.");
	   		}
   		}

   		// build the query string
     	String qry = "select distinct m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress \n"
                     + " from MLS m, LISTING l, ADDRESS a, TYPE t \n"
                     + " where l.Bathrooms = " + numberOfBathrooms
                     + " and l.AddressId = a.AddressId "
                     + " and l.MlsId = m.MlsId";
     	
     	// execute the query
     	ResultSet rs = stmt.executeQuery(qry);

		/* * * * * * * * * * * * *
		 * Print resulting query *
		 * * * * * * * * * * * * */
		System.out.println("MLS Name\t\tPrice\t\tCity\t\t\tState\t\t\tCountry\t\t\tAddress");
		printLine();
  		while (rs.next()) {
  			String mlsName = rs.getString("MlsName");
  			int price = rs.getInt("ListPrice");
  			String country = rs.getString("Country");
  			String state = rs.getString("StateOrProvince");
  			String city = rs.getString("City");
  			String address = rs.getString("FullStreetAddress");
		  	System.out.printf("%-20s\t%-15d\t%-20s\t%-20s\t%-20s\t%-100s\n", mlsName, price, country, state, city, address);
  		}
  		System.out.println( );
  		rs.close();
   	}
   
   	/**
   	 * InputPriceQuery is a method that allows users to query the databse 
   	 * by a price that is greater or lower/equal to the user's desired price range
   	 * @throws SQLException
   	 */
   	private void InputPriceQuery() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
   		
   		String qry = null;
   		int price = 0; 
   		
   		// loop until the user enters a valid price point
   		boolean valid = false;
   		while (!valid) {
   	   		System.out.print("Please enter the price: ");
	   		try {
	   			price = Integer.parseInt(scanner.nextLine());
	   			valid = true;
	   		} catch (NumberFormatException nfe) {
	   			System.out.println("You have entered an invalid price. Please ensure your price is an integer format.");
	   		}
   		}
   		
   		// loop until the user chooses whether they want to search for greater than or less/equal to
   		valid = false;
   		int choice = 0;
   		while (!valid) {
   			try {
   				System.out.println("Choose one of the following:\n\t1: Greater than\n\t2: Less than or equal to\n");
   				System.out.print("Please choose: ");
   				choice = Integer.parseInt(scanner.nextLine());
   				if (choice != 1 && choice != 2) {
   					throw new NumberFormatException();
   				}
   				valid = true;
   			} catch(NumberFormatException nfe) {
   				System.out.println("Your input was invalid, Please select option 1 or option 2 by the integer representation");
   			}
   		}
   		
   		// build the query string
   		if (choice == 1) {
   			qry = "select distinct m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress "
                  + "from MLS m, LISTING l, ADDRESS a, TYPE t "
                  + "where l.listprice > " + price + " "
                  + "and l.AddressId = a.AddressId "
                  + "and l.MlsId = m.MlsId";
   		} else if (choice == 2) {
   			qry = "select distinct m.MlsName, l.ListPrice, a.City, a.StateOrProvince, a.Country, a.FullStreetAddress"
                  + " from MLS m, LISTING l, ADDRESS a, TYPE t "
                  + " where l.listprice <= " + price + " "
                  + " and l.AddressId = a.AddressId "
                  + " and l.MlsId = m.MlsId";
   		}
      
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);

		/* * * * * * * * * * * * *
		 * Print resulting query *
		 * * * * * * * * * * * * */
		System.out.println("MLS Name\t\tPrice\t\tCity\t\t\tState\t\t\tCountry\t\t\tAddress");
		printLine();
		while (rs.next()) {
			String mlsName = rs.getString("MlsName");
			int priceQ = rs.getInt("ListPrice");
			String country = rs.getString("Country");
			String state = rs.getString("StateOrProvince");
			String city = rs.getString("City");
			String address = rs.getString("FullStreetAddress");
		  	System.out.printf("%-20s\t%-15d\t%-20s\t%-20s\t%-20s\t%-100s\n", mlsName, priceQ, country, state, city, address);
		}
		System.out.println( );
		rs.close();
   	}
 
   	
   	/* * * *
   	 * THIS WONT WORK BECAUSE WE STORE IT IN THE DB AS A STRING, NOT INTEGER GREATER THAN LESS THAN WONT WORK
   	 */
   	/*  
   	// This method is for the query for all listings with a particular number of bathrooms.
   	public void InputDateQuery() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
   		
   		String qry = null;

   		System.out.print("Please enter the date(yyyy-mm-dd): ");
   		String date = scanner.nextLine();
	      
   		System.out.println("Choose one of the following:"
	    		  		   + "\t1: Equal to or greater than"
	    		  		   + "\t2: Equal to or lower than");
   		System.out.print("Please choose: ");
   		int choice = scanner.nextInt();
	      
   		if (choice == 1) {
   			qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress"
	              + "from MLS m, LISTING l, ADDRESS a, TYPE t "
	              + "where l.ListingDate >= DATE(" + date + ") "
	              + "and l.AddressId = a.AddressId "
	              + "and l.MlsId = m.MlsId";
   		} else if (choice == 2) {
   			qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress"
	              + "from MLS m, LISTING l, ADDRESS a, TYPE t "
	              + "where l.Bathrooms <= DATE(" + date + ") "
	              + "and l.AddressId = a.AddressId "
	              + "and l.MlsId = m.MlsId";
   		}
	      
   		ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
		System.out.println("MLS Id" + "\tMLS Name" + "\tPrice" + "\tCountry" + "\tState" + "\tCity" + "\tAddress");
	   
		while (rs.next()) {
			String mlsId = rs.getString("MlsId");
			String mlsName = rs.getString("MlsName");
			int price = rs.getInt("ListPrice");
			String country = rs.getString("Country");
			String state = rs.getString("StateOrProvince");
			String city = rs.getString("City");
			String address = rs.getString("FullStreetAddress");
			System.out.println(mlsId + "\t" + mlsName + "\t" + price + "\t" + country + "\t" + state + "\t" + city + "\t" + address);
		}
		System.out.println( );
		rs.close();
   	}*/
   
   	// This method is for the query for all listings with a particular number of bathrooms.
   	/**
   	 * InputCompanyQuery 
   	 * @throws SQLException
   	 */
   	public void InputCompanyQuery() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
   		
		/* * * * * * * * * * * * * * * * * * *
		 * User Interaction / Get user input *
		 * * * * * * * * * * * * * * * * * * */
   		System.out.println("\n-----------------------------------------"
   							+ "Search By MLS Company" 
   							+ "-----------------------------------------\n");
   		
   		// call on a helper method get the mlsCompany value
   		int mlsCompany = getMlsId();

   		// build the query string
   		String qry = "select distinct m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress \n"
                   	 + " from MLS m, LISTING l, ADDRESS a, TYPE t "
                   	 + " where m.MLSID = " + mlsCompany
                   	 + " and l.AddressId = a.AddressId "
                   	 + " and l.MlsId = m.MlsId";
   		
   		// execute the query 
   		ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
		System.out.println("MLS Name\t\tPrice\t\tCity\t\t\tState\t\t\tCountry\t\t\tAddress");
    	  
		while (rs.next()) {
			String mlsName = rs.getString("MlsName");
			int price = rs.getInt("ListPrice");
			String country = rs.getString("Country");
			String state = rs.getString("StateOrProvince");
			String city = rs.getString("City");
			String address = rs.getString("FullStreetAddress");
		  	System.out.printf("%-20s\t%-15d\t%-20s\t%-20s\t%-20s\t%-100s\n", mlsName, price, country, state, city, address);
		}
    	System.out.println( );
    	
    	// close resources
    	rs.close();
	}
   	
   	/**
   	 * getMlsId is a method that displays MLS companies to the user and allows them to choose
   	 * the company they would like to see listings for
   	 * @return MlsId which is an int that is the primary key for the MLS company
   	 * @throws SQLException
   	 */
   	private int getMlsId() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = "Select * from MLS";
   		
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);
	   	   
   		// store the keys into the database
   		ArrayList<Integer> MlsId = new ArrayList<Integer>();
   		
   		// display the companies to the user
   		System.out.print("MLS Companies\n------------------");
   		int i = 0;
   		while (rs.next()) {
   			MlsId.add(rs.getInt("MlsId"));
   			System.out.print("\n" + (i++ + 1) + ") " + rs.getString("MLSNAME"));
   		}
	   
   		// loop until the user selects a valid option
   		int choice = 0;
   		boolean valid = false;
   		while (!valid) {
	   		try {
	   	   		System.out.print("\n\nPlease choose a MLS Company: ");
	   			choice = Integer.parseInt(scanner.nextLine());
	   			
	   			if(choice <= 0 || choice > (MlsId.size())) {
	   				throw new NumberFormatException();
	   			}
	   			valid = true;
	   		} catch(NumberFormatException nfe) {
	   			System.out.println("Your selection was invalid, please try again");
	   		}
   		}
   		
   		// return the primary key of the MLS company
   		return MlsId.get(choice - 1);
   	}
   	
   	/**
   	 * printRegularUserMode is called by printAndExecute whenever 
   	 * the user restarts a query
   	 */
   	private static final void printRegularUserMode() {
   		System.out.println("-----------------------------------------" 
   							+ "Regular User mode" 
   							+ "----------------------------------------");	
   	}
   	
}

