package abstracts;

import interfaces.Move;
import interfaces.Playable;
import interfaces.Tower;
import interfaces.TriTowers;

import java.util.ArrayList;
import java.util.List;

import entities.Plate;
import entities.ThreeTowers;

public abstract class AbstractHanoiGame implements Playable{

	protected List<Move> moves;
	protected TriTowers towers;

	protected void buildGame(int gameSize) {
		moves = new ArrayList<Move>();
		towers = new ThreeTowers(gameSize);
		resetGame();
	}

	public int getNumberOfMoves() {
		return moves.size();
	}

	public boolean isGameOver() {
		return towers.getRightTower().size() == towers.getPlateCount()
				|| towers.getCenterTower().size() == towers.getPlateCount();
	}

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
	 * Returns an ascii graphical representation of the current state of the game. Each tower's graphic is displayed
	 * separated by a newline character. The order of display is LEFT, MIDDLE, RIGHT.
	 */
	@Override
	public String toString() {
		return "AbstractThreeTowerGame[gameSize=" + towers.getPlateCount() + ", moveCount=" + getNumberOfMoves() + ", towers=" + towers+ "]";
	}
	
}
