
public class Address {
<<<<<<< HEAD
	
=======
	//test2
>>>>>>> origin/master
	private String address;
	private String city;
	private String state;
	private String country;
<<<<<<< HEAD
	private String postalCode;
	
	public Address() {
		this.address = null;
		this.city = null;
		this.state = null;
		this.country = null;
		this.postalCode = null;
	}
	
	public Address(String address, String city, String state, String country, String postalCode){
=======
	private int postalCode;
	
	public Address(String address, String city, String state, String country, int postalCode){
>>>>>>> origin/master
		this.address = new String(address);
		this.city = new String(city);
		this.state = new String(state);
		this.country = new String(country);
<<<<<<< HEAD
		this.postalCode = new String(postalCode);
=======
		this.postalCode = new Integer(postalCode);
>>>>>>> origin/master
	}
	
	public Address(Address address) {
		this.address = address.address;
		this.city = address.city;
		this.state = address.state;
		this.country = address.country;
		this.postalCode = address.postalCode;
	}
	
<<<<<<< HEAD
	public void setAddress(String address){
		this.address = address;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public void setState(String state){
		this.state = state;
	}
	
	public void setCountry(String country){
		this.country = country;
	}
	
	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}
	
=======
>>>>>>> origin/master
	public String getAddress() {
		String address = this.address;
		return address;
	}
	
	public String getCity() {
		String city = this.city;
		return city;
	}
	
	public String getState() {
		String state = this.state;
		return state;
	}
	
	public String getCountry() {
		String country = this.country;
		return country;
	}
	
<<<<<<< HEAD
	public String getPostalCode() {
		String postalCode = this.postalCode;
		return postalCode;
	}
	
	public boolean addressExists() {
		boolean output = false;
		
		if (this.address != null) {
			output = true;
		}
		
		return output;
	}
	
	public boolean cityExists() {
		boolean output = false;
		
		if (this.city != null) {
			output = true;
		}
		
		return output;
	}
	
	public boolean stateExists() {
		boolean output = false;
		
		if (this.state != null) {
			output = true;
		}
		
		return output;
	}
	
	public boolean countryExists() {
		boolean output = false;
		
		if (this.country != null) {
			output = true;
		}
		
		return output;
	}
	
	public boolean postalCodeExists() {
		boolean output = false;
		
		if (this.postalCode != null) {
			output = true;
		}
		
		return output;
	}
	
=======
	public int getPostalCode() {
		int postalCode = this.postalCode;
		return postalCode;
	}
	
>>>>>>> origin/master
	public String toString() {
		
		String output;
		
		output = "Address: " + address + "\nCity: " + city + "\nState: " + state + "\nCountry: " + country +
				 "\nPostal Code: " + postalCode + "\n";
		return output;
	}

}
