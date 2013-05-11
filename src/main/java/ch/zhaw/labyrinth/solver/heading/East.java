package ch.zhaw.labyrinth.solver.heading;

import ch.zhaw.labyrinth.utils.Coordinate;
import ch.zhaw.labyrinth.utils.Labyrinth;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 04.05.13
 * Time: 11:35
 */
public class East implements Heading {
    int x;
    int y;
    Labyrinth maze;
    Labyrinth solvedMaze;

    public East(int x, int y, Labyrinth maze, Labyrinth solvedMaze) {
        this.x = x;
        this.y = y;
        this.maze = maze;
        this.solvedMaze = solvedMaze;
    }

    // Get Coordinate
    public Coordinate getCoordinate() {
        return new Coordinate(getX(), getY());
    }

    public boolean isRight() {
        return maze.getCellValueAt(x, y + 1);
    }

    public boolean isLeft() {
        return maze.getCellValueAt(x, y - 1);
    }

    public boolean isStraight() {
        return maze.getCellValueAt(x + 1, y);
    }

    public boolean isBack() {
        return maze.getCellValueAt(x - 1, y);
    }

    @Override
    public int getVisits(int x, int y) {
        return maze.isCellVisited(x, y);
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
        return new West(x--, y, maze, solvedMaze);
    }

    @Override
    public Labyrinth getSolvedLabyrinth() {
        return solvedMaze;
    }

    // Getter & Setter
    private void storeIt() {
        solvedMaze.setCellValue(x, y, true);
        solvedMaze.setCellVisited(x,y);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
