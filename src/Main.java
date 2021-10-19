
public class Main {

	public static void main(String[] args) {
		Robot cleanSweep = new Robot();
		System.out.println("Clean Sweep initialized - commence one-time cleaning cycle");
		// Just try moving to any available space, pivoting as obstacles are detected
		// Currently this is representative of one "cleaning cycle"
		// When you move to a spot, clean it!
		for(int i = 0; i < 10; i++) {
			if (cleanSweep.moveStraight()) {
				cleanSweep.cleaner.cleanSpot();
			} else if (cleanSweep.moveLeft()) {
				cleanSweep.cleaner.cleanSpot();
			} else if (cleanSweep.moveRight()) {
				cleanSweep.cleaner.cleanSpot();
			} else if (cleanSweep.moveBack()) {
				cleanSweep.cleaner.cleanSpot();
			} else {
				System.out.println("Help, I'm surrounded!");
				break;
			}
			// if (cleanSweep.moveStraight() == false) {
			// 	if (cleanSweep.moveLeft() == false) {
			// 		if (cleanSweep.moveRight() == false) {
			// 			cleanSweep.moveBack();
			// 		}
			// 	}
			// }
		}
		cleanSweep.shutdown();
	}

}
