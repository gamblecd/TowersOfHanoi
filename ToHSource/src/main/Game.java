/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.List;

import plates.Plate;
import towers.Tower;

/**
 * @author $hadow$torm
 *
 */
public class Game {
	public static final int LEFT_TOWER = 0;
	public static final int MIDDLE_TOWER = 1;
	public static final int RIGHT_TOWER = 2;
	
	private int moveCounter;
	private final int gameSize;
	private Tower<Plate> leftTower;
	private Tower<Plate> middleTower;
	private Tower<Plate> rightTower;
	private final List<Tower<Plate>> towers = new ArrayList<Tower<Plate>>(); 
	
	
	/**
	 * 
	 * @param n
	 */
	public Game(int n) {
		if ((n < 3) || (n % 2 == 0)) {
			throw new IllegalArgumentException();
		}
		gameSize = n;
		buildGame();
		resetGame();
	}
	
	private void buildGame() {
		leftTower = new Tower<Plate>(gameSize);
		middleTower = new Tower<Plate>(gameSize);
		rightTower = new Tower<Plate>(gameSize);
		towers.add(leftTower);
		towers.add(middleTower);
		towers.add(rightTower);
	}
	
	public void resetGame() {
		for (int i=gameSize; i>0; i--) {
			leftTower.push(new Plate(i));
		}
		moveCounter=0;
	}
	
	public int getMovesCount() {
		return moveCounter;
	}
	
	public boolean movePlate(int towerFrom, int towerTo) {
		assert(towerTo < towers.size());
		assert(towerFrom < towers.size());
		Tower<Plate> t1 = towers.get(towerFrom);
		Tower<Plate> t2 = towers.get(towerTo);
		assert(t2.peek() != null);
		
		if (t2.push(t1.peek())) {
			t1.pop();
			moveCounter++;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkWinCondition() {
		return (((middleTower.size() == gameSize) && ((leftTower.size() == 0) && (rightTower.size() == 0))) ||
				((rightTower.size() == gameSize) && ((leftTower.size() == 0) && (middleTower.size() == 0))));
	}
}
