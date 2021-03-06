package ticTacToe;

public class Board {

	private char[][] board;

	public Board(int size) {

		this.board = new char[size][size];

	}

	public boolean updateBoard(int[] move, char symbol) {
		this.board[move[0]][move[1]] = symbol;
		if (this.winner(move, symbol)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean winner(int[] move, char symbol) {
		// check column
		for (int row = 0; row < this.board.length; row++) {
			if (board[row][move[1]] != symbol) {
				break;
			}
			if (row == (this.board.length - 1)) {
				return true;
			}
		}

		// check row
		for (int column = 0; column < this.board[move[0]].length; column++) {
			if (board[move[0]][column] != symbol) {
				break;
			}
			if (column == (this.board[move[0]].length - 1)) {
				return true;
			}
		}

		// check diagonal if the move was made on a diagonal
		if (move[0] == move[1]) {
			for (int i = 0; i < this.board.length; i++) {
				if (board[i][i] != symbol) {
					break;
				}
				if (i == (board[i].length - 1)) {
					return true;
				}
			}
		}

		// check other diagonal
		// HOW TO RESTRICT ACCESS TO THIS LOOP BASED ON LAST MOVE?
		for (int i = 0; i < this.board.length; i++) {
			if (board[i][(this.board.length - 1) - i] != symbol) {
				break;
			}
			if (i == (this.board.length - 1)) {
				return true;
			}
		}
		return false;
	}

	public boolean draw() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == '\u0000') {
					return false;
				}
			}
		}
		return true;
	}

	public boolean moveIsValid(int[] validate) {
		if ((validate[0] >= 0) && (validate[0] < this.board.length) && (validate[1] >= 0)
				&& (validate[1] < this.board.length)) {
			if (this.board[validate[0]][validate[1]] == '\u0000') {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		System.out.print("-------------\n");
		for (int row = 0; row < this.board.length; row++) {
			for (int column = 0; column < this.board[row].length; column++) {
				System.out.print("| " + this.board[row][column] + " ");
			}// end column for
			System.out.println("|\n-------------");
		}// end row for
		return buffer.toString();
	}
}