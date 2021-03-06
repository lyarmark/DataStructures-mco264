package ticTacToe;

import java.util.Scanner;

public class TicTacToe {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		System.out.println("Enter a name for player 1:");
		String name1 = keyboard.next();
		System.out.println("Enter a symbol for " + name1 + ":");
		char symbol1 = keyboard.next().charAt(0);

		System.out.println("Enter a name for player 2:");
		String name2 = keyboard.next();
		System.out.println("Enter a symbol for " + name2);
		char symbol2 = keyboard.next().charAt(0);

		// tic tac toe by definition is a 3x3 board
		// for the purpose of code reuse, i will endeavor to make the methods
		// work for square boards of varying sizes
		// System.out.println("Enter a number for the board size:");
		int size = 3;

		Game newGame = new Game(name1, symbol1, name2, symbol2, size);
		newGame.playGame();
	}
}