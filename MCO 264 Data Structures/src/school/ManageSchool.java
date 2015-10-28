package school;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ManageSchool {
	public static void main(String[] args) {
		School touro = null;
		try {
			Address address = new Address("1602 Avenue J", "Brooklyn", "NY", "11218");
			touro = new School("Touro College", address, "7185555555", "teachers.txt", "students3.txt",
					"departments.txt", "courses.txt");
		} catch (InvalidDataException e) {
			System.out.println("Either you or the file have provided invalid data.");
		} catch (FileNotFoundException e) {
			System.out.println("One or more of the file names and location you provided is not valid");
		} catch (DuplicateException e) {
			System.out.println("You can not enter duplicate information");
		}

		Scanner keyboard = new Scanner(System.in);
		int choice;
		do {
			choice = menu(keyboard);
			switch (choice) {
			case -1: {
				System.out.println("Exiting program ... Have a nice day!");
				System.exit(0);
			}
			case 1: {
				do {
					choice = teacherMenu(keyboard);
					switch (choice) {
					case 1: {
						try {
							Person p = getPersonInfo(keyboard);
							// employee fields
							System.out.println("Please enter hire date (MM/dd/yyyy)");
							String[] date = keyboard.nextLine().split("/");
							GregorianCalendar hireDate = new GregorianCalendar((Integer.parseInt(date[2])),
									(Integer.parseInt(date[0])), (Integer.parseInt(date[1])));
							System.out.println("Please enter date of birth");
							date = keyboard.nextLine().split("/");
							GregorianCalendar birthDate = new GregorianCalendar((Integer.parseInt(date[2])),
									(Integer.parseInt(date[0])), (Integer.parseInt(date[1])));
							String employeeID = getString(
									keyboard,
									"employee type "
											+ "(\nACCOUNTANT, CHAIRPERSON, CLERK, DEAN, \nPROFESSOR, INSTRUCTOR, LABTECHNICIAN, \nMAINTENANCE, TUTOR, SECRETARY, SECURITY)");

							// teacher fields
							String dID = getString(keyboard, "department ID");
							String socialSecurity = getString(keyboard, "social security number");
							String degree = getString(keyboard, "the degree");
							String major = getString(keyboard, "major");
							System.out.println("Please enter salary");
							double salary = keyboard.nextDouble();
							touro.addTeacher(p.getID(), p.getFirstName(), p.getLastName(), p.getMidInitial(),
									p.getAddress(), p.getPhoneNumber(), p.getGender(), hireDate, birthDate, employeeID,
									dID, socialSecurity, degree, major, salary);
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						} catch (DuplicateException e) {
							System.out.println(e.toString());
						}
						break;
					}
					case 2: {
						try {
							int tID = getInt(keyboard, "teacher's ID");
							touro.removeTeacher(tID);
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						}
						break;
					}
					case 3: {
						try {
							int tID = getInt(keyboard, "teacher's ID");
							String lastName = getString(keyboard, "the new last name");
							touro.modifyTeacherLastName(tID, lastName);
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						}
						break;
					}
					case 4: {
						try {

							int tID = getInt(keyboard, "teacher's ID");
							Address newAddress = getAddressInfo(keyboard);
							touro.modifyTeacherAddress(tID, newAddress);
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						}
						break;
					}
					case 5: {
						try {
							int tID = getInt(keyboard, "teacher's ID");
							String degree = getString(keyboard, "the degree");
							String major = getString(keyboard, "the major");
							touro.modifyTeachersDegree(tID, degree, major);
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						}
						break;
					}

					case 6: {
						try {
							int tID = getInt(keyboard, "teacher's ID");
							System.out.println("Please enter the amount.");
							double amount = keyboard.nextDouble();
							touro.giveTeacherRaiseAmount(tID, amount);
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						}
						break;
					}

					case 7: {
						try {
							int tID = getInt(keyboard, "teacher's ID");
							System.out.println("Please enter the percent.");
							double percent = keyboard.nextDouble();
							touro.giveTeacherRaisePercent(tID, percent);
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						}
						break;
					}
					case 8: {
						try {
							int tID = getInt(keyboard, "teacher's ID");
							String cID = getString(keyboard, "course ID");
							int year = getInt(keyboard, "the year");
							String semester = getString(keyboard, "the semester");
							String section = getString(keyboard, "the section");
							touro.addTaughtCourse(tID, cID, year, semester, section);
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						}
						break;
					}
					case 9: {
						try {
							int tID = getInt(keyboard, "teacher's ID");
							int year = getInt(keyboard, "the year");
							String semester = getString(keyboard, "the semester");

							System.out.println(touro.howManyCoursesPerSemester(tID, year, semester)
									+ " course(s) taught.");
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						}
						break;
					}
					case 0: {
						break;
					}
					}
				} while ((choice >= 1) && (choice <= 9) && (choice != 0));
				break;
			}

			case 2: {
				do {
					choice = studentMenu(keyboard);
					switch (choice) {
					case 1: {
						try {
							Person p = getPersonInfo(keyboard);
							String major = getString(keyboard, "the major");
							System.out.println("Please enter enrolled date (MM/dd/yyyy)");
							String[] date = keyboard.nextLine().split("/");
							GregorianCalendar enrolledDate = new GregorianCalendar((Integer.parseInt(date[2])),
									(Integer.parseInt(date[0])), (Integer.parseInt(date[1])));
							System.out.println("Please enter: birth date (MM/dd/yyyy)");
							date = keyboard.nextLine().split("/");
							GregorianCalendar birthDate = new GregorianCalendar((Integer.parseInt(date[2])),
									(Integer.parseInt(date[0])), (Integer.parseInt(date[1])));

							System.out.println("Please enter the GPA");
							double GPA = keyboard.nextDouble();
							int creditsEarned = getInt(keyboard, "the number of credits already earned");
							String socialSecurityNum = getString(keyboard, "the social security number");
							ArrayList<CompletedCourse> completedCourse = new ArrayList<CompletedCourse>();
							System.out.println("How many courses has the student already completed?");
							int num = keyboard.nextInt();
							keyboard.nextLine();
							if (num > 0) {
								for (int i = 0; i >= num; num--) {
									CompletedCourse cc = getCCInfo(keyboard, p);
									completedCourse.add(cc);
								}
							}
							touro.addStudent(p.getID(), p.getFirstName(), p.getLastName(), p.getMidInitial(),
									p.getAddress(), p.getPhoneNumber(), p.getGender(), major, enrolledDate, birthDate,
									GPA, creditsEarned, socialSecurityNum, completedCourse);
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						} catch (DuplicateException e) {
							System.out.println(e.toString());
						}
						break;
					}

					case 2: {
						try {
							int sID = getInt(keyboard, "student's ID");
							touro.removeStudent(sID);
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						}
						break;
					}
					case 3: {
						try {
							int sID = getInt(keyboard, "student ID");
							String last = getString(keyboard, "the new last name");
							touro.modifyStudentLastName(sID, last);
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						}
						break;
					}

					case 4: {
						try {
							int sID = getInt(keyboard, "student's ID");
							String phone = getString(keyboard, "the new phone number");
							touro.modifyStudentPhoneNumber(sID, phone);
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						}
						break;
					}
					case 5: {
						try {
							int sID = getInt(keyboard, "student's ID");
							String cID = getString(keyboard, "course ID");
							String grade = getString(keyboard, "the grade of the course");
							touro.addCompletedCourse(sID, cID, grade);
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						}

						catch (DuplicateException e) {
							System.out.println(e.toString());
						}
						break;
					}
					case 6: {
						try {
							int sID = getInt(keyboard, "student's ID");
							System.out.println(sID + " GPA: ");
							System.out.print(touro.getStudentGPA(sID));
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						}
						break;
					}
					case 7: {
						try {
							int sID = getInt(keyboard, "student's ID");
							String cID = getString(keyboard, "course ID");
							System.out.println("Student " + sID);
							System.out.println("Course " + cID.toUpperCase() + ": " + touro.getGradeOfCourse(sID, cID));
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						}
						break;

					}
					case 8: {
						try {
							int sID = getInt(keyboard, "student's ID");
							String dID = getString(keyboard, "department ID");
							System.out.println(touro.getCoursesByDepartment(sID, dID));
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						}
						break;
					}

					case 9: {
						try {
							int sID = getInt(keyboard, "student's ID");
							String grade = getString(keyboard, "the grade");
							System.out.println(touro.getCoursesByGrade(sID, grade));
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						}
					}

					case 0: {
						break;
					}
					}
				} while ((choice >= 1) && (choice <= 9) && (choice != 0));
				break;
			}
			case 3: {
				do {
					choice = departmentMenu(keyboard);
					switch (choice) {
					case 1: {
						try {
							keyboard.nextLine(); // flush buffer
							String dID = getString(keyboard, "department ID");
							String dname = getString(keyboard, "the department name");
							String location = getString(keyboard, "the department location");
							String phone = getString(keyboard, "the phone number");
							String faxNumber = getString(keyboard, "the fax number");
							int chair = getInt(keyboard, "the chairperson's ID");

							touro.addDepartment(dID, dname, location, phone, faxNumber, chair);

						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						} catch (DuplicateException e) {
							System.out.println(e.toString());
						}
						break;

					}
					case 0: {
						break;
					}
					}

				} while ((choice >= 1) && (choice <= 1) && (choice != 0));
				break;
			}
			case 4: {
				do {
					choice = courseMenu(keyboard);
					switch (choice) {
					case 1: {
						keyboard.nextLine(); // flush buffer
						String cID = getString(keyboard, "the course ID");
						String desc = getString(keyboard, "the course description");
						String dID = getString(keyboard, "the department ID");
						int numCredits = getInt(keyboard, "the number of credits");

						try {
							touro.addCourse(cID, desc, dID, numCredits);
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						} catch (DuplicateException e) {
							System.out.println(e.toString());
						}
						break;
					}
					case 2: {
						keyboard.nextLine(); // flush buffer
						String cID = getString(keyboard, "course ID");
						try {
							touro.removeCourse(cID);
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						}
						break;
					}
					case 0: {
						break;
					}
					}
				} while ((choice >= 1) && (choice >= 2) && (choice != 0));
				break;
			}
			case 5: {
				do {
					choice = schoolMenu(keyboard);
					switch (choice) {
					case 1: {
						System.out.println(touro.getTeachers());
						break;
					}

					case 2: {
						System.out.println(touro.getTeachersByName());
						break;
					}

					case 3: {
						System.out.println(touro.getStudents());
						break;
					}
					case 4: {
						System.out.println(touro.getStudentsByName());
						break;
					}

					case 5: {
						try {
							keyboard.nextLine(); // flush buffer
							String phone = getString(keyboard, "the school's phone number");
							touro.setSchoolPhone(phone);
						} catch (InvalidDataException e) {
							System.out.println(e.toString());
						}
						break;
					}
					case 6: {
						System.out.println(touro.toString());
						break;
					}

					case 0: {
						break;
					}
					}

				} while ((choice >= 1) && (choice <= 6) && (choice != 0));
				break;
			}

			}

		} while (choice <= 5);
	}

	public static int menu(Scanner keyboard) {
		System.out.println("Please select a choice." + "\nWould you like to view, edit, or create:"
				+ "\n\t1.\tTeachers" + "\n\t2.\tStudents" + "\n\t3.\tDepartments" + "\n\t4.\tCourses"
				+ "\n\t5.\tSchool" + "\n\t-1\tExit");
		return keyboard.nextInt();
	}

	public static int teacherMenu(Scanner keyboard) {
		System.out.println("Please select a choice:" + "\n\t1.\tAdd teacher" // addTeacher
				+ "\n\t2.\tRemove teacher" // removeTeacher
				+ "\n\t3.\tChange teacher's last name" // modifyTeacherLastName
				+ "\n\t4.\tChange teacher's address" // modifyTeacherAddress
				+ "\n\t5.\tChange teacher's Degree" // modifyTeacherDegree
				+ "\n\t6.\tIncrease teacher's salary by amount" // giveTeacherRaise;
				+ "\n\t7.\tIncrease teacher's salary by percent" // giveTeacherRaise
				+ "\n\t8.\tAdd taught course" // addTaughtCourse
				+ "\n\t9.\tHow many courses a teacher taught in a semester" // //howManyCoursesPerSemester
				+ "\n\t0.\tMain menu");
		return keyboard.nextInt();
	}

	public static int studentMenu(Scanner keyboard) {
		System.out.println("please select a choice:" + "\n\t1.\tAdd student" // addStudent
				+ "\n\t2.\tRemove student" // removeStudent
				+ "\n\t3.\tChange student's last name" // modifyStudentLastName
				+ "\n\t4.\tChange student phone number" // modifyStudentPhoneNumber
				+ "\n\t5.\tAdd a completed course" // addCompletedCourse
				+ "\n\t6.\tGet student's GPA" // getStudentGPA
				+ "\n\t7.\tGet a student's grade of a completed course" // getGradeOfCourse
				+ "\n\t8.\tGet a student's completed courses by department" // getCoursesByDepartment
				+ "\n\t9.\tGet a student's courses by grade" // getCoursesByGrade
				+ "\n\t0.\tReturn to main menu");
		return keyboard.nextInt();
	}

	public static int departmentMenu(Scanner keyboard) {
		System.out.println("Please select a choice:" + "\n\t1.\tAdd department" // addDepartment
				+ "\n\t0.\tReturn to main menu");
		return keyboard.nextInt();
	}

	public static int courseMenu(Scanner keyboard) {
		System.out.println("Please select a choice:" + "\n\t1.\tAdd course" // addCourse
				+ "\n\t2.\tRemove course" // removeCourse
				+ "\n\t0.\tReturn to main menu");
		return keyboard.nextInt();
	}

	public static int schoolMenu(Scanner keyboard) {
		System.out.println("Please select a choice:" + "\n\t1.\tList teachers sorted by ID" // getTeachers
				+ "\n\t2.\tList teachers sorted by name" // getTeachersSortedByName
				+ "\n\t3.\tList students sorted by ID" // getStudents
				+ "\n\t4.\tList students sorted by name" // getStudentsByName
				+ "\n\t5.\tChange school's phone number" // setSchoolPhoneNumber
				+ "\n\t6.\tView school data" // toString
				+ "\n\t0.\tReturn to main menu");
		return keyboard.nextInt();
	}

	public static Person getPersonInfo(Scanner keyboard) {
		try {

			int pID = getInt(keyboard, "person's ID");
			String first = getString(keyboard, "the first name");
			String last = getString(keyboard, "the last name");

			System.out.println("Would you like to enter a middle initial? (Y/N)");
			Character midInitial = null;
			if (keyboard.nextLine().equalsIgnoreCase("Y")) {
				midInitial = getString(keyboard, "the middle initial").charAt(0);
			}
			// else if, midinitial is already null

			Address address = getAddressInfo(keyboard);

			System.out.println("Would you like to enter a phone number? (Y/N)");
			String phoneNumber = null;
			if (keyboard.nextLine().equalsIgnoreCase("Y")) {
				phoneNumber = getString(keyboard, "the phone number");
			}
			// else if, phoneNumber is already null

			String gender = getString(keyboard, "the gender");
			Person p = new Person(pID, first, last, midInitial, address, phoneNumber, gender);
			return p;
		} catch (InvalidDataException e) {
			System.out.println(e.toString());
		}
		// could not create instance of person
		return null;
	}

	public static Address getAddressInfo(Scanner keyboard) {
		System.out.println("Please enter the address:");
		String street = getString(keyboard, "street");
		String city = getString(keyboard, "city");
		String state = getString(keyboard, "state");
		String zip = getString(keyboard, "zipcode");
		Address address = null;
		try {
			address = new Address(street, city, state, zip);
		} catch (InvalidDataException e) {
			System.out.println(e.toString());
		}
		return address;
	}

	public static int getInt(Scanner keyboard, String type) {
		System.out.println("Please enter the " + type + ".");
		int number = keyboard.nextInt();
		keyboard.nextLine();
		return number;
	}

	public static String getString(Scanner keyboard, String type) {
		System.out.println("Please enter the " + type + ".");
		return keyboard.nextLine();
	}

	public static CompletedCourse getCCInfo(Scanner keyboard, Person p) {
		CompletedCourse cc = null;
		String cID = getString(keyboard, "course ID");
		String desc = getString(keyboard, "the course description");
		int numCredits = getInt(keyboard, "the number of credits");
		String dID = getString(keyboard, "department ID");
		String grade = getString(keyboard, "the grade");
		System.out.println("Please enter completed date");
		String[] date = keyboard.nextLine().split("/");
		GregorianCalendar completedDate = new GregorianCalendar((Integer.parseInt(date[2])),
				(Integer.parseInt(date[0])), (Integer.parseInt(date[1])));
		try {
			cc = new CompletedCourse(cID, desc, numCredits, dID, p.getID(), grade, completedDate);
		} catch (InvalidDataException e) {
			System.out.println(e.toString());
		}
		return cc;
	}
}