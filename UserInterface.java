package ARC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface {

    public JFrame frame;
    private JPanel panel;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    Parser p;

    public UserInterface(){
        // create a frame of required dimensions
        frame = new JFrame("NaiveBayes Classifier");
        frame.setSize(800,600);

        //add a panel to the frame
        panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(new FlowLayout());

        // buttons for UI
        b1 = new JButton("Load data");
        b2 = new JButton("Explore data");
        b3 = new JButton("Run classifier");
        b4 = new JButton("Evaluate classifier");

        b1.setLocation(100,100);
        b2.setLocation(200,200);
        b3.setLocation(300,300);
        b4.setLocation(400,300);

       	b1.setEnabled(true);
       	b2.setEnabled(false);
       	b3.setEnabled(false);
       	b4.setEnabled(false);

        b1.setVisible(true);
        b2.setVisible(true);
        b3.setVisible(true);
        b4.setVisible(true);
        
        // Add action listener
        b1.addActionListener(new ButtonListener());
        b2.addActionListener(new ButtonListener());
        b3.addActionListener(new ButtonListener());
        b4.addActionListener(new ButtonListener());

        //b1.setLocation(100,100);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new UserInterface();
    }
    
    /**
     * Inner class for button action listener,
     * input indicates which button was clicked, 
     * and we execute actions based on this button
     */
    class ButtonListener implements ActionListener {
    	
    	/**
    	 * e - the event
    	 */
    	public void actionPerformed(ActionEvent e) {
    		String input = e.getActionCommand();
    		Object whichButton = e.getSource();
    		System.out.println("Just pressed: " + input);
    		
    		if (whichButton == b1) {
    			// Load data
    			p = new Parser();
    			b1.setEnabled(false);
    			b3.setEnabled(true);
    		} else if (e.getSource() == b2) {
    			
                b2.setEnabled(false);
                
            }else if (e.getSource() == b3) {
            	
            	p.trainTest();
                b3.setEnabled(false);
                b4.setEnabled(true);
            }else if (e.getSource() == b4) {
            	p.evaluate();
                b4.setEnabled(false);
            }
    	}
    }

}
