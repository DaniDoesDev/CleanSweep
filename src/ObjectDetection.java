import java.util.ArrayList;

public class ObjectDetection {
	private Robot robot;
	public ObjectDetection(Robot robot) {
		this.robot=robot;
	}
	public ArrayList<String> read(){
		Boolean obstacle = false; //for test
		for (Sensor i : this.robot.flatSensors) { 
			String thing=i.readFlatSensor();
			if (thing == "wall" || thing =="door-closed" || thing=="door-open") {
				this.robot.floorPlanSystem.updateFloorPlan(
					i.sensorCooridinate(), thing
				);
				obstacle=true;//
			}		
		}
		
		ArrayList<Boolean> x = this.robot.downSensor.readDownSensor();
		
		int c=1;
		for (Boolean i :this.robot.downSensor.readDownSensor()) {
			if (i == true){
				this.robot.floorPlanSystem.updateFloorPlan(
					sensorCooridinate(c), "down-stair/decline"
				);
				obstacle=true;//
			}
			c+=1;
		}
		
		// test
		if (obstacle==true) {
			System.out.println("obstacle detected");
		}else {
			System.out.println("no obstacle detected");
		}
		
		
		return null;
	}
	public String sensorCooridinate(int direction) {
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
		return coord;
	}
}
