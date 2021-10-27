import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RobotTest {

	@Test
	void testMoveUp() {
		Robot cleanSweep = new Robot();
		if (cleanSweep.moveStraight(false) == false) {
			assertEquals(cleanSweep.coordinates, "1,1" );
		} else {
			assertEquals(cleanSweep.coordinates, "1,2" );
		}
	}
	
	@Test
	void testMoveDown() {
		Robot cleanSweep = new Robot();
		if (cleanSweep.moveBack(false) == false) {
			assertEquals(cleanSweep.coordinates, "1,1" );
		} else {
			assertEquals(cleanSweep.coordinates, "1,0" );
		}
	}
	
	@Test
	void testMoveLeft() {
		Robot cleanSweep = new Robot();
		if (cleanSweep.moveLeft(false) == false) {
			assertEquals(cleanSweep.coordinates, "1,1" );
		} else {
			assertEquals(cleanSweep.coordinates, "0,1" );
		}
	}
	
	@Test
	void testMoveRight() {
		Robot cleanSweep = new Robot();
		if (cleanSweep.moveRight(false) == false) {
			assertEquals(cleanSweep.coordinates, "1,1" );
		} else {
			assertEquals(cleanSweep.coordinates, "2,1" );
		}
	}

}
