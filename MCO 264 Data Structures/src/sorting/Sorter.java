package sorting;

import java.util.ArrayList;
import java.util.Comparator;

public class Sorter<T extends Comparable<T>> {

	public ArrayList<T> sort(ArrayList<T> collection) {

		// insertion sort
		T value = null;
		for (int i = 1; i < collection.size(); i++) {
			value = collection.get(i);
			int position = i;

			while (position > 0 && value.compareTo(collection.get(position - 1)) < 0) {
				collection.set(position, collection.get(position - 1));
				position--;
			}
			collection.set(position, value);
		}
		return collection;
	}

	public ArrayList<T> sortByComparator(ArrayList<T> collection, Comparator<T> comparator) {
		T value = null;
		for (int i = 1; i < collection.size(); i++) {
			value = collection.get(i);
			int position = i;

			while (position > 0 && comparator.compare(value, collection.get(position - 1)) > 0) {
				collection.set(position, value);
			}
			collection.set(position, value);
		}
		return collection;
	}
}
