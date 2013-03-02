package ch.zhaw.labyrinth.builder;

import java.util.Arrays;

/**
 * This implements a DeepFirstSearch algorithm like descripted on
 * at the following website: http://www.migapro.com/depth-first-search/
 * @author Dominic Schlegel
 */
public class DeepFirstSearch extends LabyrinthBuilder {
  
	
	private int x;						   // keep track of current row x
	private int y;						   // keep track of current col y


	// Destructor
	public DeepFirstSearch(int n){
		
		// call super constructor
		super(n);
		// fill whole array with WALL
		Arrays.fill(getMaze(), getWall());
		// set start point in array
		this.x = getRandomInt(getDimension());
		this.y = getRandomInt(getDimension());
		setMaze(x,y,getPath());
		// create whole maze array
		createMaze();
		
	}
	
	
	

	private void createMaze() {
		// TODO Auto-generated method stub
		
	}





	private void getRandomDirection() {
		// UP = 0
		// DOWN = 1
		// RIGHT = 2
		// LEFT = 3
		
		
	}





	
	
	
	
}


