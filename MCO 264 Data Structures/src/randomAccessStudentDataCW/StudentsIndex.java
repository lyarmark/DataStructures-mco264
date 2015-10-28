package randomAccessStudentDataCW;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class StudentsIndex implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<StudentIndexRec> index;

	/**
	 * set up an index for the first time
	 */
	public StudentsIndex() {
		this.index = new ArrayList<StudentIndexRec>();
	}

	/**
	 * @param studentID
	 *
	 * @param fileLocation
	 * @throws Exception
	 *             if duplicate
	 */
	public void addStudentToIndex(Integer studentID, Long fileLocation) throws DuplicateDataException {
		StudentIndexRec temp = new StudentIndexRec(studentID, fileLocation);
		if (hasStudent(studentID)) {
			throw new DuplicateDataException();
		}
		index.add(temp);
		sortIndex();
	}

	/**
	 *
	 * @param studentID
	 * @return Long - location of record in the data file
	 * @throws NotFoundException
	 */

	public Long findStudentLocation(Integer studentID) throws NotFoundException {
		int el = findStudentBinSearch(studentID);
		StudentIndexRec indx = this.index.get(el);
		return indx.getFileLocation();
	}

	/**
	 *
	 * @param studentID
	 * @return element number of the studentindexrec in the array
	 * @throws NotFoundException
	 */
	// this method is rather useless
	// the findStudentBinSearch method does the same job more efficiently
	private int findStudent(Integer studentID) throws NotFoundException {
		for (StudentIndexRec student : this.index) {
			if (student.getStudentID().compareTo(studentID) == 0) {
				return this.index.indexOf(student);
			}
		}
		throw new NotFoundException();
	}

	/**
	 *
	 * @param studentID
	 * @return true if studentid appears in the index array
	 */

	public boolean hasStudent(Integer studentID) {
		return this.index.contains(studentID);
	}

	public void removeStudent(Integer studentID) throws NotFoundException {
		this.index.remove(findStudent(studentID));
	}

	private void sortIndex() {
		Collections.sort(this.index);
	}

	private int findStudentBinSearch(Integer studentID) throws NotFoundException {
		StudentIndexRec dummy = new StudentIndexRec(studentID, 1L);
		int el = Collections.binarySearch(this.index, dummy);
		if (el < 0) {
			throw new NotFoundException();
		} else {
			return el;
		}
	}
}
