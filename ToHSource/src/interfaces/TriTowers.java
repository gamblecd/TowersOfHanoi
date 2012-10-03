package interfaces;

import exceptions.IllegalActionException;

public interface TriTowers extends Towers {

	/**
	 * Applies the Move m to the the implementation of towers.
	 * 
	 * @param m
	 *            a Move that can be applied to a Towers
	 * @throws IllegalActionException
	 *             if the move is not acceptable for the implementation
	 */
	public void applyMove(Move m) throws IllegalActionException;

	/**
	 * Returns the very center tower of the implementation
	 * 
	 * @return the center Tower of this.
	 */
	public Tower getCenterTower();

	/**
	 * Returns the very left tower of the implementation
	 * 
	 * @return the left Tower of this.
	 */
	public Tower getLeftTower();

	/**
	 * Returns the very right tower of the implementation
	 * 
	 * @return the right Tower of this.
	 */
	public Tower getRightTower();
}
