
public class MLS {
	
	private String name;
	private String agent;
	private String address;
	private String email;
	private String phoneNumber;
	private String fax;
	
	public MLS(String name, String agent, String address, String email, String phoneNumber, String fax) {
		this.name = new String(name);
		this.agent = new String(agent);
		this.address = new String(address);
		this.email = new String(email);
		this.phoneNumber = new String(phoneNumber);
		this.fax = new String(fax);
	}
	
	public MLS(MLS mls) {
		this.name = mls.name;
		this.agent = mls.agent;
		this.address = mls.address;
		this.email = mls.email;
		this.phoneNumber = mls.phoneNumber;
		this.fax = mls.fax;
	}
	
	public String getName() {
		String name = this.name;
		return name;
	}
	
	public String getAgent() {
		String agent = this.agent;
		return agent;
	}
	
	public String getAddress() {
		String address = this.address;
		return address;
	}
	
	public String getEmail() {
		String email = this.email;
		return email;
	}
	
	public String getPhoneNumber() {
		String phoneNumber = this.phoneNumber;
		return phoneNumber;
	}
	
	public String getFax() {
		String fax = this.fax;
		return fax;
	}
	
	public String toString() {
		
		String output;
		
		output = "MLS Name: " + name + "\nAgent: " + agent + "\nAddress: " + address + "\nEmail: " + email + 
				 "\nPhone Number: " + phoneNumber + "\nFax: " + fax + "\n";
		return output;
	}

}
