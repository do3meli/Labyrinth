package ch.zhaw.labyrinth.model.solver;

import ch.zhaw.labyrinth.model.MazeModel;
import ch.zhaw.labyrinth.model.utils.Cell;
import ch.zhaw.labyrinth.model.utils.Coordinate;
import java.util.Observable;
import java.util.Observer;

/**
 * This class implements the A* Algorithm based on the explanations from
 * http://www.policyalmanac.org/games/aStarTutorial.htm
 *
 * @author b.buetikofer
 *
 */
public class AStar extends Observable implements Solver {
    // Predefined constants for the travel-costs
    private static final int G_HORIZONTAL_VERTICAL = 10;
    private static final int G_DIAGONAL = 14;

    // Our initial maze to solve
    private final MazeModel maze;
    // this object will contain the shortest path
    private final MazeModel closedSet;
    // In here are candidates for the shortest path
    private final MazeModel openSet;

    /**
     * Default constructor. This object has always an maze
     *
     * @param maze to solve
     */
    public AStar(MazeModel maze) {
        this.maze = maze;
        openSet = new MazeModel();
        closedSet = new MazeModel();
    }

    @Override
    public void solve() {
       

        // Timer
        long startTime = System.currentTimeMillis();

        /**
         *  Entry
         */
        Coordinate entry = maze.getEntry();
        Coordinate exit = maze.getExit();

        Coordinate startCoordinate = entry;
        int x = startCoordinate.getX();
        int y = startCoordinate.getY();

        /**
         * Add start point to the openSet, set predecessor to self
         */
        openSet.addPath(startCoordinate, startCoordinate);

        /**
         * Here starts the fun.
         * For every of the eight surrounding cells we will calculate their f, g and h value
         * if they are reachable and not already part of the closedSet
         */
        Coordinate currentCoordinate = openSet.getLowestF();
        Cell currentCell = openSet.getCellAt(currentCoordinate);
        Cell oldCell = null;
        // Move starting coordinate into the closedSet
        closedSet.addPath(currentCoordinate, currentCell);
        openSet.removeCell(currentCoordinate);

        while ( !currentCoordinate.equals(exit) ) {
            /**
             * Add all reachable neighbors to the openSet and add calculate f-cost
             */
            // north
            if(maze.getCellValueAt(x, y + 1) && !closedSet.getCellValueAt(x, y + 1)) {
                checkValue(x, y + 1, currentCoordinate, G_HORIZONTAL_VERTICAL);
            }
            // east
            if(maze.getCellValueAt(x + 1, y) && !closedSet.getCellValueAt(x + 1, y)) {
                checkValue(x + 1, y, currentCoordinate, G_HORIZONTAL_VERTICAL);
            }
            // west
            if(maze.getCellValueAt(x - 1, y) && !closedSet.getCellValueAt(x - 1, y)) {
                checkValue(x - 1, y, currentCoordinate, G_HORIZONTAL_VERTICAL);
            }
            // south
            if(maze.getCellValueAt(x, y - 1) && !closedSet.getCellValueAt(x, y - 1)) {
                checkValue(x, y - 1, currentCoordinate, G_HORIZONTAL_VERTICAL);
            }

            // north-east
            if(maze.getCellValueAt(x + 1, y + 1) && !closedSet.getCellValueAt(x + 1, y + 1)) {
                checkValue(x + 1, y + 1, currentCoordinate, G_DIAGONAL);
            }
            // south-west
            if(maze.getCellValueAt(x - 1, y - 1) && !closedSet.getCellValueAt(x - 1, y - 1)) {
                checkValue(x - 1, y - 1, currentCoordinate, G_DIAGONAL);
            }
            // south-east
            if(maze.getCellValueAt(x + 1, y - 1) && !closedSet.getCellValueAt(x + 1, y - 1)) {
                checkValue(x + 1, y - 1, currentCoordinate, G_DIAGONAL);
            }
            // north-west
            if(maze.getCellValueAt(x - 1, y + 1) && !closedSet.getCellValueAt(x - 1, y + 1)) {
                checkValue(x - 1, y + 1, currentCoordinate, G_DIAGONAL);
            }


            /**
             * Get cell with the lowest F value
             */
            currentCoordinate = openSet.getLowestF();
            x=currentCoordinate.getX();
            y=currentCoordinate.getY();

            // Move current coordinate into the closedSet
            oldCell = openSet.getCellAt(currentCoordinate);
            closedSet.addPath(currentCoordinate, oldCell.getPredecessor());
            openSet.removeCell(currentCoordinate);
        }

        /**
         * Add exit to closedSet
         */
        closedSet.addPath(currentCoordinate, oldCell);
        long stopTime = System.currentTimeMillis();

      

        // Print exit
        setChanged();
        notifyObservers(currentCoordinate);

        /**
         *  Working backwards from the target square, go from each square to its parent square
         *  until you reach the starting square.
         */
        int steps = 0;
        Coordinate curCoordinate = new Coordinate(exit.getX(), exit.getY());
        Coordinate target = new Coordinate(entry.getX(), entry.getY());

        while(!curCoordinate.equals(target)) {
            steps++;

            Cell curCell = closedSet.getCellAt(curCoordinate);
            curCoordinate = curCell.getPredecessor();

            setChanged();
            notifyObservers(curCoordinate);
        }

        System.out.println("Steps: " + steps + ", took: " + (stopTime - startTime) + "ms");

    }

    /**
     * This method checks if cell at a given coordinate is already in the open set.
     *
     * @param x x-ccordinate
     * @param y y-coordinate
     * @param currentCoordinate coordinates of the current cell
     * @param constant the cost constant
     */
    private void checkValue(int x, int y, Coordinate currentCoordinate, int constant) {
        if(!openSet.getCellValueAt(x, y)) {
            addAndCalculate(x, y, currentCoordinate, constant);
        } else {
            checkGValueAndUpdate(x, y, currentCoordinate, constant);
        }
    }

    /**
     *
     * If it's not it's added to the openSet. If it is, the new G value is compared with the one of its predecessor.
     * If its lower the predecessor is set to the current cell
     *
     * @param x x-ccordinate
     * @param y y-coordinate
     * @param predecessorCoordinate coordinates of predecessor
     * @param constant the cost constant
     */
    private void checkGValueAndUpdate(int x, int y, Coordinate predecessorCoordinate, int constant) {
        Cell predecessorCell = openSet.getCellAt(predecessorCoordinate);
        if(predecessorCell == null) {
            predecessorCell = closedSet.getCellAt(predecessorCoordinate);
        }
        int predecessorGValue = predecessorCell.getG();

        Cell currentCell = openSet.getCellAt(x, y);
        int currentGValue = currentCell.getG();

        if(currentGValue < predecessorGValue) {
            currentCell.setPredecessor(new Coordinate(x, y));
            // Calculate G Value
            currentCell.setG(constant + predecessorCell.getG());

            // Calculate H Value
            currentCell.setH((maze.getExit().getX() - x + maze.getExit().getY() - y) * 10);

            // Calculate F Value
            currentCell.setF(currentCell.getG() + currentCell.getH());
        }
    }

    /**
     * Adds a cell to the openSet and calculates its F, G, and H value
     *
     * @param x x-coordinate of the new cell
     * @param y y-coordinate of the new cell
     * @param predecessorCoordinate coordinate object of the cell from where this method was called
     * @param constant cost constant
     */
    private void addAndCalculate(int x, int y, Coordinate predecessorCoordinate, int constant) {
        Coordinate currentCoordinate = new Coordinate(x, y);
        Cell currentCell = maze.getCellAt(currentCoordinate);
        currentCell.setPredecessor(predecessorCoordinate);

        Cell predecessorCell = closedSet.getCellAt(predecessorCoordinate);

        openSet.addPath(currentCoordinate, currentCell);

        // Calculate G Value
        currentCell.setG(constant + predecessorCell.getG());

        // Calculate H Value
        currentCell.setH((maze.getExit().getX() - x + maze.getExit().getY() - y) * 10);

        // Calculate F Value
        currentCell.setF(currentCell.getG() + currentCell.getH());
    }

    @Override
    public void registerObserver(Observer obs) {
        this.addObserver(obs);
    }
}
