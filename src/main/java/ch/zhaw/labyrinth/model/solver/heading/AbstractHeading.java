package ch.zhaw.labyrinth.model.solver.heading;

import ch.zhaw.labyrinth.model.utils.Coordinate;
import ch.zhaw.labyrinth.model.MazeModel;

/**
 * Abstract Class representing a heading. All generic methods are defined in this class
 *
 * @author b.buetikofer
 */
public abstract class AbstractHeading implements Heading {
    // Instance Variables
    int x;
    int y;
    MazeModel maze;
    MazeModel solvedMaze;

    // Getter & Setter
    @Override
    public Coordinate getCoordinate() {
        return new Coordinate(getX(), getY());
    }

    @Override
    public int getVisits(int x, int y) {
        return maze.isCellVisited(x, y);
    }
    
    /**
     * gets the current x
     * @return int
     */
    public int getX() {
        return x;
    }
    /**
     * sets the current x
     * @param x
     */
    void setX(int x) {
        this.x = x;
    }
    /**
     * gets the current y
     * @return int
     */
    public int getY() {
        return y;
    }
    /**
     * sets the current y
     * @param y
     */
    void setY(int y) {
        this.y = y;
    }
    /**
     * stores the stuff to the cell
     */
    void storeIt() {
        solvedMaze.setCellValue(x, y, true);
        solvedMaze.setCellVisited(x,y);
    }

}
