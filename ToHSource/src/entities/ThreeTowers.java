package entities;

import abstracts.AbstractThreeTowers;
import interfaces.TriTowers;



public class ThreeTowers extends AbstractThreeTowers implements TriTowers {

	public ThreeTowers(int sizeOfGame) {
		this.left = new HanoiTower(sizeOfGame);
		this.center = new HanoiTower(sizeOfGame);
		this.right = new HanoiTower(sizeOfGame);
		for (int i = 0; i < sizeOfGame; i++) {
			this.left.pushOnto(new Plate(sizeOfGame - i));
		}
		this.numberOfPlates = sizeOfGame;
	}

}
