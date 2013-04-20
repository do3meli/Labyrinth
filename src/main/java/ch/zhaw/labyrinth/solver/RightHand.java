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
        this.solvedMaze = new int[100][100];
    }

    @Override
    public void solve() {
        // find entry
        int x=0;
        int y=findEntry(maze);

        // set exit
        int u=maze.length;
        int v=20; // TODO: findExit(maze)

        // go through labyrinth
        while((x != u) && (y!=v)) {
            // go down
            if(maze[x+1][y+1] == 0) {
                x++;
                y++;
                solvedMaze[x][y] = 3;
                continue;
            }

            // go left
            if(maze[x-1][y+1] == 0) {
                x--;
                y++;
                solvedMaze[x][y] = 3;
                continue;
            }

            // go right
            if(maze[x+1][y-1] == 0) {
                x++;
                y--;
                solvedMaze[x][y] = 3;
                continue;
            }

            // go up
            if(maze[x-1][y-1] == 0) {
                x--;
                y--;
                solvedMaze[x][y] = 3;
                continue;
            }
        }
        for(int i=0; i<maze.length;i++) {
            for (int j=0; j<maze.length; j++) {
                System.out.print(solvedMaze[i][j]);
            }
            System.out.println();
        }

    }

    public void nextStep() {
    }

}
