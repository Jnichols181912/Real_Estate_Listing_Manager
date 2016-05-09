/**
 * 
 */

/**
 * @author allen
 *
 */

import java.sql.*;
import java.util.Scanner;

//import org.apache.derby.jdbc.ClientDriver;
import org.apache.derby.jdbc.EmbeddedDriver;
/**
 * @author allen
 * 
 */
public class User {

	private static final String ANSI_CLS = "\u001b[2J";
	private static final String ANSI_HOME = "\u001b[H";
	public static Scanner scanner;
	protected Connection conn;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		scanner = new Scanner(System.in);
		int choice = 0;
		
		boolean userTypeSelected, quitProgram;
		quitProgram = userTypeSelected = false;
		
		clearConsole();
		
		while(!quitProgram) {
			
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
			
			clearConsole();
			System.out.println("Please wait...");
			
			switch (choice) {
			
				case 1: RegularUser regularUser = new RegularUser();
						regularUser.run();
						break;
						
				case 2:	AdminUser adminUser = new AdminUser();
						adminUser.run();
						break;
						
				default:System.out.println("Should never reach this case"); 
						break;
			}
			
			if (userTypeSelected && ! quitProgram) {
				
				System.out.print("Would you like to continue or quit? (c/q) ");
				String choiceString = null;
				
				while(choiceString == null || choiceString.trim().isEmpty()) {
					choiceString = scanner.nextLine();
				}
				
				if (Character.toLowerCase(choiceString.trim().charAt(0)) == 'q') {
					quitProgram = true;
				}
			}
		}
		
		scanner.close();
		clearConsole();
		System.out.println("\n\nGoodbye\n\n");
	}

	
	public void run() {
		
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
			
			// Make a menu selection
			printMenuAndExecute();
			
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
	
	protected void printMenuAndExecute() throws SQLException{}
	
	protected final static void printLine() {
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
	}
	
	protected final static void clearConsole() {
	    System.out.print(ANSI_CLS + ANSI_HOME);  
	    System.out.flush();  
	}
}
