package billOrganizer;

import java.io.Serializable;
import java.util.Comparator;

public class SortedLinkedList<T extends Serializable & Comparable<T>> extends LinkedList<T> {

	private static final long serialVersionUID = 1L;

	@Override
	public void insert(T data) throws DuplicateDataException {
		Node<T> newNode = new Node<T>(data);
		if (this.head == null) {
			this.head = newNode;
		} else {
			Node<T> previous, current;
			previous = current = this.head;
			while (current.getNext() != null) {
				if (data.compareTo(current.getData()) == 0) {
					throw new DuplicateDataException();
				} else if (data.compareTo(current.getData()) > 0) {
					newNode.setNext(current);
					previous.setNext(newNode);
				} else {
					previous = current;
					current = current.getNext();
				}
			}
		}
	}

	public void insert(T data, Comparator<T> comparator) {
		Node<T> newNode = new Node<T>(data);
		if (this.head == null) {
			this.head = newNode;
		}
		Node<T> previous, current;
		previous = current = this.head;
		while (current.getData() != null) {
			if (comparator.compare(data, current.getData()) > 0) {
				newNode.setNext(current);
				previous.setNext(newNode);
			}
		}
	}

	public Node<T> find(T data) throws NotFoundException {
		if (this.head == null) {
			return null;
		}
		Node<T> current = this.head;
		while (current.getNext() != null) {
			if (current.getData().equals(data)) {
				return current;
			}
		}
		throw new NotFoundException();
	}
}
