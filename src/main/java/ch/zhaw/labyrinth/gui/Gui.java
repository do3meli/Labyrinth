package ch.zhaw.labyrinth.gui;

import ch.zhaw.labyrinth.builder.DepthFirstSearch;
import ch.zhaw.labyrinth.builder.LabyrinthBuilder;
import ch.zhaw.labyrinth.builder.Prim;

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
    private JFrame labyrinthFrame;
    private Container contentPane;
    private JTextField tfSize;
    private JTextField tfZoom;
    private String[] createAlgorithms = { "Depth-First", "Prim", "Kruskal"};
    private String[] solveAlgorithms = { "Wall-Follower", "Right-Hand", "Trémaux", "Backtrack", "Shortest Path"};
    private JComboBox solveList;
    private JComboBox createList;

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
        JButton resetButton = new JButton("Reset");

        // ActionListeners for Buttons
        startButton.addActionListener(new StartActionListener());
        pauseButton.addActionListener(new PauseActionListener());
        resetButton.addActionListener(new ResetActionListener());

        // Panels
        JPanel configPanel = new JPanel();
        JPanel buttonPanel = new JPanel();


        // DropDown Menus
        solveList = new JComboBox(solveAlgorithms);
        solveList.setBounds(0, 242, 184, 27);
        createList = new JComboBox(createAlgorithms);
        createList.setBounds(0, 23, 184, 27);

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
    private class StartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            labyrinthFrame = new LabyrinthThread();
            labyrinthFrame.setLocation(251, 0);
            labyrinthFrame.setVisible(true);

            // Get Panel to draw in
            Container container = labyrinthFrame.getContentPane();
            JPanel panel = (JPanel)container.getComponent(0);

            // Get Variables
            int size = Integer.valueOf(tfSize.getText());
            int zoom = Integer.valueOf(tfZoom.getText());

            // Set zoom factor
//            labyrinthPanel.setZoom(zoom);

            // Get Build Type
            String type = (String)createList.getSelectedItem();

            LabyrinthBuilder lbuilder;
            if (type.equals("Depth-First")) {
                lbuilder = new DepthFirstSearch(size);
            } else if (type.equals("Prim")) {
                lbuilder = new Prim(size);
            } else {
                lbuilder = null;
            }

            Labyrinth labyrinth = new Labyrinth(panel, lbuilder);
            labyrinth.start();

        }
    }

    private class PauseActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // Pause Labyrinth Creation
        }
    }

    private class ResetActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // Reset Labyrinth Creation
        }
    }
}
