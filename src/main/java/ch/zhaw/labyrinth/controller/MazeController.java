package ch.zhaw.labyrinth.controller;

import ch.zhaw.labyrinth.model.MazeModel;
import ch.zhaw.labyrinth.model.builder.Builder;
import ch.zhaw.labyrinth.model.builder.DepthFirstSearch;
import ch.zhaw.labyrinth.model.builder.Import;
import ch.zhaw.labyrinth.model.solver.AStar;
import ch.zhaw.labyrinth.model.solver.RightHand;
import ch.zhaw.labyrinth.model.solver.Solver;
import ch.zhaw.labyrinth.view.MazePanel;
import ch.zhaw.labyrinth.view.MazeView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

/**
 * Maze Controller
 * This class represents the controller. Every action to the gui is controlled from here
 *
 * @author b.buetikofer
 */
public class MazeController {
    private MazeModel model;
    private MazeView view;
    private MazePanel mazePanel;
    private Builder mazeBuilder;
    private Solver mazeSolver;

    /**
     * Default constructor
     * creates a MazeControlle with a given model and a given view
     * @param model the maze model
     * @param view the view, main gui window
     */
    public MazeController(MazeModel model, MazeView view) {
        this.model = model;
        this.view = view;

        // Create Gui
        view.createAndShowGUI();

        // Add Listeners
        view.addImportListener(new ImportItemListener());
        view.addChangeSpeedListener(new SpeedChangeAction());
        view.addCreateListener(new CreateActionListener());
        view.addSolveListener(new SolveActionListener());

    }

    /**
     * This method setups the builder process
     */
    private void startBuilder() {
    	
    	 // Get Build Type
        String createAlgorithm = view.getCreateAlgorithm();
    	
        // local var for dim 
        int dim = view.getDimension();
        
        // do import stuff
        if (createAlgorithm.equals("Import")) {
            
        	// get the selected file
        	File f = view.getFileChooserObject();
            
            // if a file has been choosen create the Import object
            if(f != null){
            	
            	// create import obj
            	mazeBuilder = new Import(model,f); 
            	
            	// override local var for dim
            	dim = model.getDimension();
            }
        }
        
        
		// create maze panel
        mazePanel = new MazePanel(dim,view.getZoom());

        // Configure the MazePanel object
        mazePanel.setMode("create");
        mazePanel.setSpeed(view.getSpeed());
        
        // Build panel
        mazePanel.buildPanel();

        // Build new Frame
        JFrame mazeFrame = new JFrame("Maze");
        mazeFrame.add(mazePanel);
        mazeFrame.pack();
        mazeFrame.setLocationRelativeTo(null);
        mazeFrame.setResizable(false);
        mazeFrame.setVisible(true);
        
        
        // Build selected maze
        if (createAlgorithm.equals("Depth-First")) {
        	
        	// create a DFS object
        	mazeBuilder = new DepthFirstSearch(model, view.getDimension());
        
        } 

        // Register observer
        mazeBuilder.registerObserver(mazePanel);

        // Build Maze
        model = mazeBuilder.build();

        // print data structure as array if debugging is enabled
        if(view.getDebug()){
            model.printAsArray();
        }

        // Enable solve button
        view.enableSolveButton();
    }
    
    /**
     * This method setups the solve process
     */
    private void startSolver() {
        // Send MazePanel into solve mode
        mazePanel.setMode("solve");

        // Get Build Type
        String type = view.getSolveAlgorithm();

        // Build selected MazePanel
        if (type.equals("Right-Hand")) {
            mazeSolver = new RightHand(model);
        } else if (type.equals("A* Search")) {
            mazeSolver = new AStar(model);
        }

        // Register Observer
        mazeSolver.registerObserver(mazePanel);

        // Solve Maze
        mazeSolver.solve(mazePanel);
    }

    /**
     * ActionListener for Create Algorithm Button
     */
    private class CreateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            new Thread(new Runnable() {
                public void run() {
                    startBuilder();
                };
            }).start();

        }
    }

    /**
     * ActionListener for Solve Button
     */
    private class SolveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {

            new Thread(new Runnable() {
                public void run() {
                    startSolver();
                };
            }).start();

        }
    }
    
    /** 
     * ChangeListener for Seed changes in GUI
     */
    private class SpeedChangeAction implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            view.setSpeed(view.getSlider().getValue());
            mazePanel.setSpeed(view.getSpeed());
            String str = Integer.toString(view.getSpeed());
            view.setSliderLabel(str);
        }
    }

    /**
     * This ItemListeners does fire when the item of the createList is getting changed.
     * if the selected item is Import it does show JFileChooser otherwise it does nothing.
     *
     * @author d.schlegel
     */
    private class ImportItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals("Import")) {

            	 new Thread(new Runnable() {
                     public void run() {
                         startBuilder();
                     };
                 }).start();
            }
        }
    }

}
