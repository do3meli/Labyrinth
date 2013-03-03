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
        JLabel createLabel;
        JLabel solveLabel;
        JLabel createLabyrinth;

        JButton startButton;
        JButton pauseButton;
        JButton resetButton;

        JPanel labyrinthPanel;
        JPanel configPanel;
        JPanel buttonPanel;

        frame = new JFrame("LabyrinthSolver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());


        frame.setSize(800, 500);
        frame.setVisible(true);
    }


}
