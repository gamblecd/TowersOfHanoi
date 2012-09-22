package interfaces;

public interface Display {

	/**
	 * Performs the display action for the given tower.
	 * 
	 * @param tower
	 */
	public void display(Tower tower);

	/**
	 * Performs the display action for the given set of towers.
	 * 
	 * @param towers
	 */
	public void display(Towers towers);

	/**
	 * Performs the display action for the given playable.
	 * 
	 * @param playable
	 */
	public void display(Playable playable);
}
