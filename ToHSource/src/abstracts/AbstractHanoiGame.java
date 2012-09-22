package abstracts;

import interfaces.Move;
import interfaces.Playable;
import interfaces.Tower;
import interfaces.TriTowers;

import java.util.ArrayList;
import java.util.List;

import entities.Plate;
import entities.ThreeTowers;

public abstract class AbstractHanoiGame implements Playable {

	// A stored list of moves that have been successful in the game.
	protected List<Move> moves;
	// The towers that represent the a Towers of Hanoi game state.
	protected TriTowers towers;

	/**
	 * Builds a new Towers of Hanoi Game with gameSize determining how many
	 * plates are available to create with.
	 * 
	 * @param gameSize
	 *            the number of plates to set the game up with.
	 */
	protected void buildGame(int gameSize) {
		moves = new ArrayList<Move>();
		towers = new ThreeTowers(gameSize);
		resetGame();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.Playable#getNumberOfMoves()
	 */
	public int getNumberOfMoves() {
		return moves.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.Playable#isGameOver()
	 */
	public boolean isGameOver() {
		return towers.getRightTower().size() == towers.getPlateCount()
				|| towers.getCenterTower().size() == towers.getPlateCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.Playable#resetGame()
	 */
	public void resetGame() {
		for (Tower t : towers.getAllTowers()) {
			t.clear();
		}
		Tower left = towers.getLeftTower();

		for (int i = towers.getPlateCount(); i > 0; i--) {
			left.pushOnto(new Plate(i));
		}
		moves.clear();
	}

	/**
	 * Returns a string of debug information about the game, in a sensible
	 * format.
	 */
	@Override
	public String toString() {
		return "AbstractThreeTowerGame[gameSize=" + towers.getPlateCount()
				+ ", moveCount=" + getNumberOfMoves() + ", towers=" + towers
				+ "]";
	}

}
