package ch.zhaw.labyrinth.model.builder;

import ch.zhaw.labyrinth.model.MazeModel;
import ch.zhaw.labyrinth.view.MazeView;

import java.util.Observer;

/**
 * Interface that makes sure build() method is getting implemented
 * in all builder algorithms
 * @author d.schlegel
 */
public interface Builder {
	 /**
     * This method implements the builder algorithm
      */
    MazeModel build();

    void registerObserver(Observer observer);
}
