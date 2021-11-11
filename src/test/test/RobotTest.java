package test;

import main.CSLogger;
import main.Robot;
import main.SurfaceLevel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
//import org.junit.runners.Parameterized.Parameters;

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
	
	@Test
	void testLoggingRobotOn() {
		Robot cleanSweep = new Robot();
		// a new file
		// "[<date>][<time>]:ready:"
		// "[<date>][<time>]:position:1,1"
		assertEquals(cleanSweep.CSLogger.getLogLength(), 2);
		ArrayList<String> n1 = cleanSweep.CSLogger.getLog(0);
		ArrayList<String> n2 = cleanSweep.CSLogger.getLog(1);
		assertEquals(n1.get(1), "ready");
		assertEquals(n2.get(1), "position");
	}
	
	@Test
	void testLoggingDateTime() {
		Robot cleanSweep = new Robot();
		ArrayList<String> n1 = cleanSweep.CSLogger.getLog(0);
		
		String pattern = "^\\w+ \\d+, \\d+ \\d+:\\d+:\\d+ \\w+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(n1.get(0));
        assertEquals(m.find(), true);	
	}
	
	@Test
	void testLoggingPosition() {
		Robot cleanSweep = new Robot();
		ArrayList<String> n;
		// log0=ready, 1=position, 2=obstacle
		// log0=ready, 1=position, 2=obstacle, 3=position
		
		// check log for "position:1,1"
		n = cleanSweep.CSLogger.getLog(1);
		assertEquals(n.get(1), "position");
		assertEquals(n.get(2), "1,1");
		
		// move right
		if (cleanSweep.moveRight(false) == false) {
			// blocked, log doesn't change
			assertEquals(cleanSweep.CSLogger.getLogLength(), 3);
		} else {
			//"2,1"
			n = cleanSweep.CSLogger.getLog(3);
			assertEquals(n.get(1), "position");
			assertEquals(n.get(2), "2,1");
		}
	}
	
	@Test
	void testLoggingDirection() {
		Robot cleanSweep = new Robot();
		ArrayList<String> n;
		// log0=ready, 1=position, 2=obstacle, 3=position, 4=dir
		
		// move right 
		if (cleanSweep.moveRight(false) == false) {
			//no log
			assertEquals(cleanSweep.CSLogger.getLogLength(), 3);
		} else {
			assertEquals(cleanSweep.CSLogger.getLogLength(), 5);
			//check log for "direction:right"
			n = cleanSweep.CSLogger.getLog(4);
			assertEquals(n.get(1), "direction");
			assertEquals(n.get(2), "right");
		}
	}
	
	@Test
	void testLoggingSurface() {
		Robot cleanSweep = new Robot();
		cleanSweep.downSensor.returnRandomFloorType();
		// log0=ready, 1=position, 2=surface
		// check log for "surface:<BARE,LOW,HIGH,BLOCKED>"
		ArrayList<String> n = cleanSweep.CSLogger.getLog(2);
		ArrayList<String> x = new ArrayList<String>();
		x.add("BARE");
		x.add("LOW");
		x.add("HIGH");
		x.add("BLOCKED");
		assertEquals(n.get(1), "surface");
		assertTrue(x.contains(n.get(2)));
	}
	
	@Test
	void testLoggingCleaningStatus() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.cleanSpot();
		// log0=ready, 1=position, 2=cleaning
		// check log for "cleaning:cleaning spot"
		ArrayList<String> n = cleanSweep.CSLogger.getLog(2);
		
		assertEquals(n.get(1), "cleaning");
		assertEquals(n.get(2), "cleaning spot");
	}
	
// TODO: Awaiting dirt detection
//	@Test
//	void testingLoggingDirtDetected() {
//		Robot cleanSweep = new Robot();
//		assertEquals(true, true);
//	}
	
	@Test
	void testingLoggingObstacleDetected() {
		// log0=ready, 1=position, 2=obstacle
		Robot cleanSweep = new Robot();
		ArrayList<String> n;
		
		// move right
		if (cleanSweep.moveRight(false) == false) {
			// blocked
			n = cleanSweep.CSLogger.getLog(2);
			assertEquals(n.get(1), "obstacle");
			assertEquals(n.get(2), "obstacle found");
		} else {
			// clear
			n = cleanSweep.CSLogger.getLog(2);
			assertEquals(n.get(1), "obstacle");
			assertEquals(n.get(2), "none");
		}
	}
	
	@Test
	void testingLoggingCurrentPower() {
		// log0=ready, 1=position
		Robot cleanSweep = new Robot();
		ArrayList<String> n;
		
		// -1
		cleanSweep.cleaner.drainBatteryCleaning(SurfaceLevel.FloorType.BARE);
		n = cleanSweep.CSLogger.getLog(2);
		assertEquals(n.get(1), "power");
		assertEquals(n.get(2), "249.0");

		// -2
		cleanSweep.cleaner.drainBatteryCleaning(SurfaceLevel.FloorType.LOW);
		n = cleanSweep.CSLogger.getLog(3);
		assertEquals(n.get(1), "power");
		assertEquals(n.get(2), "247.0");
		
		// -3
		cleanSweep.cleaner.drainBatteryCleaning(SurfaceLevel.FloorType.HIGH);
		n = cleanSweep.CSLogger.getLog(4);
		assertEquals(n.get(1), "power");
		assertEquals(n.get(2), "244.0");
		
		// -1
		cleanSweep.cleaner.drainBatteryMovement(SurfaceLevel.FloorType.BARE,
			SurfaceLevel.FloorType.BARE);
		n = cleanSweep.CSLogger.getLog(5);
		assertEquals(n.get(1), "power");
		assertEquals(n.get(2), "243.0");
	}
	
	@Test
	void testingLoggingCharging() {
		// log0=ready, 1=position, 2=charging, 3=charging
		Robot cleanSweep = new Robot();
		ArrayList<String> n;
		
		cleanSweep.charge();
		
		//charging:charging
		n = cleanSweep.CSLogger.getLog(2);
		assertEquals(n.get(1), "charging");
		assertEquals(n.get(2), "charging");
		
		//charging:charging complete
		n = cleanSweep.CSLogger.getLog(3);
		assertEquals(n.get(1), "charging");
		assertEquals(n.get(2), "charging complete");
	}

	@Test
	void testMoveToCharger() {
		Robot cleanSweep = new Robot();
		cleanSweep.moveStraight(false);
		cleanSweep.moveLeft(false);
		cleanSweep.moveStraight(false);
		cleanSweep.moveBack(false);
		cleanSweep.moveRight(false);
		cleanSweep.moveToCharger();
		assertEquals(cleanSweep.coordinates, "1,1");
	}

	@Test
	void testIsInNeedOfChargeFalse() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.setCurrBattery(60);
		cleanSweep.cleaner.setCurrBatteryToCharger(50);
		assertEquals(false, cleanSweep.isInNeedOfCharge());
	}

	@Test
	void testIsInNeedOfChargeBorder() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.setCurrBattery(59);
		cleanSweep.cleaner.setCurrBatteryToCharger(50);
		assertEquals(false, cleanSweep.isInNeedOfCharge());
	}

	@Test
	void testIsInNeedOfChargeTrue() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.setCurrBattery(58);
		cleanSweep.cleaner.setCurrBatteryToCharger(50);
		assertEquals(true, cleanSweep.isInNeedOfCharge());
	}

	@Test
	void testNeedsEmptyingFalse() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.setCurrDirt(1);
		assertEquals(false, cleanSweep.needsEmptying());
	}

	@Test
	void testNeedsEmptyingTrue() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.setCurrDirt(0);
		assertEquals(true, cleanSweep.needsEmptying());
	}

	@Test
	void testCharge() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.setCurrBattery(0);
		cleanSweep.charge();
		assertEquals(250, cleanSweep.cleaner.getCurrBattery());
	}

	@Test
	void testEmpty() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.setCurrDirt(0);
		cleanSweep.empty();
		assertEquals(50, cleanSweep.cleaner.getCurrDirt());
	}
}