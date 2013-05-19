package ch.zhaw.labyrinth.solver;


import ch.zhaw.labyrinth.gui.LabyrinthDrawer;
import ch.zhaw.labyrinth.solver.heading.East;
import ch.zhaw.labyrinth.solver.heading.Heading;
import ch.zhaw.labyrinth.solver.heading.South;
import ch.zhaw.labyrinth.utils.Coordinate;
import ch.zhaw.labyrinth.utils.Labyrinth;

import java.util.Observable;

/**
 * This class implements the RightHand Algorithm.
 *
 * @author b.buetikofer
 *
 */
public class RightHand extends Observable implements Solver {
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
    public void solve(LabyrinthDrawer labyrinthDrawer) {
        // Step counter
        int steps = 0;

        addObserver(labyrinthDrawer);

        // Timer
        long startTime = System.currentTimeMillis();

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

            if(labyrinth.getCellAt(new Coordinate(x,y)).isPath()) {
                labyrinthDrawer.setLabyrinth(heading.getSolvedLabyrinth(), new Coordinate(x, y));
                setChanged();
                notifyObservers();
            }

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

        // Timer
        long stopTime = System.currentTimeMillis();

        // Print steps
        System.out.println("Steps: " + steps + ", took: " + (stopTime-startTime) + "ms");
        labyrinthDrawer.setLabyrinth(heading.getSolvedLabyrinth(), exit);
        setChanged();
        notifyObservers();

    }

    // Set x and y with the new values from the coordinate
    private void setCoordinate(Coordinate coordinate) {
        x = coordinate.getX();
        y = coordinate.getY();
    }

}
