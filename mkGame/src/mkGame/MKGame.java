package mkGame;
import mkGame.Player;
import mkGame.Board;
public class MKGame {
	private Player playerX;
	private Player playerO;
	private Board gameBoard;
	public MKGame(Player player1, Player player2, Board board) {
		playerX = player1;
		playerO = player2;
		gameBoard = new Board(board);
	}
	void play() {
		boolean done = false;
		Player player = playerX;

		gameBoard.displayBoard();

		while (!done)
		{
			int row;
			int col;
			do {
				int[] moveArray = new int[2];  

				moveArray = player.getMove(gameBoard.getBoard(), gameBoard.getBoardSize(), gameBoard.getWinRow());
				
				row = moveArray[0];
				col = moveArray[1];
			} while (!gameBoard.isValidMove(row, col, player.getType()));
			done = gameBoard.playMove(player.getSymbol(), row, col);
			if (player.getSymbol() == 'X')
				player = playerO;
			else
				player = playerX;
		}
	}
}
