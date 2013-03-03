package ch.zhaw.labyrinth.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 03.03.13
 * Time: 10:45
 */
public class InitGui {
    private static JFrame frame;

    public InitGui() {
        initGui();
    }

    public void initGui() {
        // Variables
        JLabel createLabel = new JLabel("Create Algorithm");
        JLabel solveLabel = new JLabel("Solve Algorithm");
        JLabel createLabyrinth = new JLabel("Create Labyrinth");

        JButton startButton = new JButton("Start");
        JButton pauseButton = new JButton("Pause");
        JButton resetButton = new JButton("Reset");

        JPanel labyrinthPanel = new JPanel();
        JPanel configPanel = new JPanel();
        JPanel buttonPanel;

        String[] createAlgorithms = { "Depth-First", "Prim", "Kruskal"};
        JComboBox createList = new JComboBox(createAlgorithms);
        String[] solveAlgorithms = { "Wall-Follower", "Right-Hand", "Trémaux", "Backtrack", "Shortest Path"};
        JComboBox solveList = new JComboBox(solveAlgorithms);

        frame = new JFrame("LabyrinthSolver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(2,2));

        labyrinthPanel.setLayout(new BorderLayout());
        configPanel.setLayout(new GridLayout(2,4));
        configPanel.add(createLabel,0,0);
        configPanel.add(createList,0,1);
        configPanel.add(solveLabel);
        configPanel.add(solveList);
        contentPane.add(configPanel);
        contentPane.add(labyrinthPanel);

        frame.setSize(800, 500);
        frame.setVisible(true);
    }


}
