package com.cgamble.games.mobiletowersofhanoi;

import interfaces.Move;
import interfaces.Playable;
import abstracts.AbstractHanoiGame;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.cgamble.games.mobiletowersofhanoi.util.MobileMove;

import entities.Plate;
import exceptions.IllegalActionException;

public class HanoiGameActivity extends Activity {

	public final String INTENT_KEY_MOVES = "com.cgamble.games.mobiletowersofhanoi.GET_MOVES_FROM_INTENT";
	private Playable game;
	private ToggleButton to;
	private ToggleButton from;

	@Override
	public void onConfigurationChanged(final Configuration newConfig) {
		// Ignore orientation change to keep activity from restarting
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		game = new AndroidHanoiGame(this);
		setContentView(R.layout.activity_hanoi_game);
		setSpinnerListeners();
		game.createNewGame();
	}

	public void setSpinnerListeners() {
		Spinner spinner = (Spinner) findViewById(R.id.difficulty);
		spinner.setOnItemSelectedListener((OnItemSelectedListener) game);
		spinner.setSelection(1); // Default to Medium
	}

	@SuppressLint("NewApi")
	public void makeMove(View v) {
		if (!game.isGameOver()) {
			if (from == null) {
				from = ((ToggleButton) v);
				from.setChecked(true);
				return;
			} else if (from.equals(v)) {
				from.setChecked(false);
				from = null;
				return;
			}
	
			if (to == null) {
				to = ((ToggleButton) v);
				to.setPressed(true);
			}
			// Both from and to are filled at this point.
			assert (to != null) && (from != null);
			performMove();
			
			to.setChecked(false);
			from.setChecked(false);
			to = null;
			from = null;
			game.display();
		}else {
			((ToggleButton) v).setChecked(false);
		}
	}

	private void performMove() {
		game.next();
		if (game.isGameOver()) {
			game.endGame();
		}
	}

	public void exit(View v) {
		this.finish();
	}

	public void reset(View v) {
		game.resetGame();
	}

	private class AndroidHanoiGame extends AbstractHanoiGame implements
			OnItemSelectedListener {

		private final int DEFAULT_GAME_SIZE = 5;
		private final int ERROR_COLOR = Color.RED;
		private final int NORMAL_COLOR = Color.WHITE;
		private int currentGameSize = DEFAULT_GAME_SIZE;
		private String message = "";
		private String error = "";

		private Activity context;

		public AndroidHanoiGame(Activity a) {
			this(a, 5);
		}

		public AndroidHanoiGame(Activity a, int gameSize) {
			context = a;
			currentGameSize = gameSize;
		}

		@Override
		public Playable createNewGame() {
			// Starts the Activity to get the number of plates to start the game
			// with.
			message = getString(R.string.welcome_text);
			buildGame(currentGameSize);
			return this;
		}

		@Override
		public void resetGame() {
			super.resetGame();
			if (from != null) from.setChecked(false);
			if (to != null) to.setChecked(false);
			from = null;
			to = null;
			display();
		}

		@Override
		public void display() {
			// update text for buttons
			ToggleButton button;
			Plate p;
			String display;

			button = (ToggleButton) findViewById(R.id.left_tower);
			p = towers.getLeftTower().peek();
			display = p != null ? p.size() + "" : "--";
			button.setTextOn(display);
			button.setTextOff(display);
			button.setText(display);

			button = (ToggleButton) findViewById(R.id.center_tower);
			p = towers.getCenterTower().peek();
			display = p != null ? p.size() + "" : "--";
			button.setTextOn(display);
			button.setTextOff(display);
			button.setText(display);

			button = (ToggleButton) findViewById(R.id.right_tower);
			p = towers.getRightTower().peek();
			display = p != null ? p.size() + "" : "--";
			button.setTextOn(display);
			button.setTextOff(display);
			button.setText(display);

			TextView movesText = (TextView) findViewById(R.id.moves_text);
			movesText.setText("Moves: " + getNumberOfMoves());

			// Update other things
			TextView messageBox = (TextView) findViewById(R.id.message_box_text);
			if (error != "") {
				messageBox.setTextColor(ERROR_COLOR);
				messageBox.setText(error);
				error = "";
			} else {
				messageBox.setTextColor(NORMAL_COLOR);
				messageBox.setText(message);
				message = "";
			}
		}

		@Override
		public void endGame() {
			int moves = getNumberOfMoves();
			message = getString(R.string.end_game_text, moves);
			//error = getString(R.string.end_game_text, moves);
		}

		private boolean performMove(Move m) {
			try {
				m.move(towers);
			} catch (IllegalActionException badMove) {
				return false;
			}
			return true;
		}

		@Override
		public Playable next() {
			Move move = new MobileMove(from, to);
			if (performMove(move)) {
				this.moves.add(move);
			} else {
				error = getString(R.string.invalid_move);
			}
			return this;
		}

		@Override
		public void onItemSelected(AdapterView<?> adapter, View v, int pos,
				long id) {
			try {
				String choice = ((TextView) v).getText().toString();
				currentGameSize = Integer.parseInt(choice.substring(choice
						.length() - 1));
			} catch (Exception nfe) {
				currentGameSize = DEFAULT_GAME_SIZE;
			}
			createNewGame();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			currentGameSize = DEFAULT_GAME_SIZE;
			createNewGame();
		}
	}
}