package randomAccessStudentDataLLIndex;

import java.io.Serializable;

//keeps a reference to a specific data
//keeps a reference to the next Node in the linked list
public class Node<T extends Comparable<T>> implements Serializable {

	// define the comparable with any letter. ex: T for type, E for element ...
	// T has to be a type that implements comparable
	// because node implements the comparable interface of type T
	// comparable is an interface, so extends comparable should really be
	// implements comparable
	// we want the index to be storable as an object- so it implements
	// serializable
	// data types that do not implement serializable must be transient

	private T data;
	// instance variable that will access the next node
	private Node<T> nextNode;

	public Node(T data) {
		this.data = data;
		// self accessing-
		// this node has access to the next node
		// but makes it null because don't yet know where
		this.nextNode = null;
	}

	public Node(T data, Node<T> nextNode) {
		this.data = data;
		this.nextNode = nextNode;
	}

	public void setNext(Node<T> nextNode) {
		this.nextNode = nextNode;
	}

	public Node<T> getNext() {
		return this.nextNode;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return this.data;
	}

	public int compareTo(Node<T> otherNode) {
		return this.getData().compareTo(otherNode.getData());
	}
}
