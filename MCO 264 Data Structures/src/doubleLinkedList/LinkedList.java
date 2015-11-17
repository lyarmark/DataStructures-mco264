package doubleLinkedList;

import java.io.Serializable;
import java.util.Iterator;

public class LinkedList<T extends Comparable<T> & Serializable> {

	/**	 */
	private static final long serialVersionUID = 1L;

	public ReverseIterator<T> iter;
	private DoubleLinkNode<T> tail;

	public LinkedList() {
		this.tail = null;
		this.iter = new ReverseIterator<T>(tail);
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
	
	public Iterator<T> iter() {
		ReverseIterator<T> iterator = new ReverseIterator<T>(tail);
		return iterator;
	}
}
