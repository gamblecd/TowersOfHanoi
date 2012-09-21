package interfaces;

public interface Playable {
	
	public Playable createNewGame();
	
	public void display();
	
	public void endGame();

	public int getNumberOfMoves();
	
	public boolean isGameOver();
	
	public Playable next();
	
	public void resetGame();

}
