package ch.zhaw.labyrinth.model.solver;

import java.util.Observer;

/**
 * Every solver algorithm has to extend this
 */
public interface Solver {

    /**
     * This method implements the solving algorithm
     */
    void solve();

    /**
     * TODO: JavaDoc
     * @param observer
     */
    void registerObserver(Observer observer);
    
}
