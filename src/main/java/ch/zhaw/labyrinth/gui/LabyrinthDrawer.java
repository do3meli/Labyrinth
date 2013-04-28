package ch.zhaw.labyrinth.gui;

import ch.zhaw.labyrinth.utils.Cell;
import ch.zhaw.labyrinth.utils.Coordinate;
import ch.zhaw.labyrinth.utils.Labyrinth;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 26.03.13
 * Time: 08:54
 */
public class LabyrinthDrawer extends Thread{
    private JPanel box;
    private Labyrinth labyrinth;
    private int zoom;
    private boolean fast;
    private int P = 1;
    private int Q = 1;
    private int p = 0;
    private int q = 0;
    
    public LabyrinthDrawer(JPanel pan, Labyrinth labyrinth, boolean fast) {
        this.box = pan;
        this.labyrinth = labyrinth;
        this.zoom = 8;
        this.fast = fast;
    }

    public void move(int i, int j, boolean aij) {
        p = i;
        q = j;

        if (!box.isVisible()){
            return;
        }

        Graphics g = box.getGraphics();
        g.setXORMode(box.getBackground());
     
        if (!aij){
            g.fillRect(p*zoom, q*zoom, P*zoom, Q*zoom);
        }
    }

    public void run() {
        try {

            HashMap<Coordinate, Cell> maze = labyrinth.getMaze();
            for(int i=0; i< labyrinth.getDimension(); i++) {
                for (int j=0; j< labyrinth.getDimension(); j++) {

                    Cell cell = maze.get(new Coordinate(i,j));

                    move(j,i,cell.isPath());
                    if(!fast) {
                        sleep(10);
                    }
                }
            }
            //for (Map.Entry<Coordinate, Cell> entry : maze.entrySet())
            //{
            //    System.out.println(entry.getKey() + "/" + entry.getValue());
            //}

        } catch (Exception e) {
        }
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
}
