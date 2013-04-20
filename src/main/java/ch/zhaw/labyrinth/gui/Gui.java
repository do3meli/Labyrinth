package ch.zhaw.labyrinth.gui;

import ch.zhaw.labyrinth.builder.DepthFirstSearch;
import ch.zhaw.labyrinth.builder.LabyrinthBuilder;
import ch.zhaw.labyrinth.builder.Prim;
import ch.zhaw.labyrinth.solver.RightHand;
import ch.zhaw.labyrinth.solver.Solver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 03.03.13
 * Time: 10:45
 */
public class Gui {
    private static JFrame frame;
    private LabyrinthThread labyrinthFrame;
    private Container contentPane;
    private JTextField tfSize;
    private JTextField tfZoom;
    private JComboBox solveList;
    private JComboBox createList;
    private JCheckBox debug;
    private Labyrinth labyrinth;
    private LabyrinthBuilder lbuilder;
    // TODO: Move these to an Enum Class
    private String[] createAlgorithms = { "Depth-First", "Prim", "Kruskal"};
    private String[] solveAlgorithms = { "Wall-Follower", "Right-Hand", "Trémaux", "Backtrack", "Shortest Path"};

    public Gui() {
        /* Use an appropriate Look and Feel */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
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
        frame = new JFrame("LabyrinthSolver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = frame.getContentPane();
        contentPane.setLayout(null);

        // Variables
        JLabel createLabel = new JLabel("Create Algorithm");
        createLabel.setBounds(6, 6, 107, 16);
        createLabel.setHorizontalAlignment(SwingConstants.LEFT);
        JLabel solveLabel = new JLabel("Solve Algorithm");
        solveLabel.setBounds(6, 221, 100, 16);

        // Buttons
        JButton startButton = new JButton("Start");
        JButton pauseButton = new JButton("Pause");
        pauseButton.setEnabled(false);
        JButton resetButton = new JButton("Reset");
        resetButton.setEnabled(false);

        // ActionListeners for Buttons
        startButton.addActionListener(new StartCreateActionListener());
        pauseButton.addActionListener(new PauseCreateActionListener());
        resetButton.addActionListener(new ResetActionListener());

        // Panels
        JPanel configPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        // DropDown Menus
        solveList = new JComboBox(solveAlgorithms);
        solveList.setBounds(0, 242, 184, 27);
        createList = new JComboBox(createAlgorithms);
        createList.setBounds(0, 23, 184, 27);
        
        // Building the panels
        configPanel.setLayout(null);
        configPanel.setBounds(0, 0, 196, 478);

        configPanel.add(createLabel);
        configPanel.add(createList);
        configPanel.add(solveLabel);
        configPanel.add(solveList);

        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(resetButton);

        buttonPanel.setBounds(0, 338, 196, 140);
        configPanel.add(buttonPanel);

        JLabel lblSize = new JLabel("Size: ");
        lblSize.setBounds(6, 55, 61, 16);
        configPanel.add(lblSize);

        JLabel lblZoom = new JLabel("Zoom: ");
        lblZoom.setBounds(6, 83, 61, 16);
        configPanel.add(lblZoom);

        tfSize = new JTextField("41");
        tfSize.setBounds(50, 49, 134, 28);
        configPanel.add(tfSize);
        tfSize.setColumns(10);

        tfZoom = new JTextField("4");
        tfZoom.setBounds(50, 83, 134, 28);
        configPanel.add(tfZoom);
        tfZoom.setColumns(10);
        
        // Tickbox for Debug
        debug = new JCheckBox("enable debugging");
        debug.setBounds(6, 120, 180, 16);
        configPanel.add(debug);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 193, 196, 16);
        configPanel.add(separator);

        contentPane.add(configPanel);
        frame.setSize(200, 600);
        frame.setVisible(true);

    }

    /**
     * ActionListeners
     */
    private class StartCreateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            labyrinthFrame = new LabyrinthThread();
            labyrinthFrame.setLocation(251, 0);
            labyrinthFrame.setVisible(true);

            // Get Variables
            int size = Integer.valueOf(tfSize.getText());
            int zoom = Integer.valueOf(tfZoom.getText());

            // Get Build Type
            String type = (String)createList.getSelectedItem();

            // Build selected Labyrinth
            if (type.equals("Depth-First")) {
                lbuilder = new DepthFirstSearch(size);
            } else if (type.equals("Prim")) {
                lbuilder = new Prim(size);
            } else {
                lbuilder = null;
            }
            
            // Print Debugging stuff to console if tickbox set
            if(debug.isSelected()){
            	lbuilder.printArray();
            }
            
            
            // Create Labyrinth
            labyrinth = new Labyrinth(labyrinthFrame.getCanvas(), lbuilder);

            // Set Zoom Factor
            labyrinth.setZoom(zoom);

            // Start Thread
            labyrinth.start();

        }
    }

    private class PauseCreateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // TODO: Pause Labyrinth Creation
            // Get Build Type
            String type = (String)solveList.getSelectedItem();

            // Build selected Labyrinth
            Solver lbsolver;
            if (type.equals("Right-Hand")) {
                lbsolver = new RightHand(lbuilder.getMaze());
            } else {
                lbsolver = null;
            }

            lbsolver.solve();
        }
    }

    private class StartSolveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // TODO: Start Labyrinth Solver

        }
    }

    private class ResetActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // Reset Labyrinth Creation

        }
    }
}
