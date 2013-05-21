package ch.zhaw.labyrinth.model.builder;

import ch.zhaw.labyrinth.model.utils.MazeModel;

/**
 * Interface that makes sure build() method is getting implemented
 * in all builder algorithms
 * @author d.schlegel
 */
public interface Builder {
	 /**
     * This method implements the builder algorithm
     * @param Labyrinth
     */
    public MazeModel build();
}
