
public class Main {

	public static void main(String[] args) {
		Robot cleanSweep = new Robot();
		System.out.println("Clean Sweep initialized - commence one-time cleaning cycle");
		// Just try moving to any available space, pivoting as obstacles are detected
		// Currently this is representative of one "cleaning cycle"
		for(int i = 0; i < 10; i++) {
			if (cleanSweep.moveUp() == false) {
				if (cleanSweep.moveLeft() == false) {
					if (cleanSweep.moveRight() == false) {
						cleanSweep.moveDown();
					}
				}
			}
		}
		cleanSweep.shutdown();
	}

}
