package test;

import main.CSLogger;
import main.Robot;
import main.SurfaceLevel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.junit.runners.Parameterized.Parameters;

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
		//CSLogger CSLogger = new CSLogger();
		assertEquals(cleanSweep.CSLogger.logLength(), 2);
		assertEquals(cleanSweep.CSLogger.getLogType(0), "ready");
		assertEquals(cleanSweep.CSLogger.getLogType(1), "position");
	}
	
	@Test
	void testLoggingDateTime() {
		Robot cleanSweep = new Robot();
		String pattern = "^\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}$"; // ex: yyyy/MM/dd HH:mm
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(cleanSweep.CSLogger.getLogDateTime(0));
        assertEquals(m.find(), true);	
	}
	
	@Test
	void testLoggingPosition() {
		Robot cleanSweep = new Robot();
		
		// check log for "position:1,1"
		assertEquals(cleanSweep.CSLogger.getLogType(1), "position");
		assertEquals(cleanSweep.CSLogger.getLogValue(1), "1,1");
		
		// move right
		if (cleanSweep.moveRight(false) == false) {
			//"1,1"
			assertEquals(cleanSweep.CSLogger.getLogType(1), "position");
			assertEquals(cleanSweep.CSLogger.getLogValue(1), "1,1");
		} else {
			//"2,1"
			assertEquals(cleanSweep.CSLogger.getLogType(1), "position");
			assertEquals(cleanSweep.CSLogger.getLogValue(1), "2,1");
		}
	}
	
	@Test
	void testLoggingDirection() {
		Robot cleanSweep = new Robot();
		
		// move right 
		if (cleanSweep.moveRight(false) == false) {
			//no log
			assertEquals(cleanSweep.CSLogger.logLength(), 2);
		} else {
			//check log for "direction:right"
			assertEquals(cleanSweep.CSLogger.getLogType(2), "direction");
			assertEquals(cleanSweep.CSLogger.getLogValue(2), "right");
		}
	}
	
	@Parameters
	public static Iterable<? extends Object> testLoggingSurfaceParam() {
	    return Arrays.asList("BARE", "LOW", "HIGH", "BLOCKED");
	}
	@Test
	void testLoggingSurface() {
		Robot cleanSweep = new Robot();
		cleanSweep.downSensor.returnRandomFloorType();
		// check log for "surface:<BARE,LOW,HIGH,BLOCKED>"	
		assertEquals(cleanSweep.CSLogger.getLogType(2), "surface");
		assertEquals(cleanSweep.CSLogger.getLogValue(2),
			testLoggingSurfaceParam());
	}
	
	@Test
	void testLoggingCleaningStatus() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.cleanSpot();
		// check log for "cleaning:cleaning a spot"
		assertEquals(cleanSweep.CSLogger.getLogType(2), "cleaning");
		assertEquals(cleanSweep.CSLogger.getLogValue(2), "cleaning a spot");
	}
	
	@Test
	void testingLoggingDirtDetected() {
		// Logging awaiting dirt detection system
		Robot cleanSweep = new Robot();
		assertEquals(true, false);
	}
	
	@Test
	void testingLoggingObstacleDetected() {
		Robot cleanSweep = new Robot();
		if (cleanSweep.objectDetectionSystem.blocked("staight")) {
			//check log for "obstacle:<obstacle>"
			assertEquals(cleanSweep.CSLogger.getLogType(2), "obstacle");
			assertEquals(cleanSweep.CSLogger.getLogValue(2), "obstacle found");
		} else {
			//check log for "obstacle:none"
			assertEquals(cleanSweep.CSLogger.getLogType(2), "obstacle");
			assertEquals(cleanSweep.CSLogger.getLogValue(2), "none");
		}
	}
	
	@Test
	void testingLoggingCurrentPower() {
		Robot cleanSweep = new Robot();
		
		// -1
		cleanSweep.cleaner.drainBatteryCleaning(SurfaceLevel.FloorType.BARE);
		//power:249
		assertEquals(cleanSweep.CSLogger.getLogType(2), "power");
		assertEquals(cleanSweep.CSLogger.getLogValue(2), "249");
		
		// -1
		cleanSweep.cleaner.drainBatteryMovement(SurfaceLevel.FloorType.BARE,
			SurfaceLevel.FloorType.BARE);
		//power:248
		assertEquals(cleanSweep.CSLogger.getLogType(3), "power");
		assertEquals(cleanSweep.CSLogger.getLogValue(3), "248");
	}
	
	@Test
	void testingLoggingCharging() {
		Robot cleanSweep = new Robot();
		
		cleanSweep.charge();
		//charging:charging
		//charging:charging complete
		assertEquals(cleanSweep.CSLogger.getLogType(2), "charging");
		assertEquals(cleanSweep.CSLogger.getLogValue(2), "charging");
		assertEquals(cleanSweep.CSLogger.getLogType(3), "charging");
		assertEquals(cleanSweep.CSLogger.getLogValue(3), "charging complete");
	}
}
