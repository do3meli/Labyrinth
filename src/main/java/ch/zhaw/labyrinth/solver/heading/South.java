package ch.zhaw.labyrinth.solver.heading;

import ch.zhaw.labyrinth.utils.Coordinate;
import ch.zhaw.labyrinth.utils.Labyrinth;

/**
 * Implements the movements when heading south
 */
public class South implements Heading {
    int x;
    int y;
    Labyrinth maze;
    Labyrinth solvedMaze;

    public South(int x, int y, Labyrinth maze, Labyrinth solvedMaze) {
        this.x = x;
        this.y = y;
        this.maze = maze;
        this.solvedMaze = solvedMaze;
    }

    // Get Coordindate
    public Coordinate getCoordinate() {
        return new Coordinate(getX(), getY());
    }

    // Check methods
    @Override
    public boolean isRight() {
        return maze.getCellValueAt(x-1, y);
    }
    @Override
    public boolean isLeft() {
        return maze.getCellValueAt(x+1, y);
    }
    @Override
    public boolean isStraight() {
        return maze.getCellValueAt(x, y+1);
    }
    @Override
    public boolean isBack() {
        return maze.getCellValueAt(x, y-1);
    }

    @Override
    public boolean isVisited(int x, int y) {
        return maze.isCellVisited(x, y);
    }

    // Move methods
    @Override
    public West goRight() {
        solvedMaze.setCellValue(x-1, y, true);
        setX(x-1);
        maze.setCellVisited(x, y, true);
        return new West(x, y, maze, solvedMaze);
    }

    @Override
    public East goLeft() {
        solvedMaze.setCellValue(x+1, y, true);
        setX(x+1);
        maze.setCellVisited(x, y, true);
        return new East(x, y, maze, solvedMaze);
    }

    @Override
    public South goStraight() {
        solvedMaze.setCellValue(x, y+1, true);
        setY(y+1);
        maze.setCellVisited(x, y, true);
        return new South(x, y, maze, solvedMaze);
    }

    @Override
    public North goBack() {
        solvedMaze.setCellValue(x, y-1, true);
        setY(y-1);
        maze.setCellVisited(x, y, true);
        return new North(x, y, maze, solvedMaze);
    }

    @Override
    public Labyrinth getSolvedLabyrinth() {
        return solvedMaze;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
