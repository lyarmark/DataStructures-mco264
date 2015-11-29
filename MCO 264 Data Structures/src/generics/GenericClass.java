package generics;

import java.util.ArrayList;

// if you make a generic class that assumes you can use certain methods,
// make sure to implement an interface that includes that method
// ex: if sorting, implement Comparable

//if this did not use generics, you would have to define the many of the same method with different parameter types (overloading)
//you can only use this generic method because of extends/implements will guarentee that it will work
public class GenericClass {

	// this class is not generic of type <T>
	// rather, it has a generic method

	// T means anything

	// <T> means every time you see this method, a type T will be defined, which
	// extends comparable

	// it returns an object of type T (the greater one when you compare)
	// and takes is 2 T parameters

	// this uses extends because it IS a comparable, by definition of
	// implementing Comparable
	// not that T itself implements Comparable, but T can be compared

	public <T extends Comparable<T>> T max(T val1, T val2) {

		int result = val1.compareTo(val2);

		if (result > 0) {
			return val1;

		} else if (result < 0) {
			return val2;
		}

		else {
			// if both same, return null
			return null;
		}
	}

	public static void main(String[] args) {
		String name1 = "Hello";
		String name2 = "Goodbye";
		GenericClass generic = new GenericClass();
		// based on the type you send to the method, the computer knows which
		// data type it is
		System.out.println(generic.max(name1, name2));

		// should only send in reference types, not primitive
		Integer i1 = 2;
		Integer i2 = 3;
		System.out.println(generic.max(i1, i2));

		Book a = new Book("Curious George", 20.0);
		Book b = new Book("Amelia Bedila", 15.0);

		// need to ensure that books are comparable with implement. even if it
		// already has a compareTo
		System.out.println(generic.max(a, b).toString());
	}
}