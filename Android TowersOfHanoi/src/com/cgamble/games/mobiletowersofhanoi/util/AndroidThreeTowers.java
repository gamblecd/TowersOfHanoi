package com.cgamble.games.mobiletowersofhanoi.util;

import abstracts.AbstractThreeTowers;

import com.cgamble.games.mobiletowersofhanoi.R;

import entities.Plate;

public class AndroidThreeTowers extends AbstractThreeTowers {

	public AndroidThreeTowers(int sizeOfGame) {
		this.left = new AndroidHanoiTower(sizeOfGame, R.id.left_tower);
		this.center = new AndroidHanoiTower(sizeOfGame, R.id.center_tower);
		this.right = new AndroidHanoiTower(sizeOfGame, R.id.right_tower);
		for (int i = 0; i < sizeOfGame; i++) {
			this.left.pushOnto(new Plate(sizeOfGame - i));
		}
		this.numberOfPlates = sizeOfGame;
	}
}
