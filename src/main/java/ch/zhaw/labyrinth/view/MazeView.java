package ch.zhaw.labyrinth.view;

import ch.zhaw.labyrinth.model.builder.Import;
import ch.zhaw.labyrinth.model.solver.Solver;
import ch.zhaw.labyrinth.model.MazeModel;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

/**
 * This class is responsible for creating the GUI
 *
 * @author b.buetikofer, d.schlegel
 *
 */
public class MazeView {
    
	// instance variables
    private MazeModel model;
    private MazeModel lbuilder;
    private MazePanel mazePanel;
    private JButton startButton;
	private JFrame frame;
    private JTextField tfDimension;
    private JTextField tfZoom;
    private JComboBox solveList;
    private JComboBox createList;
    private JCheckBox debug;
    private String[] createAlgorithms;
    private String[] solveAlgorithms;
    private JButton solveButton;
    private JSlider slider;
    private JLabel sliderLabel;
    private int speed;
    private String mode;
    private Solver lbsolver;
    
    // constants
    private static final int INIT_SPEED = 25;
    
    
    /**
     * Default constructor for MazeView
     *
     * @param model the MazeModel
     */
    public MazeView(MazeModel model) {
        this.model = model;
        this.speed = INIT_SPEED;

        /* Disable ugly lucking gui elemnts */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /* Turn off metal's use bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

    }

    public void createAndShowGUI() {
        
    	// create a new frame
    	frame = new JFrame("LabyrinthSolver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 450);
        frame.setLocationByPlatform(true);
        
        // create a content pane and add it to the frame
        Container contentPane = new Container();
        contentPane = frame.getContentPane();
        contentPane.setLayout(null);

        // Panels
        JPanel configPanel = new JPanel();
     
        // Building the config panel
        configPanel.setLayout(null);
        configPanel.setBounds(0, 0, 196, 478);

        // set up drop downs
        createAlgorithms = model.getCreateAlgorithms();
        solveAlgorithms = model.getSolveAlgorithms();

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

        // create and add SIZE label
        JLabel lblSize = new JLabel("Size: ");
        lblSize.setBounds(6, 55, 61, 16);
        configPanel.add(lblSize);
        
        // create and add maze size text field
        tfDimension = new JTextField("41");
        tfDimension.setBounds(50, 49, 134, 28);
        tfDimension.setColumns(10);
        configPanel.add(tfDimension);
        
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
        startButton = new JButton("Create");
        startButton.setBounds(50, 120, 75, 29);
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
        solveButton = new JButton("Solve");
        solveButton.setBounds(50, 230, 75, 29);
        solveButton.setEnabled(false);
        configPanel.add(solveButton);
       
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
        slider.setValue(INIT_SPEED);
        sliderLabel = new JLabel(Integer.toString(INIT_SPEED));
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Solver getLbsolver() {
        return lbsolver;
    }

    public void setLbsolver(Solver lbsolver) {
        this.lbsolver = lbsolver;
    }

    public int getDimension() {
        return Integer.valueOf(tfDimension.getText());
    }

    public void setDimension(int size) {
        tfDimension.setText(String.valueOf(size));
    }

    public int getZoom() {
        return Integer.valueOf(tfZoom.getText());
    }

    public void setZoom(int zoom) {
        tfZoom.setText(String.valueOf(zoom));
    }

    public String getCreateAlgorithm() {
        return (String)createList.getSelectedItem();
    }

    public String getSolveAlgorithm() {
        return (String)solveList.getSelectedItem();
    }

    public String getSliderLabel() {
        return sliderLabel.getText();
    }

    public void setSliderLabel(String sliderLabel) {
        this.sliderLabel.setText(sliderLabel);
    }

    public JSlider getSlider() {
        return slider;
    }
    
    public boolean getDebug(){
    	return debug.isSelected();
    }

    public void enableSolveButton() {
        solveButton.setEnabled(true);
    }

    public void disableSolveButton() {
        solveButton.setEnabled(false);
    }


    /**
     * This creates the labyrinth drawer object and calls the paint methods.
     * If debugging is selected it also prints the maze as array.
     */
     public void createLabyrinth(){


        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        if(lbuilder != null){
        //	mazePanel = new MazePanel();
        }

        // now enable the solver button
        solveButton.setEnabled(true);
    }
    
    public void showFileChooser(){
    	
    	// create file chooser
    	JFileChooser fChoose = new JFileChooser();
    	
    	// show it, and hand over the file to Import
    	int returnVal = fChoose.showOpenDialog( frame );
		if ( returnVal == JFileChooser.APPROVE_OPTION ) {
			lbuilder = new Import(fChoose.getSelectedFile()).build();
		}else{
			JOptionPane.showMessageDialog(frame, "You did not select a file");
			lbuilder = null;
		}
		
		createLabyrinth();
    }

    /**
     * These methods are called from the controller and add the specific Listener to
     * the UI elements (Button, Dropdown List, Slider)
     *
     */
    public void addImportListener(ItemListener ae) {
        createList.addItemListener(ae);
    }

    public void addCreateListener(ActionListener ae) {
        startButton.addActionListener(ae);
    }

    public void addSolveListener(ActionListener ae) {
        solveButton.addActionListener(ae);
    }

    public void addChangeSpeedListener(ChangeListener ae) {
        slider.addChangeListener(ae);
    }
}


