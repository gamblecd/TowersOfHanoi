/**
 * 
 */
package unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import unit.entities.HanoiTowerUnitTest;
import unit.entities.PlateUnitTests;

/**
 * @author $hadow$torm
 *
 */
@RunWith(Suite.class)
@SuiteClasses({PlateUnitTests.class, HanoiTowerUnitTest.class})
public class AllUnitTests {

}
