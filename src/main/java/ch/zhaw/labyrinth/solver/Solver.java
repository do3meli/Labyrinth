package ch.zhaw.labyrinth.solver;

import ch.zhaw.labyrinth.gui.LabyrinthDrawer;

/**
 * Abstract class which every algorithm has to extend
 * TODO: Should be an Interface
 */
public interface Solver {

    /**
     * This method implements the solving algorithm
     * @param labyrinthDrawer
     */
    public abstract void solve(LabyrinthDrawer labyrinthDrawer);



}
