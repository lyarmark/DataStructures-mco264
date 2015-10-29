package stacks;

import java.util.LinkedList;

public class StackLL<T> {

	// using a linked list stack wraps a linked list in a stack
	// and prevents the user from using the Linked List methods
	private LinkedList<T> values;

	public StackLL() {
		values = new LinkedList<T>();
	}

	// since user doesn't have direct access, pass in the value
	public void push(T value) {
		this.values.add(value);
	}

	// take top object off stack and decrement stack size
	public void pop() {
		if (!isEmpty()) {
			values.remove(values.getLast());
		} else {
			throw new EmptyStackException();
		}
	}

	// check the value of the top object
	public T peek() {
		T value = this.values.getLast();
		return value;
	}

	public boolean isEmpty() {
		return values.isEmpty();
	}
}
