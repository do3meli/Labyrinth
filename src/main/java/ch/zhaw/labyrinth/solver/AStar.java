package ch.zhaw.labyrinth.solver;

import ch.zhaw.labyrinth.gui.LabyrinthDrawer;
import ch.zhaw.labyrinth.utils.Cell;
import ch.zhaw.labyrinth.utils.Coordinate;
import ch.zhaw.labyrinth.utils.Labyrinth;

import java.util.Observable;

/**
 *
 * @author b.buetikofer
 *
 */
public class AStar extends Observable implements Solver {
    public static final int G_HORIZONTAL_VERTICAL = 10;
    public static final int G_DIAGONAL = 14;

    private Labyrinth maze;
    private Labyrinth closedSet;
    private Labyrinth openSet;


    public AStar(Labyrinth maze) {
        this.maze = maze;
        openSet = this.maze;
        closedSet = new Labyrinth();
    }

    @Override
    public void solve(LabyrinthDrawer labyrinthDrawer) {
        addObserver(labyrinthDrawer);

        /**
         *  Entry
         */
        Coordinate entry = maze.getEntry();
        Coordinate exit = maze.getEntry();


        Coordinate currentCoordinate = entry;
        int x = currentCoordinate.getX();
        int y = currentCoordinate.getY();

        /**
         * Add start point to the openSet, set predecessor to self
         */
        openSet.addPath(currentCoordinate, currentCoordinate);

        /**
         * Dive into the recursion!! YAY
         *
         */
        while ( !currentCoordinate.equals(exit) ) {
            /**
             * Add all reachable neighbors to the openSet and add calculate f-cost
             */
            // north
            if(maze.getCellValueAt(x, y + 1)) {
                checkValue(x, y + 1, currentCoordinate, G_HORIZONTAL_VERTICAL);
            }
            // east
            if(maze.getCellValueAt(x + 1, y)) {
                checkValue(x + 1, y, currentCoordinate, G_HORIZONTAL_VERTICAL);
            }

            // west
            if(maze.getCellValueAt(x - 1, y)) {
                checkValue(x - 1, y, currentCoordinate, G_HORIZONTAL_VERTICAL);
            }
            // south
            if(maze.getCellValueAt(x, y - 1)) {
                checkValue(x, y - 1, currentCoordinate, G_HORIZONTAL_VERTICAL);
            }

            // north-east
            if(maze.getCellValueAt(x + 1, y + 1)) {
                checkValue(x + 1, y + 1, currentCoordinate, G_DIAGONAL);
            }
            // south-west
            if(maze.getCellValueAt(x - 1, y - 1)) {
                checkValue(x - 1, y - 1, currentCoordinate, G_DIAGONAL);
            }
            // south-east
            if(maze.getCellValueAt(x + 1, y - 1)) {
                checkValue(x + 1, y - 1, currentCoordinate, G_DIAGONAL);
            }
            // north-west
            if(maze.getCellValueAt(x - 1, y + 1)) {
                checkValue(x - 1, y + 1, currentCoordinate, G_DIAGONAL);
            }

            /**
             * add current cell to the closed set and remove it from the maze
             */
            closedSet.addPath(currentCoordinate);
            openSet.removeCell(currentCoordinate);

            /**
             * Get cell with the lowest F value
             */
            currentCoordinate = openSet.getLowestF();

        }

        /**
         * Move back the closedSet to get the path
         */
        labyrinthDrawer.setLabyrinth(closedSet);
        setChanged();
        notifyObservers();
    }

    /**
     * This method checks if cell at a given coordinate is already in the open set.
     *
     * @param x
     * @param y
     * @param currentCoordinate
     * @param constant
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
     * @param x
     * @param y
     * @param currentCoordinate
     * @param constant
     */
    private void checkGValueAndUpdate(int x, int y, Coordinate currentCoordinate, int constant) {
        Cell currentCell = openSet.getCellAt(currentCoordinate);
        int currentGValue = currentCell.getG();

        Coordinate predecessorCoordinates = currentCell.getPredecessor();
        Cell predecessorCell = openSet.getCellAt(predecessorCoordinates);
        int predecessorGValue = predecessorCell.getG();

        if(currentGValue < predecessorGValue) {
            currentCell.setPredecessor(currentCoordinate);
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
     * @param currentCoordinate coordinate object of the cell from where this method was called
     * @param constant cost constant
     */
    private void addAndCalculate(int x, int y, Coordinate currentCoordinate, int constant) {
        Coordinate newCoordinate = new Coordinate(x, y);
        Cell newCell = maze.getCellAt(newCoordinate);

        Coordinate predecessorCoordinate = newCell.getPredecessor();
        Cell predecessorCell = maze.getCellAt(predecessorCoordinate);

        openSet.addPath(newCoordinate, currentCoordinate);

        // Calculate G Value
        newCell.setG(constant + predecessorCell.getG());

        // Calculate H Value
        newCell.setH((maze.getExit().getX() - x + maze.getExit().getY() - y) * 10);

        // Calculate F Value
        newCell.setF(newCell.getG() + newCell.getH());
    }
}


/**
 function A*(start,goal)
 closedset := the empty set    // The set of nodes already evaluated.
 openset := {start}    // The set of tentative nodes to be evaluated, initially containing the start node
 came_from := the empty map    // The map of navigated nodes.

 g_score[start] := 0    // Cost from start along best known path.
 // Estimated total cost from start to goal through y.
 f_score[start] := g_score[start] + heuristic_cost_estimate(start, goal)

 while openset is not empty
 current := the node in openset having the lowest f_score[] value
 if current = goal
 return reconstruct_path(came_from, goal)

 remove current from openset
 add current to closedset
 for each neighbor in neighbor_nodes(current)
 tentative_g_score := g_score[current] + dist_between(current,neighbor)
 if neighbor in closedset
 if tentative_g_score >= g_score[neighbor]
 continue

 if neighbor not in openset or tentative_g_score < g_score[neighbor]
 came_from[neighbor] := current
 g_score[neighbor] := tentative_g_score
 f_score[neighbor] := g_score[neighbor] + heuristic_cost_estimate(neighbor, goal)
 if neighbor not in openset
 add neighbor to openset

 return failure

 function reconstruct_path(came_from, current_node)
 if current_node in came_from
 p := reconstruct_path(came_from, came_from[current_node])
 return (p + current_node)
 else
 return current_node
 */