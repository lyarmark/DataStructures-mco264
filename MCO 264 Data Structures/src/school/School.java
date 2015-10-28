package school;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class School {
	private String schoolName;
	private Address schoolAddress;
	private String schoolPhoneNumber;
	private ArrayList<Person> schoolMembers;
	private ArrayList<Course> schoolCourses;
	private ArrayList<Department> schoolDepartments;

	// constructor, null arraylists
	public School(String name, Address address, String phone) throws InvalidDataException {
		// data validation
		if ((name == null) || (name == "") || (address == null) || (phone == null) || (phone == "")
				|| !(Person.validatePhoneNumber(phone))) {
			throw new InvalidDataException();
		}

		schoolName = name;
		schoolAddress = address;
		schoolPhoneNumber = phone;
		schoolMembers = new ArrayList<Person>();
		schoolCourses = new ArrayList<Course>();
		schoolDepartments = new ArrayList<Department>();
	}

	// constructor, filled arraylists
	public School(String name, Address address, String phone, String teachFileName, String studentFileName,
			String departmentFileName, String courseFileName) throws FileNotFoundException, InvalidDataException,
			DuplicateException {
		this(name, address, phone);

		// scanner will read from which ever text file is currently in the file
		// path for File file
		File file = new File(teachFileName);
		Scanner readFile = new Scanner(file);

		// only enter loop and read data if file has data at all or that hasn't
		// been read yet
		while (readFile.hasNext()) {
			readTeacher(readFile, file);
		}

		file = new File(departmentFileName);
		readFile = new Scanner(file);
		while (readFile.hasNext()) {
			readDepartment(readFile, file);
		}

		file = new File(courseFileName);
		readFile = new Scanner(file);
		while (readFile.hasNext()) {
			readCourse(readFile, file);
		}

		file = new File(studentFileName);
		readFile = new Scanner(file);
		while (readFile.hasNext()) {
			readStudent(readFile, file);
		}
	}

	private void readTeacher(Scanner readFile, File file) throws InvalidDataException, DuplicateException {
		// person fields
		Integer teacherID = readFile.nextInt();
		String firstName = readFile.next();
		String lastName = readFile.next();
		readFile.nextLine();
		// address fields
		String street = readFile.nextLine();
		String[] cityState = readFile.nextLine().split(",");
		// read a string a parse it as an int because some zips start with 0
		String zipCode = readFile.next();
		Address address = new Address(street, cityState[0], cityState[1], zipCode);
		String phoneNumber = readFile.next();
		if (phoneNumber.equalsIgnoreCase("N/A")) {
			phoneNumber = null;
		}
		Character gender = readFile.next().charAt(0);

		// employee fields
		String[] date = readFile.next().split("/"); // hire date
		GregorianCalendar hireDate = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[0]),
				Integer.parseInt(date[1]));
		date = readFile.next().split("/"); // birth date
		GregorianCalendar birthDate = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[0]),
				Integer.parseInt(date[1]));
		String employeeType = readFile.next();

		// teacher fields
		String departmentCode = readFile.next();
		String socialSecurityNumber = readFile.next();
		String degree = readFile.next();
		String major = readFile.next();
		Double salary = readFile.nextDouble();

		addTeacher(
				// person fields
				teacherID, firstName, lastName, null, address, phoneNumber, gender.toString(),
				// employee fields
				hireDate, birthDate, employeeType,
				// teacher fields
				departmentCode, socialSecurityNumber, degree, major, salary);
	}

	private void readStudent(Scanner readFile, File file) throws InvalidDataException, DuplicateException {
		// person fields
		Integer studentID = readFile.nextInt();
		String lastName = readFile.next();
		String firstName = readFile.next();
		Character midInitial = readFile.next().charAt(0);
		readFile.nextLine(); // flush buffer
		// address fields
		String street = readFile.nextLine();
		String[] cityState = readFile.nextLine().trim().split(",");
		// read a string a parse it as an int because some zips start with 0
		String zipCode = readFile.next();
		Address address = new Address(street, cityState[0], cityState[1], zipCode);
		String phoneNumber = readFile.next();
		Character gender = readFile.next().charAt(0);

		// student fields
		String major = readFile.next();
		String[] date = readFile.next().split("/");
		GregorianCalendar enrolledDate = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[0]),
				Integer.parseInt(date[1]));
		date = readFile.next().split("/"); // birth date
		GregorianCalendar birthDate = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[0]),
				Integer.parseInt(date[1]));
		String socialSecurity = readFile.next();

		addStudent(
				// person fields
				studentID, firstName, lastName, midInitial, address, phoneNumber, gender.toString(),
				// student fields
				major, enrolledDate, birthDate, 0.0, 0, socialSecurity, null);
	}

	private void readDepartment(Scanner readFile, File file) throws InvalidDataException, DuplicateException {
		String[] deptArray = readFile.nextLine().split(";");
		Integer employeeID = Integer.parseInt(deptArray[5]);
		addDepartment(deptArray[0], deptArray[1], deptArray[2], deptArray[3], deptArray[4], employeeID);
	}

	private void readCourse(Scanner readFile, File file) throws InvalidDataException, NumberFormatException,
	DuplicateException {
		String[] courseData = readFile.nextLine().split(";");
		addCourse(courseData[0], courseData[1], (courseData[2]), Integer.parseInt(courseData[3]));
	}

	public void setSchoolPhone(String phone) throws InvalidDataException {
		if (!Person.validatePhoneNumber(phone)) {
			throw new InvalidDataException();
		}
		schoolPhoneNumber = phone;
	}

	// getters deep copy
	public String getSchoolName() {
		String theSchoolName = schoolName;
		return theSchoolName;
	}

	public Address getSchoolAddress() throws InvalidDataException {
		Address theSchoolAddress = new Address(schoolAddress.getStreet(), schoolAddress.getCity(),
				schoolAddress.getState(), schoolAddress.getZipCode());
		return theSchoolAddress;
	}

	public String getSchoolPhoneNumber() {
		String theSchoolPhone = schoolPhoneNumber;
		return theSchoolPhone;
	}

	public ArrayList<Person> getSchoolMembers() {
		ArrayList<Person> theSchoolMembers = new ArrayList<Person>();
		for (Person p : schoolMembers) {
			theSchoolMembers.add(p);
		}
		return theSchoolMembers;
	}

	public ArrayList<Course> getSchoolCourses() {
		ArrayList<Course> theSchoolCourses = new ArrayList<Course>();
		for (Course c : schoolCourses) {
			theSchoolCourses.add(c);
		}
		return theSchoolCourses;
	}

	public ArrayList<Department> getSchoolDepartments() {
		ArrayList<Department> theSchoolDepartments = new ArrayList<Department>();
		for (Department d : schoolDepartments) {
			theSchoolDepartments.add(d);
		}
		return theSchoolDepartments;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nSchool Name: " + getSchoolName());
		// getSchoolAddress throws an exception because it returns a deep copy
		// of the address
		// since the copy will be created from a valid address, this exception
		// will never be thrown.
		try {
			buffer.append(getSchoolAddress().toString());
		} catch (InvalidDataException e) {
		}
		buffer.append("\nSchool phone number: " + getSchoolPhoneNumber());
		buffer.append("\\nnTeachers: " + getTeachersByName().toString());
		buffer.append("\n\nStudents: " + getStudentsByName().toString());
		/*
		 * buffer.append("\\nnOther Employees: "); for (Person p :
		 * schoolMembers) { if (p instanceof Employee) { if (!(p instanceof
		 * Teacher)) { buffer.append(p.toString()); } } }
		 */

		buffer.append("\nCourses: ");
		for (Course c : schoolCourses) {
			buffer.append("\n" + c.toString());
		}

		buffer.append("\nDepartments: ");
		for (Department d : schoolDepartments) {
			buffer.append("\n" + d.toString());
		}
		return buffer.toString();
	}

	public void addTeacher(
			// person fields
			Integer personID, String firstName, String lastName, Character midInitial, Address address,
			String phoneNumber, String gender,
			// employee fields
			GregorianCalendar hireDate, GregorianCalendar dateOfBirth, String employeeTypeID,
			// teacher fields
			String departmentID, String socialSecurity, String degree, String major, Double salary)
					throws InvalidDataException, DuplicateException {

		Teacher t = new Teacher(personID, firstName, lastName, midInitial, address, phoneNumber, gender, hireDate,
				dateOfBirth, employeeTypeID, departmentID, socialSecurity, degree, major, salary);

		for (Person p : getSchoolMembers()) {
			if (p.equals(t)) {
				throw new DuplicateException();
			}
		}
		schoolMembers.add(t);
	}

	public void addStudent(
			// person fields
			Integer ID, String first, String last, Character midInitial, Address address, String phone, String gender,
			// student fields
			String major, GregorianCalendar enrolledDate, GregorianCalendar dateOfBirth, Double GPA,
			Integer creditsEarned, String socialSecurityNum, ArrayList<CompletedCourse> completedCourses)
					throws InvalidDataException, DuplicateException {
		Student s = new Student(ID, first, last, midInitial, address, phone, gender, major, enrolledDate, dateOfBirth,
				GPA, creditsEarned, socialSecurityNum, completedCourses);

		for (Person p : getSchoolMembers()) {
			if (p.equals(s)) {
				throw new DuplicateException();
			}
		}
		schoolMembers.add(s);
	}

	public void addCourse(String courseID, String description, String departmentID, Integer numCredits)
			throws InvalidDataException, DuplicateException {
		Course c = new Course(courseID, description, departmentID, numCredits);

		for (Course c2 : getSchoolCourses()) {
			if (c2.equals(c)) {
				throw new DuplicateException();
			}
		}
		schoolCourses.add(c);
	}

	public void addDepartment(String ID, String name, String location, String phone, String faxNumber, Integer chair)
			throws InvalidDataException, DuplicateException {
		Department d = new Department(ID, name, location, phone, faxNumber, chair);

		for (Department d2 : getSchoolDepartments()) {
			if (d2.equals(d)) {
				throw new DuplicateException();
			}
		}
		schoolDepartments.add(d);
	}

	public void removeTeacher(Integer teacherID) throws NotFoundException {
		boolean found = false;
		for (Teacher t : getTeachers()) {

			if (t.getID().compareTo(teacherID) == 0) {
				found = true;
				schoolMembers.remove(t);
				break;
			}
		}
		// teacher not found
		if (!found) {
			throw new NotFoundException();
		}
	}

	public void removeStudent(Integer studentID) throws NotFoundException {
		boolean found = false;
		for (Student s : getStudents()) {
			if (s.getID().compareTo(studentID) == 0) {
				found = true;
				schoolMembers.remove(s);
				break;
			}
		}
		// student not found
		if (!found) {
			throw new NotFoundException();
		}
	}

	public void removeCourse(String courseID) throws NotFoundException {
		boolean found = false;
		for (Course c : schoolCourses) {
			if (c.getCourseID().equalsIgnoreCase(courseID)) {
				found = true;
				schoolCourses.remove(c);
				break;
			}
		}
		// course not found
		if (!found) {
			throw new NotFoundException();
		}
	}

	// change teacher's last name
	public void modifyTeacherLastName(Integer teacherID, String newLastName) throws InvalidDataException,
	NotFoundException {

		boolean found = false;
		for (Teacher t : getTeachers()) {
			if (t.getID().compareTo(teacherID) == 0) {
				found = true;
				t.setLastName(newLastName);
				break;
			}
		}
		// teacher not found
		if (!found) {
			throw new NotFoundException();
		}
	}

	// change teacher's address
	public void modifyTeacherAddress(Integer teacherID, Address address) throws InvalidDataException, NotFoundException {
		boolean found = false;
		for (Teacher t : getTeachers()) {
			if (t.getID().compareTo(teacherID) == 0) {
				found = true;
				t.setAddress(address);
				break;
			}
		}
		// teacher not found
		if (!found) {
			throw new NotFoundException();
		}
	}

	// change teacher's degree
	public void modifyTeachersDegree(Integer teacherID, String degree, String major) throws InvalidDataException,
	NotFoundException {
		boolean found = false;
		for (Teacher t : getTeachers()) {
			if (t.getID().compareTo(teacherID) == 0) {
				found = true;
				t.setMajorID(major);
				t.setDegree(degree);
				break;
			}
		}
		// teacher not found
		if (!found) {
			throw new NotFoundException();
		}
	}

	public void giveTeacherRaisePercent(Integer teacherID, Double percent) throws InvalidDataException,
	NotFoundException {
		if (((teacherID == null) | (percent == null)) || (percent < 0)) {
			throw new InvalidDataException();
		}
		boolean found = false;
		for (Teacher t : getTeachers()) {
			if (t.getID().compareTo(teacherID) == 0) {
				found = true;
				t.setSalary(t.getSalary() * percent);
				break;
			}
		}
		// teacher not found
		if (!found) {
			throw new NotFoundException();
		}
	}

	public void giveTeacherRaiseAmount(Integer teacherID, Double amount) throws InvalidDataException, NotFoundException {
		if ((amount == null) || (amount < 0)) {
			throw new InvalidDataException();
		}
		boolean found = false;
		for (Teacher t : getTeachers()) {
			if (t.getID().compareTo(teacherID) == 0) {
				found = true;
				t.setSalary(t.getSalary() + amount);
				break;
			}
		}
		// teacher not found
		if (!found) {
			throw new NotFoundException();
		}
	}

	public void modifyStudentLastName(Integer studentID, String newLastName) throws InvalidDataException,
			NotFoundException {

		boolean found = false;
		for (Student s : getStudents()) {
			if (s.getID().compareTo(studentID) == 0) {
				found = true;
				s.setLastName(newLastName);
				break;
			}
		}

		// student not found
		if (!found) {
			throw new NotFoundException();

		}
	}

	public void modifyStudentPhoneNumber(Integer studentID, String newPhoneNumber) throws InvalidDataException,
	NotFoundException {

		boolean found = false;
		for (Student s : getStudents()) {
			if (s.getID().compareTo(studentID) == 0) {
				found = true;
				s.setPhoneNumber(newPhoneNumber);
				break;
			}
		}
		// student not found
		if (!found) {
			throw new NotFoundException();
		}
	}

	public void addCompletedCourse(Integer studentID, String courseID, String grade) throws InvalidDataException,
	NotFoundException, DuplicateException {

		boolean cfound = false;
		// go through courses
		for (Course c : schoolCourses) {
			if (c.getCourseID().compareToIgnoreCase(courseID) == 0) {
				cfound = true;
				boolean sfound = false;
				// make a new completed course
				GregorianCalendar completedDate = new GregorianCalendar();
				CompletedCourse cc = new CompletedCourse(
						// course fields
						c.getCourseID(), c.getDescription(), c.getNumCredits(), c.getDepartmentID(),
						// completed course fields
						studentID, grade.toString(), completedDate);
				// go through people
				for (Person p : schoolMembers) {
					if (p.getID().compareTo(studentID) == 0) {
						sfound = true;
						Student s = (Student) p;
						// check if the completed course is already entered
						for (CompletedCourse cc2 : s.getCompletedCourse()) {
							if (cc.getCourseID().compareTo(cc2.getCourseID()) == 0) {
								throw new DuplicateException();
							}
						}
						s.completeCourse(cc, grade);
						break;
					}
				}
				// course found, student not found
				if (!sfound) {
					throw new NotFoundException();
				}
			}
		}
		// course not found
		if (!cfound) {
			throw new NotFoundException();
		}
	}

	public Double getStudentGPA(Integer studentID) throws NotFoundException, InvalidDataException {
		if (studentID == null) {
			throw new InvalidDataException();
		}
		for (Student s : getStudents()) {
			if (s.getID().compareTo(studentID) == 0) {
				return s.getGPA();
			}
		}
		// student not found
		throw new NotFoundException();
	}

	public String getGradeOfCourse(Integer studentID, String courseID) throws NotFoundException, InvalidDataException {
		for (Student s : getStudents()) {
			if (s.getID().compareTo(studentID) == 0) {
				return s.getGradeOfCourse(courseID);
			}
		}
		// student not found
		throw new NotFoundException();
	}

	public ArrayList<CompletedCourse> getCoursesByDepartment(Integer studentID, String departmentID)
			throws NotFoundException, InvalidDataException {
		ArrayList<CompletedCourse> ccOfDept = new ArrayList<CompletedCourse>();
		for (Student s : getStudents()) {
			if (s.getID().compareTo(studentID) == 0) {
				ccOfDept = s.getCoursesByDepartment(departmentID);
				break;
			}
		}
		return ccOfDept;
	}

	public ArrayList<CompletedCourse> getCoursesByGrade(Integer studentID, String g) throws InvalidDataException {
		Grade validateGrade = Grade.validateGrade(g);
		if (validateGrade == null) {
			throw new InvalidDataException();
		}
		ArrayList<CompletedCourse> ccWithGrade = new ArrayList<CompletedCourse>();
		for (Student s : getStudents()) {
			if (s.getID().compareTo(studentID) == 0) {
				ccWithGrade = s.getCourseByGrade(validateGrade);
			}
		}
		return ccWithGrade;
	}

	class compareName implements Comparable<Person> {
		public int compareTo(Person a, Person b) {
			return a.getID().compareTo(b.getID());
		}

		@Override
		public int compareTo(Person o) {
			// TODO Auto-generated method stub
			return 0;
		}
	}

	public ArrayList<Teacher> getTeachers() {
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		for (Person p : schoolMembers) {
			if (p instanceof Teacher) {
				Teacher t = (Teacher) p;
				teachers.add(t);
			}
		}
		Collections.sort(teachers);
		return teachers;
	}

	public ArrayList<Person> getTeachersByName() {
		ArrayList<Person> teachers = new ArrayList<Person>();
		for (Person p : schoolMembers) {
			if (p instanceof Teacher) {
				teachers.add(p);
			}
		}
		Collections.sort(teachers, new Comparator<Person>() {
			@Override
			public int compare(Person t1, Person t2) {
				if (t1.getLastName().compareTo(t2.getLastName()) == 0) {
					return t1.getFirstName().compareTo(t2.getFirstName());
				} else {
					return t1.getLastName().compareTo(t2.getLastName());
				}
			}
		});
		return teachers;
	}

	public ArrayList<Student> getStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		for (Person p : schoolMembers) {
			if (p instanceof Student) {
				Student s = (Student) p;
				students.add(s);
			}
		}
		Collections.sort(students);
		return students;
	}

	public ArrayList<Person> getStudentsByName() {
		ArrayList<Person> students = new ArrayList<Person>();
		for (Person p : schoolMembers) {
			if (p instanceof Student) {
				students.add(p);
			}
		}
		Collections.sort(students, new Comparator<Person>() {
			@Override
			public int compare(Person s1, Person s2) {
				if (s1.getLastName().compareTo(s2.getLastName()) == 0) {
					return s1.getFirstName().compareTo(s2.getFirstName());
				} else {
					return s1.getLastName().compareTo(s2.getLastName());
				}
			}
		});
		return students;
	}

	public void addTaughtCourse(Integer teacherID, String courseID, Integer year, String semester, String section)
			throws InvalidDataException, NotFoundException {

		boolean tfound = false;
		// go through teachers
		for (Teacher t : getTeachers()) {
			if (t.getID().compareTo(teacherID) == 0) {
				tfound = true;
				boolean cfound = false;
				// go through courses
				for (Course c : schoolCourses) {
					if (c.getCourseID().compareToIgnoreCase(courseID) == 0) {
						cfound = true;
						t.taughtCourse(c, t.getID(), year, semester, section);
						break;
					}
				}
				// teacher found, course not found
				if (!cfound) {
					throw new NotFoundException();
				}
			}
			break;
		}
		// teacher not found, course not checked
		if (!tfound) {
			throw new NotFoundException();
		}
	}

	public int howManyCoursesPerSemester(Integer teacherID, Integer year, String semester) throws NotFoundException {
		Semester validateSemester = Semester.validateSemester(semester);
		boolean found = false;
		// go through teachers
		for (Teacher t : getTeachers()) {
			if (t.getID().compareTo(teacherID) == 0) {
				found = true;
				return t.numCoursesTaughtThisSemester(year, validateSemester);
			}
		}
		// teacher not found
		if (!found) {
			throw new NotFoundException();
		}
		return 0;
	}
}