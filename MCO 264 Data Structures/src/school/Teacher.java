package school;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Teacher extends Employee {

	private String departmentID;
	private String socialSecurityNum;
	// what if a teacher has multiple degrees in multiple majors?
	private Degree degreeID;
	private Major majorID;
	private Double salary;
	ArrayList<TaughtCourse> taughtCourses;

	// constructor
	public Teacher(
			// person fields
			Integer personID, String firstName, String lastName, Character midInitial, Address address,
			String phoneNumber, String gender,
			// employee fields
			GregorianCalendar hireDate, GregorianCalendar dateOfBirth, String employeeTypeID,
			// teacher fields
			String departmentID, String socialSecurity, String degree, String major, Double salary)
			throws InvalidDataException {
		super(personID, firstName, lastName, midInitial, address, phoneNumber, gender, hireDate, dateOfBirth,
				employeeTypeID);

		// data validation
		if ((departmentID == null) || (departmentID == "") || (socialSecurity == null) || (socialSecurity == "")
				|| (degree == null) || (degree == "") || (major == null) || (major == "") || (salary == null)) {
			throw new InvalidDataException();
		}

		// validate degree is in enum
		Degree validateDegree = validateDegree(degree);
		// validate major is in enum
		Major validateMajor = validateMajor(major);
		if ((validateDegree == null) || (validateMajor == null)) {
			throw new InvalidDataException();
		}

		// validate salary is reasonable
		if ((salary > 1000000.0) || (salary < 15000.0)) {
			throw new InvalidDataException();
		}

		this.departmentID = departmentID;
		this.socialSecurityNum = socialSecurity;
		this.degreeID = validateDegree;
		this.majorID = validateMajor;
		this.salary = salary;
		this.taughtCourses = new ArrayList<TaughtCourse>();
	}// end constructor

	// find degree in enum
	public static Degree validateDegree(String validateDegree) {
		for (Degree degree : Degree.values()) {
			// check by enum value
			if (degree.getName().equalsIgnoreCase(validateDegree)
					|| (degree.toString().equalsIgnoreCase(validateDegree))) {
				return degree;
			}
		}
		return null;
	}

	// find major in enum
	public static Major validateMajor(String validateMajor) {
		for (Major major : Major.values()) {
			// check by string constant
			if (major.getName().equalsIgnoreCase(validateMajor)
			// check by enum value
					|| (major.toString().equalsIgnoreCase(validateMajor))) {
				return major;
			}
		}
		// major not found
		return null;
	}

	// setters
	public void setDegree(String degree) throws InvalidDataException {
		if (degree == null) {
			throw new InvalidDataException();
		}
		Degree validateDegree = validateDegree(degree);
		if (validateDegree == null) {
			throw new InvalidDataException();
		}
		this.degreeID = validateDegree;
	}

	public void setMajorID(String major) throws InvalidDataException {
		if (major == null) {
			throw new InvalidDataException();
		}
		Major validateMajor = validateMajor(major);
		if (validateMajor == null) {
			throw new InvalidDataException();
		}
		this.majorID = validateMajor;
	}

	public void setSalary(Double salary) throws InvalidDataException {
		if ((salary > 1000000.0) || (salary < this.salary)) {
			throw new InvalidDataException();
		}
		this.salary = salary;
	}

	// getters- deep copies
	public String getDepartmentID() {
		String theDeptID = this.departmentID;
		return theDeptID;
	}

	public String socialSecurityNum() {
		String theSocial = this.socialSecurityNum;
		return theSocial;
	}

	public Degree getDegree() {
		Degree theDegree = this.degreeID;
		return theDegree;
	}

	public Major getMajor() {
		Major theMajor = this.majorID;
		return theMajor;
	}

	public Double getSalary() {
		Double theSalary = this.salary;
		return theSalary;
	}

	public ArrayList<TaughtCourse> getTaughtCourses() {
		ArrayList<TaughtCourse> theTaughtCourses = new ArrayList<TaughtCourse>();
		for (TaughtCourse tc : this.taughtCourses) {
			theTaughtCourses.add(tc);
		}
		return theTaughtCourses;
	}

	// apply raise
	public void applyRaise(Double percent) throws InvalidDataException {
		// validate salary not being decreased
		if (percent < 0) {
			throw new InvalidDataException();
		}
		this.salary *= percent;
	}

	// add a taught course to this teacher's list
	public void taughtCourse(Course c, Integer teacherID, Integer year, String semester, String sectionID)
			throws InvalidDataException, NotFoundException {

		TaughtCourse taughtCourse = new TaughtCourse(c, teacherID, year, semester, sectionID);

		boolean found = false;
		if (this.taughtCourses.size() == 0) {
			// taught courses is empty, so just add
			this.taughtCourses.add(taughtCourse);
		} else {
			for (TaughtCourse tc : this.taughtCourses) {
				if ((tc.getCourseID().compareTo(taughtCourse.getCourseID()) == 0)
						&& (tc.getYear().compareTo(taughtCourse.getYear()) == 0)
						&& (tc.getSemesterID().compareTo(taughtCourse.getSemesterID()) == 0)
						&& (tc.getSection().compareTo(taughtCourse.getSection()) == 0)) {
					// the taught course has already been added to the list
					found = true;
					break;
				}
			}
			// this taught course is not in the list already
			// add it
			if (!found) {
				this.taughtCourses.add(taughtCourse);
			}
		}
	}

	// how many courses did this teacher teach this semester
	public int numCoursesTaughtThisSemester(Integer year, Semester semesterID) {
		int numCoursesTaught = 0;
		for (TaughtCourse tc : this.taughtCourses) {
			if (tc.getYear().compareTo(year) == 0) {
				if (tc.getSemesterID().equals(semesterID)) {
					numCoursesTaught++;
				}
			}
		}
		return numCoursesTaught;
	}

	public int numDifferentCoursesTaught() {
		// WHAT WOULD BE THE MOST EFFICIENT WAY TO DO THIS?
		// ASSUME NO DOUBLES, THEN COMPARE EACH ELEMENT OF ARRAYLIST AND
		// DECREMENT COUNTER
		// OR
		// CREATE NEW ARRAYLIST, ONLY ADD A COURSE TO IT IF DOUBLE NOT ALREADY
		// IN LIST
		// RETURN NEW ARRAYLIST.SIZE()
		// OR
		// SORT THE TAUGHT COURSES AND SEARCE FOR TAUGHT COURSES WITH TEACHER ID
		// AND INCREASE COUNTER
		ArrayList<TaughtCourse> distinctTaughtCourses = new ArrayList<TaughtCourse>();
		for (TaughtCourse tc : this.taughtCourses) {
			for (TaughtCourse tc2 : distinctTaughtCourses) {
				if (tc.getCourseID() != tc2.getCourseID()) {
					distinctTaughtCourses.add(tc);
				}
			}
		}
		return distinctTaughtCourses.size();
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		DecimalFormat dformat = new DecimalFormat("$#,###.##");
		buffer.append(super.toString());
		buffer.append("\nDepartment ID: " + this.getDepartmentID());
		buffer.append("\nSocial Security Number: " + this.socialSecurityNum());
		buffer.append("\nDegree: " + this.getDegree());
		buffer.append("\nMajor: " + this.getMajor());
		buffer.append("\nSalary: " + dformat.format(this.getSalary()));
		if (this.taughtCourses.size() != 0) {
			buffer.append("\nTaught Courses: ");
			for (TaughtCourse tc : this.taughtCourses) {
				buffer.append("\n" + tc.toString());
			}
		}
		return buffer.toString();
	}
}
