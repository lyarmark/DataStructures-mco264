package school;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Employee extends Person {
	private GregorianCalendar hireDate;
	private GregorianCalendar dateOfBirth;
	private EmployeeType employeeTypeID; // HOLDS THE ID(ATTRIBUTE/NAME), NOT

	// THE VALUE

	// constructors
	public Employee(
	// person fields
			Integer ID, String first, String last, Character midInitial, Address address, String phone, String gender,
			// employee fields
			GregorianCalendar hireDate, GregorianCalendar dateOfBirth, String employeeTypeID)
			throws InvalidDataException {
		super(ID, first, last, midInitial, address, phone, gender);

		// data validation
		if ((hireDate == null) || (dateOfBirth == null) || (hireDate.compareTo(dateOfBirth) <= 0)
				|| (employeeTypeID == null)) {
			throw new InvalidDataException();
		}

		// check if hireDate - dateOfBirth is >= 18 years and <= 80 years
		// this prevents hiring someone too old or young
		// how do you hire interns who are under 18?
		// how to you force an employee to retire? (on pension, of course)
		if ((((hireDate.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR)) < 18) || ((hireDate.get(Calendar.YEAR) - dateOfBirth
				.get(Calendar.YEAR)) > 80))) {
			throw new InvalidDataException();
		}

		EmployeeType validateType = EmployeeType.validateEmployeeType(employeeTypeID);
		if (validateType == null) {
			throw new InvalidDataException();
		}

		this.hireDate = hireDate;
		this.dateOfBirth = dateOfBirth;
		this.employeeTypeID = validateType;
	}

	// getters and setters
	public void setEmployeeType(String type) throws InvalidDataException {
		if ((type == null) || (type == "")) {
			throw new InvalidDataException();
		}

		EmployeeType validateType = EmployeeType.validateEmployeeType(type);
		if (validateType == null) {
			throw new InvalidDataException();
		}

		this.employeeTypeID = validateType;
	}

	public GregorianCalendar getHireDate() {
		GregorianCalendar theHireDate = new GregorianCalendar(this.hireDate.get(Calendar.YEAR),
				this.hireDate.get(Calendar.MONTH) + 1, this.hireDate.get(Calendar.DAY_OF_MONTH));
		return theHireDate;
	}

	public GregorianCalendar getDateOfBirth() {
		GregorianCalendar theDateOfBirth = new GregorianCalendar(this.dateOfBirth.get(Calendar.YEAR),
				this.dateOfBirth.get(Calendar.MONTH) + 1, this.dateOfBirth.get(Calendar.DAY_OF_MONTH));
		return theDateOfBirth;
	}

	public String getEmployeeType() {
		EmployeeType theType = this.employeeTypeID;
		return theType.toString();
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\nHire date: " + dateFormatter.format(this.getHireDate().getTime()));
		buffer.append("\nBirth date: " + dateFormatter.format(this.getDateOfBirth().getTime()));
		buffer.append("\nEmployee type: " + this.getEmployeeType());
		return buffer.toString();
	}
}