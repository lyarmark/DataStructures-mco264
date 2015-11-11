package genericBag;

public interface BagInterface<T extends Comparable<T>>{

	void dropIn(T value);

	T takeOut();
	
	T takeOut(T item);

	boolean isEmpty();
	boolean isFull();
	
	void dump();

}
