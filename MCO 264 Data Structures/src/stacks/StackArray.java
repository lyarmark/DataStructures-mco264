package stacks;

//a stack job is LIFO
//stacks are good a backtracking ex: stacktrace/stackoverflow

//StackArray is a class which shows the functions of a Stack using an Array
public class StackArray<E> {
	// user cannot have direct access- variables are private

	// store an Array of type E
	// E will be defined later- its an object type
	private int top;
	private E[] values;

	public StackArray() {

		// top = 0 is used to store
		// so cant be used to test empty
		// use -1 as size for empty array
		top = -1;

		// instantiate Array of unknown object like below
		// define the fact the E extends Object
		// and cast the object type as E so only E's will be in the array
		values = (E[]) new Object[10];
		// we have a fullException and emptyException if you try to add/get when
		// not possible
	}

	// ignore this?
	public StackArray(StackArray<E> stack) {
		// copy constructor
		this.top = stack.top;
		values = (E[]) new Object[stack.values.length];
		for (int i = 0; i < top; i++) {
			values[i] = stack.values[i];
		}
	}

	// for user defined size array
	public StackArray(int qty) {
		top = -1;
		values = (E[]) new Object[qty];
	}

	// since user doesn't have direct access, pass in the value
	public void push(E value) {
		if (!isFull()) {
			values[++top] = value;
		}
	}

	// take top object off stack and decrement stack size
	public void pop() {
		if (!isEmpty()) {
			top--;

		} else {
			throw new EmptyStackException();
		}
	}

	// check the value of the top object
	public E peek() {
		if (!isEmpty()) {
			return values[top];
		} else {
			return null;
		}
	}

	public boolean isEmpty() {
		if (top == -1) {
			return true;
		} else {
			return false;
		}

	}

	public boolean isFull() {
		if (top == values.length) {
			return true;
		} else {
			return false;
		}
	}

	// this is for teaching purposes
	// you should never iterate through array because that's LIFO is the point
	public StackIterator<E> iterator() {

		return new StackIterator<E>(values, top);
	}

}
