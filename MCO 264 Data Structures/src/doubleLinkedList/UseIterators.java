package doubleLinkedList;

import java.util.Iterator;

public class UseIterators {

	public static void main(String[] args) {

		LinkedList<Integer> numbers = new LinkedList<Integer>();
		
		numbers.add(10);
		numbers.add(5);		
		numbers.add(15);
		numbers.add(25);
		numbers.add(20);
		numbers.add(65);
		numbers.add(1);
		numbers.add(32);
		numbers.add(103);
		numbers.add(2);

		

		ReverseIterator<Integer> theIter = numbers.getIterator();

		while (theIter.hasNext()) {
			System.out.println(theIter.next());
		}
		
		try {
			System.out.println(numbers.find(32));
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			numbers.remove(25);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		theIter.reset();
		while (theIter.hasNext()) {
			System.out.println(theIter.next());
		}
	}
}
