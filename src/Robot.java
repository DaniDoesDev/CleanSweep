import java.util.ArrayList;

public class Robot {
	
	public ObjectDetection objectDetectionSystem;
	public FloorPlan floorPlanSystem;
	public ArrayList<Sensor> flatSensors;
	public Sensor downSensor;
	public SensorSimulator sensorSimulatorSystem;
	public String coordinates; 
	
	public Robot() {
		start();
	}
	
	public void start() {
		this.objectDetectionSystem = new ObjectDetection(this);
		this.floorPlanSystem = new FloorPlan();
		this.flatSensors = new ArrayList();
		this.flatSensors.add(new Sensor(1, this)); // Straight 
		this.flatSensors.add(new Sensor(2, this)); // Right
		this.flatSensors.add(new Sensor(3, this)); // Back
		this.flatSensors.add(new Sensor(4, this)); // Left
		this.downSensor = new Sensor(5, this); // Down
		this.sensorSimulatorSystem = new SensorSimulator(this);
		this.coordinates = "1,1"; // Machine start position
		// check for obstacles 
		this.objectDetectionSystem.read();
		this.floorPlanSystem.print();
		
	}

	public boolean moveUp() {
		if (!objectDetectionSystem.blocked()) {

			// Convert string coordinates to ints for accurate incrementing/decrementing
			char xCoord = coordinates.charAt(0);
			char yCoord = coordinates.charAt(2);
			String xCoordString = "" + xCoord;
			String yCoordString = "" + yCoord;
			Integer xCoordInt = Integer.parseInt(xCoordString);
			Integer yCoordInt = Integer.parseInt(yCoordString);

			// Update int based on directional movement
			yCoordInt++;

			// Convert back to string and update robot coordinates
			String coords = xCoordInt.toString() + "," + yCoordInt.toString();
			this.coordinates = coords;

			// Return true to indicate that we can and have moved in this direction
			return true;

		} else {
			System.out.println("I can't move up a space, I'm blocked!");
			// Return false to indicate that this way is currently blocked
			return false;
		}
	}

	public boolean moveDown() {
		if (!objectDetectionSystem.blocked()) {

			// Convert string coordinates to ints for accurate incrementing/decrementing
			char xCoord = coordinates.charAt(0);
			char yCoord = coordinates.charAt(2);
			String xCoordString = "" + xCoord;
			String yCoordString = "" + yCoord;
			Integer xCoordInt = Integer.parseInt(xCoordString);
			Integer yCoordInt = Integer.parseInt(yCoordString);

			// Update int based on directional movement
			yCoordInt--;

			// Convert back to string and update robot coordinates
			String coords = xCoordInt.toString() + "," + yCoordInt.toString();
			this.coordinates = coords;

			// Return true to indicate that we can and have moved in this direction
			return true;

		} else {
			System.out.println("I can't move down a space, I'm blocked!");
			// Return false to indicate that this way is currently blocked
			return false;
		}

	}

	public boolean moveLeft() {
		if (!objectDetectionSystem.blocked()) {

			// Convert string coordinates to ints for accurate incrementing/decrementing
			char xCoord = coordinates.charAt(0);
			char yCoord = coordinates.charAt(2);
			String xCoordString = "" + xCoord;
			String yCoordString = "" + yCoord;
			Integer xCoordInt = Integer.parseInt(xCoordString);
			Integer yCoordInt = Integer.parseInt(yCoordString);

			// Update int based on directional movement
			xCoordInt--;

			// Convert back to string and update robot coordinates
			String coords = xCoordInt.toString() + "," + yCoordInt.toString();
			this.coordinates = coords;

			// Return true to indicate that we can and have moved in this direction
			return true;

		} else {
			System.out.println("I can't move one space left, I'm blocked!");
			// Return false to indicate that this way is currently blocked
			return false;
		}

	}

	public boolean moveRight() {
		if (!objectDetectionSystem.blocked()) {

			// Convert string coordinates to ints for accurate incrementing/decrementing
			char xCoord = coordinates.charAt(0);
			char yCoord = coordinates.charAt(2);
			String xCoordString = "" + xCoord;
			String yCoordString = "" + yCoord;
			Integer xCoordInt = Integer.parseInt(xCoordString);
			Integer yCoordInt = Integer.parseInt(yCoordString);

			// Update int based on directional movement
			xCoordInt++;

			// Convert back to string and update robot coordinates
			String coords = xCoordInt.toString() + "," + yCoordInt.toString();
			this.coordinates = coords;

			// Return true to indicate that we can and have moved in this direction
			return true;

		} else {
			System.out.println("I can't move one space right, I'm blocked!");
			// Return false to indicate that this way is currently blocked
			return false;
		}

	}

	public void shutdown() {
		System.out.println("Robot shutting down!");
	}

 }
