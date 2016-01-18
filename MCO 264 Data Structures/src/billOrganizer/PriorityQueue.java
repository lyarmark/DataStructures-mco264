package billOrganizer;

import java.io.Serializable;
import java.util.Comparator;

public class PriorityQueue<T extends Serializable & Comparable<T>> {
	private SortedLinkedList<T> list;
	private Comparator<T> comparator;

	public PriorityQueue(BillCriteria criteria) {
		this.list = new SortedLinkedList<T>();
		this.setComparatorType(criteria);
	}

	public void enqueue(T data) {
		list.insert(data, this.comparator);
	}

	public T dequeue() throws ListEmptyException, NotFoundException {
		Node<T> head = this.list.head;
		if (head != null) {
			this.list.remove(head.getData());
			return head.getData();
		} else {
			throw new ListEmptyException();
		}
	}

	public Node<T> peek() {
		return this.list.head;
	}

	public void remove(T data) throws ListEmptyException, NotFoundException {
		list.remove(data);
	}

	private void setComparatorType(BillCriteria criteria) {
		if (criteria.equals(BillCriteria.BILLAMOUNT)) {
			this.comparator = (Comparator<T>) new ComparatorBillAmount();
		} else if (criteria.equals(BillCriteria.BILLDUEDATE)) {
			this.comparator = (Comparator<T>) new ComparatorBillDate();
		} else if (criteria.equals(BillCriteria.BILLTYPE)) {
			this.comparator = (Comparator<T>) new ComparatorBillType();
		}
	}

	public SortedLinkedList<T> getList() {
		return this.list;
	}
}
