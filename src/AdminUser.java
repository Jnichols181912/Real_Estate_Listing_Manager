import java.sql.*;
//import org.apache.derby.jdbc.ClientDriver;
import org.apache.derby.jdbc.EmbeddedDriver;
import java.util.Scanner;
/**
 * 
 */

/**
 * @author allen
 *
 */

public class AdminUser extends User{

	public AdminUser() {
		super();
	}

	@Override
	protected void printMenuAndExecute() throws SQLException {

		int choice = 0;
		boolean validChoice = false;
		
		clearConsole();
		printAdminMode();
		
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
				printAdminMode();
				System.out.println("\nYour input needs to be an integer in range [1,4]\n");
			}
		}
		
		clearConsole();
		printAdminMode();
		
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
	
	void insertRecord() {
		
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
	
	void updateRecord() {
		
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
	
	void deleteRecord() {
		
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
	
	void showAllAddresses() {
		Statement stmt = conn.createStatement();
     	String qry = "select * from ADDRESS";
     	ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
     	// AddressId, FullStreetAddress, City, StateOrProvince, PostalCode, Country
     	System.out.println("Address Id\t\tAddress\t\tCity\t\t\tState or Province\t\t\tCountry\t\t\tPostal code");
     	printLine();
     	
		while (rs.next()) {
			int addressId = rs.getInt("AddressId");
			String address = rs.getString("FullStreetAddress");
			String city = rs.getString("City");
			String state = rs.getString("StateOrProvince");
			String country = rs.getString("Country");
			String postalCode = rs.getString("PostalCode");
	  	System.out.printf("%-20s\t%-15d\t%-20s\t%-20s\t%-20s\t%-100s\n", addressId, address, city, state, country, postalCode);
		}
		System.out.println( );
		rs.close();
	}
	
	void showAllListings() {
		Statement stmt = conn.createStatement();
     	String qry = "select * from LISTING";
     	ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
     	//ListingId, ListPrice, ListURL, ListingDate, Bedrooms, Bathrooms
     	System.out.println("Listing Id\t\tPrice\t\tURL\t\t\tDate Listed\t\t\tBedrooms\t\t\tBathrooms");
     	printLine();
     	
		while (rs.next()) {
			int listingId = rs.getInt("ListingId");
			int price = rs.getInt("ListPrice");
			String url = rs.getString("ListingURL");
			String date = rs.getString("ListingDate");
			int bedrooms = rs.getInt("Bedrooms");
			int bathrooms = rs.getInt("Bathrooms");
	  	System.out.printf("%-20s\t%-15d\t%-20s\t%-20s\t%-20s\t%-100s\n", listingId, price, url, bedrooms, bathrooms);
		}
		System.out.println( );
		rs.close();
	}
	
	void showAllCompanies() {
		Statement stmt = conn.createStatement();
     	String qry = "select * from MLS";
     	ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
     	// MlsId, MlsName, MlsAgent, MlsAddress, MlsEmail, MlsPhoneNumber, MlsFax
     	System.out.println("MLS Id\t\tName\t\tAgent\t\t\tAddress\t\t\tEmail\t\t\tPhone Number\t\tFax");
     	printLine();
     	
		while (rs.next()) {
			int mlsId = rs.getInt("MlsId");
			String name = rs.getString("MlsName");
			String address = rs.getString("MlsAddress");
			String email = rs.getString("ListingDate");
			String phoneNumber = rs.getString("Bedrooms");
			String fax = rs.getString("Bathrooms");
	  	System.out.printf("%-20s\t%-15d\t%-20s\t%-20s\t%-20s\t%-100s\n", mlsId, name, address, email, phoneNumber, fax);
		}
		System.out.println( );
		rs.close();
	}
	
	void showAllPhotos() {
		Statement stmt = conn.createStatement();
     	String qry = "select * from PHOTO";
     	ResultSet rs = stmt.executeQuery(qry);

		// Loop through the result set
     	// MediaModificationTimestamp, MediaUrl, �ListingId�
     	System.out.println("Timestamp\t\tURL\t\tListing Id");
     	printLine();
     	
		while (rs.next()) {
			String timestamp = rs.getString("MediaModificationTimestamp");
			String url = rs.getString("MediaUrl");
			int listingId = rs.getInt("ListingId");
	  	System.out.printf("%-20s\t%-15d\t%-20s", timestamp, url, listingId);
		}
		System.out.println( );
		rs.close();
	}
	
	void showAllCategories() {
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
			System.out.printf("%-20s\t%-15d", categoryId, name);
		}
		System.out.println( );
		rs.close();
	}
	
	void showAllTypes() {
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
			System.out.printf("%-20s\t%-15d", typeId, type);;
		}
		System.out.println( );
		rs.close();
	}
	
	void showAllStatuses() {
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
			System.out.printf("%-20s\t%-15d", statusId, status);;
		}
		System.out.println( );
		rs.close();
	}
	
	void insertAddress() {
		//FullStreetAddress, City, StateOrProvince, PostalCode, Country
		
		boolean stop = false;
		String choice;
		String address;
		String city;
		String state;
		String country;
		int postalCode;
		
		while (!stop) {
			System.out.print("Please enter the address: ");
			address = scanner.nextLine();
			System.out.print("Please enter the city: ");
			city = scanner.nextLine();
			System.out.print("Please enter the state: ");
			state = scanner.nextLine();
			System.out.print("Please enter the country: ");
			country = scanner.nextLine();
			System.out.print("Please enter the postal code: ");
			postalCode = Integer.parseInt(scanner.nextLine());
			printLine();
			System.out.println("Address:\t" + address +
							   "City:\t" + city + 
							   "State:\t" + state +
							   "Country:\t" + country +
							   "Postal Code: \t" + postalCode);
			System.out.print("Is this the correct information(y or n): ");
			choice = scanner.nextLine();
			choice = choice.toUpperCase();
			if(choice == "Y" || choice == "YES") {
				stop = true;
			}
		}
		
		conn = null;
		
		try {
			// Step 1:  connect to database server
			//Driver d = new ClientDriver();
			
			Driver d = new EmbeddedDriver();
			String url = "jdbc:derby:/home/allen/Dropbox/EclipseWorkspace/workspace/RealEstateListingManager/RealEstateDb";
			conn = d.connect(url, null);
			if (conn == null) {
				System.out.println("\nError establishing connection to DB");
				throw new SQLException();
			}
			
			Statement stmt = conn.createStatement();
	     	String qry = "insert into ADDRESS(FullStreetAddress, City, StateOrProvince, PostalCode, Country) "
	     				 + "values (`" + address + "`, `" + city + "`, `" + state + "`, `" + country + "`, "
	     				 + postalCode +")";
	     	ResultSet rs = stmt.executeQuery(qry);
	     	rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		} finally {
			// Step 4: Disconnect from the server
			try {
				if(conn != null)
					conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
	
	void updateAddress() {
		//FullStreetAddress, City, StateOrProvince, PostalCode, Country
	}
	
	void deleteAddress() {
		//FullStreetAddress, City, StateOrProvince, PostalCode, Country
	}
	
	void insertListing() {
		// ListingId, ListPrice, ListURL, ListingDescription, ListingDate, Bedrooms, Bathrooms, �TypeId�, �StatusId�, �CategoryId�, �MlsId�, �AddressId�
		boolean stop = false;
		String choice;
		int price;
		String URL;
		String description;
		int bedrooms;
		int bathrooms;
		int type;
		int status;
		int category;
		int mls;
		int address;
		
		while (!stop) {
			System.out.print("Please enter the price: ");
			price = Integer.parseInt(scanner.nextLine());
			System.out.print("Please enter the URL: ");
			URL = scanner.nextLine();
			System.out.print("Please enter the desctiption: ");
			description = scanner.nextLine();
			System.out.print("Please enter the bedrooms: ");
			bedrooms = Integer.parseInt(scanner.nextLine());
			System.out.print("Please enter the bathrooms: ");
			bathrooms = Integer.parseInt(scanner.nextLine());
			System.out.print("Please enter the type id: ");
			type = Integer.parseInt(scanner.nextLine());
			System.out.print("Please enter the status id: ");
			status = Integer.parseInt(scanner.nextLine());
			System.out.print("Please enter the category id: ");
			category = Integer.parseInt(scanner.nextLine());
			System.out.print("Please enter the mls id: ");
			mls = Integer.parseInt(scanner.nextLine());
			System.out.print("Please enter the address id: ");
			address = Integer.parseInt(scanner.nextLine());
			printLine();
			System.out.println("Price:\t" + price +
							   "URL:\t" + URL + 
							   "Description:\t" + description +
							   "Bedrooms:\t" + bedrooms +
							   "Bathrooms: \t" + bathrooms + 
							   "Type Id:\t" + type + 
							   "Status Id:\t" + status + 
							   "Category Id:\t" + category +
							   "MLS Id:\t" + mls + 
							   "Address Id:\t" + address);
			System.out.print("Is this the correct information(y or n): ");
			choice = scanner.nextLine();
			choice = choice.toUpperCase();
			if(choice == "Y" || choice == "YES") {
				stop = true;
			}
		}
		
		Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		
		conn = null;
		
		try {
			// Step 1:  connect to database server
			//Driver d = new ClientDriver();
			
			Driver d = new EmbeddedDriver();
			String url = "jdbc:derby:/home/allen/Dropbox/EclipseWorkspace/workspace/RealEstateListingManager/RealEstateDb";
			conn = d.connect(url, null);
			if (conn == null) {
				System.out.println("\nError establishing connection to DB");
				throw new SQLException();
			}
			
			Statement stmt = conn.createStatement();
	     	String qry = "insert into ADDRESS(ListPrice, ListURL, ListingDescription, ListingDate, Bedrooms, Bathrooms, TypeId, StatusId, CategoryId, MlsId, AddressId) "
	     				 + "values (" + price + ", `" + URL + "`, `" + description + "`, `" + date + "`, "+ bedrooms + ", " + bathrooms + ", " + type + ", " + status + ", "
	     				 + category + ", " + mls + ", " + ", " + address;
	     	ResultSet rs = stmt.executeQuery(qry);
	     	rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		} finally {
			// Step 4: Disconnect from the server
			try {
				if(conn != null)
					conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
	
	void updateListing() {
		// ListingId, ListPrice, ListURL, ListingDescription, ListingDate, Bedrooms, Bathrooms, �TypeId�, �StatusId�, �CategoryId�, �MlsId�, �AddressId�

	}
	
	void deleteListing() {
		// ListingId, ListPrice, ListURL, ListingDescription, ListingDate, Bedrooms, Bathrooms, �TypeId�, �StatusId�, �CategoryId�, �MlsId�, �AddressId�

	}
	
	void insertMls() {
		//MlsId, MlsName, MlsAgent, MlsAddress, MlsEmail, MlsPhoneNumber, MlsFax
		
		boolean stop = false;
		String name;
		String agent;
		String address;
		String email;
		String phoneNumber;
		String fax;
		
		while (!stop) {
			System.out.print("Please enter the address: ");
			address = scanner.nextLine();
			System.out.print("Please enter the city: ");
			city = scanner.nextLine();
			System.out.print("Please enter the state: ");
			state = scanner.nextLine();
			System.out.print("Please enter the country: ");
			country = scanner.nextLine();
			System.out.print("Please enter the postal code: ");
			postalCode = Integer.parseInt(scanner.nextLine());
			printLine();
			System.out.println("Address:\t" + address +
							   "City:\t" + city + 
							   "State:\t" + state +
							   "Country:\t" + country +
							   "Postal Code: \t" + postalCode);
			System.out.print("Is this the correct information(y or n): ");
			choice = scanner.nextLine();
			choice = choice.toUpperCase();
			if(choice == "Y" || choice == "YES") {
				stop = true;
			}
		}
		
		conn = null;
		
		try {
			// Step 1:  connect to database server
			//Driver d = new ClientDriver();
			
			Driver d = new EmbeddedDriver();
			String url = "jdbc:derby:/home/allen/Dropbox/EclipseWorkspace/workspace/RealEstateListingManager/RealEstateDb";
			conn = d.connect(url, null);
			if (conn == null) {
				System.out.println("\nError establishing connection to DB");
				throw new SQLException();
			}
			
			Statement stmt = conn.createStatement();
	     	String qry = "insert into ADDRESS(FullStreetAddress, City, StateOrProvince, PostalCode, Country) "
	     				 + "values (`" + address + "`, `" + city + "`, `" + state + "`, `" + country + "`, "
	     				 + postalCode +")";
	     	ResultSet rs = stmt.executeQuery(qry);
	     	rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		} finally {
			// Step 4: Disconnect from the server
			try {
				if(conn != null)
					conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
	
	void updateMls() {
		//MlsId, MlsName, MlsAgent, MlsAddress, MlsEmail, MlsPhoneNumber, MlsFax

	}
	
	void deleteMls() {
		//MlsId, MlsName, MlsAgent, MlsAddress, MlsEmail, MlsPhoneNumber, MlsFax

	}
	
	void insertPhoto() {
		//MediaModificationTimestamp, MediaUrl, �ListingId�
	}
	
	void updatePhoto() {
		//MediaModificationTimestamp, MediaUrl, �ListingId�

	}
	
	void deletePhoto() {
		//MediaModificationTimestamp, MediaUrl, �ListingId�

	}
	void insertCategory() {
		//CategoryId, CategoryName
	}
	
	void updateCategory() {
		//CategoryId, CategoryName

	}
	
	void deleteCategory() {
		//CategoryId, CategoryName

	}
	
	void insertType() {
		//TypeId, PropertyType
	}
	
	void updateType() {
		//TypeId, PropertyType

	}
	
	void deleteType() {
		//TypeId, PropertyType

	}
	
	void insertStatus() {
		//StatusId, ListingStatus
	}
	
	void updateStatus() {
		//StatusId, ListingStatus

	}
	
	void deleteStatus() {
		//StatusId, ListingStatus

	}

   	private static final void printAdminMode() {
   		System.out.println("-----------------------------------------" 
   							+ "Administrator mode"
   							+"-----------------------------------------");	
   	}
}