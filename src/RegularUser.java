import java.sql.*;

import org.apache.derby.jdbc.ClientDriver;
import java.util.Scanner;

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
	void printMenuAndExecute() throws SQLException{
		
		Scanner keyboard = new Scanner(System.in);
		int choice = 0;
		boolean validChoice = false;
		
		while (!validChoice) {
			System.out.println("Choose from one of the following options:"
	                           + "\n\t1:  Search listing by country"
	                           + "\n\t2:  Search listing by state"
	                           + "\n\t3:  Search listing by city"
	                           + "\n\t4:  Search listing by postal code"
	                           + "\n\t5:  Search listing by address"
	                           + "\n\t6:  Search listing by category"
	                           + "\n\t7:  Search listing by type"
	                           + "\n\t8:  Search listing by number of bedrooms"
	                           + "\n\t9:  Search listing by number of bathrooms"
	                           + "\n\t10: Search listing by price"
	                           + "\n\t11: Search listing by date"
	                           + "\n\t12: Search listing by MLS company"
	                           + "\n\t13: Search listing by listing number");
			System.out.print("Your choice ==> ");
			
			try {
				choice = Integer.parseInt(keyboard.nextLine());
				if (choice < 1 || choice > 13) throw new NumberFormatException();
				validChoice = true;
			} catch (NumberFormatException nfe) {
				Menu.clearConsole();
				System.out.println("Your input needs to be an integer in range [1,13]");
			}
		}
		
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
	  				 
	  		case 11: InputDateQuery();
	  				 break;
	  				
	  		case 12: //InputCompanyQuery();
	  				 break;
	  				
	  		case 13: //InputListingNumberQuery();
	  				 break;
	  					
	  		default: System.out.println("Error handling ensures that code will never reach here... if somehow this line gets printed our program has some major issues");
	             	break;
		}
	}
	

	// This method is for the query for all listings in a particular country.
	public void InputCountryQuery() throws SQLException {
		
		Statement stmt = conn.createStatement();

		String country;
	    Scanner scannerObject = new Scanner(System.in);
	    System.out.print("Enter a country: ");
	    country = scannerObject.nextLine().toUpperCase();
	    country = "'" + country + "'";
	    String qry = "select m.MlsId, m.MlsName, a.StateOrProvince, a.City, a.FullStreetAddress "
	                 + "from MLS m, LISTING l, ADDRESS a "
	                 + "where a.Country = " + country + " "
	                 + "and l.AddressId = a.AddressId "
	                 + "and l.MlsId = m.MlsId";
	    
	    ResultSet rs = stmt.executeQuery(qry);

	    // If nothing is returned then say so. Otherwise display table results
	    /*if (!rs.isBeforeFirst()) {    
	    	System.out.println("No listings exist for this country."); 
	    } else { // Loop through the result set*/
	    	System.out.format("%4d %30-s %20-s %20-s %50-s", "MlsId", "MlsName", "StateorProvince", "FullStreetAddress");
	    
	    	while (rs.next()) {
	    		String mlsId = rs.getString("MlsId");
	    		String mlsName = rs.getString("MlsName");
	    		int price = rs.getInt("ListPrice");
	    		String state = rs.getString("StateOrProvince");
	    		String city = rs.getString("City");
	    		String address = rs.getString("FullStreetAddress");
	    		System.out.println(mlsId + "\t" + mlsName + "\t" + price + "\t" + state + "\t" + city + "\t" + address);
	    	}
	    	System.out.println( );
	    	rs.close();
	    /*}*/
	    scannerObject.close();
	}
	   
	// This method is for the query for all listings in a particular state or province.
	public void InputStateQuery() throws SQLException {
		
		Statement stmt = conn.createStatement();

	    String state;
	    Scanner scannerObject = new Scanner(System.in);
	    System.out.print("Enter a state or province: ");
	    state = scannerObject.nextLine( );
	      
	    state = state.toUpperCase();

	    String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.City, a.FullStreetAddress "
	                 + "from MLS m, LISTING l, ADDRESS a "
	                 + "where a.StateOrProvince = " + state + " "
	                 + "and l.AddressId = a.AddressId "
	                 + "and l.MlsId = m.MlsId";
	    ResultSet rs = stmt.executeQuery(qry);

	    // If nothing is returned then say so. Otherwise display table results
	    if (!rs.isBeforeFirst() ) {    
	    	System.out.println("No listings exist for this state or province."); 
	    } else {
	      
	    	// Loop through the result set
	    	System.out.println("MLS Id" + "\tMLS Name" + "\tPrice" + "\tCountry" + "\tCity" + "\tAddress");
	    	
	    	while (rs.next()) {
	    		String mlsId = rs.getString("MlsId");
	    		String mlsName = rs.getString("MlsName");
	    		int price = rs.getInt("ListPrice");
	    		String country = rs.getString("Country");
	    		String city = rs.getString("City");
	    		String address = rs.getString("FullStreetAddress");
	    		System.out.println(mlsId + "\t" + mlsName + "\t" + price + "\t" + country + "\t" + city + "\t" + address);
	    	}
	    	System.out.println( );
	    	rs.close();
	    }
	}
	   
	// This method is for the query for all listings in a particular city.
	public void InputCityQuery() throws SQLException {
		
		Statement stmt = conn.createStatement();

		String city;
		Scanner scannerObject = new Scanner(System.in);
		System.out.print("Enter a city: ");
		city = scannerObject.nextLine( );
      
		city = city.toUpperCase();

		String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.State, a.FullStreetAddress "
                   	 + "from MLS m, LISTING l, ADDRESS a "
                   	 + "where a.City = " + city + " "
                   	 + "and l.AddressId = a.AddressId "
                   	 + "and l.MlsId = m.MlsId";
		ResultSet rs = stmt.executeQuery(qry);

		// If nothing is returned then say so. Otherwise display table results
		if (!rs.isBeforeFirst() ) {    
			System.out.println("No listings exist for this city."); 
		} else {
      
			// Loop through the result set
			System.out.println("MLS Id" + "\tMLS Name" + "\tPrice" + "\tCountry" + "\tState" + "\tAddress");
    	  
			while (rs.next()) {
				String mlsId = rs.getString("MlsId");
				String mlsName = rs.getString("MlsName");
				int price = rs.getInt("ListPrice");
				String country = rs.getString("Country");
				String state = rs.getString("State");
				String address = rs.getString("FullStreetAddress");
				System.out.println(mlsId + "\t" + mlsName + "\t" + price + "\t" + country + "\t" + state + "\t" + address);
			}
			System.out.println( );
			rs.close();
		}
	}

	// This method is for the query for all listings in a particular postal code.
	public void InputPostalCodeQuery() throws SQLException {
		
		Statement stmt = conn.createStatement();

		int postalCode;
		Scanner scannerObject = new Scanner(System.in);
		System.out.print("Enter a postal code: ");
		postalCode = scannerObject.nextInt( );	

		String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress "
                   	 + "from MLS m, LISTING l, ADDRESS a "
                   	 + "where a.PostalCode = " + postalCode + " "
                   	 + "and l.AddressId = a.AddressId "
                   	 + "and l.MlsId = m.MlsId";
		ResultSet rs = stmt.executeQuery(qry);

		// If nothing is returned then say so. Otherwise display table results
		if (!rs.isBeforeFirst() ) {    
			System.out.println("No listings exist for this postal code."); 
		} else {
      
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
		}
	}
   
   	// This method is for the query for all listings in a particular address.
   	public void InputAddressQuery() throws SQLException {
   		   		
   		Statement stmt = conn.createStatement();

   		String address;
   		Scanner scannerObject = new Scanner(System.in);
   		System.out.print("Enter an address: ");
   		address = scannerObject.nextLine( );
      
   		address = address.toUpperCase();

   		String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City,"
                   	 + "from MLS m, LISTING l, ADDRESS a "
                   	 + "where a.PostalCode = " + address + " "
                   	 + "and l.AddressId = a.AddressId "
                   	 + "and l.MlsId = m.MlsId";
   		ResultSet rs = stmt.executeQuery(qry);

   		// If nothing is returned then say so. Otherwise display table results
   		if (!rs.isBeforeFirst() ) {    
   			System.out.println("No listings exist for this adderss."); 
   		} else {
      
   			// Loop through the result set
   			System.out.println("MLS Id" + "\tMLS Name" + "\tPrice" + "\tCountry" + "\tState" + "\tCity");
    	  
   			while (rs.next()) {
   				String mlsId = rs.getString("MlsId");
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
   	}
   
   	// Get the category number from the user
   	public int getCategoryChoice() throws SQLException {
   		
   		int category = 0;
	   
   		Statement stmt = conn.createStatement();
   		String qry = "Select c.* from CATEGORY";
   		ResultSet rs = stmt.executeQuery(qry);
	   
   		System.out.print("Id" + "\tCategory");
	   
   		int rsLength = 0;
   		if (rs.last()) {
   			rsLength = rs.getRow();
   			rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
   		}
	   
   		int[] categoryId = new int[rsLength];
   		String[] categoryName = new String[rsLength];
	   
   		int i = 0;
   		while (rs.next()) {
   			i++;
   			categoryId[i] = rs.getInt("CategoryId");
   			categoryName[i] = rs.getString("CategoryName");
   		}
	   
   		Scanner scanner = new Scanner(System.in);
   		System.out.print("Please choose a category number: ");
   		category = scanner.nextInt();
   		   		
   		return category;
   	}
   
   	// This method is for the query for all listings with a particular category.
   	public void InputCategoryQuery() throws SQLException {
   	   		
   		Statement stmt = conn.createStatement();

   		int category = getCategoryChoice();

   		String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress"
                   	 + "from MLS m, LISTING l, ADDRESS a, CATEGORY c "
                   	 + "where c.CategoryId = " + category + " "
                   	 + "and l.AddressId = a.AddressId "
                   	 + "and l.MlsId = m.MlsId";
   		ResultSet rs = stmt.executeQuery(qry);

   		// If nothing is returned then say so. Otherwise display table results
   		if (!rs.isBeforeFirst() ) {    
   			System.out.println("No listings exist for this category."); 
   		} else {
      
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
   		}
   	}
   
   	// Get the type number from the user
   	public int getTypeChoice() throws SQLException {
   		
   		int type = 0;
   		
   		Statement stmt = conn.createStatement();
   		String qry = "Select t.* from TYPE";
   		ResultSet rs = stmt.executeQuery(qry);
	   
   		System.out.print("Id" + "\tType");
	   
   		int rsLength = 0;
   		if (rs.last()) {
   			rsLength = rs.getRow();
   			rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
   		}
	   
   		int[] typeId = new int[rsLength];
   		String[] propertyType = new String[rsLength];
	   
   		int i = 0;
   		while (rs.next()) {
   			i++;
   			typeId[i] = rs.getInt("TypeId");
   			propertyType[i] = rs.getString("PropertyType");
   		}
	   
   		Scanner scanner = new Scanner(System.in);
   		System.out.print("Please choose a type number: ");
   		type = scanner.nextInt();
   		
   		return type;
   	}
   
   	// This method is for the query for all listings with a particular type.
   	public void InputTypeQuery() throws SQLException {
   		
   		
   		Statement stmt = conn.createStatement();

   		int type = getTypeChoice();

   		String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress"
                     + "from MLS m, LISTING l, ADDRESS a, TYPE t "
                     + "where t.TypeId = " + type + " "
                     + "and l.AddressId = a.AddressId "
                     + "and l.MlsId = m.MlsId";
   		ResultSet rs = stmt.executeQuery(qry);

   		// If nothing is returned then say so. Otherwise display table results
   		if (!rs.isBeforeFirst() ) {    
   			System.out.println("No listings exist for this type."); 
   		} else {
      
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
   		}
   	}
   
   	// This method is for the query for all listings with a particular number of bedrooms.
   	public void InputNumberOfBedroomsQuery() throws SQLException {
      
   		Statement stmt = conn.createStatement();
      
   		Scanner scanner = new Scanner(System.in);
   		System.out.print("Please enter the number of bedrooms: ");
   		int numOfBedrooms = scanner.nextInt();

   		String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress"
                     + "from MLS m, LISTING l, ADDRESS a, TYPE t "
                     + "where l.Bedrooms = " + numOfBedrooms + " "
                     + "and l.AddressId = a.AddressId "
                     + "and l.MlsId = m.MlsId";
   		ResultSet rs = stmt.executeQuery(qry);

   		// If nothing is returned then say so. Otherwise display table results
   		if (!rs.isBeforeFirst() ) {    
   			System.out.println("No listings exist with that number of bedrooms."); 
   		} else {
      
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
   		}
   	}
   
   	// This method is for the query for all listings with a particular number of bathrooms.
   	public void InputNumberOfBathroomsQuery() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
      
     	Scanner scanner = new Scanner(System.in);
     	System.out.print("Please enter the number of bathrooms: ");
     	int numOfBathrooms = scanner.nextInt();

     	String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress"
                     + "from MLS m, LISTING l, ADDRESS a, TYPE t "
                     + "where l.Bathrooms = " + numOfBathrooms + " "
                     + "and l.AddressId = a.AddressId "
                     + "and l.MlsId = m.MlsId";
     	ResultSet rs = stmt.executeQuery(qry);

     	// If nothing is returned then say so. Otherwise display table results
      	if (!rs.isBeforeFirst() ) {    
      		System.out.println("No listings exist with that number of bathrooms."); 
      	} else {
      
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
      	}
   	}
   
   	// This method is for the query for all listings with a price greater or lower than the users input.
   	public void InputPriceQuery() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
   		
   		String qry = null;
   		Scanner scanner = new Scanner(System.in);
   		System.out.print("Please enter the price: ");
   		int numOfBathrooms = scanner.nextInt();
      
   		System.out.println("Choose one of the following:"
    		  			   + "\t1: Equal to or greater than"
    		  			   + "\t2: Equal to or lower than");
   		System.out.print("Please choose: ");
   		int choice = scanner.nextInt();
      
   		if (choice == 1) {
   			qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress"
                  + "from MLS m, LISTING l, ADDRESS a, TYPE t "
                  + "where l.Bathrooms >= " + numOfBathrooms + " "
                  + "and l.AddressId = a.AddressId "
                  + "and l.MlsId = m.MlsId";
   		} else if (choice == 2) {
   			qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress"
                  + "from MLS m, LISTING l, ADDRESS a, TYPE t "
                  + "where l.Bathrooms <= " + numOfBathrooms + " "
                  + "and l.AddressId = a.AddressId "
                  + "and l.MlsId = m.MlsId";
   		}
      
   		ResultSet rs = stmt.executeQuery(qry);

   		// If nothing is returned then say so. Otherwise display table results
   		if (!rs.isBeforeFirst() ) {    
   			System.out.println("No listings exist with that number of bathrooms."); 
   		} else {
      
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
   		}
   	}
   
   	// This method is for the query for all listings with a particular number of bathrooms.
   	public void InputDateQuery() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
   		
   		String qry = null;
   		Scanner scanner = new Scanner(System.in);
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

   		// If nothing is returned then say so. Otherwise display table results
   		if (!rs.isBeforeFirst() ) {
   			System.out.println("No listings exist with that number of bathrooms."); 
   		} else {
		   
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
   		}
   	}
   
   	// This method is for the query for all listings with a particular number of bathrooms.
   	public void InputMlsCompanyQuery() throws SQLException {
   		
   		Statement stmt = conn.createStatement();
      
   		Scanner scanner = new Scanner(System.in);
   		System.out.print("Please enter the mls company acronym: ");
   		String mlsCompany = scanner.nextLine();

   		String qry = "select m.MlsId, m.MlsName, l.ListPrice, a.Country, a.StateOrProvince, a.City, a.FullStreetAddress"
                   	 + "from MLS m, LISTING l, ADDRESS a, TYPE t "
                   	 + "where l.Bathrooms = " + mlsCompany + " "
                   	 + "and l.AddressId = a.AddressId "
                   	 + "and l.MlsId = m.MlsId";
   		ResultSet rs = stmt.executeQuery(qry);

   		// If nothing is returned then say so. Otherwise display table results
   		if (!rs.isBeforeFirst() ) {    
   			System.out.println("No listings exist with that number of bathrooms."); 
   		} else {
      
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
   		}
	}
}

