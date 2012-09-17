/**
 * 
 */
package commands;

import java.util.HashMap;
import java.util.Map;

import plates.Plate;
import receivers.Tower;

/**
 * @author $hadow$torm
 *
 */
public class RemovePlateFromTower implements Command {
    private static Map<Tower<Plate>, RemovePlateFromTower> singletonsOfThisCommand;
    private Tower<Plate> tower;
    private Plate plate;

    private static void makeMap() {
        singletonsOfThisCommand = new HashMap<Tower<Plate>, RemovePlateFromTower>();
    }
    private static RemovePlateFromTower makeNewCommand(Tower<Plate> t) {
        RemovePlateFromTower command = new RemovePlateFromTower(t);
        singletonsOfThisCommand.put(t, command);
        return command;
    }
    
    public static RemovePlateFromTower getCommand(Tower<Plate> t) {
        if (singletonsOfThisCommand == null) {
            makeMap();
        }else {
            RemovePlateFromTower command = singletonsOfThisCommand.get(t);
            if (command != null) {
                return command;
            }
        }
        return makeNewCommand(t);
    }
    
    /**
     * 
     */
    public RemovePlateFromTower(Tower<Plate> t) {
        // TODO Auto-generated constructor stub
        tower = t;
    }
    
    /* (non-Javadoc)
     * @see commands.Command#execute()
     */
    @Override
    public boolean execute() {
        plate = tower.pop();
        return plate != null;
    }
    
    public Plate getPlate() {
        return plate;
    }
    
}
