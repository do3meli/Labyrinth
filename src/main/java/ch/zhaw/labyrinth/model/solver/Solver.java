package ch.zhaw.labyrinth.model.solver;

import java.util.Observer;

/**
 * Every solver algorithm has to extend this
 *
 * @author b.buetikofer
 */
public interface Solver {

    /**
     * This method implements the solving algorithm
     */
    void solve();

    /**
     * This methode registers an observer
     *
     * @param observer
     *          the observer object
     */
    void registerObserver(Observer observer);

    /**
     * This methode prints all visited Cells, not only the path
     */
    void printCheckedCells();
    
}
