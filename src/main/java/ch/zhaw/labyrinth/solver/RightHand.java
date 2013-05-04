package ch.zhaw.labyrinth.solver;


import ch.zhaw.labyrinth.solver.heading.*;
import ch.zhaw.labyrinth.utils.Coordinate;
import ch.zhaw.labyrinth.utils.Labyrinth;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 20.04.13
 * Time: 11:49
 */
public class RightHand extends Solver {
    int x;
    int y;
    private Labyrinth solvedLabyrinth;
    private Labyrinth labyrinth;
    private Heading heading;
    private Coordinate currentLocation;

    public RightHand(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
        solvedLabyrinth = new Labyrinth();
    }

    @Override
    public void solve() {
        // Step counter
        int steps = 0;

        // find entry
        Coordinate entry = labyrinth.getEntry();
        x=entry.getX();
        y=entry.getY();
        // set heading
        if(x == 0) {
            heading = new East(x, y, labyrinth, solvedLabyrinth);
        } else if (y == 0) {
            heading = new South(x, y, labyrinth, solvedLabyrinth);
        }

        // set exit
        Coordinate exit = labyrinth.getExit();
        int u=exit.getX();
        int v=exit.getY();

        // go through labyrinth
        while((x != u) && (y!=v)) {
            steps++;
            if(steps > 1000) { System.out.println(steps); break; }

            // Move according to heading
            if(heading.isRight()) {
                heading = heading.goRight();
                setCoordinate(heading.getCoordinate());
                continue;
            }
            if(heading.isStraight()) {
                heading = heading.goStraight();
                setCoordinate(heading.getCoordinate());
                continue;
            }
            if(heading.isBack()) {
                heading = heading.goBack();
                setCoordinate(heading.getCoordinate());
                continue;
            }
            if(heading.isLeft()) {
                heading = heading.goLeft();
                setCoordinate(heading.getCoordinate());
                continue;
            }


        }

        // Print Solved maze to console
        System.out.print("fertig");
    }

    // Set x and y with the new values from the coordinate
    private void setCoordinate(Coordinate coordinate) {
        x = coordinate.getX();
        y = coordinate.getY();
    }

}
