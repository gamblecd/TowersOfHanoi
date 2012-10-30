package abstracts;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import entities.HanoiTower;
import exceptions.IllegalActionException;
import interfaces.Move;
import interfaces.Tower;
import interfaces.TriTowers;

public abstract class AbstractThreeTowers implements TriTowers {

	protected HanoiTower center;
	protected HanoiTower left;
	protected int numberOfPlates;
	protected HanoiTower right;

	public AbstractThreeTowers() {
		super();
	}

	public void applyMove(Move m) throws IllegalActionException {
		m.move(this);
	}

	public Iterable<Tower> getAllTowers() {
		List<Tower> iter = new LinkedList<Tower>();
		iter.add(left);
		iter.add(center);
		iter.add(right);
		return Collections.unmodifiableCollection(iter);
	}

	public HanoiTower getCenterTower() {
		return center;
	}

	public Tower getLeftTower() {
		return left;
	}

	public int getPlateCount() {
		return numberOfPlates;
	}

	public HanoiTower getRightTower() {
		return right;
	}

	@Override
    public String toString() {
		return "ThreeTowers[" + "LEFT=" + left + ", " + "CENTER=" + center
				+ ", " + "RIGHT=" + center + "]";
	}

}