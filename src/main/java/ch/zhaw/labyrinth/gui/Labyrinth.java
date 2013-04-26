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
    private JPanel box;
    private LabyrinthBuilder labyrinthBuilder;
    private int zoom;
    private boolean fast;
    private int P = 1;
    private int Q = 1;
    private int p = 0;
    private int q = 0;
    
    public Labyrinth(JPanel pan, LabyrinthBuilder labyrinthBuilder, boolean fast) {
        this.box = pan;
        this.labyrinthBuilder = labyrinthBuilder;
        this.zoom = 8;
        this.fast = fast;
    }

    public void move(int i, int j, int aij) {
        p = i;
        q = j;

        if (!box.isVisible()){
            return;
        }

        Graphics g = box.getGraphics();
        g.setXORMode(box.getBackground());
     
        if (aij == LabyrinthBuilder.getWall()){
            g.fillRect(p*zoom, q*zoom, P*zoom, Q*zoom);
        }
    }

    public void run() {
        try {

            int[][] maze = labyrinthBuilder.getMaze();
            for(int i=0; i<labyrinthBuilder.getDimension(); i++) {
                for (int j=0; j<labyrinthBuilder.getDimension(); j++) {

                    move(j,i,maze[i][j]);
                    if(!fast) {
                        sleep(10);
                    }
                }
            }

        } catch (Exception e) {
        }
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
}
