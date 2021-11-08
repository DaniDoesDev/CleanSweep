package portal;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegistrationGUI {

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private static JFrame parentFrame;
    private static JFrame jf;
    private static JTextField text1;
    private static JTextField text2;
    private static CleanPortal CP;

    public static void addComponentsToPane(Container pane) {
        JLabel label;
        JButton button;

        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        label = new JLabel("Username");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        //c.gridwidth = 3;
        pane.add(label, c);

        //Calendar date = Calendar.getInstance();
        //date.add(Calendar.HOUR_OF_DAY, 1);
        text1 = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 3;
        pane.add(text1, c);

        label = new JLabel("Password");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        pane.add(label, c);

        text2 = new JPasswordField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 3;
        pane.add(text2, c);

        label = new JLabel("Clean Sweep Serial #");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        //c.gridwidth = 3;
        pane.add(label, c);

        //Calendar date = Calendar.getInstance();
        //date.add(Calendar.HOUR_OF_DAY, 1);
        text1 = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 3;
        pane.add(text1, c);

        button = new JButton("Complete registration");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 6;
        pane.add(button, c);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        // delete cb by id
                        try {
                        } catch (Exception e) {
                            // If fields are empty
                        }
                        parentFrame.setVisible(true);
                        jf.dispose();
                    }
                });
            }
        });



    }

    /*
     Create the GUI and show it
     */
    static void createAndShowGUI(JFrame pf, CleanPortal c) {
        parentFrame = pf;
        CP = c;
        //Create and set up the window.
        jf = new JFrame("GridBagLayoutDemo");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(10, 10, 700, 600);


        //Set up the content pane.
        addComponentsToPane(jf.getContentPane());

        //Display the window.
        //frame.pack();
        jf.setVisible(true);
    }
}