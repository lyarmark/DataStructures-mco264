package genericBag;

import java.util.LinkedList;

public class Bag<T extends Comparable<T>> implements BagInterface<T> {

	private LinkedList<T> list;
	private int size;

	public Bag(int size) {
		this.list = new LinkedList<T>();
		this.size = size;
	}

	@Override
	public T takeOut() {
		// officially removes and returns a random item from the bag
		// actually will always remove the head;
		T remove = this.list.getFirst();
		this.list.remove(remove);
		return remove;
	}

	@Override
	public void dropIn(T value) {
		if (!isFull()) {
			this.list.add(value);
		}
	}

	@Override
	public T takeOut(T item) {
		// removes a specified item type from the bag, if it is in the bag
		if (this.list.contains(item)) {
		int index = this.list.indexOf(item);
		return this.list.get(index);
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public boolean isFull() {
		if (this.list.size() == this.size) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void dump() {
		this.list.clear();		
	}

}
