package queueArray;

//import Queues.QueueException;

public class Queue<T> {

	private QueueLinkedList items;

	// private final int MAX_QUEUE;
	// private T items[];
	// private int front, back, count;

	/*
	 * circular queue rewrites over itself from the beginning when it gets to
	 * the end need to keep track of the front and the back to pop and push.
	 * nothing is bumped from popping- only the pointers move finite amount of
	 * space, which can be reused
	 */

	public Queue() {
		this(5); // arbitrary default amount

	}

	public Queue(int qty) {
		items = new QueueLinkedList<T>();

		// if (qty > 0) {
		// MAX_QUEUE = qty;
		// items = (T[]) new Object[MAX_QUEUE];
		// front = count = 0;
		// back = MAX_QUEUE - 1;

		// } else {
		// throw new InvalidDataException(); // runtime exception bec. should
		// know better
		// }
	}

	public boolean isEmpty() {
		// front = back could be empty or full
		// so have method to test if empty
		// return count == 0;

		return items.isEmpty();

	}

	public boolean isFull() {
		// return count == MAX_QUEUE;
		return false;
	}

	public void enqueue(T newItem) throws QueueFullException {
		// if (!isFull()) {
		// back = (back + 1) % (MAX_QUEUE);
		// items[back] = newItem;
		// ++count;

		// } else
		// throw new QueueFullException();

		items.enqueue(newItem);

	}

	public T dequeue() throws QueueEmptyException {
		// if (!isEmpty()) {
		// queue is not empty , remove first element
		// T queueFront = items[front];
		// front = (front + 1) % (MAX_QUEUE);
		// --count;
		// return queueFront;

		// } else
		// throw new QueueEmptyException();
		return (T) items.dequeue();
	}

	public void dequeueAll() {
		// garbage collector will remove current array
		// items = (T[]) new Object[MAX_QUEUE];
		// front = count = 0;
		// back = MAX_QUEUE - 1;

		items.removeAll();

	}

	// public T peek() throws QueueEmptyException {
	// if (!isEmpty()) {
	// return items[front];
	// } else
	// throw new QueueEmptyException();
	//
	// }
}
