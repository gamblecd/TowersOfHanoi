package abstracts;

import interfaces.Move;
import interfaces.Towers;
import interfaces.Tower;
import entities.Plate;

public abstract class AbstractThreeWayMove implements Move {

	public static enum TowerEnum {
		CENTER, LEFT, RIGHT;

		private Plate getPlateToMove(Towers t) {
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
		
		private Tower getTower(Towers t) {
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
	
	public boolean move(Towers towers) {
		Plate p = from.getPlateToMove(towers);
		if (p != null) {
			if (to.getTower(towers).pushOnto(p)) {
				from.getTower(towers).pop();
				return true;
			}
		}
		return false;
	}
}
