package com.cgamble.games.mobiletowersofhanoi;

import interfaces.Playable;
import interfaces.Tower;
import interfaces.Towers;
import abstracts.AbstractDisplay;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.cgamble.games.mobiletowersofhanoi.util.AndroidHanoiTower;

import entities.Plate;

@SuppressLint("NewApi")
public class MobileDisplay extends AbstractDisplay {
	private View context;
	private final int ERROR_COLOR = Color.RED;
	private final int NORMAL_COLOR = Color.WHITE;
	private int maxPlateCount;
	private int plateUnitWidth;
	private int plateHeight;
	
	
	public MobileDisplay(View context) {
		this(context, context.getResources().getInteger(R.integer.default_game_size));
	}
	
	public MobileDisplay(View context, int gameSize) {
	    this.context = context;
	    this.maxPlateCount = gameSize;
	    this.plateHeight = context.findViewById(R.id.tower_collection).getHeight() / maxPlateCount;
	    this.plateUnitWidth = context.findViewById(R.id.tower_collection).getWidth() / (3 * maxPlateCount);
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

		setTowerImageOnButton(t, button);
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
	
	private Drawable makeImageFrom(Tower t) {
	    RectShape shape;
        Drawable shapeDrawable;
        Drawable[] plateLayers = new Drawable[t.size() + 1];
        shape = new RectShape();
        shapeDrawable = new ShapeDrawable(shape);
        shapeDrawable.setColorFilter(Color.YELLOW, Mode.SRC);
        plateLayers[0] = shapeDrawable;
        
        if (t.size() > 0) {
            Tower t2 = t.cloneTower();
            Plate top = t.peek();
            
            for (int i=1; i<=t.size(); i++) {
                Plate p = t2.pop();
                int plateWidth = plateUnitWidth * p.size();
                shape = new OvalShape();
                shape.resize(plateWidth, plateHeight);
                if (p.equals(top)) {
                    //Special Case top plate
                    shapeDrawable = new ShapeDrawable(shape);
                    shapeDrawable.setColorFilter(Color.RED, Mode.SRC);
                }else {
                    shapeDrawable = new ShapeDrawable(shape);
                }
                plateLayers[i] = shapeDrawable;
            }
        
        }
        
        LayerDrawable layers = new LayerDrawable(plateLayers);
        return layers;
	}
	
	private void setTowerImageOnButton(Tower t, ToggleButton button) {
	    button.setBackground(makeImageFrom(t));
	}
}
