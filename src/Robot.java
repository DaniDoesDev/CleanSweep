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
}
