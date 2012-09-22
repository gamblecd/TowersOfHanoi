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
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		t1 = new HanoiTower(3);
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
}
