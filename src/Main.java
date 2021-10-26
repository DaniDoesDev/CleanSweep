
public class Main {

	public static void main(String[] args) {
		Robot cleanSweep = new Robot();
		System.out.println("Clean Sweep initialized - commence one-time cleaning cycle");
		// Just try moving to any available space, pivoting as obstacles are detected
		// Currently this is representative of one "cleaning cycle"
		// When you move to a spot, clean it!
		//int i = 0;

		while ((!cleanSweep.isInNeedOfCharge())) {
			SurfaceLevel.FloorType curr = cleanSweep.downSensor.returnRandomFloorType();
			SurfaceLevel.FloorType destination = cleanSweep.downSensor.returnRandomFloorType();

			if (cleanSweep.moveStraight()) {
				// try {
				// 	Thread.sleep(2500);
				// } catch(InterruptedException e) {
				// 	System.out.println("got interrupted!");
				// }
				cleanSweep.cleaner.drainBatteryMovement(curr, destination);
				cleanSweep.cleaner.cleanSpot();
				cleanSweep.cleaner.drainBatteryCleaning(destination);
			} else if (cleanSweep.moveLeft()) {
				// try {
				// 	Thread.sleep(2500);
				// } catch(InterruptedException e) {
				// 	System.out.println("got interrupted!");
				// }
				cleanSweep.cleaner.drainBatteryMovement(curr, destination);
				cleanSweep.cleaner.cleanSpot();
				cleanSweep.cleaner.drainBatteryCleaning(destination);
			} else if (cleanSweep.moveRight()) {
				// try {
				// 	Thread.sleep(2500);
				// } catch(InterruptedException e) {
				// 	System.out.println("got interrupted!");
				// }
				cleanSweep.cleaner.drainBatteryMovement(curr, destination);
				cleanSweep.cleaner.cleanSpot();
				cleanSweep.cleaner.drainBatteryCleaning(destination);
			} else if (cleanSweep.moveBack()) {
				// try {
				// 	Thread.sleep(2500);
				// } catch(InterruptedException e) {
				// 	System.out.println("got interrupted!");
				// }
				cleanSweep.cleaner.drainBatteryMovement(curr, destination);
				cleanSweep.cleaner.cleanSpot();
				cleanSweep.cleaner.drainBatteryCleaning(destination);
			} else {
				System.out.println("Help, I'm surrounded!");
				break;
			}

		}
		cleanSweep.moveToCharger();
		cleanSweep.charge();
		cleanSweep.shutdown();
	}

}
