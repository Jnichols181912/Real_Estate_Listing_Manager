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
	
	private void insertRecord() {
		
	}
	
	private void updateRecord() {
		
	}
	
	private void deleteRecord() {
		
	}

   	private static final void printAdminMode() {
   		System.out.println("-----------------------------------------" 
   							+ "Administrator mode"
   							+"-----------------------------------------");	
   	}
}