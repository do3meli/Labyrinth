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

    private JPanel canvas;

    public LabyrinthThread() {
        createFrame();

    }

    private void createFrame() {
        setSize(400, 400);
        setTitle("Labyrinth Solver");

        Container container = getContentPane();
        canvas = new JPanel();
        container.add(canvas, "Center");

    }

    public JPanel getCanvas() {
        return canvas;
    }
}
