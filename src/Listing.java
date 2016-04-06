
public class Listing {
	
	 private String id;
	 private int price;
	 private String URL;
	 private String description;
	 private String dateAdded;
	 private int bedrooms;
	 private int bathrooms;
	 
	 public Listing(String id, int price, String URL, String description, String dateAdded, int bedrooms, int bathrooms){
		 this.id = new String(id);
		 this.price = new Integer(price);
		 this.URL = new String(URL);
		 this.description = new String(description);
		 this.dateAdded = new String(dateAdded);
		 this.bedrooms = new Integer(bedrooms);
		 this.bathrooms = new Integer(bathrooms);
	 }
	 
	 public Listing(Listing listing) {
		 this.id = listing.id;
		 this.price = listing.price;
		 this.URL = listing.URL;
		 this.description = listing.description;
		 this.dateAdded = listing.dateAdded;
		 this.bedrooms = listing.bedrooms;
		 this.bathrooms = listing.bathrooms;
	 }
	 
	 public String getId() {
		 String id = this.id;
		 return id;
	 }
	 
	 public int getPrice() {
		 int price = this.price;
		 return price;
	 }
	 
	 public String getURL() {
		 String URL = this.URL;
		 return URL;
	 }
	 
	 public String getDescription() {
		 String description = this.description;
		 return description;
	 }
	 
	 public String getDateAdded() {
		 String dateAdded = this.dateAdded;
		 return dateAdded;
	 }
	 
	 public int getBedrooms() {
		 int bedrooms = this.bedrooms;
		 return bedrooms;
	 }
	 
	 public int bathrooms() {
		 int bathrooms = this.bathrooms;
		 return bathrooms;
	 }
	 
	 public String toString() {
		 
		 String output;
		 
		 output = "Id: " + id + "\nPrice: " + price + "\nURL: " + URL + "\nDescription: " + description + 
				  "\nDate Added: " + dateAdded + "\nBedrooms: " + bedrooms + "\nBathrooms: " + bathrooms +"\n";
		 return output;
	 }
	  

}
