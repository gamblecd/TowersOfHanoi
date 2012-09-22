package abstracts;

import interfaces.Move;
import interfaces.TriTowers;
import interfaces.Tower;
import entities.Plate;
import exceptions.IllegalActionException;

public abstract class AbstractThreeWayMove implements Move {

	public static enum TowerEnum {
		CENTER, LEFT, RIGHT;

		private Plate getPlateToMove(TriTowers t) {
			switch (this) {
			case LEFT:
				return t.getLeftTower().peek();
			case CENTER:
				return t.getCenterTower().peek();
			case RIGHT:
				return t.getRightTower().peek();
			default:
				return null;
			}
		}

		private Tower getTower(TriTowers t) {
			switch (this) {
			case LEFT:
				return t.getLeftTower();
			case CENTER:
				return t.getCenterTower();
			case RIGHT:
				return t.getRightTower();
			default:
				return null;
			}
		}
	}

	protected TowerEnum from;
	protected TowerEnum to;

	public void move(TriTowers towers) throws IllegalActionException {
		Plate p = from.getPlateToMove(towers);
		if (p != null) {
			if (to.getTower(towers).pushOnto(p)) {
				from.getTower(towers).pop();
			}
		}
		throw new IllegalActionException("Cannot perform the move from " + from
				+ " to " + to + ".");
	}
}
