package com.cgamble.games.mobiletowersofhanoi;

import interfaces.Playable;
import interfaces.Tower;
import interfaces.Towers;
import abstracts.AbstractDisplay;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.cgamble.games.mobiletowersofhanoi.util.AndroidHanoiTower;

import entities.Plate;

public class MobileDisplay extends AbstractDisplay {
	private View context;
	private final int ERROR_COLOR = Color.RED;
	private final int NORMAL_COLOR = Color.WHITE;
	private String error;
	private String message;
	
	
	public MobileDisplay(View context) {
		this.context = context;
	}

	/*
	 * (non-Javadoc)
	 * @see interfaces.Display#display(interfaces.Tower)
	 */
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

	/*
	 * (non-Javadoc)
	 * @see interfaces.Display#display(interfaces.Playable)
	 */
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

	/*
	 * (non-Javadoc)
	 * @see interfaces.Display#display(interfaces.Towers)
	 */
	public void display(Towers towers) {
		// update text for buttons
		for (Tower t: towers.getAllTowers()) {
			display(t);
		}
	}
}
