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
    private Labyrinth cameFrom;


    public AStar(Labyrinth maze) {
        this.maze = maze;
        openSet = new Labyrinth();
        closedSet = new Labyrinth();
        cameFrom = new Labyrinth();
    }

    @Override
    public void solve(LabyrinthDrawer labyrinthDrawer) {
        /**
         *  Current coordinates
         */
        Coordinate currentCoordinate = maze.getEntry();
        int x = currentCoordinate.getX();
        int y = currentCoordinate.getY();

        /**
         * Add start point to the openSet, set predecessor to self
         */
        openSet.addPath(currentCoordinate, currentCoordinate);

        /**
         * Add all reachable neighbors to the openSet and add calculate f-cost
         */
        // north
        if(maze.getCellValueAt(x, y + 1)) {
            addAndCalculate(x, y + 1, currentCoordinate, G_HORIZONTAL_VERTICAL);
        }
        // east
        if(maze.getCellValueAt(x + 1, y)) {
            addAndCalculate(x + 1, y, currentCoordinate, G_HORIZONTAL_VERTICAL);
        }

        // west
        if(maze.getCellValueAt(x - 1, y)) {
            addAndCalculate(x - 1, y, currentCoordinate, G_HORIZONTAL_VERTICAL);
        }
        // south
        if(maze.getCellValueAt(x, y - 1)) {
            addAndCalculate(x, y - 1, currentCoordinate, G_HORIZONTAL_VERTICAL);
        }

        // north-east
        if(maze.getCellValueAt(x + 1, y + 1)) {
            addAndCalculate(x + 1, y + 1, currentCoordinate, G_DIAGONAL);
        }
        // south-west
        if(maze.getCellValueAt(x - 1, y - 1)) {
            addAndCalculate(x - 1, y - 1, currentCoordinate, G_DIAGONAL);
        }
        // south-east
        if(maze.getCellValueAt(x + 1, y - 1)) {
            addAndCalculate(x + 1, y - 1, currentCoordinate, G_DIAGONAL);
        }
        // north-west
        if(maze.getCellValueAt(x - 1, y + 1)) {
            addAndCalculate(x - 1, y + 1, currentCoordinate, G_DIAGONAL);
        }

        /**
         * add current cell to the closed set and remove it from the maze
         */
        closedSet.addPath(currentCoordinate);
        openSet.removeCell(currentCoordinate);


    }

    /**
     * Adds a cell to the openSet and calculates its F, G, and H value
     *
     * @param x x-coordinate of the new cell
     * @param y y-coordinate of the new cell
     * @param currentCoordinate coordinate object of the cell from where this method was called
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