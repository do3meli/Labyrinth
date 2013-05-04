package ch.zhaw.labyrinth.solver.heading;

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

    public boolean isRight() {
        return maze.getCellValueAt(x, y+1);
    }

    public boolean isLeft() {
        return maze.getCellValueAt(x, y-1);
    }

    public boolean isStraight() {
        return maze.getCellValueAt(x+1, y);
    }

    public boolean isBack() {
        return maze.getCellValueAt(x-1, y);
    }

    // Move methods
    @Override
    public South goRight() {
            solvedMaze.setCellValue(x, y+1, true);
            return new South(x, y++, maze, solvedMaze);
    }

    @Override
    public North goLeft() {
        solvedMaze.setCellValue(x, y-1, true);
        return new North(x, y--, maze, solvedMaze);
    }

    @Override
    public East goStraight() {
        solvedMaze.setCellValue(x+1, y, true);
        return new East(x++, y, maze, solvedMaze);
    }

    @Override
    public West goBack() {
        solvedMaze.setCellValue(x-1, y, true);
        return new West(x--, y, maze, solvedMaze);
    }
}
