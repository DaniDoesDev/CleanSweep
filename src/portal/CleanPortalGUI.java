package portal;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CleanPortalGUI {

	
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private static JFrame jf;
    private static CleanPortal CP;
    
    public CleanPortalGUI(CleanPortal c) {
    	CP = c;
    }
    
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
	    
	    label = new JLabel("Welcome to Clean Portal!");
	    label.setHorizontalAlignment(SwingConstants.CENTER);
	    label.setFont(new Font("Courier", Font.BOLD, 40));
	    c.weightx = 0.5;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.anchor = GridBagConstraints.CENTER;
	    c.gridx = 0;
	    c.gridy = 0;
	    c.gridwidth = 2;
	    pane.add(label, c);
	    
	    // add repeat schedule
	    button = new JButton("Add new repeating schedule");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 1;
	    c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 2;
	    pane.add(button, c);
	    button.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        		public void run() {
	        			jf.setVisible(false);
	        			new RepeatedCleaningGUI().createAndShowGUI(jf, CP);
	        		}
	        	});
	        }
	    });
	    
	    // show schedules
	    button = new JButton("Show schedules");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 1;
	    c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 2;
	    pane.add(button, c);
	    button.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        		public void run() {
	        			jf.setVisible(false);
	        			new ScheduleEditorGUI().createAndShowGUI(jf, CP);
	        		}
	        	});
	        }
	    });
	    
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    static void createAndShowGUI() {
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
