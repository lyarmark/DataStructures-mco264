package school;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Student extends Person {

	// WHAT IF A STUDENT HAS MULTIPLE MAJORS?
	private Major major;
	private GregorianCalendar enrolledDate;
	private GregorianCalendar dateOfBirth;
	private Double GPA;
	private Integer creditsEarned;
	private String socialSecurityNum;
	private ArrayList<CompletedCourse> completedCourse;

	// default constructor, no student args
	public Student(Integer ID, String firstName, String lastName, Character midInitial, Address address,
			String phoneNumber, String gender) throws InvalidDataException {
		super(ID, firstName, lastName, midInitial, address, phoneNumber, gender);
		// default values
		GPA = null;
		creditsEarned = 0;
	}

	// Constructors
	public Student(
			// person fields
			Integer ID, String first, String last, Character midInitial, Address address, String phone, String gender,
			// student fields
			String major, GregorianCalendar enrolledDate, GregorianCalendar dateOfBirth, Double GPA,
			Integer creditsEarned, String socialSecurityNum, ArrayList<CompletedCourse> completedCourses)
			throws InvalidDataException {

		super(ID, first, last, midInitial, address, phone, gender);

		// data validation

		// not checking if ArrayList completedCourse is null, because student
		// may not have completed courses yet.
		// if student has completed courses (from high school, seminary etc.),
		// they will be copied to the new ArrayList
		if ((enrolledDate == null) || (dateOfBirth == null) || (dateOfBirth.compareTo(enrolledDate) <= 0) || (GPA < 0)
				|| (GPA > 4.0) || (creditsEarned < 0) || (creditsEarned > 300) || (socialSecurityNum == null)) {
			throw new InvalidDataException();
		}

		// validate major in enum
		Major majorCode = Major.validateMajor(major);

		if (majorCode == null) {
			// no corresponding major code could be found
			throw new InvalidDataException();
		}

		this.major = majorCode;
		this.enrolledDate = enrolledDate;
		this.dateOfBirth = dateOfBirth;
		this.GPA = GPA;
		this.creditsEarned = creditsEarned;
		this.socialSecurityNum = socialSecurityNum;
		completedCourse = new ArrayList<CompletedCourse>();

		// potentially give the option of filling in the arraylist on
		// registration
		/*
		 * if (completedCourse.size() != 0) { for (CompletedCourse cc:
		 * completedCourses) { this.completedCourse.add(cc); } }
		 */
	}// end constructor

	// constructor no major given
	public Student(
			// person fields
			Integer ID, String first, String last, Character midInitial, Address address, String phone, String gender,
			// student fields
			GregorianCalendar enrolledDate, GregorianCalendar dateOfBirth, Double GPA, Integer creditsEarned,
			String socialSecurityNum, ArrayList<CompletedCourse> completedCourse) throws InvalidDataException {
		this(ID, first, last, midInitial, address, phone, gender, Major.UDCD.toString(), enrolledDate, dateOfBirth,
				GPA, creditsEarned, socialSecurityNum, completedCourse);
	}

	// setters
	public void setMajor(String major) throws InvalidDataException {
		// validate major in enum
		Major majorCode = Major.validateMajor(major);

		if (majorCode == null) {
			// no corresponding major code could be found
			throw new InvalidDataException();
		}
	}

	public void setGPA(Double GPA) throws InvalidDataException {
		if ((GPA < 0.0) || (GPA > 4.0)) {
			throw new InvalidDataException();
		}
		this.GPA = GPA;
	}

	public void setCreditsEarned(Integer creditsEarned) throws InvalidDataException {
		if ((creditsEarned < 0) || (creditsEarned > 300)) {
			throw new InvalidDataException();
		}
		this.creditsEarned = creditsEarned;
	}

	// getters deep copy
	public String getMajor() {
		String theMajor = major.toString();
		return theMajor;
	}

	public GregorianCalendar getEnrolledDate() {
		GregorianCalendar theEnrolledDate = new GregorianCalendar(enrolledDate.get(Calendar.YEAR),
				enrolledDate.get(Calendar.MONTH) + 1, enrolledDate.get(Calendar.DAY_OF_MONTH));
		return theEnrolledDate;
	}

	public GregorianCalendar getDateOfBirth() {
		GregorianCalendar theDateOfBirth = new GregorianCalendar(dateOfBirth.get(Calendar.YEAR),
				dateOfBirth.get(Calendar.MONTH) + 1, dateOfBirth.get(Calendar.DAY_OF_MONTH));
		return theDateOfBirth;
	}

	public Double getGPA() {
		Double theGPA = GPA;
		return theGPA;
	}

	public Integer getCreditsEarned() {
		Integer theCreditsEarned = creditsEarned;
		return theCreditsEarned;
	}

	public String getSocialSecurityNum() {
		String theSocial = socialSecurityNum;
		return theSocial;
	}

	public ArrayList<CompletedCourse> getCompletedCourse() {
		ArrayList<CompletedCourse> theCompletedCourse = new ArrayList<CompletedCourse>();
		for (CompletedCourse cc : completedCourse) {
			theCompletedCourse.add(cc);
		}
		return theCompletedCourse;
	}

	public void completeCourse(Course c, String g) throws InvalidDataException {
		Grade gradeCode = Grade.validateGrade(g);
		if ((c == null) || (gradeCode == null)) {
			throw new InvalidDataException();
		}

		// completed course today
		GregorianCalendar completedDate = new GregorianCalendar();
		CompletedCourse cc = new CompletedCourse(
		// course fields
				c.getCourseID(), c.getDescription(), c.getNumCredits(), c.getDepartmentID(),
				// completedCourse fields
				getID(), g.toString(), completedDate);
		completedCourse.add(cc);
		creditsEarned += c.getNumCredits();

		// update GPA
		double gradeXcredits = 0.0;
		for (CompletedCourse cc2 : completedCourse) {
			// sum of (each course's grade * course's numCredits
			gradeXcredits += (gradeCode.getName() * cc2.getNumCredits());
		}
		// gpa is above sum / by total credits earned
		GPA = (gradeXcredits / creditsEarned);
	}

	public CompletedCourse findCompletedCourse(String courseID) throws NotFoundException, InvalidDataException {
		if (courseID == null) {
			throw new InvalidDataException();
		}

		for (CompletedCourse cc : completedCourse) {
			if (cc.getCourseID().toString().equalsIgnoreCase(courseID)) {
				CompletedCourse theCC = new CompletedCourse(
				// course fields
						cc.getCourseID(), cc.getDescription(), cc.getNumCredits(), cc.getDepartmentID(),
						// completedCourse fields
						cc.getStudentID(), cc.getGrade().toString(), cc.getCompletedDate());
				return theCC;
			}
		}
		// could not find completed course
		throw new NotFoundException();
	}

	public String getGradeOfCourse(String courseID) throws NotFoundException, InvalidDataException {
		return findCompletedCourse(courseID).getGrade();
	}

	public ArrayList<CompletedCourse> getCoursesByDepartment(String departmentID) throws NotFoundException,
	InvalidDataException {
		if (departmentID == null) {
			throw new InvalidDataException();
		}

		ArrayList<CompletedCourse> ccByDepartment = new ArrayList<CompletedCourse>();
		for (CompletedCourse cc : getCompletedCourse()) {
			if (cc.getDepartmentID().equalsIgnoreCase(departmentID)) {
				ccByDepartment.add(cc);
			}
		}
		if (ccByDepartment.size() == 0) {
			// dept did not offer any courses
			// could have method return null instead of throwing exception,
			// but if a dept exists, it should offer courses
			throw new NotFoundException();
		}
		return ccByDepartment;
	}

	public ArrayList<CompletedCourse> getCourseByGrade(Grade g) throws InvalidDataException {
		ArrayList<CompletedCourse> ccWithGrade = new ArrayList<CompletedCourse>();
		for (CompletedCourse cc : completedCourse) {
			if (cc.getGrade().equalsIgnoreCase(g.toString())) {
				ccWithGrade.add(cc);
			}
		}
		return ccWithGrade;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		buffer.append(super.toString());
		buffer.append("\nMajor: " + getMajor());
		buffer.append("\nDate student enrolled: " + dateFormatter.format(this.getEnrolledDate().getTime()));
		buffer.append("\nDate of birth: " + dateFormatter.format(this.getDateOfBirth().getTime()));
		buffer.append("\nGPA: " + getGPA());
		buffer.append("\nCredits earned: " + getCreditsEarned());
		buffer.append("\nSocial security number: " + getSocialSecurityNum());
		buffer.append("\nCompleted courses: " + getCompletedCourse().toString());
		return buffer.toString();
	}
}