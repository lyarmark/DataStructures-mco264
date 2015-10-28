package randomAccessStudentDataLLIndex;

import java.util.Iterator;

public class UseIterators {

	public static void main(String[] args) {
		LinkedList<String> names = new LinkedList<String>();
		names.add("Libby");
		names.add("Rena");
		names.add("Ahuva");
		// when you add these strings, the list will be alphabetical
		// because the LinkedList is sorted

		// iter is the internal iterator from the LinkedList
		names.iter.reset();

		while (names.iter.hasNext()) {
			System.out.println(names.iter.next());
		}

		Iterator<String> theIter = names.iterator();

		while (theIter.hasNext()) {
			System.out.println(theIter.next());
		}

	}
}
