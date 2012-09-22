package com.cgamble.games.mobiletowersofhanoi;

import interfaces.Display;
import interfaces.Playable;
import interfaces.Tower;
import interfaces.Towers;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.cgamble.games.mobiletowersofhanoi.HanoiGameActivity.AndroidHanoiGame;
import com.cgamble.games.mobiletowersofhanoi.util.AndroidHanoiTower;

import entities.Plate;

public class MobileDisplay implements Display {
	private View context;
	private final int ERROR_COLOR = Color.RED;
	private final int NORMAL_COLOR = Color.WHITE;
	private String error;
	private String message;
	
	
	public MobileDisplay(View context) {
		this.context = context;
	}

	@Override
	public void display(Tower tower) {
		ToggleButton button;
		String displayText;
		Plate p;
		AndroidHanoiTower t = (AndroidHanoiTower) tower;
		button = (ToggleButton) context.findViewById(t.getId());
		p = tower.peek();
		displayText = p != null ? p.size() + "" : "--";
		button.setTextOn(displayText);
		button.setTextOff(displayText);
		button.setText(displayText);
	}

	@Override
	public void display(Playable playable) {
		TextView movesText = (TextView) context.findViewById(R.id.moves_text);
		movesText.setText("Moves: " + playable.getNumberOfMoves());

		// Update other things
		TextView messageBox = (TextView) context.findViewById(R.id.message_box_text);
		if (error != null) {
			messageBox.setTextColor(ERROR_COLOR);
			messageBox.setText(error);
			error = null;
		} else {
			messageBox.setTextColor(NORMAL_COLOR);
			messageBox.setText(message);
			message = null;
		}
	}

	@Override
	public void display(Towers towers) {
		// update text for buttons
		for (Tower t: towers.getAllTowers()) {
			display(t);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see interfaces.Display#setPlayableError(java.lang.String)
	 */
	public void setPlayableError(String error) {
		this.error = error;
	}

	/*
	 * (non-Javadoc)
	 * @see interfaces.Display#setPlayableMessage(java.lang.String)
	 */
	public void setPlayableMessage(String message) {
		this.message = message;
	}
}
