package doubleLinkedList;

import java.io.Serializable;
import java.util.Iterator;

public class LinkedListIterator<T extends Comparable<T> & Serializable> implements Iterator<T> {

	private DoubleLinkNode<T> current;
	private DoubleLinkNode<T> tail;

	public LinkedListIterator(DoubleLinkNode<T> tail) {
		this.tail = tail;
		this.current = tail;
	}

	@Override
	public boolean hasNext() {
		if (current != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public T next() {
		T data = current.getData();
		current = current.next;
		return data;
	}

}
