import java.sql.*;
import java.util.ArrayList;

/**
 * AdminUser 
 * @author Allen Bui(x339y958), John Nichols ()
 * @version 1
 */
public class AdminUser extends User {

	private static String adminMode = "Administrator Mode";
	private static String insertRecord = "Inserting Record";
	
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
	                           + "\n\t3:  delete record"
	                           + "\n\t4:  regular user mode");
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
	  				
	  		case 3: deleteRecord();
	  				break;
	  				
	  		case 4: RegularUser reg = new RegularUser();
	  				reg.printMenuAndExecute();
	  				break;
	  					
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
				System.out.println("Your input needs to be an integer in range [1,7]");
			}
		}
		
		switch (choice) {
		
			case 1:  	showAllAddresses();
						updateAddress();
		 				break;
			
			case 2:  	showAllListings();
						updateListing();
						break;
		 
			case 3:  	showAllCompanies();
						updateMls();
						break;
  	 
			case 4:  	showAllPhotos();
						updatePhoto();
							break;
			
			case 5: 	showAllCategories();
						updateCategory();
							break;
			
			case 6: 	showAllStatuses();
						updateStatus();
							break;
			
			case 7:		showAllTypes();
						updateType();
							break;
	  					
	  		default: System.out.println("Error handling ensures that code will never reach here... if somehow this line gets printed our program has some major issues");
	             	break;
		
		}
	}
	
	void deleteRecord() throws SQLException {
		
		int choice = 0;
		boolean validChoice = false;
		
		while (!validChoice) {
			System.out.println("Choose from one of the following options:"
	                           + "\n\t1:  Delete record from ADDRESS"
	                           + "\n\t2:  Delete record from LISTING"
	                           + "\n\t3:  Delete record from MLS"
	                           + "\n\t4:  Delete record from PHOTO"
	                           + "\n\t5:  Delete record from CATEGORY"
	                           + "\n\t6:  Delete record from STATUS"
	                           + "\n\t7:  Delete record from TYPE");
			System.out.print("Your choice ==> ");
			
			try {
				choice = Integer.parseInt(scanner.nextLine());
				if (choice < 1 || choice > 7) throw new NumberFormatException();
				validChoice = true;
			} catch (NumberFormatException nfe) {
				clearConsole();
				System.out.println("Your input needs to be an integer in range [1,7]");
			}
		}
		
		switch (choice) {
		
			case 1:  	showAllAddresses();
						deleteAddress();
		 				break;
			
			case 2:  	showAllListings();
						deleteListing();
						break;
		 
			case 3:  	showAllCompanies();
						deleteMls();
						break;
  	 
			case 4:  	showAllPhotos();
						deletePhoto();
						break;
			
			case 5: 	showAllCategories();
						deleteCategory();
						break;
			
			case 6: 	showAllStatuses();
						deleteStatus();
						break;
			
			case 7:		showAllTypes();
						deleteType();
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
     	System.out.println("Timestamp\t\tURL");
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
			
			printLine();
			System.out.println("Address:\t" + address +
							   "City:\t" + city + 
							   "State:\t" + state +
							   "Country:\t" + country +
							   "Postal Code: \t" + postalCode);
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
     	
     	boolean finished = stmt.execute(qry);
     
	}
	
	/**
	 * 
	 */
	void updateAddress() {
		//FullStreetAddress, City, StateOrProvince, PostalCode, Country
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
							   "\nMLS Id:\t" + mls + 
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
     	
     	if (success) {
     		System.out.println("successfully inserted a listing");
     	} else {
     		System.out.println("inserting a listing was unsuccessful");
     	}
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
	   			System.out.print("\nCity: " + (i + 1) + ") " + rs.getString("City"));
	   			System.out.print("\nState/Province: " + (i + 1) + ") " + rs.getString("StateOrProvince"));
	   			System.out.print("\nPostalCode: " + (i + 1) + ") " + rs.getInt("PostalCode"));
	   			System.out.print("\nCountry: " + (i + 1) + ") " + rs.getString("Country"));
	   			System.out.print("\nFull Street Address" + (i++ + 1) + ") " + rs.getString("FullStreetAddress"));
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
	 * 
	 */
	void updateListing() {
		// ListingId, ListPrice, ListURL, ListingDescription, ListingDate, Bedrooms, Bathrooms, �TypeId�, �StatusId�, �CategoryId�, �MlsId�, �AddressId�

	}
	
	/**
	 * 
	 */
	void insertMls() throws SQLException{
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
			System.out.println("Name:" + name +
							   "\tAddress: " + address + 
							   "\tState: " + email +
							   "\tCountry: " + phone +
							   "\tPostal Code: " + fax);
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
	     	
	     	if (inserted == true) {
	     		System.out.println("Successfully added.\n\n");
	     	}
	    

	}
	
	void updateMls() throws SQLException {
		//MlsId, MlsName, MlsAgent, MlsAddress, MlsEmail, MlsPhoneNumber, MlsFax

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
			boolean valid = false;
			while (!valid) {
				try {
					listingId = Integer.parseInt(scanner.nextLine());
					valid = true;
				} catch (NumberFormatException nfe) {
					System.out.println("You've entered an incorrect postal code format. Please enter the digits corresponding to the postal code");
				}
			}
			
			printLine();
			System.out.println("URL:" + URL +
							   "\tListing Id: " + listingId);
			System.out.print("Is this the correct information(y or n): ");
			choice = scanner.nextLine();
			if (Character.toLowerCase(choice.trim().charAt(0)) == 'y') {
				done = true;
			}
		}
			
			Statement stmt = conn.createStatement();
	     	String qry = "insert into PHOTO(MediaModificationTimestamp, MediaUrl, ListingId) "
	     				 + "values ('" + timestamp + "', '" + URL + "', " + listingId +")";
	     	
	     	boolean inserted = stmt.execute(qry);
	}
	
	void updatePhoto() throws SQLException {
		//MediaModificationTimestamp, MediaUrl, ListingId

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
			System.out.println("Category:" + category);
			System.out.print("Is this the correct information(y or n): ");
			choice = scanner.nextLine();
			if (Character.toLowerCase(choice.trim().charAt(0)) == 'y') {
				done = true;
			}
		}
			
			Statement stmt = conn.createStatement();
	     	String qry = "insert into CATEGORY(CategoryName) "
	     				 + "values ('" + category + "')";
	     	
	     	boolean inserted = stmt.execute(qry);
	}
	
	void updateCategory() throws SQLException {
		//CategoryId, CategoryName

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
	     	
	     	boolean inserted = stmt.execute(qry);
	}
	
	void updateType() throws SQLException {
		//TypeId, PropertyType

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
			System.out.println("Status:" + status);
			System.out.print("Is this the correct information(y or n): ");
			choice = scanner.nextLine();
			if (Character.toLowerCase(choice.trim().charAt(0)) == 'y') {
				done = true;
			}
		}
			
			Statement stmt = conn.createStatement();
	     	String qry = "insert into STATUS(ListingStatus) "
	     				 + "values ('" + status + "')";
	     	
	     	boolean inserted = stmt.execute(qry);
	}
	
	void updateStatus() throws SQLException {
		//StatusId, ListingStatus

	}
}