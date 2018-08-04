package ARC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserInterface {

    public JFrame frame;
    private JPanel panel;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;

    public UserInterface(){

        // create a frame of required dimensions
        frame = new JFrame("NaiveBayes Classifier");
        frame.setSize(800,600);

        //add a panel to the frame
        panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(new FlowLayout());

        // buttons for UI
        b1 = new JButton("Load Data");
        b2 = new JButton("Explore Data");
        b3 = new JButton("Pre-process Data");
        b4 = new JButton("Train Data");
        b5 = new JButton("Test Data");
        b6 = new JButton("Display Results");

        b1.setLocation(100,100);
        b2.setLocation(200,200);
        b3.setLocation(300,300);
        b4.setLocation(400,300);
        b5.setLocation(500,500);
        b6.setLocation(500,600);

        setButtons(true);

        b1.setVisible(true);
        b2.setVisible(true);
        b3.setVisible(true);
        b4.setVisible(true);
        b5.setVisible(true);
        b6.setVisible(true);

        //b1.setLocation(100,100);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setButtons(Boolean set){
        b1.setEnabled(set);
        b2.setEnabled(set);
        b3.setEnabled(set);
        b4.setEnabled(set);
        b5.setEnabled(set);
        b6.setEnabled(set);
    }

    /**
     * Indicate the button which was clicked.
     *
     * @param e the event
     */
    public void actionPerformed(ActionEvent e) {

        // perform the action after the mouse click and disable buttons
        if (e.getSource() == b1) {
            b1.setEnabled(false);

        } else if (e.getSource() == b2) {
            b2.setEnabled(false);

        }else if (e.getSource() == b3) {
            b3.setEnabled(false);


        }else if (e.getSource() == b4) {
            b4.setEnabled(false);


        }else if (e.getSource() == b5) {
            b5.setEnabled(false);

        }else if (e.getSource() == b6) {
            b6.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new UserInterface();
    }


}
