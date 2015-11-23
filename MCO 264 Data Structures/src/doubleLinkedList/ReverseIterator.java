package doubleLinkedList;

import java.io.Serializable;
import java.util.Iterator;

public class ReverseIterator<T extends Comparable<T> & Serializable> implements Iterator<T> {

	private DoubleLinkNode<T> current;
	private DoubleLinkNode<T> tail;

	public ReverseIterator(DoubleLinkNode<T> tail) {
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
		current = current.prev;
		return data;
	}

	public void reset() {
		this.current = tail;
	}
}
