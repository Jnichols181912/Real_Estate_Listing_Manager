import java.util.Scanner;

/**
 * 
 */

/**
 * @author allen
 *
 */
public class Menu {

	private static final String ANSI_CLS = "\u001b[2J";
	private static final String ANSI_HOME = "\u001b[H";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   
		int choice = 0;
		boolean done, stopAsking;
		done = stopAsking = false;
		Scanner scanner = new Scanner(System.in);
		
		clearConsole();
		
		while(!stopAsking) {
			
			while (!done) {
				System.out.println("Choose how you wish to log in:"
	   			  	  + "\n\t1: Regular User"
	   			  	  + "\n\t2: Administrative User"
	   			  	  + "\n\t3: Quit");
				System.out.print("Please choose: ");
				
				try {
					choice = Integer.parseInt(scanner.nextLine());
					if (choice < 1 || choice > 3) throw new NumberFormatException();
					done = true;
				} catch (NumberFormatException nfe) {
					clearConsole();
					System.out.println("\n\nYou have entered an invalid option. Please choose the digit corresponding to your desired option\n");
				}	
			}
			
			clearConsole();
			
			switch (choice) {
				case 1: RegularUser regularUser = new RegularUser();
						regularUser.run();
						break;
						
				case 2:	AdminUser adminUser = new AdminUser();
						adminUser.run();
						break;
						
				case 3:	System.out.println("\nGoodbye.");
						stopAsking = true;
						break;
						
				default:System.out.println("Should never reach this case"); 
						break;
			}
		}
		scanner.close();
	}
	
	public final static void clearConsole() {
	    System.out.print(ANSI_CLS + ANSI_HOME);  
	    System.out.flush();  
	}
}