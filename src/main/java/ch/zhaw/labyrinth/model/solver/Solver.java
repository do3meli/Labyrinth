package ch.zhaw.labyrinth.model.solver;

import ch.zhaw.labyrinth.view.MazePanel;

import java.util.Observer;

/**
 * Every solver algorithm has to extend this
 */
public interface Solver {

    /**
     * This method implements the solving algorithm
     * @param mazePanel
     */
    void solve(MazePanel mazePanel);

    void registerObserver(Observer observer);
    
}
