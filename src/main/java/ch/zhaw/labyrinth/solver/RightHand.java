package ch.zhaw.labyrinth.solver;

import ch.zhaw.labyrinth.builder.LabyrinthBuilder;
import ch.zhaw.labyrinth.gui.Labyrinth;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 20.04.13
 * Time: 11:49
 */
public class RightHand extends Solver {
    private int[][] labyrinth;

    public RightHand(int[][] labyrinth) {
        this.labyrinth = labyrinth;
    }

    @Override
    public void solve() {
        // find entry FIXME: Move to parent

        // go through labyrinth
        for(int i=0; i<labyrinth.length; i++) {
            for (int j=0; j<labyrinth.length; j++) {
                System.out.print(labyrinth[i][j]);
            }
            System.out.println();
        }

    }

    public void nextStep() {
    }

}
