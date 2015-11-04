package queueArray;

public class QueueLinkedList<T> {

	private Node<T> head;
	private Node<T> tail;

	public QueueLinkedList() {
		head = tail = null;
	}

	public QueueLinkedList(Node n) {
		this.head = n;
	}

	public void enqueue(T data) {
		// add data to the end of the list
		Node<T> newNode = new Node(data);
		if (this.isEmpty()) {
			this.head = tail = newNode;

		} else {
			// step1: hook together
			// step2: set new node
			this.tail.setNext(newNode);
			this.tail = newNode;
		}
	}

	public T dequeue() {
		// remove data from the beginning of the list
		if (!isEmpty()) {
			T data2 = this.head.getData();
			this.head = head.getNextNode();
			
			if (head == null) {
				tail = null;
			}
			
			return data2;
		}
		throw new QueueEmptyException();
	}

	public boolean isEmpty() {
		if (head == null) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		QueueLinkedList<String> names = new QueueLinkedList<String>();
		names.enqueue("Mickey Mouse");
		names.enqueue("Minnie Mouse");
		names.enqueue("Donald Duck");
		names.enqueue("Walt Disney");
		names.enqueue("Mary Poppins");
		while (!names.isEmpty()) {
			System.out.println(names.dequeue());
		}
		names.enqueue("Pluto");
	}
	
	public void removeAll() {
		head = tail = null;
	}

}
