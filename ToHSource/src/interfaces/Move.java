package interfaces;

import exceptions.IllegalActionException;

public interface Move {

	/**
	 * Attempts to Perform an action defined as a move on the set of towers
	 * 
	 * @param tower
	 * @return
	 */
	void move(TriTowers tower) throws IllegalActionException;

}
