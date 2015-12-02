package sorting;

import java.util.ArrayList;
import java.util.Collections;

public class StudentElection {
	public static void main(String[] args) {
		ArrayList<Student> students = new ArrayList<Student>();

		students.add(new Student("2", "Rena", "K", 1.0));
		students.add(new Student("3", "Miri", "U", 2.00));
		students.add(new Student("1", "Libby", "J", 0.00));

		//System.out.println(students);

		//Collections.sort(students);

		System.out.println(students);
		
		Sorter<Student> sorter = new Sorter<Student>();
		System.out.println(sorter.sort(students));

		System.out.println(sorter.sortByComparator(students, new StudentBalanceComparator()));
	}
	
}
