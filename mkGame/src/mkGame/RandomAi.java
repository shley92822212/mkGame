package mkGame;
import java.util.Random;
import mkGame.Board;
import mkGame.MKGame;
import mkGame.Player;

public class RandomAi extends Player {

	private char type = 'c';


	public RandomAi(char symbol) {
		super(symbol);
	}
	public char getType() {
		return type;
	}
	public char getSymbol() {
		return playerSymbol;
	}
	public int[] getMove(char[][] board, int boardSize, int winRowSize) {
		int row = 0;
		int col = 0;
		Random rand = new Random();
		row = rand.nextInt(boardSize);
		col = rand.nextInt(boardSize);
		int[] arr = {row, col};
		return arr;
	}
}