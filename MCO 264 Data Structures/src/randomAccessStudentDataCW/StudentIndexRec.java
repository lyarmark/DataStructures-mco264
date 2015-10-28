package randomAccessStudentDataCW;

import java.io.Serializable;

public class StudentIndexRec implements Serializable, Comparable<StudentIndexRec> {
	private Integer studentID;
	private Long fileLocation;

	public StudentIndexRec(Integer studentID, Long fileLocation) {
		this.studentID = studentID;
		this.fileLocation = fileLocation;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public Long getFileLocation() {
		return fileLocation;
	}

	// base comparison on the value of the student ID
	@Override
	public int compareTo(StudentIndexRec indexRec) {
		return this.getStudentID().compareTo(indexRec.getStudentID());
	}
}
