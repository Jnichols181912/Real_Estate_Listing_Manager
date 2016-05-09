import java.sql.*;
import java.util.ArrayList;
//import org.apache.derby.jdbc.ClientDriver;

/**
 * 
 */

/**
 * @author allen
 *
 */

public class RegularUser extends User{
	
	public RegularUser() {
		super();
	}

	@Override
	protected void printMenuAndExecute() throws SQLException{

		int choice = 0;
		boolean validChoice = false;
		
		clearConsole();
		printRegularUserMode();
		
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
		
		clearConsole();
		printRegularUserMode();
		
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
	
	 /* * * * * * * * * *
	  * COUNTRY QUERY DONE!
	  */
	// This method is for the query for all listings in a particular country.
	private void InputCountryQuery() throws SQLException {

		Statement stmt = conn.createStatement();

		System.out.println("\n-----------------------------------------"
							+ "Search By Country"
							+ "-----------------------------------------\n");
	    System.out.print("Enter a country: ");
	    String country = scanner.nextLine().toUpperCase();
	    
	    String qry = "select m.MlsId, m.MlsName, l.listprice, a.StateOrProvince, a.City, a.FullStreetAddress "
	                 + "from MLS m, LISTING l, ADDRESS a "
	                 + "where a.Country = " + "'" + country + "' "
	                 + "and l.AddressId = a.AddressId "
	                 + "and l.MlsId = m.MlsId";
	    
	    ResultSet rs = stmt.executeQuery(qry);
	    
    	// Loop through the result set
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
	
	 /* * * * * * * * * *
	  * STATE QUERY DONE!
	  */
	
	// This method is for the query for all listings in a particular state or province.
	private void InputStateQuery() throws SQLException {
		
		Statement stmt = conn.createStatement();
		
		System.out.println("\n-----------------------------------------" 
							+ "Search By State" 
							+ "-----------------------------------------\n");
	    System.out.print("Enter a state or province: ");
	    String state = scanner.nextLine().toUpperCase();

	    String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.City, a.FullStreetAddress "
	                 + "from MLS m, LISTING l, ADDRESS a "
	                 + "where a.StateOrProvince = " + "'" + state + "' "
	                 + "and l.AddressId = a.AddressId "
	                 + "and l.MlsId = m.MlsId";
	    ResultSet rs = stmt.executeQuery(qry);
      
    	// Loop through the result set
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
	
	 /* * * * * * * * * *
	  * CITY QUERY DONE!
	  */
	// This method is for the query for all listings in a particular city.
	private void InputCityQuery() throws SQLException {
		
		Statement stmt = conn.createStatement();
		
		System.out.println("\n-----------------------------------------" 
							+ "Search By City" 
							+ "-----------------------------------------\n");
		System.out.print("Enter a city: ");
		String city = scanner.nextLine().toUpperCase();

		String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.FullStreetAddress "
                   	 + "from MLS m, LISTING l, ADDRESS a "
                   	 + "where a.City = " + "'" + city + "' "
                   	 + "and l.AddressId = a.AddressId "
                   	 + "and l.MlsId = m.MlsId";
		
		ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
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
		rs.close();
		
	}

	
	 /* * * * * * * * * *
	  * POSTAL QUERY DONE!
	  */
	// This method is for the query for all listings in a particular postal code.
	private void InputPostalCodeQuery() throws SQLException {
		
		Statement stmt = conn.createStatement();

		int postalCode;
		System.out.println("\n-----------------------------------------" 
							+ "Search By Postal Code" 
							+ "-----------------------------------------\n");
		System.out.print("Enter a postal code: ");
		postalCode = scanner.nextInt( );	

		String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress "
                   	 + "from MLS m, LISTING l, ADDRESS a "
                   	 + "where a.PostalCode = " + postalCode + " "
                   	 + "and l.AddressId = a.AddressId "
                   	 + "and l.MlsId = m.MlsId";
		ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
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
	/* * * * * * * * * * * * * * * * *
	 * ADDRESS QUERY DONE!
	 */
   	// This method is for the query for all listings in a particular address.
	private void InputAddressQuery() throws SQLException {
   		   		
   		Statement stmt = conn.createStatement();

		System.out.println("\n-----------------------------------------" 
							+ "Search By Street Address" 
							+ "-----------------------------------------\n");
   		System.out.print("\nEnter an address: ");
   		String address = scanner.nextLine().trim();

   		String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City\n"
                   	 + "from MLS m, LISTING l, ADDRESS a \n"
                   	 + "where a.fullstreetaddress = '" + address + "' "
                   	 + "and l.AddressId = a.AddressId "
                   	 + "and l.MlsId = m.MlsId";

   		ResultSet rs = stmt.executeQuery(qry);

		// print column names of results table
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

   	// This method is for the query for all listings with a particular category.
   	private void InputCategoryQuery() throws SQLException {
   	   		
   		Statement stmt = conn.createStatement();
   		
		System.out.println("\n-----------------------------------------" 
							+ "Search By Category" 
							+ "-----------------------------------------");
   		int category = getCategoryChoice();

   		String qry = "select m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress \n"
                   	 + "from MLS m, LISTING l, ADDRESS a, CATEGORY c "
                   	 + "where c.CategoryId = " + category + " "
                   	 + "and l.AddressId = a.AddressId "
                   	 + "and l.MlsId = m.MlsId";
   		ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
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
   	
   	/* * * *
   	 * CATEGORY CHOICE DONE!
   	 */
   	// Get the category number from the user
   	private int getCategoryChoice() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
   		
   		String qry = "Select * from CATEGORY";
   		ResultSet rs = stmt.executeQuery(qry);
   		

   		System.out.print("Category\n------------------");
	   	   
   		ArrayList<Integer> categoryId = new ArrayList<Integer>();
	   
   		int i = 0;
   		while (rs.next()) {
   			categoryId.add(rs.getInt("CategoryId"));
   			System.out.print("\n" + (i++ + 1) + ") " + rs.getString("CategoryName"));
   		}
	   
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
   		
   		return categoryId.get(categoryChoice - 1);
   	}
   /* * * *
    * TYPE QUERY DONE!
    */
   	// This method is for the query for all listings with a particular type.
   	private void InputTypeQuery() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
   		
		System.out.println("\n-----------------------------------------" 
							+ "Search By Property Type" 
							+ "-----------------------------------------\n");
   		int type = getTypeChoice();
   		System.out.println(type);
   		String qry = "select distinct m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress \n"
                     + "from MLS m, LISTING l, ADDRESS a, TYPE t "
                     + "where l.TypeId = " + type
                     + " and l.AddressId = a.AddressId"
                     + " and l.MlsId = m.MlsId";
   		ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
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
   	/* * *
   	 * TYPE CHOICE DONE!
   	 */
   	// Get the type number from the user
   	private int getTypeChoice() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
   		
   		String qry = "Select * from TYPE";
   		ResultSet rs = stmt.executeQuery(qry);

   		System.out.print("Property Type\n------------------");
	   	   
   		ArrayList<Integer> typeId = new ArrayList<Integer>();
	   
   		int i = 0;
   		while (rs.next()) {
   			typeId.add(rs.getInt("typeId"));
   			System.out.print("\n" + (i++ + 1) + ") " + rs.getString("propertytype"));
   		}
	   
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
   	
   	/* * * * *
   	 * BEDROOM QUERY DONE!
   	 */
   	// This method is for the query for all listings with a particular number of bedrooms.
   	private void InputNumberOfBedroomsQuery() throws SQLException {
      
   		Statement stmt = conn.createStatement();
   		
		System.out.println("\n-----------------------------------------" 
							+ "Search By Number of Bedrooms"  
							+ "-----------------------------------------\n");
   		
   		int numberOfBedrooms = 0;
   		boolean valid = false;
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
   		
   		String qry = "select distinct m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress \n"
                     + " from MLS m, LISTING l, ADDRESS a, TYPE t \n"
                     + " where l.Bedrooms = " + numberOfBedrooms
                     + " and l.AddressId = a.AddressId"
                     + " and l.MlsId = m.MlsId";
   		ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
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
   	/* * * * *
   	 * Bathroom QUERY DONE!
   	 */
   	// This method is for the query for all listings with a particular number of bathrooms.
   	private void InputNumberOfBathroomsQuery() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
   		
   		System.out.println("\n-----------------------------------------" 
   							+ "Search By Number of Bathrooms" 
   							+ "-----------------------------------------");
   		
   		int numberOfBathrooms = 0;
   		boolean valid = false;
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

     	String qry = "select distinct m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress \n"
                     + " from MLS m, LISTING l, ADDRESS a, TYPE t \n"
                     + " where l.Bathrooms = " + numberOfBathrooms
                     + " and l.AddressId = a.AddressId "
                     + " and l.MlsId = m.MlsId";
     	ResultSet rs = stmt.executeQuery(qry);

  		// Loop through the result set
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
   
   	// This method is for the query for all listings with a price greater or lower than the users input.
   	private void InputPriceQuery() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
   		
   		String qry = null;
   		int price = 0; 
   		
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
      
   		ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
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
   	public void InputCompanyQuery() throws SQLException {
   		
   		Statement stmt = conn.createStatement();

   		System.out.println("\n-----------------------------------------"
   							+ "Search By MLS Company" 
   							+ "-----------------------------------------\n");
   		int mlsCompany = getMlsId();

   		String qry = "select distinct m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress \n"
                   	 + " from MLS m, LISTING l, ADDRESS a, TYPE t "
                   	 + " where m.MLSID = " + mlsCompany
                   	 + " and l.AddressId = a.AddressId "
                   	 + " and l.MlsId = m.MlsId";
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
    	rs.close();
	}
   	
   	private int getMlsId() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
   		
   		String qry = "Select * from MLS";
   		ResultSet rs = stmt.executeQuery(qry);
	   	   
   		ArrayList<Integer> MlsId = new ArrayList<Integer>();
   		
   		System.out.print("MLS Companies\n------------------");
   		int i = 0;
   		while (rs.next()) {
   			MlsId.add(rs.getInt("MlsId"));
   			System.out.print("\n" + (i++ + 1) + ") " + rs.getString("MLSNAME"));
   		}
	   
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
   		
   		return MlsId.get(choice - 1);
   	}
   	
   	private static final void printRegularUserMode() {
   		System.out.println("-----------------------------------------" 
   							+ "Regular User mode" 
   							+ "----------------------------------------");	
   	}
   	
}

