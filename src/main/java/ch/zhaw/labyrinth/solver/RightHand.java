package ch.zhaw.labyrinth.solver;


/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 20.04.13
 * Time: 11:49
 */
/*public class RightHand extends Solver {
    private int[][] maze;
    private int[][] solvedMaze;
    private LabyrinthBuilder labyrinthBuilder;

    public RightHand(int[][] maze, LabyrinthBuilder labyrinthBuilder) {
        this.maze = maze;
        this.solvedMaze = new int[this.maze.length][this.maze.length];
        this.labyrinthBuilder = labyrinthBuilder;


    }

    @Override
    public void solve() {
        // Step counter
        int steps = 0;

        // find entry
        int[] entry = labyrinthBuilder.getEntry();
        int x=entry[0];
        int y=entry[1];

        // set exit
        int[] exit = labyrinthBuilder.getExit();
        int u=exit[0];
        int v=exit[1];

        solvedMaze[x][y] = 4;  // mark entry
        solvedMaze[u][v] = 5;  // mark exit
        // go through labyrinth
        while((x != u) && (y!=v)) {
            steps++;
            if(steps > 1000) { System.out.println(steps); break; }

            // if possible turn right
            if(maze[x][y++] == 0 && solvedMaze[x][y++] != 3) {
                y++;
                solvedMaze[x][y] = 3;
                continue;
            }
            // go down
            else if (maze[x++][y] == 0 && solvedMaze[x++][y] != 3) {
                x++;
                solvedMaze[x][y] = 3;
                continue;
            }
            // go left
            else if (maze[x][y--] == 0 && solvedMaze[x][y--] != 3) {
                y--;
                solvedMaze[x][y] = 3;
                continue;
            }
            // go up
            else if (maze[x--][y] == 0 && solvedMaze[x--][y] != 3) {
                x--;
                solvedMaze[x][y] = 3;
                continue;
            }

        }

        // Print Solved maze to console
        for(int i=0; i<maze.length;i++) {
            for (int j=0; j<maze.length; j++) {
                System.out.print(solvedMaze[i][j]);
            }
            System.out.println();
        }

    }

    public void nextStep() {
    }

}*/
