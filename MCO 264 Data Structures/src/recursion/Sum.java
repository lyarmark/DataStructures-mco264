package recursion;

import java.util.Random;

public class Sum {

	private int[] numbers;

	public Sum(int[] number) {
		this.numbers = number;

	}

	public int sum(int[] numbers) {
		// assume array is filled to capacity
		// use a loop
		return this.recurSum(numbers, 0);
	}

	private int recurSum(int[] numbers, int position) {
		// use recursion
		if (numbers.length == 0) {
			return 0;
		}
		if (position == numbers.length - 1) {
			return numbers[position];
		} else {
			return this.numbers[position] + this.recurSum(numbers, position+1);
		}
	}

	public static void main(String[] args) {
		int[] numbers = { 5, 10, 15, 20, 25 };
		Sum sum = new Sum(numbers);
		System.out.print(sum.sum(numbers));
	}
}
