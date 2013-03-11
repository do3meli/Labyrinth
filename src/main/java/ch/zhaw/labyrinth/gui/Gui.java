package ch.zhaw.labyrinth.gui;

import ch.zhaw.labyrinth.builder.DepthFirstSearch;
import ch.zhaw.labyrinth.builder.LabyrinthBuilder;

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
    private JPanel labyrinthPanel;
    private JTextField tfSize;
    private JTextField tfZoom;
    private String[] createAlgorithms = { "Depth-First", "Prim", "Kruskal"};
    private String[] solveAlgorithms = { "Wall-Follower", "Right-Hand", "Trémaux", "Backtrack", "Shortest Path"};
    private Labyrinth labyrinth;
    private JComboBox solveList;
    private JComboBox createList;

    public Gui() {
        labyrinth = new Labyrinth();
        initGui();

    }

    /**
     * @wbp.parser.entryPoint
     */
    public void initGui() {
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


        frame = new JFrame("LabyrinthSolver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        frame.getContentPane().setLayout(null);
        configPanel.setLayout(null);
        configPanel.setBounds(0, 0, 196, 478);

        configPanel.add(createLabel);
        configPanel.add(createList);
        configPanel.add(solveLabel);
        configPanel.add(solveList);

        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(resetButton);

        contentPane.add(configPanel);

        buttonPanel.setBounds(0, 338, 196, 140);
        configPanel.add(buttonPanel);

        JLabel lblSize = new JLabel("Size: ");
        lblSize.setBounds(6, 55, 61, 16);
        configPanel.add(lblSize);

        JLabel lblZoom = new JLabel("Zoom: ");
        lblZoom.setBounds(6, 83, 61, 16);
        configPanel.add(lblZoom);

        tfSize = new JTextField("");
        tfSize.setBounds(50, 49, 134, 28);
        configPanel.add(tfSize);
        tfSize.setColumns(10);

        tfZoom = new JTextField("");
        tfZoom.setBounds(50, 83, 134, 28);
        configPanel.add(tfZoom);
        tfZoom.setColumns(10);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 193, 196, 16);
        configPanel.add(separator);

        labyrinthPanel = new Labyrinth();
        labyrinthPanel.setBounds(195, 0, 605, 478);

        frame.getContentPane().add(labyrinthPanel);

        frame.setSize(800, 500);
        frame.setVisible(true);
    }

    /**
     * ActionListeners
     */
    private class StartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            LabyrinthBuilder lab;
            // Get Variables
            String ssize = tfSize.getText();
            int size = Integer.valueOf(ssize);
            String szoome = tfZoom.getText();
            int zoom = Integer.valueOf(ssize);

            // Get Build Type
            String type = (String)createList.getSelectedItem();

            if (type.equals("Depth-First")) {
                lab = new DepthFirstSearch(size);
            } else {
                lab = null;
            }

            // Build Labyrinth
            int[][] a = lab.getMaze();
            for(int i=0; i<lab.getDimension(); i++) {
                for (int j=0; j<lab.getDimension(); j++) {
                    labyrinth.addPoint(i,j,a[i][j]);
                }
            }

            // TODO: Print Labyrinth point by point in gui

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
