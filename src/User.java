import java.sql.*;
import java.util.Scanner;
//import org.apache.derby.jdbc.ClientDriver;
import org.apache.derby.jdbc.EmbeddedDriver;

/**
 * CS665 Database Design & Implementation Project
 * Real Estate Listing Manager 
 * 
 * @author Allen Bui (x339y958), John Nichols ()
 * @version 1
 */
public class User {

	// strings used to clear the console
	private static final String ANSI_CLS = "\u001b[2J";
	private static final String ANSI_HOME = "\u001b[H";
	
	// resources that are used by derived classes so they are made protected
	protected static Scanner scanner;
	protected static Connection conn;
	
	/**
	 * Main is the beginning of the MLS program and allows the user
	 * to decide whether they want to enter regular mode or admin mode
	 * 
	 * @param args N/A 
	 */
	public static void main(String[] args) {
		
		// start the resource
		scanner = new Scanner(System.in);
		
		// regularUser/administrator/quit
		int choice = 0;
		
		boolean userTypeSelected, quitProgram;
		quitProgram = userTypeSelected = false;
		
		// clear the screen and start user interaction
		clearConsole();
		
		// loop until the user has decided that he/she is finished accessing the DB
		while(!quitProgram) {
			
			// make the user select the mode they want to enter or quit
			while (!userTypeSelected) {
				System.out.println("Choose how you wish to log in:"
	   			  	  + "\n\t1: Regular User"
	   			  	  + "\n\t2: Administrative User"
	   			  	  + "\n\t3: Quit");
				System.out.print("Please choose: ");
				
				try {
					choice = Integer.parseInt(scanner.nextLine());
					if (choice < 1 || choice > 3) throw new NumberFormatException();
					userTypeSelected = true;
				} catch (NumberFormatException nfe) {
					clearConsole();
					System.out.println("\n\nYou have entered an invalid option. Please choose the digit corresponding to your desired option\n");
				}
			}
			
			// clear the console and as the user to wait because it takes a while to establish the connection
			clearConsole();
			System.out.println("Please wait...");
			
			// instantiate and run the proper inner class
			switch (choice) {
			
				case 1: RegularUser regularUser = new RegularUser();
						regularUser.run();
						break;
						
				case 2:	AdminUser adminUser = new AdminUser();
						adminUser.run();
						break;
				case 3: quitProgram = true;
						continue;
				
			}
			
			// does the user want to quit or continue?
			if (userTypeSelected && ! quitProgram) {
				
				String choiceString = null;
				
				while(choiceString == null || choiceString.trim().isEmpty()) {
					System.out.print("Would you like to continue or quit? (c/q) ");
					choiceString = scanner.nextLine();
					if (!choiceString.trim().isEmpty()) { 
						if (Character.toLowerCase(choiceString.trim().charAt(0)) == 'q') {
							quitProgram = true;
						} else if (Character.toLowerCase(choiceString.trim().charAt(0)) != 'c') {
							System.out.println("Please make a selection");
							choiceString = null;
						}
					}
				}
			}
		}
		
		// close the resources and wave goodbye
		scanner.close();
		clearConsole();
		System.out.println("\n\nGoodbye\n\n");
	}

	
	/**
	 * run is executed by the inner classes and is used to establish the connections
	 * to access the DB
	 */
	public void run() {
		
		// initialize the connection
		conn = null;
		
		try {
			/*
			 *  Step 1:  connect to database server
			 *  
			 *  NOTE: if on CS servers, use Clientdriver()
			 *  		else use the EmbeddedDriver()
			 */
			//Driver d = new ClientDriver();
			
			Driver d = new EmbeddedDriver();
			
			// location of the DB
			String url = "jdbc:derby:/home/allen/Dropbox/EclipseWorkspace/workspace/RealEstateListingManager/RealEstateDb";
			
			// establish a connection to the DB
			conn = d.connect(url, null);
			
			// if d.connect returns null then there was an error, throw an exception
			if (conn == null) {
				System.out.println("\nError establishing connection to DB");
				throw new SQLException();
			}
			
			// Make a menu selection
			printMenuAndExecute();
			
		} catch(SQLException e) {		// error handling
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
	
	/**
	 * printMenuAndExecute is a method that will be overridden by the derived classes
	 * @throws SQLException
	 */
	protected void printMenuAndExecute() throws SQLException{}
	
	/**
	 * printLine is a helper method that will be utilized by the derived 
	 * classes in order to display the tables in a user friendly manner
	 */
	protected final static void printLine() {
		System.out.println("----------------------"
							+ "-------------------"
							+ "-------------------"
							+ "-------------------"
							+ "-------------------"
							+ "-------------------"
			                + "-------------------"
                            + "-------------------"
							+ "-------------------");
	}
	
	/**
	 * clearConsole is a method that clears the command line 
	 * (IN A LINUX COMMAND LINE). used for a better user interface
	 */
	protected final static void clearConsole() {
	    System.out.print(ANSI_CLS + ANSI_HOME);  
	    System.out.flush();  
	}
	
   	/**
   	 * printHeader is a helper method that takes the mode as input string and outputs to console in a 
   	 * friendly manner
   	 * @param string which is the string of the header you would like to print
   	 */
   	protected static final void printHeader(String string) {
   		System.out.println("-----------------------------------------" 
   							+ string
   							+"-----------------------------------------");	
   	}
}
