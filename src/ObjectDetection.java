import java.util.ArrayList;
import java.util.HashMap;

public class ObjectDetection {
	private Robot robot;
	public ObjectDetection(Robot robot) {
		this.robot=robot;
	}
	public void read() {
		Boolean obstacle = false; //for test
		for (Sensor i : this.robot.flatSensors) { 
			String thing = i.readFlatSensor();
			// Always write to floor plan
			this.robot.floorPlanSystem.updateFloorPlan(
				i.sensorCoordinate(), thing
			);
			if (thing == "wall" || thing == "door-closed") {
				obstacle=true;//
			}		
		}
		
		ArrayList<Boolean> x = this.robot.downSensor.readDownSensor();
		
		int c=1;
		for (Boolean i :this.robot.downSensor.readDownSensor()) {
			if (i == true) {
				this.robot.floorPlanSystem.updateFloorPlan(
					sensorCoordinate(c), "down-stair/decline"
				);
				obstacle=true;//
				//objects.set(c-1, "down-stair/decline");
			}
			c+=1;
		}
		
		// test
		if (obstacle==true) {
			System.out.println("obstacle detected");
		} else {
			System.out.println("no obstacle detected");
		}
		
		// shutdown?
		if (blocked()) {
			robot.shutdown();
		}
	}
	public String sensorCoordinate(int direction) {
		String[] c = this.robot.coordinates.split(",");
		// 1=straight, 2=right, 3=back, 4=left
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
		return coord;
	}

	public Boolean blocked() {
		for (int c=1; c<5; c++) {
			String thing = this.robot.floorPlanSystem.readFloorPlan(
				sensorCoordinate(c)
			);
			if (thing == "clear" || thing == "charging-base" ||
				thing == "door-open" 
			)
			{
				return false;
			}
		}
		// It's blocked
		return true;
	}
}
