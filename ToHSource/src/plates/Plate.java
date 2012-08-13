/**
 * 
 */
package plates;

import interfaces.CLInteraction;
import interfaces.Copyable;
import interfaces.HasSize;

/**
 * @author $hadow$torm
 *
 */
public class Plate implements Comparable<Plate>, Copyable<Plate>, HasSize, CLInteraction {
	private final int PLATE_SIZE;
	private final String unit;
	
	/**
	 * Creates a new plate of the given size, and default string representation. Plate sizes can only be positive values.
	 * @param size the size of the plate. Must be non-negative.
	 */
	public Plate(int size) {
		this(size, "==");
	}
	
	/**
	 * Creates a new plate of the given size, and will represent the plate in string format by unit. For best appearances
	 * the length of unit should be 2. Plate sizes can only be positive values.
	 * @param size the size of the plate. Must be non-negative.
	 * @param unit the way to represent one unit of size. For best looks, should only be a String of length 2.
	 */
	public Plate(int size, String unit) {
		if (size < 0) {
			throw new IllegalArgumentException();
		}
		PLATE_SIZE = size;
		this.unit = unit.replace("\n", "");
	}
	
	/** 
	 * @see main.CLInteraction#cliDraw()
	 */
	public String cliDraw() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<PLATE_SIZE; i++) {
			sb.append(unit);
		}
		return sb.toString();
	}
	
	@Override
	public int compareTo(Plate p) {
		if (p != null)
			return this.PLATE_SIZE - p.PLATE_SIZE;
		return 0;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Plate) 
			return (0 == compareTo((Plate) other));
		return false;
	}
	
	/**
	 * Returns a String of the class name, with the field names and their values.
	 */
	@Override 
	public String toString() {
		return "Plate[size=" + PLATE_SIZE +", unit= " + this.unit + "]";
				
	}
	
	/** 
	 * @see main.Copyable#copy()
	 */
	public Plate copy() {
		return new Plate(this.PLATE_SIZE, this.unit);
	}

	/**
	 * @see main.HasSize#size()
	 */
	@Override
	public int size() {
		return PLATE_SIZE;
	}

}
