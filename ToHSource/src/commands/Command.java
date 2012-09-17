/**
 * 
 */
package commands;


/**
 * @author $hadow$torm
 *
 */
public interface Command {
    
    /**
     * Executes the command of the implementing class
     * @return whether or not the command was successfully executed.
     */
    public boolean execute();
}
