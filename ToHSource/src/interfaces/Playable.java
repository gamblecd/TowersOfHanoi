package interfaces;

public interface Playable {

	/**
	 * Constructs a Playable and returns it it. The Playable instance can begin
	 * performing actions when returned, it is guaranteed to be properly setup
	 * at the end of this function.
	 * 
	 * @return
	 */
	public Playable createNewGame();

	/**
	 * Performs all the actions to display the current state of the Playable;
	 */
	public void display();

	/**
	 * Ends the current game, and displays any additional information regarding
	 * the game. eg stats.
	 */
	public void endGame();

	/**
	 * Returns the number of moves that have been performed on the playable.
	 * 
	 * @return the number of moves performed as an int.
	 */
	public int getNumberOfMoves();

	/**
	 * Returns true if the current game is over. Once a game is over, next()
	 * should not be able to run properly
	 * 
	 * @return
	 */
	public boolean isGameOver();

	/**
	 * Performs one cycle or turn for the game. The workings of the game are
	 * determined by the implementation of Playable.
	 * 
	 * @return a Playable in the state after the next turn has been performed.
	 */
	public Playable next();

	/**
	 * Resets the game to the state that would be returned immediately after
	 * createNewGame().
	 */
	public void resetGame();

}
