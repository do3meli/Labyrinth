package ch.zhaw.labyrinth.model.solver;


import ch.zhaw.labyrinth.model.MazeModel;
import ch.zhaw.labyrinth.model.solver.heading.East;
import ch.zhaw.labyrinth.model.solver.heading.Heading;
import ch.zhaw.labyrinth.model.solver.heading.South;
import ch.zhaw.labyrinth.model.utils.Coordinate;
import java.util.Observable;
import java.util.Observer;

/**
 * This class implements the RightHand Algorithm.
 *
 * @author b.buetikofer
 *
 */
public class RightHand extends Observable implements Solver {
    private int x;
    private int y;
    private final MazeModel solvedMazeModel;
    private final MazeModel mazeModel;
    private Heading heading;

    // Constructor
    public RightHand(MazeModel mazeModel) {

        this.mazeModel = mazeModel;
        solvedMazeModel = new MazeModel();
        solvedMazeModel.setDimension(mazeModel.getDimension());
        solvedMazeModel.createEmptyMaze();
    }

    // Implemented methods
    @Override
    public void solve() {
        // Step counter
        int steps = 0;
        int u;
        int v;

        // Timer
        long startTime = System.currentTimeMillis();

        // set entry and exit
        Coordinate entry = mazeModel.getEntry();
        Coordinate exit = mazeModel.getExit();
        x=entry.getX();
        y=entry.getY();
        u=exit.getX();
        v=exit.getY();

        solvedMazeModel.setCellValue(x,y,true);
        solvedMazeModel.setCellValue(u,v,true);

        // set heading
        if(x == 0) {
            heading = new East(x, y, mazeModel, solvedMazeModel);
        } else if (y == 0) {
            heading = new South(x, y, mazeModel, solvedMazeModel);
        }

        // go through mazeModel
        while((x != u) || (y != v)) {
            steps++;

            if(mazeModel.getCellAt(new Coordinate(x,y)).isPath()) {
                setChanged();
                notifyObservers(new Coordinate(x,y));
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
            }
        }

        // Timer
        long stopTime = System.currentTimeMillis();

        // Print steps
        System.out.println("Steps: " + steps + ", took: " + (stopTime-startTime) + "ms");
        setChanged();
        notifyObservers(new Coordinate(u,v));

    }

    @Override
    public void registerObserver(Observer obs) {
        this.addObserver(obs);
    }


    /**
     * Set x and y with the new values from the coordinate
     */
    private void setCoordinate(Coordinate coordinate) {
        x = coordinate.getX();
        y = coordinate.getY();
    }

    @Override
    public void printCheckedCells() {
        System.out.println("Not implemented yet!");
    }
}
