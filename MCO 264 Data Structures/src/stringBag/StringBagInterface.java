package stringBag;

public interface StringBagInterface {

	// insert string into array
	void insert(String word) throws ArrayFullException;

	// empty array
	void clear();

	// size
	public int size();

	// is Empty
	boolean isEmpty();

	// isFull
	boolean isFull();

	// get name of array
	String getName();

	// to string
	@Override
	String toString();

	// remove if array not empty
	String remove() throws ArrayEmptyException;
}
