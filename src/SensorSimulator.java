import java.util.ArrayList;
import java.util.HashMap;

public class SensorSimulator {
	private HashMap<String, String> floorPlan;
	private ArrayList<String> obstacles = new ArrayList<String>();
	private Robot robot;
	
	public SensorSimulator(Robot robot) {
		// clear, obstacle, wall, door-closed, door-open, stair/incline,
		// down-stair/decline, charging-base
		// ex: k = "4,5" v = "wall"
		this.robot = robot;
		this.floorPlan = new HashMap<String, String>();
		//this.obstacles.add("clear");
		this.obstacles.add("obstacle");
		this.obstacles.add("wall");
		this.obstacles.add("door-closed");
		this.obstacles.add("door-open");
		this.obstacles.add("stair/incline");
		this.obstacles.add("down-stair/decline"); // =(clear for other sensors)
		//this.obstacles.add("charging-base");
		
		for (int i = 0; i<10; i++) {
			for (int j = 0; j<10; j++) {
				if (i == 0 || i == 9) { // Puts wall on outside
					this.floorPlan.put(i + "," + j, "wall");
				} else if (j == 0 || j == 9) { // Puts wall on outside
					this.floorPlan.put(i + "," + j, "wall");
				} else {
					if (Math.random() > 0.2) { // =0-1
						this.floorPlan.put(i + "," + j, "clear");
					} else {
						int index = (int)(Math.random() * this.obstacles.size());  
			            this.floorPlan.put(i + "," + j, obstacles.get(index));
					}
				}
			}
		}
		
		// The robot's starting location is always clear.
		this.floorPlan.put(this.robot.coordinates, "clear");
		
		// Add a randomly placed charging base
		int x = (int)(Math.random() * 7) + 1;
		int y = (int)(Math.random() * 7) + 1;
		this.floorPlan.put(x + "," + y, "charging-base");
	}
	
	public ArrayList<Boolean> readDownSensor() {
		// Returns array of Straight, Right, Back, Left
		// ex: [False, False, False, False]
		
		ArrayList<Boolean> d = new ArrayList<Boolean>();
		String[] c = this.robot.coordinates.split(",");
		int x = Integer.parseInt(c[0]);
		int y = Integer.parseInt(c[1]);
		// Straight
		d.add(this.floorPlan.get(x + "," + (y + 1)) == "down-stair/decline");
		// Right
		d.add(this.floorPlan.get((x + 1) + "," + y) == "down-stair/decline");
		// Back
		d.add(this.floorPlan.get(x + "," + (y - 1)) == "down-stair/decline");
		// Left
		d.add(this.floorPlan.get((x - 1) + "," + y) == "down-stair/decline");
		
		return d;
	}
	public String readFlatSensor(int direction) {
		// Assumption: down sensor sees stair/decline in every
		// direction.
		String[] c = this.robot.coordinates.split(",");
		// 1=straight, 2=right, 3=back, 4=left, 5=down
		int x = Integer.parseInt(c[0]);
		int y = Integer.parseInt(c[1]);
		switch(direction) {
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
		
		// Special case: A down-stair/decline for a
		// regular sensor is seen as "clear"
		if (this.floorPlan.get(coord) == "down-stair/decline") {
			return "clear";
		}
		
		return this.floorPlan.get(coord);
		//return this.floorPlan.get(x + "," + y);
	}
}
