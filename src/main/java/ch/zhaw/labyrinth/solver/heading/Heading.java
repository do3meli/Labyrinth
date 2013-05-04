package ch.zhaw.labyrinth.solver.heading;

import ch.zhaw.labyrinth.utils.Coordinate;

/**
 * This interface defines how to move with a specific heading.
 */
public interface Heading {

    // Check methods if is a wall
    boolean isRight();
    boolean isLeft();
    boolean isStraight();
    boolean isBack();

    // Methods to move
    Heading goRight();
    Heading goLeft();
    Heading goStraight();
    Heading goBack();

    // Get Coordinate
    Coordinate getCoordinate();

}
