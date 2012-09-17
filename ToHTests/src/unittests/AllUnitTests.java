/**
 * 
 */
package unittests;

import objecttests.GameUnitTest;
import objecttests.PlateUnitTests;
import objecttests.TowerUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import commandtests.AddPlateToTowerTest;

/**
 * @author $hadow$torm
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ GameUnitTest.class, PlateUnitTests.class, TowerUnitTest.class, AddPlateToTowerTest.class })
public class AllUnitTests {

}
