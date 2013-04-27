package ch.zhaw.labyrinth.builder;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 03.04.13
 * Time: 21:49
 */
public class Cell {
    // Instance Vars
    private int x;
    private int y;
    
    // Constructor
    public Cell (int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

  
    public int getY() {
        return y;
    }

}
