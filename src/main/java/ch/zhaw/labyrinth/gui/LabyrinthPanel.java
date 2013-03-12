package ch.zhaw.labyrinth.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LabyrinthPanel extends JPanel {

    private final ArrayList<Point> points = new ArrayList<Point>();
    private int zoom;

    public void addPoint(int x1, int y1, int value) {
        if(value == 1){
          this.points.add(new Point(x1, y1));
        }	
    }

    public void paintComponent(Graphics g) {
        for(final Point r : points) {
            r.paint(g, zoom);
        }
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
}

