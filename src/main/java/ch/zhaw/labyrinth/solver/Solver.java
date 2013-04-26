package ch.zhaw.labyrinth.solver;

public abstract class Solver {

    public abstract void solve();

    public int[] findEntry(int[][] maze) {
        int[] entry = new int[5];

        while(true) {
            for (int x=0; x<maze.length; x++) {
                if (maze[x][0] == 0) {
                    entry[0]=x;
                    entry[1]=0;
                    return entry;
                }
            }
            for (int y=0; y<maze.length; y++) {
                if (maze[0][y] == 0) {
                    entry[0]=0;
                    entry[1]=y;
                    return entry;
                }
            }
        }


    }


}
