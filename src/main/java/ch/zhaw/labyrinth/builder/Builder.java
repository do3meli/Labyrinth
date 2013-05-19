package ch.zhaw.labyrinth.builder;

import ch.zhaw.labyrinth.utils.Labyrinth;

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
    public Labyrinth build();
}
