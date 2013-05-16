package ch.zhaw.labyrinth.solver;

import ch.zhaw.labyrinth.gui.LabyrinthDrawer;

/**
 * Abstract class which every solver algorithm has to extend
 */
public interface Solver {

    /**
     * This method implements the solving algorithm
     * @param labyrinthDrawer
     */
    public abstract void solve(LabyrinthDrawer labyrinthDrawer);
    
}
