package stringBag;

import java.util.Random;

public class StringBagADT implements StringBagInterface {

	private String name;
	private String[] bagArray;
	private int lastIndex = -1;

	public StringBagADT(String name, int maxSize) {
		// using an array is more realistic because
		// like a bag, it can hold a limited number of elements
		this.name = name;
		bagArray = new String[maxSize];
	}

	@Override
	public void insert(String word) throws ArrayFullException {
		if (!isFull()) {
			lastIndex++;
			bagArray[lastIndex] = word;
		} else {
			throw new ArrayFullException();
		}
	}

	@Override
	public void clear() {
		this.lastIndex = -1;
		for (int i = 0; i < lastIndex; i++) {
			bagArray[i] = null;
		}
	}

	@Override
	public int size() {
		return this.lastIndex + 1;
	}

	@Override
	public boolean isEmpty() {
		if (this.lastIndex == -1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if (this.lastIndex == (bagArray.length - 1)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String remove() throws ArrayEmptyException {
		if (!isEmpty()) {
			// generate a random int no larger than the last index
			// save the string value at that index to return
			// bump up all elements below the one to remove in order to remove
			// it
			// and keep the array contiguous
			Random ran = new Random();
			int remove = 0;
			if (lastIndex != 0){
				remove = ran.nextInt(lastIndex);
			}
			String s = bagArray[remove];
			for (int i = remove; i < lastIndex; i++) {
				bagArray[i] = bagArray[i + 1];
			}
			bagArray[lastIndex] = null;
			lastIndex--;
			return s;
		} else {
			throw new ArrayEmptyException();
		}
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i <= this.lastIndex; i++) {
			buffer.append("\n" + bagArray[i]);
		}
		return buffer.toString();
	}
}
