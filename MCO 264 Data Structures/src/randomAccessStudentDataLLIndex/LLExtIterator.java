package randomAccessStudentDataLLIndex;

import java.util.Iterator;

public class LLExtIterator<T extends Comparable<T>> implements Iterator {
	// Iterator defines the next, hasNext, remove methods

	private Node<T> currentNode;
	private Node<T> head; // for the purpose of resetting

	// constructor
	public LLExtIterator(Node<T> head) {
		this.head = head;
		this.currentNode = head;
	}

	@Override
	public boolean hasNext() {
		if (currentNode != null) {
			return true;
		} else {
			return false;
		}
	}

	// assume programmer checked hasNext() before calling next()
	@Override
	public T next() {
		T data = currentNode.getData();
		currentNode = currentNode.getNext();
		return data;
	}

	public void reset() {
		currentNode = head;
	}

}
