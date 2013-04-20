package ch.zhaw.labyrinth.solver;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 20.04.13
 * Time: 11:49
 */
public class RightHand extends Solver {
    private int[][] maze;
    private int[][] solvedMaze;

    public RightHand(int[][] maze) {
        this.maze = maze;
    }

    @Override
    public void solve() {
        // find entry
        int x=0;
        int y=findEntry(maze);

        // set exit
        int u=40;
        int v=20;

        // go through labyrinth
        while((x != u) && (y!=v)) {
            if(maze[x+1][y] == 1) {
                x++;
                y++;
            }

        }

    }

    public void nextStep() {
    }

}
