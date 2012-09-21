package entities;

import interfaces.Move;
import interfaces.Tower;
import interfaces.Towers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class ThreeTowers implements Towers {

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
	public boolean applyMove(Move m) {
		return m.move(this);
	}

	@Override
	public Iterable<HanoiTower> getAllTowers() {
		List<HanoiTower> iter = new LinkedList<HanoiTower>();
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
	
	public String toString() {
		return  "ThreeTowers[" + 
				"LEFT=" + left + ", " + 
				"CENTER=" + center + ", " + 
				"RIGHT=" + center + "]";
	}

}
