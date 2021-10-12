import java.util.ArrayList;
import java.util.HashMap;

public class FloorPlan {
	private HashMap<String, String> floorPlan;
	public FloorPlan() {
		this.floorPlan = new HashMap<String, String>();
	}
	public void updateFloorPlan(String coordinate, String thing) {
		this.floorPlan.put(coordinate, thing);
	}
	public void print() {
		System.out.println(this.floorPlan);
	}
	public String readFloorPlan(String coordinate) {
		return this.floorPlan.get(coordinate);
	}
	public HashMap<String,String> getFloorPlan(){
		return this.floorPlan;
	}
}
