package cli;

import interfaces.Display;
import interfaces.Move;
import interfaces.Playable;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import abstracts.AbstractHanoiGame;
import exceptions.IllegalActionException;



public class CLIHanoi extends AbstractHanoiGame{

	private Scanner in;
	private PrintStream out;
	private Display display;
	private boolean reset;
	private boolean fastExit;
	private List<String> exitOptions = Arrays.asList("e", "q", "exit", "quit");
	private List<String> resetOptions = Arrays.asList("r", "reset", "restart",
			"redo");

	public CLIHanoi() {
		this(null, null);
	}

	public CLIHanoi(InputStream in) {
		this(in, null);
	}

	public CLIHanoi(InputStream in, OutputStream out) {
		InputStream input = System.in;
		this.out = System.out;
		if (in != null) {
			input = in;
		}
		if (out != null) {
			this.out = (PrintStream) out;
		}
		this.in = new Scanner(input);
		this.display = new CLIDisplay(this.out);
		fastExit = false;
		reset = false;
	}

	public CLIHanoi(OutputStream out) {
		this(null, out);
	}

	private String checkForResetString(String input) {
		String test = input.toLowerCase();
		fastExit = exitOptions.contains(test);
		reset = resetOptions.contains(test);
		return input;
	}

	@Override
	public Playable createNewGame() {
		printWelcome();

		int gameSize = getNumberOfPlatesForGame();
		if ((gameSize < 3) || (gameSize % 2 == 0)) {
			throw new IllegalArgumentException();
		}
		buildGame(gameSize);

		out.println("Starting Game.");
		return this;
	}

	@Override
	public void display() {
		out.println("Moves: " + getNumberOfMoves());
		printTowers();
	}

	public void endGame() {
		if (fastExit) {
			out.println("Quitting");
		}else {
			printTowers();
			out.println("Congratulations! You completed the game in "
					+ getNumberOfMoves() + " moves.");
		}
		out.println("Thank you for playing.");
	}

	/**
	 * Reads input until an integer appears, or a valid escape string appears.
	 * Escape Strings are designated by the two options arrays. Returns -1 if an
	 * escape character is found.
	 * 
	 * @return an int from the input, if something went wrong or an escape
	 *         string was found, then returns -1.
	 */
	private int getIntFromInput() {
		String input;
		int i;
		boolean foundInt = false;
		while (!foundInt) {
			try {
				input = checkForResetString(in.next());
				i = Integer.parseInt(input);
				return i;
			} catch (Exception e) {
				if (reset || fastExit)
					return -1;
				out.println("Not an integer");
			}
		}
		return -1;
	}

	private int getMove(String prompt, String errorResponse) {
		out.println(prompt);
		int tower = getIntFromInput();

		while ((tower != 1) && (tower != 2) && (tower != 3)) {
			if (reset || fastExit)
				return -1;
			out.println(errorResponse);
			tower = getIntFromInput();
		}
		return tower;
	}

	private int getNumberOfPlatesForGame() {
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

	@Override
	public Playable next() {
		play();
		if (reset) {
			resetGame();
		}
		return this;
	}

	private void play() {
		int towerOne = 0, towerTwo = 0;
		boolean firstTry = true;
		boolean success = false;
		Move m;
		String firstPrompt = "Choose the tower to move a plate from. (Left [1], Middle [2], Right [3])";
		String errorResponse = "Invalid Option. Choose a proper tower. (Left [1], Middle [2], Right [3])";
		String secondPrompt = "Choose the tower to move a plate to. (Left [1], Middle [2], Right [3])";

		do {
			if (!firstTry) {
				out.println("Not a valid move");
			} else {
				firstTry = !firstTry;
			}
			towerOne = getMove(firstPrompt, errorResponse);
			if (reset) {
				resetGame();
				return;
			} else if (fastExit) {
				return;
			}
			towerTwo = getMove(secondPrompt, errorResponse);

			m = new CLIMove(towerOne, towerTwo);
			try {
				towers.applyMove(m);
				success = true;
			} catch (IllegalActionException badMove) {
				
			}
		} while (reset || success);
		moves.add(m);

	}
	
	@Override
	public boolean isGameOver() {
		return super.isGameOver() || fastExit;
	}

	/**
	 * Helper method to abstract the printing.
	 */
	private void printTowers() {
		display.display(towers);
	}

	/**
	 * Prints a welcome message to the output
	 */
	private void printWelcome() {
		out.println("Welcome to Towers of Hanoi.");
	}

}
