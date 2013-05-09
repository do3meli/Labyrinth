package ch.zhaw.labyrinth.solver;

import ch.zhaw.labyrinth.utils.Labyrinth;

/**
 * Abstract class which every algorithm has to extend
 */
public abstract class Solver {

    /**
     * This method implements the solving algorithm
     */
    public abstract Labyrinth solve();



}
