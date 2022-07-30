package mkGame;
import java.util.Random;
import mkGame.Player;

public class MiniMax extends Player{
	private char opponent;
	private char type = 'c';
	private class move {
		int row;
		int col;
		int val;
	};

	public int tilesInARow(char playerPiece, int x, int y, char[][] board, int boardSize, int winRowSize) {
		int best = 0, rowLength = 0;

		if (board[x][y] == playerPiece) {

			//check down
			boolean check = false;
			int row = 0, col = 0;
			do {
				check = false;
				if (x + row < boardSize) {

					if (board[x + row][y] == playerPiece) {
						rowLength = rowLength + 1;
						check = true;
					}
					row++;
				}
			} while (check == true);

			if (rowLength > best) {
				best = rowLength;
			}
			//check up
			row = 0;
			col = 0;
			rowLength = 0;
			do {
				check = false;
				if (x + row > 0) {

					if (board[x + row][y] == playerPiece) {
						rowLength = rowLength + 1;
						check = true;
					}
					row--;
				}
			} while (check == true);

			if (rowLength > best) {
				best = rowLength;
			}
			//check down right
			row = 0;
			col = 0;
			rowLength = 0;
			do {
				check = false;
				if (x + row < boardSize && y + col < boardSize) {
					if (board[x + row][y + col] == playerPiece) {
						rowLength = rowLength + 1;
						check = true;
					}
					row++;
					col++;
				}
			} while (check == true);

			if (rowLength > best) {
				best = rowLength;
			}

			//check up left
			row = 0;
			col = 0;
			rowLength = 0;
			do {
				check = false;
				if (x + row > 0 && y + col > 0) {
					if (board[x + row][y + col] == playerPiece) {
						check = true;
						rowLength = rowLength + 1;
					}
					row--;
					col--;
				}
			} while (check == true);

			if (rowLength > best) {
				best = rowLength;
			}

			//check down left
			row = 0;
			col = 0;
			rowLength = 0;
			do {
				check = false;
				if (x + row < boardSize && y + col > 0) {
					if (board[x + row][y + col] == playerPiece) {
						rowLength = rowLength + 1;
						check = true;
					}
					row++;
					col--;
				}
			} while (check == true);

			if (rowLength > best) {
				best = rowLength;
			}

			//check up right
			row = 0;
			col = 0;
			rowLength = 0;
			do {
				check = false;
				if (x + row > 0 && y + col < boardSize) {
					if (board[x + row][y + col] == playerPiece) {
						rowLength = rowLength + 1;
						check = true;
					}
					row--;
					col++;
				}
			} while (check == true);

			if (rowLength > best) {
				best = rowLength;
			}

			//across right
			row = 0;
			col = 0;
			rowLength = 0;
			do {
				check = false;
				if (y + col < boardSize) {

					if (board[x][y + col] == playerPiece) {
						rowLength = rowLength + 1;
						check = true;
					}
					col++;
				}
			} while (check == true);

			if (rowLength > best) {
				best = rowLength;
			}

			//across left
			row = 0;
			col = 0;
			rowLength = 0;
			do {
				check = false;
				if (y + col > 0) {

					if (board[x][y + col] == playerPiece) {
						rowLength = rowLength + 1;
						check = true;
					}
					col--;
				}
			} while (check == true);

			if (rowLength > best) {
				best = rowLength;
			}
		}
		return best;
	}
	private int win(char playerPiece, char[][] board, int boardSize, int winRowSize) {
		int best = 0, rowLength = 0;
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				if (board[x][y] == playerPiece) {

					//check down
					int row = 0;
					int col = 0;
					boolean check = false;

					do {
						check = false;
						if (x + row < boardSize) {

							if (board[x + row][y] == playerPiece) {
								check = true;
								rowLength++;
							}
							row++;
						}
					} while (check == true && row < winRowSize);

					if (rowLength > best) {
						best = rowLength;
					}

					if (check == false) {
						//check down right
						row = 0;
						col = 0;
						rowLength = 0;
						do {
							check = false;
							if (x + row < boardSize && y + col < boardSize) {
								if (board[x + row][y + col] == playerPiece) {
									check = true;
									rowLength++;
								}
								row++;
								col++;
							}
						} while (check == true && row < winRowSize);
						if (rowLength > best) {
							best = rowLength;
						}
					}
					if (check == false) {
						//check down left
						row = 0;
						col = 0;
						rowLength = 0;

						do {
							check = false;
							if (x + row < boardSize && y + col > boardSize) {
								if (board[x + row][y + col] == playerPiece) {
									check = true;
									rowLength++;
								}
								row++;
								col--;
							}
						} while (check == true && row < winRowSize);
						if (rowLength > best) {
							best = rowLength;
						}
					}
					if (check == false) {
						//across
						row = 0;
						col = 0;
						rowLength = 0;

						do {
							check = false;
							if (y + col < boardSize) {

								if (board[x][y + col] == playerPiece) {
									check = true;
									rowLength++;
								}
								col++;
							}
						} while (check == true && col < winRowSize);
						if (rowLength > best) {
							best = rowLength;
						}
					}

					if (check == true) {
						return -1;
					}
					else {
						return best;
					}
				}
			}
		}
		return -2;
	}
	private int maxDepth(char[][] board, int boardSize) {
		int maxMoves = 1000000;
		int freeCells = 0;
		for (int i = 0; i <boardSize; i++)
		{
			for (int j = 0; j < boardSize; j++)
			{
				if (board[i][j] == ' ') {
					freeCells++;
				}
			}
		}

		int possibleMoves = freeCells;
		int maxDepth = -1;
		while (possibleMoves < maxMoves) {
			if (freeCells < 0) {
				break;
			}
			freeCells--;
			maxDepth++;
			possibleMoves = possibleMoves * freeCells;
		}
		return maxDepth;
	}
	private int minimax(int depth, boolean isMyTurn, char[][] board, int boardSize, int winRowSize)
	{

		if (win(playerSymbol, board, boardSize, winRowSize) == -1) {
			return 100 * (depth + 1);
		}
		if (win(opponent, board, boardSize, winRowSize) == -1) {
			return -100 * (depth  + 1);
		}
		boolean freeSpot = false;
		for (int i = 0; i <boardSize; i++)
		{
			for (int j = 0; j < boardSize; j++)
			{
				if (board[i][j] == ' ') {
					freeSpot = true;
				}
			}
		}
		if (!freeSpot) {
			return 0;
		}

		int best = 0;

		if (!isMyTurn)
		{
			for (int i = 0; i < boardSize; i++)
			{
				for (int j = 0; j < boardSize; j++)
				{
					if (depth >= 0) {
						if (board[i][j] == ' ')
						{
							board[i][j] = opponent;
							best = min(best, minimax(depth - 1, true, board, boardSize, winRowSize));
							int temp = win(playerSymbol, board, boardSize, winRowSize);
							if (temp == -1) {
								best = best + winRowSize;
							}
							else {
								best = best + temp;
							}
							board[i][j] = ' ';
						}
					}
					else {
						return (win(opponent, board, boardSize, winRowSize) * -1);
					}
				}
			}
		}
		else
		{
			for (int i = 0; i < boardSize; i++)
			{
				for (int j = 0; j < boardSize; j++)
				{
					if (depth >= 0) {
						if (board[i][j] == ' ')
						{
							board[i][j] = playerSymbol;
							best = max(best, minimax(depth - 1, false, board, boardSize, winRowSize));
							int temp = win(opponent, board, boardSize, winRowSize);
							if (temp == -1) {
								best = best - winRowSize;
							}
							else {
								best = best - temp;  
							}
							board[i][j] = ' ';
						}
					}
					else {
						return (win(playerSymbol, board, boardSize, winRowSize));
					}
				}
			}
		}
		return best;
	}
	private int max(int best, int toCheck) {
		if (best < toCheck) {
			return toCheck;
		}
		return best;
	}
	private int min(int best, int toCheck) {
		if (best > toCheck) {
			return toCheck;
		}
		return best;
	}
	
	public char getType() {
		return type;
	}

	public MiniMax(char mySymbol){
		super(mySymbol);
		if (playerSymbol == 'X') {
			opponent = 'O';
		}
		else {
			opponent = 'X';
		}
	}

	public int[] getMove(char[][] board, int boardSize, int winRowSize)
	{
		Random rand = new Random();
		int freeCells = 0;
		for (int i = 0; i < boardSize; i++)
		{
			for (int j = 0; j < boardSize; j++)
			{
				if (board[i][j] == ' ') {
					freeCells++;
				}
			}
		}
		
		int bestVal = -10000;
		int[] bestMove = new int[2];

		int totalCells = boardSize * boardSize;
		if (freeCells != totalCells) {
			int counter = 0;
			move[] moveValArray = new move[freeCells];
			for (int i = 0; i < freeCells; i++) {
				moveValArray[i] = new move();
				moveValArray[i].row = -1;
				moveValArray[i].col = -1;
				moveValArray[i].val = -1000;
			}
			move[] bestScores = new move[freeCells];
			for (int i = 0; i < freeCells; i++) {
				bestScores[i] = new move();
				bestScores[i].val = -10000;
				bestScores[i].row = -1;
				bestScores[i].col = -1;
			}
			
			for (int i = 0; i < boardSize; i++)
			{
				for (int j = 0; j < boardSize; j++)
				{

						int moveVal = -1001;

						if (board[i][j] == ' ')
						{

							board[i][j] = playerSymbol;

							moveValArray[counter].val = minimax(maxDepth(board, boardSize), false, board, boardSize, winRowSize);
							moveVal = moveValArray[counter].val;
							if (moveVal >= bestVal) {
								bestVal = moveVal;
							}
							moveValArray[counter].row = i;
							moveValArray[counter].col = j;
							char space = ' ';
							board[i][j] = space;

							System.out.println("current move " + moveVal + " row " + moveValArray[counter].row + " col " + moveValArray[counter].col);
							System.out.println("bestMove" + bestVal);							
							counter++;

						}
						else {
						}
						System.out.println("Thinking...");
				}
			}
			int copyCounter = 0;

			for (int i = 0;i < freeCells; i++) {
				if (moveValArray[i].val == bestVal) {
					bestScores[copyCounter].val = moveValArray[i].val;
					bestScores[copyCounter].row = moveValArray[i].row;
					bestScores[copyCounter].col = moveValArray[i].col;
					copyCounter++;
				}
			}
			int randomChoice = rand.nextInt(copyCounter);
			
			bestMove[0] = bestScores[randomChoice].row;
			bestMove[1] = bestScores[randomChoice].col;
			bestVal = bestScores[randomChoice].val;
			
			System.lineSeparator();
		}else {
			bestMove[0] = rand.nextInt(boardSize);
			bestMove[1] = rand.nextInt(boardSize);
		}
		return bestMove;
	}
	
}