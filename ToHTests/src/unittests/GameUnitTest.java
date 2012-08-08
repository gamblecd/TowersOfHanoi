/**
 * 
 */
package unittests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import main.Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author $hadow$torm
 *
 */
public class GameUnitTest {

	private Game g1;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		g1 = new Game(3);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMovePlate() {
		assertTrue("Unable to make move.", g1.movePlate(Game.LEFT_TOWER, Game.RIGHT_TOWER));
		assertFalse("Able to make move.", g1.movePlate(Game.LEFT_TOWER, Game.RIGHT_TOWER));
		assertTrue("Unable to make move.", g1.movePlate(Game.LEFT_TOWER, Game.MIDDLE_TOWER));
	}
	
	@Test
	public void testCheckWinCondition() {
		g1.movePlate(Game.LEFT_TOWER, Game.RIGHT_TOWER);
		assertFalse("We haven't won yet.", g1.checkWinCondition());
		g1.movePlate(Game.LEFT_TOWER, Game.MIDDLE_TOWER);
		assertFalse("We haven't won yet.", g1.checkWinCondition());
		g1.movePlate(Game.RIGHT_TOWER, Game.MIDDLE_TOWER);
		assertFalse("We haven't won yet.", g1.checkWinCondition());
		g1.movePlate(Game.LEFT_TOWER, Game.RIGHT_TOWER);
		assertFalse("We haven't won yet.", g1.checkWinCondition());
		g1.movePlate(Game.MIDDLE_TOWER, Game.LEFT_TOWER);
		assertFalse("We haven't won yet.", g1.checkWinCondition());
		g1.movePlate(Game.MIDDLE_TOWER, Game.RIGHT_TOWER);
		assertFalse("We haven't won yet.", g1.checkWinCondition());
		g1.movePlate(Game.LEFT_TOWER, Game.RIGHT_TOWER);
		assertTrue("We won.", g1.checkWinCondition());
	}

}
