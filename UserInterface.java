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
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton exploreClass0;
    private JButton exploreClass1;
    private JButton exploreClass2;
    private JButton exploreClass3;
    private JLabel l1;
    private JTextArea inputTextField;
    private JTextArea inputTextArea;
    private JPanel displayPanel;
    private JTextArea display2;
    
    Parser p;

    public UserInterface() {
    	createFrame();
    	createMainPanel();
    	createButtonPanel();
    	createDisplayPanel();
    	 createDisplayPanel2();
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
    
    public void enableButtons() {
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);
        exploreClass0.setEnabled(true);
        exploreClass1.setEnabled(true);
        exploreClass2.setEnabled(true);
        exploreClass3.setEnabled(true);
    }
    
    public void createDisplayPanel() {
    	display = new JTextArea(50, 50);
    	display.setEditable(false);
    	display.setLineWrap(true);
    	display.setWrapStyleWord(true);
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
     	
     	frame.setSize(1000,600);
     	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	frame.setResizable(false);
    }
    
    public void createMainPanel() {
    	// Create the main panel which covers the entire frame
    	// don't  put components directly onto jframe
    	gui = new JPanel();
    	gui.setLayout(new BorderLayout());
    }
    
    public void createDisplayPanel2() {
        displayPanel= new JPanel();
        display2 = new JTextArea(2,50);
        display2.setEditable(false);
        display2.setBackground(frame.getBackground());
        displayPanel.add(display2);
    }

    public void createSubmitReviewPanel(){
    	reviewSubmitPanel = new JPanel();
    	reviewSubmitPanel.setLayout(new BorderLayout());
    	
        // label for textField
    	l1 = new JLabel("Enter review to classify:");

        // text field to enter the review
    	inputTextArea = new JTextArea(4,30);
        //inputTextField.setColumns(20);
    	inputTextArea.setEnabled(true);
    	inputTextArea.setVisible(true);
    	inputTextArea.setLineWrap(true);
    	inputTextArea.setWrapStyleWord(true);
        buttonPanel.add(inputTextArea);

        //submit button
        b5 = new JButton("Classify");
        b5.setEnabled(false);
        b5.addActionListener(new ButtonListener());
        
        reviewSubmitPanel.add(l1, BorderLayout.NORTH);
        reviewSubmitPanel.add(b5, BorderLayout.EAST);
        reviewSubmitPanel.add(inputTextArea, BorderLayout.CENTER); ///////
        reviewSubmitPanel.add(displayPanel, BorderLayout.SOUTH); /////
    }

    public void createButtonPanel() {
    	buttonPanel = new JPanel();
    	buttonPanel.setLayout(new GridLayout(2, 4, 2, 2));
    	
        // buttons for UI
        b2 = new JButton("Explore all reviews");
        b3 = new JButton("Train classifier");
        b4 = new JButton("Evaluate classifier");
        exploreClass0 = new JButton("Explore has_information_giving");
        exploreClass1 = new JButton("Explore has_information_seeking");
        exploreClass2 = new JButton("Explore has_feature_request");
        exploreClass3 = new JButton("Explore has_bug_report");
        
        // Disable (until parser finishes, then enableButtons is called)
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        exploreClass0.setEnabled(false);
        exploreClass1.setEnabled(false);
        exploreClass2.setEnabled(false);
        exploreClass3.setEnabled(false);

        // Add action listeners
        b2.addActionListener(new ButtonListener());
        b3.addActionListener(new ButtonListener());
        b4.addActionListener(new ButtonListener());
        exploreClass0.addActionListener(new ButtonListener());
        exploreClass1.addActionListener(new ButtonListener());
        exploreClass2.addActionListener(new ButtonListener());
        exploreClass3.addActionListener(new ButtonListener());
        
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);
        buttonPanel.add(new JLabel()); // dummy space
        buttonPanel.add(exploreClass0);
        buttonPanel.add(exploreClass1);
        buttonPanel.add(exploreClass2);
        buttonPanel.add(exploreClass3);
    }
    
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.p = new Parser();
        ui.enableButtons();
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
    		System.out.println("Just pressed: " + input);
    		
    		if (e.getSource() == b2) {
    			display.setText("\n\n#############   DATA EXPLORATION   #############\n");
    			display.append(p.termStr);
    			display.append(p.dataExplorationStr);
            }else if (e.getSource() == b3) {
            	display.setText("\nRunning classifier on sample 80% train/20% test set...");
            	p.trainTest();
            	b5.setEnabled(true);
            }else if (e.getSource() == b4) {
            	String evaluation = p.evaluate();
            	display.append("#############   EVALUATION   #############\n\n" + evaluation);
            }else if (e.getSource()== b5){
            	display2.setText("");
                String review = inputTextArea.getText();
                //Make sure the new text is visible, even if there
                //was a selection in the text area.
                inputTextArea.setCaretPosition(inputTextArea.getDocument().getLength());
                display2.setText("Class of the given review is: ");
                System.out.println(review);
                String reviewClass = p.classifyReview(review);
                display2.append(reviewClass);
            } else if (e.getSource() == exploreClass0) {
            	display.setText("############# HAS_INFORMATION_GIVING REVIEWS #############\n");
            	display.append(p.class0Reviews);
            } else if (e.getSource() == exploreClass1) {
            	display.setText("############# HAS_INFORMATION_SEEKING REVIEWS #############\n");
            	display.append(p.class1Reviews);
            } else if (e.getSource() == exploreClass2) {
            	display.setText("############# HAS_FEATURE_REQUEST REVIEWS #############\n");
            	display.append(p.class2Reviews);
            } else if (e.getSource() == exploreClass3) {
            	display.setText("############# HAS_BUG_REPORT REVIEWS #############\n");
            	display.append(p.class3Reviews);
            }
    	}
    }

}
