package ch.zhaw.labyrinth.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 26.03.13
 * Time: 08:52
 */
public class LabyrinthThread extends JFrame {

    private static JPanel canvas;

    public LabyrinthThread() {
        createFrame();

    }

    private void createFrame() {
        setSize(400, 400);
        setTitle("Labyrinth Solver");

        Container container = getContentPane();
        canvas = new JPanel();
        container.add(canvas, "Center");
        JPanel panel = new JPanel();
        container.add(panel, "North");

    }
}
