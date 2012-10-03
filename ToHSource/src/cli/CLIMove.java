package cli;

import abstracts.AbstractThreeWayMove;

public class CLIMove extends AbstractThreeWayMove {
	public CLIMove(int from, int to) {
		switch (from) {
		case 1:
			this.from = TowerEnum.LEFT;
			break;
		case 2:
			this.from = TowerEnum.CENTER;
			break;
		case 3:
			this.from = TowerEnum.RIGHT;
			break;
		default:
			throw new IllegalArgumentException("Not a legitimate tower");
		}

		switch (to) {
		case 1:
			this.to = TowerEnum.LEFT;
			break;
		case 2:
			this.to = TowerEnum.CENTER;
			break;
		case 3:
			this.to = TowerEnum.RIGHT;
			break;
		default:
			throw new IllegalArgumentException("Not a legitimate tower");
		}
	}
}
