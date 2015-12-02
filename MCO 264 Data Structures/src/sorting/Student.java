package sorting;

public class Student implements Comparable<Student> {
	private String studentID;
	private String firstName;
	private String lastName;
	private Double balance;

	public Student(String id, String first, String last, Double balance) {
		this.studentID = id;
		this.firstName = first;
		this.lastName = last;
		this.balance = balance;
	}

	public int compareTo(Student other) {
		return lastName.compareTo(other.lastName);
	}

	@Override
	public String toString() {
		return "Student [studentID=" + studentID + ", firstName=" + firstName + ", lastName=" + lastName + ", balance="
				+ balance + "]";
	}

	public Double getBalance() {
		return this.balance;
	}

}
