package interfaces;

public interface Towers {

	/**
	 * Returns a copy of all towers held in reference in the implementation, no
	 * order is guaranteed by the iterable.
	 * 
	 * @return an iterable if all towers in the Towers
	 */
	public Iterable<Tower> getAllTowers();

	/**
	 * Returns the number of plates that are contained within all of the towers
	 * in this.
	 * 
	 * @return the number of plates in the towers
	 */
	public int getPlateCount();

}