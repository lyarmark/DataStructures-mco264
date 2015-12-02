package sorting;

import java.util.Comparator;

//this is for sorting different than natural ordering
//the natural sort is defined in the Student class ex: alphabetical.
//to compare 2 students any other way, need to declare a new Comparator
//Comparator is an interface that must have a compare() method
//which accepts 2 students
public class StudentBalanceComparator implements Comparator<Student> {

	public int compare(Student one, Student two) {
		return one.getBalance().compareTo(two.getBalance());
	}
}
