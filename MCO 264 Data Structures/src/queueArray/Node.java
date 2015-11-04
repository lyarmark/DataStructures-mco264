package queueArray;

public class Node<T> {

	private T data;
	private Node<T> nextNode;

	public Node(T data) {
		this.data = data;
		this.nextNode = null;
	}

	public Node(T data, Node nextNode) {
		this.data = data;
		this.nextNode = nextNode;
	}

	public T getData() {
		return this.data;
	}

	public Node getNextNode() {
		return this.nextNode;
	}

	public void setNext(Node nextNode) {
		this.nextNode = nextNode;
	}

}
