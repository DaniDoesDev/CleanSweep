import java.util.HashMap;

public class FloorPlan {
	private HashMap<String, String> floorPlan;
	public FloorPlan() {
		this.floorPlan = new HashMap<String, String>();
	}
	public void updateFloorPlan(String cooridinate, String thing) {
		this.floorPlan.put(cooridinate,thing);
	}
	public void print() {
		System.out.println(this.floorPlan);
	}
}
