package ch.zhaw.labyrinth.gui;

import ch.zhaw.labyrinth.builder.LabyrinthBuilder;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 26.03.13
 * Time: 08:54
 */
public class Labyrinth extends Thread{
    JPanel box;
    LabyrinthBuilder labyrinthBuilder;
    int P = 1, Q = 1, p = 0, q = 0;
    public Labyrinth(JPanel pan, LabyrinthBuilder labyrinthBuilder) {
        box = pan;
        this.labyrinthBuilder = labyrinthBuilder;

    }

    public void move(int i, int j, int aij) {
        int zoom = 8;
        p = i;
        q = j;

        if (!box.isVisible())
            return;

        Graphics g = box.getGraphics();
        g.setXORMode(box.getBackground());

        if (aij == 1)
            g.fillRect(p*zoom, q*zoom, P*zoom, Q*zoom);
    }

    public void run() {
        try {

            int[][] a = labyrinthBuilder.getMaze();
            for(int i=0; i<labyrinthBuilder.getDimension(); i++) {
                for (int j=0; j<labyrinthBuilder.getDimension(); j++) {

                    move(i,j,a[i][j]);
                    sleep(10);
                }
            }

        } catch (Exception e) {
        }
    }
}