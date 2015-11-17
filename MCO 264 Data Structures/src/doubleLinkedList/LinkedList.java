package doubleLinkedList;

import java.io.Serializable;
import java.util.Iterator;

public class LinkedList<T extends Comparable<T> & Serializable> {

	/**	 */
	private static final long serialVersionUID = 1L;

	public ReverseIterator<T> iter;
	private DoubleLinkNode<T> head;

	public LinkedList() {
		this.head = null;
		this.iter = new ReverseIterator<T>(head);
	}

	public void add(T data) {
		DoubleLinkNode<T> currentNode;

		if (head == null) {
			head = new DoubleLinkNode<T>(data);
		}
		else {			
			DoubleLinkNode<T> newNode = new DoubleLinkNode<T>(data);
			currentNode = head;
			while(currentNode != null && data.compareTo(currentNode.getData()) > 0) {
				currentNode.setPrev(currentNode);
				currentNode = currentNode.getNext();
			}
			if (currentNode == head) {
				newNode.setNext(head);
				head = newNode;
			} else {
				currentNode.getPrev().setNext(newNode);
				newNode.setNext(currentNode);
			}
			
		}
	}
	
	public Iterator<T> iter() {
		ReverseIterator<T> iterator = new ReverseIterator<T>(head);
		return iterator;
	}
}
