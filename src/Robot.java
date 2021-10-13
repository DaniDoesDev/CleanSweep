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
		this.floorPlanSystem = new FloorPlan(this);
		this.flatSensors = new ArrayList();
		this.flatSensors.add(new Sensor(1, this)); // Straight 
		this.flatSensors.add(new Sensor(2, this)); // Right
		this.flatSensors.add(new Sensor(3, this)); // Back
		this.flatSensors.add(new Sensor(4, this)); // Left
		this.downSensor = new Sensor(5, this); // Down
		this.sensorSimulatorSystem = new SensorSimulator(this);
		this.coordinates = "1,1"; // Machine start position
		
		// check for obstacles 
		//this.objectDetectionSystem.read();
		//this.floorPlanSystem.print();
		
	}

	public Integer convertToInteger(char coord) {
		String coordString = "" + coord;
		Integer coordInt = Integer.parseInt(coordString);
		return coordInt;
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
	
	public boolean moveStraight() {
		if (!objectDetectionSystem.blocked("straight")) {

			// Convert string coordinates to ints for accurate incrementing/decrementing
			char xCoord = coordinates.charAt(0);
			char yCoord = coordinates.charAt(2);
			Integer yCoordInt = convertToInteger(yCoord);

			// Update int based on directional movement
			yCoordInt++;

			// Convert back to string and update robot coordinates
			String coords = xCoord + "," + yCoordInt.toString();
			this.coordinates = coords;
			System.out.println("I moved to coordinates " + coords);

			// Return true to indicate that we can and have moved in this direction
			return true;

		} else {
			System.out.println("I can't move up a space, I'm blocked!");
			// Return false to indicate that this way is currently blocked
			return false;
		}
	}

	public boolean moveBack() {
		if (!objectDetectionSystem.blocked("back")) {

			// Convert string coordinates to ints for accurate incrementing/decrementing
			char xCoord = coordinates.charAt(0);
			char yCoord = coordinates.charAt(2);
			Integer yCoordInt = convertToInteger(yCoord);

			// Update int based on directional movement
			yCoordInt--;

			// Convert back to string and update robot coordinates
			String coords = xCoord + "," + yCoordInt.toString();
			this.coordinates = coords;
			System.out.println("I moved to coordinates " + coords);

			// Return true to indicate that we can and have moved in this direction
			return true;

		} else {
			System.out.println("I can't move down a space, I'm blocked!");
			// Return false to indicate that this way is currently blocked
			return false;
		}
	}

	public boolean moveLeft() {
		if (!objectDetectionSystem.blocked("left")) {

			// Convert string coordinates to ints for accurate incrementing/decrementing
			char xCoord = coordinates.charAt(0);
			char yCoord = coordinates.charAt(2);
			Integer xCoordInt = convertToInteger(xCoord);

			// Update int based on directional movement
			xCoordInt--;

			// Convert back to string and update robot coordinates
			String coords = xCoordInt.toString() + "," + yCoord;
			this.coordinates = coords;
			System.out.println("I moved to coordinates " + coords);

			// Return true to indicate that we can and have moved in this direction
			return true;

		} else {
			System.out.println("I can't move one space left, I'm blocked!");
			// Return false to indicate that this way is currently blocked
			return false;
		}
	}

	public boolean moveRight() {
		if (!objectDetectionSystem.blocked("right")) {

			// Convert string coordinates to ints for accurate incrementing/decrementing
			char xCoord = coordinates.charAt(0);
			char yCoord = coordinates.charAt(2);
			Integer xCoordInt = convertToInteger(xCoord);

			// Update int based on directional movement
			xCoordInt++;

			// Convert back to string and update robot coordinates
			String coords = xCoordInt.toString() + "," + yCoord;
			this.coordinates = coords;
			System.out.println("I moved to coordinates " + coords);

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
