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
    int x,y,u,v;
    private Labyrinth solvedLabyrinth;
    private Labyrinth labyrinth;
    private Heading heading;

    // Constructor
    public RightHand(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
        solvedLabyrinth = new Labyrinth();
        solvedLabyrinth.setDimension(labyrinth.getDimension());
        solvedLabyrinth.createEmptyMaze();
    }

    // Implemented methods
    @Override
    public Labyrinth solve() {
        // Step counter
        int steps = 0;

        // set entry and exit
        Coordinate entry = labyrinth.getEntry();
        Coordinate exit = labyrinth.getExit();
        x=entry.getX();
        y=entry.getY();
        u=exit.getX();
        v=exit.getY();

        solvedLabyrinth.setCellValue(x,y,true);
        solvedLabyrinth.setCellValue(u,v,true);

        // set heading
        if(x == 0) {
            heading = new East(x, y, labyrinth, solvedLabyrinth);
        } else if (y == 0) {
            heading = new South(x, y, labyrinth, solvedLabyrinth);
        }

        // go through labyrinth
        while((x != u) || (y != v)) {
            steps++;

            // Debug only, print solved array
            //heading.getSolvedLabyrinth().printAsArray();

            // TODO: It would be nice if we would update the GUI here. This way we'd have a real breadcrumb painting


            // If we already visited the next cell, try to go right
            // otherwise, go straight ahead, if thats not possible go left
            if(heading.getVisits(x, y) > 0) {
                if(heading.isRight()) {
                    heading = heading.goRight();
                } else if (heading.isStraight()) {
                    heading = heading.goStraight();
                } else if (heading.isLeft()) {
                    heading = heading.goLeft();
                } else {
                    heading = heading.goBack();
                }
                setCoordinate(heading.getCoordinate());
                continue;
            }

            /**
             *  Move according to heading
              */
            // If possible go right, always the first choice
            if(heading.isRight()) {
                heading = heading.goRight();
                setCoordinate(heading.getCoordinate());
                continue;
            }
            // Else go straight
            if(heading.isStraight()) {
                heading = heading.goStraight();
                setCoordinate(heading.getCoordinate());
                continue;
            }
            // Try going left
            if(heading.isLeft()) {
                heading = heading.goLeft();
                setCoordinate(heading.getCoordinate());
                continue;
            }
            // If everything else fails got back
            if(heading.isBack()) {
                heading = heading.goBack();
                setCoordinate(heading.getCoordinate());
                continue;
            }
        }

        // return solved array to gui
        return heading.getSolvedLabyrinth();
    }

    // Set x and y with the new values from the coordinate
    private void setCoordinate(Coordinate coordinate) {
        x = coordinate.getX();
        y = coordinate.getY();
    }

}
