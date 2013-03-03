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
    private JPanel labyrinthPanel;

    public InitGui() {

    }

    public void initGui(Labyrinth laby) {
        // Variables
        JLabel createLabel = new JLabel("Create Algorithm");
        createLabel.setBounds(26, 10, 107, 16);
        createLabel.setHorizontalAlignment(SwingConstants.LEFT);
        JLabel solveLabel = new JLabel("Solve Algorithm");
        solveLabel.setBounds(26, 77, 100, 16);

        JButton startButton = new JButton("Start");
        JButton pauseButton = new JButton("Pause");
        JButton resetButton = new JButton("Reset");
        JPanel configPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        String[] createAlgorithms = { "Depth-First", "Prim", "Kruskal"};
        String[] solveAlgorithms = { "Wall-Follower", "Right-Hand", "Trémaux", "Backtrack", "Shortest Path"};
        JComboBox solveList = new JComboBox(solveAlgorithms);
        solveList.setBounds(26, 105, 143, 27);
        JComboBox createList = new JComboBox(createAlgorithms);
        createList.setBounds(26, 38, 130, 27);

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

        labyrinthPanel = laby;
        labyrinthPanel.setBounds(195, 0, 605, 478);

        frame.getContentPane().add(labyrinthPanel);

        frame.setSize(800, 500);
        frame.setVisible(true);
    }

}
