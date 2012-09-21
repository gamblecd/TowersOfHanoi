package com.cgamble.games.mobiletowersofhanoi.util;

import abstracts.AbstractThreeWayMove;
import android.widget.Button;

import com.cgamble.games.mobiletowersofhanoi.R;

public class MobileMove extends AbstractThreeWayMove {
	
	public MobileMove (Button from, Button to) {
		switch(from.getId()) {
		case R.id.left_tower:
			this.from = TowerEnum.LEFT;
			break;
		case R.id.center_tower:
			this.from = TowerEnum.CENTER;
			break;
		case R.id.right_tower:
			this.from = TowerEnum.RIGHT;
			break;
		default:
			throw new IllegalArgumentException();
		}
		
		switch(to.getId()) {
		case R.id.left_tower:
			this.to = TowerEnum.LEFT;
			break;
		case R.id.center_tower:
			this.to= TowerEnum.CENTER;
			break;
		case R.id.right_tower:
			this.to = TowerEnum.RIGHT;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

}
