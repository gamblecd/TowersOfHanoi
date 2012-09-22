package entities;

import interfaces.Move;
import interfaces.Tower;
import interfaces.TriTowers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import exceptions.IllegalActionException;

public class ThreeTowers implements TriTowers {

	private HanoiTower center;
	private HanoiTower left;
	private int numberOfPlates;
	private HanoiTower right;

	public ThreeTowers(int sizeOfGame) {
		this.left = new HanoiTower(sizeOfGame);
		this.center = new HanoiTower(sizeOfGame);
		this.right = new HanoiTower(sizeOfGame);
		for (int i = 0; i < sizeOfGame; i++) {
			this.left.pushOnto(new Plate(sizeOfGame - i));
		}
		this.numberOfPlates = sizeOfGame;
	}

	@Override
	public void applyMove(Move m) throws IllegalActionException {
		m.move(this);
	}

	@Override
	public Iterable<Tower> getAllTowers() {
		List<Tower> iter = new LinkedList<Tower>();
		iter.add(left);
		iter.add(center);
		iter.add(right);
		return Collections.unmodifiableCollection(iter);
	}

	@Override
	public HanoiTower getCenterTower() {
		return center;
	}

	@Override
	public Tower getLeftTower() {
		return left;
	}

	@Override
	public int getPlateCount() {
		return numberOfPlates;
	}

	@Override
	public HanoiTower getRightTower() {
		return right;
	}

	@Override
	public String toString() {
		return "ThreeTowers[" + "LEFT=" + left + ", " + "CENTER=" + center
				+ ", " + "RIGHT=" + center + "]";
	}

}
