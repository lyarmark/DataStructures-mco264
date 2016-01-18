package billOrganizer;

import java.io.Serializable;
import java.util.Comparator;

public class SortedLinkedList<T extends Serializable & Comparable<T>> extends LinkedList<T> {

	private static final long serialVersionUID = 1L;

	@Override
	public void insert(T data) throws DuplicateDataException {
		Node<T> newNode = new Node<T>(data);
		// newNode is head
		if (this.head == null || data.compareTo(head.getData()) < 0) {
			newNode.setNext(head);
			this.head = newNode;
		} else {
			Node<T> previous, current;
			previous = current = this.head;
			while (current != null) {
				// newNode is =
				if (data.compareTo(current.getData()) == 0) {
					throw new DuplicateDataException();
					// newNode is greater
				} else if (data.compareTo(current.getData()) < 0) {
					newNode.setNext(current);
					previous.setNext(newNode);
				} else if (current.getNext() == null) {
					current.setNext(newNode);
					current = newNode.getNext();
				} else {
					previous = current;
					current = current.getNext();
				}
			}
		}
	}

	public void insert(T data, Comparator<T> comparator) {
		Node<T> newNode = new Node<T>(data);
		if (this.head == null || comparator.compare(data, head.getData()) <= 0) {
			newNode.setNext(head);
			this.head = newNode;
		} else {
			Node<T> previous, current;
			previous = current = this.head;
			while (current != null) {
				if (comparator.compare(data, current.getData()) < 0) {
					newNode.setNext(current);
					previous.setNext(newNode);
					current = null; // data has been inserted
				} else if (current.getNext() == null) {
					current.setNext(newNode);
					current = null; // data has been inserted
				} else {
					previous = current;
					current = current.getNext();
				}
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
