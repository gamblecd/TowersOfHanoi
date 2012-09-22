package cli;

import interfaces.Display;
import interfaces.Playable;
import interfaces.Tower;
import interfaces.Towers;
import interfaces.TriTowers;

import java.io.PrintStream;

import entities.Plate;

public class CLIDisplay implements Display {

	private PrintStream output;

	public CLIDisplay(PrintStream out) {
		this.output = out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.Display#display(interfaces.Tower)
	 */
	@Override
	public void display(Tower tower) {
		int maxSize = tower.maxSize();
		StringBuilder base = new StringBuilder();
		StringBuilder towerDisplay = new StringBuilder();
		int middle = (maxSize + 1);
		for (int i = 0; i < maxSize + 2; i++) {
			base.append("mm");
		}

		for (int i = 0; i < (maxSize + 1 - tower.size()); i++) {
			for (int j = 0; j < middle; j++) {
				towerDisplay.append(" ");
			}
			towerDisplay.append("||\n");
		}

		Tower t = tower.cloneTower();

		for (int i = 0; i < tower.size(); i++) {
			Plate temp = t.pop();
			for (int j = 0; j < middle - (temp.size() - 1); j++) {
				towerDisplay.append(" ");
			}
			towerDisplay.append(temp.cliDraw() + "\n");
		}

		towerDisplay.append(base.toString());
		output.println(towerDisplay.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.Display#display(interfaces.Towers)
	 */
	@Override
	public void display(Towers towers) {
		TriTowers triTowers = (TriTowers) towers;
		display(triTowers.getLeftTower());
		display(triTowers.getCenterTower());
		display(triTowers.getRightTower());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.Display#display(interfaces.Playable)
	 */
	@Override
	public void display(Playable playable) {
		output.println("Moves: " + playable.getNumberOfMoves());
	}

}
