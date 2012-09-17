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
public class AddPlateToTower implements Command {
    private static Map<Tower<Plate>, AddPlateToTower> singletonsOfThisCommand;
    private Tower<Plate> tower;
    private Plate plate;
    
    private static void makeMap() {
        singletonsOfThisCommand = new HashMap<Tower<Plate>, AddPlateToTower>();
    }
    private static AddPlateToTower makeNewCommand(Tower<Plate> t, Plate p) {
        AddPlateToTower command = new AddPlateToTower(t, p);
        singletonsOfThisCommand.put(t, command);
        return command;
    }
    
    public static AddPlateToTower getCommand(Tower<Plate> t) {
        return getCommand(t, null);
    }
    
    public static AddPlateToTower getCommand(Tower<Plate> t, Plate p) {
        if (singletonsOfThisCommand == null) {
            makeMap();
        }else {
            AddPlateToTower command = singletonsOfThisCommand.get(t);
            if (command != null) {
                if (p != null)
                    command.setPlate(p);
                return command;
            }
        }
        return makeNewCommand(t, p);
    }

    /**
     * 
     */
    private AddPlateToTower(Tower<Plate> t, Plate p) {
        tower = t;
        plate = p;
    }
    
    public void setPlate(Plate p) {
        plate = p;
    }
    
    /* (non-Javadoc)
     * @see commands.Command#execute()
     */
    @Override
    public boolean execute() {
        if (plate == null) return false;
        
        return tower.push(plate);
    }
    
}
