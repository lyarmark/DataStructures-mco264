package recursion;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {

	private int[] numbers;

	public BinarySearch(int size) {
		this.numbers = new int[size];
		Random gen = new Random(); // for testing purposes

		// fill array
		for (int i = 0; i < size; i++) {
			numbers[i] = gen.nextInt(10);
		}

		Arrays.sort(numbers);

	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int value : numbers) {
			builder.append(value + " ");
		}
		return builder.toString();
	}

	public int search(int value) {
		return this.binSearch(value, 0, numbers.length-1);
	}

	private int binSearch(int value, int start, int end) {
		// no list- anchor
		if (end < start) {
			return -1;
		}

		int mid = (end + start) / 2;

		if (numbers[mid] == value) {
			return mid;
		}

		else if (numbers[mid] > value) {
			return this.binSearch(value, start, mid - 1);

		} else { // (numbers[mid] < value)
			return this.binSearch(value, mid + 1, end);
		}
	}

	public static void main(String[] args) {
		BinarySearch bin = new BinarySearch(4);
		int index = bin.search(5);
		System.out.println(index > 0 ? index : "not found");
	}
}
