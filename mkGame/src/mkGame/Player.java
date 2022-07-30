package mkGame;

public class Player {
	protected char playerSymbol;
	protected char type;
	public Player(char symbol) {
		playerSymbol = symbol;
	}
	public char getType() {
		return '!';
	}
	public int[] getMove(char[][] board, int boardSize, int winRowSize){
		int[] array = {-1, -1};
		return array;
	}
	char getSymbol() {
		return playerSymbol;
	}
}
