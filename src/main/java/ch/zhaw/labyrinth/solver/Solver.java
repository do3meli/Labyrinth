package ch.zhaw.labyrinth.solver;

import ch.zhaw.labyrinth.gui.LabyrinthDrawer;

/**
 * Every solver algorithm has to extend this
 */
public interface Solver {

    /**
     * This method implements the solving algorithm
     * @param labyrinthDrawer
     */
    public abstract void solve(LabyrinthDrawer labyrinthDrawer);
    
}
