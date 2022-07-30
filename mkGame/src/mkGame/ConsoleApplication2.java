package mkGame;
import mkGame.Board;
import mkGame.Player;
import mkGame.RandomAi;
import mkGame.Human;
import mkGame.MiniMax;
import java.util.Scanner;
import java.util.Random;

public class ConsoleApplication2 {
	
	public static void main(String [ ] args)
	{
		
		char check;
		System.out.print("Would you like to simulate a game? y/n");
		Scanner reader = new Scanner(System.in);
		check = reader.next().charAt(0);
		System.out.println(check);
		if (check == 'y') {
			System.out.println("check");
			Random rand = new Random();
			do {
				Board random = new Board( (rand.nextInt(20)) + 1, (rand.nextInt(20)) + 1);
				random.randomGame();
				System.out.print("would you like to simulate another game? y/n");
				check = reader.next().charAt(0);
			} while (check != 'n');
		}
		int boardSize = 0;
		int winSize = 0;
		Player firstPlayer;
		Player secondPlayer;
		firstPlayer = new Human('X');
		secondPlayer = new Human('O');

		do {
			System.out.print("Please enter the board size 3 or greater and 99 or less: ");
			boardSize = reader.nextInt();
			           // user > 18 && user < 40
		} while ((boardSize < 3) || (boardSize > 99));
		do {
			System.out.print("Please enter a win row number of 2 or greater: ");
			winSize = reader.nextInt();
		} while (winSize <= 1);
		int player1Type;
		do {
			System.out.print("Please select player 1 type: Human 1, Random 2, Ai 3: ");
			player1Type = reader.nextInt();
		} while (player1Type < 0 && player1Type > 4);
		if (player1Type == 1) {
			firstPlayer = new Human('X');
		}
		else if (player1Type == 2) {
			firstPlayer = new RandomAi('X');
		}
		else if (player1Type == 3) {
			firstPlayer = new MiniMax('X');
		}
		int player2Type;
		do {
			System.out.print("Please select player 2 type: Human 1, Random 2, Ai 3: ");
			player2Type = reader.nextInt();
		} while (player2Type < 0 && player2Type > 4);
		if (player2Type == 1) {
			secondPlayer = new Human('O');
		}
		else if (player2Type == 2) {
			secondPlayer = new RandomAi('O');
		}
		else if (player2Type == 3) {
			secondPlayer = new MiniMax('O');
		}
		Board game = new Board(boardSize, winSize);
		MKGame play = new MKGame(firstPlayer, secondPlayer, game);

		play.play();
		
	}

}
