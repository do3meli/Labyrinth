package ch.zhaw.labyrinth.model.solver.heading;

import ch.zhaw.labyrinth.model.MazeModel;

/**
 * This is the East implementation of Heading
 *
 * @author b.buetikofer
 */
public class East extends AbstractHeading implements Heading {

    public East(int x, int y, MazeModel maze, MazeModel solvedMaze) {
        this.x = x;
        this.y = y;
        this.maze = maze;
        this.solvedMaze = solvedMaze;
    }

    // Methods
    @Override
    public boolean isRight() {
        return maze.getCellValueAt(x, y + 1);
    }

    @Override
    public boolean isLeft() {
        return maze.getCellValueAt(x, y - 1);
    }

    @Override
    public boolean isStraight() {
        return maze.getCellValueAt(x + 1, y);
    }

    @Override
    public boolean isBack() {
        return maze.getCellValueAt(x - 1, y);
    }

    // Move methods
    @Override
    public South goRight() {
        // mark current cell as visited
        maze.setCellVisited(x, y);
        // Store it in the solvedMaze Map
        setY(y+1);
        storeIt();
        return new South(x, y, maze, solvedMaze);
    }

    @Override
    public North goLeft() {
        // mark current cell as visited
        maze.setCellVisited(x, y);
        // Store it in the solvedMaze Map
        setY(y - 1);
        storeIt();
        return new North(x, y, maze, solvedMaze);
    }

    @Override
    public East goStraight() {
        // mark current cell as visited
        maze.setCellVisited(x, y);
        // Store it in the solvedMaze Map
        setX(x + 1);
        storeIt();
        return new East(x, y, maze, solvedMaze);
    }

    @Override
    public West goBack() {
        // mark current cell as visited
        maze.setCellVisited(x, y);
        // Store it in the solvedMaze Map
        setX(x - 1);
        storeIt();
        return new West(x, y, maze, solvedMaze);
    }
}
