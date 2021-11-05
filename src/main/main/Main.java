package main;
// import portal.*; // Portal can run from the Robot if required

public class Main {

	public static void main(String[] args) {
		Robot cleanSweep = new Robot();
		// Todo: Read from schedules.csv and use 
		//       ScheduledExecutorService to add tell the robot
		//       to start running on these dates
		System.out.println("Clean Sweep initialized - commence one-time cleaning cycle");
		// Just try moving to any available space, pivoting as obstacles are detected
		// Currently this is representative of one "cleaning cycle"
		// When you move to a spot, clean it if it's dirty!


		while (!cleanSweep.isInNeedOfCharge() && !cleanSweep.needsEmptying()) {
			SurfaceLevel.FloorType curr = cleanSweep.downSensor.returnRandomFloorType();
			SurfaceLevel.FloorType destination = cleanSweep.downSensor.returnRandomFloorType();

			if (cleanSweep.moveStraight(false)) {
			} else if (cleanSweep.moveLeft(false)) {
			} else if (cleanSweep.moveRight(false)) {
			} else if (cleanSweep.moveBack(false)) {
			} else {
				System.out.println("Help, I'm surrounded!");
				break;
			}
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				System.out.println("got interrupted!");
			}
			cleanSweep.cleaner.drainBatteryMovement(curr, destination);

			if (cleanSweep.downSensor.isDirty()) {
				cleanSweep.cleaner.cleanSpot();
				cleanSweep.cleaner.drainBatteryCleaning(destination);
			}

			System.out.println("--------------------------------------");
		}

		// Clean Sweep now needs to charge or be emptied
		cleanSweep.moveToCharger();
		cleanSweep.charge();
		cleanSweep.empty();
		cleanSweep.shutdown();
	}

}
