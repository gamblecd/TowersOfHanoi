package com.cgamble.games.mobiletowersofhanoi.util;

import entities.HanoiTower;

public class AndroidHanoiTower extends HanoiTower {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8761583601120898846L;
	private int id;
	
	public AndroidHanoiTower(int maxSize, int id) {
		super(maxSize);
		this.id = id;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
}