package main;
import java.util.ArrayList;
import java.util.Random;

public class Sensor {
	private int direction;
	private Robot robot;
	public Sensor(int direction, Robot robot) {
		this.direction = direction;
		this.robot = robot;
	}
	
	public String sensorCoordinate() {
		String[] c = this.robot.coordinates.split(",");
		// 1=straight, 2=right, 3=back, 4=left, 5=down
		int x = Integer.parseInt(c[0]);
		int y = Integer.parseInt(c[1]);
		switch(this.direction) {
			case 1:
				y = y + 1;
				break;
			case 2:
				x = x + 1;
				break;
			case 3:
				y = y - 1;
				break;
			case 4:
				x = x - 1;
				break;
		}
		String coord = x + "," + y; 
		return coord;
	}
	public ArrayList<Boolean> readDownSensor(){
		return this.robot.sensorSimulatorSystem.readDownSensor();
	}
	public String readFlatSensor() {
	    return this.robot.sensorSimulatorSystem.readFlatSensor(this.direction);
	}

	// This currently returns a random floor type, once floor types are added to the layout itself it will be updated to return floor type at curr spot
	public SurfaceLevel.FloorType returnRandomFloorType() {
		Random rn = new Random();
		int randNum = rn.nextInt(3) + 1;
		return SurfaceLevel.getSurfaceLevel(randNum);
	}

	// This currently returns a random dirt amount, once dirt detection is implemented it will return the amount of dirt located at this grid spot
	public boolean isDirty() {
		Random rn = new Random();
		int randNum = rn.nextInt(2) + 1;
		if (randNum == 1) {
			System.out.println("This spot needs cleaning");
			return true;
		}
		System.out.println("This spot is clean");
		return false;
	}


}
