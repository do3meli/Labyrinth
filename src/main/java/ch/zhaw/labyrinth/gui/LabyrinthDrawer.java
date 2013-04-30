package ch.zhaw.labyrinth.gui;

import ch.zhaw.labyrinth.utils.Cell;
import ch.zhaw.labyrinth.utils.Coordinate;
import ch.zhaw.labyrinth.utils.Labyrinth;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 26.03.13
 * Time: 08:54
 */
public class LabyrinthDrawer extends Thread{
   
	// instance var
	private JPanel box;
    private Labyrinth labyrinth;
    private int zoom;
    private boolean fast;
    
    public LabyrinthDrawer(JPanel pan, Labyrinth labyrinth, boolean fast) {
        this.box = pan;
        this.labyrinth = labyrinth;
        this.zoom = 8;
        this.fast = fast;
    }

    public void move(int i, int j, boolean paint) {

        if (!box.isVisible()){
            return;
        }

        Graphics g = box.getGraphics();
        g.setXORMode(box.getBackground());
     
        if (!paint){
            g.fillRect(i*zoom, j*zoom, 1*zoom, 1*zoom);
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

        } catch (Exception e) {
        }
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
}
