/**
 * 
 */
package objecttests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import plates.Plate;
import receivers.Tower;

/**
 * @author $hadow$torm
 *
 */
public class TowerUnitTest {

	private Tower<Plate> t1;
	private Tower<Plate> t2;
	private Tower<Plate> t3;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		t1 = new Tower<Plate>(3);
		t2 = new Tower<Plate>(5);
		t3 = new Tower<Plate>(8);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPush() {
		assertFalse("Can Push to t1", t1.push(new Plate(4)));
		assertTrue("Cannot Push to t1", t1.push(new Plate(3)));
		assertFalse("Can Push to t1", t1.push(new Plate(3)));
		assertFalse("Can Push to t1", t1.push(new Plate(3)));
		assertFalse("Can Push to t1", t1.push(new Plate(4)));
		assertTrue("Cannot Push to t1", t1.push(new Plate(2)));
		assertTrue("Cannot Push to t1", t1.push(new Plate(1)));
		assertFalse("Can Push to t1", t1.push(new Plate(0)));
	}
	
	@Test
	public void testPeek() {
		t1.push(new Plate(3));
		int size = t1.size();
		assertEquals("Not equals", t1.peek(), new Plate(3));
		assertEquals("Not equals", size, t1.size());
	}

	@Test
	public void testPop() {
		t1.push(new Plate(2));
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
		t2.push(new Plate(5));
		t2.push(new Plate(4));
		t2.push(new Plate(3));
		t2.push(new Plate(2));
		t2.push(new Plate(1));
		t3.push(new Plate(7));
		t3.push(new Plate(6));
		t3.push(new Plate(5));
		t3.push(new Plate(4));
		t3.push(new Plate(3));
		t3.push(new Plate(2));
		t3.push(new Plate(1));
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
