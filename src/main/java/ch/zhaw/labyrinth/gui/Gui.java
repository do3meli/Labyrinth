package ch.zhaw.labyrinth.gui;

import ch.zhaw.labyrinth.builder.DepthFirstSearch;
import ch.zhaw.labyrinth.builder.Import;
import ch.zhaw.labyrinth.solver.AStar;
import ch.zhaw.labyrinth.solver.RightHand;
import ch.zhaw.labyrinth.solver.Solver;
import ch.zhaw.labyrinth.utils.Labyrinth;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * This class is responsible for creating the GUI
 *
 * @author b.buetikofer, d.schlegel
 *
 */
public class Gui {
    
	// instance variables
	private static JFrame frame;
    private JTextField tfSize;
    private JTextField tfZoom;
    private JComboBox solveList;
    private JComboBox createList;
    private JCheckBox debug;
    private JCheckBox chckbxFastMode;
    private Labyrinth lbuilder;
    private String[] createAlgorithms;
    private String[] solveAlgorithms;
    private LabyrinthDrawer labyrinthDrawer;
    private JButton startSbutton;
    private JSlider slider;
    private JLabel sliderLabel;
    private int speed;
    private static Gui gui;

    public Gui() {
        gui = this;

    	// set up drop downs
    	createAlgorithms = new String[]{ "Depth-First", "Import"};
    	solveAlgorithms = new String[]{ "Right-Hand", "A* Search"};
    	
    	/* Use an appropriate Look and Feel - if possible */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
        /* Turn off metal's use bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }

    public void createAndShowGUI() {
        
    	// create a new frame
    	frame = new JFrame("LabyrinthSolver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 450);
        
        // create a content pane and add it to the frame
        Container contentPane = new Container();
        contentPane = frame.getContentPane();
        contentPane.setLayout(null);

        // Panels
        JPanel configPanel = new JPanel();
     
        // Building the config panel
        configPanel.setLayout(null);
        configPanel.setBounds(0, 0, 196, 478);

        // Gui object: create label
        JLabel createLabel = new JLabel("Create Algorithm");
        createLabel.setBounds(6, 6, 120, 16);
        createLabel.setHorizontalAlignment(SwingConstants.LEFT);
        createLabel.setFont(new Font(createLabel.getFont().getName(),Font.BOLD,createLabel.getFont().getSize()));
        configPanel.add(createLabel);
        
        // DropDown Menu for create list
        createList = new JComboBox(createAlgorithms);
        createList.setBounds(0, 23, 184, 27);
        configPanel.add(createList);
        createList.addItemListener(new ImportItemListener());
        
        // create and add SIZE label
        JLabel lblSize = new JLabel("Size: ");
        lblSize.setBounds(6, 55, 61, 16);
        configPanel.add(lblSize);
        
        // create and add maze size text field
        tfSize = new JTextField("41");
        tfSize.setBounds(50, 49, 134, 28);
        tfSize.setColumns(10);
        configPanel.add(tfSize);
        
        // create and add Zoom label
        JLabel lblZoom = new JLabel("Zoom: ");
        lblZoom.setBounds(6, 83, 61, 16);
        configPanel.add(lblZoom);
        
        // create and add zoom text field
        tfZoom = new JTextField("8");
        tfZoom.setBounds(50, 77, 134, 28);
        tfZoom.setColumns(10);
        configPanel.add(tfZoom);
       
        // Start Buttons for create maze
        JButton startButton = new JButton("Create");
        startButton.setBounds(50, 120, 75, 29);
        startButton.addActionListener(new StartCreateActionListener());
        configPanel.add(startButton);

        // create the separator between the panels
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 163, 196, 16);
        configPanel.add(separator);
        
        // Gui object: Solve Algorithm
        JLabel solveLabel = new JLabel("Solve Algorithm");
        solveLabel.setBounds(6, 180, 120, 16);
        solveLabel.setFont(new Font(createLabel.getFont().getName(),Font.BOLD,createLabel.getFont().getSize()));
        configPanel.add(solveLabel);
        
        // DropDown Menu for solve list
        solveList = new JComboBox(solveAlgorithms);
        solveList.setBounds(0, 197, 184, 27);
        configPanel.add(solveList);
        
        // Start button for solve algorithmen
        startSbutton = new JButton("Solve");
        startSbutton.setBounds(50, 230, 75, 29);
        startSbutton.addActionListener(new StartSolveActionListener());
        startSbutton.setEnabled(false);
        configPanel.add(startSbutton);
       
        // create the separator between the panels
        JSeparator separator2 = new JSeparator();
        separator2.setBounds(0, 280, 196, 16);
        configPanel.add(separator2);
        
        // Options label
        JLabel optLabel = new JLabel("Options");
        optLabel.setBounds(6, 290, 107, 16);
        optLabel.setHorizontalAlignment(SwingConstants.LEFT);
        optLabel.setFont(new Font(createLabel.getFont().getName(),Font.BOLD,createLabel.getFont().getSize()));
        configPanel.add(optLabel);

        // Speed Slider
        slider = new JSlider(0,50);
        slider.setValue(25);
        slider.addChangeListener(new SpeedChangeAction());
        sliderLabel = new JLabel("25");
        slider.setBounds(6, 310, 130, 16);
        sliderLabel.setBounds(136, 310, 40, 16);
        configPanel.add(slider);
        configPanel.add(sliderLabel);

        // Tickbox for Debug
        debug = new JCheckBox("enable debugging");
        debug.setBounds(6, 340, 180, 16);
        configPanel.add(debug);

        // now we are done with the config panel - add it 
        contentPane.add(configPanel);

        // finally make the window visible
        frame.setVisible(true);
      
    }
    
    /**
     * This returns the status of the check box for fast mode in GUI
     * @return true if check box is selected, false if not 
     */
    public boolean getChckbxFastMode() {
        return chckbxFastMode.isSelected();
    }

    public int getTfZoom() {
        return Integer.valueOf(tfZoom.getText());

    }

    public void setTfZoom(String zoom) {
        tfZoom.setText(zoom);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public static void drawLabyrinth(LabyrinthDrawer ld) {
        javax.swing.SwingUtilities.invokeLater(ld);
    }

    /**
     * ActionListener for Create Algorithm Button
     */
    private class StartCreateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           

            // Get Variables from GUI text boexes
            int size = Integer.valueOf(tfSize.getText());

            // Get Build Type
            String type = (String)createList.getSelectedItem();

            // Build selected LabyrinthDrawer
            if (type.equals("Depth-First")) {
                lbuilder = new DepthFirstSearch(size);
            } else if (type.equals("Import")) {
            	showFileChooser();
            } else {
                lbuilder = null;
            }
            
            // create the labyrinth drawer and active button
            createLabyrinth();   
            startSbutton.setEnabled(true);
        }
    }
    
    /**
     * ActionListener for Solve  Button
     */
    private class StartSolveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // TODO: Pause LabyrinthDrawer Creation
            // Get Build Type
            String type = (String)solveList.getSelectedItem();

            // Build selected LabyrinthDrawer
            Solver lbsolver;
            if (type.equals("Right-Hand")) {
                lbsolver = new RightHand(lbuilder);
            } else if (type.equals("A* Search")) {
                lbsolver = new AStar(lbuilder);
            } else {
                lbsolver = null;
            }

            labyrinthDrawer.setMode("solve");
            lbsolver.solve(labyrinthDrawer);

        }
    }

    private class SpeedChangeAction implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            setSpeed(slider.getValue());
            String str = Integer.toString(getSpeed());
            sliderLabel.setText(str);
        }
    }
    
    /**
     * This ItemListeners does fire when the item of the createList is getting changed.
     * if the selected item is Import it does show JFileChooser otherwise it does nothing.
     * @author d.schlegel
     */
    private class ImportItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED && e.getItem() == "Import") {
				
				showFileChooser();
			} 
		}
    }
    
    /**
     * This creates the labyrinth drawer object and calls the paint methods.
     * If debugging is selected it also prints the maze as array.
     */
    private void createLabyrinth(){
    	   
        // Print Debugging stuff to console if tickbox set
        if(debug.isSelected()){
        	lbuilder.printAsArray();
        }
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        if(lbuilder != null){
        	labyrinthDrawer = new LabyrinthDrawer(lbuilder, "create", gui);
            drawLabyrinth(labyrinthDrawer);
        }
    }
    
    private void showFileChooser(){
    	
    	// create file chooser
    	JFileChooser fChoose = new JFileChooser();
    	
    	// show it, and hand over the file to Import
    	int returnVal = fChoose.showOpenDialog( frame );
		if ( returnVal == JFileChooser.APPROVE_OPTION ) {
			lbuilder = new Import(fChoose.getSelectedFile());
		}else{
			JOptionPane.showMessageDialog(frame, "You did not select a file");
		}
		
		createLabyrinth();
    }
    
}
