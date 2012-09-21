/**
 * 
 */
package unit.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.HanoiTower;
import entities.Plate;


/**
 * @author $hadow$torm
 *
 */
public class HanoiTowerUnitTest {

	private HanoiTower t1;
	private HanoiTower t2;
	private HanoiTower t3;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		t1 = new HanoiTower(3);
		t2 = new HanoiTower(5);
		t3 = new HanoiTower(8);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPush() {
		assertFalse("Can Push to t1", t1.pushOnto(new Plate(4)));
		assertTrue("Cannot Push to t1", t1.pushOnto(new Plate(3)));
		assertFalse("Can Push to t1", t1.pushOnto(new Plate(3)));
		assertFalse("Can Push to t1", t1.pushOnto(new Plate(3)));
		assertFalse("Can Push to t1", t1.pushOnto(new Plate(4)));
		assertTrue("Cannot Push to t1", t1.pushOnto(new Plate(2)));
		assertTrue("Cannot Push to t1", t1.pushOnto(new Plate(1)));
		assertFalse("Can Push to t1", t1.pushOnto(new Plate(0)));
	}
	
	@Test
	public void testPeek() {
		t1.pushOnto(new Plate(3));
		int size = t1.size();
		assertEquals("Not equals", t1.peek(), new Plate(3));
		assertEquals("Not equals", size, t1.size());
	}

	@Test
	public void testPop() {
		t1.pushOnto(new Plate(2));
		assertEquals("Not equals", t1.pop(), new Plate(2));
		assertEquals("Not equals", 0, t1.size());
	}
	
	@Test
	public void testCLIDraw() {
		assertEquals("Failure", t1.cliDraw(),
				"    ||\n" + 
				"    ||\n" +
				"    ||\n" +
				"    ||\n" +
				"mmmmmmmmmm");
		assertEquals("Failure", t2.cliDraw(),
				"      ||\n" +
				"      ||\n" +
				"      ||\n" +
				"      ||\n" +
				"      ||\n" +
				"      ||\n" +
				"mmmmmmmmmmmmmm");
		assertEquals("Failure", t3.cliDraw(),
				"         ||\n" +
				"         ||\n" +
				"         ||\n" +
				"         ||\n" +
				"         ||\n" +
				"         ||\n" +
				"         ||\n" +
				"         ||\n" +
				"         ||\n" +
				"mmmmmmmmmmmmmmmmmmmm");
		t2.pushOnto(new Plate(5));
		t2.pushOnto(new Plate(4));
		t2.pushOnto(new Plate(3));
		t2.pushOnto(new Plate(2));
		t2.pushOnto(new Plate(1));
		t3.pushOnto(new Plate(7));
		t3.pushOnto(new Plate(6));
		t3.pushOnto(new Plate(5));
		t3.pushOnto(new Plate(4));
		t3.pushOnto(new Plate(3));
		t3.pushOnto(new Plate(2));
		t3.pushOnto(new Plate(1));
		assertEquals("Failure", t3.cliDraw(),
				"         ||\n" +
				"         ||\n" +
				"         ==\n" +
				"        ====\n" +
				"       ======\n" +
				"      ========\n" +
				"     ==========\n" +
				"    ============\n" +
				"   ==============\n" +
				"mmmmmmmmmmmmmmmmmmmm");
		assertEquals("Failure", t2.cliDraw(),
				"      ||\n" +
				"      ==\n" +
				"     ====\n" +
				"    ======\n" +
				"   ========\n" +
				"  ==========\n" +
				"mmmmmmmmmmmmmm");
	}

}
