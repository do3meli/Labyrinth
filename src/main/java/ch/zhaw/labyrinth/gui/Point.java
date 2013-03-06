package ch.zhaw.labyrinth.gui;

import java.awt.*;

public class Point {
    public final int x1;
    public final int y1;

    public Point(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public void paint(Graphics g) {

        // TODO: Not very efficient, we could do this with drawRect
        int size = 8;
        for (int i = 0; i < size; i++) {
            g.drawLine(this.x1*size, this.y1*size+i, this.x1*size+(size-1), this.y1*size+i);
        }
    }
}
