package cli;

import interfaces.Display;
import interfaces.Move;
import interfaces.Playable;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import abstracts.AbstractHanoiGame;
import exceptions.IllegalActionException;

public class CLIHanoi extends AbstractHanoiGame {

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

	public CLIHanoi(PrintStream out) {
		this(null, out);
	}

	/**
	 * Creates a new Command Line Interface for a Towers of Hanoi Game where the
	 * interactions are specified by in and out.
	 * 
	 * @param in
	 *            The input stream to read user input from.
	 * @param out
	 *            The print stream to send messages to the user with.
	 */
	public CLIHanoi(InputStream in, PrintStream out) {
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

	/**
	 * Helper method to check for possible reset or quite commands. If any are
	 * found, then the respective booleans will be set.
	 * 
	 * @param input
	 *            the string to check
	 * @return the string provided
	 */
	private String checkForResetString(String input) {
		String test = input.toLowerCase();
		fastExit = exitOptions.contains(test);
		reset = resetOptions.contains(test);
		return input;
	}

	/**
	 * Prints a welcome message to the output
	 */
	private void printWelcome() {
		out.println("Welcome to Towers of Hanoi.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.Playable#createNewGame()
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.Playable#display()
	 */
	public void display() {
		display.display(this);
		display.display(towers);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.Playable#endGame()
	 */
	public void endGame() {
		if (fastExit) {
			out.println("Quitting");
		} else {
			display.display(towers);
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

	/**
	 * Sends the prompt to the output, and attempts to retrieve data, if an
	 * error occurs, displays the given error message. Returns an int
	 * representing the choice of the user.
	 * 
	 * @param prompt
	 * @param errorResponse
	 * @return int representing a tower index. Returns -1 for menu commands like
	 *         reset or exit.
	 */
	private int getTowerChoice(String prompt, String errorResponse) {
		out.println(prompt);
		int choice = getIntFromInput();

		while ((choice != 1) && (choice != 2) && (choice != 3)) {
			if (reset || fastExit)
				return -1;
			out.println(errorResponse);
			choice = getIntFromInput();
		}
		return choice;
	}

	/**
	 * Prompts the user for the number of plates to play with, then validates
	 * the input.
	 * 
	 * @return the number of plates the user chose.
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.Playable#next()
	 */
	public Playable next() {
		play();
		if (reset) {
			resetGame();
		}
		return this;
	}

	/**
	 * Private helper method that performs one turn from start to finish.
	 */
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
			towerOne = getTowerChoice(firstPrompt, errorResponse);
			if (reset) {
				resetGame();
				return;
			} else if (fastExit) {
				return;
			}
			towerTwo = getTowerChoice(secondPrompt, errorResponse);

			m = new CLIMove(towerOne, towerTwo);
			try {
				towers.applyMove(m);
				success = true;
			} catch (IllegalActionException badMove) {

			}
		} while (reset || success);
		moves.add(m);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see abstracts.AbstractHanoiGame#isGameOver()
	 */
	@Override
	public boolean isGameOver() {
		return super.isGameOver() || fastExit;
	}
}
