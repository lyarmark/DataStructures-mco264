package school;

public class Person implements Comparable<Person> {
	private Integer ID;
	private String firstName;
	private String lastName;
	private Character midInitial;
	private Address address;
	private String phoneNumber;
	private Gender gender;

	// constructors
	public Person(Integer ID, String first, String last, Character midInitial, Address address, String phoneNumber,
			String gender) throws InvalidDataException {

		// data validation
		if ((ID == null) || (ID < 0) || (first == null) || (first == "") || (last == null) || (last == "")
				|| (address == null) || !(validatePhoneNumber(phoneNumber)) || (gender == null)) {
			throw new InvalidDataException();
		}

		Gender validateGender = Gender.validateGender(gender);
		if (validateGender == null) {
			throw new InvalidDataException();
		}

		this.ID = ID;
		this.firstName = first;
		this.lastName = last;
		this.midInitial = midInitial;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.gender = validateGender;
	}

	public Person(Integer ID, String first, String last, Address address, String gender) throws InvalidDataException {
		this(ID, first, last, null, address, null, gender);
	}

	public Person(Integer ID, String first, String last, Address address, String phoneNumber, String gender)
			throws InvalidDataException {
		this(ID, first, last, null, address, phoneNumber, gender);
	}

	public Person(Integer ID, String first, String last, Character midInitial, Address address, String gender)
			throws InvalidDataException {
		this(ID, first, last, midInitial, address, null, gender);
	}

	// setters
	public void setLastName(String last) throws InvalidDataException {
		if ((last == null) || (last == "")) {
			throw new InvalidDataException();
		}
		this.lastName = last;
	}

	public void setAddress(Address address) throws InvalidDataException {
		if (address == null) {
			throw new InvalidDataException();
		}
		this.address = address;
	}

	public void setPhoneNumber(String phoneNumber) throws InvalidDataException {
		if (!validatePhoneNumber(phoneNumber)) {
			throw new InvalidDataException();
		}
		this.phoneNumber = phoneNumber;
	}

	// getters
	public Integer getID() {
		Integer theID = this.ID;
		return theID;
	}

	public String getFirstName() {
		String theFirstName = this.firstName;
		return theFirstName;
	}

	public String getLastName() {
		String theLastName = this.lastName;
		return theLastName;
	}

	public Character getMidInitial() {
		if (midInitial != null) {
			Character theMidInitial = this.midInitial;
			return theMidInitial;
		} else {
			return null;
		}
	}

	public Address getAddress() throws InvalidDataException {
		Address theAddress = new Address(this.address.getStreet(), this.address.getCity(), this.address.getState(),
				this.address.getZipCode());
		return theAddress;
	}

	public String getPhoneNumber() {
		if (this.phoneNumber != null) {
			String thephoneNumber = this.phoneNumber;
			return thephoneNumber;
		} else {
			return null;
		}
	}

	public String getGender() {
		String theGender = this.gender.name();
		return theGender;
	}

	// method is public so other classes can use it
	// don't need duplicate methods in multiple classes if they're all doing the
	// same thing
	public static Boolean validatePhoneNumber(String phoneNumber) {
		// either phone is a 10 digit number or it is null
		if ((phoneNumber == null) || (phoneNumber.length() == 10)) {
			return true;
		}
		return false;
	}

	// toString
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("\n\nName: " + this.getFirstName() + " ");
		if (this.midInitial != null) {
			buffer.append(this.getMidInitial() + ". ");
		}
		buffer.append(this.getLastName());

		buffer.append("\nID: " + this.getID());
		buffer.append(this.address.toString());
		// check if there is a phone number
		if (this.phoneNumber != null) {
			buffer.append("\nPhone number: " + this.getPhoneNumber());
		} else {
			buffer.append("\nPhone number not available");
		}
		buffer.append("\nGender: " + this.getGender());

		return buffer.toString();
	}

	// compare to
	@Override
	public int compareTo(Person other) {
		return this.getID().compareTo(other.getID());
	}

	// equals
	@Override
	public boolean equals(Object obj) {
		// if they point to the same location in memory
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		// the 2 objects are of different classes, and are therefore obviously
		// not equal
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		// obviously if we got here, obj is a Student, but cast it
		// check if one is null and the other is not
		Person other = (Person) obj;
		if (this.getID() == null) {
			if (other.getID() != null) {
				return false;
			}
		}
		// check if the ID values are equal
		else if (!this.getID().equals(other.getID())) {
			return false;
		}
		// IDs are equal, return true
		return true;
	}
}