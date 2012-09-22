package main;

import interfaces.Playable;
import cli.CLIHanoi;

public class GameRunner {

	public static void main(String args[]) {
		Playable p = getPlayable(parseArgs(args));
		do {
			p.createNewGame();
			do {
				p.display();
				p.next();
			} while (!p.isGameOver());
			p.endGame();
		} while (restart());
	}

	/**
	 * Creates a new Playable based on the given type and returns it.
	 * 
	 * @param type
	 * @returns the Playable created by this method
	 */
	private static Playable getPlayable(String type) {
		if (type.equals("CommandLine")) {
			return new CLIHanoi();
		}
		return null;
	}

	/**
	 * Parses the args and returns a string of recognizable commands for this.
	 * 
	 * @param args
	 * @return
	 */
	private static String parseArgs(String[] args) {
		String type = "CommandLine";
		return type;
	}

	/**
	 * Returns true if the user wants to restart the game.
	 * 
	 * @return
	 */
	private static boolean restart() {
		return false;
	}
}
