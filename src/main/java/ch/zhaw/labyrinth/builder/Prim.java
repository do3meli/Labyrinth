package ch.zhaw.labyrinth.builder;

import java.util.Arrays;

public class Prim extends LabyrinthBuilder {

	// vars
	private int[][] mazevisits;
	
	// constans
	private static final int VISITED = 1;
	
	public Prim(int dim) {
		// call super constructor
		super(dim);
		// create int array for track of visited cells
		this.mazevisits = new int[getDimension()][getDimension()];
		// fill mazevisited array
		fillArray();
		// now create the maze
		createMaze();
		
	}
	
	
	
	private void createMaze() {
		// choose random cell
		int x = getRandomIntOdd(getDimension());
		int y = getRandomIntOdd(getDimension());
		
		// mark this cell now as visited
		this.mazevisits[x][y] = VISITED;
		
		while(!allVisited()) {
			
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
