//  File:  Menu.java

//  This program llustrates the use of a menu, which would be the basis
//  for constructing a larger program by adding more options, where each
//  option is handled by a separate function.

//  This file is in the directory ~kdjackso/cs665
import java.util.Scanner;

public class Menu
{
   public static void main(String[] args) {
	   
	   int choice;
	   boolean stopAsking = false;
	   Scanner scanner = new Scanner(System.in);
	   
	   while (stopAsking == false) {
		   System.out.println("Choose how you wish to log in:"
			   			  	  + "\n\t1: Regular User"
			   			  	  + "\n\t2: Administrative User"
			   			  	  + "\n\t3: Quit");
		   System.out.print("Please choose: ");
		   choice = scanner.nextInt();
		   scanner.close();
		   
		   if (choice == 1) {
			   RegularUser user = new RegularUser();
			   user.run();
			   stopAsking = true;
		   } else if (choice == 2) {
			   AdminUser user = new AdminUser();
			   user.run();
			   stopAsking = true;
		   } else if (choice == 3) {
			   System.out.println("\nGoodbye.");
			   stopAsking = true;
		   } else {
			   System.out.println("\nPlease only enter a valid choice.\n");
		   }
	   }
   }
}
