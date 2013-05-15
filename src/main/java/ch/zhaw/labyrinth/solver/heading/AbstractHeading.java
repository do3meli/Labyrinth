package ch.zhaw.labyrinth.solver.heading;

import ch.zhaw.labyrinth.utils.Coordinate;
import ch.zhaw.labyrinth.utils.Labyrinth;

/**
 * Abstract Class representing a heading. All generic methods are defined in this class
 *
 * @author b.buetikofer
 */
public abstract class AbstractHeading implements Heading {
    // Instance Variables
    protected int x;
    protected int y;
    protected Labyrinth maze;
    protected Labyrinth solvedMaze;

    // Getter & Setter
    @Override
    public Coordinate getCoordinate() {
        return new Coordinate(getX(), getY());
    }

    @Override
    public Labyrinth getSolvedLabyrinth() {
        return solvedMaze;
    }

    @Override
    public int getVisits(int x, int y) {
        return maze.isCellVisited(x, y);
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

    public void storeIt() {
        solvedMaze.setCellValue(x, y, true);
        solvedMaze.setCellVisited(x,y);
    }


}
