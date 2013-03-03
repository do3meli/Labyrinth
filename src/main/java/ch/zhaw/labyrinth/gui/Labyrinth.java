package ch.zhaw.labyrinth.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Labyrinth extends JPanel {

    private final ArrayList<Line> lines = new ArrayList<Line>();

    public void addLine(int x1, int y1, int x2, int y2) {
        this.lines.add(new Line(x1, y1, x2, y2));
    }

    public void paintComponent(Graphics g) {
        for(final Line r : lines) {
            r.paint(g);
        }
    }
}

