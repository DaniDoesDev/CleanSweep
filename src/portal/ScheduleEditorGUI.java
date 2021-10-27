package portal;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScheduleEditorGUI {

	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private static JFrame parentFrame; // Reference to parent frame 
    private static JFrame jf; // This frame
    private static CleanPortal CP; // Reference to portal
    private static JComboBox cb;

    
    public static void addComponentsToPane(Container pane) {
    	JLabel label;
    	JButton button;
    	JTextField text; 
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    	
    	if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        
	    pane.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    ArrayList<String> schedules = CP.schedule.readFromFile();
	    // String s1[] = { "Jalpaiguri", "Mumbai", "Noida", "Kolkata", "New Delhi" };
	    System.out.println(schedules.toString());
	    cb = new JComboBox(schedules.toArray());
        cb.setEditable(false);
//        combo.setSize(125, 25);
        c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 1;
	    c.gridx = 0;
	    c.gridy = 0;
        pane.add(cb, c); // Add combo to panel
        
        button = new JButton("Delete schedule!");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 1;
	    c.gridx = 0;
	    c.gridy = 1;
	    //c.gridwidth = 6;
	    pane.add(button, c);
	    
        button.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        		public void run() {
	        			// delete cb by id
	        			try {
	        				CleanPortal.schedule.deleteSchedule(cb.getSelectedIndex());
	        			} catch (Exception e) {
	        				// If schedule is empty
	        			}
	        			parentFrame.setVisible(true);
	        			jf.dispose();
	        		}
	        	});
	        }
	    });

	    
//	    
//	    Integer index = 1;
//	    for (String line : schedules) {
//	    	combo.addItem(new JComboItem(1, "1"));
//	    	
//	    	label = new JLabel("Schedule (" + index.toString() + "): " + line);
//		    c.weightx = 0.5;
//		    c.fill = GridBagConstraints.HORIZONTAL;
//		    c.gridx = 0;
//		    c.gridy = index - 1;
//		    //c.gridwidth = 3;
//		    pane.add(label, c);
//		    
//		    
//		    //
//		    button.addActionListener(new ActionListener() {
//		        @Override
//		        public void actionPerformed(ActionEvent e) {
//		        	javax.swing.SwingUtilities.invokeLater(new Runnable() {
//		        		public void run() {
//		        			parentFrame.setVisible(true);
//		        			jf.dispose();
//		        		}
//		        	});
//		        }
//		    });
//	    }
	 
	    
	    
	    
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    static void createAndShowGUI(JFrame pf, CleanPortal c) {
    	parentFrame = pf;
    	CP = c;
        //Create and set up the window.
        jf = new JFrame("GridBagLayoutDemo");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(10,10,700,600); 

        
        //Set up the content pane.
        addComponentsToPane(jf.getContentPane());
 
        //Display the window.
        //frame.pack();
        jf.setVisible(true);
    }
 
}