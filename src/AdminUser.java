import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * AdminUser 
 * @author Allen Bui(x339y958), John Nichols ()
 * @version 1
 */
public class AdminUser extends User {

	private static String adminMode = "Administrator Mode";
	private static String insertRecord = "Inserting Record";
	private static String updateRecord = "Updating Record";
	
	public AdminUser() {
		super();
	}

	@Override
	protected void printMenuAndExecute() throws SQLException {

		int choice = 0;
		boolean validChoice = false;
		
		clearConsole();
		printHeader(adminMode);
		
		while (!validChoice) {
			System.out.println("Choose from one of the following options:"
	                           + "\n\t1:  insert record"
	                           + "\n\t2:  update record"
	                           + "\n\t3:  regular user mode"
							   + "\n\t4:  quit");
			System.out.print("Your choice ==> ");

			try {
				choice = Integer.parseInt(scanner.nextLine());
				if (choice < 1 || choice > 4) throw new NumberFormatException();
				validChoice = true;
			} catch (NumberFormatException nfe) {
				clearConsole();
				printHeader(adminMode);
				System.out.println("\nYour input needs to be an integer in range [1,4]\n");
			}
		}
		
		clearConsole();
		printHeader(adminMode);
		
		switch (choice) {
		
	  		case 1: insertRecord();
	  				break;
	  					
	  		case 2: updateRecord();
	  				break;
	  				
	  		case 3: RegularUser reg = new RegularUser();
	  				reg.printMenuAndExecute();
	  				break;
	  				
	  		case 4: return;
	  					
	  		default: System.out.println("Error handling ensures that code will never reach here... if somehow this line gets printed our program has some major issues");
	             	break;
		}
		
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	void insertRecord() throws SQLException {
		
		clearConsole();
		printHeader(adminMode);
		printHeader(insertRecord);
		
		int choice = 0;
		boolean validChoice = false;
		
		while (!validChoice) {
			System.out.println("Choose from one of the following options:"
	                           + "\n\t1:  Insert new record into ADDRESS"
	                           + "\n\t2:  Insert new record into LISTING"
	                           + "\n\t3:  Insert new record into MLS"
	                           + "\n\t4:  Insert new record into PHOTO"
	                           + "\n\t5:  Insert new record into CATEGORY"
	                           + "\n\t6:  Insert new record into STATUS"
	                           + "\n\t7:  Insert new record into TYPE");
			System.out.print("Your choice ==> ");
			
			try {
				choice = Integer.parseInt(scanner.nextLine());
				if (choice < 1 || choice > 7) throw new NumberFormatException();
				validChoice = true;
			} catch (NumberFormatException nfe) {
				clearConsole();
				printHeader(adminMode);
				printHeader(insertRecord);
				System.out.println("Your input needs to be an integer in range [1,7]");
			}
		}
		
		clearConsole();
		printHeader(adminMode);
		printHeader(insertRecord);
		
		
		switch (choice) {
		
	  		case 1:  	showAllAddresses();
	  					insertAddress();
	  				 	break;
	  					
	  		case 2:  	showAllListings();
	  					insertListing();
				 	 	break;
	  				 
	  		case 3:  	showAllCompanies();
	  					insertMls();
				  	 	break;
				  	 
	  		case 4:  	showAllPhotos();
	  					insertPhoto();
	  					break;
	  					
	  		case 5: 	showAllCategories();
	  					insertCategory();
	  					break;
	  					
	  		case 6: 	showAllStatuses();
	  					insertStatus();
	  					break;
	  					
	  		case 7:		showAllTypes();
	  					insertType();
	  					break;
	  					
	  		default: System.out.println("Error handling ensures that code will never reach here... if somehow this line gets printed our program has some major issues");
	             	break;
		
		}
	}
	
	void updateRecord() throws SQLException {
		
		int choice = 0;
		boolean validChoice = false;
		
		while (!validChoice) {
			System.out.println("Choose from one of the following options:"
	                           + "\n\t1:  Update record in ADDRESS"
	                           + "\n\t2:  Update new record in LISTING"
	                           + "\n\t3:  Update new record in MLS"
	                           + "\n\t4:  Update new record in PHOTO"
	                           + "\n\t5:  Update new record in CATEGORY"
	                           + "\n\t6:  Update new record in STATUS"
	                           + "\n\t7:  Update new record in TYPE");
			System.out.print("Your choice ==> ");
			
			try {
				choice = Integer.parseInt(scanner.nextLine());
				if (choice < 1 || choice > 7) throw new NumberFormatException();
				validChoice = true;
			} catch (NumberFormatException nfe) {
				clearConsole();
				printHeader(adminMode);
				System.out.println("\nYour input needs to be an integer in range [1,7]");
			}
		}
		
		clearConsole();
		printHeader(adminMode);
		printHeader(updateRecord);
		
		switch (choice) {
		
			case 1:  	updateAddress();
		 				break;
			
			case 2:  	updateListing();
						break;
		 
			case 3:  	updateMls();
						break;
  	 
			case 4:		updatePhoto();
						break;
			
			case 5:		updateCategory();
						break;
			
			case 6: 	updateStatus();
						break;
			
			case 7:		updateType();
						break;
	  					
	  		default: System.out.println("Error handling ensures that code will never reach here... if somehow this line gets printed our program has some major issues");
	             	break;
		
		}
	}
	
	/**
	 * SHOW ALL ADDRESSES DONE!
	 */
	/**
	 * 
	 * @throws SQLException
	 */
	void showAllAddresses() throws SQLException {

		Statement stmt = conn.createStatement();
		
		// build the query string
     	String qry = "select * from ADDRESS";
     	
     	// execute the query
     	ResultSet rs = stmt.executeQuery(qry);

		/* * * * * * * * * * * * *
		 * Print resulting query *
		 * * * * * * * * * * * * */
     	System.out.println("Address Id\tCity\t\t\tState or Province\tCountry\t\t\tPostal Code\tAddress");
     	printLine();
     	
		while (rs.next()) {
			int addressId = rs.getInt("AddressId");
			String address = rs.getString("FullStreetAddress");
			String city = rs.getString("City");
			String state = rs.getString("StateOrProvince");
			String country = rs.getString("Country");
			int postalCode = rs.getInt("PostalCode");
			System.out.printf("%-8d\t%-20s\t%-20s\t%-20s\t%-10d\t%-100s\n", addressId, city, state, country, postalCode, address);
		}
		
		System.out.println();
		
		// close the resource
		rs.close();
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	void showAllListings() throws SQLException {
		Statement stmt = conn.createStatement();
     	String qry = "select * from LISTING";
     	ResultSet rs = stmt.executeQuery(qry);

	
     	System.out.println("Listing Id\t\tPrice\t\tURL\t\t\tDate Listed\t\t\tBedrooms\t\t\tBathrooms");			// FIX THIS ONE
     	printLine();
     	
		while (rs.next()) {
			int listingId = rs.getInt("ListingId");
			int price = rs.getInt("ListPrice");
			String description = rs.getString("ListingDescription");
			String url = rs.getString("ListURL");
			String date = rs.getString("ListingDate");
			int bedrooms = rs.getInt("Bedrooms");
			int bathrooms = rs.getInt("Bathrooms");
			System.out.printf("%-8d\t%-15d\t%-50s\t%-4d\t%-4d\t%-20s\t%-100s\n", listingId, price, url, bedrooms, bathrooms, date, description);
		}

		System.out.println( );
		rs.close();
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	void showAllCompanies() throws SQLException {
		Statement stmt = conn.createStatement();
     	String qry = "select * from MLS";
     	ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
     	// MlsId, MlsName, MlsAgent, MlsAddress, MlsEmail, MlsPhoneNumber, MlsFax
     	System.out.println("MLS Id\t\tName\t\t\tAddress\t\t\t\t\t\tEmail\t\t\tPhone Number\t\tFax");
     	printLine();
     	
		while (rs.next()) {
			int mlsId = rs.getInt("MlsId");
			String name = rs.getString("MlsName");
			String address = rs.getString("MlsAddress");
			String email = rs.getString("MlsEmail");
			String phoneNumber = rs.getString("MlsPhoneNumber");
			String fax = rs.getString("MlsFax");
	  	System.out.printf("%-8d\t%-20s\t%-40s\t%-20s\t%-20s\t%-30s\n", mlsId, name, address, email, phoneNumber, fax);
		}
		System.out.println( );
		rs.close();
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	void showAllPhotos() throws SQLException {
		Statement stmt = conn.createStatement();
     	String qry = "select * from PHOTO";
     	ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
     	// MediaModificationTimestamp, MediaUrl, ListingId
     	System.out.println("Timestamp\t\tURL\t\t\tListing Id");
     	printLine();
     	
		while (rs.next()) {
			String timestamp = rs.getString("MediaModificationTimestamp");
			String url = rs.getString("MediaUrl");
			int listingId = rs.getInt("ListingId");
	  	System.out.printf("%-20s\t%-15s\t%-20s\n", timestamp, url, listingId);
		}
		System.out.println( );
		rs.close();
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	void showAllCategories() throws SQLException {
		Statement stmt = conn.createStatement();
     	String qry = "select * from CATEGORY";
     	ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
     	// CategoryId, CategoryName
     	System.out.println("Category Id\t\tName");
     	printLine();
     	
		while (rs.next()) {
			int categoryId = rs.getInt("CategoryId");
			String name = rs.getString("CategoryName");
			System.out.printf("%-20d\t%-15s\n", categoryId, name);
		}
		System.out.println( );
		rs.close();
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	void showAllTypes() throws SQLException {
		Statement stmt = conn.createStatement();
     	String qry = "select * from TYPE";
     	ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
     	// TypeId, PropertyType
     	System.out.println("Type Id\t\tProperty Type");
     	printLine();
     	
		while (rs.next()) {
			int typeId = rs.getInt("TypeId");
			String type = rs.getString("PropertyType");
			System.out.printf("%-20d\t%-15s\n", typeId, type);;
		}
		System.out.println( );
		rs.close();
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	void showAllStatuses() throws SQLException {
		Statement stmt = conn.createStatement();
     	String qry = "select * from STATUS";
     	ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
     	// StatusId, ListingStatus
     	System.out.println("Status Id\t\tStatus");
     	printLine();
     	
     	while (rs.next()) {
			int statusId = rs.getInt("StatusId");
			String status = rs.getString("ListingStatus");
			System.out.printf("%-20d\t%-15s\n", statusId, status);;
		}
		System.out.println( );
		rs.close();
	}
	
	/**
	 * 
	 */
	void insertAddress() throws SQLException {
		//FullStreetAddress, City, StateOrProvince, PostalCode, Country
		
		boolean done = false;
		String choice;
		String address;
		String city;
		String state;
		String country;
		choice = address = city = state = country = null;
		
		int postalCode = 0;
		
		while (!done) {
			System.out.print("Please enter the address: ");
			address = scanner.nextLine();
			
			System.out.print("Please enter the city: ");
			city = scanner.nextLine();
			
			System.out.print("Please enter the state: ");
			state = scanner.nextLine();
			
			System.out.print("Please enter the country: ");
			country = scanner.nextLine();
			
			System.out.print("Please enter the postal code: ");
			boolean valid = false;
			while (!valid) {
				try {
					postalCode = Integer.parseInt(scanner.nextLine());
					valid = true;
				} catch (NumberFormatException nfe) {
					System.out.println("You've entered an incorrect postal code format. Please enter the digits corresponding to the postal code");
				}
			}
			clearConsole();
			printHeader(adminMode);
			printHeader(insertRecord);
			System.out.println("\nAddress: " + address +
							   "\nCity: " + city + 
							   "\nState: " + state +
							   "\nCountry: " + country +
							   "\nPostal Code: " + postalCode);
			System.out.print("Is this the correct information? (y/n) ");
			choice = scanner.nextLine();
			
			if (Character.toLowerCase(choice.trim().charAt(0)) == 'y') {
				done = true;
			}
		}
				
		Statement stmt = conn.createStatement();
		
     	String qry = "insert into ADDRESS(FullStreetAddress, City, StateOrProvince, PostalCode, Country) \n"
     				 + "values ('" + address + "', '" + city + "', '" + state + "', " + postalCode + ", '"
     				 + country + "')";
     	
     	stmt.execute(qry);
     
     	System.out.println("\nThe address has been inserted\n");
	}
	
	/**
	 * @throws SQLException 
	 * 
	 */
	void updateAddress() throws SQLException {
		//FullStreetAddress, City, StateOrProvince, PostalCode, Country
		
		ArrayList<String> addressInfo = getAddresses();
		
		int i = 0;
		System.out.print("\n\t" + "1) Full Street Address: " + addressInfo.get(i++));
		System.out.print("\n\t" + "2) City: " + addressInfo.get(i++));
		System.out.print("\n\t" + "3) State Or Province: " + addressInfo.get(i++));
		System.out.print("\n\t" + "4) Country: " + addressInfo.get(i++));
		System.out.print("\n\t" + "5) Postal Code: " + addressInfo.get(i++));
		
		int addressId = 0;
		
		try {
			addressId = Integer.parseInt(addressInfo.get(i));
		} catch(NumberFormatException nfe) {
			System.out.println("error while updating address. program will now end");
			System.exit(-1);
		}
		
		boolean correct = false, done = false;
		int updateOption = 0;
		while (!done) {
			while (!correct) {
				System.out.println("\n\nPlease insert the field you want to update: (1-5)");
				try {
					updateOption = Integer.parseInt(scanner.nextLine());
					if (updateOption < 1 || updateOption > 5) throw new NumberFormatException();
					correct = true;
				} catch (NumberFormatException nfe) {
					System.out.println("\n\nYou have entered an invalid option. Please choose the digit corresponding to your desired option\n");
				}
			}
		
			switch (updateOption) {
				case 1: updateAddressFullStreetAddress(addressId);
						break;
				case 2: updateAddressCity(addressId);
						break;
				case 3: updateAddressStateOrProvince(addressId);
						break;
				case 4:	updateAddressCountry(addressId);
						break;
				case 5:	updateAddressPostalCode(addressId);
						break;
			}
		
			String choiceString = null;
			
			while(choiceString == null || choiceString.trim().isEmpty()) {
				System.out.println("\n\nWould you like to continue updating the same address? (y/n) :");
				choiceString = scanner.nextLine();
				if (!choiceString.trim().isEmpty()) { 
					if (Character.toLowerCase(choiceString.trim().charAt(0)) == 'n') {
						done = true;
					} else if (Character.toLowerCase(choiceString.trim().charAt(0)) == 'y') {
						correct = false;
						done = false;
					} else {
						choiceString = null;
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	private static ArrayList<String> getAddresses() throws SQLException {
		
		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = "Select * from Address";
   		
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);
	   	   
   		// store the keys into the database
   		HashMap<Integer, ArrayList<String>> addresses = new HashMap<Integer,ArrayList<String>>();
   		
   		// display the companies to the user
   		System.out.print("ADDRESSES\n-------------------");
   		int i = 0;
   		while (rs.next()) {
   			ArrayList<String> temp = new ArrayList<String>();
   			String tempString = null;
   			
   			System.out.print("\n" + (i++ + 1) + ")");
   			
   			tempString = rs.getString("FullStreetAddress");
   			System.out.print("\n\t" + "Full Street Address: " + tempString);
   			temp.add(tempString);
   			
   			tempString = rs.getString("City");
   			System.out.print("\n\t" + "City: " + tempString);
   			temp.add(tempString);
   			
   			tempString = rs.getString("StateOrProvince");
   			System.out.print("\n\t" + "State Or Province: " + tempString);
   			temp.add(tempString);
   			
   			tempString = rs.getString("Country");
   			System.out.print("\n\t" + "Country: " + tempString);
   			temp.add(tempString);
   			
   			tempString = Integer.toString(rs.getInt("PostalCode"));
   			System.out.print("\n\t" + "Postal Code: " + tempString);
   			temp.add(tempString);
   			
   			temp.add(Integer.toString(rs.getInt("AddressId")));
   			
   			addresses.put(rs.getInt("AddressId"), temp);
   		}
	   
   		// loop until the user selects a valid option
   		int choice = 0;
   		boolean valid = false;
   		while (!valid) {
	   		try {
	   	   		System.out.print("\n\nPlease choose an address to update: ");
	   			choice = Integer.parseInt(scanner.nextLine());
	   			
	   			if(choice <= 0 || choice > (addresses.size())) {
	   				throw new NumberFormatException();
	   			}
	   			valid = true;
	   		} catch(NumberFormatException nfe) {
	   			System.out.println("Your selection was invalid, please try again");
	   		}
   		}
   		
   		// return the primary key of the MLS company
   		return addresses.get(choice);
	}
	
	private static void updateAddressFullStreetAddress(int addressId) throws SQLException {
		String fullStreetAddress = null;
		while (fullStreetAddress == null || fullStreetAddress.trim().isEmpty()) {
			System.out.print("Please enter a street address for the address: ");
			fullStreetAddress = scanner.nextLine();
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	"update ADDRESS \n" +
   						"SET FullStreetAddress = '" + fullStreetAddress + "' \n" +
   						"WHERE AddressId = " + addressId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
   		return;
	}
	
	private static void updateAddressCity(int addressId) throws SQLException {
		String city = null;
		while (city == null || city.trim().isEmpty()) {
			System.out.print("Please enter a city for the address: ");
			city = scanner.nextLine();
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	"update ADDRESS \n" +
   						"SET City = '" + city + "' \n" +
   						"WHERE AddressId = " + addressId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
   		return;
	}
	
	private static void updateAddressStateOrProvince(int addressId) throws SQLException {
		String stateOrProvince = null;
		while (stateOrProvince == null || stateOrProvince.trim().isEmpty()) {
			System.out.print("Please enter a state or province for the address: ");
			stateOrProvince = scanner.nextLine();
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	"update ADDRESS \n" +
   						"SET StateOrProvince = '" + stateOrProvince + "' \n" +
   						"WHERE AddressId = " + addressId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
   		return;
	}
	
	private static void updateAddressCountry(int addressId) throws SQLException {
		String country = null;
		while (country == null || country.trim().isEmpty()) {
			System.out.print("Please enter a country for the address: ");
			country = scanner.nextLine();
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	"update ADDRESS \n" +
   						"SET Country = '" + country + "' \n" +
   						"WHERE AddressId = " + addressId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
   		return;
	}
	
	private static void updateAddressPostalCode(int addressId) throws SQLException {
		String postalCode = null;
		int postalCodeInt = 0;
		while (postalCode == null || postalCode.trim().isEmpty()) {
			System.out.print("Please enter a new postal code price: ");
			postalCode = scanner.nextLine();
			try {
				postalCodeInt = Integer.parseInt(postalCode);
			} catch(NumberFormatException nfe) {
				postalCode = null;
			}
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	"update ADDRESS \n" +
   						"SET PostalCode = " + postalCodeInt + " \n" +
   						"WHERE AddressId = " + addressId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
   		return;
	}
	
	/**
	 * 
	 */
	void insertListing() throws SQLException {
		// ListingId, ListPrice, ListURL, ListingDescription, ListingDate, Bedrooms, Bathrooms, �TypeId�, �StatusId�, �CategoryId�, �MlsId�, �AddressId�
		boolean valid = false, stop = false;
		
		String choice;
		String url;
		String description;
		
		choice = url = description = null;
		
		int bedrooms, bathrooms, type, status, category, mls, address, price;
		bedrooms = bathrooms = type = status = category = mls = address = price = 0;
		
		while (!stop) {
			System.out.print("Please enter the price: ");
			price = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Please enter the URL: ");
			url = scanner.nextLine();
			
			System.out.print("Please enter a brief, one line description: ");
			description = scanner.nextLine();
			
			System.out.print("Please enter the bedrooms: ");
			valid = false;
			while (!valid) {
				try {
					bedrooms = Integer.parseInt(scanner.nextLine());
					valid = true;
				} catch (NumberFormatException nfe) {
					System.out.println("You've entered an invalid number of bedrooms. please enter an integer representation");
				}
			}
			
			System.out.print("Please enter the bathrooms: ");
			valid = false;
			while (!valid) {
				try {
					bathrooms = Integer.parseInt(scanner.nextLine());
					valid = true;
				} catch (NumberFormatException nfe) {
					System.out.println("You've entered an invalid number of bathrooms. please enter an integer representation");
				}
			}
			
			type = getType();
			status = getStatus();
			category = getCategory();
			mls = getMls();
			address = getAddress();
			
			printLine();
			System.out.println("\nPrice:\t" + price +
							   "\nURL:\t" + url + 
							   "\nDescription:\t" + description +
							   "\nBedrooms:\t" + bedrooms +
							   "\nBathrooms: \t" + bathrooms + 
							   "\nType Id:\t" + type + 
							   "\nStatus Id:\t" + status + 
							   "\nCategory Id:\t" + category +
							   "\nMLS Id:\t\t" + mls + 
							   "\nAddress Id:\t" + address);
			System.out.print("Is this the correct information(y or n): ");
			choice = scanner.nextLine();
			if (Character.toLowerCase(choice.trim().charAt(0)) == 'y') stop = true;
		}
		
		Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

		Statement stmt = conn.createStatement();
		
     	String qry = "insert into LISTING(ListPrice, ListURL, ListingDescription, ListingDate, Bedrooms, Bathrooms, TypeId, StatusId, CategoryId, MlsId, AddressId) "
     				 + "values(" + price + ", '" + url + "', '" + description + "', '" + date + "', " + bedrooms + ", " + bathrooms + ", " + type + ", " + status + ", "
     				 + category + ", " + mls + ", " + address + ")";
     
     	boolean success = stmt.execute(qry);
     	
     	System.out.println("\nsuccessfully inserted a listing.\n");

   	}
	
	 public static int getType() throws SQLException {
	   		Statement stmt = conn.createStatement();
	   		
	   		// generate the query string
	   		String qry = "Select * from TYPE";
	   		
	   		// execute the query
	   		ResultSet rs = stmt.executeQuery(qry);
		   	   
	   		// store the keys into the database
	   		ArrayList<Integer> MlsId = new ArrayList<Integer>();
	   		
	   		// display the companies to the user
	   		System.out.print("Property Type\n------------------");
	   		int i = 0;
	   		while (rs.next()) {
	   			MlsId.add(rs.getInt("Typeid"));
	   			System.out.print("\n" + (i++ + 1) + ") " + rs.getString("propertytype"));
	   		}
		   
	   		// loop until the user selects a valid option
	   		int choice = 0;
	   		boolean valid = false;
	   		while (!valid) {
		   		try {
		   	   		System.out.print("\n\nPlease choose a property type: ");
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
	 
	 public static int getStatus() throws SQLException {
	   		Statement stmt = conn.createStatement();
	   		
	   		// generate the query string
	   		String qry = "Select * from STATUS";
	   		
	   		// execute the query
	   		ResultSet rs = stmt.executeQuery(qry);
		   	   
	   		// store the keys into the database
	   		ArrayList<Integer> status = new ArrayList<Integer>();
	   		
	   		// display the statuses to the user
	   		System.out.print("Status\n------------------");
	   		int i = 0;
	   		while (rs.next()) {
	   			status.add(rs.getInt("StatusId"));
	   			System.out.print("\n" + (i++ + 1) + ") " + rs.getString("listingstatus"));
	   		}
		   
	   		// loop until the user selects a valid option
	   		int choice = 0;
	   		boolean valid = false;
	   		while (!valid) {
		   		try {
		   	   		System.out.print("\n\nPlease choose a Status: ");
		   			choice = Integer.parseInt(scanner.nextLine());
		   			
		   			if(choice <= 0 || choice > (status.size())) {
		   				throw new NumberFormatException();
		   			}
		   			valid = true;
		   		} catch(NumberFormatException nfe) {
		   			System.out.println("Your selection was invalid, please try again");
		   		}
	   		}
	   		
	   		// return the primary key of the status
	   		return status.get(choice - 1);
	 }
	 
	 public static int getCategory() throws SQLException {
	   		Statement stmt = conn.createStatement();
	   		
	   		// generate the query string
	   		String qry = "Select * from CATEGORY";
	   		
	   		// execute the query
	   		ResultSet rs = stmt.executeQuery(qry);
		   	   
	   		// store the keys into the database
	   		ArrayList<Integer> category = new ArrayList<Integer>();
	   		
	   		// display the companies to the user
	   		System.out.print("CATEGORIES\n------------------");
	   		int i = 0;
	   		while (rs.next()) {
	   			category.add(rs.getInt("CATEGORYID"));
	   			System.out.print("\n" + (i++ + 1) + ") " + rs.getString("CATEGORYNAME"));
	   		}
		   
	   		// loop until the user selects a valid option
	   		int choice = 0;
	   		boolean valid = false;
	   		while (!valid) {
		   		try {
		   	   		System.out.print("\n\nPlease choose a category: ");
		   			choice = Integer.parseInt(scanner.nextLine());
		   			
		   			if(choice <= 0 || choice > (category.size())) {
		   				throw new NumberFormatException();
		   			}
		   			valid = true;
		   		} catch(NumberFormatException nfe) {
		   			System.out.println("Your selection was invalid, please try again");
		   		}
	   		}
	   		
	   		// return the primary key of the category
	   		return category.get(choice - 1);
	 }
	 
	 public static int getMls() throws SQLException {
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
	 
	 public static int getAddress() throws SQLException {
	   		Statement stmt = conn.createStatement();
	   		
	   		// generate the query string
	   		String qry = "Select * from ADDRESS";
	   		
	   		// execute the query
	   		ResultSet rs = stmt.executeQuery(qry);
		   	   
	   		// store the keys into the database
	   		ArrayList<Integer> MlsId = new ArrayList<Integer>();
	   		
	   		// display the companies to the user
	   		System.out.print("ADDRESSES\n-------------------");
	   		int i = 0;
	   		while (rs.next()) {
	   			MlsId.add(rs.getInt("AddressId"));
	   			System.out.print("\n" + (i++ + 1) + ")");
	   			System.out.print("\n\t" + "City: " + rs.getString("City"));
	   			System.out.print("\n\t" + "State/Province: " + rs.getString("StateOrProvince"));
	   			System.out.print("\n\t" + "PostalCode: " + rs.getInt("PostalCode"));
	   			System.out.print("\n\t" + "Country: "  + rs.getString("Country"));
	   			System.out.print("\n\t" + "Full Street Address" + rs.getString("FullStreetAddress"));
	   		}
		   
	   		// loop until the user selects a valid option
	   		int choice = 0;
	   		boolean valid = false;
	   		while (!valid) {
		   		try {
		   	   		System.out.print("\n\nPlease choose an address: ");
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
	 
	 public static int getListingId() throws SQLException {
	   		Statement stmt = conn.createStatement();
	   		
	   		// generate the query string
	   		String qry = "Select * from LISTING";
	   		
	   		// execute the query
	   		ResultSet rs = stmt.executeQuery(qry);
		   	   
	   		// store the keys into the database
	   		ArrayList<Integer> listingId = new ArrayList<Integer>();
	   		
	   		// display the companies to the user
	   		System.out.print("LISTINGS\n-------------------");
	   		int i = 0;
	   		while (rs.next()) {
	   			listingId.add(rs.getInt("ListingId"));
	   			System.out.print("\n" + (i++ + 1) + ")");
	   			System.out.print("\n\t" + "Description: " + rs.getString("ListingDescription"));
	   			System.out.print("\n\t" + "URL: " + rs.getString("ListURL"));
	   			System.out.print("\n\t" + "Price: " + rs.getInt("ListPrice"));
	   			System.out.print("\n\t" + "Bedrooms: " + rs.getString("Bedrooms"));
	   			System.out.print("\n\t" + "Bathrooms: " + rs.getString("Bathrooms"));
	   			System.out.print("\n\t" + "MLS Company: " + getMlsInfo(rs.getInt("MlsId")));
	   			System.out.print("\n\t" + "Category: " + getCategoryName(rs.getInt("CategoryId")));
	   			System.out.print("\n\t" + "Property Type: " + getTypeName(rs.getInt("TypeId")));
	   			System.out.print("\n\t" + "Address: " + getAddressName(rs.getInt("AddressId")));
	   			System.out.println("\n\t" + "Status: " + getStatusInfo(rs.getInt("StatusId")));
	   		}
		   
	   		// loop until the user selects a valid option
	   		int choice = 0;
	   		boolean valid = false;
	   		while (!valid) {
		   		try {
		   	   		System.out.print("\n\nPlease choose a listing: ");
		   			choice = Integer.parseInt(scanner.nextLine());
		   			
		   			if(choice <= 0 || choice > (listingId.size())) {
		   				throw new NumberFormatException();
		   			}
		   			valid = true;
		   		} catch(NumberFormatException nfe) {
		   			System.out.println("Your selection was invalid, please try again");
		   		}
	   		}
	   		
	   		// return the primary key of the MLS company
	   		return listingId.get(choice - 1);
	 }
	
	
	
	/**
	 * @throws SQLException 
	 * 
	 */
	void updateListing() throws SQLException {
		
		ArrayList<String> listingInfo = getListing();
		
		int i = 0;
		System.out.print("\n\t" + "1) Description: " + listingInfo.get(i++));
		System.out.print("\n\t" + "2) URL: " + listingInfo.get(i++));
		System.out.print("\n\t" + "3) Price: " + listingInfo.get(i++));
		System.out.print("\n\t" + "4) Bedrooms: " + listingInfo.get(i++));
		System.out.print("\n\t" + "5) Bathrooms: " + listingInfo.get(i++));
		System.out.print("\n\t" + "6) MLS Company: " + listingInfo.get(i++));
		System.out.print("\n\t" + "7) Category: " + listingInfo.get(i++));
		System.out.print("\n\t" + "8) Property Type: " + listingInfo.get(i++));
		System.out.print("\n\t" + "9) Address: " + listingInfo.get(i++));
		System.out.println("\n\t 10) Status: " + listingInfo.get(i++));
		
		int listingId = 0;
		
		try {
			listingId = Integer.parseInt(listingInfo.get(i));
		} catch(NumberFormatException nfe) {
			System.out.println("error while updating listing. program will now end");
			System.exit(-1);
		}
		
		boolean correct = false, done = false;
		int updateOption = 0;
		while (!done) {
			while (!correct) {
				System.out.println("\n\nPlease insert the field you want to update");
				try {
					updateOption = Integer.parseInt(scanner.nextLine());
					if (updateOption < 1 || updateOption > 11) throw new NumberFormatException();
					correct = true;
				} catch (NumberFormatException nfe) {
					System.out.println("\n\nYou have entered an invalid option. Please choose the digit corresponding to your desired option\n");
				}
			}
		
			switch (updateOption) {
				case 1: updateListingDescription(listingId);
						break;
				case 2: updateListingUrl(listingId);
						break;
				case 3: updateListingPrice(listingId);
						break;
				case 4:	updateListingBedrooms(listingId);
						break;
				case 5:	updateListingBathrooms(listingId);
						break;
				case 6:	updateListingMlsCompany(listingId);
						break;
				case 7:	updateListingCategory(listingId);
						break;
				case 8:	updateListingPropertyType(listingId);
						break;
				case 9:	updateListingAddress(listingId);
						break;
				case 10:updateListingStatus(listingId);
						break;
			}
		
			String choiceString = null;
			
			while(choiceString == null || choiceString.trim().isEmpty()) {
				System.out.println("Would you like to continue updating the same listing? (y/n)");
				choiceString = scanner.nextLine();
				if (!choiceString.trim().isEmpty()) { 
					if (Character.toLowerCase(choiceString.trim().charAt(0)) == 'n') {
						done = true;
					} else if (Character.toLowerCase(choiceString.trim().charAt(0)) == 'y') {
						correct = false;
						done = false;
					} else {
						choiceString = null;
					}
				}
			}
		}
	}
	
	private static void updateListingDescription(int listingId) throws SQLException {
		String description = null;
		while (description == null || description.trim().isEmpty()) {
			System.out.print("Please enter a decription for the listing: ");
			description = scanner.nextLine();
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	"update LISTING \n" +
   						"SET ListingDescription = '" + description + "' \n" +
   						"WHERE ListingId = " + listingId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
   		return;
	}

	private static void updateListingUrl(int listingId) throws SQLException {
		String url = null;
		while (url == null || url.trim().isEmpty()) {
			System.out.print("Please enter a url for the listing: ");
			url = scanner.nextLine();
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	" update LISTING \n" +
   						" SET LISTURL = '" + url + "' \n" + 
   						" WHERE listingId = " + listingId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
	}

	private static void updateListingPrice(int listingId) throws SQLException {
		String price = null;
		int priceInt = 0;
		while (price == null || price.trim().isEmpty()) {
			System.out.print("Please enter a new list price: ");
			price = scanner.nextLine();
			try {
				priceInt = Integer.parseInt(price);
			} catch(NumberFormatException nfe) {
				price = null;
			}
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	" update LISTING \n" +
   						" SET ListPrice = " + priceInt + " \n" +
   						" WHERE ListingId = " + listingId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
	}
	
	private static void updateListingBathrooms(int listingId) throws SQLException {
		String bathrooms = null;
		int bathroomsInt = 0;
		while (bathrooms == null || bathrooms.trim().isEmpty()) {
			System.out.print("Please enter a new list price: ");
			bathrooms = scanner.nextLine();
			try {
				bathroomsInt = Integer.parseInt(bathrooms);
			} catch(NumberFormatException nfe) {
				bathrooms = null;
			}
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	" update LISTING \n" +
   						" SET BATHROOMS = " + bathroomsInt + " \n" +
   						" WHERE ListingId = " + listingId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
	}
	
	private static void updateListingBedrooms(int listingId) throws SQLException {
		String bedrooms = null;
		int bedroomsInt = 0;
		while (bedrooms == null || bedrooms.trim().isEmpty()) {
			System.out.print("Please enter a new list price: ");
			bedrooms = scanner.nextLine();
			try {
				bedroomsInt = Integer.parseInt(bedrooms);
			} catch(NumberFormatException nfe) {
				bedrooms = null;
			}
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	" update LISTING \n" +
   						" SET BEDROOMS = " + bedroomsInt + " \n" +
   						" WHERE ListingId = " + listingId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
	}
	
	private static void updateListingMlsCompany(int listingId) throws SQLException {
		
		int mlsCompanyId = getMls();
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	" update LISTING \n" +
   						" SET MLSID = " + mlsCompanyId + " \n" +
   						" WHERE ListingId = " + listingId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
	}
	
	private static void updateListingCategory(int listingId) throws SQLException {
		
		int categoryId = getCategory();
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	" update LISTING \n" +
   						" SET CATEGORYID = " + categoryId + " \n" +
   						" WHERE ListingId = " + listingId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
	}
	
	private static void updateListingPropertyType(int listingId) throws SQLException {
   		Statement stmt = conn.createStatement();
   		
   		int typeId = getType();
   		
   		// generate the query string
   		String qry = 	" update LISTING \n" +
   						" SET TYPEID = " + typeId + " \n" +
   						" WHERE ListingId = " + listingId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
	}
	
	private static void updateListingAddress(int listingId) throws SQLException {
		
		int addressId = getAddress();
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	" update LISTING \n" +
   						" SET ADDRESSID = " + addressId + " \n" +
   						" WHERE ListingId = " + listingId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
	}
	
	private static void updateListingStatus(int listingId) throws SQLException {
		
		int statusId = getStatus();
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	" update LISTING " +
   						" SET StatusId = " + statusId +
   						" WHERE ListingId = " + listingId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	private static ArrayList<String> getListing() throws SQLException {
		
		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = "Select * from LISTING";
   		
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);
	   	   
   		// store the keys into the database
   		HashMap<Integer, ArrayList<String>> listings = new HashMap<Integer,ArrayList<String>>();
   		
   		// display the companies to the user
   		System.out.print("ADDRESSES\n-------------------");
   		int i = 0;
   		while (rs.next()) {
   			ArrayList<String> temp = new ArrayList<String>();
   			String tempString = null;
   			System.out.print("\n" + (i++ + 1) + ")");
   			
   			tempString = rs.getString("ListingDescription");
   			System.out.print("\n\t" + "Description: " + tempString);
   			temp.add(tempString);
   			
   			tempString = rs.getString("ListUrl");
   			System.out.print("\n\t" + "URL: " + tempString);
   			temp.add(tempString);
   			
   			tempString = rs.getString("ListPrice");
   			System.out.print("\n\t" + "Price: " + tempString);
   			temp.add(tempString);
   			
   			System.out.print("\n\t" + "Date Listed: "  + tempString);
   			
   			tempString = Integer.toString(rs.getInt("Bedrooms"));
   			System.out.print("\n\t" + "Bedrooms: " + tempString);
   			temp.add(tempString);
   			
   			tempString = Integer.toString(rs.getInt("Bathrooms"));
   			System.out.print("\n\t" + "Bathrooms: " + tempString);
   			temp.add(tempString);
   			
   			tempString = getMlsInfo(rs.getInt("MLSID"));
   			System.out.print("\n\t" + "MLS Company: " + tempString);
   			temp.add(tempString);
   			
   			tempString = getCategoryName(rs.getInt("CATEGORYID"));
   			System.out.print("\n\t" + "Category: " + tempString);
   			temp.add(tempString);
   			
   			tempString = getTypeName(rs.getInt("TypeId"));
   			System.out.print("\n\t" + "Property Type: " + tempString);
   			temp.add(tempString);
   			
   			tempString = getAddressName(rs.getInt("AddressId"));
   			System.out.print("\n\t" + "Address: " + tempString);
   			temp.add(tempString);
   			
   			tempString = getStatusInfo(rs.getInt("STATUSID"));
   			System.out.print("\n\t" + "Status: " + tempString);
   			temp.add(tempString);
   			
   			temp.add(Integer.toString(rs.getInt("LISTINGID")));
   			
   			listings.put(rs.getInt("ListingId"), temp);
   		}
	   
   		// loop until the user selects a valid option
   		int choice = 0;
   		boolean valid = false;
   		while (!valid) {
	   		try {
	   	   		System.out.print("\n\nPlease choose a listing to update: ");
	   			choice = Integer.parseInt(scanner.nextLine());
	   			
	   			if(choice <= 0 || choice > (listings.size())) {
	   				throw new NumberFormatException();
	   			}
	   			valid = true;
	   		} catch(NumberFormatException nfe) {
	   			System.out.println("Your selection was invalid, please try again");
	   		}
   		}
   		
   		// return the primary key of the MLS company
   		return listings.get(choice);
	}
	
	private static String getCategoryName(int categoryId) throws SQLException {
		
		String categoryName = null;
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = "Select categoryName from CATEGORY where CATEGORYID = " + categoryId;
   		
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);
   		
   		while (rs.next()) {
   			categoryName = rs.getString("categoryName");
   		}
   		
		return categoryName;
	}
	
	private static String getMlsInfo(int mlsId) throws SQLException {
		
		String mlsInfo = null;
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = "Select MLSNAME from MLS where MLSID = " + mlsId;
   		
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);
   		
   		while (rs.next()) {
   			mlsInfo = rs.getString("MLSNAME");
   		}
   		
		return mlsInfo;
	}
	
	private static String getStatusInfo(int statusId) throws SQLException {
		
		String listingStatus = null;
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = "Select LISTINGSTATUS from STATUS where StatusId = " + statusId;
   		
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);
   		
   		while (rs.next()) {
   			listingStatus = 	rs.getString("LISTINGSTATUS");
   		}
   		
		return listingStatus;
	}
	
	private static String getAddressName(int addressId) throws SQLException {
		
		String addressName = null;
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = "Select * from ADDRESS where AddressId = " + addressId;
   		
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);
   		
   		while (rs.next()) {
   			addressName = 	rs.getString("FULLSTREETADDRESS") + "\n\t\t " +
   							rs.getString("CITY") + "\n\t\t " +
   							rs.getString("STATEORPROVINCE") + "\n\t\t " +
   							rs.getString("POSTALCODE") + "\n\t\t " +
   							rs.getString("COUNTRY"); 
   		}
   		
		return addressName;
	}
	
	private static String getTypeName(int typeId) throws SQLException {
		
		String typeName = null;
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = "Select PROPERTYTYPE from TYPE where typeId = " + typeId;
   		
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);
   		
   		while (rs.next()) {
   			typeName =rs.getString("PROPERTYTYPE"); 
   		}
   		
		return typeName;
	}
	
	/**
	 * 
	 */
	private void insertMls() throws SQLException{
		//MlsId, MlsName, MlsAddress, MlsEmail, MlsPhoneNumber, MlsFax
		
		boolean done = false;
		String choice = null;
		String name = null;
		String address = null;
		String email = null;
		String phone = null;
		String fax = null;
		
		while (!done) {
			System.out.print("Please enter the name of the MLS company: ");
			name = scanner.nextLine();

			System.out.print("Please enter the address: ");
			address = scanner.nextLine();
			
			System.out.print("Please enter the email: ");
			email = scanner.nextLine();
			
			System.out.print("Please enter the phone number: ");
			phone = scanner.nextLine();
			
			System.out.print("Please enter the fax number: ");
			fax = scanner.nextLine();
			
			printLine();
			System.out.println("\nName:\t" + name +
							   "\nAddress:\t" + address + 
							   "\nState:\t" + email +
							   "\nCountry\t: " + phone +
							   "\nPostal Code\t: " + fax);
			System.out.print("Is this the correct information(y or n): ");
			choice = scanner.nextLine();
			if (Character.toLowerCase(choice.trim().charAt(0)) == 'y') {
				done = true;
			}
		}
			
		Statement stmt = conn.createStatement();
     	String qry = "insert into MLS(MlsName, MlsAddress, MlsEmail, MlsPhoneNumber, MlsFax) "
     				 + "values ('" + name + "', '" + address + "', '" + email + "', '" + phone + "', '"
     				 + fax +"')";
     	
     	boolean inserted = stmt.execute(qry);
     	

 		System.out.println("Successfully added a new MLS Company.\n\n");	    

	}
	
	void updateMls() throws SQLException {
		//MlsName, MlsAgent, MlsAddress, MlsEmail, MlsPhoneNumber, MlsFax
		ArrayList<String> mlsInfo = getMlsCompanies();
		
		int i = 0;
		System.out.print("\n\t" + "1) Name: " + mlsInfo.get(i++));
		System.out.print("\n\t" + "2) Address: " + mlsInfo.get(i++));
		System.out.print("\n\t" + "3) Email: " + mlsInfo.get(i++));
		System.out.print("\n\t" + "4) Phone Number: " + mlsInfo.get(i++));
		System.out.print("\n\t" + "5) Fax: " + mlsInfo.get(i++));
		
		int mlsId = 0;
		
		try {
			mlsId = Integer.parseInt(mlsInfo.get(i));
		} catch(NumberFormatException nfe) {
			System.out.println("error while updating mls. program will now end");
			System.exit(-1);
		}
		
		boolean correct = false, done = false;
		int updateOption = 0;
		while (!done) {
			while (!correct) {
				System.out.println("\n\nPlease insert the field you want to update");
				try {
					updateOption = Integer.parseInt(scanner.nextLine());
					if (updateOption < 1 || updateOption > 5) throw new NumberFormatException();
					correct = true;
				} catch (NumberFormatException nfe) {
					System.out.println("\n\nYou have entered an invalid option. Please choose the digit corresponding to your desired option\n");
				}
			}
		
			switch (updateOption) {
				case 1: updateMlsName(mlsId);
						break;
				case 2: updateMlsAddress(mlsId);
						break;
				case 3: updateMlsEmail(mlsId);
						break;
				case 4:	updateMlsPhoneNumber(mlsId);
						break;
				case 5:	updateMlsFax(mlsId);
						break;
			}
		
			String choiceString = null;
			
			while(choiceString == null || choiceString.trim().isEmpty()) {
				System.out.println("Would you like to continue updating the same company? (y/n)");
				choiceString = scanner.nextLine();
				if (!choiceString.trim().isEmpty()) { 
					if (Character.toLowerCase(choiceString.trim().charAt(0)) == 'n') {
						done = true;
					} else if (Character.toLowerCase(choiceString.trim().charAt(0)) == 'y') {
						correct = false;
						done = false;
					} else {
						choiceString = null;
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	private static ArrayList<String> getMlsCompanies() throws SQLException {
		
		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = "Select * from MLS";
   		
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);
	   	   
   		// store the keys into the database
   		HashMap<Integer, ArrayList<String>> mlsCompanies = new HashMap<Integer,ArrayList<String>>();
   		
   		// display the companies to the user
   		System.out.print("MLS COMPANIES\n-------------------");
   		int i = 0;
   		while (rs.next()) {
   			ArrayList<String> temp = new ArrayList<String>();
   			String tempString = null;
   			
   			System.out.print("\n" + (i++ + 1) + ")");
   			
   			tempString = rs.getString("MlsName");
   			System.out.print("\n\t" + "Name: " + tempString);
   			temp.add(tempString);
   			
   			tempString = rs.getString("MlsAddress");
   			System.out.print("\n\t" + "Address: " + tempString);
   			temp.add(tempString);
   			
   			tempString = rs.getString("MlsEmail");
   			System.out.print("\n\t" + "Email: " + tempString);
   			temp.add(tempString);
   			
   			tempString = rs.getString("MlsPhoneNumber");
   			System.out.print("\n\t" + "Phone Nnumber: " + tempString);
   			temp.add(tempString);
   			
   			tempString = rs.getString("MlsFax");
   			System.out.print("\n\t" + "Fax: " + tempString);
   			temp.add(tempString);
   			
   			temp.add(Integer.toString(rs.getInt("MlsId")));
   			
   			mlsCompanies.put(rs.getInt("MlsId"), temp);
   		}
	   
   		// loop until the user selects a valid option
   		int choice = 0;
   		boolean valid = false;
   		while (!valid) {
	   		try {
	   	   		System.out.print("\n\nPlease choose a company to update: ");
	   			choice = Integer.parseInt(scanner.nextLine());
	   			
	   			if(choice <= 0 || choice > (mlsCompanies.size())) {
	   				throw new NumberFormatException();
	   			}
	   			valid = true;
	   		} catch(NumberFormatException nfe) {
	   			System.out.println("Your selection was invalid, please try again");
	   		}
   		}
   		
   		// return the primary key of the MLS company
   		return mlsCompanies.get(choice);
	}
	
	private static void updateMlsName(int mlsId) throws SQLException {
		String name = null;
		while (name == null || name.trim().isEmpty()) {
			System.out.print("Please enter a name for the company: ");
			name = scanner.nextLine();
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	"update MLS \n" +
   						"SET MlsName = '" + name + "' \n" +
   						"WHERE MlsId = " + mlsId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
   		return;
	}
	
	private static void updateMlsAddress(int mlsId) throws SQLException {
		String address = null;
		while (address == null || address.trim().isEmpty()) {
			System.out.print("Please enter an address for the company: ");
			address = scanner.nextLine();
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	"update MLS \n" +
   						"SET MlsAddress = '" + address + "' \n" +
   						"WHERE MlsId = " + mlsId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
   		return;
	}
	
	private static void updateMlsEmail(int mlsId) throws SQLException {
		String email = null;
		while (email == null || email.trim().isEmpty()) {
			System.out.print("Please enter an email for the company: ");
			email = scanner.nextLine();
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	"update MLS \n" +
   						"SET MlsEmail = '" + email + "' \n" +
   						"WHERE MlsId = " + mlsId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
   		return;
	}
	
	private static void updateMlsPhoneNumber(int mlsId) throws SQLException {
		String phoneNumber = null;
		while (phoneNumber == null || phoneNumber.trim().isEmpty()) {
			System.out.print("Please enter a phone number for the company: ");
			phoneNumber = scanner.nextLine();
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	"update MLS \n" +
   						"SET MlsPhoneNumber = '" + phoneNumber + "' \n" +
   						"WHERE MlsId = " + mlsId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
   		return;
	}
	
	private static void updateMlsFax(int mlsId) throws SQLException {
		String fax = null;
		while (fax == null || fax.trim().isEmpty()) {
			System.out.print("Please enter a fax number for the company: ");
			fax = scanner.nextLine();
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	"update MLS \n" +
   						"SET MlsFax = '" + fax + "' \n" +
   						"WHERE MlsId = " + mlsId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
   		return;
	}
	
	
	void insertPhoto() throws SQLException{
		//MediaModificationTimestamp, MediaUrl, ListingId
		
		boolean done = false;
		String choice = null;
		String URL = null;
		Timestamp timestamp = new java.sql.Timestamp(new java.util.Date().getTime());;
		int listingId = 0;

		
		while (!done) {
			System.out.print("Please enter the photo URL: ");
			URL = scanner.nextLine();

			System.out.print("Please enter the listing id: ");
			listingId = getListingId();
			
			boolean correct = false;
			while (!correct) {
				printLine();
				System.out.println("URL:\t" + URL +
								   "\nListing Id:\t" + listingId);
				System.out.print("\nIs this the correct information(y or n): ");
				choice = scanner.nextLine();
				if (Character.toLowerCase(choice.trim().charAt(0)) == 'y') {
					done = true;
					correct = true;
				} else if (Character.toLowerCase(choice.trim().charAt(0)) == 'n') {
					done = false;
					correct = false;
				} else {
					System.out.println("please enter yes or no to indicate that you are satisfied with your input.");
					correct = false;
				}
			}
		}
			
			Statement stmt = conn.createStatement();
	     	String qry = "insert into PHOTO(MediaModificationTimestamp, MediaUrl, ListingId) "
	     				 + "values ('" + timestamp + "', '" + URL + "', " + listingId +")";
	     	
	     	boolean inserted = stmt.execute(qry);
	     	
	     	System.out.println("Successfully added a photo.");
	}
	
	void updatePhoto() throws SQLException {
		//MediaModificationTimestamp, MediaUrl, ListingId
		ArrayList<String> photoInfo = getPhotos();
		
		int i = 0;
		String consumeTimestamp = photoInfo.get(i++);
		System.out.print("\n\t" + "1) URL: " + photoInfo.get(i++));
		System.out.print("\n\t" + "2) Listing Id: " + photoInfo.get(i++));
		
		int photoId = 0;
		
		try {
			photoId = Integer.parseInt(photoInfo.get(i));
		} catch(NumberFormatException nfe) {
			System.out.println("error while updating photo. program will now end");
			System.exit(-1);
		}
		
		boolean correct = false, done = false;
		int updateOption = 0;
		while (!done) {
			while (!correct) {
				System.out.println("\n\nPlease insert the field you want to update");
				try {
					updateOption = Integer.parseInt(scanner.nextLine());
					if (updateOption < 1 || updateOption > 2) throw new NumberFormatException();
					correct = true;
				} catch (NumberFormatException nfe) {
					System.out.println("\n\nYou have entered an invalid option. Please choose the digit corresponding to your desired option\n");
				}
			}
		
			switch (updateOption) {
				case 1: updatePhotoURL(photoId);
						break;
				case 2: updatePhotoListingId(photoId);
						break;
			}
		
			String choiceString = null;
			
			while(choiceString == null || choiceString.trim().isEmpty()) {
				System.out.println("Would you like to continue updating the same photo? (y/n)");
				choiceString = scanner.nextLine();
				if (!choiceString.trim().isEmpty()) { 
					if (Character.toLowerCase(choiceString.trim().charAt(0)) == 'n') {
						done = true;
					} else if (Character.toLowerCase(choiceString.trim().charAt(0)) == 'y') {
						correct = false;
						done = false;
					} else {
						choiceString = null;
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	private static ArrayList<String> getPhotos() throws SQLException {
		
		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = "Select * from PHOTO";
   		
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);
	   	   
   		// store the keys into the database
   		HashMap<Integer, ArrayList<String>> photos = new HashMap<Integer,ArrayList<String>>();
   		
   		// display the companies to the user
   		System.out.print("PHOTOS\n-------------------");
   		int i = 0;
   		while (rs.next()) {
   			ArrayList<String> temp = new ArrayList<String>();
   			String tempString = null;
   			
   			System.out.print("\n" + (i++ + 1) + ")");
   			
   			tempString = rs.getString("MediaModificationTimestamp");
   			System.out.print("\n\t" + "Timestamp: " + tempString);
   			temp.add(tempString);
   			
   			tempString = rs.getString("MediaUrl");
   			System.out.print("\n\t" + "URL: " + tempString);
   			temp.add(tempString);
   			
   			tempString = rs.getString("ListingId");
   			System.out.print("\n\t" + "Listing Id: " + tempString);
   			temp.add(tempString);
   			
   			temp.add(Integer.toString(rs.getInt("PhotoId")));
   			
   			photos.put(rs.getInt("PhotoId"), temp);
   		}
	   
   		// loop until the user selects a valid option
   		int choice = 0;
   		boolean valid = false;
   		while (!valid) {
	   		try {
	   	   		System.out.print("\n\nPlease choose a photo to update: ");
	   			choice = Integer.parseInt(scanner.nextLine());
	   			
	   			if(choice <= 0 || choice > (photos.size())) {
	   				throw new NumberFormatException();
	   			}
	   			valid = true;
	   		} catch (NumberFormatException nfe) {
	   			System.out.println("Your selection was invalid, please try again");
	   		}
   		}
   		
   		// return the primary key of the MLS company
   		return photos.get(choice);
	}
	
	private static void updatePhotoURL(int photoId) throws SQLException {
		String url = null;
		while (url == null || url.trim().isEmpty()) {
			System.out.print("Please enter a photo url: ");
			url = scanner.nextLine();
		}
		
		Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	"update PHOTO \n" +
   						"SET MediaModificationTimestamp = '" + date + "', MediaURL = '" + url +"' \n" +
   						"WHERE PhotoId = " + photoId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
   		return;
	}
	
	private static void updatePhotoListingId(int photoId) throws SQLException {
		int listingId = 0;
		
		listingId = getListingId();
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	"update PHOTO \n" +
   						"SET ListingId = " + listingId + " \n" +
   						"WHERE PhotoId = " + photoId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
   		return;
	}
	
	void insertCategory() throws SQLException {
		//CategoryId, CategoryName
		
		boolean done = false;
		String choice = null;
		String category = null;

		while (!done) {
			System.out.print("Please enter the category: ");
			category = scanner.nextLine();
			
			printLine();
			boolean correct = false;
			while (!correct) {
				System.out.println("\nCategory:\t " + category);
				System.out.print("\nIs this the correct information (y or n): ");
				choice = scanner.nextLine();
				if (Character.toLowerCase(choice.trim().charAt(0)) == 'y') {
					done = true;
					correct = true;
				} else if (Character.toLowerCase(choice.trim().charAt(0)) == 'n') {
					done = false;
					correct = false;
				} else {
					System.out.println("please enter yes or no to indicate that you are satisfied with your input.");
					correct = false;
				}
			}
		}
			
			Statement stmt = conn.createStatement();
	     	String qry = "insert into CATEGORY(CategoryName) "
	     				 + "values ('" + category + "')";
	     	
	     	boolean inserted = stmt.execute(qry);
	}
	
	void updateCategory() throws SQLException {
		//CategoryId, CategoryName
		
		ArrayList<String> categoryInfo = getCategories();
		
		int i = 0;
		System.out.print("\n\t" + "1) Category: " + categoryInfo.get(i++));
		
		int categoryId = 0;
		
		try {
			categoryId = Integer.parseInt(categoryInfo.get(i));
		} catch(NumberFormatException nfe) {
			System.out.println("error while updating category. program will now end");
			System.exit(-1);
		}
		
		boolean correct = false, done = false;
		int updateOption = 0;
		while (!done) {
			while (!correct) {
				System.out.println("\n\nPlease insert the field you want to update");
				try {
					updateOption = Integer.parseInt(scanner.nextLine());
					if (updateOption < 1 || updateOption > 1) throw new NumberFormatException();
					correct = true;
				} catch (NumberFormatException nfe) {
					System.out.println("\n\nYou have entered an invalid option. Please choose the digit corresponding to your desired option\n");
				}
			}
		
			switch (updateOption) {
				case 1: updateCategoryName(categoryId);
						break;
			}
		
			String choiceString = null;
			
			while(choiceString == null || choiceString.trim().isEmpty()) {
				System.out.println("Would you like to continue updating the same category? (y/n)");
				choiceString = scanner.nextLine();
				if (!choiceString.trim().isEmpty()) { 
					if (Character.toLowerCase(choiceString.trim().charAt(0)) == 'n') {
						done = true;
					} else if (Character.toLowerCase(choiceString.trim().charAt(0)) == 'y') {
						correct = false;
						done = false;
					} else {
						choiceString = null;
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	private static ArrayList<String> getCategories() throws SQLException {
		
		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = "Select * from CATEGORY";
   		
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);
	   	   
   		// store the keys into the database
   		HashMap<Integer, ArrayList<String>> categories = new HashMap<Integer,ArrayList<String>>();
   		
   		// display the companies to the user
   		System.out.print("CATEGORIES\n-------------------");
   		int i = 0;
   		while (rs.next()) {
   			ArrayList<String> temp = new ArrayList<String>();
   			String tempString = null;
   			
   			System.out.print("\n" + (i++ + 1) + ")");
   			
   			tempString = rs.getString("CategoryName");
   			System.out.print("\n\t" + "Category: " + tempString);
   			temp.add(tempString);
   			
   			temp.add(Integer.toString(rs.getInt("CategoryId")));
   			
   			categories.put(rs.getInt("CategoryId"), temp);
   		}
	   
   		// loop until the user selects a valid option
   		int choice = 0;
   		boolean valid = false;
   		while (!valid) {
	   		try {
	   	   		System.out.print("\n\nPlease choose a category to update: ");
	   			choice = Integer.parseInt(scanner.nextLine());
	   			
	   			if(choice <= 0 || choice > (categories.size())) {
	   				throw new NumberFormatException();
	   			}
	   			valid = true;
	   		} catch(NumberFormatException nfe) {
	   			System.out.println("Your selection was invalid, please try again");
	   		}
   		}
   		
   		// return the primary key of the MLS company
   		return categories.get(choice);
	}
	
	private static void updateCategoryName(int categoryId) throws SQLException {
		String name = null;
		while (name == null || name.trim().isEmpty()) {
			System.out.print("Please enter a category name: ");
			name = scanner.nextLine();
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	"update CATEGORY \n" +
   						"SET CategoryName = '" + name + "' \n" +
   						"WHERE CategoryId = " + categoryId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
   		return;
	}
	
	void insertType() throws SQLException {
		//TypeId, PropertyType
		
		boolean done = false;
		String choice = null;
		String type = null;

		while (!done) {
			System.out.print("Please enter the type: ");
			type = scanner.nextLine();
			
			printLine();
			System.out.println("Type:" + type);
			System.out.print("Is this the correct information(y or n): ");
			choice = scanner.nextLine();
			if (Character.toLowerCase(choice.trim().charAt(0)) == 'y') {
				done = true;
			}
		}
			
			Statement stmt = conn.createStatement();
	     	String qry = "insert into TYPE(PropertyType) "
	     				 + "values ('" + type + "')";
	     	
	     	stmt.execute(qry);
	}
	
	void updateType() throws SQLException {
		//TypeId, PropertyType
		ArrayList<String> typeInfo = getTypes();
		
		int i = 0;
		System.out.print("\n\t" + "1) Type: " + typeInfo.get(i++));
		
		int typeId = 0;
		
		try {
			typeId = Integer.parseInt(typeInfo.get(i));
		} catch(NumberFormatException nfe) {
			System.out.println("error while updating type. program will now end");
			System.exit(-1);
		}
		
		boolean correct = false, done = false;
		int updateOption = 0;
		while (!done) {
			while (!correct) {
				System.out.println("\n\nPlease insert the field you want to update");
				try {
					updateOption = Integer.parseInt(scanner.nextLine());
					if (updateOption < 1 || updateOption > 1) throw new NumberFormatException();
					correct = true;
				} catch (NumberFormatException nfe) {
					System.out.println("\n\nYou have entered an invalid option. Please choose the digit corresponding to your desired option\n");
				}
			}
		
			switch (updateOption) {
				case 1: updatePropertyType(typeId);
						break;
			}
		
			String choiceString = null;
			
			while(choiceString == null || choiceString.trim().isEmpty()) {
				System.out.println("Would you like to continue updating the same type? (y/n)");
				choiceString = scanner.nextLine();
				if (!choiceString.trim().isEmpty()) { 
					if (Character.toLowerCase(choiceString.trim().charAt(0)) == 'n') {
						done = true;
					} else if (Character.toLowerCase(choiceString.trim().charAt(0)) == 'y') {
						correct = false;
						done = false;
					} else {
						choiceString = null;
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	private static ArrayList<String> getTypes() throws SQLException {
		
		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = "Select * from TYPE";
   		
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);
	   	   
   		// store the keys into the database
   		HashMap<Integer, ArrayList<String>> types = new HashMap<Integer,ArrayList<String>>();
   		
   		// display the companies to the user
   		System.out.print("TYPES\n-------------------");
   		int i = 0;
   		while (rs.next()) {
   			ArrayList<String> temp = new ArrayList<String>();
   			String tempString = null;
   			
   			System.out.print("\n" + (i++ + 1) + ")");
   			
   			tempString = rs.getString("PropertyType");
   			System.out.print("\n\t" + "Type: " + tempString);
   			temp.add(tempString);
   			
   			temp.add(Integer.toString(rs.getInt("TypeId")));
   			
   			types.put(rs.getInt("TypeId"), temp);
   		}
	   
   		// loop until the user selects a valid option
   		int choice = 0;
   		boolean valid = false;
   		while (!valid) {
	   		try {
	   	   		System.out.print("\n\nPlease choose a category to update: ");
	   			choice = Integer.parseInt(scanner.nextLine());
	   			
	   			if(choice <= 0 || choice > (types.size())) {
	   				throw new NumberFormatException();
	   			}
	   			valid = true;
	   		} catch(NumberFormatException nfe) {
	   			System.out.println("Your selection was invalid, please try again");
	   		}
   		}
   		
   		// return the primary key of the MLS company
   		return types.get(choice);
	}
	
	private static void updatePropertyType(int typeId) throws SQLException {
		String name = null;
		while (name == null || name.trim().isEmpty()) {
			System.out.print("Please enter a type name: ");
			name = scanner.nextLine();
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	"update TYPE \n" +
   						"SET PropertyType = '" + name + "' \n" +
   						"WHERE TypeId = " + typeId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
   		return;
	}
	
	void insertStatus() throws SQLException {
		//StatusId, ListingStatus
		
		boolean done = false;
		String choice = null;
		String status = null;

		while (!done) {
			System.out.print("Please enter the status: ");
			status = scanner.nextLine();
			
			printLine();
			boolean correct = false;
			while (!correct) {
				System.out.println("Status:\n" + status);
				System.out.print("\nIs this the correct information(y or n): ");
				choice = scanner.nextLine();
				if (Character.toLowerCase(choice.trim().charAt(0)) == 'y') {
					done = true;
					correct = true;
				} else if (Character.toLowerCase(choice.trim().charAt(0)) == 'n') {
					done = false;
					correct = false;
				} else {
					System.out.println("please enter yes or no to indicate that you are satisfied with your input.");
					correct = false;
				}
			}
		}
			
			Statement stmt = conn.createStatement();
	     	String qry = "insert into STATUS(ListingStatus) "
	     				 + "values ('" + status + "')";
	     	
	     	boolean inserted = stmt.execute(qry);
	}
	
	private static void updateStatus() throws SQLException {
		//StatusId, ListingStatus
		ArrayList<String> statusInfo = getStatuses();
		
		int i = 0;
		System.out.print("\n\t" + "1) Category: " + statusInfo.get(i++));
		
		int statusId = 0;
		
		try {
			statusId = Integer.parseInt(statusInfo.get(i));
		} catch(NumberFormatException nfe) {
			System.out.println("error while updating status. program will now end");
			System.exit(-1);
		}
		
		boolean correct = false, done = false;
		int updateOption = 0;
		while (!done) {
			while (!correct) {
				System.out.println("\n\nPlease insert the field you want to update");
				try {
					updateOption = Integer.parseInt(scanner.nextLine());
					if (updateOption < 1 || updateOption > 1) throw new NumberFormatException();
					correct = true;
				} catch (NumberFormatException nfe) {
					System.out.println("\n\nYou have entered an invalid option. Please choose the digit corresponding to your desired option\n");
				}
			}
		
			switch (updateOption) {
				case 1: updateStatusName(statusId);
						break;
			}
		
			String choiceString = null;
			
			while(choiceString == null || choiceString.trim().isEmpty()) {
				System.out.println("Would you like to continue updating the same category? (y/n)");
				choiceString = scanner.nextLine();
				if (!choiceString.trim().isEmpty()) { 
					if (Character.toLowerCase(choiceString.trim().charAt(0)) == 'n') {
						done = true;
					} else if (Character.toLowerCase(choiceString.trim().charAt(0)) == 'y') {
						correct = false;
						done = false;
					} else {
						choiceString = null;
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	private static ArrayList<String> getStatuses() throws SQLException {
		
		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = "Select * from STATUS";
   		
   		// execute the query
   		ResultSet rs = stmt.executeQuery(qry);
	   	   
   		// store the keys into the database
   		HashMap<Integer, ArrayList<String>> photos = new HashMap<Integer,ArrayList<String>>();
   		
   		// display the companies to the user
   		System.out.print("STATUSES\n-------------------");
   		int i = 0;
   		while (rs.next()) {
   			ArrayList<String> temp = new ArrayList<String>();
   			String tempString = null;
   			
   			System.out.print("\n" + (i++ + 1) + ")");
   			
   			tempString = rs.getString("ListingStatus");
   			System.out.print("\n\t" + "Status: " + tempString);
   			temp.add(tempString);
   			
   			temp.add(Integer.toString(rs.getInt("StatusId")));
   			
   			photos.put(rs.getInt("StatusId"), temp);
   		}
	   
   		// loop until the user selects a valid option
   		int choice = 0;
   		boolean valid = false;
   		while (!valid) {
	   		try {
	   	   		System.out.print("\n\nPlease choose a status to update: ");
	   			choice = Integer.parseInt(scanner.nextLine());
	   			
	   			if(choice <= 0 || choice > (photos.size())) {
	   				throw new NumberFormatException();
	   			}
	   			valid = true;
	   		} catch(NumberFormatException nfe) {
	   			System.out.println("Your selection was invalid, please try again");
	   		}
   		}
   		
   		// return the primary key of the MLS company
   		return photos.get(choice);
	}
	
	private static void updateStatusName(int statusId) throws SQLException {
		String name = null;
		while (name == null || name.trim().isEmpty()) {
			System.out.print("Please enter a status name: ");
			name = scanner.nextLine();
		}
		
   		Statement stmt = conn.createStatement();
   		
   		// generate the query string
   		String qry = 	"update STATUS \n" +
   						"SET ListingStatus = '" + name + "' \n" +
   						"WHERE StatusId = " + statusId;
   		
   		stmt.execute(qry);
   		
   		printUpdateVerification();
   		return;
	}
	
	private static void printUpdateVerification() {
		System.out.println("You have successfully updated this field.");
	}
}