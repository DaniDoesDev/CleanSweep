package main;
//import java.util.*;

public class Cleaner {

    private static final double MAX_BATTERY_CAPACITY = 250;
    private static final double MAX_DIRT_CAPACITY = 50;
    private double currBattery;
    private double currDirt;
    // private HashMap<String, String> DirtMap;
    private Robot robot;// reference to robot
    private double currBatteryToCharger = 0;

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

    public double getCurrBatteryToCharger() {
        return currBatteryToCharger;
    }

    public void setCurrBattery(double currBattery) {
        this.currBattery = currBattery;
    }

    public void setCurrDirt(double currDirt) {
        this.currDirt = currDirt;
    }

    public void setCurrBatteryToCharger(double battery) {
        this.currBatteryToCharger = battery;
    }

    public void cleanSpot() {
    	// This method currently cleans a spot regardless of whether it's dirty or not (no dirt detection present). This needs to be added for cleaning - future state
        
    	// If we have capacity to clean more dirt
        if (currDirt > 0) {
            currDirt--;
            robot.CSLogger.log("cleaning", "cleaning spot");
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

    public void drainBatteryMovement(SurfaceLevel.FloorType curr, SurfaceLevel.FloorType destination) {
        double batteryLost = 0;
        if (curr == destination) {
            if (curr == SurfaceLevel.FloorType.BARE) {
                currBattery -= 1;
                batteryLost = 1;
            } else if (curr == SurfaceLevel.FloorType.LOW) {
                currBattery -= 2;
                batteryLost = 2;
            } else if (curr == SurfaceLevel.FloorType.HIGH) {
                currBattery -= 3;
                batteryLost = 3;
            }
            // Otherwise floor types are different, get the average
        } else {
            if ((curr == SurfaceLevel.FloorType.BARE || destination == SurfaceLevel.FloorType.BARE)
                    && (curr == SurfaceLevel.FloorType.LOW || destination == SurfaceLevel.FloorType.LOW)) {
                currBattery -= 1.5;
                batteryLost = 1.5;
            } else if ((curr == SurfaceLevel.FloorType.BARE || destination == SurfaceLevel.FloorType.BARE)
                    && (curr == SurfaceLevel.FloorType.HIGH || destination == SurfaceLevel.FloorType.HIGH)) {
                currBattery -= 2;
                batteryLost = 2;
            } else if ((curr == SurfaceLevel.FloorType.LOW || destination == SurfaceLevel.FloorType.LOW)
                    && (curr == SurfaceLevel.FloorType.HIGH || destination == SurfaceLevel.FloorType.HIGH)) {
                currBattery -= 2.5;
                batteryLost = 2.5;
            }
        }
        System.out.println("The battery level is currently at " + getCurrBattery() + " units of charge");
        // Increment the amount of charge necessary to get to the initial charger using the same path
        currBatteryToCharger += batteryLost;
        if (getCurrBattery() == 0) {
            System.out.println("I have run out of power, not good!");
        }
        robot.CSLogger.log("power", String.valueOf(currBattery));
    }

    public void drainBatteryCleaning(SurfaceLevel.FloorType curr) {
        if (curr == SurfaceLevel.FloorType.BARE) {
            currBattery -= 1;
        } else if (curr == SurfaceLevel.FloorType.LOW) {
            currBattery -= 2;
        } else if (curr == SurfaceLevel.FloorType.HIGH) {
            currBattery -= 3;
        }
        System.out.println("The battery level is currently at " + getCurrBattery() + " units of charge");
        if (getCurrBattery() == 0) {
            System.out.println("I have run out of power, not good!");
        }
        robot.CSLogger.log("power", String.valueOf(currBattery));
    }

}

