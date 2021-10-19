import java.util.*;
public class Cleaner {

    private static final double MAX_BATTERY_CAPACITY = 250;
    private static final double MAX_DIRT_CAPACITY = 50;
    private double currBattery;
    private double currDirt;
    private HashMap<String, String> DirtMap;
    private Robot robot;

    public Cleaner(Robot robot) {
        currBattery = MAX_BATTERY_CAPACITY;
        currDirt = MAX_DIRT_CAPACITY;
        this.robot = robot;
    }

    public double getCurrBattery() {
        return currBattery;
    }

    public double getCurrDirt() {
        return currDirt;
    }

    public void setCurrBattery(double currBattery) {
        this.currBattery = currBattery;
    }

    public void setCurrDirt(double currDirt) {
        this.currDirt = currDirt;
    }

    public void cleanSpot() {
        // This method currently cleans a spot regardless of whether it's dirty or not (no dirt detection present). This needs to be added for cleaning - future state
        // If we have capacity to clean more dirt
        if (currDirt > 0) {
            currDirt--;
            // Here we will also decrement the amount of dirt present at this particular spot for cleaning - future state once dirt detection is complete
            // We only decrement once per cleaning cycle because the sensor cannot tell how much dirt is present (according to PDF) just if an area is clean, so we may need to call this method multiple times elsewhere
            System.out.println("I cleaned one unit of dirt, my current capacity is now " + currDirt + " units");
        }  else {
            System.out.println("My cleaner capacity is maxed out, please return me to the charging station and turn on my 'EMPTY ME' indicator");
        }

        // After cleaning a spot and checking our curr capacity for dirt, if the cleaner is full we want to immediately move back to a charging station and indicate "Empty me"
        if (currDirt == 0) {
            // returnToCharger(); <-- a method like this will need to be added later 
            System.out.println("My cleaner capacity is maxed out, please return me to the charging station and turn on my 'EMPTY ME' indicator");
        }
        
    }



}

