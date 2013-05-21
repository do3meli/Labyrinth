package ch.zhaw.labyrinth.model.solver;

import ch.zhaw.labyrinth.view.LabyrinthDrawer;

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
