package interfaces;

import entities.Plate;

public interface Tower extends Cloneable {

	/**
	 * Empties the tower to have a size of 0 again, nothing is returned, the values are thrown away.
	 */
	public void clear();

	/**
	 * Returns a copy of the object at the top of the tower. If the tower is empty, returns null.
	 * @return A copy of the top element of the tower. If there is no element, returns null.
	 */
	public Plate peek();

	/**
	 * Returns the top element of the tower, if there is no element, returns null.
	 * @return the top element of the tower, if there is no element, returns null.
	 */
	public Plate pop();

	/**
	 * Tries to push the item onto the tower if possible. The item is copied to prevent the object from being tampered 
	 * with after the push. Compares the item to the top most item in the tower, if item is less than the top of the tower, 
	 * successfully pushs the item onto the stack and returns true. Otherwise, if there is no more room on the tower, 
	 * (size() == max), or the item is greater than the top of the tower, then the item is not pushed onto the tower and
	 * returns false.
	 * @param item
	 * @return true if the item was successful pushed onto the stack, otherwise false.
	 */
	public boolean pushOnto(Plate p);

	/**
	 * Returns the current size of the tower.
	 * @return the current size of the tower.
	 */
	public int size();
	
	/**
	 * Returns the maximum size of the tower.
	 * @return the maximum size of the tower.
	 */
	public int maxSize();

	/**
	 * Returns a deep copy of the tower implementation.
	 * @return a deep copy of this
	 */
	public Tower cloneTower();
	
	/**
	 * Returns a String of the class name, with the field names and their values.
	 */
	@Override
	public String toString();
	
	@Override
	public boolean equals(Object other);

	@Override
	public int hashCode();

}