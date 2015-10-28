package school;

public class Department {
	String departmentID;
	String departmentName;
	String location;
	String phoneNumber;
	String faxNumber;
	Integer departmentChairperson;

	// constructors
	public Department(String ID, String name, String location, String phone, String faxNumber, Integer chair)
			throws InvalidDataException {

		// data validation
		if ((ID == null)
				|| (ID == "")
				// not checking for other parameter variables == null because
				// they are allowed to be
				// but they should not be ""
				|| (name == "") || (location == "") || (phone == "") || (faxNumber == "")
				|| !(Person.validatePhoneNumber(phone)) || !(Person.validatePhoneNumber(faxNumber))) {
			throw new InvalidDataException();
		}

		// if you get here, data is valid
		this.departmentID = ID;
		this.departmentName = name;
		this.location = location;
		this.phoneNumber = phone;
		this.faxNumber = faxNumber;
		this.departmentChairperson = chair;
	}

	public Department(String ID, String name) throws InvalidDataException {
		this(ID, name, null, null, null, null);
	}

	public Department(String ID, String name, String location) throws InvalidDataException {
		this(ID, name, location, null, null, null);
	}

	public Department(String ID, String name, String phoneNumber, String faxNumber) throws InvalidDataException {
		this(ID, name, null, phoneNumber, faxNumber, null);
	}

	public Department(String ID, String name, String location, String phoneNumber, String faxNumber)
			throws InvalidDataException {
		this(ID, name, location, phoneNumber, faxNumber, null);
	}

	public Department(String ID, String name, Integer chair) throws InvalidDataException {
		this(ID, name, null, null, null, chair);
	}

	public Department(String ID, String name, String location, Integer chair) throws InvalidDataException {
		this(ID, name, location, null, null, chair);
	}

	public Department(String ID, String name, Integer chair, String phoneNumber) throws InvalidDataException {
		this(ID, name, null, phoneNumber, null, chair);
	}

	public Department(String ID, Integer chair, String name, String faxNumber) throws InvalidDataException {
		this(ID, name, null, null, faxNumber, chair);
	}

	public Department(String ID, String name, String location, String phoneNumber, Integer chair)
			throws InvalidDataException {
		this(ID, name, location, phoneNumber, null, chair);
	}

	public Department(String ID, String name, String location, Integer chair, String faxNumber)
			throws InvalidDataException {
		this(ID, name, location, null, faxNumber, chair);
	}

	// setters
	public void setLocation(String location) throws InvalidDataException {
		if ((location == null) || (location == "")) {
			throw new InvalidDataException();
		}
		this.location = location;
	}

	public void setPhoneNumber(String phoneNumber) throws InvalidDataException {
		if (!Person.validatePhoneNumber(phoneNumber)) {
			throw new InvalidDataException();
		}
		this.phoneNumber = phoneNumber;
	}

	public void setFaxNumber(String faxNumber) throws InvalidDataException {
		if (!Person.validatePhoneNumber(faxNumber)) {
			throw new InvalidDataException();
		}
		this.faxNumber = faxNumber;
	}

	public void setChair(Integer chair) throws InvalidDataException {
		if (chair == null) {
			throw new InvalidDataException();
		}
		departmentChairperson = chair;
	}

	// getters
	public String getDepartmentID() {
		String theID = departmentID;
		return theID;
	}

	public String getDepartmentName() {
		String theName = departmentName;
		return theName;
	}

	public String getLocation() {
		if (location != null) {
			String theLocation = location;
			return theLocation;
		} else {
			return "data not available";
		}
	}

	public String getPhoneNumber() {
		if (phoneNumber != null) {
			String thePhoneNumber = phoneNumber;
			return thePhoneNumber;
		} else {
			return "data not available";
		}
	}

	public String getFaxNumber() {
		if (faxNumber != null) {
			String theFaxNumber = faxNumber;
			return theFaxNumber;
		} else {
			return "data not available";
		}
	}

	public Integer getDepartmentChairperson() {
		if (departmentChairperson != null) {
			Integer theChair = departmentChairperson;
			return theChair;
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nDepartment Name: " + departmentName);
		buffer.append("\nID: " + departmentID);
		buffer.append("\nLocation: " + getLocation());
		buffer.append("\nPhone number: " + getPhoneNumber());
		buffer.append("\nFax number" + getFaxNumber());
		buffer.append("\nChair person: ");
		if (departmentChairperson != null) {
			buffer.append(departmentChairperson);
		} else {
			buffer.append("data not available");
		}
		return buffer.toString();
	}

	// compare to
	public int compareTo(Department other) {
		return departmentID.compareTo(other.departmentID);
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
		// obviously if we got here, obj is a Department, but cast it
		// check if one is null and the other is not
		Department other = (Department) obj;
		if (departmentID == null) {
			if (other.departmentID != null) {
				return false;
			}
		}
		// check if the ID values are equal
		else if (!departmentID.equals(other.departmentID)) {
			return false;
		}
		// IDs are equal, return true
		return true;
	}
}
