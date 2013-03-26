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
 * Date: 26.03.13
 * Time: 08:52
 */
public class LabyrinthThread extends JFrame {

    private JPanel canvas;

    public LabyrinthThread() {
        setSize(400, 400);
        setTitle("Labyrinth Solver");

        Container container = getContentPane();
        canvas = new JPanel();
        container.add(canvas, "Center");
        JPanel panel = new JPanel();
        add(panel, "Start", new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                LabyrinthBuilder lbuilder;
                lbuilder = new DepthFirstSearch(41);

                Labyrinth labyrinth = new Labyrinth(canvas, lbuilder);
                labyrinth.start();
            }
        });
        add(panel, "Stop", new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                canvas.setVisible(false);
                System.exit(0);
            }
        });
        container.add(panel, "North");
    }
    public void add(Container container, String title, ActionListener listener) {
        JRadioButton button = new JRadioButton(title);
        container.add(button);
        button.addActionListener(listener);
    }

    public JPanel getCanvas() {
        return canvas;
    }
}
