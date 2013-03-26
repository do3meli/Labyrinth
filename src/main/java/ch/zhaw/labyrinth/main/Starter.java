package ch.zhaw.labyrinth.main;

import ch.zhaw.labyrinth.gui.LabyrinthThread;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 03.03.13
 * Time: 10:56
 */
public class Starter {
    public static void main(String[] args) {
        // Initialize GUI
        //Gui gui = new Gui();

        JFrame frame = new LabyrinthThread();
        frame.setVisible(true);

    }
}
