package doubleLinkedList;

import java.io.Serializable;
import java.util.Iterator;

public class LinkedList<T extends Comparable<T> & Serializable> {

	/**	 */
	private static final long serialVersionUID = 1L;

	public ReverseIterator<T> iterator;
	private DoubleLinkNode<T> head;
	private DoubleLinkNode<T> tail;

	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.iterator = new ReverseIterator<T>(tail);
	}

	public void add(T data) {

		DoubleLinkNode<T> newNode = new DoubleLinkNode<T>(data);
		DoubleLinkNode<T> currentNode = head;

		if (head == null) {
			this.head = this.tail = new DoubleLinkNode<T>(data);
		}

		else if (data.compareTo(currentNode.getData()) < 0) {
			newNode.setNext(head);
			head.setPrev(newNode);
			this.head = newNode;

		} else {
			while (currentNode != null) {
				if (data.compareTo(currentNode.getData()) > 0) {
					if (currentNode.getNext() != null) {
						currentNode = currentNode.getNext();
					}
					else {
						break;
					}
				} else {
					currentNode.getPrev().setNext(newNode);
					newNode.setPrev(currentNode.getPrev());
					newNode.setNext(currentNode);
					currentNode.setPrev(newNode);
					break;
				}
			}
			
			if (newNode.getNext() == null){
				currentNode.setNext(newNode);
				newNode.setPrev(currentNode);
				tail = newNode;
			}
		}
	}

	public T find(T data) throws NotFoundException {
		DoubleLinkNode<T> current = head;
		while (current != null) {
			if (current.getData().equals(data)) {
				return current.getData();
			}
			current = current.getNext();
		}
		throw new NotFoundException();
	}

	public void remove(T data) throws NotFoundException {
		DoubleLinkNode<T> current = head;
		boolean found = false;
		while (current != null) {
			if (current.getData().equals(data)) {
				current.getPrev().setNext(current.getNext());
				found = true;
				break;
			}
			current = current.getNext();
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	public ReverseIterator<T> getIterator() {
		ReverseIterator<T> iterator = new ReverseIterator<T>(tail);
		return iterator;
	}
}
