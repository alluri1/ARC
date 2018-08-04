package ARC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface {

    private JFrame frame;
    private JPanel gui;
    private JPanel buttonPanel;
    private JTextArea display;
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    
    Parser p;

    public UserInterface() {
    	createFrame();
    	createMainPanel();
    	createButtonPanel();
    	createDisplayPanel();
        addComponentsToFrame();
        frame.setVisible(true);
    }
    
    public void addComponentsToFrame() {
        //add a panel to the frame
    	gui.add(buttonPanel, BorderLayout.NORTH);
    	gui.add(display, BorderLayout.CENTER);
    	frame.add(gui);
    	frame.pack();
    }
    
    public void createDisplayPanel() {
    	display = new JTextArea(50, 50);
    	display.setEditable(false);
    }
    
    public void createFrame() {
    	// create a frame of required dimensions
        frame = new JFrame("NaiveBayes Classifier");
        
        // Set the look and feel to the cross-platform look and feel,
     	// otherwise mac os will have quirks like gaps between jbuttons
     	try {
     		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
     	} catch (Exception e) {
     		System.err.println("Unsupported look and feel.");
     		e.printStackTrace();
     	}
     	
     	frame.setSize(800,600);
     	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void createMainPanel() {
    	// Create the main panel which covers the entire frame
    	// don't  put components directly onto jframe
    	gui = new JPanel();
    	gui.setLayout(new BorderLayout());
    }
    
    public void createButtonPanel() {
    	buttonPanel = new JPanel();
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
        
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);
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
