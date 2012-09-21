/**
 * 
 */
package interfaces;


/**
 * @author $hadow$torm
 *
 */
public interface TowerCommand {
    
    /**
     * Executes the command of the implementing class
     * @return whether or not the command was successfully executed.
     */
    public boolean execute();
}
