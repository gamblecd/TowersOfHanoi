package interfaces;

import entities.HanoiTower;

public interface Towers {
	
	public boolean applyMove(Move m);
	
	public Iterable<HanoiTower> getAllTowers();

	public HanoiTower getCenterTower();

	public Tower getLeftTower();
	
	public int getPlateCount();
	
	public HanoiTower getRightTower();
}
