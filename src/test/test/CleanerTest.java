package test;

import main.Robot;
import main.SurfaceLevel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CleanerTest {
	
	@Test
	void testDrainBatteryMovementBothBare() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.drainBatteryMovement(SurfaceLevel.FloorType.BARE, SurfaceLevel.FloorType.BARE);
		assertEquals(249, cleanSweep.cleaner.getCurrBattery());
		assertEquals(1, cleanSweep.cleaner.getCurrBatteryToCharger());
	}

	@Test
	void testDrainBatteryMovementBothLow() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.drainBatteryMovement(SurfaceLevel.FloorType.LOW, SurfaceLevel.FloorType.LOW);
		assertEquals(248, cleanSweep.cleaner.getCurrBattery());
		assertEquals(2, cleanSweep.cleaner.getCurrBatteryToCharger());
	}

	@Test
	void testDrainBatteryMovementBothHigh() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.drainBatteryMovement(SurfaceLevel.FloorType.HIGH, SurfaceLevel.FloorType.HIGH);
		assertEquals(247, cleanSweep.cleaner.getCurrBattery());
		assertEquals(3, cleanSweep.cleaner.getCurrBatteryToCharger());
	}

	@Test
	void testDrainBatteryMovementBareLow() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.drainBatteryMovement(SurfaceLevel.FloorType.BARE, SurfaceLevel.FloorType.LOW);
		assertEquals(248.5, cleanSweep.cleaner.getCurrBattery());
		assertEquals(1.5, cleanSweep.cleaner.getCurrBatteryToCharger());
	}

	@Test
	void testDrainBatteryMovementBareHigh() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.drainBatteryMovement(SurfaceLevel.FloorType.BARE, SurfaceLevel.FloorType.HIGH);
		assertEquals(248, cleanSweep.cleaner.getCurrBattery());
		assertEquals(2, cleanSweep.cleaner.getCurrBatteryToCharger());
	}

	@Test
	void testDrainBatteryMovementLowHigh() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.drainBatteryMovement(SurfaceLevel.FloorType.LOW, SurfaceLevel.FloorType.HIGH);
		assertEquals(247.5, cleanSweep.cleaner.getCurrBattery());
		assertEquals(2.5, cleanSweep.cleaner.getCurrBatteryToCharger());
	}

	@Test
	void testDrainBatteryCleaningBare() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.drainBatteryCleaning(SurfaceLevel.FloorType.BARE);
		assertEquals(249, cleanSweep.cleaner.getCurrBattery());
	}

	@Test
	void testDrainBatteryCleaningLow() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.drainBatteryCleaning(SurfaceLevel.FloorType.LOW);
		assertEquals(248, cleanSweep.cleaner.getCurrBattery());
	}

	@Test
	void testDrainBatteryCleaningHigh() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.drainBatteryCleaning(SurfaceLevel.FloorType.HIGH);
		assertEquals(247, cleanSweep.cleaner.getCurrBattery());
	}

	@Test
	void testCleanSpot() {
		Robot cleanSweep = new Robot();
		cleanSweep.cleaner.cleanSpot();
		assertEquals(49, cleanSweep.cleaner.getCurrDirt());
	}
}