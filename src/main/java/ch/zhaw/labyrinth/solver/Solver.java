package ch.zhaw.labyrinth.solver;

public abstract class Solver {

    public abstract void solve();

    public int findEntry(int[][] maze) {
        for (int i=0; i<maze.length; i++) {
            if (maze[0][i] == 0) {

                return i;
            }
        }
        return -1; // no entry found
    }
}
