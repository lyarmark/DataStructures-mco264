package billOrganizer;

import java.io.Serializable;

public class LinkedList<T extends Serializable & Comparable<T>> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1826782479863723174L;
	protected Node<T> head;

	public LinkedList() {
		head = null;
	}

	// insert data at the beginning
	// doesn't check for duplicates

	public void insert(T data) throws Exception {
		if (head == null) {
			// this data will be entered into first node
			head = new Node<T>(data);
		} else {
			// insert new node and head of list
			// have the new node point to the previous head of list
			Node<T> aNode = new Node<T>(data);
			aNode.setNext(head);
			head = aNode;
		}
	}

	// remove Node that contains the data specified
	public void remove(T data) throws ListEmptyException, NotFoundException {
		Node<T> currNode;
		Node<T> prevNode;
		if (head == null)
			throw new ListEmptyException();
		else {
			// find the node with the data in it
			// adjust the links so the Node is removed from the chain of Nodes
			currNode = head;
			prevNode = head;
			while (currNode != null) {
				if (currNode.getData().compareTo(data) == 0) {
					// found the data we are looking for
					if (currNode == head) {
						// reset head and you're done!
						head = head.getNext();
						return;

					} else {
						prevNode.setNext(currNode.getNext());
						return;

					}
				} else {
					// didn't find it yet, continue on to next node
					prevNode = currNode;
					currNode = currNode.getNext();
				}

			}
			// done list, didn't find a match
			throw new NotFoundException();
		}

	}

	public void removeAll() {
		head = null;
	}

	public Node<T> getFirst() {
		return head;
	}

	public boolean isEmpty() {
		return (head == null);
	}

	public String toString() {
		Node<T> currNode = head.getNext();
		String info = null;
		if (head == null)
			return " ";
		else
			info = head.getData().toString();
		while (currNode != null) {
			info = info + " " + currNode.getData().toString();
			currNode = currNode.getNext();
		}
		return info;
	}

	public LinkedListIterator<T> iterator() {
		return new LinkedListIterator<T>(head);
	}

}