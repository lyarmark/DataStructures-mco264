package billOrganizer;

import java.io.Serializable;

public class LinkedListIterator<T extends Comparable<T>> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Node<T> current;
	private Node<T> head;

	public LinkedListIterator(Node<T> head) {
		current = head;
		this.head = head;
	}

	public void reset() {
		current = head;
	}

	public boolean hasNext() {
		if (current != null)
			return true;
		else
			return false;

	}

	public T next() {
		T temp = current.getData();
		current = current.getNext();
		return temp;
	}
}