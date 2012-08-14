package cligame;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import main.Game;

public class RunCLIGame {
    
    private static Game         towersGame;
    private static boolean      reset;
    private static PrintStream  out;
    private static Scanner      in;
    private static int[]        towerChoices = { 0, Game.LEFT_TOWER, Game.MIDDLE_TOWER,
            Game.RIGHT_TOWER                };
    private static List<String> resetOptions = Arrays.asList("r", "reset", "restart", "redo");
    private static List<String> exitOptions  = Arrays.asList("e", "q", "exit", "quit");
    
    /**
     * Starts a new game from the command line. Output defaults to standard out, and input defaults
     * to standard in. Currently there are no available options.
     * 
     * @param args
     */
    public static void main(String[] args) {
        InputStream input;
        if (args.length > 0) {
            // out = null;
            // in = null;
        } else {
            out = System.out;
            input = System.in;
            in = new Scanner(input);
        }
        
        printWelcome();
        startGame();
        playGame();
        endGame();
    }
    
    /**
     * Reads input until an integer appears, or a valid escape string appears. Escape Strings are
     * designated by the two options arrays. Returns -1 if an escape character is found.
     * 
     * @return an int from the input, if something went wrong or an escape string was found, then
     *         returns -1.
     */
    private static int getIntFromInput() {
        String input;
        int i;
        boolean foundInt = false;
        while (!foundInt) {
            try {
                input = checkForResetString(in.next());
                i = Integer.parseInt(input);
                return i;
            } catch (Exception e) {
                if (reset) return -1;
                out.println("Not an integer");
            }
        }
        return -1;
    }
    
    /**
     * Prints a welcome message to the output
     */
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
    
    private static void resetGame() {
        towersGame.resetGame();
        reset = false;
    }
    
    private static String checkForResetString(String input) {
        String test = input.toLowerCase();
        if (exitOptions.contains(test)) {
            System.exit(0);
        }
        reset = resetOptions.contains(test);
        return input;
    }
    
    private static void printTowers() {
        out.println(towersGame.cliDraw());
    }
    
    private static void playGame() {
        out.println("Starting Game.");
        
        do {
            out.println("Moves: " + towersGame.getMovesCount());
        } while (playRound());
        
        out.println("Congratulations! You completed the game in " + towersGame.getMovesCount()
                + " moves.");
    }
    
    private static boolean playRound() {
        printTowers();
        makeMove();
        if (reset) resetGame();
        boolean keepPlaying = !towersGame.checkWinCondition();
        return keepPlaying;
        
    }
    
    private static int getMove(String prompt, String errorResponse) {
        out.println(prompt);
        int tower = getIntFromInput();
        
        while ((tower != 1) && (tower != 2) && (tower != 3)) {
            if (reset) return -1;
            out.println(errorResponse);
            tower = getIntFromInput();
        }
        return towerChoices[tower];
    }
    
    private static void makeMove() {
        int towerOne = 0, towerTwo = 0;
        boolean firstTry = true;
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
            if (reset) return;
            towerTwo = getMove(secondPrompt, errorResponse);
        } while (reset || !towersGame.movePlate(towerOne, towerTwo));
    }
    
    private static void endGame() {
        out.println("Thank you for playing.");
    }
    
}
