
public class Main {



	public static void main(String[] args) {
		Robot cleanSweep = new Robot();
		System.out.println("Clean Sweep initialized - commence one-time cleaning cycle");
		// Just try moving to any available space, pivoting as obstacles are detected
		// Currently this is representative of one "cleaning cycle"
		// When you move to a spot, clean it!
		int i = 0;

		while (!cleanSweep.isInNeedOfCharge() || i < 20) {
			SurfaceLevel.FloorType curr = cleanSweep.downSensor.returnRandomFloorType();
			SurfaceLevel.FloorType destination = cleanSweep.downSensor.returnRandomFloorType();

			if (cleanSweep.moveStraight()) {
				cleanSweep.cleaner.drainBatteryMovement(curr, destination);
				cleanSweep.cleaner.cleanSpot();
				cleanSweep.cleaner.drainBatteryCleaning(destination);
				System.out.println("Help, I'm moving up!");

			} else if (cleanSweep.moveLeft()) {
				cleanSweep.cleaner.drainBatteryMovement(curr, destination);
				cleanSweep.cleaner.cleanSpot();
				cleanSweep.cleaner.drainBatteryCleaning(destination);
				System.out.println("Help, I'm moving left!");

			} else if (cleanSweep.moveRight()) {
				cleanSweep.cleaner.drainBatteryMovement(curr, destination);
				cleanSweep.cleaner.cleanSpot();
				cleanSweep.cleaner.drainBatteryCleaning(destination);
				System.out.println("Help, I'm moving right!");

			} else if (cleanSweep.moveBack()) {
				cleanSweep.cleaner.drainBatteryMovement(curr, destination);
				cleanSweep.cleaner.cleanSpot();
				cleanSweep.cleaner.drainBatteryCleaning(destination);
				System.out.println("Help, I'm moving back!");

			} else {
				System.out.println("Help, I'm surrounded!");
			}
			i++;

		}
		cleanSweep.moveToCharger();
		cleanSweep.shutdown();
	}

}
