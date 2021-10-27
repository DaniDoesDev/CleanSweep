package portal;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
 
/*
 * GridBagLayoutDemo.java requires no other files.
 * https://docs.oracle.com/javase/tutorial/displayCode.html?
 *   code=https://docs.oracle.com/javase/tutorial/uiswing/examples
 *   /layout/GridBagLayoutDemoProject/src/layout/GridBagLayoutDemo.java
 */
 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
 
public class RepeatedCleaningGUI {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private static JFrame parentFrame; 
    private static JFrame jf;
    private static JTextField text1;
    private static JTextField text2;
    private static JLabel errorLabel;
    private static CleanPortal CP;
    
    private static boolean validNumber(String text) {
    	String pattern = "^\\d{1,3}$"; // ex: 9, 99, 999 
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(text);
		return m.find(); // true if number is valid
	}
    private static Boolean validDate(String text) {    	
//    	String pattern = "^\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}$";
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(text);
//		if (!m.find()) {
//			return false; // true if date is valid
//		}
		
    	try {
			//@SuppressWarnings("deprecation");
			Date date = new Date(text);
			// Date is valid.
			// Check future date to current date
			if (date.compareTo(new Date()) == -1) {
				// date is before now (==-1)
				return false;
			} else {
				// ==0, ==1 means date is later than now
			}
			//System.out.println("here0");
		} catch (Exception e) {
			//System.out.println("here1");
			return false;
		} finally {}
    	return true;
    }
    public static void addComponentsToPane(Container pane) {
    	JLabel label;
    	JButton button;
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    	
    	if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        
	    pane.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    label = new JLabel("Start (YYYY/MM/DD HH:MM):");
	    c.weightx = 0.5;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    //c.gridwidth = 3;
	    pane.add(label, c);
	 
	    Calendar date = Calendar.getInstance();
	    date.add(Calendar.HOUR_OF_DAY, 1);
	    text1 = new JTextField(formatter.format(date.getTime()));
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 3;
	    c.gridy = 0;
//	    c.gridwidth = 3;
	    pane.add(text1, c);
	    
	    label = new JLabel("End date (YYYY/MM/DD HH:MM) or # repeats:");
	    c.weightx = 0.5;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 1;
	    //c.gridwidth = 3;
	    pane.add(label, c);
	  
	    text2 = new JTextField("");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 3;
	    c.gridy = 1;
	    c.gridwidth = 3;
	    pane.add(text2, c);
	 
	    button = new JButton("Add new schedule!");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 1;
	    c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 6;
	    pane.add(button, c);
	    
	    //
	    button.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        		public void run() {
	        			//
	        			if (!validDate(text1.getText())) {
	        				errorLabel.setVisible(true);
	        				errorLabel.setText("Invalid start date or date is in the past!");
	        			} else if (!validDate(text2.getText()) &&
	        					!validNumber(text2.getText())) {
	        				errorLabel.setVisible(true);
	        				errorLabel.setText("Invalid end date, date is in the past, or invalid repeat amount!");
	        			} else {
	        				// write to file
	        				if (validNumber(text2.getText())) {
	        					CleanPortal.schedule.addDates(text1.getText(), "", text2.getText());
	        				} else {
	        					CleanPortal.schedule.addDates(text1.getText(), text2.getText(), "");
	        				}
	        				
		        			parentFrame.setVisible(true);
		        			jf.dispose();
	        			}
	        		}
	        	});
	        }
	    });

	    errorLabel = new JLabel("");
	    c.weightx = 0.5;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 3;
	    //c.gridwidth = 3;
	    errorLabel.setVisible(false);
	    pane.add(errorLabel, c);
	    
//	    button = new JButton("Long-Named Button 4");
//	    c.fill = GridBagConstraints.HORIZONTAL;
//	    c.ipady = 40;      //make this component tall
//	    c.weightx = 0.0;
//	    c.gridwidth = 3;
//	    c.gridx = 0;
//	    c.gridy = 1;
//	    pane.add(button, c);
	 
//	    button = new JButton("5");
//	    c.fill = GridBagConstraints.HORIZONTAL;
//	    c.ipady = 0;       //reset to default
//	    c.weighty = 1.0;   //request any extra vertical space
//	    c.anchor = GridBagConstraints.PAGE_END; //bottom of space
//	    c.insets = new Insets(10,0,0,0);  //top padding
//	    c.gridx = 1;       //aligned with button 2
//	    c.gridwidth = 2;   //2 columns wide
//	    c.gridy = 2;       //third row
//	    pane.add(button, c);
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
