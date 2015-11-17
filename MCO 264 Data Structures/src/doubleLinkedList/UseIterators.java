package doubleLinkedList;

import java.util.Iterator;

public class UseIterators {

	public static void main(String[] args) {
		LinkedList<Integer> names = new LinkedList<Integer>();
		names.add(5);
		names.add(10);
		names.add(15);
		names.add(20);
		names.add(25);
		// when you add these strings, the list will be alphabetical
		// because the LinkedList is sorted

		Iterator<Integer> theIter = names.iter();

		while (theIter.hasNext()) {
			System.out.println(theIter.next());
		}

	}
}
