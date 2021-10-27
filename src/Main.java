
public class Main {

	public static void main(String[] args) {
		Robot cleanSweep = new Robot();
		System.out.println("Clean Sweep initialized - commence one-time cleaning cycle");
		// Just try moving to any available space, pivoting as obstacles are detected
		// Currently this is representative of one "cleaning cycle"
		// When you move to a spot, clean it!
		//int i = 0;

		while (!cleanSweep.isInNeedOfCharge() && !cleanSweep.needsEmptying()) {
			SurfaceLevel.FloorType curr = cleanSweep.downSensor.returnRandomFloorType();
			SurfaceLevel.FloorType destination = cleanSweep.downSensor.returnRandomFloorType();

			if (cleanSweep.moveStraight()) {
			} else if (cleanSweep.moveLeft()) {
			} else if (cleanSweep.moveRight()) {
			} else if (cleanSweep.moveBack()) {
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
		}

		// Clean Sweep now needs to charge or be emptied
		cleanSweep.moveToCharger();
		cleanSweep.charge();
		cleanSweep.empty();
		cleanSweep.shutdown();
	}

}
