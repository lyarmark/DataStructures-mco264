package school;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CompletedCourse extends Course{
	private Integer studentID;
	//grade will hold a letter grade
	private Grade grade;
	private GregorianCalendar completedDate;
	
	public CompletedCourse(
			//course fields
			String courseID, String description, Integer numCredits, String departmentID,
			//completedCourse fields
			Integer studentID, String grade, GregorianCalendar completedDate)
			throws InvalidDataException {
		super(courseID, description, departmentID, numCredits);
		
		//data validation
		if (studentID == null || grade == null || completedDate == null) {
			throw new InvalidDataException();
		}
		
		//validate grade in enum
		Grade gradeCode = Grade.validateGrade(grade);
		if (gradeCode == null) {
			//no corresponding major code could be found
			throw new InvalidDataException();
			}
		
		this.studentID = studentID;
		this.grade = gradeCode;
		this.completedDate = completedDate;
		}
	
	
	//getters
	public Integer getStudentID() {
		Integer theStudentID = this.studentID;
		return theStudentID;
	}
	public String getGrade() {
		Grade theGrade = this.grade;
		return theGrade.toString();
	}
	public GregorianCalendar getCompletedDate() {
		GregorianCalendar theCompletedDate = new GregorianCalendar(
				this.completedDate.get(Calendar.YEAR), this.completedDate.get(Calendar.MONTH)+1, this.completedDate.get(Calendar.DAY_OF_MONTH));
		return theCompletedDate;
	}
}
