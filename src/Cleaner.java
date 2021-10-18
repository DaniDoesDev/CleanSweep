import java.util.*;
public class Cleaner {

    private static final double MAX_BATTERY_CAPACITY = 250;
    private static final double MAX_DIRT_CAPACITY = 50;
    private double currBattery;
    private double currDirt;
    private HashMap<String, String> DirtMap;

    public Cleaner() {
        currBattery = MAX_BATTERY_CAPACITY;
        currDirt = MAX_DIRT_CAPACITY;
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



}

