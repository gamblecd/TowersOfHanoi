/**
 * 
 */
package plates;

import interfaces.CLInteraction;
import interfaces.HasSize;

/**
 * @author $hadow$torm
 *
 */
public class Plate implements Comparable<Plate>, HasSize, CLInteraction {
	private final int PLATE_SIZE;
	private final String unit;
	
	public Plate(int size) {
		this(size, "==");
	}
	
	public Plate(int size, String unit) {
		if (size < 0) {
			throw new IllegalArgumentException();
		}
		PLATE_SIZE = size;
		this.unit = unit.replace("\n", "");
	}
	
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
	
	@Override 
	public String toString() {
		return "Plate[size=" + PLATE_SIZE +"]";
				
	}

	/* (non-Javadoc)
	 * @see main.HasSize#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return PLATE_SIZE;
	}

}
