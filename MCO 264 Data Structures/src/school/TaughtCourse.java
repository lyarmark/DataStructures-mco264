package school;

//Instantiate a TaughtCourse by adding the TeacherID of the given teacher and data provided by the parameters

public class TaughtCourse extends Course {
	private Integer teacherID;
	private Integer year;
	private Semester semesterID;
	private Section sectionID;

	// constructor
	public TaughtCourse(
			// course fields
			Course course,
			// taughtCourse fields
			Integer teacherID, Integer year, String semesterID, String sectionID) throws InvalidDataException {
		super(course.getCourseID(), course.getDescription(), course.getDepartmentID(), course.getNumCredits());

		// data validation
		if ((teacherID == null) || (year == null) || (semesterID == null) || (semesterID == "") || (sectionID == "")
				|| (sectionID == null)) {
			throw new InvalidDataException();
		}

		Semester validateSemester = Semester.validateSemester(semesterID.toString());
		Section validateSection = Section.validateSection(sectionID.toString());
		if ((validateSemester == null) || (validateSection == null)) {
			throw new InvalidDataException();
		}

		this.teacherID = teacherID;
		this.year = year;
		this.semesterID = validateSemester;
		this.sectionID = validateSection;
	}// end constructor

	// getters- deep copies
	public Integer getTeacherID() {
		Integer theTeacherID = this.teacherID;
		return theTeacherID;
	}

	public Integer getYear() {
		Integer theYear = this.year;
		return theYear;
	}

	public Semester getSemesterID() {
		Semester theSemester = this.semesterID;
		return theSemester;
	}

	public Section getSection() {
		Section theSection = this.sectionID;
		return theSection;
	}

	// toString
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nTeacher ID: " + this.getTeacherID());
		buffer.append("\nYear: " + this.getYear());
		buffer.append("\nSemester ID: " + this.getSemesterID());
		buffer.append("\nSection ID: " + this.getSection());
		return buffer.toString();
	}
}