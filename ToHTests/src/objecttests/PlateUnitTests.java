/**
 * 
 */
package objecttests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import plates.Plate;

/**
 * @author $hadow$torm
 *
 */
public class PlateUnitTests {

	private Plate p1;
	private Plate p2;
	private Plate p3;
	private Plate p4;
	private Plate p5;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		p1 = new Plate(1);
		p2 = new Plate(2);
		p3 = new Plate(3);
		p4 = new Plate(4);
		p5 = new Plate(5);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testComparison() {
		assertTrue("Plate 1 is larger than plate 2", 0 > p1.compareTo(p2));
		assertTrue("Plate 3 is smaller than plate 2", 0 < p3.compareTo(p2));
		assertTrue("Plate 5 is not the same size as plate 5", 0 == p5.compareTo(p5));
		assertTrue("Plate 5 is not the same size as plate 5", p5.equals(p5));
	}
	
	@Test
	public void testCliDraw() {
		assertEquals("Failure", p1.cliDraw(), "==");
		assertEquals("Failure", p2.cliDraw(), "====");
		assertEquals("Failure", p3.cliDraw(), "======");
		assertEquals("Failure", p4.cliDraw(), "========");
		assertEquals("Failure", p5.cliDraw(), "==========");
		
		Plate p = new Plate(2, "123");
		assertEquals("Failure", p.cliDraw(), "123123");
	}

}
