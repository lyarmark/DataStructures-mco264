package school;

public class Address {
	private String street;
	private String city;
	private USstate state;	//holds 2 letter abbrv.
	private String zipCode;

	
	//constructor
	public Address(String street, String city, String state, String zipCode) throws InvalidDataException {

		//data validation
		if (street == null || street == "" || city == null || city == ""
				|| state == null || state == "" || zipCode == null) {
			throw new InvalidDataException();
			}

		USstate tempState = USstate.validateState(state);
		if (tempState == null || !(validateZipcode(zipCode))) {
			throw new InvalidDataException();
			}
		
		//if you get here, your data is valid
		this.street = street;
		this.city = city;
		this.state = tempState;
		this.zipCode = zipCode;
		}//end constructor
	
	/*
	//the constructor below exists because the student file only provides city and state
	 * NOW IT DOES NOT EXIST BECAUSE SOMEONE WAS SO KIND AS TO PROVIDE A FILE WITH FULL ADDRESS
	//i do not think this is correct- students should have to provide full address
	public Address(String city, String state) throws InvalidDataException {
		//data validation
		if(city == null || city == "" || state == null || state == "") {
			throw new InvalidDataException();
		}
		USstate validateState = USstate.validateState(state);
		this.city = city;
		this.state = validateState;
	}
	*/
	
	//setters
	public void setStreet(String street) throws InvalidDataException {
		if (street == null || street == "") {
			throw new InvalidDataException();
		}
		this.street = street;
		}
	public void setCity(String city) throws InvalidDataException {
		if (city == null || city == "") {
			throw new InvalidDataException();
		} 
		this.city = city;
		}
	public void setState(String state) throws InvalidDataException {
		if (state == null || state == "") {
			throw new InvalidDataException();
		}
		USstate tempState = USstate.validateState(state);
		if (tempState != null) {
			this.state = tempState;
			}
		else {
			throw new InvalidDataException();
			}
		}
	public void setZip(String zip) throws InvalidDataException {
		if (zip == null) {
			throw new InvalidDataException();
		}
		if (validateZipcode(zip)) {
			this.zipCode = zip;
			}
		else {
			throw new InvalidDataException();
			}
	}
	
	//getters copy
	public String getStreet() {
		String theStreet = this.street;
		return theStreet;
	}
	public String getCity() {
		String theCity = this.city;
		return theCity;
	}
	public String getState() {
		String theState = this.state.getName();
		return theState;
	}
	public String getZipCode() {
		String theZipCode = this.zipCode;
		return theZipCode;
	}

	private static boolean validateZipcode(String zipCode) {
		if (String.valueOf(zipCode).length() == 5 || String.valueOf(zipCode).length() == 9) {
			return true;
		}
		else {
			return false;
		}
	}

	//toString
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nAddress: ");
		buffer.append("\n" + this.getStreet() + " ");
		buffer.append("\n" + this.getCity() + ", " + this.getState() + " " + this.getZipCode());
		return buffer.toString();
	}
}
