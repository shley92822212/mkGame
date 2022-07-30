package mkGame;
import java.util.Random;
enum status { WIN, DRAW, CONTINUE };

public class Board {
	
	private int winRowSize;
	private char board[][];
	private int boardSize;
	public Board(int size, int winSize) {
		boardSize = size;
		board = new char[size][size];
		winRowSize = winSize;
		fillBoard(size);
	}
	public Board(Board obj){
		winRowSize = obj.winRowSize;
		boardSize = obj.boardSize;
		board = new char[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				board[i][j] = obj.board[i][j];
			}
		}
	}
	public int getWinRow() {
		return winRowSize;
	}
	public int getBoardSize() {
		return boardSize;
	}
	public char getBoardSize(int row, int col) {
		return board[row][col];
	}
	public char[][] getBoard(){
		return board;
	}
	public void fillBoard(int size) {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				board[i][j] = ' ';
			}
		}
	}
	void displayBoard() {
		System.out.print("  ");
		for (int col = 0; col < boardSize; col++) {
			if (col + 1 < 10) {
				System.out.print("  " + (col + 1) + "   ");
			}
			else {
				System.out.print(" " + (col + 1) + "   ");
			}
		}
		System.out.print("\n");
		System.out.print("    ");
		for (int col = 0;col != boardSize - 1; col++) {
			System.out.print("   |  ");
		}
		System.out.print("\n");

		for (int row = 0; row < boardSize; row++)
		{

			System.out.print(row + 1);
			if (row + 1 < 10) {
				System.out.print("   ");
			}
			else {
				System.out.print("  ");
			}
			for (int col = 0; col < boardSize; col++)
			{
				System.out.print(board[row][col]);
				if (col != boardSize - 1)
					System.out.print("  |  ");
			}
			System.out.print("\n");
			if (row != boardSize - 1) {
				System.out.print("  ");
				for (int col = 0;col != boardSize - 1; col++) {
					System.out.print("_____|");
					if (col == boardSize - 2) {
						System.out.print("_____");
					}
				}
				System.out.print("\n");
				
			}
			if (row != boardSize - 1) {
				System.out.print("    ");
				for (int col = 0;col != boardSize - 1; col++) {
					System.out.print("   |  ");
				}
				System.out.print("\n");
				//System.lineSeparator();
			}

		}
		System.out.print("    ");
		for (int col = 0;col != boardSize - 1; col++) {
			System.out.print("   |  ");
		}
		System.out.print("\n");
		System.out.print("\n");
	}
	boolean playMove(char playerSymbol, int row, int col) {
		board[row][col] = playerSymbol;
		
		displayBoard();
		status gStatus;
		if(playerSymbol == ' ') {
			gStatus = status.CONTINUE;
		}else {
			gStatus = gameStatus(playerSymbol);
		}
		if(gStatus == status.WIN) {
			System.out.println("Player " + playerSymbol + " wins!");
			return true;
		}else if (gStatus == status.DRAW) {
			System.out.println("This game is a draw!");
			return true;
		}
		return false;
	}
	boolean isValidMove(int row, int col, char type) {
		if (row >= 0 && row <= boardSize && col >= 0 && col <= boardSize) {
			if (board[row][col] == ' ') {
				return true;
			}
		}
		else
			if (type == 'h') {
				System.out.println("Please select a valid move");
			}
		return false;

	}
	status gameStatus(char playerSymbol)
	{
		boolean freeSpot = false;
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {

				if (board[x][y] == ' ') {
					freeSpot = true;
				}
				if (board[x][y] == playerSymbol) {

					//check down
					int row = 0;
					int col = 0;
					boolean check = false;

					do {
						check = false;
						if (x + row < boardSize) {

							if (board[x + row][y] == playerSymbol) {
								check = true;
							}
							row++;
						}
					} while ((check == true) && (row < winRowSize));

					if (check == false) {
						//check down right
						row = 0;
						col = 0;
						do {
							check = false;
							if ((x + row < boardSize) && (y + col < boardSize)) {
								if (board[x + row][y + col] == playerSymbol) {
									check = true;
								}
								row++;
								col++;
							}
						} while ((check == true) && (row < winRowSize));
					}
					if (check == false) {
						//check down left
						row = 0;
						col = 0;
						do {
							check = false;
							if ((x + row < boardSize) && (y + col > boardSize)) {
								if (board[x + row][y + col] == playerSymbol) {
									check = true;
								}
								row++;
								col--;
							}
						} while ((check == true) && (row < winRowSize));
					}
					if (check == false) {
						//across
						row = 0;
						col = 0;
						do {
							check = false;
							if (y + col < boardSize) {

								if (board[x][y + col] == playerSymbol) {
									check = true;
								}
								col++;
							}
						} while ((check == true) && (col < winRowSize));
					}

					if (check == true) {
						return status.WIN;
					}
				}
			}
		}
		if (freeSpot == true) {
			return status.CONTINUE;
		}
		return status.DRAW;
	}
	void randomGame() {
		boolean done = false;
		char symbol = 'X';
		Random rand = new Random();
		System.out.println("Win row size " + winRowSize);
		while (!done)
		{
			int row;
			int col;
			do {

				row = 0;
				col = 0;
				row = rand.nextInt(boardSize);
				col = rand.nextInt(boardSize);

			} while (!isValidMove(row, col, symbol));
			done = gostMove(symbol, row, col);
			if (symbol == 'X')
				symbol = 'O';
			else
				symbol = 'X';
		}
	}
	boolean gostMove(char playerSymbol, int row, int col)
	{

		board[row][col] = playerSymbol;

		status gStatus;
		if (playerSymbol == ' ') {
			gStatus = status.CONTINUE;

		}
		else {
			gStatus = gameStatus(playerSymbol);
		}
		
		if (gStatus == status.WIN)
		{
			displayBoard();
			System.out.println("Player " + playerSymbol + " wins!");
			return true;
		}
		else if (gStatus == status.DRAW)
		{
			displayBoard();
			System.out.println("This game is a draw!");
			return true;
		}
		return false;
	}
	
}
