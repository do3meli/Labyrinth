package ch.zhaw.labyrinth.model.solver.heading;

import ch.zhaw.labyrinth.model.utils.Coordinate;

/**
 * This interface defines how to move with a specific heading.
 *
 * @author b.buetikofer
 */
public interface Heading {

    /**
     * This method checks if the cell right of itself is a path
     * @return true if path
     */
    boolean isRight();

    /**
     * This method checks if the cell left of itself is a path
     * @return true if path
     */
    boolean isLeft();

    /**
     * This method checks if the cell straight ahead of itself is a path
     * @return true if path
     */
    boolean isStraight();

    /**
     * This method checks if the cell behind of itself is a path
     * @return true if path (should always be true!)
     */
    boolean isBack();

    /**
     * Get number of visits of given cell
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return number of visits
     */
    int getVisits(int x, int y);

    /**
     * Turns right and goes on step ahead
     * @return the new heading
     */
    Heading goRight();

    /**
     * Turns left and goes on step ahead
     * @return the new heading
     */
    Heading goLeft();

    /**
     * Goes one step ahead
     * @return the new heading (should always be the same as before)
     */
    Heading goStraight();

    /**
     * Turns around and goes on step ahead
     * @return the new heading
     */
    Heading goBack();

    /**
     *
     * @return the new coordinate
     */
    Coordinate getCoordinate();

}
