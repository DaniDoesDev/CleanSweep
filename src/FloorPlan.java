import java.util.HashMap;

public class FloorPlan {
	private HashMap<String, String> floorPlan; // Coordinate, Objects at location
	private Robot robot;
	public FloorPlan(Robot robot) {
		this.floorPlan = new HashMap<String, String>();
		this.robot = robot;
	}
	public void updateFloorPlan(String coordinate, String thing) {
		this.floorPlan.put(coordinate, thing);
	}
	public void print() {
		System.out.println(this.floorPlan);
	}
	public String readFloorPlanCoordinate(String coordinate) {
		return this.floorPlan.get(coordinate);
	}
	public HashMap<String,String> readFloorPlan(){
		// Return hashmap of the objects in four directions
		// {"straight": "wall", "right":..., "back":..., "left:...}
		HashMap<String, String> h = new HashMap<>();
		h.put("straight", readFloorPlanCoordinate(sensorCoordinate("straight")));
		h.put("right", readFloorPlanCoordinate(sensorCoordinate("right")));
		h.put("back", readFloorPlanCoordinate(sensorCoordinate("back")));
		h.put("left", readFloorPlanCoordinate(sensorCoordinate("left")));
		return h;
	}
	public HashMap<String,String> getFloorPlan(){
		return this.floorPlan;
	}
	private String sensorCoordinate(String direction) {
		String[] c = this.robot.coordinates.split(",");
		// 1=straight, 2=right, 3=back, 4=left
		int x = Integer.parseInt(c[0]);
		int y = Integer.parseInt(c[1]);
		switch(direction) {
			case "straight":
				y = y + 1;
				break;
			case "right":
				x = x + 1;
				break;
			case "back":
				y = y - 1;
				break;
			case "left":
				x = x - 1;
				break;
		}
		String coord = x + "," + y; 
		return coord;
	}
}
