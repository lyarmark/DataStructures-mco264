package decimalToBinary;

import java.util.Scanner;
import java.util.Stack;

public class DecimalToBinary {

	public static String decimalToBinary(int decimal) {
		Stack <Integer> stack = new Stack<Integer>();
		
		do {
			stack.push(decimal % 2);
		decimal = (decimal - (decimal % 2)) / 2;	
		}  while (decimal > 0);
		
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
		System.out.println(decimalToBinary(number));
	}
}
