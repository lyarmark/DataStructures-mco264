package school;

public class Course implements Comparable<Course> {
	private String courseID;
	private String description;
	private Integer numCredits;
	private String departmentID;

	// constructor
	public Course(String courseID, String description, String departmentID, Integer numCredits)
			throws InvalidDataException {
		// data validation
		if ((courseID == null) || (courseID == "") || (description == null) || (description == "")
				|| (numCredits == null) || (numCredits < 0) || (numCredits > 4) || (departmentID == null)
				|| (departmentID == "")) {
			throw new InvalidDataException();
		}

		this.courseID = courseID;
		this.description = description;
		this.numCredits = numCredits;
		this.departmentID = departmentID;
	}

	// getters- deep copy
	public String getCourseID() {
		String theCourseID = courseID;
		return theCourseID;
	}

	public String getDescription() {
		String theDescription = description;
		return theDescription;
	}

	public Integer getNumCredits() {
		Integer theNumCredits = numCredits;
		return theNumCredits;
	}

	public String getDepartmentID() {
		String theDepartmentID = departmentID;
		return theDepartmentID;
	}

	// toString
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nCourse ID: " + getCourseID());
		buffer.append("\nCourse Description: " + getDescription());
		buffer.append("\nDepartment ID: " + getDepartmentID());
		return buffer.toString();
	}

	// compareTo
	@Override
	public int compareTo(Course other) {
		if (courseID.compareTo(other.courseID) > 0) {
			return 1;
		} else if (courseID.compareTo(other.courseID) < 0) {
			return -1;
		} else {
			return 0;
		}
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
		// obviously if we got here, obj is a Course, but cast it
		// check if one is null and the other is not
		Course other = (Course) obj;
		if (courseID == null) {
			if (other.courseID != null) {
				return false;
			}
		}
		// check if the ID values are equal
		else if (!courseID.equals(other.courseID)) {
			return false;
		}
		// IDs are equal, return true
		return true;
	}
}