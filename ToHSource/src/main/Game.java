/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.List;

import plates.Plate;
import towers.Tower;

/**
 * @author $hadow$torm
 *
 */
public class Game {
	public static final int LEFT_TOWER = 0;
	public static final int MIDDLE_TOWER = 1;
	public static final int RIGHT_TOWER = 2;
	
	private int moveCounter;
	private final int gameSize;
	private Tower<Plate> leftTower;
	private Tower<Plate> middleTower;
	private Tower<Plate> rightTower;
	private final List<Tower<Plate>> towers = new ArrayList<Tower<Plate>>(); 
	
	
	/**
	 * Creates a new game of Towers of Hanoi, starting with n plates. The number of plates allowed must always be at 
	 * least 3, and must be odd, these are defined by the rules of the game.
	 * @param n
	 */
	public Game(int n) {
		if ((n < 3) || (n % 2 == 0)) {
			throw new IllegalArgumentException();
		}
		gameSize = n;
		buildGame();
		resetGame();
	}
	
	/**
	 * Builds the fields for the game to start.
	 */
	private void buildGame() {
		leftTower = new Tower<Plate>(gameSize);
		middleTower = new Tower<Plate>(gameSize);
		rightTower = new Tower<Plate>(gameSize);
		towers.add(leftTower);
		towers.add(middleTower);
		towers.add(rightTower);
	}
	
	/**
	 * Clears out the towers of the game, and readds plates to the leftTower, to set the game back to the beginning.
	 */
	public void resetGame() {
		for (Tower<Plate> t: towers) {
			t.clear();
		}
		for (int i=gameSize; i>0; i--) {
			leftTower.push(new Plate(i));
		}
		moveCounter=0;
	}
	
	/**
	 * Returns the number of successful moves that have been made since the last time the game was reset.
	 * @return the number of moves that have been made.
	 */
	public int getMovesCount() {
		return moveCounter;
	}
	
	/**
	 * Tries to move a plate from the tower represented by towerFrom to the tower represented by towerTo. If it is 
	 * possible returns true, otherwise returns false. It is not possible to make the following moves:
	 * towerFrom cannot be empty.
	 * the plate to move cannot be larger than the top plate of towerTo, if towerTo has at least one plate.
	 * @param towerFrom an integer representing the tower to move a plate from.
	 * @param towerTo an integer representing the tower to move a plate to.
	 * @return true if the move was successful, otherwise false.
	 */
	public boolean movePlate(int towerFrom, int towerTo) {
		assert(towerTo < towers.size());
		assert(towerFrom < towers.size());
		Tower<Plate> t1 = towers.get(towerFrom);
		Tower<Plate> t2 = towers.get(towerTo);
		if (t1.peek() == null) 
			return false;
		
		if (t2.push(t1.peek())) {
			t1.pop();
			moveCounter++;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks to see if all the plates are on a tower other than the left tower, and asserts that the other two towers
	 * agree with that fact. Returns true if that is the case, false otherwise.
	 * @return true if all the plates are on a tower that is not the left one.
	 */
	public boolean checkWinCondition() {
		boolean allOnMidTower = ((middleTower.size() == gameSize) && ((leftTower.size() == 0) && (rightTower.size() == 0)));
		boolean allOnRightTower = ((rightTower.size() == gameSize) && ((leftTower.size() == 0) && (middleTower.size() == 0)));
		return ( allOnMidTower || allOnRightTower);
	}
	
	/**
	 * Returns an ascii graphical representation of the current state of the game. Each tower's graphic is displayed
	 * separated by a newline character. The order of display is LEFT, MIDDLE, RIGHT.
	 */
	@Override
	public String toString() {
		return "Game[gameSize=" + gameSize + ", moveCount=" + moveCounter + ", towers=" + towers+ "]";
	}
	
	public String cliDraw() {
		return (towers.get(LEFT_TOWER).cliDraw() + "\n" +
				towers.get(MIDDLE_TOWER).cliDraw() + "\n" +
				towers.get(RIGHT_TOWER).cliDraw());
	}
}
