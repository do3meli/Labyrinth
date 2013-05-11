package ch.zhaw.labyrinth.solver;

import ch.zhaw.labyrinth.gui.LabyrinthDrawer;

/**
 * Abstract class which every algorithm has to extend
 */
public abstract class Solver {

    /**
     * This method implements the solving algorithm
     * @param labyrinthDrawer
     */
    public abstract void solve(LabyrinthDrawer labyrinthDrawer);



}
