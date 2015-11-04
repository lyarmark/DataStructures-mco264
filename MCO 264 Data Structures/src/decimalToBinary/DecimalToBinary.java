package decimalToBinary;

import java.util.Scanner;
import java.util.Stack;

public class DecimalToBinary {
	private Stack<Integer> stack = new Stack<Integer>();
	
	public String decimalToBinary(int decimal) {	

		do {
			stack.push(decimal % 2);
			decimal = decimal / 2;
		} while (decimal > 0);

		StringBuilder binary = new StringBuilder();
		while (!stack.isEmpty()) {
			binary.append(stack.pop());
		}
		return binary.toString();
	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter a decimal number to be converted to binary.");
		int number = keyboard.nextInt();
		DecimalToBinary dtb = new DecimalToBinary();
		System.out.println(dtb.decimalToBinary(number));
	}
}