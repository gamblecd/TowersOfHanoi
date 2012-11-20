package com.cgamble.games.mobiletowersofhanoi;

import interfaces.Display;
import interfaces.Move;
import interfaces.Playable;

import java.util.ArrayList;

import abstracts.AbstractHanoiGame;
import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.cgamble.games.mobiletowersofhanoi.util.AndroidThreeTowers;
import com.cgamble.games.mobiletowersofhanoi.util.MobileMove;

import exceptions.IllegalActionException;

public class HanoiGameActivity extends Activity {

	public final String INTENT_KEY_MOVES = "com.cgamble.games.mobiletowersofhanoi.GET_MOVES_FROM_INTENT";
	private Playable game;
	private ToggleButton to;
	private ToggleButton from;
	private final Context context = this;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.app.Activity#onConfigurationChanged(android.content.res.Configuration
	 * )
	 */
	@Override
	public void onConfigurationChanged(final Configuration newConfig) {
		// Ignore orientation change to keep activity from restarting
		super.onConfigurationChanged(newConfig);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hanoi_game);
		game = new AndroidHanoiGame(new MobileDisplay(
				findViewById(R.id.overall_collection)));
		setSpinnerListeners();
		game.createNewGame();
	}

	/**
	 * Helper method to set all spinner listeners when the activity is created.
	 */
	public void setSpinnerListeners() {
		Spinner spinner = (Spinner) findViewById(R.id.difficulty);
		spinner.setOnItemSelectedListener((OnItemSelectedListener) game);
		spinner.setSelection(1); // Default to Medium
	}

	/**
	 * onClick Callback method for when a tower button is pushed.
	 * 
	 * @param v
	 *            the View that was clicked
	 */
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
		} else {
			((ToggleButton) v).setChecked(false);
		}
	}

	/**
	 * Helper method to encapsulate the playable actions
	 */
	private void performMove() {
		game.next();
		if (game.isGameOver()) {
			game.endGame();
		}
	}

	/**
	 * onClick Callback method for when the exit button is clicked. Ends the
	 * activity
	 * 
	 * @param v
	 *            the button that was clicked.
	 */
	public void exit(View v) {
		this.finish();
	}

	/**
	 * onClick Callback method for when the reset button is clicked, it resets
	 * the game.
	 * 
	 * @param v
	 *            the button that was clicked
	 */
	public void reset(View v) {
		game.resetGame();
	}

	private class AndroidHanoiGame extends AbstractHanoiGame implements
			OnItemSelectedListener {
		private Display display;
		private int currentGameSize;

		public AndroidHanoiGame(Display d) {
			this(d, context.getResources().getInteger(R.integer.default_game_size));
		}

		/**
		 * Creates a new Tower of Hanoi game with gameSize plates for use with
		 * an Android Activity
		 * 
		 * @param Display
		 *            the display for this game to interact with.
		 * @param gameSize
		 *            the number of plates to play with
		 */
		public AndroidHanoiGame(Display display, int gameSize) {
			this.display = display;
			currentGameSize = gameSize;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see interfaces.Playable#createNewGame()
		 */
		public Playable createNewGame() {
			display.setPlayableMessage(getString(R.string.welcome_text));
			buildGame(currentGameSize);
			return this;
		}

		@Override
		protected void buildGame(int gameSize) {
			moves = new ArrayList<Move>();
			towers = new AndroidThreeTowers(gameSize);
			resetGame();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see abstracts.AbstractHanoiGame#resetGame()
		 */
		@Override
		public void resetGame() {
			super.resetGame();
			if (from != null)
				from.setChecked(false);
			if (to != null)
				to.setChecked(false);
			from = null;
			to = null;
			display();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see interfaces.Playable#display()
		 */
		public void display() {
			display.display(this);
			display.display(towers);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see interfaces.Playable#endGame()
		 */
		public void endGame() {
			int moves = getNumberOfMoves();
			display.setPlayableMessage(getString(R.string.end_game_text, moves));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see interfaces.Playable#next()
		 */
		public Playable next() {
			Move move = new MobileMove(from, to);
			try {
				move.move(towers);
				moves.add(move);
			} catch (IllegalActionException badMove) {
				display.setPlayableError(getString(R.string.invalid_move));
			}
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.widget.AdapterView.OnItemSelectedListener#onItemSelected(
		 * android.widget.AdapterView, android.view.View, int, long)
		 */
		public void onItemSelected(AdapterView<?> adapter, View v, int pos,
				long id) {
			try {
				int choice = context.getResources().getIntArray(R.array.difficulty_array_values)
				        [((Spinner) adapter).getSelectedItemPosition()];
				currentGameSize = choice;
			} catch (Exception nfe) {
				currentGameSize = context.getResources().getInteger(R.integer.default_game_size);
			}
			createNewGame();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.widget.AdapterView.OnItemSelectedListener#onNothingSelected
		 * (android.widget.AdapterView)
		 */
		public void onNothingSelected(AdapterView<?> arg0) {
			currentGameSize = context.getResources().getInteger(R.integer.default_game_size);
			createNewGame();
		}
	}
}