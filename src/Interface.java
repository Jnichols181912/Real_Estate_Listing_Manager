import java.util.Scanner;

public class Interface {

	public static void main(String args[]) {
		Address address = new Address();
		MLS mls = new MLS();
		Category category = new Category();
		Type type = new Type();
		Status status = new Status();
		int choice = 0;
		String incorrect = "Please only enter a valid option.";
		Scanner scanner = new Scanner(System.in);
		
		while (choice != 4) {
		
			System.out.println("Welcome to the Real Estate Listing Manager(RELM)!\n"
						   	   + "This interface allows you to add or search listings.\n\n"
						   	   + "\t1: Search for a listing\n"
						   	   + "\t2: Add a new listing\n"
						   	   + "\t3: Update an existing listing\n"
						   	   + "\t4: Exit the interface\n"
						   	   + "Choose: ");

			choice = new Integer(scanner.next());
		
			if (choice <= 0) {
				System.out.println(incorrect);
			}else if (choice == 1) {
				search(address, mls, category, type, status);
			} else if (choice == 2) {
				add();
			} else if (choice == 3) {
				update();
			} else if (choice > 4) {
				System.out.println(incorrect);
			}
		}
	}
	
	public static void search(Address address, MLS mls, Category category, Type type, Status status) {
		
		System.out.println("This interface allows you to view existing listings within the database.\n\n"
				   		   + "Choose one of the following options:\n"
				   		   + "\t1: Display available locationsn"
				   		   + "\t2: Display available companies\n"
				   		   + "\t3: Display available types\n"
				   		   + "\t4: Display available categories\n"
				   		   + "\t5: Display available statuses\n"
				   		   + "\t6: Search by listing\n"
				   		   + "\t7: View with current settings\n"
				   		   + "\t8: Reset search\n"
				   		   + "Choose: ");

		Scanner scanner = new Scanner(System.in);
		int choice = new Integer(scanner.next());

		if (choice == 1) {
			//get a table with only the available countries
			System.out.println("Only locations with available listings will be displayed.\n"
							   + "Choose one of the following options:\n");
			String countryChoice = scanner.next();
			//get a table with only the available states if United States else get available cities
			
		}
	}
	
	public static void add() {
		
	}
	
	public static void update() {
		
	}
}
