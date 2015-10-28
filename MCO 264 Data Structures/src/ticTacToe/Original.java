package ticTacToe;

import java.util.Scanner;

public class Original {

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		char[][] gameBoard = new char[3][3];
		char player1 = 'X';
		char player2 = 'O';

		// user can't enter more input than the array has room for, so start
		// with nested for loop
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				// after each entry, check if there's a winner
				input(gameBoard, player1);
				winner(gameBoard);
				input(gameBoard, player2);
				winner(gameBoard);
			}// end j for
		}// end i for
		System.out.println("You have tied. Exiting game");
		System.exit(0);
		keyboard.close();
	}// end main

	public static void printBoard(char[][] gameBoard) {
		// print out a 3x3 board
		System.out.print("-------------\n");
		for (int row = 0; row < gameBoard.length; row++) {
			for (int column = 0; column < gameBoard[row].length; column++) {
				System.out.print("| " + gameBoard[row][column] + " ");
			}// end column for
			System.out.println("|\n-------------");
		}// end row for
	}// end printBoard

	public static void input(char[][] gameBoard, char player) {
		Scanner keyboard = new Scanner(System.in);
		printBoard(gameBoard);
		int row, column;

		// get the user's choice of row, make sure it exists on the grid
		do {
			System.out.println("Enter a row (0, 1, or 2) for player " + player + ":");
			row = keyboard.nextInt();
		} while (row > (gameBoard.length - 1));
		// get the user's column choice, make sure it exists on the grid
		do {
			System.out.println("Enter a column (0, 1, or 2) for player " + player + ":");
			column = keyboard.nextInt();
		} while (column > (gameBoard[row].length - 1));

		// only assign the player his choice if the position is blank
		if (gameBoard[row][column] == '\u0000') {
			gameBoard[row][column] = player;
		}
		// otherwise the user has to choose again
		else {
			System.out.println("That position has been filled already. Please try another.");
			input(gameBoard, player);
		}
	}// end input

	public static void draw(char[][] gameBoard) {
		// check if there's a draw by going through the whole array to see it
		// all positions are blank
		int row = 0;
		int column = 0;

		while (row < gameBoard.length) {
			while (column < gameBoard[row].length) {
				if (gameBoard[row][column] != '\u0000') {
					if (row == gameBoard.length) {
						printBoard(gameBoard);
						System.out.println(gameBoard[row][column] + " is the winner. Exiting game.");
						System.exit(0);
					} else {
						break;
					}
				}
				column++;
			}
			row++;
		}
	}

	public static void winner(char[][] gameBoard) {
		// first check if there's a draw
		// if not, check for row, column, diagonal
		draw(gameBoard);
		checkDiagonal1(gameBoard);
		checkDiagonal2(gameBoard);
		checkRow(gameBoard);
		checkColumn(gameBoard);

	}// end winner

	public static void checkRow(char[][] gameBoard) {
		// check array for winning row
		// by comparing the [0] value in every row to the [1 ]value in that row
		// and if that matches, to the [2] value
		// going through all the rows in the array
		int row = 0;
		while (row < gameBoard.length) {
			int column = 0;
			while (column < gameBoard[row].length) {
				if (gameBoard[row][column] != '\u0000') {
					if (gameBoard[row][column] == gameBoard[row][column + 1]) {
						if (gameBoard[row][column] == gameBoard[row][column + 2]) {
							printBoard(gameBoard);
							System.out.println(gameBoard[row][column] + " is the winner. Exiting game.");
							System.exit(0);
						}// end if
					}// end if
				}// end if
				break;
			}// end while
			row++;
		}// end while
	}// end checkRow

	public static void checkColumn(char[][] gameBoard) {
		// check array for winning column
		// by comparing the [0] value in every column to the [1 ]value in that
		// column
		// and if that matches, to the [2] value
		// going through all the columns in the array

		// row is used to check column's length
		// and column is used to check row's length
		int column = 0;
		while (column < gameBoard.length) {
			int row = 0;
			while (row < gameBoard[column].length) {
				if (gameBoard[row][column] != '\u0000') {
					if (gameBoard[row][column] == gameBoard[row + 1][column]) {
						if (gameBoard[row][column] == gameBoard[row + 2][column]) {
							printBoard(gameBoard);
							System.out.println(gameBoard[row][column] + " is the winner. Exiting game.");
							System.exit(0);
						}// end if
					}// end if
				}// end if
				break;
			}// end while
			column++;
		}// end while
	}// end checkColumn

	public static void checkDiagonal1(char[][] gameBoard) {
		int row = (gameBoard.length - 1);
		int column = 0;
		while ((gameBoard[row][row] == gameBoard[column][column]) && (gameBoard[row][row] != '\u0000')) {
			if (row == column) {
				if (gameBoard[row][column] == gameBoard[0][0]) {
					printBoard(gameBoard);
					System.out.println(gameBoard[row][row] + " is the winner. Exiting game.");
					System.exit(0);
				}// end if
				else {
					break;
				}
			} else {
				row--;
				column++;
			}
		}
	}

	public static void checkDiagonal2(char[][] gameBoard) {
		int row = (gameBoard.length - 1);
		int column = 0;

		while ((gameBoard[row][column] == gameBoard[column][row]) && (gameBoard[row][column] != '\u0000')) {
			if (row == column) {
				if (gameBoard[row][row] == gameBoard[0][gameBoard.length - 1]) {
					printBoard(gameBoard);
					System.out.println(gameBoard[row][column] + " is the winner. Exiting game.");
					System.exit(0);
				} else {
					break;
				}
			} else {
				row--;
				column++;
			}
		}
	}

}// end class