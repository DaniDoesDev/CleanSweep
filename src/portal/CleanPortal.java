package portal;


/*
 * GridBagLayoutDemo.java requires no other files.
 * https://docs.oracle.com/javase/tutorial/displayCode.html?
 *   code=https://docs.oracle.com/javase/tutorial/uiswing/examples
 *   /layout/GridBagLayoutDemoProject/src/layout/GridBagLayoutDemo.java
 */
 
public class CleanPortal {
	// This is a singleton class
	
	public static Schedule schedule; 
	
	public CleanPortal() {
		new CleanPortalGUI(this).createAndShowGUI();
		schedule = new Schedule();
	}
	
	
	
 
}

