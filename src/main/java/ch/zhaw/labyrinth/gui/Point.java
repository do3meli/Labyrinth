package ch.zhaw.labyrinth.gui;

import java.awt.*;

public class Point {
    public final int x1;
    public final int y1;
    public int zoom;

    public Point(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public void paint(Graphics g, int size) {

        // TODO: Not very efficient, we could do this with drawRect
        for (int i = 0; i < zoom; i++) {
            g.drawLine(this.x1*zoom, this.y1*zoom+i, this.x1*zoom+(zoom-1), this.y1*zoom+i);
        }
    }

}
