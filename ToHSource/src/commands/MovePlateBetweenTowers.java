/**
 * 
 */
package commands;

import java.util.HashMap;
import java.util.Map;

import plates.Plate;
import receivers.Tower;
import util.Pair;

/**
 * @author $hadow$torm
 *
 */
public class MovePlateBetweenTowers implements Command {
    private static Map<Pair<Tower<Plate>, Tower<Plate>>, MovePlateBetweenTowers> singletonsOfThisCommand;
    private Tower<Plate> towerFrom;
    private Tower<Plate> towerTo;
    
    private static void makeMap() {
        singletonsOfThisCommand = new HashMap<Pair<Tower<Plate>, Tower<Plate>>, MovePlateBetweenTowers>();
    }
    private static MovePlateBetweenTowers makeNewCommand(Tower<Plate> t, Tower<Plate> t2) {
        MovePlateBetweenTowers command = new MovePlateBetweenTowers(t, t2);
        singletonsOfThisCommand.put(new Pair<Tower<Plate>, Tower<Plate>>(t, t2), command);
        return command;
    }
    
    public static MovePlateBetweenTowers getCommand(Tower<Plate> t, Tower<Plate> t2) {
        if (singletonsOfThisCommand == null) {
            makeMap();
        }else {
            MovePlateBetweenTowers command = singletonsOfThisCommand.get(t);
            if (command != null) {
                return command;
            }
        }
        return makeNewCommand(t, t2);
    }
    /**
     * 
     */
    public MovePlateBetweenTowers(Tower<Plate> t, Tower<Plate> t2) {
        towerFrom = t;
        towerTo = t2;
    }
    
    /* (non-Javadoc)
     * @see commands.Command#execute()
     */
    @Override
    public boolean execute() {
        RemovePlateFromTower removeCommand = RemovePlateFromTower.getCommand(towerFrom);
        Plate p;
        if (removeCommand.execute()) {
            p = removeCommand.getPlate();
        }else {
            return false;
        }
        AddPlateToTower addCommand = AddPlateToTower.getCommand(towerTo, p);
        if (addCommand.execute()) {
            return true;
        } else {
            //Set the plate back on tower it came from
            addCommand = AddPlateToTower.getCommand(towerFrom, p);
            addCommand.execute();
        }
        return false;
    }
    
}
