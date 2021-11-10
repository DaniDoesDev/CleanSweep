package main;
import java.util.ArrayList;
import java.util.Stack;

public class Robot {
	
	public ObjectDetection objectDetectionSystem;
	public FloorPlan floorPlanSystem;
	public ArrayList<Sensor> flatSensors;
	public Sensor downSensor;
	public SensorSimulator sensorSimulatorSystem;
	public Cleaner cleaner;
	public String coordinates;
	public Stack<String> pathToInitialCharger;
	public CSLogger CSLogger;
	
	public Robot() {
		start();
	}
	
	public void start() {
		this.CSLogger = new CSLogger();
		this.objectDetectionSystem = new ObjectDetection(this);
		this.floorPlanSystem = new FloorPlan(this);
		this.flatSensors = new ArrayList<>();
		this.flatSensors.add(new Sensor(1, this)); // Straight 
		this.flatSensors.add(new Sensor(2, this)); // Right
		this.flatSensors.add(new Sensor(3, this)); // Back
		this.flatSensors.add(new Sensor(4, this)); // Left
		this.downSensor = new Sensor(5, this); // Down
		this.sensorSimulatorSystem = new SensorSimulator(this);
		this.cleaner = new Cleaner(this);
		this.coordinates = "1,1"; // Machine start position
		this.pathToInitialCharger = new Stack<>();
		CSLogger.log("position", "1,1");
	}

	public Integer convertToInteger(char coord) {
		String coordString = "" + coord;
		Integer coordInt = Integer.parseInt(coordString);
		return coordInt;
	}
	
	public void moveRobot(String coord, String direction) {
		this.coordinates = coord;
		CSLogger.log("position", this.coordinates);
		CSLogger.log("direction", direction);
	}
	
//	public ArrayList<Integer> parseCoordinates() {
//		String[] c = coordinates.split(",");
//		// 1=straight, 2=right, 3=back, 4=left
//		int x = Integer.parseInt(c[0]);
//		int y = Integer.parseInt(c[1]);
//		ArrayList<Integer> co = new ArrayList<Integer>();
//		co.add(x);
//		co.add(y);
//		return co;
//	}
	
	public boolean moveStraight(boolean returningToCharger) {
		if (!objectDetectionSystem.blocked("straight")) {

			// Convert string coordinates to ints for accurate incrementing/decrementing
			char xCoord = coordinates.charAt(0);
			char yCoord = coordinates.charAt(2);
			Integer yCoordInt = convertToInteger(yCoord);

			// Update int based on directional movement
			yCoordInt++;

			// Convert back to string and update robot coordinates
			String coords = xCoord + "," + yCoordInt.toString();
			//this.coordinates = coords;
			moveRobot(coords, "straight");
			System.out.println("I moved to coordinates " + coords);

			// Push the opposite of this successful movement to path history so we can retrace steps, but only if this moves us further in a direction
			if (!returningToCharger) {
				if (!pathToInitialCharger.isEmpty() && pathToInitialCharger.peek() == "straight") {
					System.out.println("Test pop from stack");
					pathToInitialCharger.pop();
				} else {
					pathToInitialCharger.push("back");
				}
			}

			// Return true to indicate that we can and have moved in this direction
			return true;

		} else {
			System.out.println("I can't move up a space, I'm blocked!");
			// Return false to indicate that this way is currently blocked
			return false;
		} 
	}

	public boolean moveBack(boolean returningToCharger) {
		if (!objectDetectionSystem.blocked("back")) {

			// Convert string coordinates to ints for accurate incrementing/decrementing
			char xCoord = coordinates.charAt(0);
			char yCoord = coordinates.charAt(2);
			Integer yCoordInt = convertToInteger(yCoord);

			// Update int based on directional movement
			yCoordInt--;

			// Convert back to string and update robot coordinates
			String coords = xCoord + "," + yCoordInt.toString();
			//this.coordinates = coords;
			moveRobot(coords, "back");
			System.out.println("I moved to coordinates " + coords);

			// Push the opposite of this successful movement to path history so we can retrace steps
			if (!returningToCharger) {
				if (!pathToInitialCharger.isEmpty() && pathToInitialCharger.peek() == "back") {
					pathToInitialCharger.pop();
				} else {
					pathToInitialCharger.push("straight");
				}
			}

			// Return true to indicate that we can and have moved in this direction
			return true;

		} else {
			System.out.println("I can't move down a space, I'm blocked!");
			// Return false to indicate that this way is currently blocked
			return false;
		}
	}

	public boolean moveLeft(boolean returningToCharger) {
		if (!objectDetectionSystem.blocked("left")) {

			// Convert string coordinates to ints for accurate incrementing/decrementing
			char xCoord = coordinates.charAt(0);
			char yCoord = coordinates.charAt(2);
			Integer xCoordInt = convertToInteger(xCoord);

			// Update int based on directional movement
			xCoordInt--;

			// Convert back to string and update robot coordinates
			String coords = xCoordInt.toString() + "," + yCoord;
			//this.coordinates = coords;
			moveRobot(coords, "left");
			System.out.println("I moved to coordinates " + coords);

			// Push the opposite of this successful movement to path history so we can retrace steps
			if (!returningToCharger) {
				if (!pathToInitialCharger.isEmpty() && pathToInitialCharger.peek() == "left") {
					pathToInitialCharger.pop();
				} else {
					pathToInitialCharger.push("right");
				}
			}
			// Return true to indicate that we can and have moved in this direction
			return true;

		} else {
			System.out.println("I can't move one space left, I'm blocked!");
			// Return false to indicate that this way is currently blocked
			return false;
		}
	}

	public boolean moveRight(boolean returningToCharger) {
		if (!objectDetectionSystem.blocked("right")) {

			// Convert string coordinates to ints for accurate incrementing/decrementing
			char xCoord = coordinates.charAt(0);
			char yCoord = coordinates.charAt(2);
			Integer xCoordInt = convertToInteger(xCoord);

			// Update int based on directional movement
			xCoordInt++;

			// Convert back to string and update robot coordinates
			String coords = xCoordInt.toString() + "," + yCoord;
			//this.coordinates = coords;
			moveRobot(coords, "right");
			System.out.println("I moved to coordinates " + coords);

			// Push the opposite of this successful movement to path history so we can retrace steps
			if (!returningToCharger) {
				if (!pathToInitialCharger.isEmpty() && pathToInitialCharger.peek() == "right") {
					pathToInitialCharger.pop();
				} else {
					pathToInitialCharger.push("left");
				}
			}

			// Return true to indicate that we can and have moved in this direction
			return true;

		} else {
			System.out.println("I can't move one space right, I'm blocked!");
			// Return false to indicate that this way is currently blocked
			return false;
		}
	}

	public void moveToCharger() {
		while (!pathToInitialCharger.isEmpty()) {
			// System.out.println(pathToInitialCharger.pop());
		 	String direction = pathToInitialCharger.pop();
		 	if (direction.equals("straight")) {
		 		moveStraight(true);
		 	} else if (direction.equals("back")) {
		 		moveBack(true);
		 	} else if (direction.equals("left")) {
		 		moveLeft(true);
		 	} else if (direction.equals("right")) {
		 		moveRight(true);
		 	}
		}
		System.out.println("Back at my charging station");
		// Decrement amount of charge we know it will take to get back based on our curr battery to charger value, ***need to update***
		this.cleaner.setCurrBattery(this.cleaner.getCurrBattery() - this.cleaner.getCurrBatteryToCharger());
		System.out.println("My battery is currently at " + this.cleaner.getCurrBattery());
	}

	public boolean isInNeedOfCharge() {
		// At worst, we can lose 3 units of battery per movement
		// We know how much battery required to take our initial path back to our charger, 
		// so check if we pass the threshold (with a buffer of 6 round trip + 3 for potential worst cleaning cycle) where we need to turn back
		if (this.cleaner.getCurrBatteryToCharger() > (this.cleaner.getCurrBattery() - 9)) {
			System.out.println("I need to turn around and move back to my charger now!");
			System.out.println("My curr battery is " + this.cleaner.getCurrBattery());
			System.out.println("My curr battery to charger is " + this.cleaner.getCurrBatteryToCharger());
			return true;
		} else {
			System.out.println("I still have the energy to keep going!");
			return false;
		}
	}

	// Check if we need to return to charger and trigger "empty me" notification
	public boolean needsEmptying() {
		if (this.cleaner.getCurrDirt() > 0) {
			return false;
		}
		System.out.println("My dirt capacity is full, I need to return to the charger and be emptied!");
		return true;
	}

	public void charge() {
		CSLogger.log("charging", "charging"); // charging
		this.cleaner.setCurrBattery(250);
		System.out.println("I'm all charged up and ready to go!");
		CSLogger.log("charging", "charging complete"); // done charging
	}

	public void empty() {
		this.cleaner.setCurrDirt(50);
		System.out.println("My cleaning capacity has been restored!");
	}

	public void shutdown() {
		System.out.println("Robot shutting down!");
	}

 }
