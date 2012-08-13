package cligame;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import main.Game;

public class RunCLIGame {
	
	private static Game towersGame;
	private static PrintStream out;
	private static Scanner in;
	private static int[] towerChoices = { 0, Game.LEFT_TOWER, Game.MIDDLE_TOWER, Game.RIGHT_TOWER};

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream input;
		if (args.length > 0) {
			out = null;
			in = null;
		}else {
			out = System.out;
			input = System.in;
			in = new Scanner(input);
		}
		
		printWelcome();
		startGame();
		playGame();
		endGame();
		
	}
	
	private static int getIntFromInput() {
		int i;
		boolean foundInt = false;
		while (!foundInt) {
			try {
				i = in.nextInt();
				return i;
			}catch (Exception e) {
				in.next();
				out.println("Not an integer");
			}
		}
		return 0;
		
	}
	
	private static void printWelcome() {
		out.println("Welcome to Towers of Hanoi.");
	}
	
	private static int getNumberOfPlates() {
		String question = "How tall would you like the towers to be? (Must be an odd number.)";
		String errorResponse = "That is an invalid input. Please enter an odd number >= 3";
		String validResponse = "Thank you";
		out.println(question);
		int gameSize = getIntFromInput();
		while ((gameSize % 2 != 1) || (gameSize <= 1)) {
			out.println(errorResponse);
			gameSize = getIntFromInput();
		}
		out.println(validResponse);
		return gameSize;	
	}

	private static void startGame() {
		int gameSize = getNumberOfPlates();
		towersGame = new Game(gameSize);
	}
	
	private static void printTowers() {
		out.println(towersGame);
	}
	
	private static void playGame() {
		out.println("Starting Game.");
		
		do {
			out.println("Moves: " + towersGame.getMovesCount());
		} while (playRound());
		
		out.println("Congratulations! You completed the game in " + towersGame.getMovesCount() + " moves.");
	}
	
	private static boolean playRound() {
		printTowers();
		makeMove();
		boolean keepPlaying = !towersGame.checkWinCondition();
		return keepPlaying;
		
	}
	
	private static int getMove(String prompt, String errorResponse) {
		out.println(prompt);
		int tower = getIntFromInput();

		while ((tower != 1) && (tower != 2) && (tower != 3)) {
			out.println(errorResponse);
			tower = getIntFromInput();
		}
		return towerChoices[tower];
	}
	
	private static void makeMove() {
		int towerOne, towerTwo;
		boolean firstTry = true;
		String firstPrompt = "Choose the tower to move a plate from. (Left [1], Middle [2], Right [3])";
		String errorResponse ="Invalid Option. Choose a proper tower. (Left [1], Middle [2], Right [3])";
		String secondPrompt= "Choose the tower to move a plate to. (Left [1], Middle [2], Right [3])";
		
		do {
			if (!firstTry) {
				out.println("Not a valid move");
			}else {
				firstTry = !firstTry;
			}
			
			towerOne = getMove(firstPrompt, errorResponse);
			towerTwo = getMove(secondPrompt, errorResponse);
		} while (!towersGame.movePlate(towerOne, towerTwo));
	}
	
	private static void endGame() {
		out.println("Thank you for playing.");
	}
	
	
}
