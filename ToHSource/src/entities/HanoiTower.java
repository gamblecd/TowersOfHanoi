/**
 * 
 */
package entities;

import interfaces.CLInteraction;
import interfaces.HasSize;
import interfaces.Tower;

import java.util.Stack;


/**
 * @author $hadow$torm
 *
 */
public class HanoiTower extends Stack<Plate> implements HasSize, CLInteraction, Tower {
	private static final long serialVersionUID = 2547452368202527253L;
	private int maxSize;
	
	/**
	 * Creates a new tower with a capacity of max. Max must be greater than 0 because a tower cannot be forced to have
	 * nothing on it.
	 * @param max > 0.
	 */
	public HanoiTower(int max) {
		if (max < 1) 
			throw new IllegalArgumentException("Integer max must b greater 0. A Tower cannot have a max of 0.");
		maxSize = max;
	}
	
	/* (non-Javadoc)
	 * @see entities.Tower#cliDraw()
	 */
	@Override
	public String cliDraw() {
		StringBuilder base = new StringBuilder();
		StringBuilder tower= new StringBuilder();
		int middle = (maxSize + 1);
		for (int i=0; i<maxSize + 2; i++) {
			base.append("mm");
		}
		
		for (int i=0; i<(maxSize+1 - size()); i++) {
			for (int j=0; j<middle; j++){
				tower.append(" ");	
			}
			tower.append("||\n");
		}
		
		@SuppressWarnings("unchecked")
		Stack<Plate> s = (Stack<Plate>) this.clone();
		
		for (int i=0; i<size(); i++) {
			Plate temp = s.pop();
			for (int j=0; j<middle - (temp.size() - 1); j++){
				tower.append(" ");	
			}
			tower.append(temp.cliDraw() + "\n");
		}
		
		tower.append(base.toString());
		return tower.toString();
	}
	
	private boolean equals(HanoiTower t) {
	    return (this.maxSize == t.maxSize) && super.equals((Stack<Plate>) t);
	}
	
	/* (non-Javadoc)
	 * @see entities.Tower#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
	    if (other instanceof HanoiTower) 
            return this.equals((HanoiTower) other);
        return false;
    }
	
	/* (non-Javadoc)
	 * @see entities.Tower#hashCode()
	 */
	@Override
	public int hashCode() {
	    return maxSize ^ super.hashCode();
	}
	
	/* (non-Javadoc)
	 * @see entities.Tower#peek()
	 */
	@Override
	public Plate peek() {
		if (isEmpty())
			return null;
		return super.peek().copy();
	}
	
	/* (non-Javadoc)
	 * @see entities.Tower#pop()
	 */
	@Override
	public Plate pop() {
		if (isEmpty())
			return null;
		return super.pop();
	}
	
    /* (non-Javadoc)
	 * @see entities.Tower#push(entities.Plate)
	 */
	@Override
	public boolean pushOnto(Plate p) {
		if ((size() == 0)) {
			if (p.size() <= maxSize)
				return p.equals(super.push(p));
			return false;
		}
		if ((p.compareTo(super.peek()) < 0)) {
			if (size() < maxSize)
				return p.equals(super.push(p));
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see entities.Tower#toString()
	 */
	@Override
	public String toString() {
		return "Tower[size=" + size() +", maxSize="+ maxSize + "]";
	}
}
