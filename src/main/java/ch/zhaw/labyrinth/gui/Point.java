package ch.zhaw.labyrinth.gui;

import java.awt.*;

public class Point {
    public final int x1;
    public final int x2;
    public final int y1;
    public final int y2;

    public Point(int x1, int y1) {
        this.x1 = x1;
        this.x2 = x1+100;
        this.y1 = y1;
        this.y2 = y1;
    }

    public void paint(Graphics g) {
        g.drawLine(this.x1, this.y1, this.x2, this.y2);
    }
}
