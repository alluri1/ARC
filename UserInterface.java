package ARC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface {

    private JFrame frame;
    private JPanel gui;
    private JPanel buttonPanel;
    private JPanel reviewSubmitPanel;
    private JScrollPane scrollDisplay;
    private JTextArea display;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JLabel l1;
    private JTextArea inputTextField;
    
    Parser p;

    public UserInterface() {
    	createFrame();
    	createMainPanel();
    	createButtonPanel();
    	createDisplayPanel();
    	createSubmitReviewPanel();
        addComponentsToFrame();
        frame.setVisible(true);
    }
    
    public void addComponentsToFrame() {
        //add a panel to the frame
    	gui.add(buttonPanel, BorderLayout.NORTH);
    	gui.add(scrollDisplay, BorderLayout.CENTER);
    	gui.add(reviewSubmitPanel, BorderLayout.SOUTH);
    	frame.add(gui);
    	//frame.pack();
    }
    
    public void createDisplayPanel() {
    	display = new JTextArea(50, 50);
    	display.setEditable(false);
    	scrollDisplay = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
     	
     	frame.setSize(800,500);
     	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	frame.setResizable(false);
    }
    
    public void createMainPanel() {
    	// Create the main panel which covers the entire frame
    	// don't  put components directly onto jframe
    	gui = new JPanel();
    	gui.setLayout(new BorderLayout());
    }


    public void createSubmitReviewPanel(){
    	reviewSubmitPanel = new JPanel();
    	reviewSubmitPanel.setLayout(new BorderLayout());
    	
        // label for textField
    	l1 = new JLabel("Enter review to classify:");

        // text field to enter the review
        inputTextField = new JTextArea(4,30);
        //inputTextField.setColumns(20);
        inputTextField.setLocation(400,400);
        inputTextField.setEnabled(true);
        inputTextField.setVisible(true);
        buttonPanel.add(inputTextField);

        //submit button
        b5 = new JButton("Submit");
        b5.setVisible(true);
        b5.setEnabled(false);
        b5.addActionListener(new ButtonListener());
        reviewSubmitPanel.add(l1, BorderLayout.NORTH);
        reviewSubmitPanel.add(inputTextField, BorderLayout.CENTER);
        reviewSubmitPanel.add(b5, BorderLayout.EAST);
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
    			display.append("Processing documents...\n");
    			p = new Parser();
    			display.append("Documents loaded: " + p.myDocs.size() + "\n\n");
    			b1.setEnabled(false);
    			b2.setEnabled(true);
    			b3.setEnabled(true);
    			b5.setEnabled(true);
    		} else if (e.getSource() == b2) {
    			display.append("\n\n#############   DATA EXPLORATION   #############\n");
    			display.append(p.termStr);
    			display.append(p.dataExplorationStr);
    			b2.setEnabled(false);
            }else if (e.getSource() == b3) {
            	display.append("\nRunning classifier...");
            	p.trainTest();
                b3.setEnabled(false);
                b4.setEnabled(true);
            }else if (e.getSource() == b4) {
            	String evaluation = p.evaluate();
            	display.append("\n\n#############   EVALUATION   #############\n\n" + evaluation);
                b4.setEnabled(false);
            }else if (e.getSource()== b5){
                String str = inputTextField.getText();
                //Make sure the new text is visible, even if there
                //was a selection in the text area.
                inputTextField.setCaretPosition(inputTextField.getDocument().getLength());
                System.out.println(str);

            }
    	}
    }

}
