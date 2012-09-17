/**
 * 
 */
package commandtests;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import plates.Plate;
import receivers.Tower;

import commands.AddPlateToTower;

/**
 * @author $hadow$torm
 *
 */
public class AddPlateToTowerTest {
    Tower<Plate> t;
    Plate p1;
    Plate p2;
    Plate p3;
    Plate p4;
    Plate p5;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        t = new Tower<Plate>(5);
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
    public void testExecution() {
        AddPlateToTower addCommand = AddPlateToTower.getCommand(t, p1);
        assertTrue(addCommand.execute());
        assertFalse(addCommand.execute());
        assertEquals(p1, t.pop());
    }
    
    @Test
    public void testSetPlate() {
        AddPlateToTower addCommand = AddPlateToTower.getCommand(t);
        assertFalse(addCommand.execute());
        addCommand.setPlate(p5);
        assertTrue(addCommand.execute());
        assertEquals(t.size(), 1);
        assertEquals("Wrong Plate", p5, t.peek());
        addCommand = AddPlateToTower.getCommand(t);
        assertFalse(addCommand.execute());
        addCommand.setPlate(p2);
    }
    
    @Test
    public void testMultipleTowersInMap() {
        Tower<Plate> t2 = new Tower<Plate>(5);
        AddPlateToTower addCommand = AddPlateToTower.getCommand(t, p2);
        addCommand = AddPlateToTower.getCommand(t2, p1);
        addCommand = AddPlateToTower.getCommand(t, p3);
        addCommand.execute();
        assertEquals(p3, t.peek());
        
        addCommand = AddPlateToTower.getCommand(t2);
        addCommand.execute();
        assertEquals(p1, t2.peek());
        
        addCommand = AddPlateToTower.getCommand(t);
        addCommand.execute();
        assertEquals(p3, t.peek());
        
    }
    
}
