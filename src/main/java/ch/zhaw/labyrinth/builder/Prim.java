package ch.zhaw.labyrinth.builder;

import java.util.ArrayList;
import java.util.Arrays;

public class Prim extends LabyrinthBuilder {

	// vars
    private ArrayList<Cell> labyrinth;
	private int[][] mazevisits;
	
	
	// constans
	private static final int VISITED = 1;
	private static final int FRONTIER = 0;

	public Prim(int dim) {
		// call super constructor
		super(dim);
		// create int array for track of visited cells
		this.mazevisits = new int[getDimension()][getDimension()];

        // Create "walled" Labyrinth
        labyrinth = new ArrayList<Cell>();
        for (int i = 0; i < getDimension(); i++) {
            for(int j = 0; j<getDimension(); j++) {
                Cell cell = new Cell(i,j);
                cell.setWall(true);
                labyrinth.add(cell);
            }
        }

		// now create the maze
		createMaze();
		
	}

    /**
     * Start by picking a cell, making it "in", and setting all its neighbors to "frontier".
     * Proceed by picking a "frontier" cell at random, and carving into it from one of its neighbor cells that are "in".
     * Change that "frontier" cell to "in", and update any of its neighbors that are "out" to "frontier".
     * The Maze is done when there are no more "frontier" cells left
     * (which means there are no more "out" cells left either, so they're all "in").
     */
	
	private void createMaze() {
		// choose random cell and set a wall
		int r = getRandomIntOdd(getDimension());
        Cell cell = labyrinth.get(r);
        cell.setWay(true);
        
        // get all its neighbours
        ArrayList<Cell> neighbours = cell.getNeighbours(getDimension());

		// mark this cell now as visited
		mazevisits[cell.getX()][cell.getY()] = VISITED;

        // mark neighbours as frontier
        // TODO: Check for borders
        mazevisits[cell.getX()+1][cell.getY()] = FRONTIER;
        mazevisits[cell.getX()-1][cell.getY()] = FRONTIER;
        mazevisits[cell.getX()][cell.getY()+1] = FRONTIER;
        mazevisits[cell.getX()][cell.getY()-1] = FRONTIER;




		while(!allVisited()) {
           // Pick a random wall from the list.
           x = getRandomIntOdd(getDimension());
           y = getRandomIntOdd(getDimension());

            // If the cell on the opposite side isn't in the maze yet:
            if(mazevisits[x][y] != VISITED) {
                // Make the wall a passage and mark the cell on the opposite side as part of the maze.

                // Add the neighboring walls of the cell to the wall list.
            } else {
                // If the cell on the opposite side already was in the maze, remove the wall from the list.
            }

		}
		
	}
	
	private boolean allVisited(){
		for(int x = 0 ; x < 3 ; x++){
			for(int y = 0 ; y < 3 ; y++){
			      if(this.mazevisits[x][y] == 0){
			    	  return false;
			      }
			}
		}
		return true;
	}


	public void fillArray(){
		for(int x=0; x < this.mazevisits.length; x++){
		    Arrays.fill( this.mazevisits[x], 0 );
		}
	}
	
	
}
