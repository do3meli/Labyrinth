package ch.zhaw.labyrinth.solver.heading;

import ch.zhaw.labyrinth.utils.Coordinate;
import ch.zhaw.labyrinth.utils.Labyrinth;

/**
 * This interface defines how to move with a specific heading.
 */
public interface Heading {

    // Check methods if is a wall
    boolean isRight();
    boolean isLeft();
    boolean isStraight();
    boolean isBack();

    // Check if the cell was already visited
    int getVisits(int x, int y);

    // Methods to move
    Heading goRight();
    Heading goLeft();
    Heading goStraight();
    Heading goBack();

    // Get Coordinate
    Coordinate getCoordinate();

    // Get solved maze
    Labyrinth getSolvedLabyrinth();

}
