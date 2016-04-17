
public class Address {
	//test2
	private String address;
	private String city;
	private String state;
	private String country;
	private int postalCode;
	
	public Address(String address, String city, String state, String country, int postalCode){
		this.address = new String(address);
		this.city = new String(city);
		this.state = new String(state);
		this.country = new String(country);
		this.postalCode = new Integer(postalCode);
	}
	
	public Address(Address address) {
		this.address = address.address;
		this.city = address.city;
		this.state = address.state;
		this.country = address.country;
		this.postalCode = address.postalCode;
	}
	
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
	
	public int getPostalCode() {
		int postalCode = this.postalCode;
		return postalCode;
	}
	
	public String toString() {
		
		String output;
		
		output = "Address: " + address + "\nCity: " + city + "\nState: " + state + "\nCountry: " + country +
				 "\nPostal Code: " + postalCode + "\n";
		return output;
	}

}
