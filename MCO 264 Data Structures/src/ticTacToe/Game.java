package ticTacToe;

import java.util.Scanner;

public class Game {

	private Player playerA;
	private Player playerB;
	private Board board;
	private Player currentPlayer;

	public Game(String name1, char symbol1, String name2, char symbol2, int size) {
		this.playerA = new Player(name1, symbol1);
		this.playerB = new Player(name2, symbol2);
		this.board = new Board(size);
		this.currentPlayer = this.playerA;
	}

	public void playGame() {
		System.out.println(this.board.toString());
		int[] nextMove = this.nextMove();
		boolean status = this.updateBoard(nextMove, this.currentPlayer.getSymbol());
		// board has updated because no win or draw
		while (status) {
			if (this.board.draw()) {
				System.out.println("Draw- Game Over!\n Exiting ... have a nice day!");
				System.exit(0);
			}
			this.playGame();
		}
		this.board.toString();
		this.currentPlayer = takeTurns(this.currentPlayer);
		System.out.println(this.currentPlayer.getName() + " has won the game! Exiting ... have a nice day!");
		System.exit(0);
	}

	public boolean updateBoard(int[] nextMove, char symbol) {
		this.currentPlayer = takeTurns(this.currentPlayer);
		return this.board.updateBoard(nextMove, symbol);
	}

	public int[] nextMove() {
		Scanner keyboard = new Scanner(System.in);
		int[] move = new int[2];
		do {
			System.out.println(this.currentPlayer.getName() + "'s turn:");
			System.out.println("Enter the row of your next move:");
			move[0] = keyboard.nextInt();
			System.out.println("Enter the column of your next move:");
			move[1] = keyboard.nextInt();
		} while (!this.board.moveIsValid(move));
		return move;
	}

	public Player takeTurns(Player p) {
		if (p.equals(this.playerA)) {
			return this.playerB;
		} else {
			return this.playerA;
		}
	}
}