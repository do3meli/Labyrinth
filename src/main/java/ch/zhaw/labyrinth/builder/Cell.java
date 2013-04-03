package ch.zhaw.labyrinth.builder;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 03.04.13
 * Time: 21:49
 */
public class Cell {
    // Instance Vars
    private boolean frontier;
    private boolean wall;
    private boolean way;
    private int x;
    private int y;

    // Constructor
    public Cell (int x, int y) {
        this.x = x;
        this.y = y;
    }


    // Getter and Setter
    public boolean isFrontier() {
        return frontier;
    }

    public void setFrontier(boolean frontier) {
        this.frontier = frontier;
    }

    public boolean isWall() {
        return wall;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }

    public boolean isWay() {
        return way;
    }

    public void setWay(boolean way) {
        this.way = way;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ArrayList<Cell> getNeighbours() {

        return null;
    }
}
