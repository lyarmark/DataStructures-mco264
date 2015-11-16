package doubleLinkedList;

import java.io.Serializable;

public class LinkedList<T extends Comparable<T> & Serializable> {

	/**	 */
	private static final long serialVersionUID = 1L;

	public LinkedListIterator<T> iter;
	private DoubleLinkNode<T> tail;

	public LinkedList() {
		this.tail = null;
		this.iter = new LinkedListIterator<T>(tail);
	}

	public void add(T data) {
		DoubleLinkNode<T> currentNode;
		DoubleLinkNode<T> prevNode;
		if (tail == null) {
			tail = new DoubleLinkNode<T>(data);
		}
		else {			
			DoubleLinkNode<T> newNode = new DoubleLinkNode<T>(data);
			currentNode = prevNode = tail;
			while(currentNode != null && data.compareTo(currentNode.getData()) < 0) {
				prevNode = currentNode;
				currentNode = currentNode.getNext();
			}
			if (currentNode == tail) {
				newNode.setNext(tail);
				tail = newNode;
			} else {
				prevNode.setNext(newNode);
				newNode.setNext(currentNode);
			}
			
		}
	}
}
