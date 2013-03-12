package ch.zhaw.labyrinth.gui;

import ch.zhaw.labyrinth.gui.panels.ConfigPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 03.03.13
 * Time: 10:45
 */
public class Gui {
    private static JFrame frame;

    public Gui() {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                }

                frame = new JFrame("LabyrinthSolver");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setLayout(new BorderLayout());

                ConfigPanel cPanel = new ConfigPanel();

                frame.add(cPanel);

                frame.setSize(800, 500);
                frame.setVisible(true);

               // new Thread(new LabyrinthEngine(labyrinth)).start();

            }
        });

    }

}
