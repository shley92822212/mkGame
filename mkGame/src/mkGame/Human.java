package mkGame;
import mkGame.Player;
import java.util.Scanner;

public class Human extends Player{
	private char type = 'h';
	public Human(char symbol) {
		super(symbol);
	}
	public char getAi() {
		return 'b';
	}
	public char getSymbol() {
		return playerSymbol;
	}
	public char getType() {
		return type;
	}
	public int[] getMove(char[][] board, int boardSize, int winRowSize) {
		int row = 0;
		int col = 0;

		System.out.print("Player " + playerSymbol + " enter move: ");
		Scanner reader = new Scanner(System.in); 
		row = reader.nextInt();
		col = reader.nextInt();

		System.out.print("\n");
		row--;
		col--;
		int[] arr = {row, col};
		return arr;
	}
}