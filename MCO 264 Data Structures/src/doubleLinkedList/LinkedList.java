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
				newNode.setNext(currentNode.getNext());
				currentNode.setNext(newNode);
				currentNode = newNode;
			}
			if (currentNode == head) {
				newNode.setNext(head);
				head = newNode;
			} else {
				newNode.setNext(currentNode.getNext());
				currentNode.setNext(newNode);
			}
			
		}
	}
	
	public T find() {}
	public void remove(T data) {}
	
	public Iterator<T> iter() {
		ReverseIterator<T> iterator = new ReverseIterator<T>(head);
		return iterator;
	}
}
