package billOrganizer;

import java.io.Serializable;
import java.util.Comparator;

public class SortedLinkedList<T extends Serializable & Comparable<T>> extends LinkedList<T> {

	private static final long serialVersionUID = 1L;

	@Override
	public void insert(T data) {
	}

	public void insert(T data, Comparator<T> comparator) {

	}
	
	public Node<T> find(T data) {}
}
