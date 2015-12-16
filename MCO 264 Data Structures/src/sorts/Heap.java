package sorts;

import java.util.Vector;
import java.util.Iterator;

public class Heap<T extends Comparable<T>> {
	private Vector<T> heap;
	private boolean sorted;
	private int last;
	private int size;

	public Heap() {
		heap = new Vector<T>(); // create an instance of a Vector
		sorted = false;
		last = 0;
		size = 0;
	}

	public void add(T nextVal) throws RuntimeException {
		T temp;
		// inserts new item after last item in the heap
		// Bubbles it up to its proper position
		// can not add a value to a sorted Heap
		if (sorted)
			throw new RuntimeException("heap is sorted, can't add value");
		// insert value at last position in the vector
		heap.add(size, nextVal);

		// now bubble it up to its correct position
		int place = size; // current position
		int parent = (place - 1) / 2; // simulate a tree using a Vector
		// keep searching until you exhaust the array
		// as long as the value of the parent of this new value is not larger
		// than the value that was just add ed
		while (parent >= 0 && heap.elementAt(place).compareTo(heap.elementAt(parent)) > 0) {
			// swap heap[place] with heap[parent] since it isn't in the correct
			// order
			temp = heap.elementAt(parent);
			heap.set(parent, heap.elementAt(place));
			heap.set(place, temp);
			// now start checking heap from the next higher postion
			place = parent;
			parent = (place - 1) / 2;
		} // end while
		size++; // successfully added a new item
		last = size - 1; // none of the heap is sorted

	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	public T remove() throws RuntimeException {
		// removes and returns the element with the largest value
		if (isEmpty())
			throw new RuntimeException("heap is empty, nothing to remove");
		T temp = heap.elementAt(0); // references the object at top of the heap
		--size; // reduces size of the heap
		heap.set(0, heap.elementAt(size)); // places last value of heap, at
											// "root" of heap
		last = size - 1; // heap must be resorted, it is about to be rebuilt
		rebuildHeap(0);
		return temp;

	}

	private void rebuildHeap(int root) {
		T temp;
		// continue this process if
		// a. root is not a leaf
		// b. root's value is less than the larger of its children

		int child = 2 * root + 1; // index of root's left child, it is exists

		if (child <= last) {
			// if has a left child , so it isn't a leaf
			int rChild = child + 1;

			// check if rChild exists
			// if rChild exists, checks which value is larger, lChild or rChild

			if ((rChild <= last) && heap.elementAt(rChild).compareTo(heap.elementAt(child)) > 0)
				child = rChild; // index of larger of the children

			// if the current root's value is smaller than the value in the
			// larger child,
			// swap the values
			if (heap.elementAt(root).compareTo(heap.elementAt(child)) < 0) { // swap
																				// the
																				// values
				temp = heap.elementAt(child);
				heap.set(child, heap.elementAt(root));
				heap.set(root, temp);

				// transform the new subtree into a heap
				// this is a recursive call
				rebuildHeap(child);

			}
		}

	}

	public void sort() {
		T temp;
		if (!sorted) {
			// begin sort process
			// this is an "in place" sort , uses same memory locations
			// last - is used to divide sorted and unsorted sections
			last = size - 1;
			for (int step = 1; step < size; step++) {
				// swap the first and last values
				// we know that the first value is the largest value in the data
				// set
				temp = heap.elementAt(last);
				heap.set(last, heap.elementAt(0));
				heap.set(0, temp);

				--last; // sorted part is expanding, heap part shrinking
				// make the heap back into a heap
				rebuildHeap(0);
			}
		}
	}

	public void display() {
		Iterator<T> iter = heap.iterator();
		StringBuffer results = new StringBuffer();
		System.out.println("heap contains: ");
		while (iter.hasNext()) {
			results.append(iter.next().toString());
			results.append(" ");

		}
		System.out.println(results);
	}
}