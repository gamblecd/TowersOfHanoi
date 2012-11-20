package entities;

import interfaces.Tower;

import java.util.Stack;

public class HanoiTower extends Stack<Plate> implements Tower {
	private static final long serialVersionUID = 2547452368202527253L;
	private int maxSize;

	/**
	 * Creates a new tower with a capacity of max. Max must be greater than 0
	 * because a tower cannot be forced to have nothing on it.
	 * 
	 * @param max
	 *            > 0.
	 */
	public HanoiTower(int max) {
		if (max < 1)
			throw new IllegalArgumentException(
					"Integer max must b greater 0. A Tower cannot have a max of 0.");
		maxSize = max;
	}

	private boolean equals(HanoiTower t) {
		return (this.maxSize == t.maxSize) && super.equals(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Tower#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof HanoiTower)
			return this.equals((HanoiTower) other);
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Tower#hashCode()
	 */
	@Override
	public int hashCode() {
		return maxSize ^ super.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Tower#peek()
	 */
	@Override
	public Plate peek() {
		if (isEmpty())
			return null;
		return super.peek().clone();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Tower#pop()
	 */
	@Override
	public Plate pop() {
		if (isEmpty())
			return null;
		return super.pop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Tower#pushOnto(entities.Plate)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Tower#clone()
	 */
	public Tower cloneTower() {
		HanoiTower p = (HanoiTower) this.clone();
		p.maxSize = this.maxSize;
		return p;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Tower#toString()
	 */
	@Override
	public String toString() {
		return "Tower[size=" + size() + ", maxSize=" + maxSize + "]";
	}

	public int maxSize() {
		return maxSize;
	}
}
