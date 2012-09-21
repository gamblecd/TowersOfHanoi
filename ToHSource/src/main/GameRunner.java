package main;

import interfaces.Playable;
import cli.CLIHanoi;

public class GameRunner {
	private static Playable getPlayable(String type) {
		if (type.equals("CommandLine")) {
			return new CLIHanoi();
		}
		return null;
	}
	
	public static void main(String args []) {
		Playable p = getPlayable(parseArgs(args));
		do {
			p.createNewGame();
			do {
				p.display();
				p.next();
			} while (!p.isGameOver());
			p.endGame();
		}while (restart());
	}

	private static String parseArgs(String[] args) {
		String type = "CommandLine";
		
		return type;
	}
	
	private static boolean restart() {
		return false;
	}
}
